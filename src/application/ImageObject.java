package application;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class ImageObject extends GraphicObject
{
	public String src;
	
	public ImageObject()
	{
		src = "cat.png";
	}
	
	public ImageObject(String source)
	{
		src = source;
	}
	
	@Override
	void Draw(GraphicsContext gc)
	{
		Draw(gc, this.x, this.y);
	}
	
	public void Draw(GraphicsContext gc, double x, double y)
	{
		Draw(gc, x, y, this.width, this.height);
	}
	
	public void Draw(GraphicsContext gc, double x, double y, double width, double height)
	{
		Image img = new Image(this.getClass().getResource(src).toString(), width, height, true, false);
		gc.drawImage(img, x, y);
		
		this.x		= (int) x;
		this.y 		= (int) y;
		this.width 	= (int) img.getWidth();
		this.height = (int) img.getHeight();
	}
}