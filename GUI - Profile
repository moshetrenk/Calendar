package CalendarTest;

import javax.imageio.IIOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;
import javax.swing.Timer; 
import java.awt.*;
import java.awt.List;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

public class Profile {
	
	static JFrame frmMain;
	static Container pane;
	static JPanel pnlProfile;
	static DefaultTableModel mtblDailyEvents, mtblGroups;
	static JTable tblDailyEvents, tblGroups;
	static JScrollPane stblDailyEvents, stblGroups;
	static JLabel lblUsername, profilePic, usernameDoesntExist;
	static JButton btnMyCalendar, btnModifyEvents, btnAddFriend, btnGroups, btnAddEvent, btnNotifications;
	static JTextField addFriendText;
	static private String username;
	static private User userForProfile;
	
	static private void prepareFrame(){
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String lcOSName = System.getProperty("os.name").toLowerCase();
		boolean IS_MAC = lcOSName.startsWith("mac os x");
		System.setProperty("apple.laf.useScreenMenuBar", String.valueOf(IS_MAC));
		
		//set-up frame
				frmMain = new JFrame("Group Calendar");
				frmMain.setSize(550,385);
				pane = frmMain.getContentPane();
				pane.setLayout(null);
				frmMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Close when X is clicked
	}
	public static void centerWindow(Window frame) {
	    Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
	    int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
	    int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
	    frame.setLocation(x, y);
	}

	static private void createControls() throws IOException{
		//create controls
				mtblDailyEvents = new DefaultTableModel(){public boolean isCellEditable(int rowIndex, int mColIndex){return false;}};
				mtblGroups = new DefaultTableModel(){public boolean isCellEditable(int rowIndex, int mColIndex){return false;}};
				
				tblDailyEvents = new JTable(mtblDailyEvents);
				tblGroups = new JTable(mtblGroups);
				
				stblDailyEvents = new JScrollPane(tblDailyEvents);
				stblGroups = new JScrollPane(tblGroups);
				
				addFriendText = new JTextField();
				
				pnlProfile = new JPanel(null);
				
				btnMyCalendar = new JButton("My Calendar");
				btnModifyEvents = new JButton("Modify Events");
				btnAddFriend = new JButton("Add Friend");
				btnAddEvent = new JButton("Add Event");
				btnNotifications = new JButton("Notifications");
				btnAddFriend.setEnabled(false);
				btnGroups = new JButton("Groups");
				btnGroups.setEnabled(true);
				
				//CALL METHOD TO GET USERNAME FROM DATABASE
				username = userForProfile.getUsername(); // query
				lblUsername = new JLabel(username);
				
				usernameDoesntExist = new JLabel(" ");
				
				//CALL METHOD TO GET IMAGE FROM DATABASE
				BufferedImage myPicture = ImageIO.read(new File("/Users/keiththompson/Downloads/src/edu/cuny/csi/csc330/bill.jpeg"));
				if(username.toLowerCase().equals("omar"))
					myPicture = ImageIO.read(new File("/Users/keiththompson/Downloads/src/edu/cuny/csi/csc330/bill.jpeg"));
				else if (username.toLowerCase().equals("moshe"))
					myPicture = ImageIO.read(new File("/Users/keiththompson/Downloads/src/edu/cuny/csi/csc330/jon.jpeg"));
				else
					myPicture = ImageIO.read(new File("/Users/keiththompson/Downloads/src/edu/cuny/csi/csc330/dude.png"));

				
				
				profilePic = new JLabel(new ImageIcon(myPicture));
	}
	static private void setBorder(){
		//set border
				pnlProfile.setBorder(BorderFactory.createTitledBorder("Profile                                                                        Current Temperature: 55\u00B0 F"));
				
				//set color
				frmMain.getContentPane().setBackground(Color.BLUE);
	}
	static private void addControls(){
		//add controls to pane
				pane.add(pnlProfile);
				pnlProfile.add(stblDailyEvents);
				pnlProfile.add(stblGroups);
				pnlProfile.add(lblUsername);
				pnlProfile.add(profilePic);
				pnlProfile.add(btnMyCalendar);
				pnlProfile.add(btnModifyEvents);
				pnlProfile.add(btnGroups);
				pnlProfile.add(btnAddEvent);
				pnlProfile.add(btnNotifications);
				pnlProfile.add(addFriendText);
				pnlProfile.add(usernameDoesntExist);
				frmMain.setJMenuBar(MenuExp.PrepareMenuBar(userForProfile));
	}
	static private void registerActionListeners(){
		//add action listeners
				btnMyCalendar.addActionListener(new btnMyCalendar_Action());
				btnAddEvent.addActionListener(new btnAddEvent_Action());
				btnNotifications.addActionListener(new btnNotifications_Action());
				btnGroups.addActionListener(new btnGroups_Action());
	/*			
				addFriendText.addActionListener(new java.awt.event.ActionListener() {
			        public void actionPerformed(java.awt.event.ActionEvent evt) {
			        	addFriendTextActionPerformed(evt);
			        }
			    });
				addFriendText.addKeyListener(new java.awt.event.KeyAdapter() {
			        public void keyReleased(java.awt.event.KeyEvent evt) {
			        	addFriendTextKeyReleased(evt);
			        }
			     
			    });
	 */
	}
	static private void setBounds(){
		//set bounds
		pnlProfile.setBounds(0,0,650,650);
		lblUsername.setBounds(75,15,100,50);
		stblGroups.setBounds(75,225,175,100);
		stblDailyEvents.setBounds(300,225,175,100);
		profilePic.setBounds(325,45,150,150);
		btnMyCalendar.setBounds(75,80 ,100,50);
		btnGroups.setBounds(75,150,100,50);
		btnAddEvent.setBounds(200,80, 100,50);
		btnNotifications.setBounds(200,150,100,50);
		//addFriendText.setBounds(75,150,110,25);
		btnAddFriend.setBounds(200, 150,100 ,25);
		usernameDoesntExist.setBounds(75, 180, 210,25);
		
		
	}
	static private void populateTables(){
		//All must be a method from a query for database
				tblDailyEvents.setRowHeight(13);
				
				mtblDailyEvents.addColumn("Today's Events");
				ArrayList<String> todaysEvents = userForProfile.getTodaysEventNames();
				mtblDailyEvents.setRowCount(todaysEvents.size());
				
				for(int i = 0; i < todaysEvents.size();i++)
				mtblDailyEvents.setValueAt(todaysEvents.get(i),i,0);
				
				
				tblGroups.setRowHeight(13);
				mtblGroups.addColumn("Groups");
				
				
				
				List myGroups =  userForProfile.getGroups();
				mtblGroups.setRowCount(myGroups.getItemCount());
				for(int i = 0; i< myGroups.getItemCount(); i++)
					mtblGroups.setValueAt(myGroups.getItem(i), i, 0);
				
	}
	static public void makeFrameVisible(boolean decision){
		frmMain.setVisible(decision);
		frmMain.setResizable(false);
	}
	static private void backgroundProcesses(){}
	
	public static void paintFrame(User user) throws IOException{
		userForProfile = user;
		prepareFrame();
		createControls();
		centerWindow(frmMain);
		setBorder();
		addControls();
		registerActionListeners();
		setBounds();
		populateTables();
		makeFrameVisible(true);
		backgroundProcesses();
	}


	public static void main(String[] args) throws IOException {
	
		try {UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());}
		catch (ClassNotFoundException e) {}
		catch (InstantiationException e) {}
		catch (IllegalAccessException e) {}
		catch (UnsupportedLookAndFeelException e) {}
	
		paintFrame(new User("Moshe"));
		
		
	}

	static class btnMyCalendar_Action implements ActionListener{
		public void actionPerformed(ActionEvent e){
			JFrame frame = frmMain;
			CalendarProgram.paintFrame(userForProfile);
			frame.setVisible(false);
		}
	}
	
	static class btnNotifications_Action implements ActionListener{
		public void actionPerformed(ActionEvent e){
			Profile.makeFrameVisible(false);
			NotificationScreen.paintFrame(userForProfile);
		}
	}
	
	static class btnAddEvent_Action implements ActionListener{
		public void actionPerformed(ActionEvent e){
			AddEvent.paintFrame(userForProfile);
		}
	}
	static class btnGroups_Action implements ActionListener{
		public void actionPerformed(ActionEvent e){
			GroupScreen.paintFrame(userForProfile);
		}
		
	}

	 


}
