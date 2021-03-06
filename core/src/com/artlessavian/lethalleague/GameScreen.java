package com.artlessavian.lethalleague;

//import com.artlessavian.lethalleague.entities.Ball;

import com.artlessavian.lethalleague.ecs.entities.Particle;
import com.artlessavian.lethalleague.ecs.entities.Player;
import com.artlessavian.lethalleague.ecs.systems.*;
import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.EntitySystem;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.ScreenAdapter;

public class GameScreen extends ScreenAdapter
{
	static final float deltaTime = 1/60f;

	public Maineroni main;

	public static Engine engine;
	EntitySystem[] drawSystems;

	public Stage stage;

	public PlayerInfo p1;
	public PlayerInfo p2;
	public BallInfo ball;

	public boolean isStocks = false;

	public GameScreen(Maineroni main, boolean ai)
	{
		this.main = main;

		this.stage = new Stage();

		// Engine and all Systems
		engine = new Engine();
		engine.addSystem(new TimersSystem());
		engine.addSystem(new GameLogicSystem(main, this, isStocks));
		engine.addSystem(new StateSystem());
		engine.addSystem(new PhysicsSystem(stage));
		// engine.addSystem(new AntiBallStealSystem());
		engine.addSystem(new HitboxCollisionSystem());
		engine.addSystem(new BallSystem());
//		engine.addSystem(new HitlagSystem());
		engine.addSystem(new RemoveSystem());

		// Drawing Systems
		drawSystems = new EntitySystem[2];
		DrawSystem drawSystem = new DrawSystem(main, this);
		engine.addSystem(drawSystem);
		drawSystems[0] = drawSystem;

		GUIDrawSystem guiDrawSystem = new GUIDrawSystem(main, this);
		engine.addSystem(guiDrawSystem);
		drawSystems[1] = guiDrawSystem;

//		DebugDrawSystem debugDrawSystem = new DebugDrawSDwystem(main, this, drawSystem);
//		engine.addSystem(debugDrawSystem);
//		drawSystems[2] = debugDrawSystem;

		// Player/Ball Factories

		if (!ai)
		{
			p1 = new PlayerInfo(Player.class, main.getInput(0), 0, 0);
		}
		else
		{
			p1 = new PlayerInfo(Player.class, RandomInput.inputs, 0, 0);
			RandomInput.game = this;
			RandomInput.me = p1;
		}
		p2 = new PlayerInfo(Player.class, main.getInput(1), 1, 1);
		ball = new BallInfo(drawSystem);

		engine.addEntity(p1.spawn());
		engine.addEntity(p2.spawn());
		engine.addEntity(ball.spawn());
	}

	public int engineRuns = 0;
	float rollover = 0;
	boolean timeStop = false;
	float timeScale = 1/1f;

	public void render(float delta)
	{
		if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {main.setScreen(new TitleScreen(main));}

		// TODO: Remove me!
		if (Gdx.input.isKeyJustPressed(Input.Keys.MINUS)) {timeStop = !timeStop;}
		if (!timeStop || Gdx.input.isKeyJustPressed(Input.Keys.EQUALS))
		{
			rollover += delta * timeScale;
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

	public static void makePoof(float x, float y)
	{
		for (int i = 0; i < 50; i++)
		{
			engine.addEntity(new Particle(
				x + (float)Math.random() * 10 - 5,
				y + (float)Math.random() * 10 - 5,
				Particle.ParticleThing.poof, (int)(Math.random() * 10) + 10
			));
		}
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