/**
*
*
* @author	Dana Hughes
* @version	1.0
* @since	2016-11-30
*/

package placeholder.EvolutionaryProgramming;

import placeholder.EvolutionaryProgramming.base.Gene;

import java.util.Random;

public class BitStringGene extends Gene
{
	private static Random rand = new Random();

	private int stringLength;
	private int[] string;


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


	public Gene[] crossover(Gene otherGene, double crossoverRate)
	{
		BitStringGene genes0 = new BitStringGene(stringLength);
		BitStringGene genes1 = new BitStringGene(stringLength);

		BitStringGene other = (BitStringGene) otherGene;

		for(int i=0; i<stringLength; i++)
		{
			if(rand.nextDouble() < crossoverRate)
			{
				genes0.setBit(i, this.getBit(i));
				genes1.setBit(i, other.getBit(i));
			}
			else
			{
				genes0.setBit(i, other.getBit(i));
				genes1.setBit(i, this.getBit(i));
			}
		}

		Gene[] genes = new Gene[2];

		genes[0] = (Gene) genes0;
		genes[1] = (Gene) genes1;

		return genes;
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
