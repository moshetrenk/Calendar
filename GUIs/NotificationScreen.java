
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Vector;

public class NotificationScreen {
	
	static JFrame frmMain;
	static Container notifPane;
	//static DefaultTableModel mtblNotifications;
	static JTable tblGroupNotifications, tblEventNotifications, tblEventWasRejected;
	static JScrollPane stblGroupNotifications, stblEventNotifications, stblEventWasRejected;
	static JPanel pnlNotifications;
	static JLabel lblGroupNotifications, lblEventNotifications, lblEventWasRejected;
	static MyGroupNotificationTableModel mtblGroupNotifications;
	static MyEventRejectedTableModel mtblEventWasRejected;
	static MyEventNotificationTableModel mtblEventNotifications;
	static JButton submitBtn;
	private static SQLHelper sqlHelper = new SQLHelper();
	private static ArrayList<Notification_Group> groups;
	private static ArrayList<Event> events;
	private static ArrayList<CancelledEventNotification> cancelledEvents;
	private static User userNotif;
	
	
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
		
		frmMain = new JFrame("Group Calendar");
		frmMain.setSize(550, 385);
		notifPane = frmMain.getContentPane();
		notifPane.setLayout(null);
		frmMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Close when X is clicked
		
	}
	private static void setUpTables(User user){

		mtblGroupNotifications = new MyGroupNotificationTableModel();
		
		groups = (user.getGroupNotifications());
		events = (user.getEventNotifications());
		cancelledEvents = (user.getCancelledEventNotifications());
		
		
		for(int i = 0; i < groups.size(); i++){
			mtblGroupNotifications.addRow(new Object[]{groups.get(i).getGroupName(),false, false});
		}
		
		
		tblGroupNotifications = new JTable(mtblGroupNotifications);
		tblGroupNotifications.getColumnModel().getColumn(0).setPreferredWidth(450);
		tblGroupNotifications.getColumnModel().getColumn(1).setPreferredWidth(50);
		tblGroupNotifications.getColumnModel().getColumn(2).setPreferredWidth(50);
		
		//Now set up rejection notifications
		
				mtblEventWasRejected = new MyEventRejectedTableModel();
				
				tblEventWasRejected = new JTable(mtblEventWasRejected);
				tblEventWasRejected.getColumnModel().getColumn(0).setPreferredWidth(90);
				tblEventWasRejected.getColumnModel().getColumn(1).setPreferredWidth(90);
				tblEventWasRejected.getColumnModel().getColumn(2).setPreferredWidth(105);
				tblEventWasRejected.getColumnModel().getColumn(3).setPreferredWidth(185);
				tblEventWasRejected.getColumnModel().getColumn(4).setPreferredWidth(80);
				
				for(int i = 0; i < cancelledEvents.size(); i++){
					mtblEventWasRejected.addRow(new Object[]{cancelledEvents.get(i).getDate(), cancelledEvents.get(i).getStartTime(), cancelledEvents.get(i).getGroupName(), cancelledEvents.get(i).getEventName(), false});
					
				}
				
				//mtblEventWasRejected.addRow(new Object[]{"04/25/2016", "5:00 PM", "9:00 PM", "Finish Project",false});
		
	//Now set up event notifications
		
		mtblEventNotifications = new MyEventNotificationTableModel();
		
		
		
		for(int i = 0; i < events.size(); i++){
			String amOrPm = "";
			String changeFromMilitaryTime;
			if(Integer.parseInt(events.get(i).getEndTime().substring(0,2)) > 12){
				changeFromMilitaryTime = String.valueOf((Integer.parseInt(events.get(i).getEndTime().substring(0,2))%2));
				amOrPm = "PM";
			}
			else{
				changeFromMilitaryTime = String.valueOf(Integer.parseInt(events.get(i).getEndTime().substring(0,2)));
				amOrPm = "AM";
			}
				
			String timeForEvent = events.get(i).getStartTime().substring(0, 5) + "-" + changeFromMilitaryTime + events.get(i).getEndTime().substring(2,5) + amOrPm;
			mtblEventNotifications.addRow(new Object[]{events.get(i).getEventDate(), timeForEvent/*events.get(i).getStartTime()*/, events.get(i).getId(), events.get(i).getEventName(),false, false});
		}
		
		/*
		for(int i = 0; i < 30; i++){
			mtblEventNotifications.addRow(new Object[]{"04/15/2016", "12:00 PM", "1:00 PM", "Finish Project" + String.valueOf(i),false, false});
		}
		*/
		
		tblEventNotifications = new JTable(mtblEventNotifications);
		tblEventNotifications.getColumnModel().getColumn(0).setPreferredWidth(80);
		tblEventNotifications.getColumnModel().getColumn(1).setPreferredWidth(100);
		tblEventNotifications.getColumnModel().getColumn(2).setPreferredWidth(105);
		tblEventNotifications.getColumnModel().getColumn(3).setPreferredWidth(175);
		tblEventNotifications.getColumnModel().getColumn(4).setPreferredWidth(45);
		tblEventNotifications.getColumnModel().getColumn(5).setPreferredWidth(45);
		
		
		tblEventNotifications.addMouseListener(new MouseAdapter() {
		    public void mousePressed(MouseEvent me) {
		        JTable table =(JTable) me.getSource();
		        Point p = me.getPoint();
		        int row = table.rowAtPoint(p);
		        if (me.getClickCount() == 2) {
		           EventDetails.paintFrame((Event)table.getValueAt(row, 0), user);
		        }
		    }
		});
		
	}
	
	private static void centerWindow(Window frame) {
	    Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
	    int x = (int) ((dimension.getWidth() - frame.getWidth())/2);
	    int y = (int) ((dimension.getHeight() - frame.getHeight())/2);
	    frame.setLocation(x, y);
	}
	
	private static void createControls(){
		
		
		pnlNotifications = new JPanel(null);
		
		stblGroupNotifications = new JScrollPane(tblGroupNotifications);
		
		stblEventNotifications = new JScrollPane(tblEventNotifications);
		
		stblEventWasRejected = new JScrollPane(tblEventWasRejected);
		
		if(groups.size() == 1)
			lblGroupNotifications = new JLabel("You a group invitation!", SwingConstants.CENTER );
		else
		lblGroupNotifications = new JLabel("You have " + groups.size()+ " group invitations!", SwingConstants.CENTER );
		if(events.size() == 1)
			lblEventNotifications = new JLabel("You have an event awaiting your answer.", SwingConstants.CENTER);
		else
		lblEventNotifications = new JLabel("You have " + events.size() + " events awaiting your answer.", SwingConstants.CENTER);
		if(cancelledEvents.size() == 1)
			lblEventWasRejected = new JLabel("The following event has been rejected.", SwingConstants.CENTER);
		else
		lblEventWasRejected = new JLabel("The following " + cancelledEvents.size() +" events have been rejected.", SwingConstants.CENTER);
		
		submitBtn = new JButton("Submit");
		
	}
	
	
	static private void addControls(User user){
		notifPane.add(pnlNotifications);
		pnlNotifications.add(stblGroupNotifications);
		pnlNotifications.add(lblGroupNotifications);
		pnlNotifications.add(stblEventNotifications);
		pnlNotifications.add(lblEventNotifications);
		pnlNotifications.add(stblEventWasRejected);
		pnlNotifications.add(lblEventWasRejected);
		pnlNotifications.add(submitBtn);
		frmMain.setJMenuBar(MenuExp.PrepareMenuBar(user));
	}
	
	static private void setBounds(User user){
		
		pnlNotifications.setBounds(0,0,550,550);
		
		lblGroupNotifications.setBounds(0, 7, 550, 20);
		stblGroupNotifications.setBounds(0,30,550,98);
		stblGroupNotifications.setAlignmentX(Frame.LEFT_ALIGNMENT);
		
		lblEventNotifications.setBounds(0, 131, 550, 20);
		stblEventNotifications.setBounds(0,158,550,98);
		stblEventNotifications.setAlignmentX(Frame.LEFT_ALIGNMENT);
		
		lblEventWasRejected.setBounds(0,259,550,20);
		stblEventWasRejected.setBounds(0,286,550,385-286);
		stblEventWasRejected.setAlignmentX(Frame.LEFT_ALIGNMENT);
		
		submitBtn.setBounds(450,7,75,20);
		
		submitBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                submitBtnActionPerformed(evt, user);
            }
        });
		
		
		
	}
	private static void submitBtnActionPerformed(java.awt.event.ActionEvent evt, User user) {    
    	for(int i = 0, j = 0; i < tblGroupNotifications.getRowCount(); i++){
    		if((boolean)tblGroupNotifications.getValueAt(i, 1) == true){
    			user.acceptGroupInvite(groups.get(j));
    		}
    		else if ((boolean)tblGroupNotifications.getValueAt(i,2) == true){
    			user.rejectGroupInvite(groups.get(j));
    		}
    		else
    			j++;
    	}
    	for(int i = 0, j =0; i < tblEventNotifications.getRowCount();i++){
    		if((boolean)tblEventNotifications.getValueAt(i,4) == true){
    			user.acceptEventInvite(events.get(j));
    		}
    		else if((boolean)tblEventNotifications.getValueAt(i,5) == true){
    			ArrayList<String> usersInGroup = new Group(user.getUsername(),events.get(j).getId()).getUsersInGroup();
    			
    			
    			
    			for(int k = 0; k < usersInGroup.size();k++){
    				ArrayList<String> tempOne = new ArrayList<String>();
    				tempOne.add(events.get(j).getId());
    				tempOne.add(user.getUsername());
    				
    				ArrayList<Object> tempTwo = new ArrayList<Object>();
    				tempTwo.add(events.get(j).getEventName());
    				tempTwo.add(events.get(j).getEventDate());
    				tempTwo.add(events.get(j).getStartTime().substring(0,2)+events.get(j).getStartTime().substring(3,5)+events.get(j).getStartTime().substring(6));
    				System.out.println(events.get(j).getStartTime());
    				System.out.println(events.get(j).getStartTime().substring(0,2)+events.get(j).getStartTime().substring(3,5)+events.get(j).getStartTime().substring(6));
    				
    				new User(usersInGroup.get(k)).addEventCancelledNotification(new CancelledEventNotification(tempOne,tempTwo));
    			}
    			user.rejectEventInvite(events.get(j));
    		}
    		else
    			j++;
    	}
    	for(int i = 0, j = 0; i < tblEventWasRejected.getRowCount(); i++){
    		if((boolean) tblEventWasRejected.getValueAt(i,4) == true){
    			user.deleteCancelledEventNotification(cancelledEvents.get(j));
    		}
    		else 
    			j++;
    	}	
    	
    	frmMain.setVisible(false);
    	paintFrame(userNotif);
    	
    }
		
                
	
	static private void makeFrameVisible(boolean decision){
		//Make frame visible
				frmMain.setResizable(false);
				frmMain.setVisible(decision);
	}
	
	public static void paintFrame(User user){
		userNotif = user;
		prepareFrame();
		setUpTables(user);
		centerWindow(frmMain);
		createControls();
		addControls(user);
		setBounds(user);
		makeFrameVisible(true);
	}
	
	 public static class MyEventRejectedTableModel extends DefaultTableModel {

		    public MyEventRejectedTableModel() {
		      super(new String[]{"Date", "Time", "Group","Event Name","Mark as Read" }, 0); 
		    }
		    
			@Override
		    public Class<?> getColumnClass(int columnIndex) {
		      Class clazz = String.class;
		      switch (columnIndex) {
		      	case 4:
		          clazz = Boolean.class;
		          break;
		      }
		      return clazz;
		    }

		    @Override
		    public boolean isCellEditable(int row, int column) {
		      return (column == 4);
		    }
		    
		    

		    @Override
		    public void setValueAt(Object aValue, int row, int column) {
		    	if (aValue instanceof Boolean && column == 4) {
			        Vector rowData = (Vector)getDataVector().get(row);
			        rowData.set(4, (boolean)aValue);
			        fireTableCellUpdated(row, column);
			        
		    	}
		    }
	 }
	

	 public static class MyEventNotificationTableModel extends DefaultTableModel {

		    public MyEventNotificationTableModel() {
		      super(new String[]{"Date", "Time", "Group","Event Name","Accept", "Reject" }, 0);
		    }
		    
		    
		    
			@Override
		    public Class<?> getColumnClass(int columnIndex) {
		      Class clazz = String.class;
		      switch (columnIndex) {
		      	case 4:
		        case 5:
		          clazz = Boolean.class;
		          break;
		      }
		      return clazz;
		    }

		    @Override
		    public boolean isCellEditable(int row, int column) {
		      return (column == 4 || column == 5);
		    }
		    
		    

		    @Override
		    public void setValueAt(Object aValue, int row, int column) {
		      if (aValue instanceof Boolean && column == 5) {
		        Vector rowData = (Vector)getDataVector().get(row);
		        rowData.set(5, (boolean)aValue);
		        if (((boolean)rowData.get(4)) == true && ((boolean)aValue) == true)
		        	rowData.set(4,false);
		        	
		        fireTableCellUpdated(row, column);
		        fireTableCellUpdated(row,column-1);
		      }
		      else if (aValue instanceof Boolean && column == 4) {
			        Vector rowData = (Vector)getDataVector().get(row);
			        rowData.set(4, (boolean)aValue);
			        if (((boolean)rowData.get(5)) == true && ((boolean)aValue) == true)
			        	rowData.set(5,false);
			        fireTableCellUpdated(row, column);
			        fireTableCellUpdated(row,column+1);
		    }

	}
	 }
	 
	
	 public static class MyGroupNotificationTableModel extends DefaultTableModel {

		    public MyGroupNotificationTableModel() {
		      super(new String[]{"Group Name", "Accept", "Reject"}, 0);
		      
		    }

		   

			@Override
		    public Class<?> getColumnClass(int columnIndex) {
		      Class clazz = String.class;
		      switch (columnIndex) {
		      	case 1:
		        case 2:
		          clazz = Boolean.class;
		          break;
		      }
		      return clazz;
		    }

		    @Override
		    public boolean isCellEditable(int row, int column) {
		      return (column == 1 || column == 2);
		    }
		    
		    

		    @Override
		    public void setValueAt(Object aValue, int row, int column) {
		      if (aValue instanceof Boolean && column == 2) {
		        Vector rowData = (Vector)getDataVector().get(row);
		        rowData.set(2, (boolean)aValue);
		        if (((boolean)rowData.get(1)) == true && ((boolean)aValue) == true)
		        	rowData.set(1,false);
		        	
		        fireTableCellUpdated(row, column);
		        fireTableCellUpdated(row,column-1);
		      }
		      else if (aValue instanceof Boolean && column == 1) {
			        Vector rowData = (Vector)getDataVector().get(row);
			        rowData.set(1, (boolean)aValue);
			        if (((boolean)rowData.get(2)) == true && ((boolean)aValue) == true)
			        	rowData.set(2,false);
			        fireTableCellUpdated(row, column);
			        fireTableCellUpdated(row,column+1);
		    }

	}
	 } 
	 
	
	public static void main (String args[]){
		//Look and feel
		try {UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());}
		catch (ClassNotFoundException e) {}
		catch (InstantiationException e) {}
		catch (IllegalAccessException e) {}
		catch (UnsupportedLookAndFeelException e) {}

		paintFrame(userNotif);
	}

	
	 }
	 

	 

