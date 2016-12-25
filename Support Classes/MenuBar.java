package CalendarTest;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.ButtonGroup;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.SwingUtilities;
 
public class MenuExp  {
	static JMenuBar menuBar = new JMenuBar();
	static JMenuBar menuBarLogIn = new JMenuBar();
	static int onlyRunOnce = 0;
	static int onlyRunOnceLogIn = 0;
     
    public MenuExp() {
         
        
        
    }
    public static JMenuBar PrepareMenuBar(User user){
    	
    	if(onlyRunOnce == 0){
    	
        // Define and add two drop down menu to the menubar
        JMenu fileMenu = new JMenu("File");
        JMenu viewMenu = new JMenu("View");
        menuBar.add(fileMenu);
        menuBar.add(viewMenu);
         
        // Create and add simple menu item to one of the drop down menu
        JMenuItem logOffAction = new JMenuItem("Log Off");
        JMenuItem exitAction = new JMenuItem("Exit");
        JMenuItem viewProfileAction = new JMenuItem("Profile");
        JMenuItem viewCalendarAction = new JMenuItem("Calendar");
        JMenuItem viewDailyAction = new JMenuItem("Daily Events");
        JMenuItem viewNotificationAction = new JMenuItem("Notifications");
        
        fileMenu.add(logOffAction);
        fileMenu.add(exitAction);
        fileMenu.addSeparator();
        fileMenu.add(exitAction);
        viewMenu.add(viewProfileAction);
        viewMenu.add(viewCalendarAction);
        viewMenu.add(viewDailyAction);
        viewMenu.add(viewNotificationAction);
       
        // Add a listener to the New menu item. actionPerformed() method will
        // invoked, if user triggred this menu item
        logOffAction.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
               
                JFrame frame = (JFrame) SwingUtilities.getRoot(menuBar);
        		frame.setVisible(false);
        		 Login.main(null);
            }
        });
        
        exitAction.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent arg0){
        		System.exit(1);
        	}
        });
        
        viewProfileAction.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent arg0){
        		JFrame frame = (JFrame) SwingUtilities.getRoot(menuBar);
        		frame.setVisible(false);
        		
        		try {
					Profile.paintFrame(user);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        		
        	}
        });
        
        viewCalendarAction.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent arg0){
        		
        		JFrame frame = (JFrame) SwingUtilities.getRoot(menuBar);
        		frame.setVisible(false);
        		CalendarProgram.setMonthBeforeToNull();
        		CalendarProgram.paintFrame(user);
        	}
        });
        
        
        viewDailyAction.addActionListener(new ActionListener(){
	    	public void actionPerformed(ActionEvent arg0){
	    		JFrame frame = (JFrame) SwingUtilities.getRoot(menuBar);
	    		frame.setVisible(false);
	    		
	    		String timeStamp = new SimpleDateFormat("MMddyyy").format(Calendar.getInstance().getTime());
	    		
	    		DailyEvents.paintFrame(timeStamp, user);
	    		
	    	}
	    	
	    });
        viewNotificationAction.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent arg0){
        		JFrame frame = (JFrame) SwingUtilities.getRoot(menuBar);
        		frame.setVisible(false);
        		
        		NotificationScreen.paintFrame(user);
        	}
        });
        
        
        onlyRunOnce = 1;
    	}
    	
    	
    	
    	//onlyRunOnceLogIn = 0;
        return menuBar;
        
        
        
        
    }
   
    public static JMenuBar PrepareMenuBar(String logIn){
    	
    	if(onlyRunOnceLogIn == 0){
    		
    	JMenu fileMenu = new JMenu("File");
    	menuBarLogIn.add(fileMenu);
    	JMenuItem exitAction = new JMenuItem("Exit");
    	fileMenu.add(exitAction);
    	
    	 exitAction.addActionListener(new ActionListener(){
         	public void actionPerformed(ActionEvent arg0){
         		System.exit(1);
         	}
         });
    	}
    	//onlyRunOnce =0;
    	onlyRunOnceLogIn = 1;
    	 return menuBarLogIn;
    	
    }
    public static void main(String[] args) {
        MenuExp me = new MenuExp();
       
    }
}
