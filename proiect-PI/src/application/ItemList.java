package application;
import java.util.*;
public class ItemList {
private long id;
private List<Item> list;
private String title;
public ItemList(long id, List<Item> list, String title) {
	this.id = id;
	this.list = list;
	this.title = title;
}
public ItemList() {
}
public long getId() {
	return id;
}
public void setId(long id) {
	this.id = id;
}
public List<Item> getList() {
	return list;
}
public void setList(List<Item> list) {
	this.list = list;
}
public String getTitle() {
	return title;
}
public void setTitle(String title) {
	this.title = title;
}

public String toString() {
	return "ItemList  Id :  " + id + "  list : " + list + " title  : " + title ;
}


}
