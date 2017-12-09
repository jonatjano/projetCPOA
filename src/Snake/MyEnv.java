package Snake;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.media.j3d.Transform3D;
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
	private SnakePart stock;
	private SnakePart head;
	private SnakePart last;

	public MyEnv()
	{
		Wall w1 = new Wall(new Vector3d(worldSize / 2, 0, 0), worldSize, 1, this);
		w1.rotate90(1);
		add(w1);
		Wall w2 = new Wall(new Vector3d(-worldSize / 2, 0, 0), worldSize, 2, this);
		w2.rotate90(1);
		add(w2);
		Wall w3 = new Wall(new Vector3d(0, 0, worldSize / 2), worldSize, 1, this);
		add(w3);
		Wall w4 = new Wall(new Vector3d(0, 0, -worldSize / 2), worldSize, 2, this);
		add(w4);
		head = new SnakeHead(new Vector3d(0, 0, 0), this);
		last = new SnakeBody(new Vector3d(0, 0, 0), this);
		last.setPartLink(head);
		head.setPartLink(last);
		add(head);

		stock = new SnakeBody(new Vector3d(0, 100, 0), this);
		add(stock);
		for (int i = 0; i < Math.pow(worldSize * 2, 2); i++)
		{
			SnakePart nextStock = new SnakeBody(new Vector3d(0, 100, 0), this);
			nextStock.setColor(new Color3f((float) Math.random(), (float) Math.random(), (float) Math.random()));
			nextStock.setPartLink(stock);
			stock = nextStock;
			add(stock);
		}
		add(last);
	}

	void growSnake()
	{
		SnakeBody newBody = (SnakeBody) stock;
		stock = stock.getLinked();
		
		newBody.setPartLink(last);
		Vector3d v3d = last.getVector3d();

		Transform3D rotation = new Transform3D();
		last.getRotationTransform(rotation);

		last = newBody;
		v3d.setY(-100);
		newBody.translateTo(v3d);
		newBody.rotateY(rotation);
		newBody.isAdded();
	}
}
