/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package engineTester;

import entities.Camera;
import entities.Entity;
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

        RawModel model = OBJLoader.loadObjModel("stall", loader);

        ModelTexture texture = new ModelTexture(loader.loadTexture("stallTexture"));

        TexturedModel staticModel = new TexturedModel(model, texture);

        Entity entity = new Entity(staticModel, new Vector3f(0, 0, -50), 0, 0, 0, 1);

        Camera camera = new Camera();

        while (!Display.isCloseRequested()) {

            camera.move();
            
            entity.increaseRotation(0, 1, 0);

            renderer.prepare();

            shader.start();
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
