/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Operations;

import Util.KeyMapper;
import java.awt.AWTException;
import java.awt.Robot;
import Util.Globals;

/**
 *
 * @author U55369
 */
public class KeyEvents {

    private Robot robot;

    public KeyEvents() {
        try {
            robot = new Robot();
        } catch (AWTException ex) {
            System.err.println("Robot Class initialization failed");
        }
    }

    public void waitOperation(int waitTime) {
        try {
            Thread.sleep(waitTime);
        } catch (InterruptedException ex) {
            System.err.println("Error in wait operation");
        }
    }

    public void keyStroke(String key) {
        if (KeyMapper.isSpecialChar(key)) {
            keyStroke("shift", key);
        } else if (Character.isUpperCase(key.toCharArray()[0])) {
            keyStroke("shift", key.toLowerCase());
        } else {
            robot.keyPress(KeyMapper.MapKey(key));
            robot.keyRelease(KeyMapper.MapKey(key));
        }
    }

    public void keyStroke(String key1, String key2) {
        
        robot.keyPress(KeyMapper.MapKey(key1));
        robot.keyPress(KeyMapper.MapKey(key2));
        waitOperation(Globals.KEY_WAIT);
        robot.keyRelease(KeyMapper.MapKey(key2));
        robot.keyRelease(KeyMapper.MapKey(key1));
       
    }

    public void keyStroke(String key1, String key2, String key3) {
        robot.keyPress(KeyMapper.MapKey(key1));
        robot.keyPress(KeyMapper.MapKey(key2));
        robot.keyPress(KeyMapper.MapKey(key3));
        waitOperation(Globals.KEY_WAIT);
        robot.keyRelease(KeyMapper.MapKey(key3));
        robot.keyRelease(KeyMapper.MapKey(key2));
        robot.keyRelease(KeyMapper.MapKey(key1));

    }

    public void keyStroke(String key, int count) {
        for (int i = 0; i < count; i++) {
            keyStroke(key);
            waitOperation(Globals.MULTI_KEY_WAIT);
        }
    }

    public void keyStroke(String key1, String key2, int count) {
        for (int i = 0; i < count; i++) {
            keyStroke(key1, key2);
            waitOperation(Globals.MULTI_KEY_WAIT);
        }
    }

    public void typeValue(String key) {
        for (char c : key.toCharArray()) {
            keyStroke(String.valueOf(c));
            waitOperation(Globals.TYPE_KEY_WAIT);
        }
    }

    public void pasteValue(String value) {
       WindowUtil.setClipBoardContents(value);
        paste();
    }

    // misc functions
    public void cut() {
        keyStroke("ctrl", "x");
    }

    public void paste() {
        keyStroke("ctrl", "v");
    }

    public void copy() {
        keyStroke("ctrl", "c");
    }

    public void selectAll() {
        keyStroke("ctrl", "a");
    }

    public void switchWindow() {
        keyStroke("alt", "tab");
    }
}
