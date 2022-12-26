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
import java.util.ArrayList;
import java.util.List;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
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
	private Label header = new Label("Creare lista");
	private Button addButton = new Button("Adăugare item");
	private Label name = new Label("Nume");
	private TextField nameField = new TextField();
	private Text textName = new Text();
	ChoiceBox<String> choiceBox = new ChoiceBox<>();
	private TextField selectField = new TextField();
	private Text selectText = new Text();
	private Label select = new Label("Selectează");
	private Button backButton = new Button("Înapoi");
	private Button createList = new Button("Creare");
	private TextArea textArea = new TextArea();

	public static ItemType toItemType(String type) {

		if (ItemType.Patiserie.toString().equals(type))
			return ItemType.Patiserie;
		if (ItemType.Fructe.toString().equals(type))
			return ItemType.Fructe;
		if (ItemType.Legume.toString().equals(type))
			return ItemType.Legume;
		if (ItemType.Lactate.toString().equals(type))
			return ItemType.Lactate;
		if (ItemType.Bauturi.toString().equals(type))
			return ItemType.Bauturi;
		if (ItemType.Combustibil.toString().equals(type))
			return ItemType.Combustibil;
		if (ItemType.Mezeluri.toString().equals(type))
			return ItemType.Mezeluri;
		if (ItemType.Decoratiuni.toString().equals(type))
			return ItemType.Decoratiuni;
		if (ItemType.Unelte.toString().equals(type))
			return ItemType.Unelte;
		if (ItemType.Dulciuri.toString().equals(type))
			return ItemType.Dulciuri;
		if (ItemType.Paste.toString().equals(type))
			return ItemType.Paste;
		if (ItemType.Altele.toString().equals(type))
			return ItemType.Altele;
		return null;
	}
	public boolean ListContainsItem(List<String> list,String a) {
		for(String i : list)
			if(i.equals(a))
				return true;
			else return false;
		return false;
	}
	public Scene create(Stage primaryStage, double windowWidth, double windowHeight) {
		primaryStage.setWidth(windowWidth);
		primaryStage.setHeight(windowHeight);
		FlowPane root = new FlowPane();
		Scene a = new Scene(root, windowWidth, windowHeight);
		MainMenu.noConnectionLabel.setId("noConnectionLabel");
		DataBaseOperations.checkConnection();
		MainMenu.noConnectionLabelSetProp();
		a.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent mouseEvent) {
				if (DataBaseOperations.checkConnection() == false) {
					primaryStage.setScene((new MainMenu()).showMenu(primaryStage, windowWidth, windowHeight));
					MainMenu.noConnectionLabel.setText("Nu exista conexiune spre baza de date");
					System.out.println("ai apasat pe root");
				} else
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
		addButton.setTranslateY(150);
		addButton.setTranslateX(-50);
		backButton.setTranslateX(-350);
		backButton.setTranslateY(300);
		createList.setTranslateX(-100);
		createList.setTranslateY(300);
		textArea.setEditable(false);
		textArea.setFont(Font.font(19));
		// textArea.setWrapText(true);
		textArea.setTranslateX(250);
		textArea.setTranslateY(-100);

		List<Item> ItemList = DataBaseOperations.listItem();
		List<Item> lista = new ArrayList<>();
		for (Item i : ItemList) {
			choiceBox.getItems().add(i.getId() + "|" + i.getName() + "|" + i.getItemType().toString());
		}
		a.getStylesheets().add(getClass().getResource("styleCreateList.css").toExternalForm());
		root.setAlignment(Pos.CENTER);
		root.getChildren().addAll(header, addButton, name, nameField, textName, choiceBox, select, backButton,
				createList, textArea);
		backButton.setOnMouseClicked(e -> {
			if (e.getButton() == MouseButton.PRIMARY)
				primaryStage.setScene((new MainMenuInterface()).showMainMenu(primaryStage, windowWidth, windowHeight));
		});

		List<String> list = new ArrayList<>();
		addButton.setOnMouseClicked(e -> {
			List<String> list1 = new ArrayList<>();
			if (e.getButton() == MouseButton.PRIMARY) {
				String item = choiceBox.getValue();
				
				if(ListContainsItem(list, item)==true) { 
					Alert alert = new Alert(AlertType.WARNING);
					alert.setTitle("Problema lista");
					alert.setHeaderText("Obiectul adaugat exista deja");
					alert.showAndWait();	
				}
				else
					{textArea.appendText(item + "\n");
					   list.add(item);
					}
			}
			System.out.println(list);
		});

		List<String> list1 = new ArrayList<>();
		createList.setOnMouseClicked(e -> {
			List<Item> listaIT = new ArrayList<>();
			if (e.getButton() == MouseButton.PRIMARY) {
				if (textArea.getText().isEmpty()) {
					Alert alert = new Alert(AlertType.WARNING);
					alert.setTitle("Problema lista");
					alert.setHeaderText("Lista este goala");
					alert.showAndWait();
				
				} else {
					String[] split = textArea.getText().split("\n");
					for (String s : split) {
						String s1[] = s.split("\\|");
						int id = Integer.parseInt(s1[0]);
						String name = s1[1];
						String itemType = s1[2];
						Item item1 = new Item(id, name, toItemType(itemType));
						listaIT.add(item1);
					}
					String title = nameField.getText();
					if (nameField.getText().isBlank()) {
						Alert alert = new Alert(AlertType.WARNING);
						alert.setTitle("Problema titlu");
						alert.setHeaderText("Numele listei e null");
						// alert.setContentText("Connect to the database successfully!");
						alert.showAndWait();
					
					}
					boolean isNameDiff = DataBaseOperations.isNameDifferent(title);
					if (isNameDiff == true) {
						DataBaseOperations.createList(listaIT, title);
					} else {

						Alert alert = new Alert(AlertType.WARNING);
						alert.setTitle("Problema titlu");
						alert.setHeaderText("Numele listei deja exista");
						// alert.setContentText("Connect to the database successfully!");

						alert.showAndWait();

					}

					// System.out.println(listaIT);
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Informare");
					alert.setHeaderText("Lista a fost creată cu succes");
					alert.showAndWait();
					primaryStage.setScene((new MainMenuInterface()).showMainMenu(primaryStage, windowWidth, windowHeight));
					// Select * from item inner join item_to_item_list on
					// item.id=item_to_item_list.item_id where
					// item_to_item_list.item_list_id=(select ID from ITEMLIST order by 1 desc limit
					// 1)
				}
			}
		});

		return a;
	}

	private char[] ArrayToString(String[] s1) {
		// TODO Auto-generated method stub
		return null;
	}

}
