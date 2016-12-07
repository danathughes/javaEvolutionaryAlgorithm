/**
*
*
* @author	Dana Hughes
* @version	1.0
* @since	2016-11-30
*/

import java.util.Random;

public class BitStringGene extends Gene
{
	private static Random rand = new Random();

	private int stringLength;
	private int[] string;


	public static BitStringGene[] crossover(BitStringGene parentGene1, BitStringGene parentGene2, double crossoverRate)
	{
		BitStringGene[] genes = new BitStringGene[2];
		genes[0] = new BitStringGene(parentGene1.getLength());
		genes[1] = new BitStringGene(parentGene2.getLength());

		for(int i=0; i<parentGene1.getLength(); i++)
		{
			if(rand.nextDouble() < crossoverRate)
			{
				genes[0].setBit(i, parentGene1.getBit(i));
				genes[1].setBit(i, parentGene2.getBit(i));
			}
			else
			{
				genes[0].setBit(i, parentGene2.getBit(i));
				genes[1].setBit(i, parentGene1.getBit(i));
			}
		}

		return genes;
	}


	public Gene[] crossover(Gene otherGene, double crossoverRate)
	{
		BitStringGene[] genes = new BitStringGene[2];
		genes[0] = new BitStringGene(stringLength);
		genes[1] = new BitStringGene(stringLength);

		BitStringGene other = (BitStringGene) otherGene;

		for(int i=0; i<stringLength; i++)
		{
			if(rand.nextDouble() < crossoverRate)
			{
				genes[0].setBit(i, this.getBit(i));
				genes[1].setBit(i, other.getBit(i));
			}
			else
			{
				genes[0].setBit(i, other.getBit(i));
				genes[1].setBit(i, this.getBit(i));
			}
		}

		return genes;
	}


	public BitStringGene(int stringLength)
	{
		this.stringLength = stringLength;
		this.string = new int[stringLength];
	}


	public void randomize()
	{
		for(int i=0; i<stringLength; i++)
		{
			string[i] = rand.nextInt(2);
		}
	}


	public int getLength()
	{
		return stringLength;
	}


	public void setBit(int idx, int value)
	{
		string[idx] = value;
	}


	public int getBit(int idx)
	{
		return string[idx];
	}


	public void mutate(double mutationRate)
	{
		for(int i=0; i<stringLength; i++)
		{
			if(rand.nextDouble() < mutationRate)
			{
				string[i] = 1 - string[i];
			}
		}
	}


	public String toString()
	{
		StringBuilder builder = new StringBuilder();

		for(int i=0; i<stringLength; i++)
		{
			builder.append(string[i]);
		}

		return builder.toString();
	}
}
