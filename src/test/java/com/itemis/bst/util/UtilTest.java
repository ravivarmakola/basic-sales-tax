package com.itemis.bst.util;

import com.itemis.bst.util.converter.annot.StringToBigDecimal;
import com.itemis.bst.util.converter.annot.StringToRounding;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class UtilTest {
    @ParameterizedTest
    @CsvSource(value = {"1,2,", ",,", "1,,", ",2,", ",,UP"})
    public void testRoundWithDifferent(@StringToBigDecimal BigDecimal number, @StringToBigDecimal BigDecimal nearest, @StringToRounding RoundingMode roundingMode) {
        assertThrows(IllegalArgumentException.class, () -> Util.round(number, nearest, roundingMode));
    }

    @Test
    public void testRoundWhenValidValue() {
        BigDecimal actualValue = Util.round(BigDecimal.valueOf(0.5625), BigDecimal.valueOf(0.05), RoundingMode.UP);
        assertEquals(BigDecimal.valueOf(0.60).setScale(2), actualValue);
    }
}
