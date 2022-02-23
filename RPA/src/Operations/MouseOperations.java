/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Operations;

import Util.Globals;
import java.awt.AWTException;
import java.awt.Robot;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author u55369
 */
public class MouseOperations {

    private static Robot robot;

    public MouseOperations() {
        try {
            robot = new Robot();
        } catch (AWTException ex) {
            System.err.println("Robot Class initialization failed");
        }
    }

    private void waitOperation() {
        try {
            Thread.sleep(Globals.MOUSE_WAIT);
        } catch (InterruptedException ex) {
            System.err.println("Error in operation wait");
        }
    }

    private void mousePress(String button) {
        robot.mousePress(KeyMapper.MapKey(button));
    }

    private void mouseRelease(String button) {
        robot.mouseRelease(KeyMapper.MapKey(button));
    }

    public void mouseClick(String button) {
        mousePress(button);
        waitOperation();
        mouseRelease(button);
    }

    public void mouseWheel(int wheelAmount) {
        robot.mouseWheel(wheelAmount);
    }

    public void mouseMove(int x, int y) {
        robot.mouseMove(x, y);
    }
}
