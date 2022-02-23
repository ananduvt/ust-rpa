/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ust.keysim.ui;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author u55369
 */
public class BackEndUtils {

    public static void main(String[] args) {
      
    }

    public static Map<String, List<String>> getOperationMap(String inputFile) {

        List<List<String>> list = loadInputToList(inputFile);
        Map<String, List<String>> map = new LinkedHashMap<>();
        for (List<String> temp : list) {
            map.put(temp.remove(0), temp);
        }
        System.out.println(inputFile + " : Loaded Successfully");
        System.out.println(map);
        return map;
    }

    public static List<List<String>> getDataList(String inputFile) {
        List<List<String>> list = loadInputToList(inputFile);
        System.out.println(inputFile + " : Loaded Successfully");
        System.out.println(list);
        return list;
    }

    private static List<List<String>> loadInputToList(String inputFile) {

        List<List<String>> inputList = new ArrayList<>();
        FileInputStream file = null;
        ArrayList temp = null;
        try {
            file = new FileInputStream(new File(inputFile));
        } catch (FileNotFoundException ex) {
            System.out.println("ERROR : Configuration file can not be openened");
        }
        //Create Workbook instance holding reference to .xlsx file
        XSSFWorkbook workbook = null;
        try {
            workbook = new XSSFWorkbook(file);
        } catch (IOException ex) {
            System.out.println("ERROR : Can not open the excel sheet");
        }
        //Get first/desired sheet from the workbook
        XSSFSheet sheet = workbook.getSheetAt(0);
        Row cCount = sheet.getRow(0);
        int cellCount = cCount.getLastCellNum();

        for (int i = 0; i < cellCount; i++) {
            Iterator<Row> rowIterator = sheet.iterator();
            temp = new ArrayList();
            // System.out.println("loop "+i);
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                //For each row, iterate through all the columns
                Cell cell = row.getCell(i);
                try {
                    switch (cell.getCellType()) {
                        case Cell.CELL_TYPE_BLANK:
                            temp.add("");
                            //System.out.println("blank");
                            break;
                        case Cell.CELL_TYPE_NUMERIC:
                            // System.out.print(cell.getNumericCellValue() + "\t");
                            cell.setCellType(Cell.CELL_TYPE_STRING);
                            temp.add(cell.getStringCellValue());
                            break;
                        case Cell.CELL_TYPE_STRING:
                            //System.out.print(cell.getStringCellValue() + "\t");
                            temp.add(cell.getStringCellValue());
                            break;
                    }
                } catch (NullPointerException e) {
                    //System.out.println("null value");
                }
            }
            inputList.add(temp);
        }
        try {
            file.close();
        } catch (IOException ex) {

            System.out.println("ERROR : File can not be closed");
        }
        // System.out.println(inputFile + " : Loaded successfully \n" + inputList);
        return inputList;
    }

}
