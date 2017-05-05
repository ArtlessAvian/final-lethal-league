package com.artlessavian.lethalleague;

public class StateMachine
{
	State current;

	public StateMachine()
	{

	}

	public void gotoState(State newState)
	{
		newState.reset();
		current = newState;
	}

	public void run()
	{
		while (current.changeStateMaybe(this));
		current.doStuff();
	}
}