import java.util.LinkedList ;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;

//Sindy Wang 500766809

public class Contacts 
/**
 * This is the Contacts class
 */
{
	private LinkedList<Person> list;
	private ArrayList<String> arr;
	private Person person;
	private EmailComparator emailComp;
	private TelComparator telComp;

	public Contacts()
	/**
	 * This is the contructor of the contacts class
	 */
	{
		emailComp = new EmailComparator();
		telComp = new TelComparator();
		list = new LinkedList<Person>();
		arr= new ArrayList<String>();
		try
		{
			readContactFile();
		}
		
		catch(FileNotFoundException e)
		{
			System.out.println(e);
		}
	}
	
	public Person findPersonName(String last, String first)
	/**
	 * This method finds a person
	 * @param last,first
	 * @return p or null (Person object)
	 */
	{
		person = new Person(last, first, null, null, null);
		for(Person p:list)
		{
			if(p.compareTo(person) == 0){
				return p;
			}
		}
		return null;
	}
	
	public Person findPersonEmail(String email) 
	/**
	 * This method finds a person
	 * @param email
	 * @return p or null (Person Object)
	 */
	{
		person = new Person(null, null, null, null, email);
		for(Person p:list)
		{
			if(emailComp.compare(person,p) == 0){
				return p;
			}	
		}
		return null;
	}
	
	public Person findPersonTel(String tel)
	/**
	 * This method finds a person
	 * @param tel
	 * @return p or null (Person Object)
	 */
	{
		person = new Person(null, null, null, tel, null);
		for(Person p:list)
		{
			if(telComp.compare(person,p) == 0){
				return p;
			}
		}
		return null;
	}
	
	public void readContactFile() throws FileNotFoundException
	/**
	 * This method reads the contacts.txt file
	 * @param none
	 * @return none
	 */
	{
		int lines = 0;
		File file = new File("contacts.txt");
		Scanner in = new Scanner(file);
		while(in.hasNextLine())
		{
			lines++;
			String i = in.nextLine();
			arr.add(i);
		}
		if ((lines -1) % 5 != 0)
		{
			throw new IllegalArgumentException("Each contact must have 5 elements");
		}
		contactInList();
	}
	
	public void contactInList()
	/**
	 * This method puts the elements into a linkedlist
	 * @param none
	 * @return none
	 */
	{
		int people = Integer.parseInt(arr.get(0));
		for(int i = 0; i < people; i++)
		{
			Person p = new Person(arr.get(1 + (i*5)),arr.get(2 + (i*5)),arr.get(3 + (i*5)),arr.get(4 + (i*5)),arr.get(5 + (i*5)));
			list.add(p);
		}
		
	}
}
