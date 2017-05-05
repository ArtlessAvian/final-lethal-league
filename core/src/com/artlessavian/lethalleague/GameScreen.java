package com.artlessavian.lethalleague;

import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class GameScreen extends ScreenAdapter
{
	Maineroni main;
	OrthographicCamera cam;

	Player p1;
	Player p2;
	Ball ball;
	Stage stage;

	public GameScreen(Maineroni main)
	{
		this.main = main;
		this.cam = new OrthographicCamera(980, 540);
		cam.translate(0, cam.viewportHeight/2f);

		cam.update();

		p1 = new Player(main.getInput(0));
		p1.vel.add(2, 2);
		p2 = new Player(main.getInput(1));
		ball = new Ball();
		stage = new Stage();
	}

	float rollover = 0;

	public void render(float delta)
	{
		rollover += delta;
		for (; rollover > 0; rollover -= 1/60f) {doStuff();}

		main.batch.setProjectionMatrix(cam.combined);
		main.batch.begin();

		// stage.sprite.draw(main.batch);

		p1.sprite.setPosition(p1.pos.x - p1.sprite.getWidth()/2f, p1.pos.y);
		p1.sprite.draw(main.batch);
		p2.sprite.setPosition(p2.pos.x - p2.sprite.getWidth()/2f, p2.pos.y);
		p2.sprite.draw(main.batch);

		main.font.draw(main.batch, "player1", p1.pos.x, p1.pos.y + 6);
		main.font.draw(main.batch, "player2", p2.pos.x, p2.pos.y + 6);
		main.font.draw(main.batch, "ball", ball.pos.x, ball.pos.y + 6);
		main.batch.end();
	}

	public void doStuff()
	{
		p1.move();
		p2.move();

		// move ball
		//    do move
		//    check collisions
		//    check wall collision
		//    
		// kill people
	}
}