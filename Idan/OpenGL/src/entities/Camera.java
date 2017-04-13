package entities;

import org.lwjgl.input.Keyboard;
import org.lwjgl.util.vector.Vector3f;

/**
 *
 * @author quadmium
 */
public class Camera {
    
    private Vector3f position = new Vector3f(0, 0, 0);
    private float pitch; // up and down
    private float yaw;   // left and right
    private float roll;  // tilted -> 180 = upside down
    
    public Camera()
    {
        
    }
    
    public void move()
    {
        if(Keyboard.isKeyDown(Keyboard.KEY_W))
        {
            position.z -= 0.02f;
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_S))
        {
            position.z += 0.02f;
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_D))
        {
            position.x += 0.02f;
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_A))
        {
            position.x -= 0.02f;
        }
    }

    public Vector3f getPosition() {
        return position;
    }

    public float getPitch() {
        return pitch;
    }

    public float getYaw() {
        return yaw;
    }

    public float getRoll() {
        return roll;
    }
    
}
