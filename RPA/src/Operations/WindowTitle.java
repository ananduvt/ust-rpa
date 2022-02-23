/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Operations;

import com.sun.jna.Native;
import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef;

/**
 *
 * @author u55369
 */
public class WindowTitle {

    public String getWindowTitle() {
        char[] buffer = new char[1024 * 2];
        WinDef.HWND hwnd = null;

        hwnd = User32.INSTANCE.GetForegroundWindow();
        User32.INSTANCE.GetWindowText(hwnd, buffer, 1024);
        return Native.toString(buffer);
    }

}
