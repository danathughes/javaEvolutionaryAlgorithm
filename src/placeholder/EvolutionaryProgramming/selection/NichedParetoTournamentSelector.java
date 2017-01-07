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

import placeholder.EvolutionaryProgramming.base.ParetoFitness;

import java.util.Random;

import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

import java.lang.Math;

public class NichedParetoTournamentSelector extends Selector
{
	private static Random rand = new Random();

	private int comparisonSetSize;
	private int tournamentSize;
	private double nicheRadius;

	public NichedParetoTournamentSelector(int tournamentSize, int comparisonSetSize, double nicheRadius)
	{
		this.tournamentSize = tournamentSize;
		this.comparisonSetSize = comparisonSetSize;
		this.nicheRadius = nicheRadius;
	}


	public void process(Population population)
	{

	}

	public Individual select()
	{
		Individual[] tournamentPopulation = new Individual[tournamentSize];
		Individual[] comparisonSet = new Individual[comparisonSetSize];

		int[] dominationCount = new int[tournamentSize];

		for(int i=0; i<tournamentSize; i++)
		{
			tournamentPopulation[i] = population.getIndividual(rand.nextInt(population.size()));
			dominationCount[i] = 0;
		}

		for(int i=0; i<comparisonSetSize; i++)
		{
			comparisonSet[i] = population.getIndividual(rand.nextInt(population.size()));
		}

		// Check how many times each individual dominates individuals in the comparison set
		for(int i=0; i<tournamentSize; i++)
		{
			for(int j=0; j<comparisonSetSize; j++)
			{
				if(tournamentPopulation[i].compareTo(comparisonSet[i]) > 0)
				{
					dominationCount[i] += 1;
				}
			}
		}

		// Is there a dominant individual?
		int bestDominationCount = -1;

		for(int i=0; i<tournamentSize; i++)
		{
			if(dominationCount[i] > bestDominationCount)
			{
				bestDominationCount = dominationCount[i];
			}
		}

		int numDominant = 0;

		for(int i=0; i<tournamentSize; i++)
		{
			if(dominationCount[i] == bestDominationCount)
			{
				numDominant += 1;
			}
		}

		// Get only the dominant set
		Individual[] dominantSet = new Individual[numDominant];
		int curIdx = 0;

		for(int i=0; i<tournamentSize; i++)
		{
			if(dominationCount[i] == bestDominationCount)
			{
				dominantSet[curIdx] = tournamentPopulation[i];
				curIdx++;
			}
		}

		Individual whichItem;

		// Is there only one individual?  i.e., it is dominant?
		if(numDominant == 1)
		{
			whichItem = dominantSet[0];
		}
		// Otherwise, we need to select based on shared fitness
		// Have we seen any fitnesses before?
		else
		{
			int minIdx;
			double minNicheCount;
			double[] nicheCounts = new double[numDominant];

			for(int i=0; i<numDominant; i++)
			{
				ParetoFitness f1 = (ParetoFitness) dominantSet[i].fitness();

				for(int j=0; j<population.size(); j++)
				{
					ParetoFitness f2 = (ParetoFitness) population.getIndividual(j).fitness();

					nicheCounts[i] += sharingFunction(distance(f1, f2));
				}
			}

			// Pick the item with the smallest niche count (i.e., largest fitness distance)
			minIdx = 0;
			minNicheCount = nicheCounts[0];

			for(int i=0; i<numDominant; i++)
			{
				if(nicheCounts[i] < minNicheCount)
				{
					minIdx = i;
					minNicheCount = nicheCounts[i];
				}
			}

			whichItem = dominantSet[minIdx];
		}

		return whichItem;
	}


	private double sharingFunction(double dist)
	{
		double distance = 0.0;

		if(dist < nicheRadius)
		{
			distance = 1.0 - dist / nicheRadius;
		}

		return distance;
	}


	private double distance(ParetoFitness fitness1, ParetoFitness fitness2)
	{
		double distance = 0.0;

		for(int i=0; i<fitness1.fitnessSize(); i++)
		{
			double tmp = fitness1.fitness()[i] - fitness2.fitness()[i];
			distance += tmp*tmp;
		}

		return Math.sqrt(distance);
	}

}
