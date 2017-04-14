/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package engineTester;

import entities.Camera;
import entities.Entity;
import entities.Light;
import java.util.ArrayList;
import java.util.List;
import org.lwjgl.opengl.Display;
import renderEngine.DisplayManager;
import renderEngine.Loader;
import models.RawModel;
import models.TexturedModel;
import org.lwjgl.util.vector.Vector3f;
import renderEngine.MasterRenderer;
import renderEngine.OBJLoader;
import terrain.Terrain;
import textures.ModelTexture;

/**
 *
 * @author Al-Zahir
 */
public class MainGameLoop {

    public static void main(String[] args) {

        DisplayManager.createDisplay();

        Loader loader = new Loader();

        RawModel model = OBJLoader.loadObjModel("tree", loader);

        ModelTexture texture = new ModelTexture(loader.loadTexture("tree"));

        TexturedModel staticModel = new TexturedModel(model, texture);
        
        List<Entity> trees = new ArrayList<Entity>();
        
        for(int i = 0; i < 1000; i++)
            trees.add(new Entity(staticModel, new Vector3f((float)(Math.random() * 400 - 200), 0, -(float)(Math.random() * 400)), 0, 0, 0, 1));
        
        Light light = new Light(new Vector3f(3000, 2000, 2000), new Vector3f(1, 1, 1));
        
        Terrain terrain = new Terrain(0, -1, loader, new ModelTexture(loader.loadTexture("grass")));
        Terrain terrain2 = new Terrain(-1, -1, loader, new ModelTexture(loader.loadTexture("grass")));

        Camera camera = new Camera();
        camera.setPosition(new Vector3f(0, 1, 0));
        
        MasterRenderer renderer = new MasterRenderer();

        while (!Display.isCloseRequested()) {

            camera.move();
            
            renderer.processTerrain(terrain);
            renderer.processTerrain(terrain2);
            
            for(Entity entity : trees)
                renderer.processEntity(entity);
            
            renderer.render(light, camera);
            
            DisplayManager.updateDisplay();

        }

        renderer.cleanUp();
        loader.cleanUp();
        DisplayManager.closeDisplay();

    }

}
