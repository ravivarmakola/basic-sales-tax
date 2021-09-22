package com.itemis.bst;

import com.itemis.bst.exception.ProcessingException;
import com.itemis.bst.model.Item;
import com.itemis.bst.model.ProcessResponse;
import com.itemis.bst.tax.processor.TaxProcessingEngine;
import com.itemis.bst.tax.processor.TaxProcessor;
import com.itemis.bst.tax.processor.impl.BasicSalesTaxProcessor;
import com.itemis.bst.tax.processor.impl.ImportedSalesTaxProcessor;
import com.itemis.bst.util.io.IOUtil;
import lombok.extern.java.Log;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;

@Log
public class BasicSalesTaxMain {
    public static void main(String[] args) {
        String filePath = System.getProperty("input-path");
        try {
            List<Item> itemList = IOUtil.readFile(filePath);
            log.info(itemList.toString());
            TaxProcessor processingChain = new BasicSalesTaxProcessor(new ImportedSalesTaxProcessor(null));
            TaxProcessingEngine taxProcessingEngine = new TaxProcessingEngine(processingChain);
            ProcessResponse response = taxProcessingEngine.calculateBill(itemList);
            // TODO Write the output to file
        } catch (IOException e) {
            log.log(Level.SEVERE, "Either the path ({0}) is wrong or the file doesn't exists", filePath);
        } catch (ProcessingException e) {
            e.printStackTrace();
        }
    }
}
