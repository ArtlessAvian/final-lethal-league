package com.artlessavian.lethalleague;

import java.util.HashMap;

public class StateMachine
{
	public State current;
	private HashMap<Class<? extends State>, State> states;

	public StateMachine()
	{
		states = new HashMap<Class<? extends State>, State>();
	}

	public void addState(State state)
	{
		states.put(state.getClass(), state);
	}

	public void gotoState(Class<? extends State> newState)
	{
		current = states.get(newState);
		current.reset();
	}

	public void run()
	{
		while (current.changeStateMaybe(this)) { ; }
		current.doStuff();
	}
}