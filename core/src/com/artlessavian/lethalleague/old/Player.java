/*
package com.artlessavian.lethalleague.old;

import com.artlessavian.lethalleague.PlayerInput;
import com.artlessavian.lethalleague.StateMachine;
import com.artlessavian.lethalleague.playerstates.*;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class Player
{
	Sprite sprite;
	public Vector2 pos;
	public Vector2 vel;
	Vector2 lastPos;
	boolean grounded = false;

	StateMachine stateMachine;

	public PlayerInput input;

	public Player(PlayerInput input)
	{
		sprite = new Sprite(new Texture("badlogic.jpg"));
		sprite.setSize(128, 128);
		pos = new Vector2(0, 100);
		vel = new Vector2();
		lastPos = new Vector2();
		stateMachine = new StateMachine();

		stateMachine.current = new FallState(this);

		this.input = input;
	}

	public void move()
	{
		stateMachine.run();
		// if (pos.y <= 0) {pos.y += 900;}

	}
}*/
