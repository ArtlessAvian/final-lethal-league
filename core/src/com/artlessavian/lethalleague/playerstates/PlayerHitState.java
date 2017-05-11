/*
package com.artlessavian.lethalleague.playerstates;

import com.artlessavian.lethalleague.State;
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

    }

    @Override
    public void enter()
    {
        //swing
    }

    @Override
    public boolean changeStateMaybe()
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

        if(!physicsC.grounded)
        {


            physicsC.vel.x -= Math.signum(physicsC.vel.x) * player.groundFriction;
        }
    }

    @Override
    public int getSpriteID()
    {
        return 0;
    }
}
*/
