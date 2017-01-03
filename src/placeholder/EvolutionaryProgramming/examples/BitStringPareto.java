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

import placeholder.EvolutionaryProgramming.base.ParetoFitness;

import placeholder.EvolutionaryProgramming.base.Population;

import placeholder.EvolutionaryProgramming.selection.Selector;
import placeholder.EvolutionaryProgramming.selection.EliteSelector;
import placeholder.EvolutionaryProgramming.selection.NichedParetoTournamentSelector;
import placeholder.EvolutionaryProgramming.selection.NSGA2Selector;


public class BitStringPareto
{
	public static void printPopulationCount(Population pop)
	{
		System.out.print("Pareto Front - ");

		int[][] counts = new int[13][12];

		int total = 0;

		for(int i=0; i<13; i++)
		{
			for(int j=0; j<12; j++)
			{
				counts[i][j] = 0;
			}
		}

		for(int i=0; i<pop.size(); i++)
		{
			double[] fit = ((ParetoFitness) pop.getIndividual(i).fitness()).fitness();
			counts[(int) fit[0]][(int) fit[1]] += 1;
			total += 1;
		}

		System.out.println(total);

		for(int i=0; i<13; i++)
		{
			for(int j=0; j<12; j++)
			{
				System.out.format("%4d ", counts[i][j]);
			}
			System.out.println();
		}

		System.out.println();
	}

	public static void main(String[] args)
	{
		System.out.println("Running GeneticAlgorithmDemo");
		System.out.println("  Creating Genetic Algorithm");

		AbstractIndividualFactory individualFactory = new BitStringParetoIndividualFactory(new BitStringParetoFitnessFunction());
		Selector selector = new NichedParetoTournamentSelector(2, 20, 2.5);
//		Selector selector = new NSGA2Selector();

		GeneticAlgorithm ga = new GeneticAlgorithm(100, 0.9, 0.01, individualFactory, selector);

		for(int i=0; i<200; i++)
		{
//			((NSGA2Selector) selector).setup(ga.getPopulation());
			ga.step();

			if(i%5 == 0)
			{
				System.out.println("Step #" + i);
				printPopulationCount(ga.getPopulation());
			}
		}


	}
}
