package main;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class End extends JFrame
{
	private JLabel label;
	
	End()
	{
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(500, 80);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		label = new JLabel();
		label.setText("Oh no you died! What a shame...");
		label.setSize(500, 50);
		this.add(label);
	}
}
