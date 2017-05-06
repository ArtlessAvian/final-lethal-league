package com.artlessavian.lethalleague;

public class NullState implements State
{
	public static NullState singleton = new NullState();

	@Override
	public void exit()
	{

	}

	@Override
	public void enter()
	{

	}

	@Override
	public boolean changeStateMaybe(StateMachine sm)
	{
		return false;
	}

	@Override
	public void doStuff()
	{

	}
}
