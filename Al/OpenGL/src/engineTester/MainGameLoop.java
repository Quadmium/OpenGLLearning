/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package engineTester;

import org.lwjgl.opengl.Display;
import renderEngine.DisplayManager;
import renderEngine.Loader;
import renderEngine.RawModel;
import renderEngine.Renderer;
import shaders.StaticShader;

/**
 *
 * @author Al-Zahir
 */
public class MainGameLoop {
    
    public static void main(String[] args) {
        
        DisplayManager.createDisplay();
        
        Loader loader = new Loader();
        Renderer renderer = new Renderer();
        StaticShader shader = new StaticShader();
        
        float[] vertices = {
        
            -0.5f, 0.5f, 0f,    //V0
            -0.5f, -0.5f, 0f,   //V1
            0.5f, -0.5f, 0f,    //V2
            0.5f, 0.5f, 0f      //V3
            
        };
        
        int[] indices = {
        
            0, 1, 3,    //Top left triangle (V0, V1, V3)
            3, 1, 2     //Bottom right triangle (V3, V1, V2)
            
        };
        
        RawModel model = loader.loadToVAO(vertices, indices);
        
        while(!Display.isCloseRequested()){
            
            renderer.prepare();
            shader.start();
            renderer.render(model);
            shader.stop();
        
            //game logic
            //render
            DisplayManager.updateDisplay();
            
        }
        
        shader.cleanUp();
        loader.cleanUp();
        DisplayManager.closeDisplay();
        
    }
    
}
