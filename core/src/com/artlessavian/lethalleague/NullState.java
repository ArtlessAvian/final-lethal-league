package com.artlessavian.lethalleague;

/**
 * Intentionally Empty State. Avoids NPE.
 */
public class NullState extends State
{
	public static NullState singleton = new NullState();

	@Override
	public void exit() {}

	@Override
	public void enter() {}

	@Override
	public boolean changeStateMaybe(StateMachine sm)
	{
		return false;
	}

	@Override
	public void doStuff() {}

	@Override
	public int getSpriteID() { return 0; }
}
