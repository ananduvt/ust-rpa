/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package keysim;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
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
public class Loader {

    public static String getConfigFile() {
        return configFile;
    }

    public static void setConfigFile(String configFile) {
        
        Loader.configFile = configFile;
    }

    public static String getCompFile() {
        return CompFile;
    }

    public static void setCompFile(String CompFile) {
        Loader.CompFile = CompFile;
    }

    public static ArrayList<ArrayList<String>> getOperations() {
        return operations;
    }

    public void setOperations(ArrayList operations) {
        Loader.operations = operations;
    }

    public static ArrayList<ArrayList<String>> getComponents() {
        return components;
    }

    public void setComponents(ArrayList components) {
        Loader.components = components;
    }
    
    static String configFile;
    static String CompFile;
    
    static ArrayList<ArrayList<String>> operations;
    static ArrayList<ArrayList<String>> components;
    
    static ArrayList loadConfig(){
        
        operations=new ArrayList<ArrayList<String>>();
         FileInputStream file = null;
         ArrayList config= null;
        try {
            file = new FileInputStream(new File(configFile));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Loader.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //Create Workbook instance holding reference to .xlsx file
            XSSFWorkbook workbook = null;
        try {
            workbook = new XSSFWorkbook(file);
        } catch (IOException ex) {
            Logger.getLogger(Loader.class.getName()).log(Level.SEVERE, null, ex);
        }
 
            //Get first/desired sheet from the workbook
            XSSFSheet sheet = workbook.getSheetAt(0);
            
            Row cCount=sheet.getRow(0);
            int cellCount=cCount.getLastCellNum();
            
            for(int i=0;i<cellCount;i++)
            {
                Iterator<Row> rowIterator = sheet.iterator();
               config=new ArrayList();
                System.out.println("loop "+i);
             while (rowIterator.hasNext()) 
             {
                Row row = rowIterator.next();
                //For each row, iterate through all the columns
                
                    Cell cell=row.getCell(i);
                   
                   try{
                     switch(cell.getCellType())
                   {
                       case Cell.CELL_TYPE_BLANK :
                            config.add("");
                             break;
                       case Cell.CELL_TYPE_NUMERIC :
                            //System.out.print(cell.getNumericCellValue() + "\t");
                            config.add(String.valueOf(cell.getNumericCellValue()));
                           
                            break;
                        case Cell.CELL_TYPE_STRING:
                            //System.out.print(cell.getStringCellValue() + "\t");
                            config.add(cell.getStringCellValue());
                                    
                            break;
                   }
                   }
                   catch(NullPointerException e)
                   {
                       System.out.println("null value");
                   }
             }
             
                operations.add(config);
                
            }
          
            try {
            file.close();
        } catch (IOException ex) {
            Logger.getLogger(Loader.class.getName()).log(Level.SEVERE, null, ex);
        }
            
            return operations;
 
   }
            
            
            
    
    
    static ArrayList loadComp(){
        
        components=new ArrayList<ArrayList<String>>();
         FileInputStream file = null;
         ArrayList compnt = null;
        try {
            file = new FileInputStream(new File(CompFile));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Loader.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //Create Workbook instance holding reference to .xlsx file
            XSSFWorkbook workbook = null;
        try {
            workbook = new XSSFWorkbook(file);
        } catch (IOException ex) {
            Logger.getLogger(Loader.class.getName()).log(Level.SEVERE, null, ex);
        }
 
            //Get first/desired sheet from the workbook
            XSSFSheet sheet = workbook.getSheetAt(0);
            
            Row cCount=sheet.getRow(0);
            int cellCount=cCount.getLastCellNum();
            
            for(int i=0;i<cellCount;i++)
            {
                Iterator<Row> rowIterator = sheet.iterator();
               compnt=new ArrayList();
               
             while (rowIterator.hasNext()) 
             {
                Row row = rowIterator.next();
                //For each row, iterate through all the columns
                
                    Cell cell=row.getCell(i);
                 try{
                     
                    switch(cell.getCellType())
                   {
                       case Cell.CELL_TYPE_NUMERIC :
                            //System.out.print(cell.getNumericCellValue() + "\t");
                           int no=(int) cell.getNumericCellValue();
                            compnt.add(String.valueOf(no));
                           //System.out.println("NUM TYPE = "+no);
                           
                            break;
                        case Cell.CELL_TYPE_STRING:
                            //System.out.print(cell.getStringCellValue() + "\t");
                            compnt.add(cell.getStringCellValue());
                           // System.out.println("STRING TYPE = "+cell.getStringCellValue());      
                            break;
                   }
                 }
                     catch(NullPointerException e)
                   {
                       //System.out.println("null value");
                   }
                    
                   
             }
             
             components.add(compnt);
                
            }
          
            try {
            file.close();
        } catch (IOException ex) {
            Logger.getLogger(Loader.class.getName()).log(Level.SEVERE, null, ex);
        }
             System.out.println("component list loaded..\n"+components);   
            return components;
     
    }
    
    static void opValidate()
    {
        
    }
    
}
