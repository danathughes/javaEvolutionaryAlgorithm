/**
*
*
* @author	Dana Hughes
* @version	1.0
* @since	2016-12-07
*/

package placeholder.EvolutionaryProgramming;

import placeholder.EvolutionaryProgramming.base.Individual;
import placeholder.EvolutionaryProgramming.base.Gene;
import placeholder.EvolutionaryProgramming.base.FitnessFunction;

import placeholder.EvolutionaryProgramming.base.*;


import java.util.Random;

public class IndividualFactory
{
	private FitnessFunction fitnessFunction;

	public IndividualFactory(FitnessFunction fitnessFunction)
	{
		this.fitnessFunction = fitnessFunction;
	}

	public Individual createIndividual()
	{
		Individual individual = new Individual(fitnessFunction, this);
		individual.initialize();

		return individual;
	}

	public Individual createIndividual(Gene gene)
	{
		Individual individual = new Individual((BitStringGene) gene, fitnessFunction, this);

		return individual;
	}
}
