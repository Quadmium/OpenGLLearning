/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package engineTester;

import entities.Camera;
import entities.Entity;
import entities.Light;
import org.lwjgl.opengl.Display;
import renderEngine.DisplayManager;
import renderEngine.Loader;
import models.RawModel;
import models.TexturedModel;
import org.lwjgl.util.vector.Vector3f;
import renderEngine.OBJLoader;
import renderEngine.Renderer;
import shaders.StaticShader;
import textures.ModelTexture;

/**
 *
 * @author Al-Zahir
 */
public class MainGameLoop {

    public static void main(String[] args) {

        DisplayManager.createDisplay();

        Loader loader = new Loader();
        StaticShader shader = new StaticShader();
        Renderer renderer = new Renderer(shader);

        RawModel model = OBJLoader.loadObjModel("dragon", loader);

        ModelTexture texture = new ModelTexture(loader.loadTexture("white"));

        TexturedModel staticModel = new TexturedModel(model, texture);
        
        texture.setShineDamper(10);
        texture.setReflectivity(1);

        Entity entity = new Entity(staticModel, new Vector3f(0, -5, -25), 0, 0, 0, 1);
        
        Light light = new Light(new Vector3f(0, 0, -20), new Vector3f(1, 1, 1));

        Camera camera = new Camera();

        while (!Display.isCloseRequested()) {

            camera.move()

            renderer.prepare();

            shader.start();
            
            shader.loadLight(light);
            
            shader.loadViewMatrix(camera);

            renderer.render(entity, shader);

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
