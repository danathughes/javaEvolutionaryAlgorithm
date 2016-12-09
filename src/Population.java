/**
*
*
* @author	Dana Hughes
* @version	1.0
* @since	2016-11-30
*/

import java.util.Random;

public class Population
{
	private static Random rand = new Random();

	private int populationSize = 0;
	private Individual[] population;

	private IndividualFactory individualFactory;

	public Population(int populationSize, IndividualFactory individualFactory)
	{
		this.populationSize = populationSize;
		this.individualFactory = individualFactory;

        population = new Individual[populationSize];
	
		for(int i=0; i<populationSize; i++)
		{
			population[i] = individualFactory.createIndividual();
		}
	}


	public void initialize()
	{
		for(Individual individual : population)
		{
			individual.initialize();
		}
	}


	public String toString()
	{
		double total = 0.0;
		double best = 0.0;

		StringBuilder builder = new StringBuilder();

		builder.append("    Genotypes: ");
		for(Individual individual : population)
		{
			builder.append(individual.toString() + "  ");
			total += individual.fitness();

			if(individual.fitness() > best)
			{
				best = individual.fitness();
			}
		}

		builder.append("\n    Average Fitness: " + total/populationSize + "   Best Fitness: " + best);

		return builder.toString();
	}


	public int size()
	{
		return populationSize;
	}


	public void setIndividual(int idx, Individual individual)
	{
		population[idx] = individual;
	}


	public Individual getIndividual(int idx)
	{
		return population[idx];
	}


	public Population breed(Selector selector, double crossoverRate, double mutationRate)
	{
		Population newPopulation = new Population(populationSize, individualFactory);

		for(int i=0; i<populationSize/2; i++)
		{
			Individual parent1 = selector.select(this);
			Individual parent2 = selector.select(this);

			Individual[] children = parent1.crossover(parent2, crossoverRate);

			children[0].mutate(mutationRate);
			children[1].mutate(mutationRate);

			newPopulation.setIndividual(2*i, children[0]);
			newPopulation.setIndividual(2*i+1, children[1]);
		}

		return newPopulation;
	}


}
