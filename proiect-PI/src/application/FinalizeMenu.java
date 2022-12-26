package application;

import java.awt.BorderLayout;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class FinalizeMenu {
	private Label title1=new Label("Vizualizare");
	private Label title2=new Label("Finalizare");
	private ChoiceBox choiceBox=new ChoiceBox();
	private TextArea textArea=new TextArea();
	private Label choiceBoxText=new Label("Selectează");
	private Button finalize=new Button("Finalizare");
	private Label price=new Label("Preț");
	private TextField textField=new TextField();
	private Text priceText=new Text();
	private Label data=new Label("Data");
	private DatePicker dp=new DatePicker();
	private Text textAreaText=new Text();
	private Button backButton =new Button("Înapoi");
	public FinalList createLocalFinalList(Map<String, List<Item>> map1) {
		map1=DataBaseOperations.getAllListsWithItems();
		List<Item> lista=new ArrayList<>();
		String title=(String)choiceBox.getValue();
		lista= map1.get(title);
		double price=Double.parseDouble(textField.getText());
		LocalDate myDate=dp.getValue();
		String format=myDate.format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
		//System.out.println(price);
		//System.out.println(title);
		//System.out.println(list);
		//System.out.println(format);
		ItemList list=new ItemList(0,lista,title);
		FinalList finalList=new FinalList(myDate,price,list);
		return finalList;
	}
	
	public Scene finalization(Stage primaryStage, double windowWidth, double windowHeight) {
		FlowPane root=new FlowPane();
		Scene a = new Scene(root, windowWidth, windowHeight);
		title1.setId("title1");
		title1.setFocusTraversable(false);
		title2.setId("title2");
		title2.setFocusTraversable(false);
		choiceBox.setId("choiceBox");
		choiceBox.setFocusTraversable(false);
		textArea.setId("TextArea");
		textArea.setFocusTraversable(false);
		choiceBoxText.setId("choiceBoxText");
		choiceBoxText.setFocusTraversable(false);
		finalize.setId("finalize");
		finalize.setFocusTraversable(false);
		price.setFocusTraversable(false);
		textField.setId("textField");
		textField.setFocusTraversable(false);
		priceText.setId("priceText");
		priceText.setFocusTraversable(false);
		data.setId("data");
		data.setFocusTraversable(false);
		dp.setId("dp");
		dp.setFocusTraversable(false);
		backButton.setId("backButton");
		backButton.setFocusTraversable(false);
		textArea.setEditable(false);
		textArea.setPrefHeight(230);
		textArea.setPrefWidth(330);
		textArea.setTranslateX(100);
		textArea.setTranslateY(-60);
		title1.setTextFill(Color.color(0.627, 0.125,0.941));
		title2.setTextFill(Color.color(0.275, 0.761,0.796));
		price.setTranslateX(-165);
		price.setTranslateY(-130);
		price.setFont(Font.font(21));
		textAreaText.setId("textAreaText");
		textAreaText.setFocusTraversable(false);
		textArea.setFont(Font.font(19));
		
		
		a.getStylesheets().add(getClass().getResource("styleFinalizeMenu.css").toExternalForm());
		root.getChildren().addAll(title1,title2,choiceBox,textArea,choiceBoxText,finalize,price,textField,priceText,data,dp,backButton);
		root.setAlignment(Pos.CENTER);
		backButton.setOnMouseClicked(e -> {
			if (e.getButton() == MouseButton.PRIMARY) {
				primaryStage.setScene((new MainMenuInterface()).showMainMenu(primaryStage, windowWidth, windowHeight));
		}});
		//LocalDate today=LocalDate.now();
		
		Map<String, List<Item>> map=DataBaseOperations.getAllListsWithItems();
		for(String s: map.keySet()) {
			choiceBox.getItems().add(s);
			
		}
		
		choiceBox.setOnAction(e->{
			textArea.clear();
			for(Item i: map.get(choiceBox.getValue())) {
			textArea.appendText(i.getName()+" | "+i.getItemType());
			textArea.appendText("\n");
			}
		});
		dp.setOnAction(e->{
			LocalDate myDate=dp.getValue();
			String format=myDate.format(DateTimeFormatter.ofPattern("MM/dd/yyyy"));
			System.out.println(format);
			if(myDate.isAfter(LocalDate.now()))
			{
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Problema calendar");
				alert.setHeaderText("Alege o data calendaristica corecta");
				alert.showAndWait();
			}
			
		});
		finalize.setOnMouseClicked(e->{
			String s=textField.getText();
			boolean isNumeric=true;
			try {
				Double d=Double.parseDouble(s);
			}  catch(NumberFormatException ex) {
				isNumeric=false;
			
			}
			if(isNumeric) {
				System.out.println("Pretul e numar");
			}
			else
			 {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Problema lista");
				alert.setHeaderText("Pretul nu este numar");
				alert.showAndWait();
			}
			if(choiceBox.getSelectionModel().isEmpty()) {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Problema lista");
				alert.setHeaderText("Selecteaza o lista");
				alert.showAndWait();
			}
			if(dp.getValue()==null) {
				Alert alert = new Alert(AlertType.WARNING);
				alert.setTitle("Problema lista");
				alert.setHeaderText("Selecteaza o data calendaristica");
				alert.showAndWait();
			}
			Map<String, List<Item>> map12=DataBaseOperations.getAllListsWithItems();
			System.out.println(createLocalFinalList(map12));	
			FinalList finalList=createLocalFinalList(map12);
			DataBaseOperations.addIntoTableFinalList(finalList,(String)choiceBox.getValue());
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Informational Alert");
			alert.setHeaderText("Lista a fost finalizata cu succes");
			alert.showAndWait();
		});
		return a;
	}
}
