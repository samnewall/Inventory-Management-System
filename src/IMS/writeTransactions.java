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
 * The Class writeTransactions.
 */
public class writeTransactions {
	
	/** The item description. */
	// Create objects of database table columns
	static String itemDescription;
	
	/** The quantity sold. */
	static int quantitySold;
	
	/** The quantity brought. */
	static int quantityBrought;
	
	/** The amount. */
	static double amount;
	
	/** The current stock. */
	static int currentStock;
	
 
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
      String query = "INSERT INTO transactions(ID, itemDescription, quantitySold, quantityBrought, amount, currentStock) VALUES (null, ?, ?, ?, ?, ?)";
      // Connect and prompt for user input
      // Values entered in order of query
      try {
         conn = DriverManager.getConnection(JdbcURL, Username, Password);
         input(keyboardInput);
         pstmt = conn.prepareStatement(query);
         pstmt.setString(1, itemDescription);
         pstmt.setInt(2, quantitySold);
         pstmt.setInt(3, quantityBrought);
         pstmt.setDouble(4, amount);
         pstmt.setInt(5, currentStock);
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
      System.out.println("Enter item description: ");
      itemDescription = keyboardInput.nextLine();
      System.out.println("Enter quantity sold: ");
      quantitySold = keyboardInput.nextInt();
      System.out.println("Enter quantity brought: ");
      quantitySold = keyboardInput.nextInt();
      System.out.println("Enter amount: ");
      amount = keyboardInput.nextDouble();
      System.out.println("Enter current stock: ");
      currentStock = keyboardInput.nextInt();
   }
}