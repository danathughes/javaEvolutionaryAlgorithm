/**
*
*
* @author	Dana Hughes
* @version	1.0
* @since	2016-12-07
*/

package placeholder.EvolutionaryProgramming.examples;

import placeholder.EvolutionaryProgramming.BitStringGenotype;
import placeholder.EvolutionaryProgramming.base.FitnessFunction;
import placeholder.EvolutionaryProgramming.base.Fitness;
import placeholder.EvolutionaryProgramming.base.Individual;

import java.util.Random;

public class BitStringMaximizerFitnessFunction extends FitnessFunction
{
	public BitStringMaximizerFitnessFunction()
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
