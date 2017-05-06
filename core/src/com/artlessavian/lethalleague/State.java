package com.artlessavian.lethalleague;

/**
 * One object per state, reused by the same state machine
 */
public interface State
{
	void exit();

	void enter();

	boolean changeStateMaybe(StateMachine sm);

	void doStuff();
}