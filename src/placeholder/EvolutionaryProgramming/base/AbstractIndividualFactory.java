/**
*
*
* @author	Dana Hughes
* @version	1.0
* @since	2016-12-07
*/

package placeholder.EvolutionaryProgramming.base;

import placeholder.EvolutionaryProgramming.genotypes.Genotype;

import java.util.Random;

public abstract class AbstractIndividualFactory
{
	protected FitnessFunction fitnessFunction;

	public AbstractIndividualFactory(FitnessFunction fitnessFunction)
	{
		this.fitnessFunction = fitnessFunction;
	}

	public abstract Individual createIndividual();
	public abstract Individual createIndividual(Genotype gene);
}
