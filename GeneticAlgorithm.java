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
	private int populationSize=0;
	private byte[] population;

	Random rand;

	public GeneticAlgorithm(int populationSize)
	{
		rand = new Random();

		this.populationSize = populationSize;
        population = new byte[populationSize];

		initializeIndividuals();
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


	public 


	public void currentState()
	{
		System.out.println("Number of individuals: " + populationSize);
		for(int i=0; i<populationSize; i++)
		{
			System.out.println("  Genotype #" + i + ": " + population[i]);
		}
	}


	public String toString()
	{
		return "";
	}


	public static void main(String[] args)
	{
		System.out.println("Running GeneticAlgorithmDemo");
		System.out.println("  Creating Genetic Algorithm");
		GeneticAlgorithm ga = new GeneticAlgorithm(10);
		ga.currentState();
	}
}
