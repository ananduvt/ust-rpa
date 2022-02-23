/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Util;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author u55369
 */
public class FileLoader {

    private static String configurationFile;
    private static String ComponentFile;

    private static List<List<String>> operationsList;
    private static List<List<String>> componentsList;

    public static String getConfigurationFile() {
        return configurationFile;
    }

    public static String getComponentFile() {
        return ComponentFile;
    }

    public static void setConfigurationFile(String configurationFile) {

        FileLoader.configurationFile = configurationFile;
        loadOperationList();
    }

    public static void setComponentFile(String ComponentFile) {
        FileLoader.ComponentFile = ComponentFile;
        loadComponentList();
    }

    public static List<List<String>> getOperationsList() {
        return operationsList;
    }

    public static List<List<String>> getComponentsList() {
        return componentsList;
    }

    private static void loadOperationList() {

        operationsList = new ArrayList<>();
        FileInputStream file = null;
        ArrayList temp = null;
        try {
            file = new FileInputStream(new File(configurationFile));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileLoader.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("ERROR : Configuration file can not be openened");
        }

        //Create Workbook instance holding reference to .xlsx file
        XSSFWorkbook workbook = null;
        try {
            workbook = new XSSFWorkbook(file);
        } catch (IOException ex) {
            Logger.getLogger(FileLoader.class.getName()).log(Level.SEVERE, null, ex);
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
                            //System.out.print(cell.getNumericCellValue() + "\t");
                            temp.add(String.valueOf(cell.getNumericCellValue()));

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
            operationsList.add(temp);
        }

        try {
            file.close();
        } catch (IOException ex) {
            Logger.getLogger(FileLoader.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("ERROR : File can not be closed");
        }

        System.out.println("The Operation List loaded successfully \n" + operationsList);

    }

    private static void loadComponentList() {

        componentsList = new ArrayList<>();
        FileInputStream file = null;
        ArrayList temp = null;
        try {
            file = new FileInputStream(new File(ComponentFile));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(FileLoader.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("ERROR : Component file can not be openened");
        }

        //Create Workbook instance holding reference to .xlsx file
        XSSFWorkbook workbook = null;
        try {
            workbook = new XSSFWorkbook(file);
        } catch (IOException ex) {
            Logger.getLogger(FileLoader.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("ERROR : Can not open the excel sheet");
        }

        //Get first/desired sheet from the workbook
        XSSFSheet sheet = workbook.getSheetAt(0);

        Row cCount = sheet.getRow(0);
        int cellCount = cCount.getLastCellNum();

        for (int i = 0; i < cellCount; i++) {
            Iterator<Row> rowIterator = sheet.iterator();
            temp = new ArrayList();

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                //For each row, iterate through all the columns

                Cell cell = row.getCell(i);
                try {

                    switch (cell.getCellType()) {
                        case Cell.CELL_TYPE_NUMERIC:
                            //System.out.print(cell.getNumericCellValue() + "\t");
                            int no = (int) cell.getNumericCellValue();
                            temp.add(String.valueOf(no));
                            //System.out.println("NUM TYPE = "+no);

                            break;
                        case Cell.CELL_TYPE_STRING:
                            //System.out.print(cell.getStringCellValue() + "\t");
                            temp.add(cell.getStringCellValue());
                            // System.out.println("STRING TYPE = "+cell.getStringCellValue());      
                            break;
                    }
                } catch (NullPointerException e) {
                    //System.out.println("null value");
                }

            }
            componentsList.add(temp);
        }
        try {
            file.close();
        } catch (IOException ex) {
            Logger.getLogger(FileLoader.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("The component list loaded successfully\n" + componentsList);
    }

}
