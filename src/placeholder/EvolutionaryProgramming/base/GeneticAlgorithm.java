/**
* <h1>GeneticAlgorithm</h1>
* The GeneticAlgorithm class implements the high-level aspects of the genetic algorithm,
* allowing selection of the selector, individual and fitness function.
*
* @author	Dana Hughes
* @version	1.0
* @since	2016-11-30
*/

package placeholder.EvolutionaryProgramming.base;

import placeholder.EvolutionaryProgramming.selection.Selector;

import placeholder.EvolutionaryProgramming.base.AbstractIndividualFactory;
import placeholder.EvolutionaryProgramming.base.Population;


public class GeneticAlgorithm
{
    private int iterationNum = 0;

	private Population population;
	private AbstractIndividualFactory individualFactory;
	private Selector selector;

	double crossoverRate, mutationRate;

	public GeneticAlgorithm(int populationSize, double crossoverRate, double mutationRate,
							AbstractIndividualFactory individualFactory, Selector selector)
	{
		this.crossoverRate = crossoverRate;
		this.mutationRate = mutationRate;

		this.individualFactory = individualFactory;
		this.selector = selector;

		population = new Population(populationSize, individualFactory);
		population.initialize();
	}


	/**
	* This method performs a single evolutionary step on the population.
	*/
	public void step()
	{
		iterationNum++;
		selector.process(population);
		population = population.breed(selector, crossoverRate, mutationRate);
	}


	public Population getPopulation()
	{
		return population;
	}


	/**
	* This method gemerates a string summarizing the current state of the genetic
	* algorithm, including the following:
	* <p>
	* 1.  Iteration number
	* <p>
	* 2.  Population size
	* <p>
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
}
