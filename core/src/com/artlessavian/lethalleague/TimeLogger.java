package com.artlessavian.lethalleague;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Set;

public class TimeLogger
{
	private static final int LOGLENGTH = 60;

	private static HashMap<String, long[]> lol = new HashMap<String, long[]>();
	private static ArrayList<Long> logInTimeStack = new ArrayList<Long>();

	public static void logIn()
	{
		logInTimeStack.add(System.nanoTime());
	}

	public static void logOut(String key)
	{
		long[] longs = lol.get(key);
		if (longs == null)
		{
			longs = new long[3 + LOGLENGTH];
			// 0 is this frame
			// 1 is worst
			// 2 is where to replace for average
			// 3 ... (LOGLENGTH+3) is for average

			for (int i = 3; i < 3 + LOGLENGTH; i++)
			{
				longs[i] = 0;
			}

			lol.put(key, longs);
		}
		longs[0] = System.nanoTime() - logInTimeStack.remove(logInTimeStack.size()-1);
		longs[1] = Math.max(longs[0], longs[1]);
		longs[(int)(longs[2] % LOGLENGTH) + 3] = longs[0];
		longs[2]++;
	}

	public static float getAverage(String key)
	{
		float sum = 0;
		int ignore = 0;
		long[] ay = lol.get(key);
		for (int i = 3; i < 3 + LOGLENGTH; i++)
		{
			sum += ay[i];
			if (ay[i] == 0)
			{
				ignore++;
			}
		}
		return (sum / 1000f / 1000f) / (LOGLENGTH - ignore);
	}

	public static float getWorst(String key)
	{
		return lol.get(key)[1] / 1000f / 1000f;
	}

	public static float getCurrent(String key)
	{
		return lol.get(key)[0] / 1000f / 1000f;
	}

	public static Set<String> getKeys()
	{
		return lol.keySet();
	}
}
