package com.itemis.bst.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@Getter
public class ProcessResponse {
    private List<Item> itemList;
    private BigDecimal totalTaxes;
    private BigDecimal total;
}
