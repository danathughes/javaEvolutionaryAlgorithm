
package placeholder.EvolutionaryProgramming;

import placeholder.EvolutionaryProgramming.base.ParetoFitness;

public class ParetoTest
{
	public static void main(String[] args)
	{
		double v1[] = {5.2, 5.3};
		double v2[] = {5.1, 5.2};

		ParetoFitness fitness1 = new ParetoFitness(2, v1);
		ParetoFitness fitness2 = new ParetoFitness(2, v2);

		System.out.println(fitness1.isDominated(fitness2));
		System.out.println(fitness2.isDominated(fitness1));

		System.out.println(fitness1.compareTo(fitness2));
	}
}