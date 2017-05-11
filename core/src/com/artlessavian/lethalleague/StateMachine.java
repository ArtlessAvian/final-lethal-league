package com.artlessavian.lethalleague;

import java.util.HashMap;

/**
 * A State Based Machine for arbitary usage
 */
public class StateMachine
{
	int runs = 0;
	public State current = NullState.singleton;
	private HashMap<Class<? extends State>, State> states;

	public StateMachine()
	{
		states = new HashMap<Class<? extends State>, State>();
	}

	public void addState(State state)
	{
		states.put(state.getClass(), state);
		state.sm = this;
	}

	/**
	 * Using this, any state can pretend to be generic state, "overriding" default behavior
	 *
	 * @param clazz Class of State to override
	 * @param state Overriding State
	 */
	public void addState(Class<? extends State> clazz, State state)
	{
		states.put(clazz, state);
		state.sm = this;
	}

	public void gotoState(Class<? extends State> newState)
	{
		current.lastExit = runs;
		current.exit();
		current = states.get(newState);
		current.enter();
		current.lastEnter = runs;
	}

	public void run()
	{
		for (int i = 0; i < 10 && current.changeStateMaybe(); i++) { }
		current.doStuff();
		runs++;
	}
}