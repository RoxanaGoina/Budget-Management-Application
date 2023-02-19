package application;
import java.util.HashMap;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class GraphDifferenceMenu {

HashMap<String, Double> map=DataBaseOperations.extractDataFromFinalList();
HashMap<String, Double> mapSecond=DataBaseOperations.extractDataFromFinalListSecondMonth();
HashMap<String, Double> mapThird=DataBaseOperations.extractDataFromFinalListThirdMonth();
final static String firstMonth="Luna curenta";
final static String secondMonth="1 luna";
final static String thirdMonth="2 luni";
Button backButton=new Button("Inapoi");
double priceFirst=0;
double priceSecond=0;
double priceThird=0;
//final static Label firstMonth= new Label ("Luna curenta");
//final static Label secondMonth=new Label("1 luna");
//final static Label thirdMonth= new Label("2 luni");

public String convertToLocalDate(String date) {
	String[] split=date.split("-");
	return split[2];
}
public Scene show(Stage primaryStage, double windowWidth, double windowHeight) {
	FlowPane root=new FlowPane();
	Scene a = new Scene(root, windowWidth, windowHeight);
	 final CategoryAxis xAxis = new CategoryAxis();
     final NumberAxis yAxis = new NumberAxis();
     final BarChart<String,Number> barChart = new BarChart<String,Number>(xAxis,yAxis);
     barChart.setTitle("Diferente lunare");      
     yAxis.setLabel("Pretul");
     root.getChildren().addAll(barChart,backButton);
 	root.setAlignment(Pos.CENTER);
 	XYChart.Series series1 = new XYChart.Series(); 
 	series1.setName("2 luni inainte");
    XYChart.Series series2 = new XYChart.Series(); 
    series2.setName("1 luna inainte");
    XYChart.Series series3 = new XYChart.Series(); 
    series3.setName("Luna curenta");
    barChart.getData().addAll(series1,series2,series3); 
    backButton.setId("backButton");
    backButton.setFocusTraversable(false);
    barChart.setId("barChart");
    barChart.setFocusTraversable(false);
    backButton.setOnMouseClicked(e -> {
		if (e.getButton() == MouseButton.PRIMARY)
			primaryStage.setScene((new MainMenuInterface()).showMainMenu(primaryStage, windowWidth, windowHeight));
	});
    for(String d:map.keySet()) {
			priceFirst+=map.get(d);
			}	
    for(String d:mapSecond.keySet()) {
		priceSecond+=mapSecond.get(d);
		}	
    for(String d:mapThird.keySet()) {
		priceThird+=mapThird.get(d);
		}
		series3.getData().add(new XYChart.Data(firstMonth,priceFirst));
		series2.getData().add(new XYChart.Data(secondMonth, priceSecond));
	 	series1.getData().add(new XYChart.Data(thirdMonth, priceThird)); 
    a.getStylesheets().add(getClass().getResource("styleGraphDifferenceMenu.css").toExternalForm());
    BackgroundFill background_fill = new BackgroundFill(Color.CADETBLUE, null, null);
	 Background background = new Background(background_fill);
	 root.setBackground(background);
	return a;
}
}

