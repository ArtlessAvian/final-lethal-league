 

import com.artlessavian.lethalleague.*;
import com.artlessavian.lethalleague.ecs.components.*;
import com.artlessavian.lethalleague.playerstates.*;
import com.badlogic.ashley.core.Entity;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

/**
 * Generic Player to base others around. Extend to create new Players
 * Stores all components a Player usually has, and some functions to override for arbitrary behavior.
 */
public class Player extends Entity
{
	private SpriteComponent spriteC;
	private InputComponent inputC;
	private PhysicsComponent physicsC;
	private HitboxComponent hitboxC;
	private StateComponent stateC;

	public PlayerInputContainer input;

	public float gravity = 2000;
	public float lowGravity = 1000;

	public float jumpVelocity = 700;
	public float hopVelocity = 500;

	public float fastFallSpeed = -1000;
	public float groundMaxSpeed = 400;
	public float airMaxSpeed = 400;

	public float groundAccel = 50;
	public float airAccel = 30;
	public float groundFriction = 10;

	public Player(PlayerInputContainer input)
	{
		this.input = input;
		inputC = new InputComponent(input);
		this.add(inputC);

		physicsC = new PhysicsComponent();
		physicsC.collision.setSize(72, 144);
		this.add(physicsC);

		stateC = new StateComponent();
		this.addAllStates(stateC.machine);
		stateC.machine.gotoState(PlayerStandState.class);
		this.add(stateC);

		spriteC = new SpriteComponent(new Sprite(new Texture("grid.png")));
		spriteC.sprite.setSize(72, 144);
		this.add(spriteC);

		hitboxC = new HitboxComponent();
		this.add(hitboxC);

		StageComponent collisionBehaviorComponent = new StageComponent(new Player.PlayerCollisionBehavior());
		this.add(collisionBehaviorComponent);
	}

	private void addAllStates(StateMachine stateMachine)
	{
		stateMachine.addState(new PlayerStandState(this));
		stateMachine.addState(new PlayerChargeState(this));
		stateMachine.addState(new PlayerSwingState(this));
		stateMachine.addState(new PlayerJumpState(this));
		stateMachine.addState(new PlayerJumpSquatState(this));
		stateMachine.addState(new PlayerCrouchState(this));
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
  
    public static class PlayerCollisionBehavior extends StageComponent.CollisionBehavior
    {
        @Override
        public void onTouchCeil(Stage stage, PhysicsComponent physicsC, Entity thisEntity)
        {
            physicsC.pos.y = stage.bounds.y + stage.bounds.height - physicsC.collision.height / 2f;
            physicsC.vel.y = 0;
        }

        @Override
        public void onTouchFloor(Stage stage, PhysicsComponent physicsC, Entity thisEntity)
        {
            physicsC.grounded = true;
            physicsC.pos.y = stage.bounds.y + physicsC.collision.height / 2f;
            physicsC.vel.y = 0;

            StateComponent stateC = thisEntity.getComponent(StateComponent.class);
            stateC.machine.gotoState(PlayerStandState.class);
        }

        @Override
        public void onTouchLeft(Stage stage, PhysicsComponent physicsC, Entity thisEntity)
        {
            physicsC.pos.x = stage.bounds.x + physicsC.collision.width / 2f;
        }

        @Override
        public void onTouchRight(Stage stage, PhysicsComponent physicsC, Entity thisEntity)
        {
            physicsC.pos.x = stage.bounds.x + stage.bounds.width - physicsC.collision.width / 2f;
        }
    }
}
