package com.itemis.bst.tax.processor.impl;

import com.itemis.bst.model.Item;
import com.itemis.bst.tax.processor.TaxProcessor;

import java.math.BigDecimal;

/**
 * Processes a single Import Tax implementation
 */
public class ImportedSalesTaxProcessor extends AbstractChainTaxProcessor<Item> {
    public ImportedSalesTaxProcessor(TaxProcessor<Item> nextProcessor) {
        super(nextProcessor);
    }

    @Override
    protected BigDecimal processInternal(Item item) {
        return null;
    }
}