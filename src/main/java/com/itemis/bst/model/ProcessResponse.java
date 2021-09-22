package com.itemis.bst.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Getter
public class ProcessResponse {
    private List<Item> itemList;
    private BigDecimal totalTaxes;
    private BigDecimal total;

    @Override
    public String toString() {
        String newLine = System.lineSeparator();
        String output = itemList.stream()
                .map(item -> item.toString())
                .collect(Collectors.joining());
        String formatTotals = "Sales Tax: %.2f" + newLine + "Total: %.2f";
        return output + String.format(formatTotals, totalTaxes, total);
    }
}
