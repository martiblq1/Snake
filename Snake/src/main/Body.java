package main;

import java.awt.Color;
import java.awt.Graphics;

public class Body
{
	protected int x, y, x_old, y_old;
	
	Body(int x, int y)
	{
		this.x = x;
		this.y = y;
		x_old = x;
		y_old = y;
	}
	
	public void tick()
	{
		
	}
	
	public void render(Graphics g)
	{
		g.setColor(Color.GREEN);
		g.fillRect(x, y, 40, 40);
	}
	
	public int getx()
	{
		return x;
	}
	
	public void setx(int x)
	{
		this.x = x;
	}
	
	public int gety()
	{
		return y;
	}
	
	public void sety(int y)
	{
		this.y = y;
	}
	
	public int getx_old()
	{
		return x_old;
	}
	
	public void setx_old(int x_old)
	{
		this.x_old = x_old;
	}
	
	public int gety_old()
	{
		return y_old;
	}
	
	public void sety_old(int y_old)
	{
		this.y_old = y_old;
	}
}
