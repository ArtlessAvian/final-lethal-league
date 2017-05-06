package com.artlessavian.lethalleague;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;

public class PlayerInput extends InputAdapter
{
	public boolean upPressed;
	public boolean leftPressed;
	public boolean downPressed;
	public boolean rightPressed;

	public boolean upJustPressed;
	public boolean leftJustPressed;
	public boolean downJustPressed;
	public boolean rightJustPressed;

	int upKeycode;
	int leftKeycode;
	int downKeycode;
	int rightKeycode;

	public PlayerInput(int playerNumber)
	{
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
			upPressed = true;
			upJustPressed = true;
			return true;

		}
		if (keycode == leftKeycode)
		{
			leftPressed = true;
			leftJustPressed = true;
			return true;
		}
		if (keycode == downKeycode)
		{
			downPressed = true;
			downJustPressed = true;
			return true;
		}
		if (keycode == rightKeycode)
		{
			rightPressed = true;
			rightJustPressed = true;
			return true;
		}

		return false;
	}

	@Override
	public boolean keyUp(int keycode)
	{
		if (keycode == upKeycode)
		{
			upPressed = false;
			return true;
		}
		if (keycode == leftKeycode)
		{
			leftPressed = false;
			return true;
		}
		if (keycode == downKeycode)
		{
			downPressed = false;
			return true;
		}
		if (keycode == rightKeycode)
		{
			rightPressed = false;
			return true;
		}

		return false;
	}

	public void clearJust()
	{
		upJustPressed = false;
		downJustPressed = false;
		leftJustPressed = false;
		rightJustPressed = false;
	}
}