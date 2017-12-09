package Snake;

import javax.media.j3d.Transform3D;
import javax.vecmath.Color3f;
import javax.vecmath.Vector3d;

public class SnakeHead extends SnakePart
{	
	public SnakeHead(Vector3d position, MyEnv env)
	{
		super(position, "head", env);
	}

	public void initBehavior()
	{
		setTranslationalVelocity(0.5);
	}

	public void performBehavior()
	{		
		setRotationalVelocity(1);
		if (getCounter() > 10)
		{
			super.performBehavior();
			if ((getCounter() % 50) == 0)
			{
//				setRotationalVelocity((-0.5 + Math.random()) * 2);
			}
			
			if ((getCounter() % 100) == 0)
			{
				getEnv().growSnake();
			}

			if (anOtherAgentIsVeryNear() && getVeryNearAgent() != getLinked())
			{
//				if (getVeryNearAgent() instanceof Fruit)
//				{
//					
//				}
//				else
//				{
					System.out.println("collision de la tête avec : " + getVeryNearAgent());
//				}
			}
			
			if (collisionDetected())
			{
				System.out.println("collision de la tête avec un mur");
			}
		}
	}
}