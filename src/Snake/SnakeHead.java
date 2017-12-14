package Snake;

import javax.vecmath.Vector3d;

public class SnakeHead extends SnakePart
{	
	private SnakePart last;
	
	public SnakeHead(Vector3d position, MyEnv env)
	{
		super(position, "head", env);
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

			if ((getCounter() % 100) == 0)
			{
				getEnv().growSnake();
				setRotationalVelocity(Math.PI);
			}
			
			if ((getCounter() % 101) == 0)
			{
				setRotationalVelocity(0);
			}
			
//			if (getCounter() >= 1200)
//			{
//				setRotationalVelocity(0);
//			}

			if (anOtherAgentIsVeryNear() && getVeryNearAgent() != getLinked())
			{
//				if (getVeryNearAgent() instanceof Fruit)
//				{
//					
//				}
//				else
//				{
					System.out.println("collision de la tête avec : " + getVeryNearAgent());
					last.kill();
//				}
			}

			if (collisionDetected())
			{
				System.out.println("collision de la tête avec un mur");
				last.kill();
			}
		}
	}
}