 

import com.artlessavian.lethalleague.State;
import com.artlessavian.lethalleague.StateMachine;
import com.artlessavian.lethalleague.ecs.components.InputComponent;
import com.artlessavian.lethalleague.ecs.components.PhysicsComponent;
import com.artlessavian.lethalleague.ecs.entities.Player;

public class PlayerJumpSquatState extends State
{
    Player player;

    public PlayerJumpSquatState(Player player)
    {
        this.player = player;
    }

    @Override
    public void exit()
    {
        PhysicsComponent physicsC = player.getComponent(PhysicsComponent.class);
        physicsC.vel.y = 900;
    }

    @Override
    public void enter()
    {
        PhysicsComponent physicsC = player.getComponent(PhysicsComponent.class);
        physicsC.grounded = true;
    }

    @Override
    public boolean changeStateMaybe(StateMachine sm)
    {
        if(getTimeInState() > 5)
        {
            sm.gotoState(PlayerJumpState.class);
        }
        return false;
    }

    @Override
    public void doStuff()
    {
        PhysicsComponent physicsC = player.getComponent(PhysicsComponent.class);
        
        physicsC.vel.x -= Math.signum(physicsC.vel.x) * 10;
    }

    @Override
    public int getSpriteID()
    {
        return 0;
    }
}
