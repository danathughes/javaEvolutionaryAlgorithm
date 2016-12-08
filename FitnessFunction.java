/**
*
*
* @author	Dana Hughes
* @version	1.0
* @since	2016-11-30
*/

import java.util.Random;

public class FitnessFunction
{
	public FitnessFunction()
	{
	}


	public double fitness(Individual individual)
	{
		double value = 0.0;

		BitStringGene gene = (BitStringGene) individual.getGene();

		for(int i=0; i<gene.getLength(); i++)
		{
			value = 2.0*value + gene.getBit(i);
		}

		return value;		
	}
}
