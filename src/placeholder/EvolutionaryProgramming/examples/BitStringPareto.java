/**
* <h1>BitStringPareto</h1>
* A GA program which performs multi-objective optimization, where individuals are
* represented by a bitstring, and the objective is to jointly maximize the number
* of ones in the string, and maximize the number of adjacent complementary bits 
* (i.e., 01 or 10).
* <br>
* This example is from the following paper: <p>
* Horn, J. and Nafpliotis, N. and Goldberg, D.E., "A Niched Pareto Genetic Algorithm
* for Multiobjective Optimization," IEEE World Congress on Computational Intelligence,
* Proceedings of the First IEEE Conference on Evolutionary Computation, pp. 82--87,
* June 1994.
*
* @author	Dana Hughes
* @version	1.0
* @since	2016-12-12
*/

package placeholder.EvolutionaryProgramming.examples;

import placeholder.EvolutionaryProgramming.base.GeneticAlgorithm;
import placeholder.EvolutionaryProgramming.base.FitnessFunction;
import placeholder.EvolutionaryProgramming.base.AbstractIndividualFactory;

import placeholder.EvolutionaryProgramming.selection.Selector;
import placeholder.EvolutionaryProgramming.selection.EliteSelector;
import placeholder.EvolutionaryProgramming.selection.TournamentSelector;


public class BitStringPareto
{
	public static void main(String[] args)
	{
		System.out.println("Running GeneticAlgorithmDemo");
		System.out.println("  Creating Genetic Algorithm");

		AbstractIndividualFactory individualFactory = new BitStringParetoIndividualFactory(new BitStringMaximizerFitnessFunction());
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
