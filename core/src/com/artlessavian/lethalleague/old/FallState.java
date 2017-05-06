/*
package com.artlessavian.lethalleague.old;

import com.artlessavian.lethalleague.State;
import com.artlessavian.lethalleague.StateMachine;
import com.artlessavian.lethalleague.old.Player;

public class FallState implements State
{
	Player player;

	public FallState(Player player)
	{
		this.player = player;
	}

	public void reset()
	{

	}

	public boolean changeStateMaybe(StateMachine sm)
	{
		if (player.pos.y < 0)
		{
			// sm.gotoState(new GroundedState(player));
		}

		return false;
	}

	public void doStuff()
	{
		// if (player.vel.y > -10)
		// {
		// 	player.vel.y = Math.max(-10, player.vel.y - 0.3f);
		// }
		// if (player.vel.y < 0 && player.input.downPressed)
		// {
		// 	if (player.input.downPressed)
		// 	{
		// 		player.vel.y = -20;
		// 	}
		// }

		// if (player.input.leftPressed) {player.vel.x = Math.max(-10, player.vel.x - 1);}
		// if (player.input.rightPressed) {player.vel.x = Math.max(-10, player.vel.x - 1);}

		// player.pos.add(player.vel);
		// if (player.pos.y < -128) {player.pos.y += 900;}
		// 
		
		// if (player.input.upPressed) {player.pos.y += 1;}
	}
}*/
