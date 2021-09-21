package com.itemis.bst.tax.processor.impl;

import com.itemis.bst.exception.ProcessingException;
import com.itemis.bst.tax.processor.TaxProcessor;
import lombok.AllArgsConstructor;

import java.math.BigDecimal;

/**
 * Defines a family of Chain of {@link TaxProcessor}
 *
 * @param <E>
 */
@AllArgsConstructor
public abstract class AbstractChainTaxProcessor<E> implements TaxProcessor<E> {
    private TaxProcessor<E> nextProcessor;

    /**
     * Process the {@link TaxProcessor} in chain fashion
     *
     * @param e Type of Object
     * @return
     * @throws ProcessingException
     */
    @Override
    public BigDecimal process(E e) throws ProcessingException {
        return processInternal(e);
    }

    /**
     * Implementation of a single Tax
     *
     * @param e
     * @return
     * @throws ProcessingException
     */
    protected abstract BigDecimal processInternal(E e) throws ProcessingException;

}
