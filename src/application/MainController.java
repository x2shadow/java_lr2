package application;

import java.net.URL;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.Vector;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.*;
import javafx.scene.control.*;
import javafx.scene.control.TextField;
import javafx.scene.image.*;
import javafx.scene.input.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Paint;

public class MainController implements Initializable
{
	double mouseX;
	double mouseY;
	Vector<GraphicObject> imgVector;
	GraphicsContext gc;
	
	@FXML
	private TextField txtbox;
	
	@FXML
	private Slider slider;

	@FXML
	private Slider slider2;
	
	@FXML
	private Label sinusValue;
	
	@FXML
	private ComboBox<String> comboBox;
	
	@FXML
	private ColorPicker colorPicker;
	
	@FXML
	private BorderPane root;
	
	@FXML
	private Canvas canva;
	
	@FXML
	public void click(MouseEvent e)
	{
		System.out.println(mouseX + " " + mouseY);
		
		if(e.getButton() == MouseButton.PRIMARY)
		{

			gc.setFill(colorPicker.getValue());
			gc.setStroke(colorPicker.getValue());
			
			if(comboBox.getValue() == "Картинка")
			{
				ImageObject imgObj = new ImageObject("cat_pink.png");
				imgVector.add(imgObj);
				imgObj.Draw(gc, mouseX, mouseY);
			}

			if(comboBox.getValue() == "Прямоугольник") 
				gc.fillRect(mouseX, mouseY, Double.parseDouble(txtbox.getText()), Double.parseDouble(txtbox.getText()));
			
			if(comboBox.getValue() == "Синус")
			{
				SinusObject pinus = new SinusObject();
				imgVector.add(pinus);
				pinus.Draw(gc, mouseX, mouseY, slider.getValue(), slider2.getValue());
			}
		}
		
		if(e.getButton() == MouseButton.SECONDARY)
		{
			int size = imgVector.size();
			
			for(int i = 0; i < size; i++)
			{
				GraphicObject obj = imgVector.get(i);
				
				if(obj.checkIn(mouseX, mouseY))
				{
					System.out.println("Deleted " + i);
					imgVector.remove(i);
					gc.clearRect(0, 0, canva.getWidth(), canva.getHeight());	//Чистим канвас полностью
					DrawVector(imgVector);										//Отрисовываем все объекты заново
					break;														//Удаляем только один объект
				}
			}
		}
	}
	
    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
    	imgVector = new Vector<GraphicObject>();
    	gc = canva.getGraphicsContext2D();
    	
    	canva.setOnMouseClicked(e -> 
    	{
    		mouseX = e.getX();
    		mouseY = e.getY();
    	});
    	
    	slider.valueProperty().addListener(new ChangeListener<Number>()
    	{
			@Override
			public void changed(ObservableValue<? extends Number> ov, Number old_val, Number new_val)
			{
				gc.clearRect(50, 30, 200, 200);
				sinusValue.setText(String.format("%.2f", new_val));
				SinusObject pinus = new SinusObject();
				pinus.Draw(gc, 50, 100, slider.getValue(), slider2.getValue());
			}
        });
    	
    	slider2.valueProperty().addListener(new ChangeListener<Number>()
    	{
			@Override
			public void changed(ObservableValue<? extends Number> ov, Number old_val, Number new_val)
			{
				gc.clearRect(50, 30, 200, 200);
				sinusValue.setText(String.format("%.2f", new_val));
				SinusObject pinus = new SinusObject();
				pinus.Draw(gc, 50, 100, slider.getValue(), slider2.getValue()/10.0);
			}
        });
    	
    	comboBox.getItems().removeAll(comboBox.getItems());
        comboBox.getItems().addAll("Синус", "Прямоугольник", "Картинка");
        comboBox.getSelectionModel().select(0);
    }
    
    public void DrawVector(Vector<GraphicObject> vector)
    {
    	for(GraphicObject o : vector) o.Draw(gc);
    }
}