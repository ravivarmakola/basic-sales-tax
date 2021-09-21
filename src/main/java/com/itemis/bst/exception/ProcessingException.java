package com.itemis.bst.exception;

import lombok.Getter;

@Getter
public class ProcessingException extends Exception {
    private String message;
}
