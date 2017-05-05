package com.artlessavian.lethalleague;

public interface State
{
	public void reset();
	public boolean changeStateMaybe(StateMachine sm);
	public void doStuff();
}