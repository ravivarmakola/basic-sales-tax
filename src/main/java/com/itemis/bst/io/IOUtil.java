package com.itemis.bst.io;

import com.itemis.bst.exception.ConversionException;
import com.itemis.bst.model.Item;
import lombok.extern.java.Log;
import org.apache.commons.math3.util.Pair;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Log
public class IOUtil {

    public static final String IMPORTED = "imported ";
    private static Pattern lineItemPattern = Pattern.compile("^(\\d+) (.+) at (\\d+\\.\\d+)$");
    private static List<String> exemptedList = List.of("chocolate", "pill", "book");

    /**
     * Converts a line item to {@link Item} object
     *
     * @param lineItem should be in a specific pattern
     * @return Item object
     * @throws ConversionException when lineItem is not in desired format
     */
    public static Item convert(String lineItem) throws ConversionException {
        if (isNullOrEmpty(lineItem)) {
            throw new IllegalArgumentException("Line item cannot be empty");
        }
        Matcher matcher = lineItemPattern.matcher(lineItem);
        int noOfUnits;
        Pair<String, Boolean> productNameAndImported;
        boolean exempted;
        BigDecimal price;
        if (matcher.find()) {
            noOfUnits = extractNoOfUnits(matcher.group(1));
            productNameAndImported = extractProductName(matcher.group(2));
            exempted = extractExemptedStatus(productNameAndImported.getKey());
            price = extractPrice(matcher.group(3));
            return new Item(noOfUnits, productNameAndImported.getKey(), exempted, productNameAndImported.getValue(), price);
        }
        throw new ConversionException("Unable to parse the item: " + lineItem);
    }

    private static BigDecimal extractPrice(String price) throws ConversionException {
        if (isNullOrEmpty(price)) {
            throw new ConversionException("Product price is empty");
        }
        return new BigDecimal(price);
    }

    private static boolean extractExemptedStatus(String key) {
        if (isNullOrEmpty(key)) {
            return false;
        }
        return exemptedList.stream().anyMatch(key::contains);
    }

    private static boolean isNullOrEmpty(String lineItem) {
        return lineItem == null || lineItem.trim().isEmpty();
    }

    private static Pair<String, Boolean> extractProductName(String productName) throws ConversionException {
        if (isNullOrEmpty(productName)) {
            throw new ConversionException("Product Name is empty");
        }
        boolean imported = false;
        if (productName.contains(IMPORTED)) {
            imported = true;
            productName = productName.replace(IMPORTED, "");
        }
        return new Pair<>(productName, imported);
    }

    private static int extractNoOfUnits(String noOfUnits) throws ConversionException {
        if (isNullOrEmpty(noOfUnits)) {
            throw new ConversionException("Number of units are empty");
        }
        return Integer.parseInt(noOfUnits);
    }

    /**
     * Converts {@link List} of {@link String} lineItems to {@link List} of {@link Item} objects
     *
     * @param lineItems {@link List} of {@link String}
     * @return List of {@link Item}
     */
    public static List<Item> convert(List<String> lineItems) {
        if (lineItems == null) {
            return List.of();
        }
        return lineItems.stream().map(IOUtil::convertSafe).filter(Objects::nonNull).collect(Collectors.toUnmodifiableList());
    }

    public static Item convertSafe(String lineItem) {
        try {
            return convert(lineItem);
        } catch (ConversionException conversionException) {
            log.log(Level.SEVERE, conversionException.getMessage());
        }
        return null;
    }

    /**
     * Reads the file from the filePath and generates the {@link List} of {@link Item}
     *
     * @param filePath File filePath in which Line Items exists
     * @return List of {@link Item}
     * @throws IOException when invalid path or file doesnt exists
     */
    public static List<Item> readFile(String filePath) throws IOException {
        if (isNullOrEmpty(filePath)) {
            throw new IllegalArgumentException("File path cannot be null");
        }
        Path path = Path.of(filePath);
        List<String> lineItems = Files.readAllLines(path);
        return convert(lineItems);
    }
}
