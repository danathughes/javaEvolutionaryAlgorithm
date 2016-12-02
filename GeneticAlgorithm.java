/**
*
*
* @author	Dana Hughes
* @version	1.0
* @since	2016-11-30
*/

public class GeneticAlgorithm
{
    private int iterationNum = 0;

	private Population population;

	public GeneticAlgorithm(int populationSize, double crossoverRate, double mutationRate)
	{
		population = new Population(populationSize, crossoverRate, mutationRate);
		population.initialize();
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
		population = population.breed();
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
