 

import com.artlessavian.lethalleague.State;
import com.artlessavian.lethalleague.StateMachine;
import com.artlessavian.lethalleague.ecs.components.InputComponent;
import com.artlessavian.lethalleague.ecs.components.PhysicsComponent;
import com.artlessavian.lethalleague.ecs.entities.Player;

public class PlayerHitState extends State
{
    Player player;

    public PlayerHitState(Player player)
    {
        this.player = player;
    }

    @Override
    public void exit()
    {
        PhysicsComponent physicsC = player.getComponent(PhysicsComponent.class);
        if(!physicsC.grounded)
        {
            physicsC.vel.x -= Math.signum(physicsC.vel.x) * 10;
        }
    }

    @Override
    public void enter()
    {
        //swing
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
