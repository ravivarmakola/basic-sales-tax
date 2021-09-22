package com.itemis.bst.tax.processor.impl;

import com.itemis.bst.exception.ProcessingException;
import com.itemis.bst.model.Item;
import com.itemis.bst.tax.processor.TaxProcessor;
import com.itemis.bst.util.PropertyUtil;
import com.itemis.bst.util.converter.annot.CsvToItem;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ImportSalesTaxProcessorTest {
    @BeforeAll
    public static void init() {
        PropertyUtil.load("src/test/resources/app.properties");
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/tax/import-sales-tax.csv", numLinesToSkip = 1)
    public void testProcessWithDifferentTaxes(BigDecimal expectedPrice, @CsvToItem Item item) throws ProcessingException {
        BigDecimal actualPrice = testBasicSalesTax(item);
        assertEquals(expectedPrice, actualPrice);
    }

    @Test
    public void testProcessWithDifferentTaxes() {
        assertThrows(NullPointerException.class, () -> testBasicSalesTax(null));
    }

    private BigDecimal testBasicSalesTax(Item item) throws ProcessingException {
        TaxProcessor processor = new ImportedSalesTaxProcessor(null);
        BigDecimal actualPrice = processor.process(item);
        return actualPrice;
    }
}
