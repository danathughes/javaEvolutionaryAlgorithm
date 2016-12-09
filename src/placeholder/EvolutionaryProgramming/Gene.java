/**
*
*
* @author	Dana Hughes
* @version	1.0
* @since	2016-11-30
*/

import java.util.Random;

public abstract class Gene
{
	public abstract void randomize();

	public abstract Gene[] crossover(Gene otherGene, double crossoverRate);
	public abstract void mutate(double mutationRate);

	public abstract String toString();
}
