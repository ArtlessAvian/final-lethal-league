public class StateMachine
{
	State current;

	public void gotoState(State newState)
	{
		current = newState;
	}

	public void run()
	{
		current.doStuff();
	}
}