package com.artlessavian.lethalleague;

import com.badlogic.gdx.Gdx;

import java.util.HashMap;

public class StateMachine
{
	public State current = NullState.singleton;
	private HashMap<Class<? extends State>, State> states;

	public StateMachine()
	{
		states = new HashMap<Class<? extends State>, State>();
	}

	public void addState(State state)
	{
		states.put(state.getClass(), state);
	}

	/**
	 * Using this, a state can transfer to an unknown state, "overriding" default behavior
	 * @param clazz
	 * @param state
	 */
	public void addState(Class<? extends State> clazz, State state)
	{
		states.put(clazz, state);
	}

	public void gotoState(Class<? extends State> newState)
	{
		current.exit();
		current = states.get(newState);
		current.enter();
	}

	public void run()
	{
		for (int i = 0; i < 10 && current.changeStateMaybe(this); i++) { ; }
		current.doStuff();
	}
}