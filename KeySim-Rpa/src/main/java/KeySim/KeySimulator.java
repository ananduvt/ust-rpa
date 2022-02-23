/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package KeySim;

import Operations.KeyEvents;
import Operations.Logger;
import Operations.MouseEvents;
import Operations.WindowUtil;
import Util.Globals;
import java.util.List;
import java.util.Map;

/**
 *
 * @author U55369
 */
public class KeySimulator implements Runnable {

    private final int iterationStart;
    private final int iterationStop;
    private int iteractionCount;
    private String currentOperation;
    private final List<String> operationList;
    private final List<List<String>> dataList;
    private final Map<String, List<String>> doMap;
    private final KeyEvents keyEvent;
    private final MouseEvents mouseEvent;
    private final boolean switchWindow;
    private boolean isSimOpWorking;

    public String[] toLog(String log) {
        String allData = "";

        for (List data : dataList) {
            if (data.size() > iteractionCount) {
                allData += data.get(iteractionCount) + ",";
            } else {
                allData += "NO DATA" + ",";
            }
        }
        String[] logs = {String.valueOf(iteractionCount), currentOperation, allData, log};

        return logs;
    }

    public KeySimulator(int start, int stop, List<String> operationList,
            List<List<String>> dataList) {
        this.iterationStart = start;
        this.iterationStop = stop;
        this.operationList = operationList;
        this.dataList = dataList;
        this.doMap = null;
        this.switchWindow = false;

        keyEvent = new KeyEvents();
        mouseEvent = new MouseEvents();
    }

    public KeySimulator(int start, int stop, List<String> operationList,
            List<List<String>> dataList, Map<String, List<String>> doMap, boolean switchWindow) {
        this.iterationStart = start;
        this.iterationStop = stop;
        this.operationList = operationList;
        this.dataList = dataList;
        this.doMap = doMap;
        this.switchWindow = switchWindow;

        keyEvent = new KeyEvents();
        mouseEvent = new MouseEvents();
    }

    public boolean isIsSimOpWorking() {
        return isSimOpWorking;
    }

    public String getCurrentOperation() {
        return currentOperation;
    }

    public int getIteractionCount() {
        return iteractionCount;
    }

    @Override
    public void run() {
        System.out.println("KeySim Started");

        keyEvent.waitOperation(Globals.KEYSIM_START_WAIT);
        if (switchWindow) {
            keyEvent.switchWindow();
        }
        keyEvent.waitOperation(Globals.KEYSIM_START_WAIT);
        for (int index = iterationStart; index <= iterationStop; index++) {
            // iteration 
            System.out.println("Iteration count : " + index);
            iteractionCount = index;

            for (String operation : operationList) {
                // Operation 
                System.out.println("\tOperation : " + operation);
                currentOperation = operation;
                String[] ops = operation.split("\\.");

                // check whether window check present in the sub operation 
                boolean isWindowCheck = false;
                // check whether value check present in the sub operation 
                boolean isValueCheck = false;

                // return value of window check
                boolean windowCheckResult = false;
                // return value of value check
                boolean valueCheckResult = false;

                for (String op : ops) {
                    //Sub operations
                    System.out.print("\t\tSub Operation : " + op + "<");

                    isSimOpWorking = true;

                    if (op.startsWith("keyLoop")) {
                        doKeyLoop(op, index);
                    } else if (op.startsWith("key")) {
                        doKey(op);
                    } else if (op.startsWith("mouse")) {
                        doMouse(op);
                    } else if (op.startsWith("type")) {
                        doType(op, index);
                    } else if (op.startsWith("paste")) {
                        doPaste(op, index);
                    } else if (op.startsWith("log")) {
                        if (isWindowCheck) {
                            doLog(op, windowCheckResult);
                        } else if (isValueCheck) {
                            doLog(op, valueCheckResult);
                        } else {
                            doLog(op, false);
                        }
                    } else if (op.startsWith("windowCheck")) {
                        // TODO pending test 
                        isWindowCheck = true;
                        windowCheckResult = doWindowCheck(op);

                    } else if (op.startsWith("valueCheck")) {
                        // TODO pending test
                        isValueCheck = true; 
                        valueCheckResult = doValueCheck(op);

                    } else if (op.startsWith("do")) {
                        if (isWindowCheck) {
                            doDo(op, windowCheckResult);
                        } else if (isValueCheck) {
                            doDo(op, valueCheckResult);
                        } else {
                            doDo(op, false);
                        }
                    } else if (op.startsWith("waitForWindow")) {
                        doWaitForWindow(op);
                    } else if (op.startsWith("wait")) {
                        doWait(op);
                    } else {
                        System.err.println("Invalid operation : " + op);
                    }
                    isSimOpWorking = false;

                    System.out.println(">");
                }
                keyEvent.waitOperation(Globals.OP_WAIT);
            }
            keyEvent.waitOperation(Globals.ITERATION_WAIT);
        }
        System.out.println("KeySim completed");
    }

    private boolean isNumeric(String str) {
        return str.matches("-?\\d+(\\.\\d+)?");  //match a number with optional '-' and decimal.
    }

    private void doKey(String op) {
        String[] keys = op.substring(op.indexOf("(") + 1, op.lastIndexOf(")")).split(",");
        switch (keys.length) {
            case 1:
                keyEvent.keyStroke(keys[0]);
                break;
            case 2:
                if (isNumeric(keys[1])) {
                    keyEvent.keyStroke(keys[0], Integer.parseInt(keys[1]));
                } else {
                    keyEvent.keyStroke(keys[0], keys[1]);
                }
                break;
            case 3:
                if (isNumeric(keys[2])) {
                    keyEvent.keyStroke(keys[0], keys[1], Integer.parseInt(keys[2]));
                } else {
                    keyEvent.keyStroke(keys[0], keys[1], keys[2]);
                }
                break;
            default:
                System.err.println("Invalid Key arguments");
                break;
        }
    }

    private void doMouse(String op) {
        String key = op.substring(op.indexOf("(") + 1, op.lastIndexOf(")"));
        switch (key) {
            case "left":
                mouseEvent.mouseClick("mLeft");
                break;
            case "right":
                mouseEvent.mouseClick("mRight");
                break;
            case "middle":
                mouseEvent.mouseClick("mMiddle");
                break;
            default: {
                if (key.contains(",")) {
                    int x = Integer.parseInt(key.split(",")[0]);
                    int y = Integer.parseInt(key.split(",")[1]);
                    mouseEvent.mouseMove(x, y);
                } else {
                    int amount = Integer.parseInt(key);
                    mouseEvent.mouseWheel(amount);
                }
            }
        }
    }

    private void doWait(String op) {
        int waitTime = Integer.parseInt(op.substring(op.indexOf("(") + 1, op.lastIndexOf(")")));
        keyEvent.waitOperation(waitTime);
    }

    private void doType(String op, int index) {
        String value = op.substring(op.indexOf("(") + 1, op.lastIndexOf(")"));

        // TODO : use replace data point than replacing whole string
        if (value.contains(Globals.DATA_POINT)) {
            int col = Integer.parseInt(value.split(Globals.DATA_POINT)[1]);
            keyEvent.typeValue(dataList.get(col - 1).get(index - 1));
        } else {
            keyEvent.typeValue(value);
        }
    }

    private void doPaste(String op, int index) {
        String value = op.substring(op.indexOf("(") + 1, op.lastIndexOf(")"));
        // TODO : use replace data point than replacing whole string
        if (value.contains(Globals.DATA_POINT)) {
            int col = Integer.parseInt(value.split(Globals.DATA_POINT)[1]);
            keyEvent.pasteValue(dataList.get(col - 1).get(index - 1));
        } else {
            keyEvent.pasteValue(value);
        }
    }

    private void doLog(String op, boolean result) {
        String[] value = op.substring(op.indexOf("(") + 1, op.lastIndexOf(")")).split(",");
        if (value.length == 1) {
            Logger.log(toLog(value[0]));

        } else if (value.length == 2) {
            if (result) {
                Logger.log(toLog(value[0]));
                System.out.print("Logging : " + value[0]);
            } else {
                Logger.log(toLog(value[1]));
                System.out.print("Logging : " + value[1]);
            }
        } else {
            System.err.println("Invalid arguments for log");
        }
    }

    private boolean doWindowCheck(String op) {
        String windowTitle = op.substring(op.indexOf("(") + 1, op.lastIndexOf(")"));
        String currentWindowTitle = WindowUtil.getWindowTitle();

        if (windowTitle.equals(currentWindowTitle)) {
            System.out.print(windowTitle + " = " + currentWindowTitle);
            return true;
        } else {
            System.out.print(windowTitle + " != " + currentWindowTitle);
            return false;
        }
    }

    private boolean doValueCheck(String op) {
        String checkValue = op.substring(op.indexOf("(") + 1, op.lastIndexOf(")"));
        keyEvent.selectAll();
        keyEvent.copy();
        String currentValue = WindowUtil.getClipBoardContents();

        if (checkValue.equals(currentValue)) {
            System.out.print(checkValue + " = " + currentValue);
            return true;
        } else {
            System.out.print(checkValue + " != " + currentValue);
            return false;
        }
    }

    private void doWaitForWindow(String op) {
        String checkValue = op.substring(op.indexOf("(") + 1, op.lastIndexOf(")"));
        String currentWindow = WindowUtil.getWindowTitle();
        while (!checkValue.equals(currentWindow)) {
            keyEvent.waitOperation(Globals.WINDOW_WAIT);
            currentWindow = WindowUtil.getWindowTitle();
        }

    }

    private void doDo(String op, boolean result) {
        String[] value = op.substring(op.indexOf("(") + 1, op.lastIndexOf(")")).split(",");
        if (value.length == 1) {
            if (doMap.containsKey(value[0])) {
                System.out.print(" do : " + value[0]);
                KeySimulator keySim = new KeySimulator(1, 1, doMap.get(value[0]), dataList);
                keySim.run();
            } else {
                System.err.print(" do : " + value[0] + " not found !");
            }
        } else if (value.length == 2) {
            if (result) {
                if (doMap.containsKey(value[0])) {
                    System.out.print(" do : " + value[0]);
                    KeySimulator keySim = new KeySimulator(1, 1, doMap.get(value[0]), dataList);
                    keySim.run();
                } else {
                    System.err.print(" do : " + value[0] + " not found !");
                }
            } else {
                if (doMap.containsKey(value[1])) {
                    System.out.print(" do : " + value[1]);
                    KeySimulator keySim = new KeySimulator(1, 1, doMap.get(value[1]), dataList);
                    keySim.run();
                } else {
                    System.err.print(" do : " + value[1] + " not found !");
                }
            }
        } else {
            System.err.println("Invalid arguments for do");
        }

    }

    private void doKeyLoop(String op, int index) {
        System.out.print("loop :" + index);
        for (int i = 0; i < index; i++) {
            doKey(op);
        }

    }

}
