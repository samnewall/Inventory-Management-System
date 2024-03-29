/**
 * @author Samuel Newall / Student Number: S174348
 * @version 1.1
 */
package IMS;

import java.util.Scanner;

// TODO: Auto-generated Javadoc
/**
 * The Class mainMenu.
 */
public class mainMenu
{
	
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String args[])
	
	{
	
	    @SuppressWarnings("resource") // Resource leak on input, this suppresses the warning given from IDE
		Scanner input = new Scanner(System.in);
		
	    
	    // Main Menu splash screen
		System.out.println("|----------------------------------------------------------------------------|");
		System.out.println("| IBM Inventory Management System - Computer Hardware, Parts and Accessories |");
		System.out.println("|----------------------------------------------------------------------------|");
		System.out.println("|                                 Main Menu                                  |");
		System.out.println("| Options:                                                                   |");
		System.out.println("| 1. Add new item                                                            |");
		System.out.println("| 2. Update quantity of existing item                                        |");
		System.out.println("| 3. Remove item                                                             |");
		System.out.println("| 4. Search for an item                                                      |");
		System.out.println("| 5. Add new transaction                                                     |");
		System.out.println("| 6. View all items                                                          |");
		System.out.println("| 7. View all transactions                                                   |");
		System.out.println("| 8. Download daily transaction report                                       |");
		System.out.println("|----------------------------------------------------------------------------|");
		System.out.println("| 9. Exit                                                                    |");
		System.out.println("|----------------------------------------------------------------------------|");
		System.out.println("| Created by Samuel Newall                                                   |");
		System.out.println("| Version 1 � 2021                                                           |");	
		System.out.println("|----------------------------------------------------------------------------|");


		
		System.out.print("\n Enter a choice and Press ENTER to continue[1-9]:");
		int userinput = input.nextInt();
		
		// While loop to repeat the switch case
		// if userinput hold values that are less than 10
		while (userinput<10) {
		// Execute java classes depending on user's input
		switch(userinput)
		{
				case 1:
					writeItems.main(args);
					break;
				case 2:		
					updateItems.main(args);
					break;
				case 3:
					deleteItems.main(args);
					break;
				case 4:
					searchItems.main(args);
					break;
				case 5:
					writeTransactions.main(args);
					break;
				case 6:
					fetchItems.main(args);
					break;
				case 7:
					fetchTransactions.main(args);
					break;
				case 8:
					downloadTransactions.main(args);
					break;
				case 9:
					// Exit program when choice is 9
					System.out.print("\n Exiting program...");
					System.exit(0);
				default:
					System.out.print("\n Error! Invalid option");
					return;
			}
			// Request user input again
			userinput = input.nextInt();
		}
	}
}