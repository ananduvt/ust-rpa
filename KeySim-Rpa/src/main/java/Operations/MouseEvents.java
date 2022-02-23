/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Operations;

import Util.KeyMapper;
import Util.Globals;
import java.awt.AWTException;
import java.awt.Robot;

/**
 *
 * @author u55369
 */
public class MouseEvents {

    private static Robot robot;

    public MouseEvents() {
        try {
            robot = new Robot();
        } catch (AWTException ex) {
            System.err.println("Robot Class initialization failed");
        }
    }

    private void waitOperation(int waitTime) {
        try {
            Thread.sleep(waitTime);
        } catch (InterruptedException ex) {
            System.err.println("Error in operation wait");
        }
    }

    public void mouseClick(String button) {
        robot.mousePress(KeyMapper.MapKey(button));
        waitOperation(Globals.MOUSE_CLICK_WAIT);
        robot.mouseRelease(KeyMapper.MapKey(button));
    }

    public void mouseWheel(int wheelAmount) {
        robot.mouseWheel(wheelAmount);
    }

    public void mouseMove(int x, int y) {
        robot.mouseMove(x, y);
    }
}
