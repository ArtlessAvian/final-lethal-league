package com.artlessavian.lethalleague;

//import com.artlessavian.lethalleague.entities.Ball;

import com.artlessavian.lethalleague.ecs.components.RemoveComponent;
import com.artlessavian.lethalleague.ecs.entities.Ball;
import com.artlessavian.lethalleague.ecs.entities.Particle;
import com.artlessavian.lethalleague.ecs.entities.Player;
import com.artlessavian.lethalleague.ecs.systems.*;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Texture;

public class GameScreen extends ScreenAdapter
{
	static final float deltaTime = 1/60f;

	public Maineroni main;

	public Engine engine;
	EntitySystem[] drawSystems;

	public Stage stage;

	public PlayerInfo p1;
	public PlayerInfo p2;
	public BallInfo ball;

	public boolean isStocks = false;

	public GameScreen(Maineroni main)
	{
		this.main = main;

		this.stage = new Stage();

		engine = new Engine();
		engine.addSystem(new GameLogicSystem(main, this, isStocks));
		engine.addSystem(new StateSystem());
		engine.addSystem(new PhysicsSystem(stage));
		engine.addSystem(new HitboxCollisionSystem());
		engine.addSystem(new BallSystem());
		engine.addSystem(new HitlagSystem());
		engine.addSystem(new RemoveSystem());

		drawSystems = new EntitySystem[3];
		DrawSystem drawSystem = new DrawSystem(main, this);
		engine.addSystem(drawSystem);
		drawSystems[0] = drawSystem;

		GUIDrawSystem guiDrawSystem = new GUIDrawSystem(main, this);
		engine.addSystem(guiDrawSystem);
		drawSystems[1] = guiDrawSystem;

		DebugDrawSystem debugDrawSystem = new DebugDrawSystem(main, this, drawSystem);
		engine.addSystem(debugDrawSystem);
		drawSystems[2] = debugDrawSystem;

		p1 = new PlayerInfo(Player.class, main.getInput(0), 0, 0);
		p2 = new PlayerInfo(Player.class, main.getInput(1), 1, 1);
		ball = new BallInfo(drawSystem);

		engine.addEntity(p1.spawn());
		engine.addEntity(p2.spawn());
		engine.addEntity(ball.spawn());

//		 hue
//		for (int i = 0; i < 10; i++)
//		{
//			engine.addEntity(new Ball(drawSystem));
//		}
	}

	public int engineRuns = 0;
	float rollover = 0;
	boolean timeStop = false;
	float timeSlow = 1/1f;

	public void render(float delta)
	{
		// TODO: Remove me!
		if (Gdx.input.isKeyJustPressed(Input.Keys.MINUS)) {timeStop = !timeStop;}
		if (!timeStop || Gdx.input.isKeyJustPressed(Input.Keys.EQUALS))
		{
			rollover += delta * timeSlow;
		}

		for (; rollover > 0; rollover -= deltaTime)
		{
			RandomInput.lmao();
			engineRuns++;
			engine.update(deltaTime);
		}

//		System.out.println(rollover / deltaTime + 1);
		for (EntitySystem sys : drawSystems) {sys.update(rollover / deltaTime + 1f);}
	}

//	public boolean isRoundOver()
//	{
//		int count = 0;
//		if (p1.getComponent(RemoveComponent.class) != null) {count++;}
//		if (p2.getComponent(RemoveComponent.class) != null) {count++;}
//		if (p3.getComponent(RemoveComponent.class) != null) {count++;}
//
//		return count == 1;
//	}
}