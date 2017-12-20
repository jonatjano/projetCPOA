package Snake.Fruit;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.Timer;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;

import Snake.SnakeHead;

import simbad.sim.CherryAgent;


public abstract class Fruit extends CherryAgent
{
	static List<Fruit> listFruit = new ArrayList<Fruit>();
	
	public Fruit(Vector3d pos, String name, float radius)
	{
		super(pos, name, radius);
		listFruit.add(this);
	}

	public void eat(SnakeHead sh)
	{
	}

	void replace(int delay, SnakeHead sh)
	{
		Point3d coord = new Point3d();
		getCoords(coord);

		Vector3d v3d = new Vector3d();
		v3d.setY(100);
		translateTo(v3d);
		
		TaskPerformer taskPerformer = new TaskPerformer(sh.getSnake().getEnv().worldSize);
		Timer t = new Timer(delay, taskPerformer);
		t.start();
		t.setRepeats(false);
	}
	
	private class TaskPerformer implements ActionListener
	{
		private float usableWorldSize;
		
		public TaskPerformer(float worldSize)
		{
			usableWorldSize = worldSize - 1;
		}

		public void actionPerformed(ActionEvent evt)
		{
			Point3d coord = new Point3d();
			getCoords(coord);
			
			Vector3d v3d = new Vector3d();
			boolean stop = false;
			do
			{
				v3d = new Vector3d();
				v3d.setX(-coord.x - ((-usableWorldSize / 2) + Math.random() * usableWorldSize));
				v3d.setY(-100);
				v3d.setZ(-coord.z - ((-usableWorldSize / 2) + Math.random() * usableWorldSize));

				stop = false;
				for (Fruit fruitIte : listFruit)
				{
					Point3d coordF = new Point3d();
					fruitIte.getCoords(coordF);
					
					if (Math.sqrt(Math.pow(coord.x - coordF.x, 2) + Math.pow(coord.y - coordF.y, 2) + Math.pow(coord.z - coordF.z, 2)) < 0.5)
					{
						stop = true;
					}
				}
			}
			while(!stop);
				
			translateTo(v3d);
		}
	}
}
