import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.EmptyStackException;
import java.util.GregorianCalendar;
import java.util.Stack;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

//Sindy Wang 500766809

public class AppointmentFrame extends JFrame
/**
 * This is the AppointmentFrame class
 */
{
   private static final int FRAME_WIDTH = 800;
   private static final int FRAME_HEIGHT = 800;
   
   private static final int AREA_ROWS = 30;
   private static final int AREA_COLUMNS = 32;
  
   private ArrayList<Appointment> appointments = new ArrayList<Appointment>();
   private Stack<Appointment> appointmentStack = new Stack<Appointment>();
   private Contacts contact = new Contacts();
   private Person person;
   
   Calendar calendar;
   SimpleDateFormat sdf;
   
   private JTextField 	hourText;
   private JLabel 		hourLabel;
   private int 			hour;
   private JTextField 	minuteText;
   private JLabel 		minuteLabel;
   private int 			minute;
   private JTextField 	dayText;
   private JLabel 		dayLabel;
   private int 			day;
   private JTextField 	monthText;
   private JLabel 		monthLabel;
   private int 			month;
   private JTextField 	yearText;
   private JLabel 		yearLabel;
   private int 			year;
   private JLabel 		dateLabel;
   private JTextField 	lastText;
   private JLabel 		lastLabel;
   private JTextField 	firstText;
   private JLabel 		firstLabel;
   private JTextField 	telText;
   private JLabel 		telLabel;
   private JTextField 	emailText;
   private JLabel 		emailLabel;
   private JTextField 	addressText;
   private JLabel 		addressLabel;
   
   private JButton      nextDay;
   private JButton      prevDay;
   private JButton      dateButton;
   private JButton      createAppointment;
   private JButton      cancelAppointment;
   private JButton      findAppointment;
   private JButton      clearAppointment;
   private JButton		recallAppointment;
   private JButton		clearDescription;
   
   private JTextArea    description;
   private JTextArea 	appointmentArea;
   private JScrollPane 	scrollPane;
   private ActionListener listener;

   /**
      Constructs the frame.
   */
   public AppointmentFrame()
   /**
    * This is the constructor of class AppointmentFrame
    */
   {  
	   sdf = new SimpleDateFormat("EEE, MMM dd, yyyy");
	   calendar = new GregorianCalendar();
	   
	   dateLabel = new JLabel(sdf.format(calendar.getTime()));
	   this.setLayout(new BorderLayout());
       add(dateLabel, BorderLayout.NORTH);
   
	   appointmentArea = new JTextArea(AREA_ROWS, AREA_COLUMNS);
	   appointmentArea.setText("");
	   appointmentArea.setEditable(false);
	   scrollPane = new JScrollPane(appointmentArea, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
	   add(scrollPane, BorderLayout.WEST); 
	   
      createMainPanel();
      
      scrollPane.repaint();
      setSize(FRAME_WIDTH, FRAME_HEIGHT);
   }
   
   /**
      
   */
   private void createMainPanel()
   /**
    * This method creates the main panel of AppointmentFrame
    * @param none
    * @return none
    */
   {
	   JPanel contactPanel = createContactPanel();
	   JPanel controlPanel = createControlPanel();
	   
	   JPanel panel = new JPanel();
	   panel.setLayout(new GridLayout(1, 2));
	   panel.add(controlPanel);
	   panel.add(contactPanel);
	   
	   add(panel, BorderLayout.SOUTH);
   }
   private JPanel createContactPanel()
   /**
    * This method creates the contact panel of class AppointmentFrame
    * @param none
    * @return panel
    */
   {
	   JPanel personPanel = createPersonPanel();
	   JPanel descriptionPanel = createDescriptionPanel();
	   JPanel panel = new JPanel();
	   panel.setLayout(new GridLayout(2, 1));
	   
	   panel.add(personPanel);
	   panel.add(descriptionPanel);
	   
	   return panel;
   }
   
   private JPanel createPersonPanel()
   /**
    * This method creates the person panel of class AppointmentFrame
    * @param none
    * @return personPanel
    */
   {
	   JPanel infoPanel = new JPanel();
	   lastText = new JTextField(15);
	   lastLabel = new JLabel("Last Name");
	   firstText = new JTextField(15);
	   firstLabel = new JLabel("First Name");
	   telText = new JTextField(15);
	   telLabel = new JLabel("tel");
	   emailText = new JTextField(15);
	   emailLabel = new JLabel("email");
	   infoPanel.setLayout(new GridLayout(4, 2));
	   infoPanel.add(lastLabel);
	   infoPanel.add(firstLabel);
	   infoPanel.add(lastText);
	   infoPanel.add(firstText);
	   infoPanel.add(telLabel);
	   infoPanel.add(emailLabel);
	   infoPanel.add(telText);
	   infoPanel.add(emailText);
	   
	   JPanel addressPanel = new JPanel();
	   addressText = new JTextField(30);
	   addressLabel = new JLabel("address");
	   addressPanel.setLayout(new GridLayout(2, 1));
	   addressPanel.add(addressLabel);
	   addressPanel.add(addressText);
	   
	   JPanel contactButtonPanel = new JPanel();
	   findAppointment = new JButton("Find");
	   listener = new FindContactListener();
	   findAppointment.addActionListener(listener);
	   clearAppointment = new JButton("Clear");
	   listener = new ClearAppointmentListener();
	   clearAppointment.addActionListener(listener);
	   contactButtonPanel.add(findAppointment);
	   contactButtonPanel.add(clearAppointment);
	   
	   JPanel personPanel = new JPanel();
	   personPanel.setBorder(new TitledBorder(new EtchedBorder(), "Contact"));
	   personPanel.setLayout(new GridLayout(3, 1));
	   personPanel.add(infoPanel);
	   personPanel.add(addressPanel);
	   personPanel.add(contactButtonPanel);
	   return personPanel;
   }
   
   private JPanel createControlPanel()
   /**
    * This method creates the control panel of class AppointmentFrame
    * @param none
    * @return controlPanel
    */
   {
      JPanel datePanel        = createDatePanel();
      JPanel actionPanel      = actionAppointmentPanel();

      JPanel controlPanel = new JPanel();
      controlPanel.setLayout(new GridLayout(2, 1));
      controlPanel.add(datePanel);
      controlPanel.add(actionPanel);

      add(controlPanel, BorderLayout.SOUTH);
      
      return controlPanel;
   }
   
   class PrintNextDayAppointmentsListener implements ActionListener
   /**
    *This is the > action listener class
    */
   {  
      public void actionPerformed(ActionEvent event)
      {  
    	  calendar.add(Calendar.DAY_OF_MONTH, 1);
    	  dateLabel.setText(sdf.format(calendar.getTime()));
    	  dateLabel.repaint();
          printAppointments();
      }
   }
  
   class PrintPrevDayAppointmentsListener implements ActionListener
   /**
    *This is the < action listener class
    */
   {  
      public void actionPerformed(ActionEvent event)
      {  
    	  calendar.add(Calendar.DAY_OF_MONTH, -1);
    	  dateLabel.setText(sdf.format(calendar.getTime()));
    	  dateLabel.repaint();
    	  printAppointments();
      }
   }
   
   class PrintDateAppointmentsListener implements ActionListener
   /**
    *This is the show button action listener class
    */
   {  
      public void actionPerformed(ActionEvent event)
      {  
    	  description.setText("");
          int day   = Integer.parseInt(dayText.getText());
          int month = Integer.parseInt(monthText.getText())-1;
          int year  = Integer.parseInt(yearText.getText());
          if(!(year < 0 || month < 0 || day < 0 || month > 11 || day > Calendar.getInstance().getActualMaximum(Calendar.DAY_OF_MONTH)))
          {
        	  calendar.set(Calendar.YEAR, year);
        	  calendar.set(Calendar.MONTH, month);
        	  calendar.set(Calendar.DAY_OF_MONTH, day);
        	  dateLabel.setText(sdf.format(calendar.getTime()));
        	  dateLabel.repaint();
        	  printAppointments();
          }
          else
        	  description.setText("INVALID DATE!!");
      }
   }

   private JPanel createDatePanel()
   /**
    * This method creates the date panel of class AppointmentFrame
    * @param none
    * @return datePanel
    */
   {
	   JPanel nextPanel = new JPanel();
	   prevDay = new JButton("<");
	   nextDay = new JButton(">");
	     
	   ActionListener listener = new PrintNextDayAppointmentsListener();
	   nextDay.addActionListener(listener);
	   listener = new PrintPrevDayAppointmentsListener();
	   prevDay.addActionListener(listener); 
	   nextPanel.add(prevDay);
	   nextPanel.add(nextDay);
	   
	   JPanel dateEnterPanel = new JPanel();
	   dayText = new JTextField(2);
	   dayLabel = new JLabel("Day");
	   dateEnterPanel.add(dayLabel);
	   dateEnterPanel.add(dayText);
	   monthText = new JTextField(2);
	   monthLabel = new JLabel("Month");
	   dateEnterPanel.add(monthLabel);
	   dateEnterPanel.add(monthText);
	   yearText = new JTextField(4);
	   yearLabel = new JLabel("Year");
	   dateEnterPanel.add(yearLabel);
	   dateEnterPanel.add(yearText);
	   
	   JPanel showPanel = new JPanel();
	   dateButton = new JButton("Show");	   
       listener = new PrintDateAppointmentsListener();
       dateButton.addActionListener(listener);
       showPanel.add(dateButton);
              
       JPanel datePanel = new JPanel();
       datePanel.setBorder(new TitledBorder(new EtchedBorder(), "Date"));
       datePanel.setLayout(new GridLayout(3, 1));
       datePanel.add(nextPanel);
       datePanel.add(dateEnterPanel);
       datePanel.add(showPanel);
      
       return datePanel;
   }

   
   class CreateAppointmentListener implements ActionListener
   /**
    * This is the create button action listener class
    */
   {  
      public void actionPerformed(ActionEvent event)
      {  
         createAppointment();
      }
   }
  
   class CancelAppointmentListener implements ActionListener
   /**
    * This is the cancel button action listener class
    */
   {  
      public void actionPerformed(ActionEvent event)
      {  
         cancelAppointment();
      }
   }
   
   class RecallAppointmentListener implements ActionListener
   /**
    * This is the recall button action listener class
    */
   {
	   public void actionPerformed(ActionEvent event)
	   {
		   recallAppointment();
	   }
   }
   
   class FindContactListener implements ActionListener
   /**
    * This is the find button action listener class
    */
   {
	   public void actionPerformed(ActionEvent event)
	   {
		   findContact();
	   }
   }
   
   class ClearAppointmentListener implements ActionListener
   /**
    * This is the clear appointment button action listener class
    */
   {
	   public void actionPerformed(ActionEvent event)
	   {
		   clearAppointment();
	   }
   }
   
   class ClearDescriptionListener implements ActionListener
   /**
    * This is the clear description button action listener class
    */
   {
	   public void actionPerformed(ActionEvent event)
	   {
		   description.setText("");
		   hourText.setText("");
		   minuteText.setText("");
	   }
   }
   /**
     
   */
   private JPanel actionAppointmentPanel()
   /**
    * This method creates the action panel of class AppointmentFrame
    * @param none
    * @return panel
    */
   {
	  JPanel timePanel = new JPanel();
      hourText = new JTextField(4);
      hourLabel = new JLabel("Hour");
      minuteText = new JTextField(4);
      minuteLabel = new JLabel("Minute");
      timePanel.add(hourLabel);
      timePanel.add(hourText);
      timePanel.add(minuteLabel);
      timePanel.add(minuteText);
      
      JPanel actionPanel = new JPanel();
      createAppointment = new JButton("CREATE");
      listener = new CreateAppointmentListener();
      createAppointment.addActionListener(listener);
      listener = new CancelAppointmentListener();
      cancelAppointment = new JButton("CANCEL");
      cancelAppointment.addActionListener(listener);
      recallAppointment = new JButton("RECALL");
      listener = new RecallAppointmentListener();
      recallAppointment.addActionListener(listener);
      
      actionPanel.add(createAppointment);
      actionPanel.add(cancelAppointment);
      actionPanel.add(recallAppointment);
      
      JPanel panel = new JPanel();
      panel.setLayout(new GridLayout(2, 1));
      panel.setBorder(new TitledBorder(new EtchedBorder(), "Action"));
      panel.add(timePanel);
      panel.add(actionPanel);
      
      return panel;
   }

   /**
      
   */
   private JPanel createDescriptionPanel()
   /**
    * This method creates the description panel of class AppointmentFrame
    * @param none
    * @return panel
    */
   {
	   JPanel dPanel = new JPanel();
	   description = new JTextArea(4, 20);
	   description.setEditable(true);
	   dPanel.add(description);
	   JPanel bPanel = new JPanel();
	   clearDescription = new JButton("Clear");
	   listener = new ClearDescriptionListener();
	   clearDescription.addActionListener(listener);
	   bPanel.add(clearDescription);
	   JPanel panel = new JPanel();
	   panel.setLayout(new GridLayout(2, 1));
	   panel.add(dPanel);
	   panel.add(bPanel);
	   panel.setBorder(new TitledBorder(new EtchedBorder(), "Description"));

	   return panel;
   }

   /**
      
   */
   private void createAppointment()
   /**
    * This method creates the appointments of class AppointmentFrame
    * @param none
    * @return none
    */
   {  
      int hour, minute;
      
	  String hourString = hourText.getText();
      if (hourString.equals("")) return;
      
	  String minuteString = minuteText.getText();
      if (minuteString.equals(""))
    	  minuteString = "0";
	  
      hour   = Integer.parseInt(hourString);  
      minute = Integer.parseInt(minuteString);
      
      int year = calendar.get(Calendar.YEAR);
	  int month = calendar.get(Calendar.MONTH); 
	  int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
      
	  calendar.set(Calendar.HOUR_OF_DAY, hour);
	  calendar.set(Calendar.MINUTE, minute);
	  description.setText("");
	  String descr = description.getText();
	  
	  if(!(hour < 0 || minute < 0 || hour > 24 || minute > 59))
	  {
		  if (!findAppointment(year, month, dayOfMonth, hour, minute))
		  {  
			  if(!(lastText.getText().trim().equals("") && firstText.getText().trim().equals("") && addressText.getText().trim().equals("") && telText.getText().trim().equals("") && emailText.getText().trim().equals("")))
			  {
				  String last = lastText.getText();
				  String first = firstText.getText();
				  String tel = telText.getText();
				  String address = addressText.getText();
				  String email = emailText.getText();
		  
		  			Appointment appt = new Appointment(year, month, dayOfMonth, hour, minute, descr, last, first, address, tel, email);
		  			appointments.add(appt);
		  			appointmentStack.push(appt);
		  			Collections.sort(appointments);
			  }
			  else
			  {
			  		Appointment appt = new Appointment(year, month, dayOfMonth, hour, minute, descr);
			  		appointments.add(appt);
			  		appointmentStack.push(appt);
			  		Collections.sort(appointments);
			  }	

			  printAppointments();
     
			  description.setText("");
			  hourText.setText("");
			  minuteText.setText("");
		  }
  
		  else
			  description.setText("CONFLICT!!");
	  }
	  else
		  description.setText("INVALID TIME!!");
	  
      scrollPane.repaint();
      
   }
   
   private void cancelAppointment()
   /**
    * This method cancels the appointments of class AppointmentFrame
    * @param none
    * @return none
    */
   {  
      int hour, minute;
      int cancelledAppt = -1;
      
	  String hourString = hourText.getText();
      if (hourString.equals("")) return;
      
      String minuteString = minuteText.getText();
      if (minuteString.equals(""))
    	  minuteString = "0";
	  
      hour   = Integer.parseInt(hourString);  
      minute = Integer.parseInt(minuteString);
      
      int year = calendar.get(Calendar.YEAR);
	  int month = calendar.get(Calendar.MONTH); 
	  int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
      
	  for (int i = 0; i < appointments.size(); i++)
	  {
		   Appointment appt = appointments.get(i);
		   		   
		   if (appt.occursOn(year, month, dayOfMonth, hour, minute))
		   {
		      cancelledAppt = i;
		      break;
		   }
	  }
	  appointments.remove(cancelledAppt);
	  /*
	 Stack<Appointment> temp = new Stack<Appointment>();
	 while(!appointmentStack.isEmpty())
	 {
		 Appointment check = appointmentStack.pop();
		 if(check.compareTo(appointments.get(cancelledAppt)) == 0)
		 {
			 appointmentStack.pop();
		 }
		 else
		 {
			 temp.push(check);
		 }
	 }
	 while(!temp.isEmpty())
	 {
		Appointment tmp = temp.pop();
		appointmentStack.push(tmp);
	 }
	 */
	  
	  if(recallAppointment())
	  {
		  appointmentStack.pop();
	  }
      
	  Collections.sort(appointments);
      
      printAppointments();
      
      description.setText("");
      hourText.setText("");
      minuteText.setText("");
               
      scrollPane.repaint();
   }
   
   private void printAppointments()
   /**
    * This method prints the appointments of class AppointmentFrame
    * @param none
    * @return none
    */
   {
	   // Get date of current appointment day
	   int year = calendar.get(Calendar.YEAR);
	   int month = calendar.get(Calendar.MONTH); 
	   int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
	   
	   appointmentArea.setText("");
	   
	   for (int i = 0; i < appointments.size(); i++)
	   {
		   Appointment appt = appointments.get(i);
		  
		   if (appt.occursOn(year, month, dayOfMonth))
		      appointmentArea.append(appt.print() + "\n\n");
	   }
   }
   
   private boolean findAppointment(int year, int month, int dayOfMonth, int hour, int minute)
   /**
    * This method finds the appointments of class AppointmentFrame
    * @param none
    * @return boolean
    */
   {
	   for (int i = 0; i < appointments.size(); i++)
	   {
		   Appointment appt = appointments.get(i);
		  
		   if (appt.occursOn(year, month, dayOfMonth, hour, minute))
		     return true;
	   }
	   return false;
   }
   
   private boolean recallAppointment()
   /**
    * This method recalls the appointments of class AppointmentFrame
    * @param none
    * @return boolean
    */
   {
	   if(!(appointmentStack.isEmpty()))
	   {
	   Appointment recalled = appointmentStack.peek();
	   Calendar recalledDate = recalled.getDate();
	   int recallYear = recalledDate.get(Calendar.YEAR);
	   int recallMonth = recalledDate.get(Calendar.MONTH);
	   int recallDay = recalledDate.get(Calendar.DAY_OF_MONTH);
	   int recallHour = recalledDate.get(Calendar.HOUR_OF_DAY);
	   int recallMin = 	recalledDate.get(Calendar.MINUTE);
	   
	   hourText.setText(Integer.toString(recallHour));
	   minuteText.setText(Integer.toString(recallMin));
	   calendar.set(Calendar.YEAR, recallYear);
	   calendar.set(Calendar.MONTH, recallMonth);
	   calendar.set(Calendar.DAY_OF_MONTH, recallDay);
 	   dateLabel.setText(sdf.format(calendar.getTime()));
 	   dateLabel.repaint();
 	   printAppointments();
	   }
	   else
		   description.setText("NO APPOINTMENTS!!");
	   return true;
   }
   
   private void findContact()
   /**
    * This method finds the contacts of class AppointmentFrame
    * @param none
    * @return none
    */
   {
	   Person found = null;
	   if(telText.getText().trim().equals("") && emailText.getText().trim().equals(""))
	   {
		   if(contact.findPersonName(lastText.getText(), firstText.getText()) != null)
		   {
			   found = contact.findPersonName(lastText.getText(), firstText.getText());
			   lastText.setText(found.getLast());
			   firstText.setText(found.getFirst());
			   telText.setText(found.getTel());
			   emailText.setText(found.getEmail());
			   addressText.setText(found.getAddress());
		   }
	   }
	   
	   if(lastText.getText().trim().equals("") && firstText.getText().trim().equals("") && emailText.getText().trim().equals(""))
	   {
		   if(contact.findPersonTel(telText.getText()) != null)
		   {
			   found = contact.findPersonTel(telText.getText());
			   lastText.setText(found.getLast());
			   firstText.setText(found.getFirst());
			   telText.setText(found.getTel());
			   emailText.setText(found.getEmail());
			   addressText.setText(found.getAddress());
		   }
	   }
	   
	   if(lastText.getText().trim().equals("") && firstText.getText().trim().equals("") && telText.getText().trim().equals(""))
	   {
		   if(contact.findPersonEmail(emailText.getText()) != null)
		   {
			   found = contact.findPersonTel(telText.getText());
			   lastText.setText(found.getLast());
			   firstText.setText(found.getFirst());
			   telText.setText(found.getTel());
			   emailText.setText(found.getEmail());
			   addressText.setText(found.getAddress());
		   }
	   }
   }
   
   private void clearAppointment()
   /**
    * This method clears the contact text boxes of class AppointmentFrame
    * @param none
    * @return none
    */
   {
	   lastText.setText("");
       firstText.setText("");
       telText.setText("");
       emailText.setText("");
       addressText.setText("");
   }
   
 }
