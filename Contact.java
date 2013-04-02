/**
 * @author Camilo Viniegra
 * 
 * Program Name: ContactList
 * Class Name: Contact
 * 
 * Program Description: Create a address book for your contacts.  Create your own contacts. 
 * Saves and retrieves your contacts.
 */
import java.io.Serializable;;

/**
 * @author Camilo Viniegra
 *
 * Class Contact provides a way to organize and save your contact information in an address book.
 * An object of Class Contact stores First Name, Last Name, Street Address, Zip Code, Email Address, Phone Number, and Notes.
 * There are set and get methods for each data method, as well as a toString method to help retrieve contacts' information.
 */

public class Contact implements Serializable
{
	private static final long serialVersionUID = 1L;
	private String firstName; 
	private String lastName; 
	private String streetAddress;
	private String zipCode;
	private String email; 
	private String phoneNumber;
	private String notes;
	
	/**
	 * The Contact constructor initializes all variables.
	 */
	
	public Contact()
	{
		firstName = "";
		lastName = "";
		streetAddress = "";
		zipCode = "";
		email = "";
		phoneNumber = "";
		notes = "";
	}
	
	/**
	 * Sets the value of firstName to "first".
	 */
	void setFirstName( String first )
	{
		firstName = first;
	}
	
	/**
	 * Sets the value of lastName to "last".
	 */
	void setLastName( String last )
	{
		lastName = last; 
	}
	
	/**
	 * Sets the value of streetAddress to "street".
	 */
	void setStreetAddress( String street )
	{
		streetAddress = street;
	}
	
	/**
	 * Sets the value of zipCode to "zip".
	 */
	void setZipCode( String zip )
	{
		zipCode = zip; 
	}
	
	/**
	 * Sets the value of email to "mail".
	 */
	void setEmail( String mail )
	{
		email = mail;
	}
	
	/**
	 * Sets the value of phoneNumber to "phone".
	 */
	void setPhoneNumber( String phone )
	{
		phoneNumber = phone; 
	}
	
	/**
	 * Sets the value of notes to "note".
	 */
	void setNotes( String note )
	{
		notes = note; 
	}
	
	/**
	 * @return the value of the firstName variable.
	 */
	String getFirstName()
	{
		return firstName;
	}
	
	/**
	 * @return the value of the lastName variable.
	 */
	String getLastName()
	{
		return lastName;
	}
	
	/**
	 * @return the value of the Street Address variable.
	 */
	String getStreetAddress()
	{
		return streetAddress;
	}
	
	/**
	 * @return the value of the zipCode variable.
	 */
	String getZipCode()
	{
		return zipCode;
	}

	/**
	 * @return the value of the email variable.
	 */
	String getEmail()
	{
		return email;
	}
	
	/**
	 * @return the value of the Phone Number variable.
	 */
	String getPhoneNumber()
	{
		return phoneNumber;
	}
	
	/**
	 * @return the value of the notes variable.
	 */
	String getNotes()
	{
		return notes;
	}

	/**
	 * @return Provides a string representation of the contact and the information.
	 */
	public String toString()
	{
		String contact;
		contact = String.format("%s, %s \n\t\t\t%s, %s, %s, \n\t\t\t%s, %5s\n\n", 
				lastName, firstName, streetAddress, zipCode, phoneNumber, email, notes );
		return contact;
	}
}

