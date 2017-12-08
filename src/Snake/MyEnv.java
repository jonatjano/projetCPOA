package Snake;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.vecmath.Color3f;
import javax.vecmath.Vector3d;
import javax.vecmath.Vector3f;

import simbad.sim.Arch;
import simbad.sim.BallAgent;
import simbad.sim.Box;
import simbad.sim.CherryAgent;
import simbad.sim.EnvironmentDescription;
import simbad.sim.StaticObject;
import simbad.sim.Wall;


public class MyEnv extends EnvironmentDescription
{
	private SnakePart head;
	private SnakePart firstBody;
	
	public MyEnv()
	{
		 Wall w1 = new Wall(new Vector3d(9, 0, 0), 19, 1, this);
		 w1.rotate90(1);
		 add(w1);
		 Wall w2 = new Wall(new Vector3d(-9, 0, 0), 19, 2, this);
		 w2.rotate90(1);
		 add(w2);
		 Wall w3 = new Wall(new Vector3d(0, 0, 9), 19, 1, this);
		 add(w3);
		 Wall w4 = new Wall(new Vector3d(0, 0, -9), 19, 2, this);
		 add(w4);
		 head = new SnakeHead(new Vector3d(0, 0, 0), this);
		 firstBody = new SnakeBody(new Vector3d(0, 0, 0), this);
		 head.setPartLink(firstBody);
		 firstBody.setPartLink(head);
		 add(head);
		 add(firstBody);
	}
	
	void growSnake()
	{
		 Wall w4 = new Wall(new Vector3d(0, 0, 0), 19, 2, this);
		 add(w4);
		SnakeBody newBody = new SnakeBody(new Vector3d(0, 0, 0), this);
		newBody.setColor(new Color3f((float)Math.random(), (float)Math.random(), (float)Math.random()));
		newBody.setPartLink(head);
		firstBody.setPartLink(newBody);
		head.setPartLink(newBody);
		firstBody = newBody;
		Vector3d v3d = head.getVector3d();
		add(new SnakeBody(head.getVector3d(), this));
	}
}
