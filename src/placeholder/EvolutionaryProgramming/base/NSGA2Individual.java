/**
*
*
* @author	Dana Hughes
* @version	1.0
* @since	2016-11-30
*/

package placeholder.EvolutionaryProgramming.base;

import placeholder.EvolutionaryProgramming.base.AbstractIndividualFactory;
import placeholder.EvolutionaryProgramming.genotypes.Genotype;

import java.util.Random;

import java.util.List;
import java.util.ArrayList;

import java.lang.Comparable;

public class NSGA2Individual extends Individual implements Comparable<Individual>
{
	private static Random rand = new Random();

	public static int DOMINATION = -1;
	public static int NSGA2 = -2;

	// Needed stuff for NSGA-II
	private List<Individual> dominationSet;
	private int dominationCount;
	private int rank;
	private double crowdingDistance;

	private int objectiveNumber;


	public NSGA2Individual(Genotype gene, FitnessFunction fitness, AbstractIndividualFactory factory)
	{
		super(gene, fitness, factory);

		dominationSet = new ArrayList<Individual>();
		dominationCount = 0;
		rank = 0;
		crowdingDistance = 0.0;
	}


	public void reset()
	{
		dominationSet.clear();
		dominationCount = 0;
		rank = 0;
	}


	public void addToDominationSet(Individual individual)
	{
		dominationSet.add(individual);
	}


	public List<Individual> getDominationSet()
	{
		return dominationSet;
	}


	public void incrementDominationCount()
	{
		dominationCount++;
	}


	public void decrementDominationCount()
	{
		dominationCount--;
	}


	public int getDominationCount()
	{
		return dominationCount;
	}


	public void setRank(int rank)
	{
		this.rank = rank;
	}


	public boolean dominates(Individual other)
	{
		return ((ParetoFitness) this.fitness()).compareDominated((ParetoFitness) other.fitness()) == 1;
	}


	public void setCrowdingDistance(double value)
	{
		crowdingDistance = value;
	}


	public double getCrowdingDistance()
	{
		return crowdingDistance;
	}


	public void sortBy(int objectiveNum)
	{
		objectiveNumber = objectiveNum;

		if(objectiveNumber >= -1)
		{
			((ParetoFitness) this.fitness()).sortByObjective(objectiveNumber);
		}
	}


	public int compareTo(Individual other)
	{
		int results = 0;

		if(objectiveNumber >= -1)
		{
			results = ((ParetoFitness) this.fitness()).compareTo(other.fitness());	
		}
		else	// Sort according to partial ordering of NSGA-II
		{
			if(this.dominates((NSGA2Individual) other))
			{
				results = 1;
			}
			else if(((NSGA2Individual) other).dominates(this))
			{
				results = -1;
			}
			else
			{
				if(this.getCrowdingDistance() > ((NSGA2Individual) other).getCrowdingDistance())
				{
					results = 1;
				}
				else if(((NSGA2Individual) other).getCrowdingDistance() < this.getCrowdingDistance())
				{
					results = -1;
				}
				else
				{
					results = 0;
				}
			}
		}

		return results;
	}
}
