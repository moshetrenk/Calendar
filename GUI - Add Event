package cal;

/* This class is called from the profile screen when a user 
 * clicks add event. It is distinctly more difficult than it 
 * might seem at first glance.
 */
import javax.swing.text.BadLocationException;
import java.awt.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.awt.event.*;
import java.io.IOException;
import javax.swing.*; 

@SuppressWarnings({"serial", "static-access"})
public class AddEvent extends JFrame {
    static LocalDateTime today;
    boolean startHourPicked = false, endHourPicked = false, 
    			groupPicked = false, isGroupEvent = false;
    static User user, userForAddEvent;
    private List listOfGroups;
    private static String whichGroupToAddTo, username, fullDate, eventNameAtChosenTime;
    private String[] groups;
    private int startTimeHour, startTimeMinute, endTimeHour, endTimeMinute;
	private static int month;
	private static int date;
	private static int year;
	private static int startTime, endTime;
    private static SQLHelper sqlHelper = new SQLHelper();
    private static AddEvent one;
    private JLabel charactersRemainingLabel = new JLabel(), eventDateLabel = new JLabel(),
    			eventDetailsLabel = new JLabel(), eventNameLabel = new JLabel(), 
    			eventTimeLabel = new JLabel(), eventTimeLabel1 = new JLabel(),
    			invalidTimeLabel = new JLabel(), titleLabel = new JLabel();
    private JButton buttonAmPm = new JButton(), 
    			buttonAmPm2 = new JButton(), completeButton = new JButton();
    private JComboBox<String> monthDropDown = new JComboBox<String>(), 
    		startHourDropDown = new JComboBox<String>(), startMinDropDown = new JComboBox<String>(),
    			yearDropDown = new JComboBox<String>(), dayDropDown = new JComboBox<String>(), 
    			endHourDropDown = new JComboBox<String>(), endMinDropDown = new JComboBox<String>();
    private static JComboBox<String> groupComboBox = new JComboBox<String>();
    private JCheckBox isGroupEventCheckBox = new JCheckBox();
    private JScrollPane jScrollPane2 = new JScrollPane();
    private JTextField eventNameTextBox = new JTextField();
    private JTextPane eventDescriptionTextBox = new JTextPane();
	@SuppressWarnings("unused")
	private Event event;
    
    public AddEvent() {
    	//the following checks the operating system and displays the menubar accordingly
	//that is, macs should have the menu bar up top and windows should use the current wwindow
    	try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}

    	String lcOSName = System.getProperty("os.name").toLowerCase();
		boolean IS_MAC = lcOSName.startsWith("mac os x");
		System.setProperty("apple.laf.useScreenMenuBar", String.valueOf(IS_MAC));
     
		initComponents();
		
		//the following method is used to close the path of the local instance of SQLHelper
		//upon the user's click of the exit button
		addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
            	sqlHelper.closePath();
            	System.exit(1);
            	//setVisible(false);
            }
        });
    }

    private void initComponents() {
    	today = LocalDateTime.now();
    	setJMenuBar(MenuExp.PrepareMenuBar(user));
    	setTitle("Add Event");
        titleLabel.setFont(new java.awt.Font("Tahoma", 1, 18));
        titleLabel.setText("Add Event");
        eventNameLabel.setFont(new java.awt.Font("Tahoma", 0, 16));
        eventNameLabel.setText("Event Name");
        eventDateLabel.setFont(new java.awt.Font("Tahoma", 0, 16)); 
        eventDateLabel.setText("Event Date");
        eventTimeLabel.setFont(new java.awt.Font("Tahoma", 0, 16)); 
        eventTimeLabel.setText("Event Start");
        eventNameTextBox.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                eventNameTextBoxKeyReleased(evt);
            }
        });
        eventDetailsLabel.setFont(new java.awt.Font("Tahoma", 0, 16)); 
        eventDetailsLabel.setText("Event Details");
        eventDescriptionTextBox.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                eventDescriptionTextBoxKeyReleased(evt);
            }
        });
        jScrollPane2.setViewportView(eventDescriptionTextBox);
	
        if (today.getHour() < 12){
        	buttonAmPm.setText("AM");
		buttonAmPm2.setText("AM");
	}
        else {
        	buttonAmPm.setText("PM");
		buttonAmPm2.setText("PM");
        }
        
        buttonAmPm.setMaximumSize(new java.awt.Dimension(49, 23));
        buttonAmPm.setMinimumSize(new java.awt.Dimension(49, 23));
        buttonAmPm.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                buttonAmPmMouseReleased(evt);
            }
        });

        charactersRemainingLabel.setText("Characters remaining: 140");

        completeButton.setText("Ok");
        completeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                completeButtonActionPerformed(evt);
            }
        });

        isGroupEventCheckBox.setText("Group Event");
        isGroupEventCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                isGroupEventCheckBoxActionPerformed(evt);
            }
        });

        getGroups();
        groupComboBox.setEnabled(false);
        groupComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                groupComboBoxActionPerformed(evt);
            }
        });

        eventTimeLabel1.setFont(new java.awt.Font("Tahoma", 0, 16)); 
        eventTimeLabel1.setText("Event End");

        String[] startHours = new String[12];
        for(int i = 0; i < 12; i++){
            startHours[i] = Integer.toString(i + 1);
        }
        startHourDropDown.setModel(new  DefaultComboBoxModel<>(startHours));
        
        startHourDropDown.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startHourDropDownActionPerformed(evt);
            }
        });

        startMinDropDown.setModel(new  DefaultComboBoxModel<>(new String[] { "00", "30" }));
        startMinDropDown.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startMinDropDownActionPerformed(evt);
            }
        });

        String[] hours = new String[12];
        for(int i = 0; i < 12; i++){
            hours[i] = Integer.toString(i + 1);
        }
        
        endHourDropDown.setModel(new  DefaultComboBoxModel<>(hours));
        endHourDropDown.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                endHourDropDownActionPerformed(evt);
            }
        });

        endMinDropDown.setModel(new  DefaultComboBoxModel<>(new String[] { "00", "30"}));
        endMinDropDown.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                endMinDropDownActionPerformed(evt);
            }
        });

        buttonAmPm2.setMaximumSize(new java.awt.Dimension(49, 23));
        buttonAmPm2.setMinimumSize(new java.awt.Dimension(49, 23));

        buttonAmPm2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAmPm2ActionPerformed(evt);
            }
        });

        monthDropDown.setModel(new  DefaultComboBoxModel<>(new String[] {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"}));
        monthDropDown.setSelectedIndex(today.getMonth().getValue() - 1);
        monthDropDown.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                monthDropDownActionPerformed(evt);
            }
        });

        String[] days = new String[31];
        for(int i = 0; i < 31; i++){
            days[i] = Integer.toString(i + 1);
        }
        dayDropDown.setModel(new  DefaultComboBoxModel<>(days));
        dayDropDown.setSelectedIndex(today.getDayOfMonth() - 1);
        dayDropDown.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dayDropDownActionPerformed(evt);
            }
        });

        String[] years = new String[50];
        for(int i = 0; i < 50; i++){
            years[i] = Integer.toString(2016 + i);
        }
        yearDropDown.setModel(new  DefaultComboBoxModel<>(years));
        yearDropDown.setSelectedIndex(today.getYear() - 2016);
        yearDropDown.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                yearDropDownActionPerformed(evt);
            }
        });

        invalidTimeLabel.setText("End Time Should Be After Start Time");
        invalidTimeLabel.setVisible(false);
        
        month = today.getMonthValue();
        date = today.getDayOfMonth();
        year = today.getYear();
        startTimeHour = (today.getHour() % 12) * 1000;
        endTimeHour = (today.getHour() % 12) * 1000 + 1000;
        
        //in the following if-else the default timing is prepared for events that overlap between AM/PM periods
        if(today.getHour() != 11 && today.getHour() != 12 && today.getHour() != 23 && today.getHour() != 24){
        	if(today.getMinute() < 30){
        		startHourDropDown.setSelectedIndex(today.getHour() % 12 - 1);
        		startMinDropDown.setSelectedIndex(1);
        	}
        	else 
        		startHourDropDown.setSelectedIndex(today.getHour() % 12);
        	
    		endHourDropDown.setSelectedIndex(today.getHour() % 12);
    	}
    	else {
    		startHourDropDown.setSelectedIndex(today.getHour() % 12 - 1);
    		endHourDropDown.setSelectedIndex(0);
    	}
	
        int a = -1;
        a = today.getMinute() >= 30 ? 1 : 0;        	
        endMinDropDown.setSelectedIndex(a);
        
        //(mostly) autogenerated code starts here
        GroupLayout layout = new  GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup( GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup( GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(eventDetailsLabel, GroupLayout.PREFERRED_SIZE, 120, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap( LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup( GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, GroupLayout.PREFERRED_SIZE, 209, GroupLayout.PREFERRED_SIZE)
                            .addComponent(charactersRemainingLabel, GroupLayout.PREFERRED_SIZE, 182, GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup( GroupLayout.Alignment.LEADING)
                            .addComponent(eventNameLabel, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
                            .addComponent(eventTimeLabel, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
                            .addComponent(eventTimeLabel1, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
                            .addComponent(eventDateLabel, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE))
                        .addGap(40, 40, 40)
                        .addGroup(layout.createParallelGroup( GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup( GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(startHourDropDown, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(startMinDropDown, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(endHourDropDown, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(endMinDropDown, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup( GroupLayout.Alignment.LEADING, false)
                                    .addComponent(buttonAmPm2, GroupLayout.DEFAULT_SIZE, 49, Short.MAX_VALUE)
                                    .addComponent(buttonAmPm, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(invalidTimeLabel, GroupLayout.PREFERRED_SIZE, 212, GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(titleLabel, GroupLayout.PREFERRED_SIZE, 128, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap( LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(isGroupEventCheckBox))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup( GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(monthDropDown, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(dayDropDown, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                                    .addComponent(eventNameTextBox, GroupLayout.PREFERRED_SIZE, 144, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap( LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup( GroupLayout.Alignment.LEADING)
                                    .addComponent(groupComboBox, 0, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(yearDropDown, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE)))))))
                .addContainerGap())
            .addGroup( GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap( GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(completeButton, GroupLayout.PREFERRED_SIZE, 202, GroupLayout.PREFERRED_SIZE)
                .addGap(106, 106, 106))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup( GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup( GroupLayout.Alignment.BASELINE)
                    .addComponent(titleLabel, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
                    .addComponent(isGroupEventCheckBox))
                .addPreferredGap( LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup( GroupLayout.Alignment.BASELINE)
                    .addComponent(eventNameLabel)
                    .addComponent(eventNameTextBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(groupComboBox, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap( LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup( GroupLayout.Alignment.BASELINE)
                    .addComponent(eventDateLabel, GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE)
                    .addComponent(monthDropDown, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(dayDropDown, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(yearDropDown, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup( GroupLayout.Alignment.LEADING, false)
                    .addComponent(buttonAmPm, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup( GroupLayout.Alignment.BASELINE)
                        .addComponent(eventTimeLabel)
                        .addComponent(startHourDropDown, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(startMinDropDown, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup( GroupLayout.Alignment.LEADING, false)
                    .addComponent(buttonAmPm2, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(layout.createParallelGroup( GroupLayout.Alignment.BASELINE)
                        .addComponent(eventTimeLabel1)
                        .addComponent(endHourDropDown, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                        .addComponent(endMinDropDown, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap( LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(invalidTimeLabel)
                .addGap(1, 1, 1)
                .addGroup(layout.createParallelGroup( GroupLayout.Alignment.TRAILING)
                    .addComponent(eventDetailsLabel)
                    .addComponent(jScrollPane2, GroupLayout.PREFERRED_SIZE, 54, GroupLayout.PREFERRED_SIZE))
                .addPreferredGap( LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(charactersRemainingLabel)
                .addPreferredGap( LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(completeButton)
                .addGap(33, 33, 33))
        );
        
        setResizable(false);
        pack();
    }	//end of autogenerated code
    

    /*
     * When the user is entering the event description, as they type, they can see
     * a label live update the amount of remaining characters. If they go over the
     * limit then they will no longer be able to add characters before removing
     */
    private void eventDescriptionTextBoxKeyReleased(java.awt.event.KeyEvent evt) {    
    	int maxSize = 140;
    	
    	if(eventDescriptionTextBox.getText().contains("'")){
        	maxSize--;
        }
    	
        if (eventDescriptionTextBox.getText().length() >= maxSize){
	        try {
                    eventDescriptionTextBox.setText(eventDescriptionTextBox.getText(0, maxSize));
                    } catch (BadLocationException e) {
                        e.printStackTrace();
                    }
	            	Color c = new Color(255, 0, 51);
                        charactersRemainingLabel.setForeground(c);
            	}
            	else {
            		Color c = new Color(0, 0, 0);
                        charactersRemainingLabel.setForeground(c);
            	}

        charactersRemainingLabel.setText("Characters Remaining: " + (maxSize - eventDescriptionTextBox.getText().length()));  	 
    }                                                   

    //user can click on the button to toggle between AM and PM for the two buttons
    private void buttonAmPmMouseReleased(java.awt.event.MouseEvent evt) {                                         
        if(buttonAmPm.getText().equals("AM"))
    	   buttonAmPm.setText("PM");
       else
    	   buttonAmPm.setText("AM");
    }     
    
    private void buttonAmPm2ActionPerformed(java.awt.event.ActionEvent evt) {                                            
        if(buttonAmPm2.getText().equals("AM"))
     	   buttonAmPm2.setText("PM");
        else
     	   buttonAmPm2.setText("AM");
     }  

    //users can toggle event status as being part of a group or not
    private void isGroupEventCheckBoxActionPerformed(java.awt.event.ActionEvent evt) { 
    	boolean a;
    	a = groupComboBox.isEnabled() ? false : true;
    	groupComboBox.setEnabled(a);
    }                                                 

    //sets the group of choice
    private void groupComboBoxActionPerformed(java.awt.event.ActionEvent evt) {     
    	groupPicked = true;
        whichGroupToAddTo = groups[groupComboBox.getSelectedIndex()];    
    }                                                                                                                         

    //This method enables the user to choose from the correct number of days in the
    //month and year of their choice. For example, if the user selected January then
    //the drop down will offer 31 days, if the user selects Febuary and the year is 
    //a leap year then the drop down will offer 29 days etc.
    private void correctTheDaysDropDown(){
        String[] days  = {};
        if(month == 2 && isLeapYear(year)){
            days = new String[29];
            for(int i = 0; i < 29; i++){
                days[i] = Integer.toString(i + 1);
            }
        }
        else if (month == 2 && !isLeapYear(year)){
            days = new String[28];
            for(int i = 0; i < 28; i++){
                days[i] = Integer.toString(i + 1);
            }
        }
        else if (month == 4 || month == 6 || month == 9 || month == 11){
            days = new String[30];
            for(int i = 0; i < 30; i++){
                days[i] = Integer.toString(i + 1);
            }
        }
        else if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12){
            days = new String[31];
            for(int i = 0; i < 31; i++){
                days[i] = Integer.toString(i + 1);
            }
        }
        
        dayDropDown.setModel(new  DefaultComboBoxModel<>(days));
    }
    
    //The month is selected and then the days drop down is set to allow
    //the user options for the correct number of days in that month
    private void monthDropDownActionPerformed(java.awt.event.ActionEvent evt) {                                              
        month = monthDropDown.getSelectedIndex() + 1;
        correctTheDaysDropDown();
    }                                             

    private void dayDropDownActionPerformed(java.awt.event.ActionEvent evt) {                                            
        date = dayDropDown.getSelectedIndex() + 1;
    }                                           

    //year is selected, if it's a leap year then the days drop down is corrected again
    private void yearDropDownActionPerformed(java.awt.event.ActionEvent evt) {                                             
        year = yearDropDown.getSelectedIndex() + 1;
        year += 2015;
        
        if(isLeapYear(year) && month == 2)
        	correctTheDaysDropDown();
    }                                            
    
    private boolean isLeapYear(int leap){
        return ((leap % 400 == 0) || ((leap % 4 == 0) && (leap % 100 != 0)));
    }
    
    private void startHourDropDownActionPerformed(java.awt.event.ActionEvent evt) {                                                  
        startHourPicked = true;
    	startTimeHour = startHourDropDown.getSelectedIndex() + 1;
    }                                                 

    private void startMinDropDownActionPerformed(java.awt.event.ActionEvent evt) {                                                 
        startTimeMinute = startMinDropDown.getSelectedIndex();
        startTimeMinute *= 30;
    }                                                

    private void endHourDropDownActionPerformed(java.awt.event.ActionEvent evt) {  
    	endHourPicked = true;
        endTimeHour = endHourDropDown.getSelectedIndex() + 1;
    }                                               

    private void endMinDropDownActionPerformed(java.awt.event.ActionEvent evt) {                                               
        endTimeMinute = endMinDropDown.getSelectedIndex();
        endTimeMinute *= 30;
    }                                              

    private void eventNameTextBoxKeyReleased(java.awt.event.KeyEvent evt) { 
    /*
     * Users can enter the event name here, the entry is limited to 16 chars
     * If a user goes above 16, they will be unable to enter more characters
     * 
     *     
     * note: if event name contains two apostrophes, a SQL exception will be thrown
     * ie if the user enter's Dad's son's then the first apostrophe will be escape
     * sequenced but the second is going to be interpreted by SQL as the end of the 
     * event name and whatever comes after will be seen as a syntax error
     * This same issue applies to event description
    */ 
    	int maxSize = 16;
    	
    	if(eventNameTextBox.getText().contains("'")){
        	maxSize--;
        }
        if(eventNameTextBox.getText().length() >= 16){
        	try{
                eventNameTextBox.setText(eventNameTextBox.getText(0, maxSize));
            }catch (BadLocationException e) {
                e.printStackTrace();
            }
        }
    }                                            
   
    
  
    
    private void addEventToDataBase(){
    /*
     * in order to add the event to the database, a lot of things need to happen, here's the step-by-step
     */
    	//if the user didn't select a group but did select groupEvent then 
    	//it is clearly intended that it be the first group in the drop down
    	//becuase it appears as already selected so it is set to the zeroeth element
    	if(groupComboBox.isEnabled() && !groupPicked){
    		whichGroupToAddTo = groups[0];
    	}
    	
    	if(!startHourPicked)
    		startTimeHour = today.getHour();
    	if (!endHourPicked)
    		endTimeHour = today.getHour() + 1;
    	
    	String eventName = eventNameTextBox.getText();
    	
    	/*
    	 * since SQL's string delimiter is a single apostrophe, if the user entered any apostrophes,
    	 * they need to get an escape sequence added to prevent the application from crashing. It should
    	 * be noted that if the user enters more than one apostrophe then the program will crash
    	 */
    	if (eventName.contains("'")){
    		int f = eventName.indexOf('\'');
    		eventName = eventName.substring(0, f) + "\\'" + eventName.substring(f + 1, eventName.length());
    		System.out.println(eventName);
    	}
    	
    	String eventDescription = eventDescriptionTextBox.getText();
    	if (eventDescription .contains("'")){
    		int f = eventDescription .indexOf('\'');
    		eventDescription = eventDescription.substring(0, f) + "\\'" + eventDescription.substring(f + 1, eventDescription.length());
    		System.out.println(eventDescription);
    	}
    	
    	//the number of approvals needed is selected, if it's a personal event then 
    	//0 will be returned and so it is set to 1
        int numMem = sqlHelper.getNumberOfGroupMembers(whichGroupToAddTo);
        if(numMem == 0)
    	   numMem++;       
       
        username = user.getUsername();
        
        //if it's not a groupEvent then the user_groupID is the username
        if(!groupComboBox.isEnabled()){
        	whichGroupToAddTo = username;
        }
        
        //an arraylist must be put together containing all of the above 
        //in order to add the event
        ArrayList<Object> insert = new ArrayList<Object>();
        insert.add(sqlHelper.getHighestPrimaryKey("theEvent") + 1);
        insert.add(eventName);
        insert.add(whichGroupToAddTo);
        
        //the string for date must be a 10 char string in the form of MM/DD/YYYY
        //if the month or day is < 10 then place a zero in the empty place(s)
        if(month < 10 || date < 10){
        	if (month < 10 && date >= 10)
        		insert.add("0" + month + "/" + date + "/" + year);
        	else if (month >= 10 && date < 10)
        		insert.add(month + "/" + "0" + date + "/" + year);
        	else
        		insert.add("0" + month + "/" + "0" + date + "/" + year);
        }       
        
        insert.add(startTime);
        insert.add(endTime);
        insert.add(eventDescription);
        insert.add(1);
        insert.add(numMem);
        
        System.out.println("insert = " + insert);
        
        //if the time is available then insert the event and remove the screen
       if(sqlHelper.isEventTimeAvailable(insert)){
    	   System.out.println("entered if at addevent.632");
    	   System.out.println("insert = " + insert);
    	   if (groupComboBox.isEnabled()){
    		  Event event = new Event(insert);
    		  //System.out.println(insert);
    		  //System.out.println(username);
    		  //System.out.println(whichGroupToAddTo);
    		  Group group =  (new Group(username, whichGroupToAddTo));
    		  //System.out.println(group.getGroupName());
    		  group.sendEventNotifications(event);
    	   }
    	   sqlHelper.insertEvent(insert);
    	   user.addEvent(new Event(insert));
    	   Profile.makeFrameVisible(false);
           setVisible(false);
           
            try {
   				Profile.paintFrame(userForAddEvent);
   			} catch (IOException e1) {
   				// TODO Auto-generated catch block
   				e1.printStackTrace();
   			}
       }
       else{
    	   //if the time is unavailable then if it's a group event, indicate that 
    	   //the time is unavailable, otherwise indicate the event that is clashing
    	   
/*    	   if(groupComboBox.isEnabled())
    		   invalidTimeLabel.setText("That time is unavailable for " + whichGroupToAddTo);
    	   else
    		   invalidTimeLabel.setText("You have \"" + eventNameAtChosenTime + "\" at that time");
  */	   invalidTimeLabel.setText("That time is unavailable");  	   
    	   invalidTimeLabel.setVisible(true);
       }
    }   
    
    private void completeButtonActionPerformed(java.awt.event.ActionEvent evt) { 
    	/*
    	 * upon clicking submit, again a lot needs to happen to verify that 
    	 * the event is valid and should be passed to the addEvent method
    	 */
    	
    	if(!groupComboBox.isEnabled()){
        	username = whichGroupToAddTo;
        }
                
    	//if the start time was not picked then default to the time
    	//that was displayed on the screen which should be the next
    	//possible half hour period of the current day
    	//otherwise, if the time was picked then set it to the form
    	//of sql.time, which is 000000, or 6:30 PM is 183000
        if (!startHourPicked){
		startTime =  (startMinDropDown.getSelectedItem().toString().equals("30") ? today.getHour() * 10000 + 3000 : today.getHour() * 10000;
        }
        else
        	startTime = startTimeHour * 10000 + startTimeMinute * 100;
        
        if(!endHourPicked){
        	endTime = (endMinDropDown.getSelectedItem().toString().equals("30")) ? (today.getHour() + 1) * 10000 + 3000 : (today.getHour() + 1) * 10000;
        }
        else
        	endTime = endTimeHour * 10000 + endTimeMinute * 100;
        
        //if the start or end time is PM then set the SQL time as such
        if (buttonAmPm.getText().equals("PM"))
        	startTime += 120000;
        if (buttonAmPm2.getText().equals("PM"))
        	endTime += 120000;
        
        /*
         * the following things cause the event to not be submitted:
         * 1. the end time is AM but the start time is PM (events must start and end on the same day)
         * 2. the start time is after the end time
         * 3. the year is set to the current year and the month is earlier than the current month
         * 4. the year is set to the current year and the month is set to the current month and the day is earlier than the current day
         * 5. no event name was entered
         * 6. the month, day, and year are set to today's date and the time is before the current time
         */
        
        if((buttonAmPm.getText().equals("PM") 
                && buttonAmPm2.getText().equals("AM")) 
                || (startTime >= endTime) 
                || (month < today.getMonthValue() && year == today.getYear()) 
                || (month == today.getMonthValue() && year == today.getYear() && date < today.getDayOfMonth())
                || eventNameTextBox.getText().length() == 0
                || startTime < (today.getHour() * 10000) && month == today.getMonthValue() && year == today.getYear() && date < today.getDayOfMonth()   )
        {
        	//upon entering this, we are certain one of the above errors occured - this is my preferred variation of a try
        	//now we determine which error it is and indicate the corresponding message to the user
            invalidTimeLabel.setVisible(true);
            if (month < today.getMonthValue() && year == today.getYear())
                invalidTimeLabel.setText("Events Cannot Be In The Past");

            if((buttonAmPm.getText().equals("PM") && buttonAmPm2.getText().equals("AM")) || (startTime >= endTime))
                invalidTimeLabel.setText("End Time Should Be After Start Time");
        
            if(month == today.getMonthValue() && year == today.getYear() && date < today.getDayOfMonth())
                invalidTimeLabel.setText("Events Cannot Be In The Past");
            
            if (eventNameTextBox.getText().length() == 0)
                invalidTimeLabel.setText("Please Enter An Event Name");
            
            if (startTime < (today.getHour() * 10000) && month == today.getMonthValue() && year == today.getYear() && date < today.getDayOfMonth())
                invalidTimeLabel.setText("Events Cannot Be In The Past");
        }
        else{
        	//if nothing went wrong then hide any error messages and submit the event
            invalidTimeLabel.setVisible(false);
            addEventToDataBase();
        }
    }  
    
	//This method sets the groupDropDown to the logged in user's groups
    private void getGroups(){
	
    	
    	listOfGroups = (List)sqlHelper.getGroups(username);
        groups = new String [listOfGroups.getItemCount()];
    	
    	for(int i = 0; i < listOfGroups.getItemCount(); i++){
    		groups[i] = listOfGroups.getItem(i);
    		System.out.println("AddEvent.getGroups print[" + i + "] = " + groups[i]);
    	}
    	
    	groupComboBox.setModel(new  DefaultComboBoxModel<>(groups));
    }
    
    //paint frame is called by other classes to initialize this class
     public static void paintFrame(User locUser){
    	 userForAddEvent = locUser;
    	 user = locUser;
    	 username = user.getUsername();
    	 java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
            	 one = new AddEvent();
             	 JFrame frame = (JFrame) SwingUtilities.getRoot(groupComboBox);
            	 one.centerWindow(frame);
                 one.setVisible(true);
                 Profile.makeFrameVisible(false);
             }
         });
    }
     
    //this method is user to put the window in the middle of the screen
     public static void centerWindow(Window frame){
    	 Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
    	 int x = (int) ((dimension.getWidth() - frame.getWidth())/2);
    	 int y = (int) ((dimension.getHeight() - frame.getHeight())/2);
    	 
    	 frame.setLocation(x, y);
     }
     
     //nothing...
    public static void main(String args[]) {
        try {
            for ( UIManager.LookAndFeelInfo info :  UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                     UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AddEvent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AddEvent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AddEvent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch ( UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AddEvent.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
      
        java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				one = new AddEvent();
             	 JFrame frame = (JFrame) SwingUtilities.getRoot(groupComboBox);
            	 one.centerWindow(frame);
                 one.setVisible(true);
            }
        });
    }

}
