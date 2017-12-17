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
		setTranslationalVelocity(0.5);
	}

	public void performBehavior()
	{
//		setRotationalVelocity(1);
		if (getCounter() > 10)
		{
			super.performBehavior();
			
			/*if ((getCounter() % 50) == 0)
			{
				setRotationalVelocity((-0.5 + Math.random()) * 2);
			}*/

			if (!getName().equals("head0"))
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
				System.out.println("collision de la tête avec : " + collision + "(" + (collision == getLinked()) + ")");
				last.kill();
			}

			if (collisionDetected())
			{
				System.out.println("collision de la tête avec un mur");
				last.kill();
			}
		}
	}
	
	static void resetCounter()
	{
		count = 0;
	}
}