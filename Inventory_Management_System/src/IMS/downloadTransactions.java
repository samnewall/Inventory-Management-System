/**
 * 
 */
/**
 * @author Samuel Newall / Student Number: S174348
 *
 */
package IMS;

import java.io.*;
import java.sql.*;
import java.time.Instant;
import java.util.Calendar;
import java.util.Date;

public class downloadTransactions {
	
	public static void main(String[] args) {

		// Define connection to db
        String jdbcURL = "jdbc:mysql://localhost:3306/inventory_management_system";
        String username = "root";
        String password = "KiloSierraFoxtrot1!";
        
        // Create a Calendar and set it to the current time.
        Calendar cal = Calendar.getInstance();
        cal.setTime(Date.from(Instant.now()));
        
        // Create a .CSV filename from a format string.
        // Apply date formatting codes (format explained below)
        // Year-Monday-Day-Hour-Minute-Second-AM/PM
        // Change file path to relevant user directory if needed
        String csvFilePath = String.format(
                "C:\\Users\\newal\\Desktop\\file-%1$tY-%1$tm-%1$td-%1$tk-%1$tS-%1$tp.csv", cal); 
        
        
        try (Connection connection = DriverManager.getConnection(jdbcURL, username, password)) {
            String sql = "SELECT * FROM transactions";
             
            Statement statement = connection.createStatement();
             
            ResultSet result = statement.executeQuery(sql);
             
            BufferedWriter fileWriter = new BufferedWriter(new FileWriter(csvFilePath));
             
            // Write header line containing column names       
            fileWriter.write("ID, Item Description, Quantity Sold, Amount sold for (£), Current Stock (after purchase)");
             
            while (result.next()) {
                int ID = result.getInt("ID");
                String itemDescription = result.getString("itemDescription");
                int quantitySold = result.getInt("quantitySold");
                double amount = result.getDouble("amount");
                int currentStock = result.getInt("currentStock");
                 
                // 
                String line = String.format("\"%d\",%s,%d,%f,%d",
                        ID, itemDescription, quantitySold, amount, currentStock);
                 
                fileWriter.newLine();
                fileWriter.write(line);            
            }
          // Close the connection
  	      // Otherwise new connections will keep being made, resulting in com.mysql.jdbc.CommunicationsException error
  	      // OS limits amount of connections (sockets) to Db
            connection.close();
            statement.close();
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
