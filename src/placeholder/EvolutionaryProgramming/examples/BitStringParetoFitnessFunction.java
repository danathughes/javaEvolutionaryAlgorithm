/**
*
*
* @author	Dana Hughes
* @version	1.0
* @since	2016-12-12
*/

package placeholder.EvolutionaryProgramming.examples;

import placeholder.EvolutionaryProgramming.genotypes.BitStringGenotype;
import placeholder.EvolutionaryProgramming.base.FitnessFunction;
import placeholder.EvolutionaryProgramming.base.Fitness;
import placeholder.EvolutionaryProgramming.base.Individual;

import java.util.Random;

public class BitStringParetoFitnessFunction extends FitnessFunction
{
	public BitStringParetoFitnessFunction()
	{
	}


	public Fitness fitness(Individual individual)
	{
		double value = 0.0;

		BitStringGenotype gene = (BitStringGenotype) individual.getGene();

		for(int i=0; i<gene.getLength(); i++)
		{
			value = 2.0*value + gene.getBit(i);
		}

		return new Fitness(value);
	}
}
