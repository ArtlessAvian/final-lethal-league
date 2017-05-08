 

import com.artlessavian.lethalleague.State;
import com.artlessavian.lethalleague.StateMachine;
import com.artlessavian.lethalleague.ecs.components.InputComponent;
import com.artlessavian.lethalleague.ecs.components.PhysicsComponent;
import com.artlessavian.lethalleague.ecs.entities.Player;

public class PlayerJumpState extends State
{
	Player player;

	public PlayerJumpState(Player player)
	{
		this.player = player;
	}

	@Override
	public void exit()
	{
		//DONE
	}

	@Override
	public void enter()
	{
		PhysicsComponent physicsC = player.getComponent(PhysicsComponent.class);
		physicsC.grounded = false;
	}

	@Override
	public boolean changeStateMaybe(StateMachine sm)
	{
		return false;
	}

	@Override
	public void doStuff()
	{
		PhysicsComponent physicsC = player.getComponent(PhysicsComponent.class);

		if (player.input.leftPressed != player.input.rightPressed)
		{
			if (player.input.leftPressed)
			{
				physicsC.vel.x -= 50;
			}
			else //inputC.input.rightPressed
			{
				physicsC.vel.x += 50;
			}
		}
		else
		{
			physicsC.vel.x -= Math.signum(physicsC.vel.x) * 10;
		}
		physicsC.vel.clamp(0, 300);
	}

	@Override
	public int getSpriteID()
	{
		return 0;
	}
}
