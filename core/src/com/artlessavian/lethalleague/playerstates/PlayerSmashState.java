package com.artlessavian.lethalleague.playerstates;

import com.artlessavian.lethalleague.State;
import com.artlessavian.lethalleague.ecs.components.HitboxComponent;
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
		HitboxComponent hitboxC = player.getComponent(HitboxComponent.class);
		hitboxC.hitboxes.remove(player.swingBox);
	}

	@Override
	public void enter()
	{
		// TODO
		HitboxComponent hitboxC = player.getComponent(HitboxComponent.class);
		hitboxC.hitboxes.add(player.swingBox);

		hitboxC.cannotHit.clear();
	}

	@Override
	public boolean changeStateMaybe()
	{
		// TODO
		if (getTimeInState() > 30)
		{
			PhysicsComponent physicsC = player.getComponent(PhysicsComponent.class);
			if (physicsC.grounded)
			{
				sm.gotoState(PlayerStandState.class);
			}
			else
			{
				sm.gotoState(PlayerJumpState.class);
			}
			return true;
		}
		return false;
	}

	@Override
	public void doStuff()
	{
		PhysicsComponent physicsC = player.getComponent(PhysicsComponent.class);

		//TODO

		if (!physicsC.grounded)
		{
			CommonPlayerFuncts.fall(player, physicsC);
			CommonPlayerFuncts.fastfallCheck(player, physicsC);

			CommonPlayerFuncts.horizontalInput(player, physicsC);
			CommonPlayerFuncts.clampMovement(player, physicsC);
		}
		else
		{
			CommonPlayerFuncts.friction(player, physicsC);
		}
	}

	@Override
	public int getSpriteID()
	{
		return 0;
	}
}