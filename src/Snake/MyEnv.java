package Snake;

import java.util.Properties;

import javax.vecmath.Color3f;
import javax.vecmath.Vector3d;

import simbad.gui.Simbad;
import simbad.sim.EnvironmentDescription;
import simbad.sim.Wall;

public class MyEnv extends EnvironmentDescription
{
	private static String PROP_NB_SNAKE_PLAYER = "nbSnakePl";
	private static String PROP_NB_SNAKE_IA = "nbSnakeIA";
	private static String PROP_WORLDSIZE = "worldSize";
	private static String PROP_FLOOR_COLOR = "floorColor";
	
	private static int BASE_NB_SNAKE_PLAYER = 2;
	private static int BASE_NB_SNAKE_IA = 0;
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
		KeyController.initControls();
		
		SnakePart.resetCounter();
		
		worldSize = BASE_WORLDSIZE;
		if (prop.get(PROP_WORLDSIZE) != null) { worldSize = (Float)prop.get(PROP_WORLDSIZE); }
		if (worldSize < 5.0f || worldSize > 30.0f) { worldSize = 20.0f; }
		
		floorColor = BASE_FLOOR_COLOR;
		if (prop.get(PROP_FLOOR_COLOR) != null) { floorColor = (Color3f)prop.get(PROP_FLOOR_COLOR); }
		if (floorColor == null) { floorColor = BASE_FLOOR_COLOR; }
		
		int nbSnakePl = BASE_NB_SNAKE_PLAYER;
		if (prop.get(PROP_NB_SNAKE_PLAYER) != null) { nbSnakePl = (Integer)prop.get(PROP_NB_SNAKE_PLAYER); }
		if (nbSnakePl < 1) { nbSnakePl = 1; }
		
		int nbSnakeIA = BASE_NB_SNAKE_IA;
		if (prop.get(PROP_NB_SNAKE_IA) != null) { nbSnakeIA = (Integer)prop.get(PROP_NB_SNAKE_IA); }
		if (nbSnakeIA < 0) { nbSnakeIA = 0; }
		
		
		
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
		

		for (int i = 0; i < nbSnakePl; i++)
		{
			new Snake(this, new Vector3d(0, 0, i), true);
		}

		for (int i = 0; i < nbSnakeIA; i++)
		{
			new Snake(this, new Vector3d(0, 0, -(i + 1)), false);
		}

		stock = new SnakeBody(new Vector3d(0, Math.random() * 10 + 100, 0), null);
		add(stock);
		for (int i = 0; i < Math.pow(worldSize * 2, 2); i++)
		{
			SnakePart nextStock = new SnakeBody(new Vector3d(0, Math.random() * 10 + 100, 0), null);
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
