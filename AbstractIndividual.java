/**
*
*
* @author	Dana Hughes
* @version	1.0
* @since	2016-11-30
*/

import java.util.Random;

public abstract class AbstractIndividual
{
	private static Random rand = new Random();

	private Gene gene;

	public abstract double fitness();
	public abstract AbstractIndividual[] crossover(AbstractIndividual otherParent, double crossoverRate);
	public abstract void mutate(double mutationRate);

	public Gene getGene()
	{
		return gene;
	}
}
