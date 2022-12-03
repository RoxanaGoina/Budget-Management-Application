package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class CreateItemMenu {
	private Label label1 = new Label("Crearea unui item nou");
	private Label label2 = new Label("ID");
	private Button createButton = new Button("Creează");
	private TextField field = new TextField();
	private Text text = new Text();
	private TextField t1 = new TextField();
	private ChoiceBox<String> choicebox = new ChoiceBox<>();
	private Label l4 = new Label("Type");
	private Button backButton = new Button("Inapoi");

	public static ItemType toItemType(String type) {

		if (ItemType.Patiserie.toString().equals(type))
			return ItemType.Patiserie;
		if (ItemType.Fructe.toString().equals(type))
			return ItemType.Fructe;
		if (ItemType.Legume.toString().equals(type))
			return ItemType.Legume;
		if (ItemType.ProduseLactate.toString().equals(type))
			return ItemType.ProduseLactate;
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

	public Scene create(Stage primaryStage, double windowWidth, double windowHeight) {
		HBox root = new HBox();
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
		label1.setId("label1");

		field.setFocusTraversable(false);

		text.setFocusTraversable(false);
		label2.setId("label2");
		TextField textField = new TextField();
		textField.setFocusTraversable(false);
		t1.setId("t1");
		t1.setFocusTraversable(false);
		Label label3 = new Label("Name");
		label3.setId("label3");
		textField.setId("textField");
		// label3.setFocusTraversable(false);
		createButton.setId("createButton");
		createButton.setFocusTraversable(false);
		createButton.setTranslateY(450);
		createButton.setTranslateX(130);

		// root.setAlignment(Pos.CENTER);
		field.setTranslateX(0);
		field.setTranslateY(0);
		t1.setFont(Font.font(15));
		label3.setFont(Font.font(15));
		textField.setFont(Font.font(15.2));

		l4.setId("l4");
		l4.setFocusTraversable(false);
		choicebox.setValue("Alege categoria");
		choicebox.getItems().add(ItemType.Fructe.toString());
		choicebox.getItems().add(ItemType.Legume.toString());
		choicebox.getItems().add(ItemType.ProduseLactate.toString());
		choicebox.getItems().add(ItemType.Mezeluri.toString());
		choicebox.getItems().add(ItemType.Patiserie.toString());
		choicebox.getItems().add(ItemType.Bauturi.toString());
		choicebox.getItems().add(ItemType.Dulciuri.toString());
		choicebox.getItems().add(ItemType.Paste.toString());
		choicebox.getItems().add(ItemType.Unelte.toString());
		choicebox.getItems().add(ItemType.Combustibil.toString());
		choicebox.getItems().add(ItemType.Altele.toString());
		choicebox.setId("choicebox");
		choicebox.setFocusTraversable(false);
		a.getStylesheets().add(getClass().getResource("styleItemMenu.css").toExternalForm());
		backButton.setId("backButton");
		backButton.setFocusTraversable(false);
		backButton.setTranslateY(450);
		backButton.setTranslateX(-800);
		/*
		 * try { FileInputStream input; input = new
		 * FileInputStream("D:\\JavaStuff\\proiect-PI\\createItem.jpg"); Image image =
		 * new Image(input); ImageView imageView = new ImageView(image);
		 * imageView.setFitHeight(286); imageView.setFitWidth(350);
		 * imageView.setStyle("-fx-translate-x:680px; -fx-translate-y:50px");
		 * //imagineView.setY() root.getChildren().add(imageView); } catch
		 * (FileNotFoundException e1) { System.out.println("Nu am gasit imaginea!");
		 * e1.printStackTrace(); }
		 */
		root.getChildren().addAll(label1, text, textField, label3, createButton, choicebox, l4, backButton);
		backButton.setOnMouseClicked(e -> {
			if (e.getButton() == MouseButton.PRIMARY)
				primaryStage.setScene((new MainMenuInterface()).showMainMenu(primaryStage, windowWidth, windowHeight));
		});
		createButton.setOnMouseClicked(e -> {
			if (e.getButton() == MouseButton.PRIMARY) {
				// int id=Integer.parseInt(t1.getText());
				String name = textField.getText();
				String type = choicebox.getValue();
				System.out.println(name + " " + type);
				t1.clear();
				textField.clear();
				Item item = new Item(0, name, toItemType(type));
				// List<Item> List = DataBaseOperations.listItem();
				if (DataBaseOperations.listItem().size() >= 1) {
					if (DataBaseOperations.itemAlreadyExists(item) == true)
						System.out.println("Produsul deja exista");
					else {
						DataBaseOperations.add(item);
						System.out.println("CEVA");
						// List=DataBaseOperations.listItem();

					}
				} else
					DataBaseOperations.add(item);

			}
		});

		return a;
	}

}
