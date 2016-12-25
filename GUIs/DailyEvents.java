package calendarTest;


import javax.swing.*;

import javax.swing.event.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;


public class DailyEvents {
	
	static JFrame frmMain;
	static Container dayPane;
	static DefaultTableModel mtblChooseDays, mtblDailyEvents;
	static JTable tblChooseDays,tblDailyEvents;
	static JScrollPane stblChooseDays, stblDailyEvents;
	static JPanel pnlDailyEvents;
	static JButton [] dayBtns;
	static JList listOfDays, listOfHours;
	static Action viewEventDetails;
	static private int rowClicked, isGroupEvent = 0;
	static private String dateToView;
	static private Object[] eventDetailsArray = new Object[48];
	static private User userDailyEvents;
	static private List groups;
	
	
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
		dayPane = frmMain.getContentPane();
		dayPane.setLayout(null);
		frmMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Close when X is clicked
		mtblChooseDays = new DefaultTableModel(1,CalendarProgram.getDaysInMonth()){public boolean isCellEditable(int rowIndex, int mColIndex){return false;}};
		mtblDailyEvents = new DefaultTableModel(){public boolean isCellEditable(int rowIndex, int mColIndex){return false;}};
	}
	public static void centerWindow(Window frame) {
	    Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
	    int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
	    int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
	    frame.setLocation(x, y);
	}
	
	public static void setRowClickedToZero(){
		rowClicked = 0;
		
		
		//EventDetails.makeFrameVisible(false);
	}
	static private void createControls(String date,User user){
		
		isGroupEvent =0;
		
		
		tblChooseDays = new JTable(mtblChooseDays);
		tblDailyEvents = new JTable(mtblDailyEvents);
		
		pnlDailyEvents = new JPanel(null);
		
		tblChooseDays.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblChooseDays.setRowSelectionAllowed(false);
		tblChooseDays.setRowHeight(35);
		tblChooseDays.setShowGrid(true);
		//tblChooseDays.setSize(750, 50);
		tblChooseDays.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		tblChooseDays.setTableHeader(null);
		mtblChooseDays.setRowCount(1);
		
		//tblDailyEvents.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tblDailyEvents.setShowGrid(true);
		tblDailyEvents.setRowSelectionAllowed(true);
		//tblDailyEvents.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		
		stblChooseDays = new JScrollPane(tblChooseDays);
		stblDailyEvents = new JScrollPane(tblDailyEvents);
		
		
		
		String[] dayBtns = new String[31];
		for(int i = 0; i < CalendarProgram.getDaysInMonth(); i++){
			if(i < 9)
				dayBtns[i] = ("0"+String.valueOf(i+1));
			else
				dayBtns[i]=( String.valueOf(i+1));
			mtblChooseDays.setValueAt(dayBtns[i], 0, i);
			tblChooseDays.setDefaultRenderer(tblChooseDays.getColumnClass(0), new tblChooseDaysRenderer());
		}
		tblChooseDays.addMouseListener(new MouseAdapter() {
		    public void mousePressed(MouseEvent me) {
		        JTable table =(JTable) me.getSource();
		        Point p = me.getPoint();
		        int columnClicked = table.columnAtPoint(p);
		        String day;
		        JFrame frame = (JFrame) SwingUtilities.getRoot(tblDailyEvents);
		        if (me.getClickCount() == 2) {
		           day = (String) mtblChooseDays.getValueAt(0, columnClicked);
		           dateToView = dateToView.substring(0,2)+day+dateToView.substring(4);
		        DailyEvents.paintFrame(dateToView, userDailyEvents);
		        frame.setVisible(false);
		        rowClicked = 0;
		        
		        }
		    }
		});
		
		tblDailyEvents.setRowHeight(38);
		mtblDailyEvents.setRowCount(49);
		mtblDailyEvents.addColumn("Time");
		mtblDailyEvents.addColumn("Event Name");
		mtblDailyEvents.addColumn("");
		TableColumn column = null;
	    for (int i = 0; i < 3; i++) {
	        column = tblDailyEvents.getColumnModel().getColumn(i);
	        if (i == 1) {
	            column.setPreferredWidth(300);//Event name column is bigger
	        } 
	        
	        else if(i ==0){
	        	column.setPreferredWidth(100);
	        }
	        
	        else {
	            column.setPreferredWidth(150);
	        }
	    }
	
		String [] hours = new String[53];
		int k=12;
		String amOrPm = "PM";
		for(int j =0; j<48; j++){
			
			if( k % 12==0 && amOrPm == "PM" && j %2 == 0){
				amOrPm = "AM";
		}
		else if(k %12 ==0 && amOrPm == "AM" && j %2 == 0){
				amOrPm = "PM";
		}
		
			
			if(j%2 == 0 && k %12 == 0){
				hours[j] = Integer.toString(12)+":00 " + amOrPm;	
				
			}
			else if(j%2 == 1 && k %12 == 0){
				hours[j] = Integer.toString(12)+ ":30 "+amOrPm;
				k++;
			}
			else if(j%2 ==0){
				if(k%12 <10){
					hours[j] = "0"+Integer.toString(k%12)+":00 "+ amOrPm;
				}
				else{
					hours[j] = Integer.toString(k%12)+":00 "+ amOrPm;
				}
			}
			else if(j%2 == 1){
				if(k%12 <10){
					hours[j] = "0"+Integer.toString(k%12)+":30 "+ amOrPm;
				
				}
			else{
				hours[j] = Integer.toString(k%12)+":30 " + amOrPm;
				
			}
				k++;
			}	
			
			
			
			
			mtblDailyEvents.setValueAt(hours[j],j,0);
			if(amOrPm=="PM" && Integer.valueOf(hours[j].substring(0,2)) != 12){
			int tempInt = Integer.valueOf(hours[j].substring(0, 2)) + 12;
			hours[j] = String.valueOf(tempInt) + hours[j].substring(2);
			}
			else if(amOrPm == "AM" && Integer.valueOf(hours[j].substring(0,2)) == 12){
				int tempInt = 00;
				hours[j] = String.valueOf(tempInt) +"0"+ hours[j].substring(2);
			}
			
			String dateTime = (date.substring(0,2)+"/"+date.substring(2,4)+"/"+date.substring(4,8)+hours[j].substring(0,5)+":00");
			
			Event tempEvent = null;
			tempEvent = userDailyEvents.getEvent(dateTime);
			
			
			int i = 0;
			while(tempEvent == null && i < groups.getItemCount()){
				Group tempGroup = new Group(userDailyEvents.getUsername(),groups.getItem(i));
				tempEvent = tempGroup.getEvent(dateTime);
				if(tempEvent != null){
					isGroupEvent = 1;
				}
				i++;
			}
			
			
			if (tempEvent != null){
				eventDetailsArray[j] = tempEvent;
				if(!tempEvent.getId().toLowerCase().equals(user.getUsername().toLowerCase())){
					mtblDailyEvents.setValueAt("("+tempEvent.getId()+") " +tempEvent.getEventName(), j, 1);
				}
				else mtblDailyEvents.setValueAt(tempEvent.getEventName(), j, 1);
			}
			
			
			if(mtblDailyEvents.getValueAt(j,1) != null)
				mtblDailyEvents.setValueAt("<HTML><font color = blue><U>View Event Details</U></font><HTML>" ,j, 2);
			tblDailyEvents.setDefaultRenderer(tblDailyEvents.getColumnClass(0), new tblDailyEventsRenderer());
			
		}
		
		stblChooseDays.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		int whereToScroll = Integer.valueOf((String.valueOf(dateToView.charAt(2)))+(String.valueOf(dateToView.charAt(3))));
		stblChooseDays.getViewport().setViewPosition(new java.awt.Point(1800*whereToScroll/CalendarProgram.getDaysInMonth(),0));
}
	

	static class tblDaySelectedRenderer extends DefaultTableCellRenderer{
		public Component getTableCellRendererComponent (JTable table, Object value, boolean selected, boolean focused, int row, int column){
			super.getTableCellRendererComponent(table, value, selected, focused, row, column);
			int columnSelected = table.getSelectedColumn();
			int rowSelected = table.getSelectedRow();
			String date;
			if(column < 9){
			date = "0"+String.valueOf(column+1);
			}
			else {
				date = String.valueOf(column+1);
			}
			date = dateToView.substring(0,2) +"/"+ date+"/" + dateToView.substring(4);
			
			
			boolean groupHasEventToday = false;
			for(int i = 0; i < groups.getItemCount(); i++){
				if(new Group(userDailyEvents.getUsername(),groups.getItem(i)).isThereAnEventToday(date)){
					groupHasEventToday = true;
					i = groups.getItemCount();
				}
			}
			
			
			if(column +1  == Integer.valueOf((String.valueOf(dateToView.charAt(2)))+(String.valueOf(dateToView.charAt(3)))))
				setBackground(new Color(0,191,255));
			else if(userDailyEvents.isThereAnEventToday(date)||groupHasEventToday){
				setBackground(new Color(0, 250, 0));
			}
			else if (column % 2 == 0){ 
				setBackground(new Color(235,235,235));
			}
			else setBackground(new Color(255,255,255));
			
			
			setHorizontalAlignment(SwingConstants.CENTER);
			setBorder(null);
			setForeground(Color.black);
			return this;  
		}
		
	}
	
	static class tblChooseDaysRenderer extends DefaultTableCellRenderer{
		public Component getTableCellRendererComponent (JTable table, Object value, boolean selected, boolean focused, int row, int column){
			super.getTableCellRendererComponent(table, value, selected, focused, row, column);
	
			String test;
			if(column < 9){
			test = "0"+String.valueOf(column+1);
			}
			else {
				test = String.valueOf(column+1);
			}
			test = dateToView.substring(0,2) + test + dateToView.substring(4);
			//System.out.println(test);
			
			if(column +1  == Integer.valueOf((String.valueOf(dateToView.charAt(2)))+(String.valueOf(dateToView.charAt(3))))){
				setBackground(new Color(0,191,255));
		}
					
			//else if()
			else if (column % 2 == 0){ 
				setBackground(new Color(235,235,235));
			}
			else setBackground(new Color(255,255,255));
			setHorizontalAlignment(SwingConstants.CENTER);
			setBorder(null);
			setForeground(Color.black);
			tblChooseDays.setCursor(new Cursor(Cursor.HAND_CURSOR));
			return this;  
		}
		
	}
	
	
	static class tblDailyEventsRenderer extends DefaultTableCellRenderer{
		public Component getTableCellRendererComponent (JTable table, Object value, boolean selected, boolean focused, int row, int column){
			super.getTableCellRendererComponent(table, value, selected, focused, row, column);
			
			if(tblDailyEvents.isRowSelected(row) && tblDailyEvents.isColumnSelected(2) && tblDailyEvents.getValueAt(row, 1) != null && row != rowClicked){
				EventDetails.paintFrame((Event)eventDetailsArray[row], userDailyEvents);
				rowClicked = row;
			}
			
			else if (tblDailyEvents.isRowSelected(row)){
				setBackground(new Color(0,191,255));
			}
			else if (row % 2 == 0){ 
				setBackground(new Color(215,215,215));
			}
			else setBackground(new Color(255,255,255));
			setBorder(null);
			setForeground(Color.black);
			setHorizontalAlignment(SwingConstants.CENTER);
			
			
			return this;  
			
		}
	}
	
	static private void setBorder(){
		stblChooseDays.setFont(new Font("Courier New", Font.ITALIC, 18));
		stblChooseDays.setForeground(Color.BLUE);
	}
	
	static private void registerActionListeners(){
		
	
		tblChooseDays.addMouseListener( new java.awt.event.MouseListener() {
			public void actionPerformed(java.awt.event.MouseListener evt) {
             
			
		}

			@Override
			public void mouseClicked(MouseEvent e) {
				
				tblChooseDays.setDefaultRenderer(tblChooseDays.getColumnClass(0), new tblDaySelectedRenderer());
				
			}

			@Override
			public void mousePressed(MouseEvent e) {
				tblChooseDays.setDefaultRenderer(tblChooseDays.getColumnClass(0), new tblDaySelectedRenderer());
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
			tblChooseDays.setDefaultRenderer(tblChooseDays.getColumnClass(0), new tblDaySelectedRenderer());
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				//tblChooseDays.setDefaultRenderer(tblChooseDays.getColumnClass(0), new tblDaySelectedRenderer());
				
			}

			@Override
			public void mouseExited(MouseEvent e) {
				//tblChooseDays.setDefaultRenderer(tblChooseDays.getColumnClass(0), new tblDaySelectedRenderer());
				
			}
			
	});
		
		tblDailyEvents.addMouseListener(new java.awt.event.MouseListener() {
			public void actionPerformed(java.awt.event.MouseListener evt){
				
				
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				tblDailyEvents.setDefaultRenderer(tblChooseDays.getColumnClass(0), new tblDailyEventsRenderer());
				
			}

			@Override
			public void mousePressed(MouseEvent e) {
				//tblDailyEvents.setDefaultRenderer(tblChooseDays.getColumnClass(0), new tblDailyEventsRenderer());
				
			}

			@Override
			public void mouseReleased(MouseEvent e) {
				//tblDailyEvents.setDefaultRenderer(tblChooseDays.getColumnClass(0), new tblDailyEventsRenderer());
			}

			@Override
			public void mouseEntered(MouseEvent e) {
			}
			@Override
			public void mouseExited(MouseEvent e) {
			}
			
		});
		
		
		viewEventDetails = new AbstractAction()
		{
		    public void actionPerformed(ActionEvent e)
		    {
		        JTable table = (JTable)e.getSource();
		        int modelRow = Integer.valueOf( e.getActionCommand() );
		       String dateTime = (String) (((DefaultTableModel)table.getModel()).getValueAt(modelRow, 0));
		       frmMain.setVisible(false);
		        
		    }
		};
		
	}
	
	static private void addControls(){
		dayPane.add(pnlDailyEvents);
		pnlDailyEvents.add(stblChooseDays);
		pnlDailyEvents.add(stblDailyEvents);
		frmMain.setJMenuBar(MenuExp.PrepareMenuBar(new User("Moshe")));
		
		
		
	}
	static private void setBounds(){
		pnlDailyEvents.setBounds(0,0,550,385);
		stblChooseDays.setBounds(0,0,550,65);
		stblChooseDays.setAlignmentX(Frame.LEFT_ALIGNMENT);
		stblDailyEvents.setBounds(0,65,550,385-65);
		stblDailyEvents.setAlignmentX(Frame.LEFT_ALIGNMENT);
		
		
	}
	
	static public void makeFrameVisible(boolean decision){
		//Make frame visible
				frmMain.setResizable(false);
				frmMain.setVisible(decision);
	}
	
	public static void paintFrame(String date, User user){
		userDailyEvents = new User(user.getUsername());
		groups = userDailyEvents.getGroups();
		dateToView = date;
		prepareFrame();
		centerWindow(frmMain);
		createControls(date, user);
		setBorder();
		registerActionListeners();
		addControls();
		setBounds();
		tblChooseDays.setDefaultRenderer(tblChooseDays.getColumnClass(0), new tblDaySelectedRenderer());
		makeFrameVisible(true);
	}
	
	public static void refreshDailyEvents(String date, User user){
		JFrame frame = (JFrame) SwingUtilities.getRoot(tblDailyEvents);
		paintFrame(date, user);
		frame.setVisible(false);
		
	}
	
	public static void main (String args[]){
		//Look and feel
		try {UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());}
		catch (ClassNotFoundException e) {}
		catch (InstantiationException e) {}
		catch (IllegalAccessException e) {}
		catch (UnsupportedLookAndFeelException e) {}
		String timeStamp = new SimpleDateFormat("MMddyyy").format(Calendar.getInstance().getTime());
		paintFrame(timeStamp, new User("Moshe"));
		
		
	}

}
