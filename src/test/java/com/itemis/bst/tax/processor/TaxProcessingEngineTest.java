package com.itemis.bst.tax.processor;

import com.itemis.bst.exception.ProcessingException;
import com.itemis.bst.model.Item;
import com.itemis.bst.model.ProcessResponse;
import com.itemis.bst.tax.processor.impl.BasicSalesTaxProcessor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.NullAndEmptySource;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TaxProcessingEngineTest {

    @Test
    public void testCreateTaxProcessingEngineWithNullChain() {
        assertThrows(IllegalArgumentException.class, () -> new TaxProcessingEngine(null));
    }

    @ParameterizedTest
    @NullAndEmptySource
    public void testCalculateBillWhenListInvalid(List<Item> itemList) {
        TaxProcessingEngine engine = new TaxProcessingEngine(new BasicSalesTaxProcessor(null));
        assertThrows(IllegalArgumentException.class, () -> engine.calculateBill(itemList));
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/tax/all-sales.list.csv", numLinesToSkip = 1)
    public void testCalculateBill(String netPrices, String taxesApplicable, String grossPrices, String totalTax, String totalBill) throws ProcessingException {
        String[] netPricesArray = netPrices.split(";");
        String[] taxesApplicableArray = taxesApplicable.split(";");
        String[] grossPricesArray = grossPrices.split(";");

        TaxProcessor taxProcessor = mock(BasicSalesTaxProcessor.class);
        List<Item> itemList = prepareMocks(netPricesArray, taxesApplicableArray, taxProcessor);

        TaxProcessingEngine engine = new TaxProcessingEngine(taxProcessor);

        ProcessResponse processResponse = engine.calculateBill(itemList);

        List<Item> actualList = processResponse.getItemList();

        for (int i = 0; i < grossPricesArray.length; i++) {
            assertEquals(new BigDecimal(grossPricesArray[i]), actualList.get(i).getPrice());
        }
        assertEquals(new BigDecimal(totalTax), processResponse.getTotalTaxes());
        assertEquals(new BigDecimal(totalBill), processResponse.getTotal());
    }

    private List<Item> prepareMocks(String[] netPricesArray, String[] taxesApplicableArray, TaxProcessor taxProcessor) throws ProcessingException {
        List<Item> itemList = new ArrayList<>();
        Item item;
        for (int i = 0; i < netPricesArray.length; i++) {
            item = new Item(1, "X", false, false, new BigDecimal(netPricesArray[i]));
            String tax = taxesApplicableArray[i];
            tax = tax.substring(0, tax.length() - 1);
            when(taxProcessor.process(item)).thenReturn(new BigDecimal(tax).multiply(new BigDecimal(netPricesArray[i])).divide(BigDecimal.valueOf(100)));
            itemList.add(item);
        }
        return itemList;
    }
}
