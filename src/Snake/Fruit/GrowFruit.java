package Snake.Fruit;

import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;

import simbad.sim.CherryAgent;

import Snake.SnakeHead;

/**
 * fruit qui fait grandir
 * @author Jonathan Selle, Adam Bernouy
 */
public class GrowFruit extends Fruit
{

	public GrowFruit(Vector3d pos, String name)
	{
		super(pos, name, 0.20f);
		setColor(new Color3f(1f,0f,0f));
	}

	@Override
	public void eat(SnakeHead sh)
	{
		sh.getSnake().grow();
		
		replace(0, sh);
	}

}
