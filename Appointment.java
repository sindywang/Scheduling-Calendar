import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

//Sindy Wang 500766809

public class Appointment implements Comparable<Appointment>
/**
 * This is the Appointment Class
 */
{
   public    String     description;
   protected Calendar   date;
   private   Person     person;
      
   
   public Appointment(int year, int month, int day, int hour, int minute, String descr)
   /**
    * This is the contructor of class Appointment
    * @param year,month,day,hour,minute,descr
    */
   {
	   this.date = new GregorianCalendar();
	   date.set(Calendar.YEAR, year);
	   date.set(Calendar.MONTH, month);
	   date.set(Calendar.DAY_OF_MONTH, day);
	   date.set(Calendar.HOUR_OF_DAY, hour);
	   date.set(Calendar.MINUTE, minute);
	   description = descr;
   }
   
   public Appointment(int year, int month, int day, int hour, int minute, String descr, String last,String first,String address,String tel,String email)
   /**
    * This is the contructor of class Appointment
    * @param year,month,day,hour,minute,descr,last,first,tel,email,address
    */
   {
	   this.date = new GregorianCalendar();
	   date.set(Calendar.YEAR, year);
	   date.set(Calendar.MONTH, month);
	   date.set(Calendar.DAY_OF_MONTH, day);
	   date.set(Calendar.HOUR_OF_DAY, hour);
	   date.set(Calendar.MINUTE, minute);
	   description = descr;
	   person = new Person(last,first,address,tel,email);
   }
   
   public String print()
   /**
    * This method puts elements together in a appointment format
    * @param none
    * @return appt
    */
   {
	   String appt = "";
	   
	   int ayear      = date.get(Calendar.YEAR);
	   int amonth     = date.get(Calendar.MONTH); 
	   int adayOfMonth= date.get(Calendar.DAY_OF_MONTH);
	   int hourOfDay  = date.get(Calendar.HOUR_OF_DAY); 
	   int minute     = date.get(Calendar.MINUTE);
	   if (person != null)
	   {
		   String last = person.getLast();
		   String first = person.getFirst();
		   String tel = person.getTel();
		   String email = person.getEmail();
		   
		   if (minute > 10)
			   appt = "" + hourOfDay + ":" + minute + " " + description + " " + "WITH: " + first + " " + last + " " + tel + " " + email;
		   else
			   appt = "" + hourOfDay + ":0" + minute + " " + description + " " + "WITH: " + first + " " + last + " " + tel + " " + email; 
	   }
	   if (person == null)
	   {
		   if (minute > 10)
			   appt = "" + hourOfDay + ":" + minute + " " + description;
		   else
			   appt = "" + hourOfDay + ":0" + minute + " " + description; 
	   }
	   return appt;
   }
   
   public Calendar getDate()
   /**
    * This method gets the current date
    * @param none
    * @return date
    */
   {
	   return date;
   }
   
   public boolean occursOn(int year, int month, int day, int hour, int minute)
   /**
    * This method check if a appointment occurs on the same day or not
    * @param year,month,day,hour,minute
    * @return boolean
    */
   {
	   if (date.get(Calendar.YEAR)== year && 
		   date.get(Calendar.MONTH) == month &&
	       date.get(Calendar.DAY_OF_MONTH) == day &&
	       date.get(Calendar.HOUR_OF_DAY) == hour && 
	       date.get(Calendar.MINUTE) == minute)
         return true;
	   return false;
   }
   
   public boolean occursOn(int year, int month, int day)
   /**
    * This method check if a appointment occurs on the same day or not
    * @param year,month,day
    * @return boolean
    */
   {
	   if (date.get(Calendar.YEAR)== year && 
		   date.get(Calendar.MONTH) == month &&
	       date.get(Calendar.DAY_OF_MONTH) == day)
         return true;
	   return false;
   }
   
   public int compareTo(Appointment other)
   /**
    * This method compares 2 appointment objects
    * @param other
    * @return -1,0 or 1
    */
   {
	   return date.compareTo(other.date);
   }  
}
