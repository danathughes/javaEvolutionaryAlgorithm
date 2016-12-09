/**
*
*
* @author	Dana Hughes
* @version	1.0
* @since	2016-12-07
*/

package placeholder.EvolutionaryProgramming.selection;

import placeholder.EvolutionaryProgramming.base.Individual;
import placeholder.EvolutionaryProgramming.base.Population;

import java.util.Random;

public class RouletteWheelSelector extends Selector
{
	private static Random rand = new Random();

	public RouletteWheelSelector()
	{
	}


	public Individual select(Population population)
	{
		double[] probs = new double[population.size()];
		double total = 0.0;

		for(int i=0; i<population.size(); i++)
		{
			probs[i] = (float) population.getIndividual(i).fitness().value();
			total += (float) population.getIndividual(i).fitness().value();
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
