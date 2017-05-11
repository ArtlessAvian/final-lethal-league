package com.artlessavian.lethalleague.playerstates;

import com.artlessavian.lethalleague.State;
import com.artlessavian.lethalleague.ecs.components.PhysicsComponent;
import com.artlessavian.lethalleague.ecs.entities.Player;

public class PlayerSmashState extends State
{
	Player player;

	public PlayerSmashState(Player player)
	{
		this.player = player;
	}

	@Override
	public void exit()
	{
		// TODO

	}

	@Override
	public void enter()
	{
		// TODO

	}

	@Override
	public boolean changeStateMaybe()
	{
		// TODO
		return false;
	}

	@Override
	public void doStuff()
	{
		PhysicsComponent physicsC = player.getComponent(PhysicsComponent.class);

		// TODO
	}

	@Override
	public int getSpriteID()
	{
		return 0;
	}
}