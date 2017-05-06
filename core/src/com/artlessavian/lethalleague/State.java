package com.artlessavian.lethalleague;

import com.badlogic.gdx.Gdx;

/**
 * One object per state, reused by the same state machine
 */
public abstract class State
{
	long lastEnter;
	long lastExit;

	abstract void exit();

	abstract void enter();

	abstract boolean changeStateMaybe(StateMachine sm);

	abstract void doStuff();

	public abstract int getSpriteID();

	public long getTimeInState()
	{
		return Gdx.graphics.getFrameId() - lastEnter;
	}
}