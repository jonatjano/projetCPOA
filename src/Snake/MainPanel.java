package Snake;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import simbad.gui.Simbad;

/**
 * panel principal
 * @author Jonathan Selle, Adam Bernouy
 *
 */
public class MainPanel extends JPanel implements ActionListener
{
	private static final long serialVersionUID = 1L;

	public MainPanel()
	{
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		JButton startBut = new JButton("Start");
		startBut.setAlignmentX(CENTER_ALIGNMENT);
		startBut.addActionListener(this);
		add(startBut);

		JButton helpBut = new JButton("Aide");
		helpBut.setAlignmentX(CENTER_ALIGNMENT);
		helpBut.addActionListener(this);
		add(helpBut);

		JButton optionBut = new JButton("Option");
		optionBut.setAlignmentX(CENTER_ALIGNMENT);
		optionBut.addActionListener(this);
		add(optionBut);

		JButton quitBut = new JButton("Close");
		quitBut.setAlignmentX(CENTER_ALIGNMENT);
		quitBut.addActionListener(this);
		add(quitBut);
	}

	public void actionPerformed(ActionEvent e)
	{
		switch (((JButton) e.getSource()).getText())
		{
			case "Start":
				MyEnv.setPanel(new Simbad(new MyEnv(), false));
			break;
			case "Aide":
				MyEnv.setPanel(new HelpPanel());
			break;
			case "Option":
				MyEnv.setPanel(new OptionPanel());
			break;
			case "Close":
				MyEnv.frame.dispose();
			break;

			default:
			break;
		}
	}
}
