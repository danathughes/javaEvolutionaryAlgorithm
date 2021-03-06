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

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

public class StochasticUniversalSelector extends Selector
{
	private static Random rand = new Random();

	private int currentIndividualNumber = 0;
	private int numIndividualsSelected;
	List<Individual> populationList;
	private double totalFitness = 0.0;
	private double pointerDistance = 0.0;
	private double startPoint = 0.0;

	public StochasticUniversalSelector(int numIndividualsSelected)
	{
		this.numIndividualsSelected = numIndividualsSelected;
		populationList = new ArrayList<Individual>(numIndividualsSelected);
	}


	public void process(Population population)
	{

	}


	public Individual select()
	{
		// Need to repopulate the populationList if the individual number is reset to 0
		if(currentIndividualNumber == 0)
		{
			populationList.clear();
			System.out.print("Rebuilding list...");
			for(int i=0; i<population.size(); i++)
			{
				populationList.add(population.getIndividual(i));
				totalFitness += population.getIndividual(i).fitness().value();
			}

			Collections.sort(populationList);
			Collections.reverse(populationList);

			System.out.println(populationList.size() + " elements, total fitness = " + totalFitness);

			pointerDistance = totalFitness / (numIndividualsSelected + 1);
			startPoint = pointerDistance * rand.nextDouble();
		}

		double selectionFitness = startPoint + currentIndividualNumber * pointerDistance;

		int idx = -1;

		do 
		{
			idx += 1;
			selectionFitness -= populationList.get(idx).fitness().value();
		}
		while (selectionFitness >= 0.0);
			
		currentIndividualNumber += 1;
		if(currentIndividualNumber == numIndividualsSelected)
		{
			currentIndividualNumber = 0;
		}

		return populationList.get(idx);
	}

}
