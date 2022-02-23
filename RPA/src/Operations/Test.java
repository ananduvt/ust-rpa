package Operations;

import com.sun.javafx.scene.control.skin.VirtualFlow;
import com.sun.jna.Native;
import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef;
import java.util.ArrayList;
import java.util.List;

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
        //test_Keyop();
        //keyTest();
        keySimTest();
        //getWindows();
    }

    static void keySimTest() {
        List ks = new ArrayList();
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
        
   
       
        List data = new ArrayList();
        data.add("hello");
        data.add("test");
        data.add("world");
        data.add("ok");
        data.add("done");

        List dl = new ArrayList();
        dl.add(data);

        KeySimulator keySim = new KeySimulator(1, 2, ks, dl);

        keySim.start();
    }

    static void getWindows() {
        char[] buffer = new char[1024 * 2];
        WinDef.HWND hwnd = null;

        hwnd = User32.INSTANCE.GetForegroundWindow();
        User32.INSTANCE.GetWindowText(hwnd, buffer, 1024);
        System.out.println(Native.toString(buffer));
    }

    static void keyTest() {
        KeyOperations keyOp = new KeyOperations();

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

    }
}
