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
	private IndividualFactory individualFactory;
	private Selector selector;

	double crossoverRate, mutationRate;

	public GeneticAlgorithm(int populationSize, double crossoverRate, double mutationRate)
	{
		this.crossoverRate = crossoverRate;
		this.mutationRate = mutationRate;

		FitnessFunction fitnessFunction = new FitnessFunction();
		individualFactory = new IndividualFactory(fitnessFunction);
		selector = new TournamentSelector(3, 0.6);

		population = new Population(populationSize, individualFactory);
		population.initialize();
	}


	public void step()
	{
		iterationNum++;
		population = population.breed(selector, crossoverRate, mutationRate);
	}


	public String toString()
	{
		StringBuilder builder = new StringBuilder();

		builder.append("Iteration #" + iterationNum + "\n");
		builder.append("  Number of individuals: " + population.size() + "\n");

		builder.append(population.toString());

		return builder.toString();
	}

	public static void main(String[] args)
	{
		System.out.println("Running GeneticAlgorithmDemo");
		System.out.println("  Creating Genetic Algorithm");
		GeneticAlgorithm ga = new GeneticAlgorithm(10, 0.25, 0.05);
		System.out.println(ga.toString());
		for(int i=0; i<10; i++)
		{
			ga.step();
			System.out.println(ga.toString());
		}
	}
}
