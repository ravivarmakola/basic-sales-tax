package com.itemis.bst.tax.processor.impl;

import com.itemis.bst.model.Item;
import com.itemis.bst.tax.processor.TaxProcessor;

import java.math.BigDecimal;

import static com.itemis.bst.util.PropertyUtil.getPercent;

/**
 * Processes a single Import Tax implementation
 */
public class ImportedSalesTaxProcessor extends AbstractChainTaxProcessor<Item> {
    public ImportedSalesTaxProcessor(TaxProcessor<Item> nextProcessor) {
        super(nextProcessor);
    }

    @Override
    protected BigDecimal processInternal(Item item) {
        if (item.isImported()) {
            return item.getPrice().multiply(BigDecimal.valueOf(getPercent("app.basic.import.tax")));
        }
        return BigDecimal.valueOf(0.0);
    }
}
