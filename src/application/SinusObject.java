package application;

import javafx.scene.canvas.GraphicsContext;

public class SinusObject extends GraphicObject
{
	@Override public void Draw(GraphicsContext gc)
	{
		gc.beginPath();
		
		for(int x = 50; x <= 150; x++)
		{
			gc.lineTo(x, 100 - (50 * Math.sin((x / 100.0) * 2 * Math.PI)));
			gc.stroke();
		}
		
		gc.closePath();
	}
	
	public void Draw(GraphicsContext gc, double x, double y, double w, double phi)
	{
		gc.beginPath();
		
		for(int t = 0; t <= 100; t++)
		{
			gc.lineTo(t + x, y - (50 * Math.sin((t / 100.0) * w * Math.PI + phi)));
			gc.stroke();
		}
		
		gc.closePath();
	}
}
