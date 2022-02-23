package Util;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The class which helps to map the key and its corresponding key event.
 *
 * @author u55369
 */
public class KeyMapper {

    /**
     * @param keyMap Hash Map for string the key and it's corresponding KeyEvent
     * value
     * @param splChar List of special characters which needs shift operation
     */
    private static Map<String, Integer> keyMap;
    private static List<String> specialChar;

    // Used to Load the Map and List with the key values
    static {

        keyMap = new HashMap<>();
        // Adding mouse buttons
        keyMap.put("mLeft", KeyEvent.BUTTON1_DOWN_MASK);
        keyMap.put("mMiddle", KeyEvent.BUTTON2_DOWN_MASK);
        keyMap.put("mRight", KeyEvent.BUTTON3_DOWN_MASK);

        // Adding alphabets to the Map
        keyMap.put("a", KeyEvent.VK_A);
        keyMap.put("b", KeyEvent.VK_B);
        keyMap.put("c", KeyEvent.VK_C);
        keyMap.put("d", KeyEvent.VK_D);
        keyMap.put("e", KeyEvent.VK_E);
        keyMap.put("f", KeyEvent.VK_F);
        keyMap.put("g", KeyEvent.VK_G);
        keyMap.put("h", KeyEvent.VK_H);
        keyMap.put("i", KeyEvent.VK_I);
        keyMap.put("j", KeyEvent.VK_J);
        keyMap.put("k", KeyEvent.VK_K);
        keyMap.put("l", KeyEvent.VK_L);
        keyMap.put("m", KeyEvent.VK_M);
        keyMap.put("n", KeyEvent.VK_N);
        keyMap.put("o", KeyEvent.VK_O);
        keyMap.put("p", KeyEvent.VK_P);
        keyMap.put("q", KeyEvent.VK_Q);
        keyMap.put("r", KeyEvent.VK_R);
        keyMap.put("s", KeyEvent.VK_S);
        keyMap.put("t", KeyEvent.VK_T);
        keyMap.put("u", KeyEvent.VK_U);
        keyMap.put("v", KeyEvent.VK_V);
        keyMap.put("w", KeyEvent.VK_W);
        keyMap.put("x", KeyEvent.VK_X);
        keyMap.put("y", KeyEvent.VK_Y);
        keyMap.put("z", KeyEvent.VK_Z);
        // Adding the numerals to the Map
        keyMap.put("0", KeyEvent.VK_0);
        keyMap.put("1", KeyEvent.VK_1);
        keyMap.put("2", KeyEvent.VK_2);
        keyMap.put("3", KeyEvent.VK_3);
        keyMap.put("4", KeyEvent.VK_4);
        keyMap.put("5", KeyEvent.VK_5);
        keyMap.put("6", KeyEvent.VK_6);
        keyMap.put("7", KeyEvent.VK_7);
        keyMap.put("8", KeyEvent.VK_8);
        keyMap.put("9", KeyEvent.VK_9);
        // Adding the function keys to the Map
        keyMap.put("f1", KeyEvent.VK_F1);
        keyMap.put("f2", KeyEvent.VK_F2);
        keyMap.put("f3", KeyEvent.VK_F3);
        keyMap.put("f4", KeyEvent.VK_F4);
        keyMap.put("f5", KeyEvent.VK_F5);
        keyMap.put("f6", KeyEvent.VK_F6);
        keyMap.put("f7", KeyEvent.VK_F7);
        keyMap.put("f8", KeyEvent.VK_F8);
        keyMap.put("f9", KeyEvent.VK_F9);
        keyMap.put("f10", KeyEvent.VK_F10);
        keyMap.put("f11", KeyEvent.VK_F11);
        keyMap.put("f12", KeyEvent.VK_F12);
        // Adding the control & navigation keys to the Map
        keyMap.put("esc", KeyEvent.VK_ESCAPE);
        keyMap.put("tab", KeyEvent.VK_TAB);
        keyMap.put("capslock", KeyEvent.VK_CAPS_LOCK);
        keyMap.put("shift", KeyEvent.VK_SHIFT);
        keyMap.put("ctrl", KeyEvent.VK_CONTROL);
        keyMap.put("alt", KeyEvent.VK_ALT);
        keyMap.put("backspace", KeyEvent.VK_BACK_SPACE);
        keyMap.put("enter", KeyEvent.VK_ENTER);
        keyMap.put("up", KeyEvent.VK_UP);
        keyMap.put("down", KeyEvent.VK_DOWN);
        keyMap.put("left", KeyEvent.VK_LEFT);
        keyMap.put("right", KeyEvent.VK_RIGHT);

        keyMap.put("prntscrn", KeyEvent.VK_PRINTSCREEN);
        keyMap.put("windows", KeyEvent.VK_WINDOWS);
        keyMap.put("delete", KeyEvent.VK_DELETE);
        keyMap.put("pagedown", KeyEvent.VK_PAGE_DOWN);
        keyMap.put("pageup", KeyEvent.VK_PAGE_UP);

        // Adding the special characters to the Map
        keyMap.put(" ", KeyEvent.VK_SPACE);
        keyMap.put("space", KeyEvent.VK_SPACE);
        keyMap.put("-", KeyEvent.VK_MINUS);
        keyMap.put("=", KeyEvent.VK_EQUALS);
        keyMap.put(",", KeyEvent.VK_COMMA);
        keyMap.put(".", KeyEvent.VK_PERIOD);
        keyMap.put("/", KeyEvent.VK_SLASH);
        keyMap.put(";", KeyEvent.VK_SEMICOLON);
        keyMap.put("'", KeyEvent.VK_QUOTE);
        keyMap.put("[", KeyEvent.VK_CLOSE_BRACKET);
        keyMap.put("]", KeyEvent.VK_OPEN_BRACKET);
        keyMap.put("\\", KeyEvent.VK_BACK_SLASH);
        // Adding the special Characters which need shift operation to the Map
        keyMap.put("_", KeyEvent.VK_MINUS);
        keyMap.put("+", KeyEvent.VK_EQUALS);
        keyMap.put("<", KeyEvent.VK_COMMA);
        keyMap.put(">", KeyEvent.VK_PERIOD);
        keyMap.put("?", KeyEvent.VK_SLASH);
        keyMap.put(":", KeyEvent.VK_SEMICOLON);
        keyMap.put("\"", KeyEvent.VK_QUOTE);
        keyMap.put("{", KeyEvent.VK_CLOSE_BRACKET);
        keyMap.put("}", KeyEvent.VK_OPEN_BRACKET);
        keyMap.put("|", KeyEvent.VK_BACK_SLASH);

        keyMap.put("!", KeyEvent.VK_1);
        keyMap.put("@", KeyEvent.VK_2);
        keyMap.put("#", KeyEvent.VK_3);
        keyMap.put("$", KeyEvent.VK_4);
        keyMap.put("%", KeyEvent.VK_5);
        keyMap.put("^", KeyEvent.VK_6);
        keyMap.put("&", KeyEvent.VK_7);
        keyMap.put("*", KeyEvent.VK_8);
        keyMap.put("(", KeyEvent.VK_9);
        keyMap.put(")", KeyEvent.VK_0);

        specialChar = new ArrayList<>();
        // Adding the special Characters which need shift operation to the List
        specialChar.add("<");
        specialChar.add(">");
        specialChar.add("?");
        specialChar.add(":");
        specialChar.add("\"");
        specialChar.add("{");
        specialChar.add("}");
        specialChar.add("|");
        specialChar.add("!");
        specialChar.add("@");
        specialChar.add("#");
        specialChar.add("$");
        specialChar.add("%");
        specialChar.add("^");
        specialChar.add("&");
        specialChar.add("*");
        specialChar.add("(");
        specialChar.add(")");
        specialChar.add("_");
        specialChar.add("+");
    }

    /**
     * The function to map between the key and its corresponding key event.
     *
     * @param key The input string to get the corresponding key event value.
     * @return Key event value for the key input, returns -1 if the key is
     * invalid.
     */
    public static int MapKey(String key) {

        //System.out.println("key : " + key);
        if (keyMap.containsKey(key)) {
            return keyMap.get(key);
        } else {
            return -1;
        }
    }

    /**
     * To check the key is a special character which need shift operation.
     *
     * @param key Check whether the key is a special key
     * @return True if the key is present in the splChar List.
     */
    public static boolean isSpecialChar(String key) {
        return specialChar.contains(key);
    }
}
