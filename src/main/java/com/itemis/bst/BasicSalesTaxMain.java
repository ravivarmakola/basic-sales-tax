package com.itemis.bst;

import com.itemis.bst.io.IOUtil;
import com.itemis.bst.model.Item;
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
            // TODO Invoke Tax Processing Engine to process the items
            // TODO Write the output to file
        } catch (IOException e) {
            log.log(Level.SEVERE, "Either the path ({0}) is wrong or the file doesn't exists", filePath);
        }
    }
}
