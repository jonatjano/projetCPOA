package Snake;

import javax.vecmath.Vector3d;

import simbad.sim.Agent;

public abstract class SnakePart extends Agent
{	
	protected boolean startedToMove;
	protected double angle;
	private SnakePart link;
	private MyEnv env;
	
	public SnakePart(Vector3d position, String type, MyEnv env)
	{
		super(position, type);
		this.env = env;
	}

	public void initBehavior()
	{
		setTranslationalVelocity(0);
		setRotationalVelocity(0);
	}

	public void performBehavior()
	{
	}
	
	protected MyEnv getEnv()
	{
		return env;
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
	
	void kill()
	{
		System.out.println(getName() + " killed");

		Vector3d v3d = getVector3d();
		v3d.setY(100);
		translateTo(v3d);
		if(!getName().equals("head"))
		{
			getLinked().kill();
		}
		else 
		{
            simulator.stopSimulation();
            MyEnv.restart();
		}
	}
}