/**
 * @author Samuel Newall / Student Number: S174348
 * @version 1.1
 */
package IMS;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

// TODO: Auto-generated Javadoc
/**
 * The Class updateItems.
 */
public class updateItems {
	
		/** The id. */
		// Create objects of database table columns
		static int id;
		
		/** The quantity. */
		static int quantity;
		
		/** The total value. */
		static double totalValue;
		
		/** The unit price. */
		static double unitPrice;

		
	   /**
   	 * The main method.
   	 *
   	 * @param args the arguments
   	 */
   	public static void main(String[] args) {
		   
		  // Define connection to db
	      String JdbcURL = "jdbc:mysql://localhost:3306/inventory_management_system";
	      String Username = "root";
	      String Password = "KiloSierraFoxtrot1!";
	      Connection conn = null;
	      // Create scanner for user input and user defined prepared SQL statement
	      Scanner keyboardInput=new Scanner(System.in);
	      PreparedStatement pstmt = null;
	      String query = "UPDATE items SET quantity = ?, totalValue = ? WHERE id = ?";
	      // Connect and prompt for user input
	      // Values entered in order of query
	      try {
	         conn = DriverManager.getConnection(JdbcURL, Username, Password);
	         input(keyboardInput);
	         pstmt = conn.prepareStatement(query);
	         pstmt.setInt(1, quantity);
	         pstmt.setDouble(2, totalValue);
	         pstmt.setInt(3, id);
	         int status = pstmt.executeUpdate();
	         if(status > 0) {
	            System.out.println("Record has been updated successfully !!!");
	            
	         }
	      } catch(Exception e){
	         e.printStackTrace();
	      }
	      // Close the connection
	      // Otherwise new connections will keep being made, resulting in com.mysql.jdbc.CommunicationsException error
	      // OS limits amount of connections (sockets) to Db
	      finally {
	    	    try { conn.close(); } catch (Exception e) { /* Ignored */ }
	      }
	   }
	   
   	/**
   	 * Input.
   	 *
   	 * @param keyboardInput the keyboard input
   	 */
   	// User prompt for data entry
	   public static void input(Scanner keyboardInput) {
	      System.out.println("Enter new quantity: ");
	      quantity = keyboardInput.nextInt();
	      System.out.println("Enter item price (per unit): ");
	      unitPrice = keyboardInput.nextDouble();
	      // Calculate new total value of items currently in stock
	      totalValue = unitPrice * quantity;
	      System.out.println("Enter ID of item to be updated: ");
	      id = keyboardInput.nextInt();
	      
	   }
}
