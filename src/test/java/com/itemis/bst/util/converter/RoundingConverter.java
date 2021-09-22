package com.itemis.bst.util.converter;

import org.junit.jupiter.params.converter.ArgumentConversionException;
import org.junit.jupiter.params.converter.SimpleArgumentConverter;

import java.math.RoundingMode;

public class RoundingConverter extends SimpleArgumentConverter {
    @Override
    protected RoundingMode convert(Object source, Class<?> targetType) throws ArgumentConversionException {
        if (source == null) {
            return null;
        }
        return RoundingMode.valueOf((String) source);
    }
}
