package Snake;

import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;

import simbad.sim.Agent;

public abstract class SnakePart extends Agent
{	
	protected boolean startedToMove;
	protected double angle;
	private SnakePart link;
	private Snake snake;
	
	public SnakePart(Vector3d position, String type, Snake mySnake)
	{
		super(position, type);
		snake = mySnake;
	}

	public void initBehavior()
	{
		setTranslationalVelocity(0);
		setRotationalVelocity(0);
	}
	
	void setSnake(Snake s)
	{
		if (snake == null)
		{
			snake = s;	
		}
	}
	
	Snake getSnake()
	{
		return snake;
	}
	
	void setPartLink(SnakePart part)
	{
		link = part;
	}
	
	SnakePart getLinked()
	{
		return link;
	}
	
	Vector3d getVector3d()
	{
		return (Vector3d) v1.clone();
	}
	
	boolean isHead()
	{
		return false;
	}
	
	void kill()
	{
		System.out.println(getName() + " killed");

		Vector3d v3d = getVector3d();
		v3d.setY(100);
		translateTo(v3d);
		if(!getName().startsWith("head"))
		{
			getLinked().kill();
		}
		else 
		{
            simulator.stopSimulation();
            MyEnv.restart();
		}
	}
	
	boolean isAwayFromLinked()
	{
		if (link == null) { return true; }
		
		Point3d coordThis = new Point3d();
		getCoords(coordThis);
		
		Point3d coordLink = new Point3d();
		link.getCoords(coordLink);
		
		// d = √((x2 - x1)2 + (y2 - y1)2 + (z2 - z1)2).
		
		if (Math.sqrt(Math.pow(coordThis.x - coordLink.x, 2) + Math.pow(coordThis.y - coordLink.y, 2) + Math.pow(coordThis.z - coordLink.z, 2)) < getRadius() + link.getRadius())
		{
			return false;
		}
		
		return true;
	}
	
	static void resetCounter()
	{
		SnakeHead.resetCounter();
		SnakeBody.resetCounter();
	}
}