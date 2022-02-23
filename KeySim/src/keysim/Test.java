/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package keysim;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author u55369
 */
public class Test {
    
    public static void main(String[] args) throws InterruptedException, AWTException {
        
//        try {
//            Robot robot=new Robot();
//            robot.keyPress(KeyEvent.VK_ALT);
//            robot.keyPress(KeyEvent.VK_TAB);
//            robot.delay(50);
//            robot.keyRelease(KeyEvent.VK_ALT);
//            robot.keyRelease(KeyEvent.VK_TAB);
//            
//            
//        } catch (AWTException ex) {
//            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        Thread.sleep(1000);
//        
//        KeyMapper.keyLoad();
//        ArrayList comp1=new ArrayList();
//        ArrayList comp=new ArrayList();
//        comp.add("shabna");
//        comp.add("mammu");
//        comp.add("thendi");
//        ArrayList com=new ArrayList();
//        com.add("123");
//        com.add("456");
//        com.add("789");
//        
//        comp1.add(comp);
//        comp1.add(com);
//        
//        ArrayList ops=new ArrayList();
//        ops.add("type&sand boc and me#1000");
//       
//                
//        KeySimulator obj= new KeySimulator();
//        obj.setStart(1);
//        obj.setLimit(1);
//        obj.setCurntOps(ops);
//        obj.setComponents(comp1);
//        
//        obj.start();
        
        
//        Loader.setConfigFile("c:\\mine\\config.xlsx");
//        Loader.setCompFile("c:\\mine\\comp.xlsx");
//        Loader.loadComp();
//        Loader.loadConfig();
//        
//        ArrayList list = Loader.operations;
//        
//        System.out.println(list);
//        System.out.println(Loader.components);
        
        
//        
//        Robot r=new Robot();
//        KeyMapper.keyLoad();
//        
//        r.keyPress(KeyMapper.keyMap("-"));
//        r.keyRelease(KeyMapper.keyMap("-"));
//        
        
//        Loader.setCompFile("c:\\mine\\comp.xlsx");
//        Loader.loadComp();
//        System.out.println(Loader.components);
//        KeyMapper.keyLoad();
        Main.main();
        KeyMapper.keyLoad();
        Loader.setConfigFile("d:\\config.xlsx");
        
        //Help.main();
        
        
    }
    
}
