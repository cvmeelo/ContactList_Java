/**
 * @author Camilo Viniegra
 * 
 * Program Name: ContactList
 * Class Name: ContactListUI
 * 
 * Program Description: Create a address book for your contacts.  Create your own contacts. 
 * Saves and retrieves your contacts.
 */
import java.util.Scanner;

public class ContactListUI 
{
	private static Scanner scanner;
	private enum Status {CONTINUE, EXIT}
	private static Status status;
	private static ContactList contacts = new ContactList();
	private static String searchCriteria = new String();
	private static String searchValue = new String();
	private static int option;

	public static void main(String[] args)
	{
		scanner = new Scanner( System.in );
		scanner.useDelimiter(System.getProperty("line.separator"));
		status = Status.CONTINUE;
		
		//If an existing contact list exist, it is loaded here.
		contacts.loadContactList();
		
		System.out.println("Welcome to the Contact List Application!\n" );

		do{
			// Show menu options.
			menu();
			
			// Get the menu option number from user.
			option = getOption();
			
			// Call selectMenuOption method to perform menu action based on user input.
			selectMenuOption( option );
			
		// Continue loop while enum status is Continue, if not end loop 
		} while ( status == Status.CONTINUE );
	}
	
	
    /**
	* Prompt the user with a menu: 
	*/
	public static void menu()
	{
		System.out.println("Please choose an option from the menu: \n");
		System.out.println("1 to Add a Contact.\n" + 
							"2 to Print the Full Contact List\n" + 
							"3 to Retrieve a Contact with a Last Name\n" + 
							"4 to Retrieve a Contact with an Email\n" + 
							"5 to Retrieve a Contact with a Zip Code\n" + 
							"6 to Exit the Application\n");
	}
	
    /**
  	*   getOption method prompt the user to select a menu action with a number
  	*   @return int number user selected option		
	*/
	public static int getOption()
	{
		int num; 
		do 
		{
			num = 0;
			System.out.print("What would you like to do? " );
			if( scanner.hasNextInt() )   // verify it scanned input is a number
			{
				num = scanner.nextInt(); 
				if( num < 1 || num > 6 )
				{
					System.out.println("Please enter a number between 1 and 6.");
				}
			}else if( scanner.hasNext() )
			{
				System.out.println("Please enter a number between 1 and 6.");
				scanner.next();
			}
		// Continue loop while entry is not between 1 & 6		
		} while( num != 1 && num != 2 && num != 3 && num != 4 && num != 5 && num != 6 );
		
		return num; 
	}
	
    /**
	* The selectMenuOption method take user input to perform the selected menu action. 
    * @param option - int value used by this method to select a menu option.
    */
	public static void selectMenuOption( int option)
	{
		switch(option)	
		{
			case 1: System.out.println("Enter Contact Information: \n");
					System.out.println( contacts.addContact() ); 
					break;
			case 2: System.out.println("Print Full Contact List: \n");
					contacts.sort();
					System.out.println( contacts.toString() ); 
					break;
			case 3: System.out.print("Last Name? ");
					searchValue = readValidBasicEntry();
					searchCriteria = "last";
					System.out.println("Contacts with Last Name: " + searchValue );
					System.out.println( contacts.retrieveContact(searchCriteria, searchValue) );
					break;
			case 4: System.out.print("Email? ");
					searchValue = readValidEmail();
					searchCriteria = "email";
					System.out.println("Contacts with Email: " + searchValue );
					System.out.println( contacts.retrieveContact(searchCriteria, searchValue) );
					break;
			case 5: System.out.print("Zip Code? ");
					searchValue = readValidZip();
					searchCriteria = "zip";
					System.out.println("Contacts with Zip Code: " + searchValue );
					System.out.println( contacts.retrieveContact(searchCriteria, searchValue) );
					break;
			case 6: System.out.println("Saving to disk...");
					status = Status.EXIT;
					contacts.saveContactList();
					scanner.close();
					System.out.println("Goodbye!");	
					break;
		}	
	}
	
    /**
    * validateBasicEntry scans, checks the entry, and forces a user to re-enter input (received from scanner) if 
    * a blank space or null entry occurs.  If not in proper format, it displays an error and prompts the 
    * user again for input.
    * @param String variable entry takes data received from the scanner object.
    * @return user input that is not a null entry or a space.
    */
	public static String readValidBasicEntry()
	{
		String entry = scanner.next();		
		while( !entry.matches("[\\S]+") )
		{
			System.out.println("Entry must be a non-whitespace and not null: " );
			entry = scanner.next();
		}	
		return entry;
	}
    
	/**
	* validateEmail method verifies that user input (received from scanner) is a valid email address. 
    * If not in proper format, it displays an error and prompts the user again for input.
    * @param String variable email takes user input from the scanner object.
    * @return user input after it matches a valid email format.
    */
	public static String readValidEmail()
	{
		String email = scanner.next();
		while( !email.matches("[^\\s]+@[^\\s]+([.]\\w{2,3})+") )
		{
			System.out.println("Email is required in proper format (i.e. email@domain.com): ");
			email =  scanner.next();
		}	
		return email;
	}
	
    /**
    * validatePhone checks user input received from scanner and verifies that it is in proper
    * format (i.e. nnn-nnn-nnnn). If not in proper format, displays an error and prompts the 
    * user again for input.
    * @param String variable phone takes user input from the scanner object.
    * @return user input after it matches a valid phone number format.
    */
	public static String readValidPhone()
	{
		String phone = scanner.next();
		while( !phone.matches("[0-9]{3}-[0-9]{3}-[0-9]{4}"))
		{
			System.out.println("Phone Number is required in proper format (i.e. 123-456-7890): ");
			phone = scanner.next();
		}	
		return phone;
	}

	/**
    * validateZipCode checks user input received from scanner and verifies that it is in 
    * proper format (i.e. nnnnn). If not in proper format, displays an error and prompts the 
    * user again for input.
    * @param String variable zip takes user input from the scanner object.
    * @return user input after it matches a valid zip code format.
    */
	public static String readValidZip()
	{
		String zip = scanner.next();
		while( !zip.matches("[0-9]{5}") )
		{
			System.out.println("Zip code requires 5 digits (i.e. 94306): " );
			zip = scanner.next();
		}
		return zip;
	}
}
