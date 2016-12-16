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
import placeholder.EvolutionaryProgramming.base.ParetoFitness;
import placeholder.EvolutionaryProgramming.base.Individual;

import java.util.Random;

public class BitStringParetoFitnessFunction extends FitnessFunction
{
	public BitStringParetoFitnessFunction()
	{
	}


	public ParetoFitness fitness(Individual individual)
	{
		double fitnessValues[] = new double[2];

		fitnessValues[0] = 0.0;
		fitnessValues[1] = 0.0;

		BitStringGenotype gene = (BitStringGenotype) individual.getGene();

		for(int i=0; i<gene.getLength(); i++)
		{
			if(gene.getBit(i) == 1)
			{
				fitnessValues[0] += 1;
			}
		}

		for(int i=0; i<gene.getLength()-1; i++)
		{
			if(gene.getBit(i) != gene.getBit(i+1))
			{
				fitnessValues[1] += 1;
			}
		}

		return new ParetoFitness(2, fitnessValues);
	}
}
