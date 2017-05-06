package com.artlessavian.lethalleague;

public interface State
{
	void reset();
	boolean changeStateMaybe(StateMachine sm);
	void doStuff();
}