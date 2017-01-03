/**
*
*
* @author	Dana Hughes
* @version	1.0
* @since	2017-01-02
*/

package placeholder.EvolutionaryProgramming.selection;

import placeholder.EvolutionaryProgramming.base.Individual;
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

public class NSGA2Selector extends Selector
{
	private static Random rand = new Random();

	// List of individuals that each individual dominates
	Map<Individual, List<Individual>> dominationSets;
	// Count of individuals which dominate each individual
	Map<Individual, Integer> dominationCounts;
	// Rank of each individual
	Map<Individual, Integer> ranks;
	// List of each front
	List<List<Individual>> fronts;


	public NSGA2Selector()
	{
		// Create new maps to hold everything in
		dominationSets = new HashMap<Individual, List<Individual>>();
		dominationCounts = new HashMap<Individual, Integer>();
		ranks = new HashMap<Individual, Integer>();

		fronts = new ArrayList<List<Individual>>();
	}


	/**
	* setup(Population population)
	*
	* Initialize everything by determining the Pareto fronts and various sets, etc.
	*/
	public void setup(Population population)
	{
		// Empty the maps
		dominationSets.clear();
		dominationCounts.clear();
		ranks.clear();
		fronts.clear();

		List<Individual> currentFront = new ArrayList<Individual>();
		List<Individual> nextFront;

		for(int i=0; i<population.size(); i++)
		{
			Individual p = population.getIndividual(i);

			// Initialize the domination set and count for this individual to empty and 0
			dominationSets.put(p, new ArrayList<Individual>());
			dominationCounts.put(p, 0);

			for(int j=0; j<population.size(); j++)
			{
				Individual q = population.getIndividual(j);

				if(p != q)
				{
					// Does p dominate q?
					if(p.compareTo(q) > 0)
					{
						dominationSets.get(p).add(q);
					}
					// Does q dominate p?
					else if(p.compareTo(q) < 0)
					{
						dominationCounts.put(p, dominationCounts.get(p) + 1);
					}
				}
			}

			// Is this indvidual dominated?
			if(dominationCounts.get(p) == 0)
			{
				currentFront.add(p);
				ranks.put(p, 0);
			}
		}

		// Now need to calculate the remaining fronts
		int frontNumber = 0;
		fronts.add(currentFront);

		// Now build new fronts until empty
		while(!currentFront.isEmpty())
		{
			// Start the next front
			nextFront = new ArrayList<Individual>();

			for(Individual p : currentFront)
			{
				for(Individual q : dominationSets.get(p))
				{
					// Reduce the number of individuals that dominate q
					dominationCounts.put(q, dominationCounts.get(p) - 1);

					// Does q belong to the next front?
					if(dominationCounts.get(q) == 0)
					{
						ranks.put(q, frontNumber + 1);
						nextFront.add(q);
					}
				}	
			}

			frontNumber++;
			currentFront = nextFront;
		}

	}


	public Individual select(Population population)
	{
		return population.getIndividual(0);
	}

}
