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
