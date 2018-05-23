package com.artlessavian.lethalleague;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;

public class PlayerInput extends InputAdapter
{
	int upKeycode;
	int leftKeycode;
	int downKeycode;
	int rightKeycode;
	int swingKeycode;
	int jumpKeycode;

	boolean tapJump = true;

	PlayerInputContainer inputs;

	public PlayerInput(int playerNumber, PlayerInputContainer inputs)
	{
		this.inputs = inputs;

		if (playerNumber == 0)
		{
			upKeycode = Input.Keys.UP;
			leftKeycode = Input.Keys.LEFT;
			downKeycode = Input.Keys.DOWN;
			rightKeycode = Input.Keys.RIGHT;
			swingKeycode = Input.Keys.SHIFT_RIGHT;
			jumpKeycode = Input.Keys.SLASH;
		}
		else if (playerNumber == 1)
		{
			upKeycode = Input.Keys.W;
			leftKeycode = Input.Keys.A;
			downKeycode = Input.Keys.S;
			rightKeycode = Input.Keys.D;
			swingKeycode = Input.Keys.J;
			jumpKeycode = Input.Keys.SPACE;

		}
	}

	@Override
	public boolean keyDown(int keycode)
	{
		if (keycode == upKeycode)
		{
			inputs.upPressed = true;
			inputs.upPressFrame = Gdx.graphics.getFrameId();
			if (tapJump)
			{
				inputs.jumpPressed = true;
				inputs.jumpPressFrame = Gdx.graphics.getFrameId();
			}
			return true;

		}
		if (keycode == leftKeycode)
		{
			inputs.leftPressed = true;
			inputs.leftPressFrame = Gdx.graphics.getFrameId();
			return true;
		}
		if (keycode == downKeycode)
		{
			inputs.downPressed = true;
			inputs.downPressFrame = Gdx.graphics.getFrameId();
			return true;
		}
		if (keycode == rightKeycode)
		{
			inputs.rightPressed = true;
			inputs.rightPressFrame = Gdx.graphics.getFrameId();
			return true;
		}
		if (keycode == swingKeycode)
		{
			inputs.swingPressed = true;
			inputs.swingPressFrame = Gdx.graphics.getFrameId();
			return true;
		}
		if (keycode == jumpKeycode)
		{
			inputs.jumpPressed = true;
			inputs.jumpPressFrame = Gdx.graphics.getFrameId();
			return true;
		}

		return false;
	}

	@Override
	public boolean keyUp(int keycode)
	{
		if (keycode == upKeycode)
		{
			inputs.upPressed = false;
			if (tapJump) {inputs.jumpPressed = false;}
			return true;
		}
		if (keycode == leftKeycode)
		{
			inputs.leftPressed = false;
			return true;
		}
		if (keycode == downKeycode)
		{
			inputs.downPressed = false;
			return true;
		}
		if (keycode == rightKeycode)
		{
			inputs.rightPressed = false;
			return true;
		}
		if (keycode == swingKeycode)
		{
			inputs.swingPressed = false;
			return true;
		}
		if (keycode == jumpKeycode)
		{
			inputs.jumpPressed = false;
			return true;
		}

		return false;
	}
}