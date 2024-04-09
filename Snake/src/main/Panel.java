package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.JPanel;

public class Panel extends JPanel
{
	Panel()
	{
		this.setDoubleBuffered(true);
		this.setBounds(0, 0, 800, 800);
		this.setPreferredSize(new Dimension(800, 800));
		this.setEnabled(true);
		this.setFocusable(true);//idk
		this.setVisible(true);
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, 800, 800);
		Game.gethead().render(g);
		Game.getapple().render(g);
		
		for(Body b : Game.getbodyList())
		{
			b.render(g);
		}
	}
}
