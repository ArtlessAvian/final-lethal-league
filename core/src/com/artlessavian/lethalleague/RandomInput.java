package com.artlessavian.lethalleague;

public class RandomInput
{
	static PlayerInputContainer inputs = new PlayerInputContainer();

	static void lmao()
	{
		inputs.swingPressed = Math.random() < 1/60f;
		inputs.leftPressed = Math.random() < 1/2f;
		inputs.rightPressed = Math.random() < 1/2f;
		inputs.upPressed = Math.random() < 1/10f;
		inputs.downPressed = Math.random() < 1/10f;
		inputs.jumpPressed = Math.random() < 1/2f;
	}
}
