package Snake;

import java.util.Iterator;
import java.util.Properties;

import javax.vecmath.Color3f;
import javax.vecmath.Vector3d;

import simbad.gui.Simbad;
import simbad.sim.EnvironmentDescription;
import simbad.sim.Wall;

public class MyEnv extends EnvironmentDescription
{
	public static String PROP_NB_SNAKE_PLAYER = "nbSnakePl";
	public static String PROP_NB_SNAKE_IA = "nbSnakeIA";
	public static String PROP_WORLDSIZE = "worldSize";
	public static String PROP_FLOOR_COLOR = "floorColor";
	
	private static int DEFAULT_NB_SNAKE_PLAYER = 1;
	private static int DEFAULT_NB_SNAKE_IA = 0;
	private static float DEFAULT_WORLDSIZE = 20f;
	private static Color3f DEFAULT_FLOOR_COLOR = new Color3f(0f, 1f, 0f);
	
	private static Properties properties = new Properties();
	
	private static Simbad frame;
	
	private SnakePart stock;

	public MyEnv()
	{
		KeyController.initControls();
		
		SnakePart.resetCounter();
		Snake.emptyInGameList();
		
		worldSize = DEFAULT_WORLDSIZE;
		if (properties.get(PROP_WORLDSIZE) != null) { worldSize = (Float)properties.get(PROP_WORLDSIZE); }
		if (worldSize < 5.0f || worldSize > 30.0f) { worldSize = 20.0f; }
		
		floorColor = DEFAULT_FLOOR_COLOR;
		if (properties.get(PROP_FLOOR_COLOR) != null) { floorColor = (Color3f)properties.get(PROP_FLOOR_COLOR); }
		if (floorColor == null) { floorColor = DEFAULT_FLOOR_COLOR; }
		
		int nbSnakePl = DEFAULT_NB_SNAKE_PLAYER;
		if (properties.get(PROP_NB_SNAKE_PLAYER) != null) { nbSnakePl = (Integer)properties.get(PROP_NB_SNAKE_PLAYER); }
		if (nbSnakePl < 1) { nbSnakePl = 1; }
		
		int nbSnakeIA = DEFAULT_NB_SNAKE_IA;
		if (properties.get(PROP_NB_SNAKE_IA) != null) { nbSnakeIA = (Integer)properties.get(PROP_NB_SNAKE_IA); }
		if (nbSnakeIA < 0) { nbSnakeIA = 0; }
		
		
		
		Wall w1 = new Wall(new Vector3d(worldSize / 2, 0, 0), worldSize, 1, this);
		w1.rotate90(1);
		add(w1);
		Wall w2 = new Wall(new Vector3d(-worldSize / 2, 0, 0), worldSize, 1, this);
		w2.rotate90(1);
		add(w2);
		Wall w3 = new Wall(new Vector3d(0, 0, worldSize / 2), worldSize, 1, this);
		add(w3);
		Wall w4 = new Wall(new Vector3d(0, 0, -worldSize / 2), worldSize, 1, this);
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
		restart(new Properties());
	}

	static void restart(Properties prop)
	{
		for (Object key : prop.keySet())
		{
			properties.put(key, prop.get(key));
		}
		frame.restart(new MyEnv());
	}
}
