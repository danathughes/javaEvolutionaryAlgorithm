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
	private byte value = 0;


	public static Individual[] crossover(Individual parent1, Individual parent2, Random rand)
	{
		int crossoverPoint = rand.nextInt(5);

        byte mask = 0;

        byte bit = 1;
        
        for(int i=0; i<crossoverPoint; i++)
        {
			mask |= bit;
			bit = (byte) (bit << 1);
        }

		byte gene1 = (byte) ((parent1.getGene() & mask) + (parent2.getGene() & ~mask));
		byte gene2 = (byte) ((parent1.getGene() & ~mask) + (parent2.getGene() & mask));

		Individual child1 = new Individual(gene1);
		Individual child2 = new Individual(gene2);

		Individual[] children = new Individual[2];
		children[0] = child1;
		children[1] = child2;

		return children;
	}




	public Individual()
	{
		value = 0;
	}


	public Individual(byte val)
	{
		value = val;
	}


	public void initialize(Random rand)
	{
		value = (byte) (rand.nextInt() & 31);
	}


	public byte getGene()
	{
		return value;
	}


	public void mutate(Random rand)
	{
		int mutationPoint = rand.nextInt(5);

		byte bit = (byte)(1 << mutationPoint);

		value ^= bit;
	}


	public String toString()
	{
		return "";
	}
}
