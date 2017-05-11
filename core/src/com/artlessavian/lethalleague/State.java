package com.artlessavian.lethalleague;

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

	public abstract int getSpriteID();

	public long getTimeInState()
	{
		return sm.runs - lastEnter;
	}
}