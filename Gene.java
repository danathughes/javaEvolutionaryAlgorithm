/**
*
*
* @author	Dana Hughes
* @version	1.0
* @since	2016-11-30
*/

import java.util.Random;

public class Gene
{

	private static Random rand = new Random();

	public static byte[] crossover(byte parentGene1, byte parentGene2, double crossoverRate)
	{
		int crossoverPoint = rand.nextInt(5);

        byte mask = 0;
        byte bit = 1;
        
        for(int i=0; i<crossoverPoint; i++)
        {
			mask |= bit;
			bit = (byte) (bit << 1);
        }

		byte gene1 = (byte) ((parentGene1 & mask) + (parentGene1 & ~mask));
		byte gene2 = (byte) ((parentGene2 & ~mask) + (parentGene2 & mask));

		byte[] genes = new byte[2];
		genes[0] = gene1;
		genes[1] = gene2;

		return genes;
	}


	private byte value = 0;
	
	public Gene(byte value)
	{
		this.value = value;
	}


	public void setValue(byte val)
	{
		value = val;
	}


	public byte getValue()
	{
		return value;
	}

	public void mutate(double mutationRate)
	{
		int mutationPoint = rand.nextInt(5);
		byte bit = (byte)(1 << mutationPoint);
		value = (byte) (value ^ bit);
	}

}
