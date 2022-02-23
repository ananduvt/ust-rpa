package com.ust.keysim.ui;

import Operations.WindowUtil;
import com.ust.keysim.ui.misc.Click;
import com.ust.keysim.ui.misc.Rect;
import java.awt.Color;
import java.awt.MouseInfo;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTextPane;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author u55369
 */
public class UiUtils {

    private static boolean checkWindow = false;

    private static Thread showRectDyn;
    private static int pressedX;
    private static int pressedY;
    private static int releasedX;
    private static int releasedY;
    private static JPopupMenu jpm;
    private static JPanel jp;
    private static boolean rectSelecting;

    private static final List<Click> clickList = new ArrayList<>();
    private static final List<Rect> rectList = new ArrayList<>();

    public static boolean isCheckWindow() {
        return checkWindow;
    }

    public static void setCheckWindow(boolean checkWindow) {
        UiUtils.checkWindow = checkWindow;
    }

    public static void showWindowTitles(JTextPane outputView) {
        new Thread() {
            @Override
            public void run() {
                String currentWindow = "";
                String oldWindow = "";
                while (checkWindow) {

                    while (currentWindow.equals(oldWindow)) {

                        currentWindow = WindowUtil.getWindowTitle();
                        try {
                            Thread.sleep(100);
                        } catch (InterruptedException ex) {
                        }
                    }
                    oldWindow = currentWindow;
                    if (!currentWindow.equals("")) {
                        outputView.setText(outputView.getText() + currentWindow + "\n");
                    }
                }
            }
        }.start();
    }

    public static void panelMouseClicked(java.awt.event.MouseEvent evt) {

        if (evt.getButton() == MouseEvent.BUTTON1) {
            int x = MouseInfo.getPointerInfo().getLocation().x;
            int y = MouseInfo.getPointerInfo().getLocation().y;
            System.out.println("Clicked at : " + "X : " + String.valueOf(x) + " & "
                    + "Y : " + String.valueOf(y));
            popMenu(x, y);
        }
    }

    public static void panelMousePressed(java.awt.event.MouseEvent evt, JPanel mainPanel) {
        if (jp != null) {
            mainPanel.remove(jp);
            mainPanel.repaint();
        }
        if (jpm != null) {
            jpm.setVisible(false);
        }
        if (evt.getButton() == MouseEvent.BUTTON3) {
            int x = MouseInfo.getPointerInfo().getLocation().x;
            int y = MouseInfo.getPointerInfo().getLocation().y;
            System.out.println("Pressed at : " + x + "," + y + ")");

            pressedX = x;
            pressedY = y;
            rectSelecting = true;
            createRectDyn(mainPanel);
        }

    }

    public static void panelMouseReleased(java.awt.event.MouseEvent evt, JPanel mainPanel) {

        if (evt.getButton() == MouseEvent.BUTTON3) {

            rectSelecting = false;
            try {
                showRectDyn.join();
            } catch (InterruptedException ex) {

            }

            int x = MouseInfo.getPointerInfo().getLocation().x;
            int y = MouseInfo.getPointerInfo().getLocation().y;
            System.out.println("Released at : (" + x + "," + y + ")");

            releasedX = x;
            releasedY = y;
            if (((pressedX - releasedX) == 0) || ((pressedY - releasedY) == 0)) {

            } else {
                createRect(mainPanel);
            }
        }
    }

    private static void createRectDyn(JPanel mainPanel) {
        showRectDyn = new Thread() {
            @Override
            public void run() {
                while (rectSelecting) {

                    int x = MouseInfo.getPointerInfo().getLocation().x;
                    int y = MouseInfo.getPointerInfo().getLocation().y;
                    jp = new JPanel();
                    jp.setSize(Math.abs(pressedX - x), Math.abs(pressedY - y));
                    jp.setBackground(Color.YELLOW);

                    mainPanel.add(jp);
                    jp.setLocation(pressedX, pressedY);
                    mainPanel.repaint();
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException ex) {

                    }
                    mainPanel.remove(jp);
                }
            }
        };
        showRectDyn.start();
    }

    private static void createRect(JPanel mainPanel) {
        jp = new JPanel();
        jp.setSize(Math.abs(pressedX - releasedX), Math.abs(pressedY - releasedY));
        jp.setBackground(Color.red);

        mainPanel.add(jp);
        jp.setLocation(pressedX, pressedY);
        mainPanel.repaint();

        popMenu();

    }

    private static void popMenu() {

        jpm = new JPopupMenu();
        JMenuItem jm1 = new JMenuItem("Use Rect");
        jm1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Rect Choosed");

                rectList.add(new Rect("Rect " + (rectList.size() + 1), pressedX, pressedY, releasedX, releasedY));
                jpm.setVisible(false);
            }
        });
        jpm.add(jm1);
        jpm.setLocation(releasedX, releasedY);
        jpm.setVisible(true);

    }

    private static void popMenu(int x, int y) {

        jpm = new JPopupMenu();
        JMenuItem jm1 = new JMenuItem("Use Click");
        jm1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Click Choosed : (" + x + "," + y + ")");
                Click click = new Click("Click " + (clickList.size() + 1), x, y);
                clickList.add(click);
                jpm.setVisible(false);
            }
        });
        jpm.add(jm1);
        jpm.setLocation(x, y);
        jpm.setVisible(true);
    }

    public static void updateOutput(JTextPane pane) {

        String output = "";
        for (Click click : clickList) {
            output += click.getName() + " : " + click + "\n";
        }
        clickList.clear();
        for (Rect rect : rectList) {
            output += rect.getName() + " : " + rect + "\n";
        }
        rectList.clear();
        pane.setText(pane.getText() + output);
    }

    public static String textFormat(String text) {
        return "<html><p><center>" + text + "</center></p></html>";
    }

    public static String textFormat1(String text) {
        return "<html><p>" + text + "</p></html>";
    }
}
