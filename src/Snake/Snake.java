package Snake;

import java.util.ArrayList;
import java.util.List;

import javax.media.j3d.Transform3D;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;

/**
 * @author jonatjano
 * @version 2017-12-07
 */
public class Snake
{
	private static List<SnakePart> inGameSnakeParts = new ArrayList<SnakePart>();
	private static List<Color3f> usedColors = new ArrayList<Color3f>();
	
	private SnakeHead head;
	private SnakePart last;
	
	private MyEnv env;
	private Color3f color;
	
	private boolean isPlayerControlled;
	
	Snake(MyEnv myEnv, Vector3d pos, boolean playerControlled)
	{
		env = myEnv;
		head = new SnakeHead(pos, this);
		last = head;
		last.setPartLink(head);
		head.setPartLink(last);
		env.add(head);
		
		do
		{
			color = new Color3f((float) Math.random(), (float) Math.random(), (float) Math.random());
		}
		while (usedColors.contains(color));
		usedColors.add(color);
		head.setColor(color);
		
		isPlayerControlled = playerControlled;
	}

	void grow()
	{
		SnakeBody newBody = (SnakeBody) env.nextStock();
		if (last == head) { head.setPartLink(newBody); }
		inGameSnakeParts.add(newBody);
		
		newBody.setColor(color);
		
		Vector3d v3d = last.getVector3d();
		
		Transform3D rotation = new Transform3D();
		last.getRotationTransform(rotation);
		
		newBody.resetValues(last);
		last = newBody;
		head.setLast(last);
		
		Point3d coordNB = new Point3d();
		newBody.getCoords(coordNB);
		
		v3d.setY(-coordNB.y);
		newBody.translateTo(v3d);
		newBody.rotateY(rotation);
	}

	public static SnakePart collideWithSnake(SnakePart askingPart)
	{
		Point3d coordAsk = new Point3d();
		askingPart.getCoords(coordAsk);
		
		Point3d coordSp = new Point3d();
		
		for (SnakePart sp : inGameSnakeParts)
		{
			if (!(sp.getLinked() == askingPart) && !(askingPart.getLinked() == sp))
			{
				sp.getCoords(coordSp);
				
				if (Math.sqrt(Math.pow(coordAsk.x - coordSp.x, 2) + Math.pow(coordAsk.y - coordSp.y, 2) + Math.pow(coordAsk.z - coordSp.z, 2)) < askingPart.getRadius() + sp.getRadius())
				{
					return sp;
				}
			}
		}
		return null;
	}
	
	public boolean isPlayerControlled()
	{
		return isPlayerControlled;
	}
}
