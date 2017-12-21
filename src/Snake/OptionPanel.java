package Snake;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.vecmath.Color3f;

import Snake.KeyController.Side;

/**
 * Panel qui permet de changer les options du jeu
 * @author Jonathan Selle, Adam Bernouy
 */
public class OptionPanel extends JPanel implements ChangeListener, ActionListener
{
	/**
	 * nombre maximum de snake en jeu
	 * @see MyEnv.PROP_NB_SNAKE_PLAYER
	 * @see MyEnv.DEFAULT_NB_SNAKE_PLAYER
	 * @see MyEnv.PROP_NB_SNAKE_IA
	 * @see MyEnv.DEFAULT_NB_SNAKE_IA
	 */
	private int MAX_SNAKE = 8;
	/**
	 * taille minimale du monde
	 * @see MyEnv.PROP_WORLDSIZE
	 * @see MyEnv.DEFAULT_WORLDSIZE
	 */
	private int MIN_WORLDSIZE = 10;
	/**
	 * taille maximale du jeu
	 * @see MyEnv.PROP_WORLDSIZE
	 * @see MyEnv.DEFAULT_WORLDSIZE
	 */
	private int MAX_WORLDSIZE = 25;
	/**
	 * multiplicateur de vitesse minimale
	 * @see MyEnv.PROP_SPEED
	 * @see MyEnv.DEFAULT_SPEED
	 */
	private int MIN_SPEED = 1;
	/**
	 * multiplicateur de vitesse maximale
	 * @see MyEnv.PROP_SPEED
	 * @see MyEnv.DEFAULT_SPEED
	 */
	private int MAX_SPEED = 5;

	/**
	 * liste contenant les couleurs des 8 serpents
	 */
	private static List<Color3f> snakeColor;
	/**
	 * liste contenant les 8 panel qui permettent de modifier les touches et la couleur des serpents
	 */
	private List<SnakeOptionPan> snakePanList;

	private static final long serialVersionUID = 1L;
	/**
	 * JSlider permettant de modifier la taille du monde
	 */
	private JSlider worldSizeSlide;
	/**
	 * JSlider permettant de modifier la vitesse du serpent
	 */
	private JSlider speedSlide;
	/**
	 * JSlider permettant de modifier le nombre de serpent joueur
	 */
	private JSlider NbPlayerSlide;
	/**
	 * JSlider permettant de modifier le nombre de serpent IA
	 */
	private JSlider NbIASlide;

	/**
	 * initialisation de la couleur des robots
	 */
	static
	{
		snakeColor = new ArrayList<Color3f>();
		snakeColor.add(new Color3f(0f, 0f, 0f));
		snakeColor.add(new Color3f(0f, 0f, 1f));
		snakeColor.add(new Color3f(0f, 1f, 0f));
		snakeColor.add(new Color3f(0f, 1f, 1f));
		snakeColor.add(new Color3f(1f, 0f, 0f));
		snakeColor.add(new Color3f(1f, 0f, 1f));
		snakeColor.add(new Color3f(1f, 1f, 0f));
		snakeColor.add(new Color3f(1f, 1f, 1f));
	}

	/**
	 * le construteur ajoute au panel les JSLider et creer les panel de modification de serpent
	 */
	public OptionPanel()
	{
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		// on recupere les propriétés qui doivent être utiisée pour initialisé les valeurs
		Properties prop = MyEnv.getProperties();

		// changement de la taille du monde
		add(new JLabel(" "));
		JLabel worldSizeLab = new JLabel("Taille de la carte : valeur conseillée 15 pour 2 serpents");
		worldSizeLab.setAlignmentX(CENTER_ALIGNMENT);
		add(worldSizeLab);

		if (prop.get(MyEnv.PROP_WORLDSIZE) != null)
		{
			worldSizeSlide = new JSlider(MIN_WORLDSIZE, MAX_WORLDSIZE, (int) prop.get(MyEnv.PROP_WORLDSIZE)); // prop.get(MyEnv.PROP_WORLDSIZE)
		}
		else
		{
			worldSizeSlide = new JSlider(MIN_WORLDSIZE, MAX_WORLDSIZE, (int) MyEnv.DEFAULT_WORLDSIZE);
		}
		worldSizeSlide.setMaximumSize(new Dimension(500, 75));
		worldSizeSlide.setMajorTickSpacing(5);
		worldSizeSlide.setMinorTickSpacing(1);
		worldSizeSlide.setPaintTicks(true);
		worldSizeSlide.setPaintLabels(true);
		worldSizeSlide.setAlignmentX(CENTER_ALIGNMENT);
		add(worldSizeSlide);
		add(new JLabel(" "));

		// changement de la vitesse de deplacement
		add(new JLabel(" "));
		JLabel speedLab = new JLabel("Vitesse de déplacement : valeur conseillée 3");
		speedLab.setAlignmentX(CENTER_ALIGNMENT);
		add(speedLab);

		if (prop.get(MyEnv.PROP_SPEED) != null)
		{
			speedSlide = new JSlider(MIN_SPEED, MAX_SPEED, (int) prop.get(MyEnv.PROP_SPEED));
		}
		else
		{
			speedSlide = new JSlider(MIN_SPEED, MAX_SPEED, (int) MyEnv.DEFAULT_SPEED);
		}
		speedSlide.setMaximumSize(new Dimension(500, 75));
		speedSlide.setMajorTickSpacing(1);
		speedSlide.setPaintTicks(true);
		speedSlide.setPaintLabels(true);
		speedSlide.setAlignmentX(CENTER_ALIGNMENT);
		add(speedSlide);
		add(new JLabel(" "));

		// changement du nombre de joueur
		add(new JLabel(" "));
		JLabel NbPlayerLab = new JLabel("Nombre de joueurs");
		NbPlayerLab.setAlignmentX(CENTER_ALIGNMENT);
		add(NbPlayerLab);

		if (prop.get(MyEnv.PROP_NB_SNAKE_PLAYER) != null)
		{
			NbPlayerSlide = new JSlider(1, MAX_SNAKE, (int) prop.get(MyEnv.PROP_NB_SNAKE_PLAYER));
		}
		else
		{
			NbPlayerSlide = new JSlider(1, MAX_SNAKE, MyEnv.DEFAULT_NB_SNAKE_PLAYER);
		}
		NbPlayerSlide.setMaximumSize(new Dimension(500, 75));
		NbPlayerSlide.setMajorTickSpacing(1);
		NbPlayerSlide.setPaintTicks(true);
		NbPlayerSlide.setPaintLabels(true);
		NbPlayerSlide.setAlignmentX(CENTER_ALIGNMENT);
		NbPlayerSlide.addChangeListener(this);
		add(NbPlayerSlide);
		add(new JLabel(" "));

		// changement du nombre d'IA
		/**
		 * TODO enlever les // une fois que l'ia fonctionne
		 */
//		add(new JLabel(" "));
		JLabel NbIALab = new JLabel("Nombre de serpents controllé par l'ordinateur");
		NbIALab.setAlignmentX(CENTER_ALIGNMENT);
//		add(NbIALab);

		if (prop.get(MyEnv.PROP_NB_SNAKE_IA) != null)
		{
			NbIASlide = new JSlider(0, MAX_SNAKE - NbPlayerSlide.getValue(), (int) prop.get(MyEnv.PROP_NB_SNAKE_IA));
		}
		else
		{
			NbIASlide = new JSlider(0, MAX_SNAKE - NbPlayerSlide.getValue(), Math.min(MyEnv.DEFAULT_NB_SNAKE_IA, MAX_SNAKE - NbPlayerSlide.getValue()));
		}
		NbIASlide.setMaximumSize(new Dimension(500, 75));
		NbIASlide.setMajorTickSpacing(1);
		NbIASlide.setPaintTicks(true);
		NbIASlide.setPaintLabels(true);
		NbIASlide.setAlignmentX(CENTER_ALIGNMENT);
//		add(NbIASlide);
//		add(new JLabel(" "));

		// changement des valeurs des serpents
		add(new JLabel(" "));
		JLabel snakeOptionLab = new JLabel("Options des serpents");
		snakeOptionLab.setAlignmentX(CENTER_ALIGNMENT);
		add(snakeOptionLab);

		JPanel snakeChooserPan = new JPanel();
		snakeChooserPan.setLayout(new GridLayout(2, 4));
		snakeChooserPan.setMaximumSize(new Dimension(500, 150));
		for (int i = 0; i < 8; i++)
		{
			// on creer les bouton permettant de choisir quel serpent modifier
			JButton but = new JButton("Serpent " + (i + 1));
			but.addActionListener(this);
			but.setName(i + "");
			snakeChooserPan.add(but);
		}
		add(snakeChooserPan);
		add(new JLabel(" "));

		// on ajoute les panel qui permettent de modifier le serpent
		snakePanList = new ArrayList<SnakeOptionPan>();
		for (int i = 0; i < MAX_SNAKE; i++)
		{
			SnakeOptionPan innerSnakePan = new SnakeOptionPan(i);
			innerSnakePan.setVisible(false);
			snakePanList.add(innerSnakePan);
			add(innerSnakePan);
		}
		// on affiche celui du premier serpent
		snakePanList.get(0).setVisible(true);

		add(new JLabel(" "));

		// le bouton permettant de confirmer nos choix
		JButton confirm = new JButton("Confirmer");
		confirm.addActionListener(this);
		add(confirm);
	}

	/**
	 * methode permettant de recupéré la couleur d'un serpent depuis le nom de sa tête
	 * @param headName Le nom du serpent dont la couleur est demandée
	 * @return la couleur du serpent
	 */
	public static Color3f getSnakeColor(String headName)
	{
		if (headName.matches("head[0-7]"))
		{
			return snakeColor.get(Integer.parseInt(headName.substring(4)));
		}
		return null;
	}

	/**
	 * appelée lors du changement de la valeur du JSlider indiquant le nombre de joueur
	 * permet d'adapter le nombre de serpent IA possible pour un nombre de joueur
	 * @see MAX_SNAKE
	 */
	@Override
	public void stateChanged(ChangeEvent e)
	{
		NbIASlide.setValue(Math.min(NbIASlide.getValue(), MAX_SNAKE - NbPlayerSlide.getValue()));
		NbIASlide.setMaximum(MAX_SNAKE - NbPlayerSlide.getValue());
		if (NbIASlide.getMaximum() == 0)
		{
			NbIASlide.setEnabled(false);
		}
		else
		{
			NbIASlide.setEnabled(true);
		}
	}

	/**
	 * appelé lors de l'appui sur un bouton
	 * si le bouton est le bouton "confirmer" on enregistre les valeurs puis on retourne  l'écran principal
	 * sinon on est sur un bouton de choix du serpent a modifier et on affiche le panel correspondant
	 */
	@Override
	public void actionPerformed(ActionEvent e)
	{
		JButton src = (JButton) e.getSource();
		// c'est un bouton choix de panel
		if (src.getText().startsWith("Serpent"))
		{
			int id = -1;
			try
			{
				// on recupere l'id du serpent
				id = Integer.parseInt(((JButton) e.getSource()).getName());
				for (int i = 0; i < MAX_SNAKE; i++)
				{
					if (i == id)
					{
						snakePanList.get(i).setVisible(true);
					}
					else
					{
						snakePanList.get(i).setVisible(false);
					}
				}
			}
			catch (Exception e2)
			{
			}
		}
		// c'est le bouton confirmer
		else if (src.getText().equals("Confirmer"))
		{
			// on enregistre les valeurs dans les propriétés
			Properties prop = new Properties();
			prop.put(MyEnv.PROP_WORLDSIZE, worldSizeSlide.getValue());
			prop.put(MyEnv.PROP_NB_SNAKE_PLAYER, NbPlayerSlide.getValue());
			prop.put(MyEnv.PROP_NB_SNAKE_IA, NbIASlide.getValue());
			prop.put(MyEnv.PROP_SPEED, speedSlide.getValue());

			// on enregistre les nouvelles couleurs des serpents
			for (int i = 0; i < MAX_SNAKE; i++)
			{
				snakeColor.get(i).set(snakePanList.get(i).colorS);
			}

			MyEnv.setProperties(prop);
			// on passe au panel principal
			MyEnv.setPanel(new MainPanel());
		}
	}

	/**
	 * 
	 * @author Jonathan Selle, Adam Bernouy
	 *
	 */
	private class SnakeOptionPan extends JPanel implements ActionListener, IUpdateKey
	{
		public Color colorS;

		private int keyCodeMoveLeft;
		private int keyCodeMoveRight;

		private JButton leftKeyBut;
		private JButton rightKeyBut;
		private JButton lastSideButtonClicked;

		public SnakeOptionPan(int snakeId)
		{
			setLayout(new BorderLayout());

			add(new JLabel("Option du serpent " + (snakeId + 1), JLabel.CENTER), BorderLayout.NORTH);

			JPanel labPan = new JPanel(new GridLayout(2, 3));

			labPan.add(new JLabel("Tourner à gauche", JLabel.CENTER));
			labPan.add(new JLabel("Couleur du serpent", JLabel.CENTER));
			labPan.add(new JLabel("Tourner à droite", JLabel.CENTER));

			this.leftKeyBut = new JButton(KeyEvent.getKeyText(KeyController.getControl("head" + snakeId + "Left")));
			this.leftKeyBut.setName("head" + snakeId + "Left");
			this.leftKeyBut.addActionListener(this);
			labPan.add(this.leftKeyBut);

			final JButton color = new JButton();
			color.setBackground(new Color(snakeColor.get(snakeId).x, snakeColor.get(snakeId).y, snakeColor.get(snakeId).z));
			colorS = color.getBackground();

			ActionListener actionListener = new ActionListener()
			{
				public void actionPerformed(ActionEvent actionEvent)
				{
					Color initialBackground = color.getBackground();
					Color background = JColorChooser.showDialog(null, "Choix de la couleur du serpent", initialBackground);
					if (background != null)
					{
						color.setBackground(background);
						colorS = background;
					}
				}
			};
			color.addActionListener(actionListener);
			labPan.add(color);

			this.rightKeyBut = new JButton(KeyEvent.getKeyText(KeyController.getControl("head" + snakeId + "Right")));
			this.rightKeyBut.setName("head" + snakeId + "Right");
			this.rightKeyBut.addActionListener(this);
			labPan.add(rightKeyBut);

			add(labPan, BorderLayout.CENTER);

			add(new JLabel("                                          "), BorderLayout.WEST);
			add(new JLabel("                                          "), BorderLayout.EAST);

			// Initialisation des méthodes de l'interface permettant le changement des touches
			this.keyCodeMoveLeft = KeyController.getControl("head" + snakeId + "Left");
			this.keyCodeMoveRight = KeyController.getControl("head" + snakeId + "Right");
			this.lastSideButtonClicked = null;
		}

		@Override
		public void actionPerformed(ActionEvent e)
		{
			this.lastSideButtonClicked = (JButton) e.getSource();
			if (lastSideButtonClicked == leftKeyBut)
			{
				new SnakeOptionChangeKey(this, MyEnv.frame);
			}
			else if (lastSideButtonClicked == rightKeyBut)
			{
				new SnakeOptionChangeKey(this, MyEnv.frame);
			}
		}

		@Override
		public void onKeyUpdate(int keyCode)
		{
			if (lastSideButtonClicked != null)
			{
				KeyController.setControl(lastSideButtonClicked.getName(), keyCode);
				lastSideButtonClicked.setText(KeyEvent.getKeyText(keyCode));
			}
		}

		@Override
		public int getActualKey()
		{
			if (lastSideButtonClicked != null)
			{
				return KeyController.getControl(lastSideButtonClicked.getName());
			}
			return 0;
		}
	}

	private interface IUpdateKey
	{
		public void onKeyUpdate(int keyCode);

		public int getActualKey();
	}

	private class SnakeOptionChangeKey
	{

		private IUpdateKey iUpdateKey;

		public SnakeOptionChangeKey(IUpdateKey iUpdateKey, JFrame frame)
		{
			this.iUpdateKey = iUpdateKey;

			JOptionPane optionPane = new JOptionPane();
			JTextField field = getField();
			optionPane.setMessage(new Object[] {"Ecrivez la touche : ", field});
			optionPane.setMessageType(JOptionPane.QUESTION_MESSAGE);
			optionPane.setOptionType(JOptionPane.DEFAULT_OPTION);
			JDialog dialog = optionPane.createDialog(frame, "Choisir une touche");
			dialog.setVisible(true);
		}
		
		private JTextField getField() {
		    final JTextField field = new JTextField();
		    field.addKeyListener(new KeyListener() {

		        @Override
		        public void keyTyped(KeyEvent keyEvent) {

		        }

		        @Override
		        public void keyPressed(KeyEvent keyEvent) {
		        	if (iUpdateKey != null)
					{
						iUpdateKey.onKeyUpdate(keyEvent.getKeyCode());
						JOptionPane.getRootFrame().dispose();
					}
		        }

		        @Override
		        public void keyReleased(KeyEvent keyEvent) {
					field.setText(field.getText().substring(field.getText().length() - 1));
		        }
		    });
		    return field;
		}

	}
}
