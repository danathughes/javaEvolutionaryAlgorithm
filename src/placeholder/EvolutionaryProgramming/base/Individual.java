/**
*
*
* @author	Dana Hughes
* @version	1.0
* @since	2016-11-30
*/

package placeholder.EvolutionaryProgramming.base;

import placeholder.EvolutionaryProgramming.base.AbstractIndividualFactory;
import placeholder.EvolutionaryProgramming.genotypes.Genotype;

import java.util.Random;

import java.lang.Comparable;

public class Individual implements Comparable<Individual>
{
	private static Random rand = new Random();

	private Genotype gene;
	private FitnessFunction fitnessFunction;
	private AbstractIndividualFactory individualFactory;


	public Individual(Genotype gene, FitnessFunction fitness, AbstractIndividualFactory factory)
	{
		fitnessFunction = fitness;
		individualFactory = factory;
		this.gene = gene;
	}


	public void initialize()
	{
		gene.randomize();
	}

	
	public Genotype getGene()
	{
		return (Genotype) gene;
	}


	public Fitness fitness()
	{
		return fitnessFunction.fitness(this);
	}


	public Individual[] crossover(Individual otherParent, double crossoverRate)
	{
		Genotype[] genes = this.getGene().crossover(otherParent.getGene(), crossoverRate);

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
