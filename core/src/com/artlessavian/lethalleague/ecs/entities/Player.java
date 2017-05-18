package com.artlessavian.lethalleague.ecs.entities;

import com.artlessavian.lethalleague.*;
import com.artlessavian.lethalleague.ecs.components.*;
import com.artlessavian.lethalleague.playerstates.*;
import com.badlogic.ashley.core.Entity;
import com.badlogic.ashley.core.Engine;
import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * Generic Player to base others around. Extend to create new Players
 * Stores all components a Player usually has, and some functions to override for arbitrary behavior.
 */
public class Player extends Entity
{
	private PlayerInfo playerInfo;
	public PlayerInputContainer input;

	public float gravity = 4000;
	public float lowGravity = 2000;

	public float jumpVelocity = 1200;
//	public float hopVelocity = 800;

	public float fastFallSpeed = 2000;
	public float groundMaxSpeed = 400;
	public float airMaxSpeed = 400;
	public float maxFallSpeed = 1200; // should be same as jump speed because physics

	public float groundAccel = 50;
	public float airAccel = 30;
	public float groundFriction = 10;

	public float upAngle = 60;
	public float straightAngle = 0;
	public float downAngle = -15;
	public float smashAngle = -45;

	public Ball ball = null;

	public OffsetRectangle swingBox;
//	public OffsetRectangle smashBox; // helps you not get rsi

	public int walkFrames = 4; // should be 8, but the sprite sheet i like only has 4

	public Player(PlayerInfo playerInfo)
	{
		this.playerInfo = playerInfo;
		this.input = playerInfo.inputs;
		InputComponent inputC = new InputComponent(input);
		this.add(inputC);

		PhysicsComponent physicsC = new PhysicsComponent();
		physicsC.collision.setSize(72, 144);
		physicsC.pos.x = (float)(Math.random() * 1000 - 500);
		physicsC.pos.y = 360;
		this.add(physicsC);

//		boolean isSpriteSheet = false;
		Sprite s;
//		if (playerInfo.number == 0)
//		{
//			s = new Sprite(new Texture("butts.png"));
//			s.setSize(190, 144);
//		}
//		else
//		else if (Math.random() < 0.5f)
		{
//			s = new Sprite(new Texture("not_a_trace.png"));
//			s.setSize(144, 144);
		}
//		else
//		{
//			s = new Sprite(new Texture("creation tools/spritesheet guideline.png"));
//			s = new Sprite(new Texture("creation tools/sample.png"));
			s = new Sprite(playerInfo.texture);
			s.setSize(144, 144);
//			isSpriteSheet = true;
//		}

		SpriteComponent spriteC = new SpriteComponent(s);
		spriteC.usingTestSpriteSheet = true;
		this.add(spriteC);

		HitboxComponent hitboxC = new HitboxComponent(new PlayerHittingBehavior(), playerInfo.team);
		hitboxC.intangible = 120;
		this.add(hitboxC);

		this.add(new PlayerComponent(playerInfo));

		StageComponent collisionBehaviorComponent = new StageComponent(new Player.PlayerCollisionBehavior());
		this.add(collisionBehaviorComponent);

		this.add(new HitlagComponent());

		swingBox = new OffsetRectangle(0, 0, 144, 144);

		StateComponent stateC = new StateComponent();
		this.addAllStates(stateC.machine);
		stateC.machine.gotoState(PlayerJumpState.class);
		this.add(stateC);
	}

	private void addAllStates(StateMachine stateMachine)
	{
		stateMachine.addState(new PlayerStandState(this));
//		stateMachine.addState(new PlayerChargeState(this));
		stateMachine.addState(new PlayerSwingState(this));
		stateMachine.addState(new PlayerSmashState(this));
		stateMachine.addState(new PlayerJumpState(this));
//		stateMachine.addState(new PlayerJumpSquatState(this));
		stateMachine.addState(new PlayerCrouchState(this));
	}

	/**
	 * @return if a player can super
	 */
	public boolean canSuper()
	{
		return false;
	}

	public float getAngle()
	{
		float angle = -100000000;

		if (input.upPressed) {angle = upAngle;}
		else if (input.downPressed) {angle = downAngle;}
		else if (input.leftPressed || input.rightPressed) {angle = straightAngle;}

		if (angle == -100000000)
		{

			long lastPressed = Math.max(input.downPressFrame, input.upPressFrame);
			lastPressed = Math.max(lastPressed, input.leftPressFrame);
			lastPressed = Math.max(lastPressed, input.rightPressFrame);

			if (lastPressed == input.leftPressFrame || lastPressed == input.rightPressFrame)
			{
				angle = straightAngle;
			}
			else if (lastPressed == input.upPressFrame)
			{
				angle = upAngle;
			}
			else
			{
				angle = downAngle;
			}
		}

		if (getComponent(PhysicsComponent.class).facingLeft)
		{
			return 180 - angle;
		}
		else
		{
			return angle;
		}
	}

	public float getSmashAngle()
	{
		if (getComponent(PhysicsComponent.class).facingLeft)
		{
			return 180 - smashAngle;
		}
		else
		{
			return smashAngle;
		}
	}

	public static class PlayerCollisionBehavior extends StageComponent.CollisionBehavior
	{
		@Override
		public void onTouchCeil(Stage stage, PhysicsComponent physicsC, Entity thisEntity)
		{
//			physicsC.pos.y = stage.bounds.y + stage.bounds.height - physicsC.collision.height;
//			physicsC.vel.y = 0;
		}

		@Override
		public void onTouchFloor(Stage stage, PhysicsComponent physicsC, Entity thisEntity)
		{
			physicsC.grounded = true;
			physicsC.pos.y = stage.bounds.y;
			physicsC.vel.y = 0;

			StateComponent stateC = thisEntity.getComponent(StateComponent.class);
//
// if ((!((Player)thisEntity).input.swingPressed && false) ||
			if (stateC.machine.current.getClass() != PlayerSwingState.class && stateC.machine.current.getClass() != PlayerSmashState.class)
			{
				stateC.machine.gotoState(PlayerStandState.class);
			}

			GameScreen.makePoof(physicsC.pos.x, physicsC.pos.y);
		}

		@Override
		public void onTouchLeft(Stage stage, PhysicsComponent physicsC, Entity thisEntity)
		{
			physicsC.pos.x = stage.bounds.x + physicsC.collision.width / 2f;
			physicsC.vel.x = 0;
		}

		@Override
		public void onTouchRight(Stage stage, PhysicsComponent physicsC, Entity thisEntity)
		{
			physicsC.pos.x = stage.bounds.x + stage.bounds.width - physicsC.collision.width / 2f;
			physicsC.vel.x = 0;
		}
    }

	private static class PlayerHittingBehavior implements HitboxComponent.HitBehavior
	{
		@Override
		public void onHit(Entity thisEntity, Entity other, boolean isSmash, Engine engine)
		{
			if (other instanceof Player) {return;}

			HitboxComponent hitboxC = thisEntity.getComponent(HitboxComponent.class);
			hitboxC.cannotHit.add(other);

			PhysicsComponent physicsC = thisEntity.getComponent(PhysicsComponent.class);
			physicsC.vel.y = 0;

			if (other instanceof Ball)
			{
				((Player)thisEntity).ball = (Ball)other;
			}
		}

		@Override
		public void onGetHit(Entity thisEntity, Entity other, boolean isSmash, Engine engine)
		{
			if (other instanceof Ball)
			{
				PlayerComponent playerC = thisEntity.getComponent(PlayerComponent.class);
				playerC.playerInfo.stocks--;
				thisEntity.add(new RemoveComponent());

				PlayerCorpse corpse = new PlayerCorpse((Player)thisEntity);

				engine.addEntity(corpse);
			}
		}
	}
}
