package Snake;

import javax.vecmath.Vector3d;

public class SnakeHead extends SnakePart
{	
	private SnakePart last;
	private static int count = 0;
	
	public SnakeHead(Vector3d position, Snake snake)
	{
		super(position, "head" + count++, snake);
	}
	
	void setLast(SnakePart l)
	{
		last = l;
	}

	public void initBehavior()
	{
		super.initBehavior();
		setTranslationalVelocity(0.75);
	}

	public void performBehavior()
	{
		if (getCounter() > 10)
		{			
			if (getCounter() == 11)
			{
				getSnake().grow();
			}
			if (getCounter() == 40)
			{
				getSnake().grow();
			}

			if (getSnake().isPlayerControlled())
			{
				if (KeyController.isPressed(KeyController.getControl(getName() + "Left")))
				{
					rotateY(0.05);
				}
				if (KeyController.isPressed(KeyController.getControl(getName() + "Right")))
				{
					rotateY(-0.05);
				}
			}
			else
			{
				/* ******** */
				/* DEBUT IA */
				/* ******** */
				
				// TODO IA pour les bots serpents
				if ((getCounter() % 100) == 0)
				{
					setRotationalVelocity(-Math.PI);
				}
				
				if ((getCounter() % 101) == 0)
				{
					setRotationalVelocity(0);
				}
				
				/* ****** */
				/* FIN IA */
				/* ****** */
			}
			
			if ((getCounter() % 100) == 0)
			{
				getSnake().grow();
			}

			if (anOtherAgentIsVeryNear())
			{
//				if (getVeryNearAgent() instanceof Fruit)
//				{
//					((Fruit) getVeryNearAgent).eat(this);
//				}
			}
			
			SnakePart collision = Snake.collideWithSnake(this);
			if (collision != null)
			{
				if (collision.getName().startsWith("head"))
				{
					collision.getSnake().kill(simulator, false);
				}
				getSnake().kill(simulator);
				
			}

			if (collisionDetected())
			{
				getSnake().kill(simulator);
			}
		}
	}
	
	static void resetCounter()
	{
		count = 0;
	}
}