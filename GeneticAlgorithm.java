/**
*
*
* @author	Dana Hughes
* @version	1.0
* @since	2016-11-30
*/

import java.util.Random;

public class GeneticAlgorithm
{
	private int populationSize = 0;
    private int iterationNum = 0;
	private byte[] population;

	private double crossoverRate;
	private double mutationRate;

	Random rand;

	public GeneticAlgorithm(int populationSize, double crossoverRate, double mutationRate)
	{
		rand = new Random();

		this.populationSize = populationSize;
        population = new byte[populationSize];

		initializeIndividuals();

		this.crossoverRate = crossoverRate;
		this.mutationRate = mutationRate;
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


	public void currentState()
	{
        System.out.println("Iteration #" + iterationNum);
		System.out.println("  Number of individuals: " + populationSize);

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


	public byte[] makeNewPopulation()
	{
		byte[] newPopulation = new byte[populationSize];

		for(int i=0; i<populationSize/2; i++)
		{
			byte parent1 = select();
			byte parent2 = select();
			byte[] children = crossover(parent1, parent2);

			byte child1 = children[0];
			byte child2 = children[1];

			newPopulation[2*i] = child1;
			newPopulation[2*i+1] = child2;
		}

		for(int i=0; i<populationSize; i++)
		{
			if(rand.nextDouble() < mutationRate)
			{
				population[i] = mutate(population[i]);
			}
		}

		return newPopulation;
	}


	public byte mutate(byte parent)
	{
		int mutationPoint = rand.nextInt(5);

		byte bit = (byte)(1 << mutationPoint);

		return (byte) (parent ^ bit);
	}


	public void step()
	{
		iterationNum++;
		population = makeNewPopulation();
	}


	public String toString()
	{
		return "";
	}


	public static void main(String[] args)
	{
		System.out.println("Running GeneticAlgorithmDemo");
		System.out.println("  Creating Genetic Algorithm");
		GeneticAlgorithm ga = new GeneticAlgorithm(10, 0.25, 0.05);
		ga.currentState();
		for(int i=0; i<10; i++)
		{
			ga.step();
			ga.currentState();
		}
	}
}
