package main;

import java.awt.BorderLayout;

import javax.swing.JFrame;

public class Window extends JFrame
{
	protected Panel panel;
	
	Window()
	{
		this.setVisible(true);
		this.setTitle("Snake game");
		this.setLayout(new BorderLayout());
		this.setSize(800, 800);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		panel = new Panel();
		this.add(panel);
		this.pack();
	}
	
	public Panel getpanel()
	{
		return panel;
	}
}
