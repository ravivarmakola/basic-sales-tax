package com.itemis.bst.util.converter;

import org.junit.jupiter.params.converter.ArgumentConversionException;
import org.junit.jupiter.params.converter.SimpleArgumentConverter;

import java.math.BigDecimal;

public class BigDecimalConverter extends SimpleArgumentConverter {
    @Override
    protected BigDecimal convert(Object source, Class<?> targetType) throws ArgumentConversionException {
        if (source == null) {
            return null;
        }
        return new BigDecimal((String) source);
    }
}
