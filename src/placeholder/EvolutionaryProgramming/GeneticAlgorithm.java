/**
* <h1>GeneticAlgorithm</h1>
* The GeneticAlgorithm class implements the high-level aspects of the genetic algorithm,
* allowing selection of the selector, individual and fitness function.
*
* @author	Dana Hughes
* @version	1.0
* @since	2016-11-30
*/

package placeholder.EvolutionaryProgramming;

import placeholder.EvolutionaryProgramming.selection.Selector;
import placeholder.EvolutionaryProgramming.selection.EliteSelector;
import placeholder.EvolutionaryProgramming.selection.TournamentSelector;

import placeholder.EvolutionaryProgramming.base.FitnessFunction;
import placeholder.EvolutionaryProgramming.base.Individual;
import placeholder.EvolutionaryProgramming.base.Population;

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
		selector = new EliteSelector(new TournamentSelector(3, 0.6), 2);
//		selector = new TournamentSelector(3, 0.6);

		population = new Population(populationSize, individualFactory);
		population.initialize();
	}


	/**
	* This method performs a single evolutionary step on the population.
	*/
	public void step()
	{
		iterationNum++;
		population = population.breed(selector, crossoverRate, mutationRate);
	}


	/**
	* This method gemerates a string summarizing the current state of the genetic
	* algorithm, including the following:
	* 1.  Iteration number
	* 2.  Population size
	* 3.  Summary of the population
	* 
	* @return	String	The summary of the current state of the GeneticAlgorithm.
	*/
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
		GeneticAlgorithm ga = new GeneticAlgorithm(10, 0.25, 0.25);
		System.out.println(ga.toString());
		for(int i=0; i<50; i++)
		{
			ga.step();
			System.out.println(ga.toString());
		}
	}
}
