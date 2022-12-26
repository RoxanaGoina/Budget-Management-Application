package application;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
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

public class MainMenuInterface {
private Button addItem=new Button("Creare item");
private Button createList=new Button("Creare listă de achiziții");
private Button vizual=new Button("Vizualizare liste  de achiziții");
private Button modif=new Button("Ștergere Item");
private Button vizualizerGraphic=new Button("Vizualizare grafic de achiziții ");
//private Button modifyGraphic=new Button("Modificare grafic de achiziții");
private Label header=new Label("Meniu principal");
private Button exportButton=new Button("Exportare liste");
private Button exitButton=new Button("Închide aplicatia");


public Scene showMainMenu(Stage primaryStage, double windowWidth, double windowHeight) {
	VBox root=new VBox();
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
	
		addItem.setId("addItem");
	createList.setId("createList");
	header.setId("header");
	vizual.setId("vizual");
	modif.setId("modif");
	exportButton.setId("export");
	vizualizerGraphic.setId("vizualizerGraphic");
//	modifyGraphic.setId("modifyGraphic");
	exitButton.setId("exitButton");
	root.setId("vbox");
	root.setMargin(addItem, new Insets(20, 0, 0, 0));
	root.setMargin(exitButton,new Insets(0,0,-40,0));
	addItem.setFocusTraversable(false);
	createList.setFocusTraversable(false);
	vizual.setFocusTraversable(false);
	modif.setFocusTraversable(false);
	vizualizerGraphic.setFocusTraversable(false);
	//modifyGraphic.setFocusTraversable(false);
	exitButton.setFocusTraversable(false);
	exportButton.setFocusTraversable(false);
	a.getStylesheets().add(getClass().getResource("styleMainMenuInterface.css").toExternalForm());
	root.setAlignment(Pos.CENTER);
	root.setSpacing(15);
	root.getChildren().addAll(addItem,modif,createList,vizual,vizualizerGraphic,exportButton,exitButton,header);
	addItem.setOnMouseClicked(e->{
		if(e.getButton()==MouseButton.PRIMARY)
			primaryStage.setScene((new CreateItemMenu()).create(primaryStage, windowWidth, windowHeight));
	});
	createList.setOnMouseClicked(e->{
		if(e.getButton()==MouseButton.PRIMARY)
			primaryStage.setScene((new CreateListMenu()).create(primaryStage, windowWidth, windowHeight));
	});
	modif.setOnMouseClicked(e->{
		if(e.getButton()==MouseButton.PRIMARY)
			primaryStage.setScene((new ModifyItem()).create(primaryStage, windowWidth, windowHeight));
	});
	
	exitButton.setOnMouseClicked(e -> {
		if(e.getButton() == MouseButton.PRIMARY)
			primaryStage.close();
	});
	vizual.setOnMouseClicked(e->{
		if(e.getButton()==MouseButton.PRIMARY)
			primaryStage.setScene((new FinalizeMenu().finalization(primaryStage, windowWidth, windowHeight)));
		
	});
	exportButton.setOnMouseClicked(e->{
		if(e.getButton()==MouseButton.PRIMARY)
			primaryStage.setScene((new ExportListMenu().export(primaryStage, windowWidth, windowHeight)));
		
	});
	return a;
	
}

}
