package Calendar;

import javax.imageio.IIOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.table.*;
import javax.swing.Timer; 
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;

public class GroupScreen {
	
	static JFrame frmMain;
	static Container pane;
	static JPanel pnlProfile;
	static DefaultTableModel mtblFriends, mtblGroups;
	static JTable tblFriends, tblGroups;
	static JScrollPane stblFriends, stblGroups;
	static JLabel lblUsername, profilePic;
	static JButton btnMyCalendar, btnModifyEvents;
	static private String username;
	
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
				frmMain = new JFrame("Groups");
				frmMain.setSize(550,385);
				pane = frmMain.getContentPane();
				pane.setLayout(null);
				frmMain.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Close when X is clicked

}


static private void addControls(){
	//add controls to pane
			pane.add(pnlProfile);
			pnlProfile.add(stblFriends);
			pnlProfile.add(stblGroups);
			pnlProfile.add(lblUsername);
			pnlProfile.add(profilePic);
			pnlProfile.add(btnMyCalendar);
			pnlProfile.add(btnModifyEvents);
			//frmMain.setJMenuBar(MenuExp.PrepareMenuBar());
}


public static void paintFrame() throws IOException{
	prepareFrame();
	//createControls();
	//setBorder();
	addControls();
	//registerActionListeners();
	//setBounds();
	//populateTables();
	//makeFrameVisible(true);
	//backgroundProcesses();
}


public static void main(String[] args) throws IOException {
	
	try {UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());}
	catch (ClassNotFoundException e) {}
	catch (InstantiationException e) {}
	catch (IllegalAccessException e) {}
	catch (UnsupportedLookAndFeelException e) {}

	paintFrame();
}

static class btnMyCalendar_Action implements ActionListener{
	public void actionPerformed(ActionEvent e){
		CalendarProgram.paintFrame();
		frmMain.repaint();
		frmMain.setVisible(false);
	}
}

static class btnModifyEvents_Action implements ActionListener{
	public void actionPerformed(ActionEvent e){
		
	}
}


}
