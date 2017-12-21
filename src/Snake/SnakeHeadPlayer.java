/**
 * 
 */
package Snake;

import javax.vecmath.Vector3d;

/**
 * tête des joueur (tourne si les touches liée sont appuyée)
 * @see SnakeHead
 * @see SnakeHeadPlayer
 * @author Jonathan Selle, Adam Bernouy
 */
public class SnakeHeadPlayer extends SnakeHead
{

	public SnakeHeadPlayer(Vector3d position, Snake snake)
	{
		super(position, snake);
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see Snake.SnakeHead#behavior()
	 */
	@Override
	public void behavior()
	{
		if (KeyController.isPressed(KeyController.getControl(getName() + "Left")))
		{
			rotateY(vitesse / 12);
		}
		if (KeyController.isPressed(KeyController.getControl(getName() + "Right")))
		{
			rotateY(-vitesse / 12);
		}
	}

}
