/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import GUI.Misc.Click;
import GUI.Misc.Position;
import GUI.Misc.Rect;
import com.sun.awt.AWTUtilities;
import java.awt.Color;
import java.awt.MouseInfo;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;

/**
 *
 * @author u55369
 */
public class WindowPositionFinder extends javax.swing.JFrame {

    /**
     * Creates new form WindowLocator
     */
    public WindowPositionFinder() {
        initComponents();
        setSize((int) Toolkit.getDefaultToolkit().getScreenSize().getWidth(),
                (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight());
        showXY();

    }
    private Thread showXY;
    private Thread showRectDyn;
    private int pressedX;
    private int pressedY;
    private int releasedX;
    private int releasedY;
    private JPopupMenu jpm;
    private JPanel jp;
    private boolean rectSelecting;

    private List<Click> clickList = new ArrayList<>();
    private List<Rect> rectList = new ArrayList<>();

    private void showXY() {

        showXY = new Thread() {
            @Override
            public void run() {

                while (true) {
                    outputX.setText("X : " + String.valueOf(MouseInfo.getPointerInfo().getLocation().x));
                    outputY.setText("Y : " + String.valueOf(MouseInfo.getPointerInfo().getLocation().y));
                    try {
                        Thread.sleep(50);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(WindowPositionFinder.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        };
        showXY.start();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        close = new javax.swing.JButton();
        infoPanel = new javax.swing.JPanel();
        outputX = new javax.swing.JLabel();
        outputY = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Window Position");
        setUndecorated(true);
        setOpacity(0.4F);
        setResizable(false);

        mainPanel.setBackground(new java.awt.Color(51, 51, 51));
        mainPanel.setToolTipText("");
        mainPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                mainPanelMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                mainPanelMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                mainPanelMouseReleased(evt);
            }
        });

        close.setText("x");
        close.setIconTextGap(2);
        close.setOpaque(false);
        close.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                closeActionPerformed(evt);
            }
        });

        infoPanel.setBackground(new java.awt.Color(51, 51, 51));

        outputX.setBackground(new java.awt.Color(255, 204, 255));
        outputX.setForeground(new java.awt.Color(255, 255, 51));

        outputY.setBackground(new java.awt.Color(255, 204, 255));
        outputY.setForeground(new java.awt.Color(255, 255, 51));

        javax.swing.GroupLayout infoPanelLayout = new javax.swing.GroupLayout(infoPanel);
        infoPanel.setLayout(infoPanelLayout);
        infoPanelLayout.setHorizontalGroup(
            infoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(infoPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(infoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(outputX, javax.swing.GroupLayout.DEFAULT_SIZE, 59, Short.MAX_VALUE)
                    .addComponent(outputY, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        infoPanelLayout.setVerticalGroup(
            infoPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(infoPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(outputX, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(outputY, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addGap(0, 329, Short.MAX_VALUE)
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(close, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(infoPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainPanelLayout.createSequentialGroup()
                .addComponent(close)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 106, Short.MAX_VALUE)
                .addComponent(infoPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(110, 110, 110))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void closeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_closeActionPerformed

        showXY.stop();

        WindowTitleFinder.setPosition(new Position(clickList, rectList));

        this.dispose();
    }//GEN-LAST:event_closeActionPerformed

    private void mainPanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mainPanelMouseClicked

        if (evt.getButton() == MouseEvent.BUTTON1) {
            int x = MouseInfo.getPointerInfo().getLocation().x;
            int y = MouseInfo.getPointerInfo().getLocation().y;
            System.out.println("Clicked at : " + "X : " + String.valueOf(x)
                    + "Y : " + String.valueOf(y));
            popMenu(x, y);
        }
    }//GEN-LAST:event_mainPanelMouseClicked

    private void mainPanelMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mainPanelMousePressed
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
            System.out.println("Pressed at : " + "X : " + String.valueOf(x)
                    + "Y : " + String.valueOf(y));
            pressedX = x;
            pressedY = y;
            rectSelecting = true;
            createRectDyn();

        }

    }//GEN-LAST:event_mainPanelMousePressed

    private void createRectDyn() {
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
                        Logger.getLogger(WindowPositionFinder.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    mainPanel.remove(jp);
                }
            }
        };
        showRectDyn.start();
    }

    private void mainPanelMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_mainPanelMouseReleased

        if (evt.getButton() == MouseEvent.BUTTON3) {

            rectSelecting = false;
            try {
                showRectDyn.join();
            } catch (InterruptedException ex) {
                Logger.getLogger(WindowPositionFinder.class.getName()).log(Level.SEVERE, null, ex);
            }

            int x = MouseInfo.getPointerInfo().getLocation().x;
            int y = MouseInfo.getPointerInfo().getLocation().y;
            System.out.println("Released at : " + "X : " + String.valueOf(x)
                    + "Y : " + String.valueOf(y));
            releasedX = x;
            releasedY = y;
            createRect();
        }
    }//GEN-LAST:event_mainPanelMouseReleased

    private void createRect() {
        jp = new JPanel();
        jp.setSize(Math.abs(pressedX - releasedX), Math.abs(pressedY - releasedY));
        jp.setBackground(Color.red);

        mainPanel.add(jp);
        jp.setLocation(pressedX, pressedY);
        mainPanel.repaint();

        popMenu();
    }

    private void popMenu() {

        jpm = new JPopupMenu();
        JMenuItem jm1 = new JMenuItem("Use Rect");
        jm1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Rect Choosed");

                addRect("Rect " + (rectList.size() + 1));
                jpm.setVisible(false);
            }
        });
        jpm.add(jm1);
        jpm.setLocation(releasedX, releasedY);
        jpm.setVisible(true);

    }

    private void popMenu(int x, int y) {

        jpm = new JPopupMenu();
        JMenuItem jm1 = new JMenuItem("Use Click");
        jm1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Click Choosed");

                addClick("Click " + (clickList.size() + 1), x, y);
                jpm.setVisible(false);
            }
        });
        jpm.add(jm1);
        jpm.setLocation(x, y);
        jpm.setVisible(true);

    }

    private void addClick(String name, int x, int y) {
        Click click = new Click(name, x, y);
        clickList.add(click);
    }

    private void addRect(String Name) {
        Rect rect = new Rect(Name, pressedX, pressedY, releasedX, releasedY);
        rectList.add(rect);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;

                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(WindowPositionFinder.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(WindowPositionFinder.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(WindowPositionFinder.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(WindowPositionFinder.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new WindowPositionFinder().setVisible(true);
            }
        });

    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton close;
    private javax.swing.JPanel infoPanel;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JLabel outputX;
    private javax.swing.JLabel outputY;
    // End of variables declaration//GEN-END:variables
}
