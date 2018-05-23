package com.artlessavian.lethalleague;

import com.artlessavian.lethalleague.ecs.components.BallComponent;
import com.artlessavian.lethalleague.ecs.components.PhysicsComponent;

public class RandomInput
{
	static PlayerInputContainer inputs = new PlayerInputContainer();

	static int randomWalk = 0;

	static GameScreen game;
	static PlayerInfo me;

	static void lmao()
	{
		try
		{
			PhysicsComponent ballPhysicsC = game.ball.instance.getComponent(PhysicsComponent.class);
			PhysicsComponent physicsC = me.instance.getComponent(PhysicsComponent.class);

			int ballTeam = game.ball.instance.getComponent(BallComponent.class).team;

			inputs.rightPressed = ballPhysicsC.pos.x > physicsC.pos.x + 25;
			inputs.leftPressed = ballPhysicsC.vel.x < physicsC.pos.x - 25;
			if (me.team != ballTeam && ballTeam != -1)
			{
				inputs.rightPressed = Math.random() < 0.1 && inputs.rightPressed;
				inputs.leftPressed = Math.random() < 0.1 && inputs.leftPressed;
			}
			inputs.swingPressed = Math.random() < 0.3;
			inputs.jumpPressed = Math.random() < 0.3;
			inputs.upPressed = Math.random() < 0.3;
			inputs.downPressed = Math.random() < 0.7;
		}
		catch (Exception e)
		{

		}


//				if ((Math.random() * 2 - 1) + Math.sin(System.nanoTime() / 100000) < 0)
//				{
//					randomWalk--;
//				}
//				else
//				{
//					randomWalk++;
//				}
//				System.out.println((Math.random() * 2 - 1) + Math.sin(System.nanoTime() / 100000));
//
//				inputs.swingPressed = Math.random() < 10/60f;
//				inputs.leftPressed = randomWalk < -5;
//				inputs.rightPressed = randomWalk > 5;
//				inputs.upPressed = Math.random() < 1/10f;
//				inputs.downPressed = Math.random() < 1/10f;
//				inputs.jumpPressed = Math.random() < 1/10f;
	}
}
