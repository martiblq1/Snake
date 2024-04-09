package main;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Apple
{
	Random random = new Random();
	
	protected int x, y;
	
	private boolean intersects = false;
	
	Apple()
	{
		eaten();
	}
	
	public void eaten()
	{
		intersects = false;
		x = (random.nextInt(20))*40;
		y = (random.nextInt(20))*40;
		
		if(x == Game.gethead().getx() && y == Game.gethead().gety())
		{
			intersects = true;
		}
		
		for(int i = 0; i < Game.getbodyList().size(); i++)
		{
			if(x == Game.getbodyList().get(i).getx() && y == Game.getbodyList().get(i).gety())
			{
				intersects = true;
			}
		}
		
		if(intersects)
		{
			eaten();
		}
	}
	
	public void tick()
	{
		
	}
	
	public void render(Graphics g)
	{
		g.setColor(Color.RED);
		g.fillOval(x, y, 40, 40);
	}
	
	public int getx()
	{
		return x;
	}
	
	public int gety()
	{
		return y;
	}
}
