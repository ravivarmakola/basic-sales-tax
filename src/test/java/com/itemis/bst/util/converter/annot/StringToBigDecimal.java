package com.itemis.bst.util.converter.annot;


import com.itemis.bst.util.converter.BigDecimalConverter;
import org.junit.jupiter.params.converter.ConvertWith;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
@ConvertWith(BigDecimalConverter.class)
public @interface StringToBigDecimal {
}
