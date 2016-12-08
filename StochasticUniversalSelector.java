/**
*
*
* @author	Dana Hughes
* @version	1.0
* @since	2016-11-30
*/

import java.util.Random;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class StochasticUniversalSelector extends Selector
{
	private static Random rand = new Random();

	private int currentIndividualNumber = 0;
	List<Individual> populationList;
	private double totalFitness = 0.0;

	public StochasticUniversalSelector(int populationSize)
	{
		populationList = new ArrayList<Individual>(populationSize);
	}


	public Individual select(Population population)
	{
		// Need to repopulate the populationList if the individual number is reset to 0
		if(currentIndividualNumber == 0)
		{
			for(int i=0; i<population.size(); i++)
			{
				populationList.clear();
				populationList.add(population.getIndividual(i));
				Collections.sort(populationList);
				Collections.reverse(populationList);

				totalFitness += population.getIndividual(i).fitness();
			}
		}

		idx = 0;
		return populationList.get(idx);
	}

}
