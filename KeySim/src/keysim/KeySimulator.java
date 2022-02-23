/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package keysim;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

/**
 *
 * @author u55369
 */
public class KeySimulator extends Thread{
    
    int start;
    int limit;
    int count;
    String operation;
    ArrayList<String> curntOps;
    ArrayList<ArrayList<String>> components;

    public ArrayList getComponents() {
        return components;
    }

    public void setComponents(ArrayList components) {
        this.components = components;
    }
    
    public String getOpr() {
        return operation;
    }

    public void setOpr(String opr) {
        this.operation = opr;
    }
 
    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
    
    public ArrayList<String> getCurntOps() {
        return curntOps;
    }

    public void setCurntOps(ArrayList<String> curntOps) {
        this.curntOps = curntOps;
    }
    
  
    Robot robot;
  
    
    KeySimulator()
    {
        try {
            robot=new Robot();
        } catch (AWTException ex) {
            Logger.getLogger(KeySim.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void run()
    {
        try {
            Thread.sleep(500);
        } catch (InterruptedException ex) {
            Logger.getLogger(KeySim.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        String opr;
        String wtime;
        String key;
        
        for(count=start;count<limit+1;count++)
        {
    
            for(String s : curntOps)
            {
                 if(s.equals(""))
                 {
                     continue;
                 }
              operation=s;
               wtime=s.split("#")[1];
               opr=s.split("#")[0];
                System.out.println("opr = "+opr);
                
               if(opr.contains("loop"))
               {
                  key=opr.split("&")[0];
            
                   for(int i=0;i<count;i++)
                   {
                       robot.keyPress(KeyMapper.keyMap(key));
                       robot.keyRelease(KeyMapper.keyMap(key));
                      try {
                          Thread.sleep(100);
                      } catch (InterruptedException ex) {
                          Logger.getLogger(KeySimulator.class.getName()).log(Level.SEVERE, null, ex);
                      }
                   }
                   
               }
               else if(opr.contains("tofile"))
               {
                    Toolkit toolkit = Toolkit.getDefaultToolkit();
                    Clipboard clipboard = toolkit.getSystemClipboard();
                    String result = null;
                  try {
                      result = (String) clipboard.getData(DataFlavor.stringFlavor);
                  } catch (UnsupportedFlavorException ex) {
                      Logger.getLogger(KeySimulator.class.getName()).log(Level.SEVERE, null, ex);
                  } catch (IOException ex) {
                      Logger.getLogger(KeySimulator.class.getName()).log(Level.SEVERE, null, ex);
                  }
                    System.out.println("String from Clipboard:" + result);
                    
                    File file= new File("c:\\mine\\results.txt");
                     FileWriter fw=null;
                     try {
                        fw=new FileWriter(file,true);
                     } catch (FileNotFoundException ex) {
                         Logger.getLogger(KeySimulator.class.getName()).log(Level.SEVERE, null, ex);
                     } catch (IOException ex) {
                         Logger.getLogger(KeySimulator.class.getName()).log(Level.SEVERE, null, ex);
                     }
                     try {
                         fw.append(result);
                         fw.append("\n#####\n");
                        
                     } catch (IOException ex) {
                         Logger.getLogger(KeySimulator.class.getName()).log(Level.SEVERE, null, ex);
                     }
                     try {
                         fw.close();
                     } catch (IOException ex) {
                         Logger.getLogger(KeySimulator.class.getName()).log(Level.SEVERE, null, ex);
                     }
                     
               }
               else if(opr.contains("type"))
               {
                   
                   /** clipboard method
                   Toolkit toolkit = Toolkit.getDefaultToolkit();
                    Clipboard clipboard = toolkit.getSystemClipboard();

                    //StringSelection strs=new StringSelection("hello asan");
                    clipboard.setContents(new StringSelection(opr.split("&")[1]), null);

                    robot.keyPress(KeyEvent.VK_CONTROL);
                    robot.keyPress(KeyEvent.VK_V);
                    robot.keyRelease(KeyEvent.VK_V);
                    robot.keyRelease(KeyEvent.VK_CONTROL);
                    
                    
                    String result = null;
                  try {
                      result = (String) clipboard.getData(DataFlavor.stringFlavor);
                  } catch (UnsupportedFlavorException ex) {
                      Logger.getLogger(KeySimulator.class.getName()).log(Level.SEVERE, null, ex);
                  } catch (IOException ex) {
                      Logger.getLogger(KeySimulator.class.getName()).log(Level.SEVERE, null, ex);
                  }
                    System.out.println("String from Clipboard:" + result);
                  */
                   
                  
                   char[] word=opr.split("&")[1].toCharArray();
                   
                   for(char c:word)
                   {
                       if(Character.isUpperCase(c))
                       {
                           robot.keyPress(KeyEvent.VK_SHIFT);
                           robot.keyPress(KeyMapper.keyMap(String.valueOf(Character.toLowerCase(c))));
                           robot.keyRelease(KeyMapper.keyMap(String.valueOf(Character.toLowerCase(c))));
                           robot.keyRelease(KeyEvent.VK_SHIFT);
                       }
                        else if(KeyMapper.isSplChar(Character.toString(c)))
                               {
                                   robot.keyPress(KeyEvent.VK_SHIFT);
                                   robot.keyPress(KeyMapper.keyMap(String.valueOf(c)));
                                   robot.keyRelease(KeyMapper.keyMap(String.valueOf(c)));
                                   robot.keyRelease(KeyEvent.VK_SHIFT);
                               }
                       else
                       {
                       robot.keyPress(KeyMapper.keyMap(String.valueOf(c)));
                       robot.keyRelease(KeyMapper.keyMap(String.valueOf(c)));
                       }
                       try {
                           Thread.sleep(20);
                       } catch (InterruptedException ex) {
                           Logger.getLogger(KeySimulator.class.getName()).log(Level.SEVERE, null, ex);
                       }
                   }
                   
               }
               else if(opr.contains("compnt"))
               {
                    int listno=Integer.parseInt(opr.split("&")[1]);
                    String comp=components.get(listno-1).get(count);
                    
                    Toolkit toolkit = Toolkit.getDefaultToolkit();
                    Clipboard clipboard = toolkit.getSystemClipboard();

                    //StringSelection strs=new StringSelection("hello asan");
                    clipboard.setContents(new StringSelection(comp), null);

                    robot.keyPress(KeyEvent.VK_CONTROL);
                    robot.keyPress(KeyEvent.VK_V);
                    robot.keyRelease(KeyEvent.VK_V);
                    robot.keyRelease(KeyEvent.VK_CONTROL);
                    
                    
                    String result = null;
                  try {
                      result = (String) clipboard.getData(DataFlavor.stringFlavor);
                  } catch (UnsupportedFlavorException ex) {
                      Logger.getLogger(KeySimulator.class.getName()).log(Level.SEVERE, null, ex);
                  } catch (IOException ex) {
                      Logger.getLogger(KeySimulator.class.getName()).log(Level.SEVERE, null, ex);
                  }
                    System.out.println("String from Clipboard:" + result);
                    
                   /* 
                    char[] word=comp.toCharArray();
                    for(char c:word)
                   {   
                       
                       if(Character.isUpperCase(c))
                       {
                           robot.keyPress(KeyEvent.VK_SHIFT);
                           robot.keyPress(KeyMapper.keyMap(String.valueOf(Character.toLowerCase(c))));
                           robot.keyRelease(KeyMapper.keyMap(String.valueOf(Character.toLowerCase(c))));
                           robot.keyRelease(KeyEvent.VK_SHIFT);
                       }
                       else if(KeyMapper.isSplChar(Character.toString(c)))
                               {
                                   robot.keyPress(KeyEvent.VK_SHIFT);
                                   robot.keyPress(KeyMapper.keyMap(String.valueOf(c)));
                                   robot.keyRelease(KeyMapper.keyMap(String.valueOf(c)));
                                   robot.keyRelease(KeyEvent.VK_SHIFT);
                               }
                       else   
                       {
                       robot.keyPress(KeyMapper.keyMap(String.valueOf(c)));
                       robot.keyRelease(KeyMapper.keyMap(String.valueOf(c)));
                       }
                    */
                       try {
                           Thread.sleep(50);
                       } catch (InterruptedException ex) {
                           Logger.getLogger(KeySimulator.class.getName()).log(Level.SEVERE, null, ex);
                       }
                   }
            
               
               else if(opr.contains("shift"))
               {
                   robot.keyPress(KeyMapper.keyMap(opr.split("&")[0]));
                   robot.keyPress(KeyMapper.keyMap(opr.split("&")[1]));
                   try {
                           Thread.sleep(20);
                       } catch (InterruptedException ex) {
                           Logger.getLogger(KeySimulator.class.getName()).log(Level.SEVERE, null, ex);
                       }
                   robot.keyRelease(KeyMapper.keyMap(opr.split("&")[1]));
                   robot.keyRelease(KeyMapper.keyMap(opr.split("&")[0]));
               }
               else if(opr.contains("&"))
               {
                  if(Pattern.matches("[0-9]+", opr.split("&")[1]))
                  {
                    int j=Integer.parseInt(opr.split("&")[1]);
                    
                    for(int k=0;k<j;k++)
                    {
                    robot.keyPress(KeyMapper.keyMap(opr.split("&")[0]));
                    robot.keyRelease(KeyMapper.keyMap(opr.split("&")[0]));
                    }
                  }
                   
                   else
                   {
                   robot.keyPress(KeyMapper.keyMap(opr.split("&")[0]));
                   robot.keyPress(KeyMapper.keyMap(opr.split("&")[1]));
                   try {
                           Thread.sleep(20);
                       } catch (InterruptedException ex) {
                           Logger.getLogger(KeySimulator.class.getName()).log(Level.SEVERE, null, ex);
                       }
                   robot.keyRelease(KeyMapper.keyMap(opr.split("&")[1]));
                   robot.keyRelease(KeyMapper.keyMap(opr.split("&")[0]));
                  }
               }
               else
               {
                   if(Character.isUpperCase(opr.charAt(0)))
                   {
                       robot.keyPress(KeyEvent.VK_SHIFT);
                       robot.keyPress(KeyMapper.keyMap(String.valueOf(Character.toLowerCase(opr.charAt(0)))));
                       robot.keyRelease(KeyMapper.keyMap(String.valueOf(Character.toLowerCase(opr.charAt(0)))));
                       robot.keyRelease(KeyEvent.VK_SHIFT);
                   }
                   robot.keyPress(KeyMapper.keyMap(opr));
                   robot.keyRelease(KeyMapper.keyMap(opr));
               }
              
                try {
                    Thread.sleep(Integer.parseInt(wtime));
                } catch (InterruptedException ex) {
                    System.out.println(ex);
                    Logger.getLogger(KeySimulator.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    
}
