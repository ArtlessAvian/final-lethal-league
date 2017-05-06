package com.artlessavian.lethalleague;

/**
 * One object per state, reused by the same state machine
 */
public interface State
{
	public void exit();

	public void enter();

	public boolean changeStateMaybe(StateMachine sm);

	public void doStuff();
}