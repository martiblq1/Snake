package main;

import java.awt.Color;
import java.awt.Graphics;

public class Head
{
	private int progress = 0;
	protected int direction = 2, x = 600, y = 400, x_old = 600, y_old = 400;
	
	Head()
	{
		
	}
	
	public void tick()
	{
		progress += 5;
		
		if(progress == 40)
		{
			progress = 0;
			y_old = y;
			x_old = x;
			
			if(direction == 1)
			{
				y -= 40;
			}
			else if(direction == 2)
			{
				x -= 40;
			}
			else if(direction == 3)
			{
				x += 40;
			}
			else if(direction == 4)
			{
				y += 40;
			}
		}
	}
	
	public void render(Graphics g)
	{
		g.setColor(Color.GREEN);
		g.fillRect(x, y, 40, 40);
	}
	
	public int getdirection()
	{
		return direction;
	}
	
	public void setdirection(int direction)
	{
		this.direction = direction;
	}
	
	public int getx()
	{
		return x;
	}
	
	public int gety()
	{
		return y;
	}
	
	public int getx_old()
	{
		return x_old;
	}
	
	public int gety_old()
	{
		return y_old;
	}
}
