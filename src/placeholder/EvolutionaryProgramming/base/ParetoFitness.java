/**
* NOTE: Not yet implementd!
*
* @author	Dana Hughes
* @version	1.0
* @since	2016-12-12
*/

package placeholder.EvolutionaryProgramming.base;

import java.util.Random;

public class ParetoFitness extends Fitness 
{
	private int dimensionality;
	protected double[] paretoFitness;

	public ParetoFitness(int dimensionality, double[] values)
	{
		super(0.0);

		this.dimensionality = dimensionality;
		paretoFitness = new double[dimensionality];

		for(int i=0; i<dimensionality; i++)
		{
			paretoFitness[i] = values[i];
		}
	}


	public int fitnessSize()
	{
		return dimensionality;
	}


	public double[] fitness()
	{
		return paretoFitness;
	}


	public double value()
	{
		return 10*paretoFitness[0] + paretoFitness[1];
	}


	public int compareTo(ParetoFitness other)
	{
		int results = 0;

		if(this.isDominated(other))
		{
			results = -1;
		}
		if(other.isDominated(this))
		{
			results = 1;
		}

		return results;
	}


	public boolean isDominated(ParetoFitness other)
	{
		boolean dominated = true;

		for(int i=0; i<dimensionality; i++)
		{
			if(this.paretoFitness[i] >= other.paretoFitness[i])
			{
				dominated = false;
			}
		}

		return dominated;
	}
}
