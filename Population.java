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
	private int populationSize = 0;
	private Individual[] population;
	Random rand;

	public Population(int populationSize)
	{
		this.populationSize = populationSize;
        population = new Individual[populationSize];

		for(int i=0; i<populationSize; i++)
		{
			population[i] = new Individual();
		}

		rand = new Random();
	}


	public void initialize()
	{
		for(Individual individual : population)
		{
			individual.initialize(rand);
		}
	}


	public void printPopulation()
	{
		double total = 0.0;

		System.out.print("    Genotypes: ");
		for(Individual individual : population)
		{ 
			System.out.print(individual.getGene() + "   ");
			total += individual.getGene();
		}
		System.out.println();

		System.out.println("    Average Fitness: " + total/populationSize);
	}


	public Individual select()
	{
		double[] probs = new double[populationSize];
		double total = 0.0;

		for(int i=0; i<populationSize; i++)
		{
			probs[i] = (float) population[i].getGene();
			total += (float) population[i].getGene();
		}

		for(int i=0; i<populationSize; i++)
		{
			probs[i] /= total;
		}

		double p = rand.nextDouble();
		int idx = 0;

		while(p >= probs[idx])
		{
			p -= probs[idx];
			idx += 1;
		}
		
		return population[idx];
	}


	public int size()
	{
		return populationSize;
	}


	public void setIndividual(int idx, Individual individual)
	{
		population[idx] = individual;
	}


	public Population breed(double crossoverRate, double mutationRate)
	{
		Population newPopulation = new Population(populationSize);

		for(int i=0; i<populationSize/2; i++)
		{
			Individual parent1 = select();
			Individual parent2 = select();
			Individual[] children = Individual.crossover(parent1, parent2, rand, crossoverRate);

			if(rand.nextDouble() < mutationRate)
			{
				children[0].mutate(rand);
			}
			if(rand.nextDouble() < mutationRate)
			{
				children[1].mutate(rand);
			}

			newPopulation.setIndividual(2*i, children[0]);
			newPopulation.setIndividual(2*i+1, children[1]);
		}

		return newPopulation;
	}


}
