/**
*
*
* @author	Dana Hughes
* @version	1.0
* @since	2016-12-12
*/

package placeholder.EvolutionaryProgramming.examples;

import placeholder.EvolutionaryProgramming.base.AbstractIndividualFactory;
import placeholder.EvolutionaryProgramming.base.FitnessFunction;
import placeholder.EvolutionaryProgramming.base.Individual;
import placeholder.EvolutionaryProgramming.genotypes.BitStringGenotype;
import placeholder.EvolutionaryProgramming.genotypes.Genotype;


import java.util.Random;

public class BitStringParetoIndividualFactory extends AbstractIndividualFactory
{
	public BitStringParetoIndividualFactory(FitnessFunction fitnessFunction)
	{
		super(fitnessFunction);
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
