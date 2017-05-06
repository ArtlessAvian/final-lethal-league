package com.artlessavian.lethalleague;

import com.badlogic.gdx.Gdx;

/**
 * One object per state, reused by the same state machine
 */
public abstract class State
{
	long lastEnter;
	long lastExit;

	public abstract void exit();

	public abstract void enter();

	public abstract boolean changeStateMaybe(StateMachine sm);

	public abstract void doStuff();

	public abstract int getSpriteID();

	public long getTimeInState()
	{
		return Gdx.graphics.getFrameId() - lastEnter;
	}
}