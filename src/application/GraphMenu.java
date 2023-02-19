package application;

import java.rmi.registry.LocateRegistry;
import java.sql.Date;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.itextpdf.text.Font;
import java.util.Currency;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;

import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.FlowPane;
import javafx.scene.paint.Color;
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
private static final DecimalFormat df = new DecimalFormat("0.00");
private Label money=new Label("Moneda");


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
	money.setId("money");
	money.setFocusTraversable(false);
	
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
	total.setTextFill(Color.DEEPPINK);
	sum.setTextFill(Color.DARKVIOLET);
	BackgroundFill background_fill = new BackgroundFill(Color.CADETBLUE, null, null);
	 Background background = new Background(background_fill);
	 root.setBackground(background);
	double totalPrice=0;
	for(String d:map.keySet()) {
		Integer month=Integer.parseInt(convertToLocalDate(d));
		//System.out.println(month);
		
		series.getData().add(new XYChart.Data((month),map.get(d)));
		totalPrice+=map.get(d);
		}
	double Price=totalPrice;
	//System.out.println(totalPrice);
	String euro = "\u20ac";
	 String pound = "\u00a3";
	//sum.setText(String.valueOf(df.format(totalPrice/4.9 )+euro));
	ChoiceBox<String> choiceBox=new ChoiceBox<>();
	
	
	choiceBox.getItems().add("Euro");
	choiceBox.getItems().add("Lira sterlina");
	choiceBox.getItems().add("USD");
	choiceBox.getItems().add("Lira turceasca");
	choiceBox.setId("choiceBox");
	choiceBox.getItems().add("Franci elvetieni");
	choiceBox.getItems().add("Yen japonez");
	choiceBox.getItems().add("Forinț");
	choiceBox.setFocusTraversable(false);
	choiceBox.setOnAction(e->{
		String choice=choiceBox.getValue();
		if(choice=="Euro")
			sum.setText("-> "+String.valueOf(df.format(Price/4.9 )+" "+euro));	
		else if(choice=="USD")
			sum.setText("-> $ "+String.valueOf(df.format(Price/4.59)));	
		else if(choice=="Lira sterlina")
			sum.setText("->"+String.valueOf(df.format(Price/5.53)+"  "+ pound));	
		else if(choice=="Lira turceasca")
			sum.setText("-> "+String.valueOf(df.format(Price*4.10)+" "+"₺"));
		else if(choice=="Franci elvetieni")
		sum.setText("->"+String.valueOf(df.format(Price/4.96)+" "+"₣"));
		else if(choice=="Forinț")
			sum.setText("->"+String.valueOf(df.format(Price*78.13)+" Ft"));
		else if(choice=="Yen japonez")
			sum.setText("->"+String.valueOf(df.format(Price*29.20)+" ¥"));
		
	});
	
//	Currency current=Currency.getInstance("");
	sum.setText("->"+String.valueOf(totalPrice)+"   RON");
	lineChart.getData().addAll(series);
	money.setText("Moneda");
	money.setTextFill(Color.DARKVIOLET);
	
	root.getChildren().addAll(lineChart,backButton,total,sum,choiceBox,money);
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
