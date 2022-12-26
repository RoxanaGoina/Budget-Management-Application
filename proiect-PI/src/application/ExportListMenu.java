package application;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class ExportListMenu {
private Label header=new Label("Export");
private Label title=new Label("Selectează");
private ChoiceBox choiceBox=new ChoiceBox();
private TextArea textArea=new TextArea();
private Button backButton=new Button("Înapoi");
private Button csvButton=new Button("Export   CSV");
private Button pdfButton=new Button("Export   PDF");
 
public void showList(Map<String, List<Item>> map1) {
		map1=DataBaseOperations.getAllListsForExport();
		List<Item> lista=new ArrayList<>();
		String title=(String)choiceBox.getValue();
		lista= map1.get(title);
}

public Scene export(Stage primaryStage, double windowWidth, double windowHeight) {
	FlowPane root=new FlowPane();
	Scene a = new Scene(root, windowWidth, windowHeight);
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
	a.getStylesheets().add(getClass().getResource("ExportListMenu.css").toExternalForm());
	root.getChildren().addAll(header,title,choiceBox,textArea,backButton,csvButton,pdfButton);
	root.setAlignment(Pos.CENTER);
	backButton.setOnMouseClicked(e -> {
		if (e.getButton() == MouseButton.PRIMARY) {
			primaryStage.setScene((new MainMenuInterface()).showMainMenu(primaryStage, windowWidth, windowHeight));
	}});
	Map<String, List<Item>> map=DataBaseOperations.getAllListsForExport();
		for(String s: map.keySet()) {
			choiceBox.getItems().add(s);
		choiceBox.setOnAction(e->{
		textArea.clear();
		//Map<String, List<Item>> map=DataBaseOperations.getAllListsWithItems();
		for(Item i: map.get(choiceBox.getValue())) {
		textArea.appendText(i.getName()+" | "+i.getItemType());
		textArea.appendText("\n");
		}
	});
		}
	return a;
}
	

}

