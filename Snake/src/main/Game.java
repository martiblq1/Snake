package main;

import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;

public class Game implements Runnable
{
	private Window window;
	private Thread thread;
	protected static Head head;
	private Action upAction, leftAction, rightAction, downAction;
	private static Apple apple;
	private static Body body;
	private End end;
	
	protected static ArrayList<Body> bodyList = new ArrayList<>();
	private boolean running = false;
	private int frames_passed = 0;
	
	Game()
	{
		window = new Window();
		head = new Head();
		upAction = new UpAction();
		leftAction = new LeftAction();
		rightAction = new RightAction();
		downAction = new DownAction();
		window.getpanel().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("UP"), "upAction");
		window.getpanel().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("W"), "upAction");
		window.getpanel().getActionMap().put("upAction", upAction);
		window.getpanel().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("A"), "leftAction");
		window.getpanel().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("LEFT"), "leftAction");
		window.getpanel().getActionMap().put("leftAction", leftAction);
		window.getpanel().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("D"), "rightAction");
		window.getpanel().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("RIGHT"), "rightAction");
		window.getpanel().getActionMap().put("rightAction", rightAction);
		window.getpanel().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("S"), "downAction");
		window.getpanel().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("DOWN"), "downAction");
		window.getpanel().getActionMap().put("downAction", downAction);
		apple = new Apple();
		this.start();
	}
	
	@SuppressWarnings("deprecation")
	private void tick()
	{
		frames_passed++;
		
		head.tick();//8 frames for 1 square
		apple.tick();
		appleEaten();
		
		if(head.getx() > 800-40 || head.getx() < 0 || head.gety() > 800-40 || head.gety() < 0)
		{
			end = new End();
			thread.stop();
		}
		
		if(frames_passed == 8)
		{
			if(bodyList.size() > 0)
			{
				bodyList.get(0).tick();
				bodyList.get(0).setx_old(bodyList.get(0).getx());
				bodyList.get(0).sety_old(bodyList.get(0).gety());
				bodyList.get(0).setx(head.getx_old());
				bodyList.get(0).sety(head.gety_old());
				
				for(int i = 1; i < bodyList.size(); i++)
				{
					bodyList.get(i).tick();
					bodyList.get(i).setx_old(bodyList.get(i).getx());
					bodyList.get(i).sety_old(bodyList.get(i).gety());
					bodyList.get(i).setx(bodyList.get(i-1).getx_old());
					bodyList.get(i).sety(bodyList.get(i-1).gety_old());
				}
				
				die();
			}

			frames_passed = 0;
		}
	}
	
	@SuppressWarnings("deprecation")
	public void die()
	{
		for(int i = 0; i < bodyList.size(); i++)
		{
			if(head.getx() == bodyList.get(i).getx() && head.gety() == bodyList.get(i).gety())
			{
				end = new End();
				thread.stop();
				break;
			}
		}
	}
	
	public void appleEaten()
	{
		if(head.getx() == apple.getx() && head.gety() == apple.gety())
		{
			apple.eaten();
			
			if(bodyList.size() > 0)
			{	
				if(head.getdirection() == 1)
				{
					body = new Body(bodyList.get(bodyList.size()-1).getx(), bodyList.get(bodyList.size()-1).gety()+40);
					bodyList.add(body);
				}
				else if(head.getdirection() == 2)
				{
					body = new Body(bodyList.get(bodyList.size()-1).getx()+40, bodyList.get(bodyList.size()-1).gety());
					bodyList.add(body);
				}
				else if(head.getdirection() == 3)
				{
					body = new Body(bodyList.get(bodyList.size()-1).getx()-40, bodyList.get(bodyList.size()-1).gety());
					bodyList.add(body);
				}
				else if(head.getdirection() == 4)
				{
					body = new Body(bodyList.get(bodyList.size()-1).getx(), bodyList.get(bodyList.size()-1).gety()-40);
					bodyList.add(body);
				}
			}
			else
			{
				if(head.getdirection() == 1)
				{
					body = new Body(head.getx(), head.gety()+40);
					bodyList.add(body);
				}
				else if(head.getdirection() == 2)
				{
					body = new Body(head.getx()+40, head.gety());
					bodyList.add(body);
				}
				else if(head.getdirection() == 3)
				{
					body = new Body(head.getx()-40, head.gety());
					bodyList.add(body);
				}
				else if(head.getdirection() == 4)
				{
					body = new Body(head.getx(), head.gety()-40);
					bodyList.add(body);
				}
			}
		}
	}
	
	public class UpAction extends AbstractAction
	{
		@Override
		public void actionPerformed(ActionEvent arg0)
		{
			if(head.getdirection() != 4)
			{
				head.setdirection(1);
			}
		}	
	}
	
	public class LeftAction extends AbstractAction
	{
		@Override
		public void actionPerformed(ActionEvent arg0)
		{
			if(head.getdirection() != 3)
			{
				head.setdirection(2);
			}
		}	
	}
	
	public class RightAction extends AbstractAction
	{
		@Override
		public void actionPerformed(ActionEvent arg0)
		{
			if(head.getdirection() != 2)
			{
				head.setdirection(3);
			}
		}	
	}
	
	public class DownAction extends AbstractAction
	{
		@Override
		public void actionPerformed(ActionEvent arg0)
		{
			if(head.getdirection() != 1)
			{
				head.setdirection(4);
			}
		}	
	}
	//---------------------------------------------------------------------------------------
	@Override
	public void run()
	{
		int fps = 60;
		double timePerTick = 1000000000/fps;
		double delta = 0;
		long now;
		long lastTime = System.nanoTime();
		long timer = 0;
		int ticks = 0;
		
		while(running == true)
		{
			now = System.nanoTime();
			delta += (now-lastTime)/timePerTick;
			timer += now-lastTime;
			lastTime = now;
			if(delta >= 1)
			{
				tick();
				render();
				ticks++;
				delta--;
			}
			if(timer >= 1000000000)
			{
				System.out.println("Ticks and frames: " + ticks);
				ticks = 0;
				timer = 0;
			}
		}
		stop();
	}
	
	public synchronized void start()
	{
		if(running == true)
		{
			return;
		}
		running = true;
		thread = new Thread(this);
		thread.start();
	}
	
	private void render() 
	{
		window.getpanel().repaint();
	}
	
	public synchronized void stop()
	{
		if(running == false)
		{
			return;
		}
		try 
		{
			thread.join();
		} 
		catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
	}
	//---------------------------------------------------------------------------------------
	public static Head gethead()
	{
		return head;
	}
	
	public static Apple getapple()
	{
		return apple;
	}
	
	public static ArrayList<Body> getbodyList()
	{
		return bodyList;
	}
}
