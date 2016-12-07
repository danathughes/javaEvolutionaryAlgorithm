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
	private Selector selector;

	public Population(int populationSize)
	{
		this.populationSize = populationSize;
        population = new Individual[populationSize];

		for(int i=0; i<populationSize; i++)
		{
			population[i] = new Individual();
		}

		selector = new Selector();
	}


	public void initialize()
	{
		for(Individual individual : population)
		{
			individual.initialize();
		}
	}


	public void printPopulation()
	{
		double total = 0.0;

		System.out.print("    Genotypes: ");
		for(Individual individual : population)
		{ 
			System.out.print(individual.fitness() + "   ");
			total += individual.fitness();
		}
		System.out.println();

		System.out.println("    Average Fitness: " + total/populationSize);
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


	public Population breed(double crossoverRate, double mutationRate)
	{
		Population newPopulation = new Population(populationSize);

		for(int i=0; i<populationSize/2; i++)
		{
			Individual parent1 = selector.select(this);
			Individual parent2 = selector.select(this);
			AbstractIndividual[] children = parent1.crossover(parent2, crossoverRate);

			children[0].mutate(mutationRate);
			children[1].mutate(mutationRate);

			newPopulation.setIndividual(2*i, (Individual) children[0]);
			newPopulation.setIndividual(2*i+1, (Individual) children[1]);
		}

		return newPopulation;
	}


}
