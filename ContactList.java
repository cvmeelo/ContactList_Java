/**
 * @author Camilo Viniegra
 * 
 * Program Name: ContactList
 * Class Name: ContactList
 * 
 * Program Description: Create a address book for your contacts.  Create your own contacts. 
 * Saves and retrieves your contacts.
 */

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * @author Camilo Viniegra
 * ContactList class creates and/or opens the contact list file, loads the contact objects to an array, 
 * and instantiates the scanner and ArrayList objects. It also adds a contact to the contact list, retrieves
 * contacts based on last name, email and zip code and finally, saves all contacts to disk.
 */
public class ContactList
{
	private final String CONTACT_FILE = new String("contactList.dat");
	private Scanner scanner;
	private List<Contact> list;  
	
	
	/**
	 * ContactList Constructor instantiates scanner and two ArrayList objects. 
	 */
	public ContactList()
	{
		scanner = new Scanner( System.in );
		scanner.useDelimiter(System.getProperty("line.separator"));
		list = new ArrayList<Contact>();
	}
	
	/**
	 * addContact creates a new contact and appends it to a ArrayList object.
	 * @return A confirmation is returned, either "Contact added" or an "ERROR".
	 */
	public String addContact()
	{
		Contact contactTemp;
		contactTemp = new Contact();

		System.out.print("Enter First Name: ");
		contactTemp.setFirstName( scanner.nextLine() );
		System.out.print("Enter Last Name: ");
		contactTemp.setLastName( scanner.nextLine() );
		System.out.print("Enter Street Address: ");
		contactTemp.setStreetAddress( scanner.nextLine() );
		System.out.print("Enter Zip Code: ");
		contactTemp.setZipCode( ContactListUI.readValidZip() );
		
		System.out.print("Enter Email: ");
		contactTemp.setEmail( ContactListUI.readValidEmail() );
		
		System.out.print("Enter Phone Number (xxx-xxx-xxxx): ");
		contactTemp.setPhoneNumber( ContactListUI.readValidPhone());
		
		System.out.print("Enter Notes: ");
		contactTemp.setNotes( scanner.nextLine() );
		System.out.println();

		if( !contactTemp.getFirstName().isEmpty() && !contactTemp.getLastName().isEmpty() && 
					!contactTemp.getFirstName().matches("\\s+") && !contactTemp.getLastName().matches("\\s+") )
		{
			list.add(contactTemp);
			return "Contact Added!\n";
		} else 
		{
			return "ERROR: Contact was not added to the Contact List. \n" +
					"First Name and Last Name are required!\n";
		}
	}
	
	/**
	 * retrieveContact searches for contacts in the ArrayList object.  
	 * It returns contacts match a specified last name, email, or zip code.
	 * @param criteria determines the what data to match with, either last name, email or zip code.
	 * @param value takes an argument used to match contacts with.
	 * @return returns a string containing the heading and matching contacts or message for no matching contacts.
	 */
	public String retrieveContact( String criteria, String value )
	{
		String contactList;
		int count; // initialize variable for counting search matches
		
		contactList = "";
		count = 0;

		contactList = String.format("%s \t%s\n", "Name (Last, First)", "Personal Information (Address, Phone, Email, notes)");
		contactList += "--------------------------------------------------------------------------------\n";
		for( int i = 0; i < list.size(); i++ )
		{
				if( criteria == "last" )
				{
					if( value.equalsIgnoreCase( list.get(i).getLastName() ))
					{
						contactList += list.get(i).toString();
						count++;
					}
					
				} else if ( criteria == "email" )
				{
					if( value.equalsIgnoreCase( list.get(i).getEmail() ))
					{
						contactList += list.get(i).toString();
						count++;
					}
					
				} else if ( criteria == "zip" )
				{
					if( value.equals( list.get(i).getZipCode() ))
					{
						contactList += list.get(i).toString();
						count++;
					}
					
				}		
			
		}
		if( count == 0 )
		{
			return "Sorry, no contacts could be found.\n";
		} else 
		{
			return contactList;
		}	
	}
	
	/**
	 * loadContactList method checks to see if a contact file exist and loads the contents of an existing file
	 * to the arraylist object. 
	 */
	@SuppressWarnings("unchecked")
	public void loadContactList()
	{
		File inFile;
		FileInputStream inFileStream;
		ObjectInputStream inObjectStream;
		
		try {
			
			inFile = new File( CONTACT_FILE );
			if( inFile.exists() )
			{
				inFileStream = new FileInputStream( inFile );
				inObjectStream = new ObjectInputStream( inFileStream );
				
				list = (List<Contact>) inObjectStream.readObject();
				
				inObjectStream.close();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
			e.getMessage(); 
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} 
	}
	
	/**
	 * saveContactList method opens the contact list file and saves the entire contact list 
	 * from the ArrayList object.
	 */
	public void saveContactList()
	{
		File outFile;
		FileOutputStream outFileStream;
		ObjectOutputStream outObjectStream; 
		
			outFile = new File( CONTACT_FILE );
			try {
				outFileStream = new FileOutputStream(outFile);
				outObjectStream = new ObjectOutputStream(outFileStream);
				
				outObjectStream.writeObject(list);
				
				outObjectStream.close();
				
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}		
	}
	
	/**
	 * The sort method access the ArrayList object and sorts the contacts by last name, case-insensitive.
	 */
	public void sort()
	{
		Collections.sort(list, new Comparator<Contact>(){
			public int compare( Contact contact01, Contact contact02 )
			{
				return contact01.getLastName().compareToIgnoreCase( contact02.getLastName() );
			}
		});
	}
	

	/**
	 * The toString method returns a string containing the heading and full contact list or message for no matching contacts
	 */
	public String toString()
	{
		String fullList = ""; 
		int count = 0;
		
		fullList = String.format("%s \t%s\n", "Name (Last, First)", "Personal Information (Address, Phone, Email, notes)");
		fullList += "--------------------------------------------------------------------------------\n";
		
		for(int i = 0; i < list.size(); i++)
		{
			fullList += list.get(i).toString() ;
			count++;
		}
		
		if( count == 0 )
		{
			return "Sorry, no contacts could be found.\n";
		} else 
		{
			return fullList;
		}	
	}
	
}