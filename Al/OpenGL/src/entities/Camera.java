/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.util.vector.Vector3f;

/**
 *
 * @author Al-Zahir
 */
public class Camera {
    
    private Vector3f position = new Vector3f(0, 0, 0);
    private float pitch; //up and down
    private float yaw; //left and right
    private float roll; //tilt

    public Camera() {}
    
    public void move(){
    
        if(Keyboard.isKeyDown(Keyboard.KEY_W))
            position.z -= 0.2f;
        
        if(Keyboard.isKeyDown(Keyboard.KEY_S))
            position.z += 0.2f;
        
        if(Keyboard.isKeyDown(Keyboard.KEY_D))
            position.x += 0.2f;
        
        if(Keyboard.isKeyDown(Keyboard.KEY_A))
            position.x -= 0.2f;
        
        if(Keyboard.isKeyDown(Keyboard.KEY_SPACE))
            position.y += 0.2f;
        
        if(Keyboard.isKeyDown(Keyboard.KEY_LSHIFT))
            position.y -= 0.2f;
        
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

    public void setPosition(Vector3f position) {
        this.position = position;
    }

    public void setPitch(float pitch) {
        this.pitch = pitch;
    }

    public void setYaw(float yaw) {
        this.yaw = yaw;
    }

    public void setRoll(float roll) {
        this.roll = roll;
    }
    
    
    
}
