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

/**
 * classe environnement c'est aussi elle qui gere la fenetre
 * @author Jonathan Selle, Adam Bernouy
 */
public class MyEnv extends EnvironmentDescription
{
	/**
	 * nom de la propriété : nombre de joueurs
	 * @see DEFAULT_NB_SNAKE_PLAYER
	 * @see properties
	 */
	public static String PROP_NB_SNAKE_PLAYER = "nbSnakePl";
	/**
	 * nom de la propriété : nombre d'IA
	 * @see DEFAULT_NB_SNAKE_IA
	 * @see properties
	 */
	public static String PROP_NB_SNAKE_IA = "nbSnakeIA";
	/**
	 * nom de la propriété : taille du monde
	 * @see DEFAULT_WORLDSIZE
	 * @see properties
	 */
	public static String PROP_WORLDSIZE = "worldSize";
	/**
	 * nom de la propriété : couleur du sol
	 * @see DEFAULT_FLOOR_COLOR
	 * @see properties
	 */
	public static String PROP_FLOOR_COLOR = "floorColor";
	/**
	 * nom de la propriété : multiplicateur de vitesse
	 * @see DEFAULT_SPEED
	 * @see properties
	 */
	public static String PROP_SPEED = "speed";
	
	/**
	 * valeur par defaut de la propriété : nombre de joueurs
	 * @see PROP_NB_SNAKE_PLAYER
	 * @see properties
	 */
	public static int DEFAULT_NB_SNAKE_PLAYER = 2;
	/**
	 * valeur par defaut de la propriété : nombre d'IA
	 * @see PROP_NB_SNAKE_IA
	 * @see properties
	 */
	public static int DEFAULT_NB_SNAKE_IA = 0;
	/**
	 * valeur par defaut de la propriété : taille du monde
	 * @see PROP_WORLDSIZE
	 * @see properties
	 */
	public static int DEFAULT_WORLDSIZE = 15;
	/**
	 * valeur par defaut de la propriété : couleur du sol
	 * @see PROP_FLOOR_COLOR
	 * @see properties
	 */
	public static Color3f DEFAULT_FLOOR_COLOR = new Color3f(0.7f, 0.7f, 0.7f);
	/**
	 * valeur par defaut de la propriété : multiplicateur de vitesse
	 * @see PROP_SPEED
	 * @see properties
	 */
	public static int DEFAULT_SPEED = 3;
	
	/**
	 * hauteur à laquel sont placé les robots de stock (créer mais pas encore en jeu)
	 */
	private static double STOCK_HEIGHT = 100;
	
	/**
	 * les propriétés qui sont utilisé à la création de l'environnement
	 * @see PROP_NB_SNAKE_PLAYER
	 * @see PROP_NB_SNAKE_IA
	 * @see PROP_WORLDSIZE
	 * @see PROP_FLOOR_COLOR
	 * @see PROP_SPEED
	 */
	private static Properties properties = new Properties();
	
	/**
	 * frame principale de l'application
	 */
	static JFrame frame;
	
	/**
	 * le prochain robot à être mis en jeu
	 */
	private SnakePart stock;

	/**
	 * classe définissant l'environnement du jeu
	 */
	public MyEnv()
	{		
		/*
		 * on remet toutes les valeurs à 0
		 */
		KeyController.initControls();
		SnakePart.resetCounter();
		Snake.emptyInGameList();
		
		/*
		 * on défini les variables celon les propriétés
		 */
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
		
		// pour l'utilisation des balles
		setUsePhysics(false);
		
		// on met les murs
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

		// on creer les serpents
		for (int i = 0; i < nbSnakePl; i++)
		{
			new Snake(this, new Vector3d(0, 0, i), true);
		}

		for (int i = 0; i < nbSnakeIA; i++)
		{
			new Snake(this, new Vector3d(0, 0, -(i + 1)), false);
		}

		// on creer les stocks pour les serpents (simbad interdit de creer des Robots après le début de la simulation
		stock = new SnakeBody(new Vector3d(0, STOCK_HEIGHT, 0), null);
		add(stock);
		for (int i = 0; i < Math.pow(worldSize, 2); i++)
		{
			SnakePart nextStock = new SnakeBody(new Vector3d(0, STOCK_HEIGHT, 0), null);
			nextStock.setPartLink(stock);
			stock = nextStock;
			add(stock);
		}
		
		// on créer les fruit
		for (int i = 0; i < nbSnakePl + nbSnakeIA; i++)
		{
			add(new GrowFruit(new Vector3d((-0.5 + Math.random()) * (worldSize - 1), 0, (-0.5 + Math.random()) * (worldSize - 1)), "grow" + i));
		}
	}

	/**
	 * permet d'obtenir le dernier stock et passe au prochain
	 * @return le stock qui doit être rajouté au jeu
	 */
	SnakeBody nextStock()
	{
		SnakeBody ret = (SnakeBody) stock;
		stock = stock.getLinked();
		return ret;
	}
	
	/**
	 * main appellé
	 * @param args non utilisés
	 */
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
		
		frame.add(new HelpPanel());
		
		frame.setVisible(true);
	}

	/**
	 * permet de définir les propriétés de la prochaine partie
	 * @param prop les propriétés à rajouter
	 */
	static void setProperties(Properties prop)
	{
		for (Object key : prop.keySet())
		{
			properties.put(key, prop.get(key));
		}
	}
	
	/**
	 * permet d'obtenir une propriété
	 * @return la rpopriété demandée
	 */
	public static Properties getProperties()
	{
		return (Properties) properties.clone();
	}
	
	/**
	 * défini le panel à afficher
	 * @param cpn le panel à afficher
	 */
	public static void setPanel(Container cpn)
	{
		
		frame.setContentPane(cpn);
		SwingUtilities.updateComponentTreeUI(frame);
	}
}
