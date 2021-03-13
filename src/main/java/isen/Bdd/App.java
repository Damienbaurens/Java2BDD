package isen.Bdd;

import java.io.IOException;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class App extends Application {
	
	private Stage primaryStage;
	private static Scene scene;
	
	@Override
	public void start(Stage primaryStage) throws IOException 
	{
		
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("IsenJava2BDD");
		
		showMainPage();
		
	}

   
	public void showMainPage()
	{
		FXMLLoader fxmlLoader = new FXMLLoader();
		fxmlLoader.setLocation(App.class.getResource("../view/MainPage.fxml"));
		try {
			AnchorPane mainPage = (AnchorPane) fxmlLoader.load();
			scene = new Scene(mainPage);
			this.primaryStage.setScene(scene);
			this.primaryStage.show();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Stage getPrimaryStage() 
	{
		return primaryStage;
	}

	 
	public static void main(String[] args) 
	{
		launch(args);
	}
	
	


}
