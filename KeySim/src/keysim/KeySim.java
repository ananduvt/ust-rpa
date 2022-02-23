/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package keysim;

/**
 *
 * @author u55369
 */
public class KeySim {

    /**
     * @param args the command line arguments
     *  
     */
    public static void main(String[] args) {
        
        KeyMapper.keyLoad();
        Loader.setConfigFile(args[0]);
        Main.main();
        
        
      
    }
    
    
    
}
