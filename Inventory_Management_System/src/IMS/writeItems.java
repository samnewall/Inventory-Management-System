/**
 * 
 */
/**
 * @author Samuel Newall / Student Number: S174348
 *
 */
package IMS;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;


public class writeItems {
	
	// Create objects of database table columns
	static String itemDescription;
	static double unitPrice;
	static int quantity;
	static double totalValue;
	
   public static void main(String[] args) {
	  // Define connection to db
      String JdbcURL = "jdbc:mysql://localhost:3306/inventory_management_system";
      String Username = "root";
      String password = "KiloSierraFoxtrot1!";
      Connection con = null;
      // Create scanner for user input and user defined prepared SQL statement
      Scanner keyboardInput=new Scanner(System.in);
      PreparedStatement pstmt = null;
      String query = "INSERT INTO items(ID, itemDescription, unitPrice, quantity, totalValue) VALUES (null, ?, ?, ?, ?)";
      // Connect and prompt for user input
      // Values entered in order of query
      try {
         con = DriverManager.getConnection(JdbcURL, Username, password);
         input(keyboardInput);
         pstmt = con.prepareStatement(query);
         pstmt.setString(1, itemDescription);
         pstmt.setDouble(2, unitPrice);
         pstmt.setInt(3, quantity);
         pstmt.setDouble(4, totalValue);
         int status = pstmt.executeUpdate();
         if(status > 0) {
            System.out.println("Record is inserted successfully !!!");
         }
      } catch(Exception e){
         e.printStackTrace();
      }
      // Close the connection
      // Otherwise new connections will keep being made, resulting in com.mysql.jdbc.CommunicationsException error
      // OS limits amount of connections (sockets) to Db
      finally {
    	    try { con.close(); } catch (Exception e) { /* Ignored */ }
      }
   }
   // User prompt for data entry
   public static void input(Scanner keyboardInput) {
	   	    
      System.out.println("Enter item description: ");
      itemDescription = keyboardInput.nextLine();
      System.out.println("Enter unit price: ");
      unitPrice = keyboardInput.nextDouble();
      System.out.println("Enter quantity: ");
      quantity = keyboardInput.nextInt();
      // Calculate total value of items currently in stock
      totalValue = unitPrice * quantity;
      System.out.println("Total Value: " + totalValue);

   }
}