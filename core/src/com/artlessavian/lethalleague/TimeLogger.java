package com.artlessavian.lethalleague;

import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Set;

public class TimeLogger
{
	private static HashMap<String, long[]> lol = new HashMap<String, long[]>();
	private static long logInTime;

	public static void logIn()
	{
		logInTime = System.nanoTime();
	}

	public static void logOut(String key)
	{
		long[] longs = lol.get(key);
		if (longs == null)
		{
			longs = new long[60];
			lol.put(key, longs);
		}
		longs[(int)(Math.random() * 60)] = System.nanoTime() - logInTime;
	}

	public static float get(String key)
	{
		float average = 0;
		for (long l : lol.get(key))
		{
			average += l;
		}
		return average/60f / 1000f / 1000f;
	}

	public static Set<String> getKeys()
	{
		return lol.keySet();
	}
}
