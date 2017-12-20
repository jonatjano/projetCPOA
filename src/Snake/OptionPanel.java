package Snake;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.vecmath.Color3f;


public class OptionPanel extends JPanel implements ChangeListener, ActionListener
{
	private int MAX_SNAKE = 8;
	private int MIN_WORLDSIZE = 10;
	private int MAX_WORLDSIZE = 30;
	private int MIN_SPEED = 1;
	private int MAX_SPEED = 5;

	private static List<Color3f> snakeColor;
	private List<SnakeOptionPan> snakePanList;

	private static final long serialVersionUID = 1L;
	private JSlider worldSizeSlide;
	private JSlider speedSlide;
	private JSlider NbPlayerSlide;
	private JSlider NbIASlide;

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

	public OptionPanel()
	{
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		Properties prop = MyEnv.getProperties();

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

		/**
		 * TODO enlever les // une fois que l'ia fonctionne
		 */
		add(new JLabel(" "));
		JLabel NbIALab = new JLabel("Nombre de serpents controllé par l'ordinateur");
		NbIALab.setAlignmentX(CENTER_ALIGNMENT);
		add(NbIALab);

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
		add(NbIASlide);
		add(new JLabel(" "));

		add(new JLabel(" "));
		JLabel snakeOptionLab = new JLabel("Options des serpents");
		snakeOptionLab.setAlignmentX(CENTER_ALIGNMENT);
		add(snakeOptionLab);

		JPanel snakeChooserPan = new JPanel();
		snakeChooserPan.setLayout(new GridLayout(2, 4));
		snakeChooserPan.setMaximumSize(new Dimension(500, 150));
		for (int i = 0; i < 8; i++)
		{
			JButton but = new JButton("Serpent " + (i + 1));
			but.addActionListener(this);
			but.setName(i + "");
			snakeChooserPan.add(but);
		}
		add(snakeChooserPan);
		add(new JLabel(" "));

		snakePanList = new ArrayList<SnakeOptionPan>();
		for (int i = 0; i < MAX_SNAKE; i++)
		{
			SnakeOptionPan innerSnakePan = new SnakeOptionPan(i);
			innerSnakePan.setVisible(false);
			snakePanList.add(innerSnakePan);
			add(innerSnakePan);
		}
		snakePanList.get(0).setVisible(true);

		add(new JLabel(" "));
		
		JButton confirm = new JButton("Confirmer");
		confirm.addActionListener(this);
		add(confirm);
	}

	public static Color3f getSnakeColor(String headName)
	{
		if (headName.matches("head[0-7]"))
		{
			return snakeColor.get(Integer.parseInt(headName.substring(4)));
		}
		return null;
	}

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

	@Override
	public void actionPerformed(ActionEvent e)
	{
		JButton src = (JButton) e.getSource();
		if (src.getText().startsWith("Serpent"))
		{
			int id = -1;
			try
			{
				id = Integer.parseInt(((JButton)e.getSource()).getName());
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
			{}
		}
		else if (src.getText().equals("Confirmer"))
		{
			Properties prop = new Properties();
			prop.put(MyEnv.PROP_WORLDSIZE, worldSizeSlide.getValue());
			prop.put(MyEnv.PROP_NB_SNAKE_PLAYER, NbPlayerSlide.getValue());
			prop.put(MyEnv.PROP_NB_SNAKE_IA, NbIASlide.getValue());
			prop.put(MyEnv.PROP_SPEED, (float) speedSlide.getValue());
			
			for (int i = 0; i < MAX_SNAKE; i++)
			{
				snakeColor.get(i).set(snakePanList.get(i).colorS);	
			}
			
			MyEnv.setProperties(prop);
			MyEnv.setPanel(new MainPanel());
		}
	}

	private class SnakeOptionPan extends JPanel
	{
		public Color colorS;
		
		public SnakeOptionPan(int snakeId)
		{
			setLayout(new BorderLayout());

			add(new JLabel("Option du serpent " + (snakeId + 1), JLabel.CENTER), BorderLayout.NORTH);
			
			JPanel labPan = new JPanel(new GridLayout(2, 3));
			
			labPan.add(new JLabel("Tourner à gauche", JLabel.CENTER));
			labPan.add(new JLabel("Couleur du serpent", JLabel.CENTER));
			labPan.add(new JLabel("Tourner à droite", JLabel.CENTER));

			JButton leftKeyBut = new JButton(KeyEvent.getKeyText(KeyController.getControl("head" + snakeId + "Left")));
			labPan.add(leftKeyBut);

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

			JButton rightKeyBut = new JButton(KeyEvent.getKeyText(KeyController.getControl("head" + snakeId + "Right")));
			labPan.add(rightKeyBut);
			
			add(labPan, BorderLayout.CENTER);
			
			add(new JLabel("                                          "), BorderLayout.WEST);
			add(new JLabel("                                          "), BorderLayout.EAST);
		}
	}
}
