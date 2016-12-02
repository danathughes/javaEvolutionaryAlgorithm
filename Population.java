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
	private byte[] population;
	private double crossoverRate, mutationRate;
	Random rand;

	public Population(int populationSize, Random rand, double crossoverRate, double mutationRate)
	{
		this.populationSize = populationSize;
        population = new byte[populationSize];

		this.rand = rand;
	}


	public void initializeIndividuals()
	{
		rand.nextBytes(population);
		byte mask = 31;

		for(int i=0; i<populationSize; i++)
		{
			population[i] = (byte)(population[i] & mask);
		}
	}


	public void printPopulation()
	{
		double total = 0.0;

		System.out.print("    Genotypes: ");
		for(int i=0; i<populationSize; i++)
		{ 
			System.out.print(population[i] + "   ");
			total += population[i];
		}
		System.out.println();

		System.out.println("    Average Fitness: " + total/populationSize);
	}


	public byte select()
	{
		double[] probs = new double[populationSize];
		double total = 0.0;

		for(int i=0; i<populationSize; i++)
		{
			probs[i] = (float) population[i];
			total += (float) population[i];
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


	public byte[] crossover(byte parent1, byte parent2)
	{
		int crossoverPoint = rand.nextInt(5);

        byte mask = 0;

        byte bit = 1;
        
        for(int i=0; i<crossoverPoint; i++)
        {
			mask |= bit;
			bit = (byte) (bit << 1);
        }

		byte child1 = (byte) ((parent1 & mask) + (parent2 & ~mask));
		byte child2 = (byte) ((parent1 & ~mask) + (parent2 & mask));

		byte[] children = new byte[2];
		children[0] = child1;
		children[1] = child2;

		return children;
	}



	public byte mutate(byte parent)
	{
		int mutationPoint = rand.nextInt(5);

		byte bit = (byte)(1 << mutationPoint);

		return (byte) (parent ^ bit);
	}


	public void setIndividual(int idx, byte individual)
	{
		population[idx] = individual;
	}


	public Population makeNewPopulation()
	{
		Population newPopulation = new Population(populationSize, rand, crossoverRate, mutationRate);

		for(int i=0; i<populationSize/2; i++)
		{
			byte parent1 = select();
			byte parent2 = select();
			byte[] children = crossover(parent1, parent2);

			byte child1 = children[0];
			byte child2 = children[1];

			if(rand.nextDouble() < mutationRate)
			{
				child1 = mutate(child1);
			}

			if(rand.nextDouble() < mutationRate)
			{
				child2 = mutate(child2);
			}

			newPopulation.setIndividual(2*i, child1);
			newPopulation.setIndividual(2*i+1, child2);
		}


		return newPopulation;
	}


}
