package com.itemis.bst.io;

import com.itemis.bst.exception.ConversionException;
import com.itemis.bst.model.Item;
import com.itemis.bst.util.converter.annot.CsvToItem;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class IOUtilTest {

    @ParameterizedTest(name = "{index}")
    @CsvFileSource(resources = "/item-convert-failures.csv", numLinesToSkip = 1)
    public void testConvertWhenInvalidParameters(String lineItem, Class<? extends Throwable> exceptionClass) {
        assertThrows(exceptionClass, () -> IOUtil.convert(lineItem));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/item-convert.csv", numLinesToSkip = 1)
    public void testConvertWhenSuccess(String lineItem, @CsvToItem Item expected) throws ConversionException {
        Item actual = IOUtil.convert(lineItem);
        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @MethodSource("generateLineItems")
    public void testConvertListWhenAllValidStrings(List<String> listOfLineItems, Integer expectedCount) throws ConversionException {
        List<Item> actual = IOUtil.convert(listOfLineItems);
        assertEquals(expectedCount, actual.size());
    }

    static Stream<Arguments> generateLineItems() {
        return Stream.of(
                Arguments.of(Arrays.asList("1 imported bottle of perfume at 27.99", "1 bottle of perfume at 18.99"), 2),
                Arguments.of(Arrays.asList("Invalid Line Item", "1 bottle of perfume at 18.99"), 1),
                Arguments.of(Arrays.asList("Invalid Line Item", "Another invalid Line Item"), 0),
                Arguments.of(Collections.EMPTY_LIST, 0),
                Arguments.of(null, 0)
        );
    }

    @ParameterizedTest
    @NullAndEmptySource
    public void testFileReadWhenEmptyOrNullArgs(String path) {
        assertThrows(IllegalArgumentException.class, () -> IOUtil.readFile(path));
    }

    @ParameterizedTest
    @CsvSource(value = {"src/test/resources/test-data-1.txt, 3", "src/test/resources/test-data-2.txt,2", "src/test/resources/test-data-3.txt,4"})
    public void testFileReadWhenValidFilePath(Path path, int numberOfLineItems) throws IOException {
        List<Item> actual = IOUtil.readFile(path.toFile().getAbsolutePath());
        assertEquals(numberOfLineItems, actual.size());
    }
}