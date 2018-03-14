import java.util.Comparator;

//Sindy Wang 500766809

public class Person implements Comparable<Person> 
/**
 * This is the Person class
 */
{
	private String lastName;
	private String firstName;
	private String tel;
	private String address;
	private String email;
	
	public Person(String lastName, String firstName, String address, String tel, String email)
	/**
	 * This is the contructor of the person class
	 */
	{
		this.lastName = lastName;
		this.firstName = firstName;
		this.tel = tel;
		this.address = address;
		this.email = email;
	}
	
	public String getLast()
	/**
	 * This method get the last name of the person
	 * @param none
	 * @return lastName
	 */
	{
		return this.lastName;
	}
	
	public String getFirst()
	/**
	 * This method get the first name of the person
	 * @param none
	 * @return firstName
	 */
	{
		return this.firstName;
	}
	
	public String getTel()
	/**
	 * This method get the telephone number of the person
	 * @param none
	 * @return tel
	 */
	{
		return this.tel;
	}
	
	public String getEmail()
	/**
	 * This method get the email of the person
	 * @param none
	 * @return email
	 */
	{
		return this.email;
	}
	
	public String getAddress()
	/**
	 * This method get the address of the person
	 * @param none
	 * @return address
	 */
	{
		return this.address;
	}
	
	public int compareTo(Person other)
	/**
	 * This method compares 2 Person objects
	 * @param other
	 * @return -1,0 or 1
	 */
	{
		if(this.lastName.equals(other.lastName))
			return this.firstName.compareTo(other.firstName);
		else return this.lastName.compareTo(other.lastName);
	}
}

class TelComparator implements Comparator<Person>
/**
 * This is the telephone comparator class
 */
{
	public int compare(Person o1, Person o2) 
	/**
	 * This method compares 2 Person objects with their telephone numbers
	 * @param o1, o2
	 * @return -1,0 or 1
	 */
	{
		return o1.getTel().compareTo(o2.getTel());
	}
	
}

class EmailComparator implements Comparator<Person>
/**
 * This is the email comparator class
 */
{
	public int compare(Person o1, Person o2) 
	/**
	 * This method compares 2 Person objects with their email
	 * @param o1, o2
	 * @return -1,0 or 1
	 */
	{
		return o1.getEmail().compareTo(o2.getEmail());
	}
	
}
