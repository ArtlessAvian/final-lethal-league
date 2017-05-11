package com.artlessavian.lethalleague.playerstates;

import com.artlessavian.lethalleague.PlayerInputContainer;
import com.artlessavian.lethalleague.ecs.components.PhysicsComponent;
import com.artlessavian.lethalleague.ecs.entities.Player;

public class CommonPlayerFuncts
{
	public static void horizontalInput(Player player, PhysicsComponent physicsC)
	{
		if (player.input.leftPressed != player.input.rightPressed)
		{
			float accel = (physicsC.grounded) ? player.groundAccel : player.airAccel;

			if (player.input.leftPressed)
			{
				physicsC.vel.x -= accel;
			}
			else //inputC.input.rightPressed
			{
				physicsC.vel.x += accel;
			}
		}
		else
		{
			friction(player, physicsC);
		}
	}

	public static void friction(Player player, PhysicsComponent physicsC)
	{
		physicsC.vel.x -= Math.signum(physicsC.vel.x) * player.groundFriction;
	}

	public static void fall(Player player, PhysicsComponent physicsC)
	{
		if (physicsC.vel.y < 0)
		{
			physicsC.passiveGravity = player.gravity;
		}
		else
		{
			physicsC.passiveGravity = player.lowGravity;
		}
	}

	public static void fastfallCheck(Player player, PhysicsComponent physicsC)
	{
		if (physicsC.vel.y < 0 && player.input.downPressed)
		{
			physicsC.vel.y = player.fastFallSpeed;
		}
	}

	public static void clampMovement(Player player, PhysicsComponent physicsC)
	{
		if (physicsC.grounded)
		{
			if (physicsC.vel.x > player.groundMaxSpeed) {physicsC.vel.x = player.groundMaxSpeed;}
			if (physicsC.vel.x < -player.groundMaxSpeed) {physicsC.vel.x = -player.groundMaxSpeed;}
		}
		else
		{
			if (physicsC.vel.x > player.airMaxSpeed) {physicsC.vel.x = player.airMaxSpeed;}
			if (physicsC.vel.x < -player.airMaxSpeed) {physicsC.vel.x = -player.airMaxSpeed;}
		}
	}
}
