/**
*
*
* @author	Dana Hughes
* @version	1.0
* @since	2017-01-02
*/

package placeholder.EvolutionaryProgramming.selection;

import placeholder.EvolutionaryProgramming.base.Individual;
import placeholder.EvolutionaryProgramming.base.NSGA2Individual;
import placeholder.EvolutionaryProgramming.base.Population;

import placeholder.EvolutionaryProgramming.base.ParetoFitness;

import java.util.Random;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Collections;

import java.lang.Math;
import java.lang.Integer;
import java.lang.Double;

public class NSGA2Selector extends Selector
{
	private static Random rand = new Random();

	// List of current population
	List<NSGA2Individual> currentPopulation;

	// List of each front
	List<List<NSGA2Individual>> fronts;


	public NSGA2Selector()
	{
		// Create a list to hold the fronts in
		currentPopulation = new ArrayList<NSGA2Individual>(); 
		fronts = new ArrayList<List<NSGA2Individual>>();
	}


	private void crowdingDistanceAssignment(int frontNumber)
	{
		List<NSGA2Individual> front = fronts.get(frontNumber);
		int frontSize = front.size();

		// Can't do anything with an empty front...
		if(frontSize == 0)
		{
			return;
		}

		// Distances should already be set to zero, but double check anyway
		for(int i=0; i<frontSize; i++)
		{
			front.get(i).setCrowdingDistance(0.0);
		}

		// How many objectives are there?
		int numObjectives = ((ParetoFitness) front.get(0).fitness()).fitnessSize();

		for(int i=0; i<numObjectives; i++)
		{
			// Sort by the current objective
			for(int j=0; j<frontSize; j++)
			{
				front.get(j).sortBy(i);
			}

			Collections.sort(front);

			// The two boundaries should be set to infinity to ensure selection
			front.get(0).setCrowdingDistance(Double.MAX_VALUE);
			front.get(frontSize-1).setCrowdingDistance(Double.MAX_VALUE);

			double f_min = ((ParetoFitness) front.get(0).fitness()).fitness()[i];
			double f_max = ((ParetoFitness) front.get(frontSize-1).fitness()).fitness()[i];

			// Update the crowding distances of all the others
			for(int j=1; j<frontSize - 1; j++)
			{
				double dist = front.get(j).getCrowdingDistance();
				double prev_val = ((ParetoFitness) front.get(j-1).fitness()).fitness()[i];
				double next_val = ((ParetoFitness) front.get(j+1).fitness()).fitness()[i];

				dist += (next_val - prev_val) / (f_max - f_min);

				front.get(j).setCrowdingDistance(dist);
			}
		}
	}


	/**
	* fastNondominatedSort(Population population)
	*
	* Get all the Pareto fronts
	*/
	public void fastNondominatedSort()
	{
		// Empty the list of fronts
		fronts.clear();

		List<NSGA2Individual> currentFront = new ArrayList<NSGA2Individual>();
		List<NSGA2Individual> nextFront;

		for(int i=0; i<currentPopulation.size(); i++)
		{
			NSGA2Individual p = currentPopulation.get(i);

			// Initialize the domination set, count and rank for this individual to empty and 0
			p.reset();

			for(int j=0; j<currentPopulation.size(); j++)
			{
				NSGA2Individual q = currentPopulation.get(j);

				if(p != q)
				{
					// Does p dominate q?
					if(p.dominates(q))
					{
						p.addToDominationSet(q);
					}
					// Does q dominate p?
					else if(q.dominates(p))
					{
						p.incrementDominationCount();
					}
				}
			}

			// Is this indvidual dominated?
			if(p.getDominationCount() == 0)
			{
				currentFront.add(p);
				p.setRank(0);
			}
		}

		// Now need to calculate the remaining fronts
		int frontNumber = 0;
		fronts.add(currentFront);

		// Now build new fronts until empty
		while(!currentFront.isEmpty())
		{
			// Start the next front
			nextFront = new ArrayList<NSGA2Individual>();

			for(NSGA2Individual p : currentFront)
			{
				for(int j = 0; j<p.getDominationSet().size(); j++)
				{
					NSGA2Individual q = (NSGA2Individual) p.getDominationSet().get(j);

					// Reduce the number of individuals that dominate q
					q.decrementDominationCount();

					// Does q belong to the next front?
					if(q.getDominationCount() == 0)
					{
						q.setRank(frontNumber + 1);
						nextFront.add(q);
					}
				}	
			}

			frontNumber++;
			currentFront = nextFront;
		}

	}


	public void process(Population population)
	{
		// Add the individuals in the population to the currentPopulation list
		// The population passed should be the children of the currentPopulation list
		for(int i=0; i<population.size(); i++)
		{
			currentPopulation.add((NSGA2Individual) population.getIndividual(i));
		}

		// Perform fast nondominated sorting on the combined population
		fastNondominatedSort();

		// Start a new list of individuals for the next population
		List<NSGA2Individual> nextPopulation = new ArrayList<NSGA2Individual>();

		int currentFrontNumber = 0;

		while(nextPopulation.size() + fronts.get(currentFrontNumber).size() <= population.size())
		{
			crowdingDistanceAssignment(currentFrontNumber);

			// Add the entire front to the next population
			for(NSGA2Individual p : fronts.get(currentFrontNumber))
			{
				nextPopulation.add(p);
			}

			currentFrontNumber++;
		}

		// The next front needs to be sorted to fill out the remaining population
		for(NSGA2Individual p: fronts.get(currentFrontNumber))
		{
			p.sortBy(NSGA2Individual.NSGA2);
		}

		Collections.sort(fronts.get(currentFrontNumber));
		Collections.reverse(fronts.get(currentFrontNumber));

		// Fill up the remaining population
		int i = 0;

		while(nextPopulation.size() < population.size())
		{
			nextPopulation.add(fronts.get(currentFrontNumber).get(i));
			i++;
		}

		// Phew, all done!  Set the currentPopulation to the newly constructed population
		currentPopulation = nextPopulation;

	}


	public Individual select()
	{
		// 
		return null;
	}

}
