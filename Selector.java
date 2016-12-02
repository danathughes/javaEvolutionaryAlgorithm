/**
*
*
* @author	Dana Hughes
* @version	1.0
* @since	2016-11-30
*/

import java.util.Random;

public class Selector
{
	private static Random rand = new Random();

	public Selector()
	{
	}


	public Individual select(Population population)
	{
		double[] probs = new double[population.size()];
		double total = 0.0;

		for(int i=0; i<population.size(); i++)
		{
			probs[i] = (float) population.getIndividual(i).getFitness();
			total += (float) population.getIndividual(i).getFitness();
		}

		for(int i=0; i<population.size(); i++)
		{
			probs[i] /= total;
		}

		double p = rand.nextDouble();
		int idx = 0;

		while(p >= probs[idx])
		{
			p -= probs[idx];
			idx += 1;
		}
		
		return population.getIndividual(idx);
	}

}
