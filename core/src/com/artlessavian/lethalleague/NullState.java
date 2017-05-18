package com.artlessavian.lethalleague;

import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * Intentionally Empty State. Avoids NPE.
 */
public class NullState extends State
{
	// TODO: Move into State

	public static NullState singleton = new NullState();

	@Override
	public void exit() {}

	@Override
	public void enter() {}

	@Override
	public boolean changeStateMaybe()
	{
		return false;
	}

	@Override
	public void doStuff() {}

	@Override
	public void editSprite(Sprite sprite) {}
}
