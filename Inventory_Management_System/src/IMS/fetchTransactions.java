/**
 * 
 */
/**
 * @author Samuel Newall / Student Number: S174348
 *
 */
package IMS;

import java.sql.*;
import java.util.Currency;
import java.util.Locale;



public class fetchTransactions {

	
	 public static void main(String[] args)
	  {
	    try
	    {
	        // Set locale and currency format to UK/GBP
	        Locale locale = Locale.UK;
	        Currency curr = Currency.getInstance(locale);
	         
	      // Create mysql database connection
	      String myDriver = "com.mysql.cj.jdbc.Driver";
	      String myUrl = "jdbc:mysql://localhost:3306/inventory_management_system";
	      Class.forName(myDriver);
	      Connection conn = DriverManager.getConnection(myUrl, "root", "KiloSierraFoxtrot1!");
	      
	      // SQL SELECT query
	      String query = "SELECT * FROM transactions";

	      // Create java statement
	      Statement st = conn.createStatement();
	      
	      // Execute the query, return results to resultset
	      ResultSet rs = st.executeQuery(query);
	      
	      // iterate through the java resultset
	      while (rs.next())
	      {
	        int ID = rs.getInt("ID");
	        String ItemDescription = rs.getString("itemDescription");
	        int quantitySold = rs.getInt("quantitySold");
	        double amount = rs.getDouble("amount");
	        int currentStock = rs.getInt("currentStock");
        
	        // Print the results
	        System.out.format("ID: " + "%s " + "Description: " + "%s " + "Quantity Sold: " + "%s " + "Total Charged: " + curr.getSymbol() + "%s " + "Current Stock (at time of purchase): " + "%s\n ", ID, ItemDescription, quantitySold, amount, currentStock);
	      }
	      // Close the connection
	      // Otherwise new connections will keep being made, resulting in com.mysql.jdbc.CommunicationsException error
	      // OS limits amount of connections (sockets) to Db
	      conn.close();
	      st.close();
	    }
	    catch (Exception e)
	    {
	      System.err.println("Got an exception! ");
	      System.err.println(e.getMessage());
	    }
	  }

}
