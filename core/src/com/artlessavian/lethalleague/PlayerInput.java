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
	public boolean keyDown(int keycode)
	{
		if (keycode == upKeycode)
		{
			inputs.upPressed = true;
			inputs.upPressFrame = Gdx.graphics.getFrameId();
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

		return false;
	}

	@Override
	public boolean keyUp(int keycode)
	{
		if (keycode == upKeycode)
		{
			inputs.upPressed = false;
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

		return false;
	}
}