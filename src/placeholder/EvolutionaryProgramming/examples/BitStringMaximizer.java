/**
* <h1>BitStringMaximizer</h1>
* A simple example GA program which maximizes the value of a 10-bit string.
*
* @author	Dana Hughes
* @version	1.0
* @since	2016-12-09
*/

package placeholder.EvolutionaryProgramming.examples;

import placeholder.EvolutionaryProgramming.base.GeneticAlgorithm;

import placeholder.EvolutionaryProgramming.base.FitnessFunction;

import placeholder.EvolutionaryProgramming.selection.Selector;
import placeholder.EvolutionaryProgramming.selection.EliteSelector;
import placeholder.EvolutionaryProgramming.selection.TournamentSelector;

import placeholder.EvolutionaryProgramming.IndividualFactory;

public class BitStringMaximizer
{
	public static void main(String[] args)
	{
		System.out.println("Running GeneticAlgorithmDemo");
		System.out.println("  Creating Genetic Algorithm");

		IndividualFactory individualFactory = new IndividualFactory(new BitStringMaximizerFitnessFunction());
		Selector selector = new EliteSelector(new TournamentSelector(3, 0.6), 2);

		GeneticAlgorithm ga = new GeneticAlgorithm(10, 0.25, 0.05, individualFactory, selector);
		System.out.println(ga.toString());
		for(int i=0; i<50; i++)
		{
			ga.step();
			System.out.println(ga.toString());
		}
	}
}
