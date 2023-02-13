package application;

import java.rmi.registry.LocateRegistry;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;
/**
 * 
 * @author Roxana Goina
 *
 */
public class GraphMenu {
HashMap<String, Double> map=DataBaseOperations.extractDataFromFinalList();
private NumberAxis xAxis=new NumberAxis();
private NumberAxis yAxis=new NumberAxis();
private LineChart lineChart = new LineChart(xAxis,yAxis);
private Button backButton=new Button("Inapoi");
private Label total=new Label("Total");
private Label sum=new Label();


public String convertToLocalDate(String date) {
	String[] split=date.split("-");
	return split[2];
}
public void css() {
	lineChart.setId("lineChart");
	lineChart.setFocusTraversable(false);
	backButton.setId("backButton");
	backButton.setFocusTraversable(false);
	total.setId("total");
	total.setFocusTraversable(false);
	sum.setId("sum");
	sum.setFocusTraversable(false);
	
}
/**
 * Metoda creeaza fereastra aplicatiei care permite vizualizarea graficului de achizitii
 * @param primaryStage
 * @param windowWidth
 * @param windowHeight
 * @return Metoda returneaza fereastra creata.
 */
public Scene show(Stage primaryStage, double windowWidth, double windowHeight) {
	FlowPane root=new FlowPane();
	Scene a = new Scene(root, windowWidth, windowHeight);
	xAxis.setLabel("Data");
	yAxis.setLabel("Pretul");
	XYChart.Series series=new XYChart.Series<>();
	series.setName("Cheltuieli acumulate");
	
	double totalPrice=0;
	for(String d:map.keySet()) {
		Integer month=Integer.parseInt(convertToLocalDate(d));
		//System.out.println(month);
		
		series.getData().add(new XYChart.Data((month),map.get(d)));
		totalPrice+=map.get(d);
		}
	//System.out.println(totalPrice);
	
	sum.setText(String.valueOf(totalPrice));
	lineChart.getData().addAll(series);
	root.getChildren().addAll(lineChart,backButton,total,sum);
	root.setAlignment(Pos.CENTER);
	css();
	a.getStylesheets().add(getClass().getResource("GraphMenu.css").toExternalForm());
	backButton.setOnMouseClicked(e -> {
		if (e.getButton() == MouseButton.PRIMARY)
			primaryStage.setScene((new MainMenuInterface()).showMainMenu(primaryStage, windowWidth, windowHeight));
	});
	return a;
}


}
