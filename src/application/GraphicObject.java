package application;

import java.util.Random;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public abstract class GraphicObject
{
	protected int x, y;
	protected Color color;
	protected int width, height;
	
	public GraphicObject()
	{
		Random random = new Random();
		this.x = random.nextInt(100);
		this.y = random.nextInt(100);
		this.color = new Color(random.nextDouble(), random.nextDouble(), random.nextDouble(), 1);
		this.height = random.nextInt(100);
		this.width = random.nextInt(100);
	}
	
	public GraphicObject(int x, int y)
	{
		Random random = new Random();
		this.x = x;
		this.y = y;
		this.color = new Color(random.nextDouble(), random.nextDouble(), random.nextDouble(), 1);
		this.height = random.nextInt(100);
		this.width = random.nextInt(100);
	}
	
	public GraphicObject(int x, int y, Color color, int width, int height)
	{
		this.x = x;
		this.y = y;
		this.color = color;
		this.width = width;
		this.height = height;
	}
	
	abstract void Draw(GraphicsContext gc);
	
	public boolean checkIn(double x, double y) 
	{
		return (x > this.x) && (x < (this.x + width)) && (y > this.y) && (y < (this.y + height));
	}
}
