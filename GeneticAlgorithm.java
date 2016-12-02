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
    private int iterationNum = 0;

	private Population population;

	private Random rand;

	public GeneticAlgorithm(int populationSize, double crossoverRate, double mutationRate)
	{
		rand = new Random();

		population = new Population(populationSize, rand, crossoverRate, mutationRate);
		population.initializeIndividuals();
	}


	public void currentState()
	{
        System.out.println("Iteration #" + iterationNum);
		System.out.println("  Number of individuals: " + population.size());

		population.printPopulation();
	}


	public void step()
	{
		iterationNum++;
		population = population.makeNewPopulation();
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
