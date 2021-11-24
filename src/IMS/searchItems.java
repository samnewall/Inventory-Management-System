/**
 * @author Samuel Newall / Student Number: S174348
 * @version 1
 */
package IMS;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Currency;
import java.util.Locale;
import java.util.Scanner;


public class searchItems {
	
	 public static void main(String[] args)
	  {
	    @SuppressWarnings("resource") // Resource leak on scan, this suppresses the warning given from IDE
		 Scanner scan = new Scanner(System.in);
		    System.out.println("Enter an item ID: ");
		    String id = scan.next();
		    try {
		    	
		    	// Set locale and currency format to UK/GBP
		        Locale locale = Locale.UK;
		        Currency curr = Currency.getInstance(locale);
		        
		        	// Create db connection and sql query
		        	String JdbcURL = "jdbc:mysql://localhost:3306/inventory_management_system";
		        	String Username = "root";
		        	String Password = "KiloSierraFoxtrot1!";
		            Connection conn = DriverManager.getConnection(JdbcURL, Username, Password);
		            System.out.println("Connected to database");
		            System.out.println("Displaying queried results...");
		            String query = "SELECT * FROM items WHERE ID='" + id + "'";
		            Statement st = conn.createStatement();
		            ResultSet rs = st.executeQuery(query);
		            // Define column names and datatypes in table
		            while (rs.next()) {
		                int ID = rs.getInt(1);
		                String itemDescription = rs.getString(2);
		                double unitPrice = rs.getDouble(3);
		                int quantity = rs.getInt(4);
		                double totalValue = rs.getDouble(5);
		                // Print returned results
		                System.out.println("ID: " + ID + " Description: " + itemDescription + " Unit Price: " + unitPrice + " Quantity: " + curr.getSymbol() + quantity + " Total Value: " + curr.getSymbol() + totalValue);
		            }
		          // Close the connection
		  	      // Otherwise new connections will keep being made, resulting in com.mysql.jdbc.CommunicationsException error
		  	      // OS limits amount of connections (sockets) to Db
		  	      conn.close();
		        } catch (Exception e) {
		        System.err.println("Error");
		        }

	  }
}
