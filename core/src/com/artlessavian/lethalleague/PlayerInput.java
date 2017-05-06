package com.artlessavian.lethalleague;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;

public class PlayerInput extends InputAdapter
{
    public boolean upPressed;
    public boolean leftPressed;
    public boolean downPressed;
    public boolean rightPressed;

    int upKeycode;
    int leftKeycode;
    int downKeycode;
    int rightKeycode;
    
    public PlayerInput(int playerNumber) {
        if (playerNumber == 0)
        {
            upKeycode = Input.Keys.UP;
            leftKeycode = Input.Keys.LEFT;
            downKeycode = Input.Keys.DOWN;
            rightKeycode = Input.Keys.RIGHT;
        }
        else if (playerNumber == 1)
        {
            upKeycode = Input.Keys.W;
            leftKeycode = Input.Keys.A;
            downKeycode = Input.Keys.S;
            rightKeycode = Input.Keys.D;
        }
    }
    
    @Override
    public boolean keyDown(int keycode) {
        if (keycode == upKeycode) {
        	System.out.println("hi");
            upPressed = true;
        }
        if (keycode == leftKeycode) {
            leftPressed = true;
        }
        if (keycode == downKeycode) {
            downPressed = true;
        }
        if (keycode == rightKeycode) {
            rightPressed = true;
        }
        
        return true;
    }
    
    @Override
    public boolean keyUp(int keycode) {
        if (keycode == upKeycode) {
            upPressed = false;
        }
        if (keycode == leftKeycode) {
            leftPressed = false;
        }
        if (keycode == downKeycode) {
            downPressed = false;
        }
        if (keycode == rightKeycode) {
            rightPressed = false;
        }

        return true;
    }
}