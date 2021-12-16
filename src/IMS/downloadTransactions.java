/**
 * @author Samuel Newall / Student Number: S174348
 * @version 1.1
 */
package IMS;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.Instant;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;
import java.sql.Statement;

// TODO: Auto-generated Javadoc
/**
 * The Class downloadTransactions.
 */
public class downloadTransactions {
	
		
		// Create objects of database table columns
		/** The date of row entry - associated with dateAdded column on database table */
		static int date;
		
		
		
		/**
		 * The main method.
		 *
		 * @param args the arguments
		 */
		public static void main(String[] args) {
		
		@SuppressWarnings("resource") // Resource leak on scan, this suppresses the warning given from IDE
		Scanner keyboardInput = new Scanner(System.in);
		System.out.println("Please specify the date you wish to generate the report of: ");
		String dateAdded = keyboardInput.next();
			

		// Define connection to db
        String jdbcURL = "jdbc:mysql://localhost:3306/inventory_management_system";
        String username = "root";
        String Password = "KiloSierraFoxtrot1!";
        
        Connection conn = null;
        
        // Create a Calendar and set it to the current time.
        Calendar cal = Calendar.getInstance();
        cal.setTime(Date.from(Instant.now()));
        
        // Create a .CSV filename from a format string.
        // Apply date formatting codes (format explained below)
        // Year-Day-Hour-Minute-Second-AM/PM
        // Change file path to relevant user directory if needed
        String csvFilePath = String.format(
                "C:\\Users\\newal\\Desktop\\Transactions-%1$tY-%1$tm-%1$td-%1$tk-%1$tS-%1$tp.csv", cal); 
              
	      
        try {
        	// Create db connection and sql query
        	conn = DriverManager.getConnection(jdbcURL, username, Password); 
        	String sql = "SELECT * FROM transactions WHERE dateAdded ='" + dateAdded + "'";
	        Statement st = conn.createStatement();
	        ResultSet rs = st.executeQuery(sql);
            
            BufferedWriter fileWriter = new BufferedWriter(new FileWriter(csvFilePath));
             
            // Write header line containing column names       
            fileWriter.write("ID, Item Description, Quantity Sold, Amount sold for (£), Current Stock (after purchase), Date Added");
            
            // Define column names and datatypes in table
            // This will specify what data is to be written
            while (rs.next()) {
                int ID = rs.getInt("ID");
                String itemDescription = rs.getString("itemDescription");
                int quantitySold = rs.getInt("quantitySold");
                int quantityBrought = rs.getInt("quantityBrought");
                double amount = rs.getDouble("amount");
                int currentStock = rs.getInt("currentStock");
                 
                 
                String line = String.format("\"%d\",%s,%d,%d,%f,%d",
                        ID, itemDescription, quantitySold, quantityBrought, amount, currentStock);
                 
                fileWriter.newLine();
                fileWriter.write(line);            
            }
          // Close the connection
  	      // Otherwise new connections will keep being made, resulting in com.mysql.jdbc.CommunicationsException error
  	      // OS limits amount of connections (sockets) to Db
            conn.close();
            st.close();
            fileWriter.close();
        
          // Error handling
        } catch (SQLException e) {
            System.out.println("Database error:");
            e.printStackTrace();
        } catch (IOException e) {
            System.out.println("File IO error:");
            e.printStackTrace();
        }            
	}		
}
