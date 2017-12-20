package Snake;

import javax.vecmath.Vector3d;

import Snake.Fruit.Fruit;

import simbad.sim.SimpleAgent;

public abstract class SnakeHead extends SnakePart
{	
	private SnakePart last;
	private static int count = 0;
	
	protected double vitesse = 1.0;
	
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
		setTranslationalVelocity(vitesse);
	}
	
	public abstract void behavior();

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

			behavior();

			if (anOtherAgentIsVeryNear())
			{
				if (getVeryNearAgent() instanceof Fruit)
				{
					((Fruit) getVeryNearAgent()).eat(this);
				}
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