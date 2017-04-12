/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package engineTester;

import org.lwjgl.opengl.Display;
import renderEngine.DisplayManager;

/**
 *
 * @author Al-Zahir
 */
public class MainGameLoop {
    
    public static void main(String[] args) {
        
        DisplayManager.createDisplay();
        
        while(!Display.isCloseRequested()){
        
            //game logic
            //render
            DisplayManager.updateDisplay();
            
        }
        
        DisplayManager.closeDisplay();
        
    }
    
}
