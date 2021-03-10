package isen.Bdd;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class App extends Application {
	
	private Stage primaryStage;
	private AnchorPane mainPage;
	
	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("IsenJava2BDD");
		
	}
	
   public void initMainPage() 
   {
        try {
            // Charge la MainPage depuis le fichier fxml
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(App.class.getResource("view/MainPage.fxml"));
            
            mainPage = (AnchorPane) loader.load();
            
            // montre la scène contenant la MainPage
            Scene scene = new Scene(mainPage);
            primaryStage.setScene(scene);
            primaryStage.show();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
   
   public Stage getPrimaryStage() 
   {
	   return primaryStage;
   }

	 
	public static void main(String[] args) {
		launch(args);
	}
}
