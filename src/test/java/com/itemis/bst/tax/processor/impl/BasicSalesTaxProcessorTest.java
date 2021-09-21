package com.itemis.bst.tax.processor.impl;

import com.itemis.bst.exception.ProcessingException;
import com.itemis.bst.model.Item;
import com.itemis.bst.util.converter.annot.CsvToItem;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BasicSalesTaxProcessorTest {
    @ParameterizedTest
    @CsvFileSource(resources = "/tax/basic-sales-tax.csv", numLinesToSkip = 1)
    public void testProcessWithDifferentTaxes(BigDecimal expectedPrice, @CsvToItem Item item) throws ProcessingException {
        BigDecimal actualPrice = testBasicSalesTax(item);
        assertEquals(expectedPrice, actualPrice);
    }

    @Test
    public void testProcessWithDifferentTaxes() {
        assertThrows(ProcessingException.class, () -> testBasicSalesTax(null));
    }

    private BigDecimal testBasicSalesTax(Item item) throws ProcessingException {
        BasicSalesTaxProcessor processor = new BasicSalesTaxProcessor(null);
        BigDecimal actualPrice = processor.process(item);
        return actualPrice;
    }
}
