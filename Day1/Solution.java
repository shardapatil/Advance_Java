package com.jdbc.main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class ShowAllName {
	
	//to get all the porducts
	public void getAllProducts()
	{
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/CDAC_Adv_Java?useSSL=false","root","root");
		
			Statement st = con.createStatement();
			
			ResultSet rs = st.executeQuery("SELECT * FROM PRODUCTS;");
			
			if(rs.isBeforeFirst())
			{
				while(rs.next())
				{
					System.out.println(rs.getInt(1)+" : "+
							rs.getString("name")+ " : "+ rs.getInt(3));
				}
			}
			else
			{
				System.out.println("no products found!");
			}
			
			con.close();
		}
		catch(ClassNotFoundException | SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	//search product
	public void SearchProducts(int id)
	{
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/CDAC_Adv_Java?useSSL=false","root","root");
		
			Statement st = con.createStatement();
			
			ResultSet rs = st.executeQuery("SELECT * FROM PRODUCTS WHERE ID="+id);
			
			if(rs.isBeforeFirst())
			{
				while(rs.next())
				{
					System.out.println(rs.getInt(1)+" : "+
							rs.getString("name")+ " : "+ rs.getInt(3));
				}
			}
			else
			{
				System.out.println("no products found!");
			}
			
			con.close();
		}
		catch(ClassNotFoundException | SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	//to insert new product
	public void addNewProduct(String name, int price)
	{
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/CDAC_Adv_Java?useSSL=false","root","root");
		
			Statement st = con.createStatement();
			
			String query = "INSERT INTO PRODUCTS(NAME,PRICE) VALUES('" + name + "',"+price +" );";
			int numRowsAffected = st.executeUpdate(query);
			
			ResultSet rs = st.executeQuery("select * from products order by id desc limit 1;");
			
			if(rs.isBeforeFirst())
			{
				while(rs.next())
				{
					System.out.println(rs.getInt(1)+" : "+
							rs.getString("name")+ " : "+ rs.getInt(3));
				}
			}
			else
			{
				System.out.println("no products found!");
			}
			
			con.close();
		}
		catch(ClassNotFoundException | SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	//update product 
	public void updateProduct(int id,String name, int price)
	{
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/CDAC_Adv_Java?useSSL=false","root","root");
		
			Statement st = con.createStatement();
			
			String query = "UPDATE PRODUCTS SET NAME = '"+name+"' , PRICE = "+price+" WHERE ID ="+id+";";
			int numRowsAffected = st.executeUpdate(query);
			
			ResultSet rs = st.executeQuery("SELECT * FROM PRODUCTS WHERE ID="+id);
			
			if(rs.isBeforeFirst())
			{
				while(rs.next())
				{
					System.out.println(rs.getInt(1)+" : "+
							rs.getString("name")+ " : "+ rs.getInt(3));
				}
			}
			else
			{
				System.out.println("no products found!");
			}
			
			con.close();
		}
		catch(ClassNotFoundException | SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	//to delete product based on product id
	public void deleteProduct(int id)
	{
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/CDAC_Adv_Java?useSSL=false","root","root");
		
			Statement st = con.createStatement();
			
			String query = "delete FROM PRODUCTS WHERE ID="+id;
			int numRowsAffected = st.executeUpdate(query);
			
			con.close();
		}
		catch(ClassNotFoundException | SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		ShowAllName s = new ShowAllName();
		
		Scanner sc = new Scanner(System.in);
		int choice=0;
		do
		{
			System.out.println("Enter the choice\n"
					+ "1. Show all records\n"
					+ "2. Search\n"
					+ "3. Insert\n"
					+ "4. Update\n"
					+ "5. Delete\n"
					+ "6. To Exit");
			choice = sc.nextInt();
			
			switch(choice)
			{
				case 1: 
					s.getAllProducts();
					break;
				case 2:
					System.out.println("Enter the ID you want to search: ");
					int id = sc.nextInt();
					s.SearchProducts(id);
					break;
				case 3:
					System.out.println("Enter product name");
					String name = sc.next();
					System.out.println("Enter product price: ");
					int price = sc.nextInt();
					s.addNewProduct(name, price);
					break;
				case 4:
					System.out.println("Enter the ID you want to update: ");
					int uid = sc.nextInt();
					System.out.println("Enter product name");
					String uname = sc.next();
					System.out.println("Enter product price: ");
					int uprice = sc.nextInt();
					s.updateProduct(uid, uname, uprice);
					break;
				case 5:
					System.out.println("Enter the ID you want to delete: ");
					int delid = sc.nextInt();
					s.deleteProduct(delid);
					System.out.println("data after deleting: ");
					s.getAllProducts();
					break;
				case 6:
					choice=0;
					break;
				default : 
					System.out.println("Enter valid option: ");
					break;
			}			
		}
		while(choice!=0);				
	}
}
