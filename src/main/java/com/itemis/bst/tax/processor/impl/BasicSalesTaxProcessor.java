package com.itemis.bst.tax.processor.impl;

import com.itemis.bst.model.Item;
import com.itemis.bst.tax.processor.TaxProcessor;

import java.math.BigDecimal;

/**
 * Processes a single Basic Sales Tax implementation
 */
public class BasicSalesTaxProcessor extends AbstractChainTaxProcessor<Item> {

    public BasicSalesTaxProcessor(TaxProcessor<Item> nextProcessor) {
        super(nextProcessor);
    }

    @Override
    protected BigDecimal processInternal(Item item) {
        if (item.isExempted()) {
            return item.getPrice().multiply(BigDecimal.valueOf(0.1));
        }
        return BigDecimal.valueOf(0.0);
    }
}
