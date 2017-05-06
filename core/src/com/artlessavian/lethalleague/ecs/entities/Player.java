package com.artlessavian.lethalleague.ecs.entities;

import com.artlessavian.lethalleague.GameScreen;
import com.artlessavian.lethalleague.PlayerInput;
import com.artlessavian.lethalleague.Stage;
import com.artlessavian.lethalleague.ecs.components.*;
import com.artlessavian.lethalleague.playerstates.PlayerStandState;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

;

/**
 * Stores all components a Player usually has, and some functions to override for arbitrary behavior
 */
public class Player extends Entity
{
	private SpriteComponent spriteC;
	private InputComponent inputC;
	private PhysicsComponent physicsC;
	private StateComponent stateC;

	PlayerInput input;

	public Player(PlayerInput input)
	{
		inputC = new InputComponent(input);
		this.add(inputC);

		physicsC = new PhysicsComponent();
		this.add(physicsC);

		stateC = new StateComponent();
		stateC.machine.addState(new PlayerStandState(this));
		stateC.machine.gotoState(PlayerStandState.class);
		this.add(stateC);

		spriteC = new SpriteComponent(new Sprite(new Texture("grid.png")));
		spriteC.sprite.setSize(72, 144);
		this.add(spriteC);

		StageComponent collisionBehaviorComponent = new StageComponent(new Player.PlayerCollisionBehavior());
		this.add((StageComponent)collisionBehaviorComponent);
	}

	/**
	 * Called whenever a player dies
	 */
	public void onDie()
	{

	}

	/**
	 * Called whenever a player hits a ball
	 */
	public void onHit()
	{

	}

	/**
	 * Called whenever the player is responsible for another dying
	 */
	public void onKill()
	{

	}

	/**
	 * @return if a player can super
	 */
	public boolean canSuper()
	{
		return false;
	}

	/**
	 * Called when the player is in hitlag, and presses hit again
	 *
	 * @param game the game, to allow for crazy accesses and modification
	 */
	public void onSuper(GameScreen game)
	{

	}

	public static class PlayerCollisionBehavior extends StageComponent.CollisionBehavior
	{
		@Override
		public void onTouchCeil(Stage stage, PhysicsComponent physicsC, Entity thisEntity)
		{

		}

		@Override
		public void onTouchFloor(Stage stage, PhysicsComponent physicsC, Entity thisEntity)
		{
			physicsC.pos.y = stage.bounds.y;
			physicsC.vel.y = 0;

			StateComponent stateC = thisEntity.getComponent(StateComponent.class);
			stateC.machine.gotoState(PlayerStandState.class);
		}

		@Override
		public void onTouchLeft(Stage stage, PhysicsComponent physicsC, Entity thisEntity)
		{

		}

		@Override
		public void onTouchRight(Stage stage, PhysicsComponent physicsC, Entity thisEntity)
		{

		}
	}
}
