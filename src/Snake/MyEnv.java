package Snake;

import java.util.Properties;

import javax.vecmath.Color3f;
import javax.vecmath.Vector3d;

import simbad.gui.Simbad;
import simbad.sim.EnvironmentDescription;
import simbad.sim.Wall;

public class MyEnv extends EnvironmentDescription
{
	private static int BASE_NB_SNAKE = 1;
	private static float BASE_WORLDSIZE = 20f;
	private static Color3f BASE_FLOOR_COLOR = new Color3f(0f, 1f, 0f);
	
	
	private static Simbad frame;
	
	private SnakePart stock;

	public MyEnv()
	{
		this(new Properties());
	}

	public MyEnv(Properties prop)
	{
		SnakePart.resetCounter();
		
		worldSize = BASE_WORLDSIZE;
		if (prop.get("worldSize") != null) { worldSize = (Integer)prop.get("worldSize"); }
		
		floorColor = BASE_FLOOR_COLOR;
		
		int nbSnake = BASE_NB_SNAKE;
		if (prop.get("nbSnake") != null) { nbSnake = (Integer)prop.get("nbSnake"); }
		if (nbSnake < 1) { nbSnake = 1; }
		
		
		
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

		for (int i = 0; i < nbSnake; i++)
		{
			new Snake(this, new Vector3d(0, 0, i));
		}

		stock = new SnakeBody(new Vector3d(0, 100, 0), null);
		add(stock);
		for (int i = 0; i < Math.pow(worldSize * 2, 2); i++)
		{
			SnakePart nextStock = new SnakeBody(new Vector3d(0, 100, 0), null);
			nextStock.setPartLink(stock);
			stock = nextStock;
			add(stock);
		}
	}

	SnakeBody nextStock()
	{
		SnakeBody ret = (SnakeBody) stock;
		stock = stock.getLinked();
		return ret;
	}
	
	public static void main(String[] args)
	{
		frame = new Simbad(new MyEnv() ,false);
	}

	static void restart()
	{
		frame.restart(new MyEnv());
	}
}
