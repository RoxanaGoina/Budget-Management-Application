package application;

import java.sql.SQLException;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.scene.Parent;

public class Main extends Application {
	
	
	public void start(Stage primaryStage) {
		OpenApp A = new OpenApp(900,600);
		A.openWindow(primaryStage);
	}
	
	public static void main(String[] args)  {
		
		launch(args);
	
	}
}



