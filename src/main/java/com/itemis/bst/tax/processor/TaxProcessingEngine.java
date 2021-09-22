package com.itemis.bst.tax.processor;

import com.itemis.bst.exception.ProcessingException;
import com.itemis.bst.model.Item;
import com.itemis.bst.model.ProcessResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class TaxProcessingEngine {
    @NonNull
    private TaxProcessor<Item> taxProcessor;

    /**
     * Processes the tax calculation
     *
     * @param itemList {@link List} of {@link Item}
     * @return Returns {@link ProcessResponse} contains the Line Item price (tax inclusive), Total sales taxes and Total bill amount
     * @throws ProcessingException when there is an exception while processing taxes
     */
    public ProcessResponse calculateBill(List<Item> itemList) throws ProcessingException {
        return null;
    }
}
