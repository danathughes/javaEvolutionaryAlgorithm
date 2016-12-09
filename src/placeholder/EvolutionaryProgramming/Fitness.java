/**
*
*
* @author	Dana Hughes
* @version	1.0
* @since	2016-12-08
*/

import java.util.Random;

public class Fitness implements Comparable<Fitness>
{
	private double fitness;

	public Fitness(double value)
	{
		fitness = value;
	}


	public double value()
	{
		return fitness;
	}


	public int compareTo(Fitness other)
	{
		int results = 0;

		if (this.value() > other.value())
		{
			results = 1;
		}
		if (other.value() > this.value())
		{
			results = -1;
		}

		return results;
	}
}
