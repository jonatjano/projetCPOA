package Snake;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Properties;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class HelpPanel extends JPanel implements ActionListener
{
	private static final long serialVersionUID = 1L;
	
	public HelpPanel()
	{
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		JLabel infoLab = new JLabel("SNAKE",JLabel.CENTER);
		infoLab.setAlignmentX(CENTER_ALIGNMENT);
		add(infoLab);
		
		infoLab = new JLabel("Il faut parcourir la map avec les touche gauche et droite ",JLabel.CENTER);
		add(infoLab);
		infoLab = new JLabel("pour pouvoir recuperer des fruits permettant de grandir.  ");
		add(infoLab);
		infoLab = new JLabel(" ",JLabel.CENTER);
		add(infoLab);
		infoLab = new JLabel("Le but du jeu est d'être le dernier en vie.               ",JLabel.CENTER);
		add(infoLab);
		infoLab = new JLabel("Pour se faire, il faut \"forcer\" les autre joueurs à     ",JLabel.CENTER);
		add(infoLab);
		infoLab = new JLabel("foncer dans votre queue ou dans le mur.",JLabel.CENTER);
		add(infoLab);
		infoLab = new JLabel(" ",JLabel.CENTER);
		add(infoLab);
		infoLab = new JLabel(" ",JLabel.CENTER);
		add(infoLab);
		infoLab = new JLabel("Que la force soit avec vous !",JLabel.CENTER);
		add(infoLab);
		infoLab = new JLabel(" ",JLabel.CENTER);
		add(infoLab);
		infoLab = new JLabel(" ",JLabel.CENTER);
		add(infoLab);
		infoLab = new JLabel(" ",JLabel.CENTER);
		add(infoLab);
		infoLab = new JLabel(" ",JLabel.CENTER);
		add(infoLab);
		
		JButton retButton = new JButton("Continuer");
		retButton.addActionListener(this);
		add(retButton);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		MyEnv.setPanel(new MainPanel());
		
	}
	
}
