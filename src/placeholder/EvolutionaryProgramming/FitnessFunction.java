/**
*
*
* @author	Dana Hughes
* @version	1.0
* @since	2016-12-07
*/

import java.util.Random;

import java.lang.Comparable;

public class FitnessFunction
{
	public FitnessFunction()
	{
	}


	public Fitness fitness(Individual individual)
	{
		double value = 0.0;

		BitStringGene gene = (BitStringGene) individual.getGene();

		for(int i=0; i<gene.getLength(); i++)
		{
			value = 2.0*value + gene.getBit(i);
		}

		return new Fitness(value);
	}
}
