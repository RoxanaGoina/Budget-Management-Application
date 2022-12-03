package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
//import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.List;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class CreateListMenu {
	private Label header=new Label("Creare lista");
	private Button addButton=new Button("Adaugare item");
	private Label name=new Label("Name");
	private TextField nameField=new TextField();
	private Text textName=new Text();
	ChoiceBox<String> choiceBox=new ChoiceBox<>();
	private TextField selectField=new TextField();
	private Text selectText=new Text();
	private Label select=new Label("Selecteaza");
	private Button backButton=new Button("Inapoi");
	private Button createList=new Button("Creare");
	private TextArea textArea=new TextArea();
	
	public Scene create(Stage primaryStage, double windowWidth, double windowHeight) {
		primaryStage.setWidth(windowWidth);
		primaryStage.setHeight(windowHeight);
		FlowPane root=new FlowPane();
		Scene a=new Scene(root,windowWidth,windowHeight);
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
		textArea.setId("textArea");
		textArea.setFocusTraversable(false);
		header.setId("header");
		addButton.setId("addButton");
		name.setId("name");
		nameField.setId("nameField");
		textName.setId("textName");
		choiceBox.setId("choiceBox");
		selectField.setId("select");
		select.setId("select");
		backButton.setId("backButton");
		backButton.setFocusTraversable(false);
		select.setFocusTraversable(false);
		selectText.setId("selectText");
		createList.setId("createList");
		createList.setFocusTraversable(false);
		selectField.setFocusTraversable(false);
		selectText.setFocusTraversable(false);
		header.setFocusTraversable(false);
		addButton.setFocusTraversable(false);
		name.setFocusTraversable(false);
		nameField.setFocusTraversable(false);
		textName.setFocusTraversable(false);
		choiceBox.setId("choiceBox");
		choiceBox.setFocusTraversable(false);
		nameField.setFont(Font.font(15));
		addButton.setTranslateY(250);
		addButton.setTranslateX(30);
		backButton.setTranslateX(10);
		backButton.setTranslateY(300);
		createList.setTranslateX(300);
		createList.setTranslateY(300);
		textArea.setEditable(false);
		textArea.setFont(Font.font(19));
		//textArea.setWrapText(true);
		textArea.setTranslateX(120);
		textArea.setTranslateY(40);
		
		List<Item> ItemList=DataBaseOperations.listItem();
		for(Item i: ItemList) {
			choiceBox.getItems().add(i.getName()+" | "+i.getItemType());
		}
		a.getStylesheets().add(getClass().getResource("styleCreateList.css").toExternalForm());
		root.getChildren().addAll(header,addButton,name,nameField,textName,choiceBox,select,backButton,createList,textArea);
		backButton.setOnMouseClicked(e->{
			if(e.getButton()==MouseButton.PRIMARY)
				primaryStage.setScene((new MainMenuInterface()).showMainMenu(primaryStage, windowWidth, windowHeight));
		});
		addButton.setOnMouseClicked(e->{
			if(e.getButton()==MouseButton.PRIMARY)
			{
				String item=choiceBox.getValue();
				textArea.appendText(item+"\n");
				
			}
		});
		createList.setOnMouseClicked(e->{
			if(e.getButton()==MouseButton.PRIMARY)
			{	String title=nameField.getText();
				DataBaseOperations.addItemTitle(title);
				
				
			}
		});
		
		
		return a;
	}
	
	
	
}