package CalendarTest;


/*Contents of CalendarProgram.class */

//Import packages
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.List;
import java.awt.event.*;
import java.util.*;

public class CalendarProgram extends JFrame{
	private static JLabel lblDay,lblMonth, lblYear;
	private static JButton btnPrev, btnNext;
	private static JTable tblCalendar;
	private static JComboBox cmbYear, cmbDay, cmbGroups;
	private static JFrame frmMain;
	private static Container pane;
	private static DefaultTableModel mtblCalendar; //Table model
	private static JScrollPane stblCalendar; //The scrollpane
	private static JPanel pnlCalendar;
	private static int realYear, realMonth, realDay, currentYear, currentMonth, monthBefore, cmbDay_clicked;
	private static User userForCalendarProgram;
	static private List groups;
	
	
	static private void prepareFrame(User user){
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
		
		frmMain = new JFrame ("Group Calendar"); //Create frame
		frmMain.setSize(550, 385); //Set size to NxM pixels
		pane = frmMain.getContentPane(); //Get content pane
		pane.setLayout(null); //Apply null layout
		frmMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Close when X is clicked
		mtblCalendar = new DefaultTableModel(){public boolean isCellEditable(int rowIndex, int mColIndex){return false;}};
	}
	
	static private void createControls(){
		lblMonth = new JLabel ("January");
		lblYear = new JLabel ("Change year:");
		lblDay = new JLabel("View Day's Activities:");
		cmbYear = new JComboBox();
		cmbDay = new JComboBox();
		cmbGroups = new JComboBox();
		cmbGroups.addItem("Your Calendar");
		for(int i = 0; i < groups.getItemCount();i++){
			cmbGroups.addItem(groups.getItem(i));
		}
		
		
		btnPrev = new JButton ("<<");
		btnNext = new JButton (">>");
		
		tblCalendar = new JTable(mtblCalendar);
		stblCalendar = new JScrollPane(tblCalendar);
		pnlCalendar = new JPanel(null);
		
	}
	
	

	static private void setBorder(){
		pnlCalendar.setBorder(BorderFactory.createTitledBorder("Calendar"));
	}
	
	static private void addControls(User user){
		pane.add(pnlCalendar);
		pnlCalendar.add(lblDay);
		pnlCalendar.add(lblMonth);
		pnlCalendar.add(lblYear);
		pnlCalendar.add(cmbYear);
		pnlCalendar.add(cmbDay);
		pnlCalendar.add(btnPrev);
		pnlCalendar.add(btnNext);
		pnlCalendar.add(stblCalendar);
		pnlCalendar.add(cmbGroups);
		frmMain.setJMenuBar(MenuExp.PrepareMenuBar(user));
		
	}
	static private void registerActionListeners(){
		
			btnPrev.addActionListener(new btnPrev_Action());
			btnNext.addActionListener(new btnNext_Action());
			cmbYear.addActionListener(new cmbYear_Action());
			cmbDay.addActionListener(new cmbDay_Action());
			cmbGroups.addActionListener(new cmbGroups_Action());
			
			tblCalendar.addMouseListener(new MouseAdapter() {
			    public void mousePressed(MouseEvent me) {
			        JTable table =(JTable) me.getSource();
			        Point p = me.getPoint();
			        int columnClicked = table.columnAtPoint(p);
			        int rowClicked = table.rowAtPoint(p);
			        String dateToView;
			        String day, month, year;
			        JFrame frame = (JFrame) SwingUtilities.getRoot(tblCalendar);
			        if (me.getClickCount() == 2) {
			        
			           day = String.valueOf(mtblCalendar.getValueAt(rowClicked, columnClicked));
			           if(day !="null"){
			           if(Integer.valueOf(day) < 10)
			        	   day = "0" + day;
			           if(Integer.valueOf(currentMonth)<10)
			        	  month = "0" + (currentMonth+1);
			           else
			        	  month = String.valueOf(currentMonth+1);
			           
			           year = String.valueOf(currentYear);
			           
			           dateToView = month+day+year;
			         
			        DailyEvents.paintFrame(dateToView, userForCalendarProgram);
			        frame.setVisible(false);
			        	}
			        }
			    }
			});
			        
	}
	
	static private void setBounds(){
		pnlCalendar.setBounds(0, 0, 550, 550);
		lblDay.setBounds(15,305,140,20);
		lblMonth.setBounds(160-lblMonth.getPreferredSize().width/2, 25, 100, 25);
		lblYear.setBounds(350, 305, 100, 20);
		cmbYear.setBounds(435, 305, 90, 20);
		cmbDay.setBounds(155,305,90,20);
		cmbGroups.setBounds(95,330,150,20);
		btnPrev.setBounds(15, 25, 50, 25);
		btnNext.setBounds(475, 25, 50, 25);
		stblCalendar.setBounds(20, 50, 500, 250);
	}
	
	private static void centerWindow(Window frame) {
	    Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
	    int x = (int) ((dimension.getWidth() - frame.getWidth()) / 2);
	    int y = (int) ((dimension.getHeight() - frame.getHeight()) / 2);
	    frame.setLocation(x, y);
	}
	static private void makeFrameVisible(boolean decision){
		//Make frame visible
				frmMain.setResizable(false);
				frmMain.setVisible(decision);
	}
	
	static private void getCurrentMonthAndYear(){
		
				GregorianCalendar cal = new GregorianCalendar(); //Create calendar
				realDay = cal.get(GregorianCalendar.DAY_OF_MONTH); //Get day
				realMonth = cal.get(GregorianCalendar.MONTH); //Get month
				realYear = cal.get(GregorianCalendar.YEAR); //Get year
				currentMonth = realMonth; //Match month and year
				currentYear = realYear;
	}
	
	 static private void backgroundProcesses(){
		//Add headers
				String[] headers = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"}; //All headers
				for (int i=0; i<7; i++){
					mtblCalendar.addColumn(headers[i]);
				}
				
				tblCalendar.getParent().setBackground(tblCalendar.getBackground()); //Set background

				//No resize/reorder
				tblCalendar.getTableHeader().setResizingAllowed(false);
				tblCalendar.getTableHeader().setReorderingAllowed(false);

				//Single cell selection
				tblCalendar.setColumnSelectionAllowed(false);
				tblCalendar.setRowSelectionAllowed(false);
				tblCalendar.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

				//Set row/column count
				tblCalendar.setRowHeight(38);
				mtblCalendar.setColumnCount(7);
				mtblCalendar.setRowCount(6);
	//Populate tables
				
			for (int i=realYear-100; i<=realYear+100; i++){
				cmbYear.addItem(String.valueOf(i));
			}
	}
	
	public static void paintFrame(User user){
		userForCalendarProgram = user;
		groups = userForCalendarProgram.getGroups();
		prepareFrame(user);
		createControls();
		centerWindow(frmMain);
		setBorder();
		addControls(user);
		registerActionListeners();
		setBounds();
		makeFrameVisible(true);
		getCurrentMonthAndYear();
		backgroundProcesses();
		refreshCalendar(realMonth,realYear, false);
		
	}
	public static int getDaysInMonth(){
		GregorianCalendar cal = new GregorianCalendar();
		return cal.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);
		
	}
	
	public static int getCurrentDay(){
		return realDay;
	}
	public static int getCurrentMonth(){
		return realMonth;
	}
	public static int getCurrentYear(){
		return realYear;
	}  
	public static void setMonthBeforeToNull(){
		monthBefore = 0;
	}
	public static void main (String args[]){
		//Look and feel
		try {UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());}
		catch (ClassNotFoundException e) {}
		catch (InstantiationException e) {}
		catch (IllegalAccessException e) {}
		catch (UnsupportedLookAndFeelException e) {}
		
		paintFrame(new User("Moshe"));
		
		
	}
	
	public static void refreshCalendar(int month, int year, boolean isGroupSelected){
		//Variables
		String[] months =  {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
		int nod, som; //Number Of Days, Start Of Month
			
		//Allow/disallow buttons
		btnPrev.setEnabled(true);
		btnNext.setEnabled(true);
		if (month == 0 && year <= realYear-10){btnPrev.setEnabled(false);} //Too early
		if (month == 11 && year >= realYear+100){btnNext.setEnabled(false);} //Too late
		lblMonth.setText(months[month]); //Refresh the month label (at the top)
		lblMonth.setBounds(260-lblMonth.getPreferredSize().width/2, 25, 180, 25); //Re-align label with calendar
		cmbYear.setSelectedItem(String.valueOf(year)); //Select the correct year in the combo box
		
		//Clear table
		for (int i=0; i<6; i++){
			for (int j=0; j<7; j++){  
				mtblCalendar.setValueAt(null, i, j);
			}
		}
		
		//Get first day of month and number of days
		GregorianCalendar cal = new GregorianCalendar(year, month, 1);
		nod = cal.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);
		som = cal.get(GregorianCalendar.DAY_OF_WEEK);
		
		//Draw calendar
		for (int i=1; i<=nod; i++){
			int row = new Integer((i+som-2)/7);
			int column  =  (i+som-2)%7;
			mtblCalendar.setValueAt(i, row, column);
		}
	if(month != monthBefore){
		cmbDay.removeAllItems();
		for( int i = 1; i <=nod ;i++){
			cmbDay_clicked=0;
			cmbDay.addItem(String.valueOf(i));
		}
		cmbDay_clicked = 1;
	}

		//Apply renderers
		tblCalendar.setDefaultRenderer(tblCalendar.getColumnClass(0), new tblCalendarRenderer(isGroupSelected));
		monthBefore = month;
	}

	static class tblCalendarRenderer extends DefaultTableCellRenderer{
		private static boolean isGroupCalendar;
		tblCalendarRenderer(boolean b){
			isGroupCalendar = b;
		}
		
		public Component getTableCellRendererComponent (JTable table, Object value, boolean selected, boolean focused, int row, int column){
			super.getTableCellRendererComponent(table, value, selected, focused, row, column);
			String month = " ";
			String day = " ";
			
			
			if(currentMonth < 9){
				 month = "0"+ String.valueOf(currentMonth+1);
			}
			else
			month = String.valueOf(currentMonth+1);
			/*
			if(table.getValueAt(row, column) instanceof String){
				table.setValueAt((Integer.valueOf(((String)table.getValueAt(row, column)).substring(0,1))), row, column);
			}
			*/
			/*if(row != 0 && column != 0)
			 * */
			 
			if(table.getValueAt(row, column) != null &&(int)table.getValueAt(row, column) < 10){
				 day = "0" + String.valueOf((int)table.getValueAt(row, column));
			}
			else if(table.getValueAt(row, column) != null){
				day = String.valueOf((int)table.getValueAt(row, column));
			}
			
			boolean groupHasEventToday = false;
			if(isGroupCalendar == false){
			for(int i = 0; i < groups.getItemCount(); i++){
			if(new Group(userForCalendarProgram.getUsername(),groups.getItem(i)).isThereAnEventToday(month+"/"+day+"/"+String.valueOf(currentYear))){
				groupHasEventToday = true;
				i = groups.getItemCount();
			}
			}
			}
				
				
			if (isGroupCalendar == false && (userForCalendarProgram.isThereAnEventToday(month+"/"+day+"/"+String.valueOf(currentYear))||groupHasEventToday)){ 
				setBackground(new Color(0, 250, 0));
				//table.setValueAt(table.getValueAt(row, column)+" "+"\u00B7",row,column);
			}
			else if(isGroupCalendar == true && new Group(userForCalendarProgram.getUsername(),(String)cmbGroups.getSelectedItem()).isThereAnEventToday(month+"/"+day+"/"+String.valueOf(currentYear))){
				setBackground(new Color(0, 250, 0));
			}
			else if(table.getValueAt(row, column) != null){ //Week
				setBackground(new Color(235, 235, 235));
			}
			else
				setBackground(new Color(245,245,245));
			
			if (value != null){
				if (Integer.parseInt(value.toString()) == realDay && currentMonth == realMonth && currentYear == realYear){ //Today
					setBackground(new Color(150, 150, 255));
				}
			}
			
			setBorder(null);
			setForeground(Color.black);
			
			return this;  
			
		}
		
	}

	static class btnPrev_Action implements ActionListener{
		public void actionPerformed (ActionEvent e){
			if (currentMonth == 0){ //Back one year
				currentMonth = 11;
				currentYear -= 1;
			}
			else{ //Back one month
				currentMonth -= 1;
			}
			refreshCalendar(currentMonth, currentYear,false);
		}
	}
	static class btnNext_Action implements ActionListener{
		public void actionPerformed (ActionEvent e){
			if (currentMonth == 11){ //Foward one year
				currentMonth = 0;
				currentYear += 1;
			}
			else{ //Foward one month
				currentMonth += 1;
			}
			refreshCalendar(currentMonth, currentYear, false);
		}
	}
	static class cmbYear_Action implements ActionListener{
		public void actionPerformed (ActionEvent e){
			if (cmbYear.getSelectedItem() != null){
				String b = cmbYear.getSelectedItem().toString();
				currentYear = Integer.parseInt(b);
				refreshCalendar(currentMonth, currentYear, false);
			}
		}
	}
	static class cmbDay_Action implements ActionListener{
		public void actionPerformed (ActionEvent e){
			String day, month;
			if( cmbDay.getSelectedItem() != null && cmbDay_clicked >0){
				if(Integer.parseInt(cmbDay.getSelectedItem().toString()) < 10){
					day = "0" +cmbDay.getSelectedItem().toString();
				}
				else{
					day =cmbDay.getSelectedItem().toString();
				}
				if(currentMonth < 9){
					month = "0" + (currentMonth+1);
				}
				else{
					month = String.valueOf(currentMonth+1);
				}
				DailyEvents.paintFrame(month + day + currentYear, userForCalendarProgram);
				frmMain.setVisible(false);
			}
			cmbDay_clicked++;
				
		}
	}
	static class cmbGroups_Action implements ActionListener{
		public void actionPerformed (ActionEvent e){
			
			if(cmbGroups.getSelectedItem() != null && cmbGroups.getSelectedItem() != "Your Calendar"){
				refreshCalendar(currentMonth,currentYear,true);
			}
			else if(cmbGroups.getSelectedItem() != null && cmbGroups.getSelectedItem() == "Your Calendar")
				refreshCalendar(currentMonth,currentYear,false);
			
			
			
		}
		
		
		
	}

}
