package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
//import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class MainMenu {
	// GUI objects
	private Button meniuButton=new Button("Meniu Principal");
	private Button exitButton=new Button("Închide aplicatia");
	private Label header = new Label("Aplicație pentru gestionarea bugetului");
	
	
	public Scene showMenu(Stage primaryStage, double windowWidth, double windowHeight) {
		VBox root=new VBox();
		Scene a=new Scene(root,windowWidth,windowHeight);
		 BackgroundFill background_fill = new BackgroundFill(Color.MEDIUMTURQUOISE, null, null);
		 Background background = new Background(background_fill);
		 root.setBackground(background);
		MainMenu.noConnectionLabel.setId("noConnectionLabel");
		DataBaseOperations.checkConnection();
		MainMenu.noConnectionLabelSetProp();
		a.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
		    @Override
		    public void handle(MouseEvent mouseEvent) {
		    	if(DataBaseOperations.checkConnection() == false) {
					primaryStage.setScene((new MainMenu()).showMenu(primaryStage,windowWidth, windowHeight));
					MainMenu.noConnectionLabel.setText("Nu exista conexiune spre baza de date");
					System.out.println("ai apasat pe root");
				}
				else
					MainMenu.noConnectionLabel.setText("");
		    }
		});
		a.setFill(Color.AQUA);
		meniuButton.setId("meniuButton");
		exitButton.setId("exitButton");
		header.setId("header");
		meniuButton.setFocusTraversable(false);
		exitButton.setFocusTraversable(false);
		header.setTextFill(Color.SNOW);
		header.setFont(Font.font(50));
		a.getStylesheets().add(getClass().getResource("styleMainMenu.css").toExternalForm());
		root.setAlignment(Pos.CENTER);
		a.setFill(Color.BLACK);
		meniuButton.setOnMouseClicked(e->{
			primaryStage.setScene((new MainMenuInterface()).showMainMenu(primaryStage, windowWidth, windowHeight));
		});
		root.getChildren().addAll(meniuButton,exitButton,noConnectionLabel,header);
		exitButton.setOnMouseClicked(e -> {
			if(e.getButton() == MouseButton.PRIMARY)
				primaryStage.close();
		});

		//a.setFill(Color.BLUE);
		//a.setFill(null);
		
		return a;
		
}

	public static Label noConnectionLabel = new Label();

	public static void noConnectionLabelSetProp() {
		MainMenu.noConnectionLabel
				.setStyle("-fx-font-size:25px;\r\n" + "	-fx-font-style: italic;\r\n" + "	-fx-translate-y:240px;");
	}
}
