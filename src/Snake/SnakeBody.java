package Snake;

import java.util.ArrayList;
import java.util.List;

import javax.vecmath.Vector3d;

public class SnakeBody extends SnakePart
{
	
	private List<double[]> lastPositions; 
	private int startingCounter;
	private static int count = 0;
	
	public SnakeBody(Vector3d position, Snake snake)
	{
		super(position, "body" + count++, snake);
		resetValues();
	}

	public void performBehavior()
	{
		super.performBehavior();
		
		if (getLinked() != null)
		{
			lastPositions.add(new double[] { getLinked().getTranslationalVelocity(), getLinked().getRotationalVelocity() });
			
			if (isAwayFromLinked() && getCounter() > startingCounter + 10 && !startedToMove)
			{
				startedToMove = true;
			}
			
			if (startedToMove)
			{
				double[] dest = lastPositions.remove(0);
				setTranslationalVelocity(dest[0]);
				setRotationalVelocity(dest[1]);
			}
		}
	}
	
	void resetValues(SnakePart link)
	{
		setPartLink(link);
		resetValues();
	}
	
	void resetValues()
	{
		startingCounter = getCounter();
		startedToMove = false;
		lastPositions = new ArrayList<double[]>();
		setTranslationalVelocity(0);
		setRotationalVelocity(0);
	}
	
	static void resetCounter()
	{
		count = 0;
	}
}