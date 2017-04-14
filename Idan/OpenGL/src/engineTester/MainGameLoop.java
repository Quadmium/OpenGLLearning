package engineTester;

import entities.Camera;
import entities.Entity;
import entities.Light;
import models.RawModel;
import models.TexturedModel;
import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;
import renderEngine.*;
import terrains.Terrain;
import textures.ModelTexture;

/**
 *
 * @author quadmium
 */
public class MainGameLoop
{
    public static void main(String[] args)
    {        
        DisplayManager.createDisplay();
        Loader loader = new Loader();
        
        RawModel model = OBJLoader.loadObjModel("tree", loader);
        ModelTexture texture = new ModelTexture(loader.loadTexture("tree"));
        //texture.setShineDamper(10);
        //texture.setReflectivity(1);
        TexturedModel staticModel = new TexturedModel(model, texture);
        
        Entity entity = new Entity(staticModel, new Vector3f(20, 0, -25), 0, 0, 0, 1);
        
        Entity hut = new Entity(new TexturedModel(
                OBJLoader.loadObjModel("stall", loader), 
                new ModelTexture(loader.loadTexture("stallTexture"))), 
                new Vector3f(0, 0, -10), 0, 180, 0, 1);
        
        ModelTexture dragonModelTex = new ModelTexture(loader.loadTexture("brown"));
        dragonModelTex.setShineDamper(10);
        dragonModelTex.setReflectivity(1);
        
        Entity dragon = new Entity(new TexturedModel(
                OBJLoader.loadObjModel("dragon", loader), 
                dragonModelTex), 
                new Vector3f(30, 0, -10), 0, 180, 0, 1);
        
        Light light = new Light(new Vector3f(3000, 2000, 2000), new Vector3f(1, 1, 1));
        
        Terrain terrain = new Terrain(0, -1, loader, new ModelTexture(loader.loadTexture("grass")));
        Terrain terrain2 = new Terrain(-1, -1, loader, new ModelTexture(loader.loadTexture("grass")));
        
        Camera camera = new Camera();
        camera.setPosition(new Vector3f(0, 5, 0));
        
        MasterRenderer renderer = new MasterRenderer();
        while(!Display.isCloseRequested())
        {
            entity.increaseRotation(0, 0.5f, 0);
            hut.increaseRotation(0, 0.5f, 0);
            dragon.increaseRotation(0, 0.5f, 0);
            camera.move();
            
            renderer.processTerrain(terrain);
            renderer.processTerrain(terrain2);
            renderer.processEntity(entity);
            renderer.processEntity(hut);
            renderer.processEntity(dragon);
            
            renderer.render(light, camera);
            DisplayManager.updateDisplay();
        }
        
        renderer.cleanUp();
        loader.cleanUp();
        DisplayManager.closeDisplay();
    }
}
