package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.io.FileInputStream; 
import java.io.FileNotFoundException; 
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
public class ModifyItem {
private Button deleteItem=new Button("Stergere item");
private Label header=new Label("Selecteaza");
private Label text=new Label("Stergere Item"); 
private Button backButton=new Button("Inapoi");
private ChoiceBox<String> choiceBox=new ChoiceBox<>();

public Scene create(Stage primaryStage, double windowWidth, double windowHeight) {
	VBox root=new VBox();
	Scene a=new Scene(root,windowWidth,windowHeight);
	MainMenu.noConnectionLabel.setId("noConnectionLabel");
	DataBaseOperations.checkConnection();
	MainMenu.noConnectionLabelSetProp();
	deleteItem.setId("deleteItem");
	deleteItem.setFocusTraversable(false);
	header.setId("header");
	text.setId("text");
	text.setFocusTraversable(false);
	header.setFocusTraversable(false);
	backButton.setId("backButton");
	backButton.setFocusTraversable(false);
	choiceBox.setId("choiceBox");
	choiceBox.setFocusTraversable(false);
	text.setTextFill(Color.color(1, 0, 0));
	
	
	try {
	FileInputStream input = new FileInputStream("./delete.png");
	Image image = new Image(input);
    ImageView imageView = new ImageView(image);
    imageView.setFitHeight(186); 
      imageView.setFitWidth(200); 
      imageView.setStyle("-fx-translate-x:680px; -fx-translate-y:70px");
    //imagineView.setY()
    root.getChildren().add(imageView);
} catch (FileNotFoundException e1) {
	System.out.println("Nu am gasit imaginea!");
	e1.printStackTrace();
}

	a.getStylesheets().add(getClass().getResource("styleModifyItem.css").toExternalForm());
	root.getChildren().addAll(header, deleteItem, choiceBox, backButton,text);
	backButton.setOnMouseClicked(e -> {
		if (e.getButton() == MouseButton.PRIMARY)
			primaryStage.setScene((new MainMenuInterface()).showMainMenu(primaryStage, windowWidth, windowHeight));
	});
	List<Item> ItemList=DataBaseOperations.listItem();
	for(Item i: ItemList) {
		choiceBox.getItems().add(i.getName());
	}
	deleteItem.setOnMouseClicked(e->{
		if(e.getButton()==MouseButton.PRIMARY)
		{	String name=choiceBox.getValue();
			DataBaseOperations.delete(name);
			
			
		}
	});
	
	return a;
	
}

}
