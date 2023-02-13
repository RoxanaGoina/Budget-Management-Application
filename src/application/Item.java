package application;

public class Item {
private int id;
private String name;
private ItemType itemType;
public Item(int id, String name, ItemType itemType) {
	this.id = id;
	this.name = name;
	this.itemType = itemType;
}
public Item() {
	
}
public Item(String name,ItemType itemType) {
	this.name=name;
	this.itemType=itemType;
}
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public ItemType getItemType() {
	return itemType;
}
public void setItemType(ItemType itemType) {
	this.itemType = itemType;
}
public String toString() {
	return "Id :"  + id + "  name :  " + name + "  itemType : " + itemType ;
}


}
