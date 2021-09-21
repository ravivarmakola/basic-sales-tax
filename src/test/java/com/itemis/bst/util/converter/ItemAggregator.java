package com.itemis.bst.util.converter;

import com.itemis.bst.model.Item;
import org.junit.jupiter.api.extension.ParameterContext;
import org.junit.jupiter.params.aggregator.ArgumentsAccessor;
import org.junit.jupiter.params.aggregator.ArgumentsAggregationException;
import org.junit.jupiter.params.aggregator.ArgumentsAggregator;

import java.math.BigDecimal;

public class ItemAggregator implements ArgumentsAggregator {

    @Override
    public Item aggregateArguments(ArgumentsAccessor accessor, ParameterContext context) throws ArgumentsAggregationException {
        return new Item(accessor.getInteger(1),
                accessor.getString(2),
                accessor.getBoolean(3),
                accessor.getBoolean(4),
                new BigDecimal(accessor.getString(5)));
    }
}
