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
import java.sql.Statement;
import java.time.Instant;
import java.util.Calendar;
import java.util.Date;

// TODO: Auto-generated Javadoc
/**
 * The Class downloadTransactions.
 */
public class downloadTransactions {
	
		/**
		 * The main method.
		 *
		 * @param args the arguments
		 */
		public static void main(String[] args) {

		// Define connection to db
        String jdbcURL = "jdbc:mysql://localhost:3306/inventory_management_system";
        String username = "root";
        String Password = "KiloSierraFoxtrot1!";
        
        // Create a Calendar and set it to the current time.
        Calendar cal = Calendar.getInstance();
        cal.setTime(Date.from(Instant.now()));
        
        // Create a .CSV filename from a format string.
        // Apply date formatting codes (format explained below)
        // Year-Monday-Day-Hour-Minute-Second-AM/PM
        // Change file path to relevant user directory if needed
        String csvFilePath = String.format(
                "C:\\Users\\newal\\Desktop\\file-%1$tY-%1$tm-%1$td-%1$tk-%1$tS-%1$tp.csv", cal); 
        
        
        try (Connection conn = DriverManager.getConnection(jdbcURL, username, Password)) {
            String sql = "SELECT * FROM transactions";
             
            Statement st = conn.createStatement();
             
            ResultSet rs = st.executeQuery(sql);
             
            BufferedWriter fileWriter = new BufferedWriter(new FileWriter(csvFilePath));
             
            // Write header line containing column names       
            fileWriter.write("ID, Item Description, Quantity Sold, Amount sold for (£), Current Stock (after purchase)");
             
            while (rs.next()) {
                int ID = rs.getInt("ID");
                String itemDescription = rs.getString("itemDescription");
                int quantitySold = rs.getInt("quantitySold");
                double amount = rs.getDouble("amount");
                int currentStock = rs.getInt("currentStock");
                 
                // 
                String line = String.format("\"%d\",%s,%d,%f,%d",
                        ID, itemDescription, quantitySold, amount, currentStock);
                 
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
