package Snake;

import java.util.ArrayList;
import java.util.List;

import javax.vecmath.Vector3d;

public class SnakeBody extends SnakePart
{
	
	private List<double[]> lastPositions; 
	private int startingCounter;
	private static int count = 0;
	
	public SnakeBody(Vector3d position, MyEnv env)
	{
		super(position, "body" + count++, env);
		resetValues();
	}

	public void performBehavior()
	{
		super.performBehavior();
		//if (getName().equals("body0") || getName().equals("body401"))
		//{
		//	System.out.println(getName() + " : " + (getCounter() - startingCounter - ((getName().equals("body0")) ? 100 : 23)) + " : " + getTranslationalVelocity() + ":" + getRotationalVelocity() + " " + startedToMove);
		//}
		
		if (getLinked() != null)
		{
//			System.out.println(getName() + " linked " + getLinked());
			lastPositions.add(new double[] {getLinked().getTranslationalVelocity(), getLinked().getRotationalVelocity()});
			
			if (((!anOtherAgentIsVeryNear() || getVeryNearAgent() != getLinked()) && getCounter() > startingCounter + 10) && !startedToMove)
			{
//				System.out.println(getName() + " verynear " + getVeryNearAgent());
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
}