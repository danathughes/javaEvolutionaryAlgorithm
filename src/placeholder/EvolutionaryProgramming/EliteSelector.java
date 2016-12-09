/**
*
*
* @author	Dana Hughes
* @version	1.0
* @since	2016-12-08
*/

import java.util.Random;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class EliteSelector extends Selector
{
	private static Random rand = new Random();

	private Selector baseSelector;
	private int numElite;
	
	private int currentIndividual = 0;
	private List<Individual> sortedPopulationList;

	public EliteSelector(Selector base, int numElite)
	{
		this.baseSelector = base;
		this.numElite = numElite;
	}


	public Individual select(Population population)
	{
		if(currentIndividual == 0)
		{
			sortedPopulationList = new ArrayList<Individual>(population.size());

			sortedPopulationList.clear();

			for(int i=0; i<population.size(); i++)
			{
				sortedPopulationList.add(population.getIndividual(i));
			}

			Collections.sort(sortedPopulationList);
			Collections.reverse(sortedPopulationList);
		}

		Individual selectedIndividual = null;

		if(currentIndividual < numElite)
		{
			selectedIndividual = sortedPopulationList.get(currentIndividual);
		}
		else
		{
			selectedIndividual = baseSelector.select(population);
		}

		currentIndividual += 1;
		if(currentIndividual == population.size())
		{
			currentIndividual = 0;
		}
		
		return selectedIndividual;
	}
}
