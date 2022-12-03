package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DataBaseOperations {
	private static String dbUser = "sa";
	private static String dbPass = "";
	
	public static void openConnection() {
		try {
			Class.forName("org.h2.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static boolean checkConnection() {
		try {
			Connection conn = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test",dbUser,dbPass);
		//	MainMenu.noConnectionLabel.setVisible(false);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			MainMenu.noConnectionLabel.setVisible(true);
			MainMenu.noConnectionLabel.setText("Nu exista conexiune spre baza de date");
			return false;
		}
	}
	

	public static void main(String args[]) {
		openConnection();
		checkConnection();
	}
	
	public static void add(Item a) {
		try {
			Connection conn = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test",dbUser,dbPass);
			PreparedStatement st = conn.prepareStatement("INSERT INTO ITEM(NAME,ITEMTYPE) VALUES (?,?)");
			//st.setInt(1, a.getId());
			st.setString(1, a.getName());
			st.setString(2, a.getItemType().toString());
			//st.setString(4, "Article");
			st.execute();
			System.out.println("Produs adaugat");
			
		} catch (SQLException e) {
			System.out.println("Nu s-a adaugat produsul");
			e.printStackTrace();
		}
	}
	public static void addItemTitle(String a) {
		Connection conn;
		try {
			conn = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test",dbUser,dbPass);
			PreparedStatement st = conn.prepareStatement("INSERT INTO ITEMLIST(TITLE) VALUES (?)");
			st.setString(1, a);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	public static void addItems(Item a) {
		Connection conn;
		try {
			conn = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test",dbUser,dbPass);
			PreparedStatement st = conn.prepareStatement("INSERT INTO ITEMLIST(TITLE) VALUES (?)");
			//st.setString(1, a);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	public static List<Item> listItem() {
		try {
		List<Item> ItemList=new ArrayList<>();
		Connection conn = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test",dbUser,dbPass);
		String querry="select * from ITEM";
		Statement st=conn.createStatement();
		ResultSet rs=st.executeQuery(querry);
		while(rs.next()) {
			int id=rs.getInt("ID");
			String Name=rs.getString("NAME");
			String ItemType=rs.getString("ITEMTYPE");
			Item a=new Item(id,Name,CreateItemMenu.toItemType(ItemType));
			ItemList.add(a);
		}
		st.close();
		return ItemList;
		} catch (SQLException e) {
			System.out.println("Nu s-a adaugat produsul");
			e.printStackTrace();
		}
		return null;
		
	}
	public static boolean itemAlreadyExists(Item a) {
		for(Item i :DataBaseOperations.listItem())
		{
			if(i.getName().equals(a.getName()) && i.getItemType().toString().equals(a.getItemType().toString()))
				return true;
		}
		return false;
		
	}
	public static void delete(String s) {
		try {
			Connection conn = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test",dbUser,dbPass);
			String querry="delete from item where NAME = '"+ s +"'";
			Statement pst = conn.createStatement();
			//pst.setString(1, s);
			pst.executeUpdate(querry);
			//ResultSet rs=pst.executeQuery();
			pst.close();
			} catch (SQLException e) {
				System.out.println("Nu s-a adaugat produsul");
				e.printStackTrace();
			}
	}
}
