package com.itemis.bst.tax.processor;

import com.itemis.bst.exception.ProcessingException;

import java.math.BigDecimal;

/**
 * Processor to execute a single Tax
 *
 * @param <E>
 */
public interface TaxProcessor<E> {
    /**
     * Single execution unit of Tax processing
     *
     * @param e Type of Object
     * @return tax value of a single tax
     * @throws ProcessingException when something goes wrong while processing tax
     */
    BigDecimal process(E e) throws ProcessingException;
}
