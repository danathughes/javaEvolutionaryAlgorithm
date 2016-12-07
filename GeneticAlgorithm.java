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

	double crossoverRate, mutationRate;

	public GeneticAlgorithm(int populationSize, double crossoverRate, double mutationRate)
	{
		this.crossoverRate = crossoverRate;
		this.mutationRate = mutationRate;

		population = new Population(populationSize);
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
		population = population.breed(crossoverRate, mutationRate);
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

		BitStringGene bs1 = new BitStringGene(10);
		BitStringGene bs2 = new BitStringGene(10);

		bs1.randomize();
		bs2.randomize();

		System.out.println(bs1.toString() + '\t' + bs2.toString());

		BitStringGene[] bs = BitStringGene.crossover(bs1, bs2, 0.5);

		System.out.println(bs[0].toString() + '\t' + bs[1].toString());

	}
}
