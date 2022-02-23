/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Operations;

import java.util.ArrayList;
import java.util.regex.Pattern;
import Util.Globals;
import java.util.List;

/**
 *
 * @author U55369
 */
public class KeySimulator extends Thread {

    private final int iterationStart;
    private final int iterationStop;
    private int iteractionCount;
    private String currentOperation;
    private final List<String> operationList;
    private final List<List<String>> dataList;
    private final KeyOperations keyOp;

    // TODO: working lock 
    private boolean working;

    public KeySimulator(int start, int stop, List<String> operationList, List<List<String>> dataList) {
        this.iterationStart = start;
        this.iterationStop = stop;
        this.operationList = operationList;
        this.dataList = dataList;

        keyOp = new KeyOperations();
    }

    public String getCurrentOperation() {
        return currentOperation;
    }

    public int getIteractionCount() {
        return iteractionCount;
    }

    @Override
    public void start() {
        System.out.println("KeySim Started");

        testKeySim();
    }

    @Override
    public void run() {

        String keyStroke;
        String key1;
        String key2;
        int waitTime = 0;

        System.out.println(keyOp);
        keyOp.waitOperation(Globals.KEYSIM_START_WAIT);

        for (int repeat = iterationStart; repeat <= iterationStop; repeat++) {
            iteractionCount = repeat;
            System.out.println("Iteration Count = " + repeat);

            for (String operation : operationList) {
                //operation=operation.trim();
                currentOperation = operation;
                System.out.println("operation = " + currentOperation);

                if (operation.equals("")) {
                    continue;
                }
                if (operation.contains("#")) {
                    keyStroke = operation.split("#")[0];
                    waitTime = Integer.parseInt(operation.split("#")[1]);
                } else {
                    keyStroke = operation;
                }

                if (keyStroke.contains("loop")) {
                    key1 = keyStroke.split("&")[0];
                    // key2 = keyStroke.split("&")[1];
                    keyOp.keyStroke(key1, iteractionCount);
                } else if (keyStroke.contains("type")) {
                    keyOp.typeValue(keyStroke.split("&")[1]);
                } else if (keyStroke.contains("putval")) {
                    key1 = keyStroke.split("&")[1];
                    keyOp.pasteValue(key1);
                } else if (keyStroke.contains("compnt")) {
                    key1 = keyStroke.split("&")[1];
                    keyOp.typeValue(dataList.get(Integer.parseInt(key1) - 1).get(repeat));

                } else if (keyStroke.contains("&")) {
                    key1 = keyStroke.split("&")[0];
                    key2 = keyStroke.split("&")[1];

                    if (Pattern.matches("[0-9]+", key2)) {
                        keyOp.keyStroke(key1, Integer.parseInt(key2));
                    } else {
                        keyOp.keyStroke(key1, key2);
                    }

                    //KeyOperations.keyStroke(key1, key2);       
                } else {
                    if (KeyMapper.isSpecialChar(keyStroke) || Pattern.matches("[A-Z]", keyStroke)) {
                        keyOp.keyStroke(keyStroke);
                    } else {
                        keyOp.keyStroke(keyStroke);
                    }
                }
                if (operation.contains("#")) {
                    keyOp.waitOperation(waitTime);
                } else {
                    keyOp.waitOperation(Globals.OP_WAIT);
                }

            }
        }
    }

    public void testKeySim() {

        keyOp.waitOperation(Globals.KEYSIM_START_WAIT);

        for (int index = iterationStart; index <= iterationStop; index++) {
            System.out.println("Iteration count : " + index);
            iteractionCount = index;

            for (String operation : operationList) {
                System.out.println("\tOperation : " + operation);
                String[] ops = operation.split("\\.");

                boolean windowCheck = false;
                boolean valueCheck = false;

                for (String op : ops) {
                    System.out.println("\t\tSub Operation : " + op);
                    if (op.startsWith("key")) {
                        doKey(op);
                    } else if (op.startsWith("mouse")) {
                        doMouse();
                    } else if (op.startsWith("type")) {
                        doType(op, index);
                    } else if (op.startsWith("paste")) {
                        doPaste(op, index);
                    } else if (op.startsWith("log")) {

                    } else if (op.startsWith("wndCheck")) {

                    } else if (op.startsWith("valueCheck")) {

                    } else if (op.startsWith("do")) {

                    } else if (op.startsWith("wait")) {
                        doWait(op);
                    } else {
                        System.err.println("Invalid operation : " + op);
                    }
                }
                keyOp.waitOperation(Globals.OP_WAIT);
            }
            keyOp.waitOperation(Globals.ITERATION_WAIT);
        }

    }

    private boolean isNumeric(String str) {
        return str.matches("-?\\d+(\\.\\d+)?");  //match a number with optional '-' and decimal.
    }

    private void doKey(String op) {
        String[] keys = op.substring(op.indexOf("(") + 1, op.lastIndexOf(")")).split(",");
        if (keys.length == 1) {
            keyOp.keyStroke(keys[0]);
        } else if (keys.length == 2) {
            if (isNumeric(keys[1])) {
                keyOp.keyStroke(keys[0], Integer.parseInt(keys[1]));
            } else {
                keyOp.keyStroke(keys[0], keys[1]);
            }
        } else if (keys.length == 3) {
            if (isNumeric(keys[2])) {
                keyOp.keyStroke(keys[0], keys[1], Integer.parseInt(keys[2]));
            } else {
                keyOp.keyStroke(keys[0], keys[1], keys[2]);
            }
        } else {
            System.err.println("Invalid Key arguments");
        }
    }

    private void doMouse() {
    }

    private void doWait(String op) {
        int waitTime = Integer.parseInt(op.substring(op.indexOf("(") + 1, op.lastIndexOf(")")));
        keyOp.waitOperation(waitTime);
    }

    private void doType(String op, int index) {
        String value = op.substring(op.indexOf("(") + 1, op.lastIndexOf(")"));

        if (value.contains(Globals.DATA_POINT)) {
            int col = Integer.parseInt(value.split(Globals.DATA_POINT)[1]);
            keyOp.typeValue(dataList.get(col - 1).get(index - 1));
        } else {
            keyOp.typeValue(value);
        }
    }

    private void doPaste(String op, int index) {
        String value = op.substring(op.indexOf("(") + 1, op.lastIndexOf(")"));
        if (value.contains(Globals.DATA_POINT)) {
            int col = Integer.parseInt(value.split(Globals.DATA_POINT)[1]);
            keyOp.pasteValue(dataList.get(col - 1).get(index - 1));
        } else {
            keyOp.pasteValue(value);
        }
    }
}
