package Snake;

import java.util.ArrayList;
import java.util.List;

import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;

public class SnakeBody extends SnakePart
{
	private boolean startedToMove;
	private List<double[]> lastPositions; 
	private int startingCounter;
	
	public SnakeBody(Vector3d position, MyEnv env)
	{
		super(position, "body", env);
		startedToMove = false;
		lastPositions = new ArrayList<double[]>();
	}

	public void initBehavior()
	{
		startingCounter = getCounter();
	}

	public void performBehavior()
	{
		if (getLinked() != null)
		{
			lastPositions.add(new double[] {getLinked().getTranslationalVelocity(), getLinked().getRotationalVelocity()});
			
			if ((!anOtherAgentIsVeryNear() || getVeryNearAgent() != getLinked()) && getCounter() > startingCounter + 10)
			{
				startedToMove = true;
			}
				
			if (startedToMove)
			{
				super.performBehavior();	
				
				double[] dest = lastPositions.remove(0);
				setTranslationalVelocity(dest[0]);
				setRotationalVelocity(dest[1]);
			}
		}
	}
	
	void isAdded()
	{
		startingCounter = getCounter();
		startedToMove = false;
		lastPositions = new ArrayList<double[]>();
		setTranslationalVelocity(0);
		setRotationalVelocity(0);
	}
}