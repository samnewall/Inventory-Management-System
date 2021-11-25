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
 * The Class deleteItems.
 */
public class deleteItems {
	
			/** The id. */
			// Create objects of database table columns
			static int id;
		
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
		      String query = "DELETE FROM items WHERE id = ?";
		      // Connect and prompt for user input
		      // Values entered in order of query
		      try {
		         conn = DriverManager.getConnection(JdbcURL, Username, Password);
		         input(keyboardInput);
		         pstmt = conn.prepareStatement(query);
		         pstmt.setInt(1, id);
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
		   // User prompt for data entry
		   /**
   		 * Input.
   		 *
   		 * @param keyboardInput the keyboard input
   		 */
		   public static void input(Scanner keyboardInput) {
		      System.out.println("Enter ID of item to be deleted: ");
		      id = keyboardInput.nextInt();
		      
		   }

}
