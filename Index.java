package com.tap;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Index {
	public static void display(Connection connection, PreparedStatement prestatement, ResultSet res) throws SQLException {
		String sql = "SELECT * from employee";
		prestatement = connection.prepareStatement(sql);
		res = prestatement.executeQuery(sql);
		System.out.println(".........................................................................");
		while(res.next()) {
			int id=res.getInt("id");
			String name=res.getString("name");
			String email=res.getString("email");
			String dept=res.getString("department");
			int salary=res.getInt("salary");
			
			//System.out.println(id+", "+name+", "+email+", "+dept1+", "+salary);
			System.out.printf("|%-3d | %-10s | %-20s | %-15s | %-10d |\n",id,name,email,dept,salary);
		} 
		System.out.println(".........................................................................");
		
	}
	
	 public static void insert(Connection connection, PreparedStatement prestatement, ResultSet res) throws SQLException {
		Scanner scanner = new Scanner(System.in);
		String sql1 = "INSERT into `employee` (id,name,email,department,salary) values(?, ?, ?, ?, ?)";
		prestatement = connection.prepareStatement(sql1);
		
		System.out.println("The current table is"); 
		display(connection, prestatement, res); 
		String s="yes";
		do {
			System.out.println("Enter the id: ");
			int id = scanner.nextInt();
			prestatement.setInt(1, id);
			System.out.println("Enter the name: ");
			String name = scanner.next();
			prestatement.setString(2, name);
			System.out.println("Enter the email: ");
			String email = scanner.next();
			prestatement.setString(3, email);
			System.out.println("Enter the department: ");
			String dept = scanner.next();
			prestatement.setString(4, dept);
			System.out.println("Enter the salary: ");
			int salary = scanner.nextInt();
			prestatement.setInt(5, salary);
			
		    int i = prestatement.executeUpdate();
		    System.out.println(i);
		    display(connection,prestatement, res);
		    
		    System.out.println("Do you want enter more employees? (yes/no)");			
		}while(s.equalsIgnoreCase(scanner.next()));
	
		System.out.println("Do you want to perform other operation? (yes/no)");
		
	}
	 
	 public static void update(Connection connection, PreparedStatement prestatement, ResultSet res) throws SQLException {
		Scanner scanner = new Scanner(System.in);
		String sql = "UPDATE `employee` SET `salary` =`salary`+ ? WHERE `department`=?";
		String s="yes", n="nO";
		display(connection,prestatement, res);
		do {
			System.out.println("Enter 1 to Update the ename"); 
			System.out.println("Enter 2 to Update the email"); 
			System.out.println("Enter 3 to Update the department");
			System.out.println("Enter 4 to Update the salary");
			
			int choice = scanner.nextInt(); 
			switch(choice) { 
			case 1: 
				String sql1 = "UPDATE employee SET name = ? where id = ?"; 
				prestatement = connection.prepareStatement(sql1);
				System.out.println("Enter the name"); 
				String name = scanner.next(); 
				System.out.println("Enter the id where you want to update");
				int id1 = scanner.nextInt();
				prestatement.setString(1, name);
				prestatement.setInt(2, id1); 
				prestatement.executeUpdate(); 
				System.out.println("Data Updated Successfully. Do you want to update any other? (yes/no)");
				break; 
			case 2:
				String sql2 = "UPDATE employee SET email = ? where id = ?"; 
				prestatement = connection.prepareStatement(sql2); 
				System.out.println("Enter the email: ");
				String email = scanner.next(); 
				System.out.println("Enter the id where you want to update");
				int id2 = scanner.nextInt(); 
				prestatement.setString(1, email);
				prestatement.setInt(2, id2);
				prestatement.executeUpdate(); 
				System.out.println("Data Updated Successfully. Do you want to update any other? (yes/no)");
				break;
			case 3:
				String sql3 = "UPDATE employee SET department = ? where id = ?"; 
				prestatement = connection.prepareStatement(sql3);
				System.out.println("Enter the department"); 
				String dept = scanner.next();
				System.out.println("Enter the id where you want to update"); 
				int id3 = scanner.nextInt(); 
				prestatement.setString(1, dept);
				prestatement.setInt(2, id3);
				prestatement.executeUpdate(); 
				System.out.println("Data Updated Successfully. Do you want to update any other? (yes/no)"); 
			    break; 
			case 4:
				String sql4 = "UPDATE employee SET salary = ? where id = ?"; 
				prestatement = connection.prepareStatement(sql4); 
				System.out.println("Enter the Salary");
				String salary = scanner.next(); 
				System.out.println("Enter the id where you want to update"); 
				int id4 = scanner.nextInt(); 
				prestatement.setString(1, salary); 
				prestatement.setInt(2, id4); 
				prestatement.executeUpdate();
				display(connection,prestatement, res);
				System.out.println("Data Updated Successfully. Do you want to update any other? (yes/no)"); 
				break; 
			default:
				System.out.println("Make sure correct key should be pressed. Do you want to Update? (yes/no)");
				break; 
				
			}
			}while(s.equalsIgnoreCase(scanner.next()));
		
	        if(n.equalsIgnoreCase("no")) {
			System.out.println("No Updations Done. Do you want to perform other operations? (yes/no) "); 
			}
	 }
	 
	 public static void delete(Connection connection, PreparedStatement prestatement, ResultSet res) throws SQLException{
		 Scanner scanner = new Scanner(System.in);
		 String s="yes", n="nO";
		 String SQL = "DELETE FROM employee where id=?"; 
		 prestatement = connection.prepareStatement(SQL); 
		 System.out.println("Enter the ID you want to delete"); 
		 prestatement.setInt(1, scanner.nextInt()); 
		 prestatement.executeUpdate(); 
		 System.out.println("The table after deletion is"); 
		 display(connection, prestatement, res); 
		 System.out.println("Do you want to perform other operations? (yes/no) "); 
		 
		 while(s.equalsIgnoreCase(scanner.next()));
			
	        if(n.equalsIgnoreCase("no")) {
			System.out.println("Do you want to perform other operations? (yes/no) "); 
			}
		 
	 }
	 
	 
	 public static void main(String[] args) {
		 String url = "jdbc:mysql://localhost:3306/jdbcclasses";
		 String username = "root";
		 String password = "root"; 
		 Connection connection = null; 
		 PreparedStatement prestatement = null; 
		 ResultSet res = null; 
		 Scanner scanner = new Scanner(System.in);

		 
		 try { 
		 connection = DriverManager.getConnection(url, username, password);
		 System.out.println("<!-------Welcome to Console Based Application--------!>"); 
		 System.out.println("Enter the number to perform CRUD Operations");
		 String s="yes"; String n="no"; 
		 do {
			 System.out.println("Enter 1 to Insert the row"); 
			 System.out.println("Enter 2 to Update the row"); 
			 System.out.println("Enter 3 to Delete the row");
			 System.out.println("Enter 4 to Display the Table"); 
			 int num = scanner.nextInt(); 
			 switch(num) {
			  
			 case 1:
				 insert(connection, prestatement, res);
				 break;
			 case 2:
				 update(connection, prestatement, res);
			     break; 
			 case 3:
				 delete(connection, prestatement, null); 
				 break; 
			 case 4:
				 display(connection, prestatement, res);
				 System.out.println("Do you want to perform other operations? (yes/no) ");
				 break;
			 default: 
				 System.out.println("Make sure you enter correct number." + "Do you want to continue? (yes/no)");
				 break; 
			 }
		 }while(s.equalsIgnoreCase(scanner.next())); 
		 if(n.equalsIgnoreCase("n")) {
			 System.out.println("Thank You...!!"); 
			 }
		 }
		 
	     catch (SQLException e) 
	     { 
	        e.printStackTrace(); 
	     }
		 finally { 
			scanner.close(); 
		 try {
			 connection.close(); 
		 } 
		 catch (SQLException e) { 
			 e.printStackTrace(); 
		 }
		 }
		 
	 }
}
	 
	 
	 
	 
	 
	 
	 
	 
