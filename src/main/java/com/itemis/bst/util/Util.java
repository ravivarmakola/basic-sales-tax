package com.itemis.bst.util;

import lombok.NonNull;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Util {
    /**
     * Rounds the value to nearest value based on the {@link RoundingMode}
     *
     * @param num
     * @param nearest
     * @param roundingMode
     * @return
     */
    public static BigDecimal round(@NonNull BigDecimal num, @NonNull BigDecimal nearest,
                                   @NonNull RoundingMode roundingMode) {
        return nearest.signum() == 0 ? num : num.divide(nearest, 0, roundingMode).multiply(nearest);
    }

    /**
     * <pre>
     * Checks if the String is empty or null
     * |Value                               |Result |
     * |null                                |true   |
     * |Empty String                        |true   |
     * |A Space                             |true   |
     * |A String with at least 1 character  | false |
     * </pre>
     *
     * @param lineItem
     * @return true or false based on above table
     */
    public static boolean isNullOrEmpty(String lineItem) {
        return lineItem == null || lineItem.trim().isEmpty();
    }
}
