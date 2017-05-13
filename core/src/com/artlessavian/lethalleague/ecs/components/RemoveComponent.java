package com.artlessavian.lethalleague.ecs.components;

import com.badlogic.ashley.core.Component;
import com.badlogic.gdx.Gdx;

public class RemoveComponent implements Component
{
	long removeTime = Gdx.graphics.getFrameId();
}
