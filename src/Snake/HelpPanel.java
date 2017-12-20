package Snake;

import java.awt.FlowLayout;
import java.util.Properties;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class HelpPanel extends JPanel
{
	private static final long serialVersionUID = 1L;
	
	public HelpPanel()
	{
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		JLabel testLab = new JLabel("du coup sur ste page la on dit comment jouer objectif et tout le bordel\na la fin on met un rappel des touches des joueurs 1 et 2");
		testLab.setAlignmentX(CENTER_ALIGNMENT);
		add(testLab);
	}
}
