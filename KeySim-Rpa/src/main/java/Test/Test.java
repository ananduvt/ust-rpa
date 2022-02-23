package Test;

import KeySim.KeySimManager;
import Operations.KeyEvents;
import KeySim.KeySimulator;
import Operations.Logger;
import Operations.MouseEvents;
import Util.KeyMapper;
import Operations.WindowUtil;
import com.opencsv.CSVWriter;
import com.sun.javafx.scene.control.skin.VirtualFlow;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author u55369
 */
public class Test {

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        
       // WindowUtil.getScreenValue(18, 10, 156, 20);
        keySimTest();

    }

    static void keySimTest() {
        List ks = new ArrayList();
        //ks.add("windowCheck(test)");     
        ks.add("keyLoop(shift,a)");
        ks.add("key(enter)");

        List data = new ArrayList();
        data.add("hello");
        data.add("test");
        data.add("world");
        data.add("ok");
        data.add("done");

        List dl = new ArrayList();
        dl.add(data);
        KeyEvents keyOp = new KeyEvents();
        MouseEvents mOp = new MouseEvents();
        Logger.initialize();

        Map<String, List<String>> doMap = new HashMap<>();
        List map = new ArrayList();
        map.add("type(hello)");
        map.add("type(world)");

        doMap.put("type", map);

        KeySimManager keySimMan = new KeySimManager(1, 5, ks, dl, doMap, true);
        keySimMan.start();

    }

    static void keyTest() {
        KeyEvents keyOp = new KeyEvents();

        keyOp.waitOperation(500);
        // keyOp.switchWindow();
        keyOp.typeValue("abcdABCD1234!@#$,./;'<>?:");
        keyOp.pasteValue("hello world");
        keyOp.keyStroke("ctrl", "n");
        keyOp.waitOperation(500);
        keyOp.keyStroke("esc");
        keyOp.waitOperation(500);
        keyOp.keyStroke("a", "s");
        keyOp.keyStroke("enter");
        keyOp.keyStroke("A", 3);
        keyOp.keyStroke("enter");
        keyOp.keyStroke("a", 2);
        keyOp.keyStroke("enter");
        keyOp.keyStroke("shift", "a", 4);

        //        ks.add("key(a,).wait(200)");
//        ks.add("key(a,b).wait(200)");
//        ks.add("key(b,5).wait(200)");
//        ks.add("key(c,5).wait(200)");
//        ks.add("key(shift,z).wait(200)");
//        ks.add("key(shift,o,5).wait(200)");     
//        ks.add("key(ctrl,shift,esc)");
        //ks.add("type(hello world).paste(test)");
        //ks.add("paste(\"hai bro\")");
        // ks.add("paste(data@1)");
        // ks.add("paste(data@1)");
        //  ks.add("paste(data@1)");
        // ks.add("mouse(left)");
        // ks.add("mouse(right).wait(1000)");
        //  ks.add("mouse(middle)");
        //  ks.add("mouse(10,10).wait(1000)");
        // ks.add("paste(data@1).type(data@1).key(enter)");   
        //ks.add("valueCheck(hai).log(true,false)");
        //ks.add("valueCheck(test).log(Yes,No)");
        //   ks.add("windowCheck(Sticky Note).log(true,false)");
        // ks.add("windowCheck(Sticky).log(yes,no)");
        // ks.add("windowCheck(Sticky Note).log(test)");
        // ks.add("log(test)");
    }
}
