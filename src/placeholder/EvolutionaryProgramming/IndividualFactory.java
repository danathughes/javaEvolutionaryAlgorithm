/**
*
*
* @author	Dana Hughes
* @version	1.0
* @since	2016-12-07
*/

package placeholder.EvolutionaryProgramming;

import placeholder.EvolutionaryProgramming.base.Individual;
import placeholder.EvolutionaryProgramming.base.Genotype;
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
		Individual individual = new Individual(new BitStringGenotype(10), fitnessFunction, this);
		individual.initialize();

		return individual;
	}

	public Individual createIndividual(Genotype gene)
	{
		Individual individual = new Individual(gene, fitnessFunction, this);

		return individual;
	}
}
