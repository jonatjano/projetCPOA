package Snake;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Properties;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.vecmath.Color3f;
import javax.vecmath.Vector3d;

import simbad.gui.Simbad;
import simbad.sim.EnvironmentDescription;
import simbad.sim.Wall;
import Snake.Fruit.GrowFruit;

public class MyEnv extends EnvironmentDescription
{
	public static String PROP_NB_SNAKE_PLAYER = "nbSnakePl";
	public static String PROP_NB_SNAKE_IA = "nbSnakeIA";
	public static String PROP_WORLDSIZE = "worldSize";
	public static String PROP_FLOOR_COLOR = "floorColor";
	public static String PROP_SPEED = "speed";
	
	public static int DEFAULT_NB_SNAKE_PLAYER = 2;
	public static int DEFAULT_NB_SNAKE_IA = 0;
	public static int DEFAULT_WORLDSIZE = 15;
	public static Color3f DEFAULT_FLOOR_COLOR = new Color3f(0.7f, 0.7f, 0.7f);
	public static float DEFAULT_SPEED = 3f;
	
	private static double STOCK_HEIGHT = 100;
	
	private static Properties properties = new Properties();
	
	static JFrame frame;
	
	private SnakePart stock;

	public MyEnv()
	{		
		
		KeyController.initControls();
		SnakePart.resetCounter();
		Snake.emptyInGameList();
		
		worldSize = DEFAULT_WORLDSIZE;
		if (properties.get(PROP_WORLDSIZE) != null) { worldSize = new Float((int)properties.get(PROP_WORLDSIZE)); }
		if (worldSize < 10.0f || worldSize > 30.0f) { worldSize = 20.0f; }
		
		floorColor = DEFAULT_FLOOR_COLOR;
		if (properties.get(PROP_FLOOR_COLOR) != null) { floorColor = (Color3f)properties.get(PROP_FLOOR_COLOR); }
		if (floorColor == null) { floorColor = DEFAULT_FLOOR_COLOR; }
		
		int nbSnakePl = DEFAULT_NB_SNAKE_PLAYER;
		if (properties.get(PROP_NB_SNAKE_PLAYER) != null) { nbSnakePl = (Integer)properties.get(PROP_NB_SNAKE_PLAYER); }
		if (nbSnakePl < 1) { nbSnakePl = 1; }
		
		int nbSnakeIA = DEFAULT_NB_SNAKE_IA;
		if (properties.get(PROP_NB_SNAKE_IA) != null) { nbSnakeIA = (Integer)properties.get(PROP_NB_SNAKE_IA); }
		if (nbSnakeIA < 0) { nbSnakeIA = 0; }
		
		
		setUsePhysics(false);
		
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

		stock = new SnakeBody(new Vector3d(0, STOCK_HEIGHT, 0), null);
		add(stock);
		for (int i = 0; i < Math.pow(worldSize, 2); i++)
		{
			SnakePart nextStock = new SnakeBody(new Vector3d(0, STOCK_HEIGHT, 0), null);
			nextStock.setPartLink(stock);
			stock = nextStock;
			add(stock);
		}
		
		for (int i = 0; i < nbSnakePl + nbSnakeIA; i++)
		{
			add(new GrowFruit(new Vector3d((-0.5 + Math.random()) * (worldSize - 1), 0, (-0.5 + Math.random()) * (worldSize - 1)), "grow" + i));
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
		frame = new JFrame("Snake");
		frame.setLayout(new BorderLayout());
		frame.setSize(800, 700);
		
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		WindowListener exitListener = new WindowAdapter() {
		    public void windowClosing(WindowEvent e) {
	           System.exit(0);
		    }
		};
		frame.addWindowListener(exitListener);
		
		KeyController.initControls();
		
		frame.add(new MainPanel());
		
		frame.setVisible(true);
	}

	static void setProperties(Properties prop)
	{
		for (Object key : prop.keySet())
		{
			properties.put(key, prop.get(key));
		}
	}
	
	public static Properties getProperties()
	{
		return (Properties) properties.clone();
	}
	
	public static void setPanel(Container cpn)
	{
		frame.setContentPane(cpn);
		SwingUtilities.updateComponentTreeUI(frame);
	}
}
