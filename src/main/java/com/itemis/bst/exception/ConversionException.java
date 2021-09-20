package com.itemis.bst.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ConversionException extends Exception {
    private String message;
}
