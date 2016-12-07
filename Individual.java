/**
*
*
* @author	Dana Hughes
* @version	1.0
* @since	2016-11-30
*/

import java.util.Random;

public class Individual extends AbstractIndividual
{
	private static Random rand = new Random();

	private Gene gene;

	public Individual()
	{
		gene = new BitStringGene(10);
	}


	public Individual(BitStringGene gene)
	{
		this.gene = gene;
	}


	public void initialize()
	{
		gene.randomize();
	}


	public Gene getGene()
	{
		return gene;
	}


	public double fitness()
	{
		double value=0.0;

		for(int i=0; i<((BitStringGene) gene).getLength(); i++)
		{
			value = 2.0*value + ((BitStringGene) gene).getBit(i);
		}
		return value;
	}


	public AbstractIndividual[] crossover(AbstractIndividual otherParent, double crossoverRate)
	{
		Gene[] genes = this.getGene().crossover(otherParent.getGene(), crossoverRate);

		AbstractIndividual child1 = new Individual((BitStringGene) genes[0]);
		AbstractIndividual child2 = new Individual((BitStringGene) genes[1]);

		AbstractIndividual[] children = new Individual[2];
		children[0] = child1;
		children[1] = child2;

		return children;
	}


	public void mutate(double mutationRate)
	{
		if(rand.nextDouble() < mutationRate)
		{
			gene.mutate(mutationRate);
		}
	}


	public String toString()
	{
		return "";
	}
}
