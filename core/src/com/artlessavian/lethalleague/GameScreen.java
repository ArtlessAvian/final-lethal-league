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

	public GameScreen(Maineroni main)
	{
		this.main = main;
		this.cam = new OrthographicCamera(980, 540);
		cam.translate(0, cam.viewportHeight/2f);

		cam.update();

		p1 = new Player();
		p2 = new Player();
		ball = new Ball();
	}

	public void render(float delta)
	{
		doStuff();

		main.batch.setProjectionMatrix(cam.combined);
		main.batch.begin();
		main.font.draw(main.batch, "player1", p1.pos.x, p1.pos.y + 6);
		main.font.draw(main.batch, "player2", p2.pos.x, p2.pos.y + 6);
		main.font.draw(main.batch, "ball", ball.pos.x, ball.pos.y + 6);
		main.batch.end();
	}

	public void doStuff()
	{
		// move players
		// move ball
		//    do move
		//    check collisions
		//    check wall collision
		//    
		// kill people
	}
}