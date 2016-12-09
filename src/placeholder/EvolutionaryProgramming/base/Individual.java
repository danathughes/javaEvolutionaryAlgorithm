/**
*
*
* @author	Dana Hughes
* @version	1.0
* @since	2016-11-30
*/

package placeholder.EvolutionaryProgramming.base;

import placeholder.EvolutionaryProgramming.IndividualFactory;
import placeholder.EvolutionaryProgramming.BitStringGene;

import java.util.Random;

import java.lang.Comparable;

public class Individual implements Comparable<Individual>
{
	private static Random rand = new Random();

	private Gene gene;
	private FitnessFunction fitnessFunction;
	private IndividualFactory individualFactory;

	public Individual(FitnessFunction fitness, IndividualFactory factory)
	{
		gene = new BitStringGene(10);
		fitnessFunction = fitness;
		individualFactory = factory;
	}


	public Individual(Gene gene, FitnessFunction fitness, IndividualFactory factory)
	{
		this(fitness, factory);
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


	public Fitness fitness()
	{
		return fitnessFunction.fitness(this);
	}


	public Individual[] crossover(Individual otherParent, double crossoverRate)
	{
		Gene[] genes = this.getGene().crossover(otherParent.getGene(), crossoverRate);

		Individual child1 = individualFactory.createIndividual(genes[0]);
		Individual child2 = individualFactory.createIndividual(genes[1]);

		Individual[] children = new Individual[2];

		children[0] = child1;
		children[1] = child2;

		return children;
	}

	
	public String toString()
	{
		return gene.toString();
	}


	public void mutate(double mutationRate)
	{
		if(rand.nextDouble() < mutationRate)
		{
			gene.mutate(mutationRate);
		}
	}


	public int compareTo(Individual other)
	{
		return this.fitness().compareTo(other.fitness());
	}
}
