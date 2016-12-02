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

	public static Individual[] crossover(Individual parent1, Individual parent2, double crossoverRate)
	{
		Gene[] genes = Gene.crossover(parent1.getGene(), parent2.getGene(), crossoverRate);

		Individual child1 = new Individual(genes[0]);
		Individual child2 = new Individual(genes[1]);

		Individual[] children = new Individual[2];
		children[0] = child1;
		children[1] = child2;

		return children;
	}


	public Individual()
	{
		gene = new Gene();
	}


/*
	public Individual(byte val)
	{
		gene = new Gene(val);
	}
*/

	public Individual(Gene gene)
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


	public double getFitness()
	{
		return (double) gene.getValue();
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
