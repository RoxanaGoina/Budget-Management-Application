package application;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
/**
 * 
 * @author Roxana Goina
 *
 */
public class ExportListMenu {
private Label header=new Label("Export");
private Label title=new Label("Selectează");
private ChoiceBox choiceBox=new ChoiceBox();
private TextArea textArea=new TextArea();
private Button backButton=new Button("Înapoi");
private Button csvButton=new Button("Export   CSV");
private Button pdfButton=new Button("Export   PDF");
private Button searchByPattern=new Button("Cauta");
private TextField patternImput=new TextField(); 
private Label search=new Label("Cauta");
/**
 * Metoda contribuie la crearea unui choiceBox/ComboBox care ii permite utilizatorului sa aleaga lista pe care o doreste exportata
 * @param map1
 */
public void showList(Map<String, List<Item>> map1) {
		map1=DataBaseOperations.getAllListsForExport();
		List<Item> lista=new ArrayList<>();
		String title=(String)choiceBox.getValue();
		lista= map1.get(title);
}
/**
 * Suprascrierea operatorului de comparare. Acesta se foloseste in cazul listelor,deoarece obiectele din cadrul listelor sunt grupate pe categorii.
 */
Comparator<Item> compare = new Comparator<>() {
	public int compare(Item a1, Item a2) {
		if (a1.getItemType().equals(a2.getItemType())) {
			return a1.getName().compareTo(a2.getName());
		} else {
			return a1.getItemType().compareTo(a2.getItemType());
		}
	}
};
/**
 * Metoda ia continutul din textArea si il stocheaza intr-o lista locala.
 * @param textArea -continutul acestei zone reprezinta obiectele care vor constitui lista
 * @return Metoda returneaza lista care contine obiectele din TextArea.
 */
public List<Item> getTextAreaText(TextArea textArea){
	String[] split = textArea.getText().split("\n");
	List<Item> list=new ArrayList<>();
	for (String s : split) {
		String s1[] = s.split("\\|");
		String name = s1[0];
		String itemType = s1[1];
		Item item1 = new Item(name, CreateListMenu.toItemType(itemType));
		list.add(item1);
		
}
	Collections.sort(list,compare);
	
	return list;
}
public void css() {
	header.setId("header");
	header.setFocusTraversable(false);
	title.setId("title");
	title.setFocusTraversable(false);
	choiceBox.setId("choiceBox");
	choiceBox.setFocusTraversable(false);
	textArea.setId("textArea");
	textArea.setFocusTraversable(false);
	backButton.setId("backButton");
	backButton.setFocusTraversable(false);
	csvButton.setId("csvButton");
	csvButton.setFocusTraversable(false);
	pdfButton.setId("pdfButton");
	pdfButton.setFocusTraversable(false);
	textArea.setPrefColumnCount(250);
	textArea.setTranslateX(150);
	textArea.setTranslateY(-90);
	header.setTextFill(Color.color(0.50, 0.047,0.66));
	title.setTextFill(Color.color(0.013, 0.64, 0.624));
	textArea.setEditable(false);
	searchByPattern.setId("searchByPattern");
	patternImput.setId("patternImput");
	search.setId("search");
}
/**
 * Metoda creeaza fereastra aplicatiei care ii permite utilizatorului sa exporte listele de achizitii
 * @param primaryStage
 * @param windowWidth
 * @param windowHeight
 * @return Metoda returneaza fereastra creata.
 */
public Scene export(Stage primaryStage, double windowWidth, double windowHeight) {
	FlowPane root=new FlowPane();
	Scene a = new Scene(root, windowWidth, windowHeight);
	css();
	a.getStylesheets().add(getClass().getResource("ExportListMenu.css").toExternalForm());
	root.getChildren().addAll(header,title,choiceBox,textArea,backButton,csvButton,pdfButton,search,patternImput,searchByPattern);
	root.setAlignment(Pos.CENTER);
	backButton.setOnMouseClicked(e -> {
		if (e.getButton() == MouseButton.PRIMARY) {
			primaryStage.setScene((new MainMenuInterface()).showMainMenu(primaryStage, windowWidth, windowHeight));
	}});
	csvButton.setTranslateX(430);
	csvButton.setTranslateY(195);
	patternImput.setTranslateX(-270);
	patternImput.setPrefHeight(50);
	//searchByPattern.setPrefHeight(20);
	patternImput.setFont(Font.font("Verdana", 11.7));
	Map<String, List<Item>> map=DataBaseOperations.getAllListsForExport();
		//for(String s: map.keySet()) {
			//choiceBox.getItems().add(s);
		//choiceBox.setOnAction(e->{
	//	textArea.clear();
	//	Map<String, List<Item>> map=DataBaseOperations.getAllListsWithItems();
	
		
		searchByPattern.setOnAction(e->{
			String pattern=patternImput.getText();
			choiceBox.getItems().clear();
			
			if(pattern.equals("")) {
				for(String s: map.keySet()) 
					choiceBox.getItems().add(s);
				}
			else {
				for(String s: map.keySet()) 
					if(s.contains(pattern))
					choiceBox.getItems().add(s);
				
			}
		});
		
		choiceBox.setOnAction(e->{
			textArea.clear();
			for(Item i: map.get(choiceBox.getValue())) {
				textArea.appendText(i.getName()+"|"+i.getItemType());
				textArea.appendText("\n");
		}
			
		});
		
		
	pdfButton.setOnAction(e->{
		int ok=0;
		if(choiceBox.getSelectionModel().isEmpty()) {
			ok=1;
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Problema lista");
			alert.setHeaderText("Selecteaza o lista");
			alert.showAndWait();
		}
		System.out.println(getTextAreaText(textArea));
		String title=(String) choiceBox.getValue();
		if(ok==0) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Alerta informationala");
			alert.setHeaderText("Lista a fost exportata cu succes in formatul dorit");
			alert.showAndWait();	
		}
		
		ExportPDF.exportList(getTextAreaText(textArea),title);
	});
	csvButton.setOnAction(e->{
		int ok=0;
		if(choiceBox.getSelectionModel().isEmpty()) {
			ok=1;
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Problema lista");
			alert.setHeaderText("Selecteaza o lista");
			alert.showAndWait();
		}
		
		System.out.println(getTextAreaText(textArea));
		String title=(String) choiceBox.getValue();
		ExportCSV.exportList(getTextAreaText(textArea), title);
		if(ok==0) {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Alerta informationala");
			alert.setHeaderText("Lista a fost exportata cu succes in formatul dorit");
			alert.showAndWait();
		}
	});
	return a;
}
	

}

