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
	private double crossoverRate, mutationRate;
	Random rand;

	public Population(int populationSize, double crossoverRate, double mutationRate)
	{
		this.populationSize = populationSize;
        population = new Individual[populationSize];

		for(int i=0; i<populationSize; i++)
		{
			population[i] = new Individual();
		}

		rand = new Random();
	}


	public void initializeIndividuals()
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


	public Individual[] crossover(Individual parent1, Individual parent2)
	{
		int crossoverPoint = rand.nextInt(5);

        byte mask = 0;

        byte bit = 1;
        
        for(int i=0; i<crossoverPoint; i++)
        {
			mask |= bit;
			bit = (byte) (bit << 1);
        }

		byte gene1 = (byte) ((parent1.getGene() & mask) + (parent2.getGene() & ~mask));
		byte gene2 = (byte) ((parent1.getGene() & ~mask) + (parent2.getGene() & mask));

		Individual child1 = new Individual(gene1);
		Individual child2 = new Individual(gene2);

		Individual[] children = new Individual[2];
		children[0] = child1;
		children[1] = child2;

		return children;
	}


	public void setIndividual(int idx, Individual individual)
	{
		population[idx] = individual;
	}


	public Population breed()
	{
		Population newPopulation = new Population(populationSize, crossoverRate, mutationRate);

		for(int i=0; i<populationSize/2; i++)
		{
			Individual parent1 = select();
			Individual parent2 = select();
			Individual[] children = crossover(parent1, parent2);

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
