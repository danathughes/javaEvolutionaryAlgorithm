/**
*
*
* @author	Dana Hughes
* @version	1.0
* @since	2016-11-30
*/

import java.util.Random;

public class Individual
{
	private static Random rand = new Random();

	private Gene value;

	public static Individual[] crossover(Individual parent1, Individual parent2, double crossoverRate)
	{
		byte[] genes = Gene.crossover(parent1.getGene(), parent2.getGene(), crossoverRate);

		Individual child1 = new Individual(genes[0]);
		Individual child2 = new Individual(genes[1]);

		Individual[] children = new Individual[2];
		children[0] = child1;
		children[1] = child2;

		return children;
	}


	public Individual()
	{
		value = new Gene((byte) 0);
	}


	public Individual(byte val)
	{
		value = new Gene(val);
	}


	public void initialize()
	{
		value.setValue((byte) (rand.nextInt() & 31));
	}


	public byte getGene()
	{
		return value.getValue();
	}


	public void mutate(double mutationRate)
	{
		if(rand.nextDouble() < mutationRate)
		{
			value.mutate(mutationRate);
		}
	}


	public String toString()
	{
		return "";
	}
}
