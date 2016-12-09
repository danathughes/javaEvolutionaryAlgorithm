/**
*
*
* @author	Dana Hughes
* @version	1.0
* @since	2016-12-01
*/

package placeholder.EvolutionaryProgramming.selection;

import placeholder.EvolutionaryProgramming.base.Individual;
import placeholder.EvolutionaryProgramming.base.Population;

import java.util.Random;

public abstract class Selector
{
	public abstract Individual select(Population population);
}
