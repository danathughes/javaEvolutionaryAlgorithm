/**
*
*
* @author	Dana Hughes
* @version	1.0
* @since	2016-11-30
*/

import java.util.Random;

public class Individual
{
	private static Random rand = new Random();

	private Gene gene;
	private FitnessFunction fitnessFunction;

	public Individual(FitnessFunction fitness)
	{
		gene = new BitStringGene(10);
		fitnessFunction = fitness;
	}


	public Individual(BitStringGene gene, FitnessFunction fitness)
	{
		this(fitness);
		this.gene = gene;
	}


	public void initialize()
	{
		gene.randomize();
	}

	
	public Gene getGene()
	{
		return (Gene) gene;
	}


	public double fitness()
	{
		return fitnessFunction.fitness(this);
	}


	public Individual[] crossover(Individual otherParent, double crossoverRate)
	{
		Gene[] genes = this.getGene().crossover(otherParent.getGene(), crossoverRate);

		Individual child1 = new Individual((BitStringGene) genes[0], fitnessFunction);
		Individual child2 = new Individual((BitStringGene) genes[1], fitnessFunction);

		Individual[] children = new Individual[2];

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



}
