package com.itemis.bst;

import com.itemis.bst.exception.ProcessingException;
import com.itemis.bst.model.Item;
import com.itemis.bst.model.ProcessResponse;
import com.itemis.bst.tax.processor.TaxProcessingEngine;
import com.itemis.bst.tax.processor.TaxProcessor;
import com.itemis.bst.tax.processor.impl.BasicSalesTaxProcessor;
import com.itemis.bst.tax.processor.impl.ImportedSalesTaxProcessor;
import com.itemis.bst.util.Util;
import com.itemis.bst.util.io.IOUtil;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class BasicSalesTaxMain {
    public static void main(String[] args) {
        String filePathArgument = "input-file";
        String filePath = System.getProperty(filePathArgument);
        if (Util.isNullOrEmpty(filePath) || !Path.of(filePath).isAbsolute()) {
            System.err.println("Please pass a VM arg in format: -D" + filePathArgument + "=<Complete file path>");
            return;
        }
        try {
            List<Item> itemList = IOUtil.readFile(filePath);
            TaxProcessor processingChain = new BasicSalesTaxProcessor(new ImportedSalesTaxProcessor(null));
            TaxProcessingEngine taxProcessingEngine = new TaxProcessingEngine(processingChain);
            ProcessResponse response = taxProcessingEngine.calculateBill(itemList);
            printResponse(Path.of(filePath).getParent(), response);
        } catch (IOException e) {
            System.err.println("Either the path (" + filePath + ") is wrong or the file doesn't exists");
        } catch (ProcessingException e) {
            System.err.println("Unable to process request");
        }
    }

    private static void printResponse(Path folder, ProcessResponse response) {
        if (response == null) {
            System.err.println("Unable to process request");
            return;
        }
        try {
            String outputFilePath = folder.toFile().getAbsolutePath() + "/output.txt";
            System.out.println(response);
            System.out.println("Output File: " + outputFilePath);
            Files.write(Path.of(outputFilePath), response.toString().getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            System.err.println("Unable to write to file. Please find the output below");
            System.out.println(response.toString());
        }
    }
}
