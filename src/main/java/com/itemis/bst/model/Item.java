package com.itemis.bst.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
public class Item {
    private int units;
    private String name;
    private boolean exempted;
    private boolean imported;
    private double price;
}
