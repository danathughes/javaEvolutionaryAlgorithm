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

public class BitStringMaximizer
{
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
