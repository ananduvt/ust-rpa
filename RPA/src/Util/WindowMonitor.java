package Util;

import com.sun.jna.Native;
import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author u55369
 */
public class WindowMonitor {

    private static String lastWindow = "";
    private static String currentWindow = "";
    private static String tempWindow = "";
    private static Thread windowThread = null;

    public static String getLastWindow() {
        return lastWindow;
    }

    public static String getCurrentWindow() {
        return currentWindow;
    }

    private static String getWindows() {
        char[] buffer = new char[1024 * 2];

        WinDef.HWND hwnd = null;

        hwnd = User32.INSTANCE.GetForegroundWindow();      
        User32.INSTANCE.GetWindowText(hwnd, buffer, 1024);
        //System.out.println("Active window title: " + Native.toString(buffer));
        return Native.toString(buffer);
    }

    public static void startWindowMonitor() {
        if (windowThread == null) {
            windowThread = getWindowMonitor();
        }
        if (!windowThread.isAlive()) {
            windowThread.start();
        }
    }

    public static void stopWindowMonitor() {
        if (windowThread !=null&&windowThread.isAlive()) {
            windowThread.stop();
        }
        windowThread = null;

    }

    public static void windowChanged() {

    }

    private static Thread getWindowMonitor() {
        return new Thread() {
            public void run() {
                while (true) {
                    while (tempWindow.equals(currentWindow) || tempWindow.equals("")) {
                        tempWindow = getWindows();
                    }
                    lastWindow = currentWindow;
                    currentWindow = tempWindow;
                   
                    windowChanged();
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(WindowMonitor.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        };
    }

}
