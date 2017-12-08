/**
 * 
 */
package Snake;

import simbad.gui.Simbad;

/**
 * @author jonatjano
 * @version 2017-12-07
 */
public class Snake
{

	/**
	 * @param args arguments de la commande (non-utilisés)
	 */
	public static void main(String[] args)
	{
		Simbad frame = new Simbad(new MyEnv() ,false);
	}
}
