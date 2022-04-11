package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;

public class Main extends Application
{	
	static MainController myControllerHandle;
	
	@Override
	public void start(Stage primaryStage)
	{
		try
		{
			FXMLLoader loader = new FXMLLoader(getClass().getResource("Main.fxml"));
			BorderPane root = loader.load();
			
			myControllerHandle = (MainController)loader.getController();
			
			root.setOnMouseClicked(e -> { myControllerHandle.click(e);});
			
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			primaryStage.setTitle("Foofix");
			primaryStage.getIcons().add(new Image(this.getClass().getResource("cat.png").toString()));
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			primaryStage.show();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}	
	
	public static void main(String[] args)
	{
		launch(args);
	}
}
