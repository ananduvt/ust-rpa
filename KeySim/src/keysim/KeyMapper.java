/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package keysim;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author u55369
 */
public class KeyMapper {
    
    static HashMap<String,Integer> keyMap;
    static ArrayList<String> splChar;
   
    static void keyLoad()
    {
        keyMap = new HashMap<>();
        
        keyMap.put("a",KeyEvent.VK_A);
        keyMap.put("b",KeyEvent.VK_B);
        keyMap.put("c",KeyEvent.VK_C);
        keyMap.put("d",KeyEvent.VK_D);
        keyMap.put("e",KeyEvent.VK_E);
        keyMap.put("f",KeyEvent.VK_F);
        keyMap.put("g",KeyEvent.VK_G);
        keyMap.put("h",KeyEvent.VK_H);
        keyMap.put("i",KeyEvent.VK_I);
        keyMap.put("j",KeyEvent.VK_J);
        keyMap.put("k",KeyEvent.VK_K);
        keyMap.put("l",KeyEvent.VK_L);
        keyMap.put("m",KeyEvent.VK_M);
        keyMap.put("n",KeyEvent.VK_N);
        keyMap.put("o",KeyEvent.VK_O);
        keyMap.put("p",KeyEvent.VK_P);
        keyMap.put("q",KeyEvent.VK_Q);
        keyMap.put("r",KeyEvent.VK_R);
        keyMap.put("s",KeyEvent.VK_S);
        keyMap.put("t",KeyEvent.VK_T);
        keyMap.put("u",KeyEvent.VK_U);
        keyMap.put("v",KeyEvent.VK_V);
        keyMap.put("w",KeyEvent.VK_W);
        keyMap.put("x",KeyEvent.VK_X);
        keyMap.put("y",KeyEvent.VK_Y);
        keyMap.put("z",KeyEvent.VK_Z);
        
        keyMap.put("0",KeyEvent.VK_0);
        keyMap.put("1",KeyEvent.VK_1);
        keyMap.put("2",KeyEvent.VK_2);
        keyMap.put("3",KeyEvent.VK_3);
        keyMap.put("4",KeyEvent.VK_4);
        keyMap.put("5",KeyEvent.VK_5);
        keyMap.put("6",KeyEvent.VK_6);
        keyMap.put("7",KeyEvent.VK_7);
        keyMap.put("8",KeyEvent.VK_8);
        keyMap.put("9",KeyEvent.VK_9);
        
        keyMap.put("f1",KeyEvent.VK_F1);
        keyMap.put("f2",KeyEvent.VK_F2);
        keyMap.put("f3",KeyEvent.VK_F3);
        keyMap.put("f4",KeyEvent.VK_F4);
        keyMap.put("f5",KeyEvent.VK_F5);
        keyMap.put("f6",KeyEvent.VK_F6);
        keyMap.put("f7",KeyEvent.VK_F7);
        keyMap.put("f8",KeyEvent.VK_F8);
        keyMap.put("f9",KeyEvent.VK_F9);
        keyMap.put("f10",KeyEvent.VK_F10);
        keyMap.put("f11",KeyEvent.VK_F11);
        keyMap.put("f12",KeyEvent.VK_F12);
        
        keyMap.put("esc",KeyEvent.VK_ESCAPE);
        keyMap.put("tab",KeyEvent.VK_TAB);
        keyMap.put("capsLock",KeyEvent.VK_CAPS_LOCK);
        keyMap.put("shift",KeyEvent.VK_SHIFT);
        keyMap.put("ctrl",KeyEvent.VK_CONTROL);
        keyMap.put("alt",KeyEvent.VK_ALT);
        keyMap.put("backspace",KeyEvent.VK_BACK_SPACE);
        keyMap.put("enter",KeyEvent.VK_ENTER);
        keyMap.put("up",KeyEvent.VK_UP);
        keyMap.put("down",KeyEvent.VK_DOWN);
        keyMap.put("left",KeyEvent.VK_LEFT);
        keyMap.put("right",KeyEvent.VK_RIGHT);
        
        keyMap.put(" ",KeyEvent.VK_SPACE);
        keyMap.put("-",KeyEvent.VK_MINUS);
        keyMap.put("=",KeyEvent.VK_EQUALS);      
        keyMap.put(",",KeyEvent.VK_COMMA);
        keyMap.put(".",KeyEvent.VK_PERIOD);
        keyMap.put("/",KeyEvent.VK_SLASH);
        keyMap.put(";",KeyEvent.VK_SEMICOLON);
        keyMap.put("'",KeyEvent.VK_QUOTE);
        keyMap.put("[",KeyEvent.VK_CLOSE_BRACKET);
        keyMap.put("]",KeyEvent.VK_OPEN_BRACKET);
        keyMap.put("\\",KeyEvent.VK_BACK_SLASH);
        
        keyMap.put("_",KeyEvent.VK_MINUS);
        keyMap.put("+",KeyEvent.VK_EQUALS);      
        keyMap.put("<",KeyEvent.VK_COMMA);
        keyMap.put(">",KeyEvent.VK_PERIOD);
        keyMap.put("?",KeyEvent.VK_SLASH);
        keyMap.put(":",KeyEvent.VK_SEMICOLON);
        keyMap.put("\"",KeyEvent.VK_QUOTE);
        keyMap.put("{",KeyEvent.VK_CLOSE_BRACKET);
        keyMap.put("}",KeyEvent.VK_OPEN_BRACKET);
        keyMap.put("|",KeyEvent.VK_BACK_SLASH);
        
        keyMap.put("!",KeyEvent.VK_1);
        keyMap.put("@",KeyEvent.VK_2);
        keyMap.put("#",KeyEvent.VK_3);
        keyMap.put("$",KeyEvent.VK_4);
        keyMap.put("%",KeyEvent.VK_5);
        keyMap.put("^",KeyEvent.VK_6);
        keyMap.put("&",KeyEvent.VK_7);
        keyMap.put("*",KeyEvent.VK_8);
        keyMap.put("(",KeyEvent.VK_9);
        keyMap.put(")",KeyEvent.VK_0);
        
        splChar=new ArrayList<>();
        
        splChar.add("<");
        splChar.add(">");
        splChar.add("?");
        splChar.add(":");
        splChar.add("\"");
        splChar.add("{");
        splChar.add("}");
        splChar.add("|");
        splChar.add("!");
        splChar.add("@");
        splChar.add("#");
        splChar.add("$");
        splChar.add("%");
        splChar.add("^");
        splChar.add("&");
        splChar.add("*");
        splChar.add("(");
        splChar.add(")");
        splChar.add("_");
        splChar.add("+");
    }
    
    static int keyMap(String key)
    {
        System.out.println("key : "+key);
        if(keyMap.containsKey(key))
        {
            return keyMap.get(key);
        }
        else
        {
        return -1;      
        }       
    }
    
    static boolean isSplChar(String s)
    {
        return splChar.contains(s);
    }
} 

