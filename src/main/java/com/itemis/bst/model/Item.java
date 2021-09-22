package com.itemis.bst.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.math.BigDecimal;

@AllArgsConstructor
@Getter
@EqualsAndHashCode
public class Item {
    private int units;
    private String name;
    private boolean exempted;
    private boolean imported;
    private BigDecimal price;

    @Override
    public String toString() {
        String newLine = System.lineSeparator();
        String format = "%d %s: %.2f" + newLine;
        String EMPTY_STRING = "";
        String IMPORTED_STR = "imported ";
        return String.format(format, units, (imported ? IMPORTED_STR : EMPTY_STRING) + name, price);
    }
}
