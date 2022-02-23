/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rpa;

import GUI.MainUi;
import Util.FileLoader;

/**
 *
 * @author u55369
 */
public class RPA {   
    public static void main(String[] args) {
     
        if(args.length!=0)
        {
           FileLoader.setConfigurationFile(args[0]);
           
        }
        
        //MainUi.main(); 
        test(args);
    }
    public static  void test(String[] args)
    {
     
        FileLoader.setConfigurationFile("config.xlsx");
        MainUi.main(args);
    }
    
}
