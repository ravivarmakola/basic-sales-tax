package com.itemis.bst.tax.processor;

import com.itemis.bst.exception.ProcessingException;
import com.itemis.bst.model.Item;
import com.itemis.bst.model.ProcessResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
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
    public ProcessResponse calculateBill(@NonNull List<Item> itemList) throws ProcessingException {
        if (itemList.isEmpty()) {
            throw new IllegalArgumentException("Items cannot be empty");
        }
        BigDecimal totalSalesTax = BigDecimal.valueOf(0);
        BigDecimal totalPrice = BigDecimal.valueOf(0);
        List<Item> updatedList = new ArrayList<>();
        Item updatedItem = null;
        for (Item item : itemList) {
            BigDecimal itemTax = round(taxProcessor.process(item), BigDecimal.valueOf(0.05), RoundingMode.UP);
            totalSalesTax = totalSalesTax.add(itemTax);
            BigDecimal itemPrice = item.getPrice().add(itemTax);
            totalPrice = totalPrice.add(itemPrice);
            updatedItem = new Item(item.getUnits(), item.getName(), item.isExempted(), item.isImported(), itemPrice);
            updatedList.add(updatedItem);
        }
        return new ProcessResponse(updatedList, totalSalesTax, totalPrice);
    }

    public static BigDecimal round(BigDecimal num, BigDecimal feed,
                                   RoundingMode roundingMode) {
        return feed.signum() == 0 ? num : num.divide(feed, 0, roundingMode).multiply(feed);
    }
}
