/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package KeySim;

import java.lang.Thread.State;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author u55369
 */
public class KeySimManager {

    private KeySimulator keySim;
    private Thread keySimThread;

    public KeySimManager(int start, int stop, List<String> operationList, List<List<String>> dataList, Map<String, List<String>> doMap, boolean switchWindow) {

        keySim = new KeySimulator(start, stop, operationList, dataList, doMap, switchWindow);
    }

    public void start() {
        keySimThread = new Thread(keySim, "KeySim Thread");
        keySimThread.start();
    }

    public void stop() {
        //System.out.println("interrupting ");
        while (keySim.isIsSimOpWorking()) {
            try {
                Thread.sleep(55);
            } catch (InterruptedException ex) {
                Logger.getLogger(KeySimManager.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        keySimThread.stop();
        //System.out.println("interrupted ");
    }

    public State getStatus() {
        if (keySimThread != null) {
            return keySimThread.getState();
        } else {
            return Thread.State.NEW;
        }
    }

    public String getCurrentOp() {
        return keySim.getCurrentOperation();
    }

    public String getCount() {
        return String.valueOf(keySim.getIteractionCount());
    }
}
