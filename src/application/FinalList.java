package application;

import java.time.LocalDate;

public class FinalList {
private LocalDate date;
private double price;
private ItemList list;
public FinalList(LocalDate date, double price, ItemList list) {
	this.date = date;
	this.price = price;
	this.list = list;
}
public FinalList() {

}
public LocalDate getDate() {
	return date;
}
public void setDate(LocalDate date) {
	this.date = date;
}
public double getPrice() {
	return price;
}
public void setPrice(double price) {
	this.price = price;
}
public ItemList getList() {
	return list;
}
public void setList(ItemList list) {
	this.list = list;
}
@Override
public String toString() {
	return "FinalList  Date : " + date + "  price : " + price + " list:   " + list ;
}


}
