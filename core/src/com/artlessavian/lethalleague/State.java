package com.artlessavian.lethalleague;

import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * One object per state, reused by the same state machine
 */
public abstract class State
{
	long lastEnter;
	long lastExit;
	public StateMachine sm;

	public abstract void exit();

	public abstract void enter();

	public abstract boolean changeStateMaybe();

	public abstract void doStuff();

	public abstract void editSprite(Sprite sprite);

	public long getTimeInState()
	{
		return sm.runs - lastEnter;
	}
}