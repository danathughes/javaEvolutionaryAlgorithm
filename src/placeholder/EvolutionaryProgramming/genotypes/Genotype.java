/**
*
*
* @author	Dana Hughes
* @version	1.0
* @since	2016-11-30
*/

package placeholder.EvolutionaryProgramming.genotypes;

import java.util.Random;

public abstract class Genotype
{
	public abstract void randomize();

	public abstract Genotype[] crossover(Genotype otherGene, double crossoverRate);
	public abstract void mutate(double mutationRate);

	public abstract String toString();
}
