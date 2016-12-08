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

public class TournamentSelector extends Selector
{
	private static Random rand = new Random();

	private double selectionProbability;
	private int tournamentSize;

	public TournamentSelector(int tournamentSize, double selectionProbability)
	{
		this.tournamentSize = tournamentSize;
		this.selectionProbability = selectionProbability;
	}


	public Individual select(Population population)
	{
		// Create a tournament list of individuals from the population
		List<Individual> selectionList = new ArrayList<Individual>(tournamentSize);

		for(int i=0; i<tournamentSize; i++)
		{
			selectionList.add(population.getIndividual(rand.nextInt(population.size())));
		}

		// Sort the list so the most fit are first
		Collections.sort(selectionList);
		Collections.reverse(selectionList);


		// Randomly select the best one
		double prob = rand.nextDouble();
		double curProb = selectionProbability;

		int idx = 0;

		
		while (prob > curProb)
		{
			idx += 1;
			curProb = curProb + curProb * (1.0 - selectionProbability);
		}
	
		if (idx >= tournamentSize)
		{
			idx = tournamentSize - 1;
		}

		return selectionList.get(idx);
	}

}
