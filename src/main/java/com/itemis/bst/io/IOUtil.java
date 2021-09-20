package com.itemis.bst.io;

import com.itemis.bst.exception.ConversionException;
import com.itemis.bst.model.Item;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class IOUtil {
    /**
     * Converts a line item to {@link Item} object
     *
     * @param lineItem should be in a specific pattern
     * @return Item object
     * @throws ConversionException when lineItem is not in desired format
     */
    public static Item convert(String lineItem) throws ConversionException {
        return null;
    }

    /**
     * Converts {@link List} of {@link String} lineItems to {@link List} of {@link Item} objects
     *
     * @param lineItems {@link List} of {@link String}
     * @return List of {@link Item}
     */
    public static List<Item> convert(List<String> lineItems) {
        return Collections.emptyList();
    }

    /**
     * Reads the file from the filePath and generates the {@link List} of {@link Item}
     *
     * @param filePath File filePath in which Line Items exists
     * @return List of {@link Item}
     * @throws IOException when invalid path or file doesnt exists
     */
    public static List<Item> readFile(String filePath) throws IOException {
        return Collections.emptyList();
    }
}
