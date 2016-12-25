package cal;

/* This class is a combination of my own work and several resources I found online. 
 * The class serves to allow prompts to be output and to disappear after a specified
 * amount of time
 */

import java.awt.event.*;
import javax.swing.*;

@SuppressWarnings("serial")
class SelfClosingMessage extends JFrame{
public static void main(String[] args){
	loadingSymbol("", "", 1);
}
	static void output(String text, String title, int time){
		JFrame frame = new JFrame();
		final JDialog dialog = new JDialog(frame, title, true);
	    dialog.setLocation(450, 300);
	    frame.setResizable(false);
	    JLabel label = new JLabel("      " + text);
	    dialog.setLocationRelativeTo(null);
	    dialog.setTitle(title);
	    dialog.add(label);
	    dialog.pack();
	    dialog.setSize(350, 110);
	    
	    Timer timer = new Timer(time, new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	            dialog.setVisible(false);
	            dialog.dispose();
	        }
	    });
	    timer.setRepeats(false);
	    timer.start();
	
	    dialog.setVisible(true);
	}

	public static void loadingSymbol(String message, String title, int time){
		JFrame frame = new JFrame(title);
		frame.setLocation(450, 300);
		frame.setAlwaysOnTop(true);

		ImageIcon icon = new ImageIcon("/Users/keiththompson/Downloads/src/edu/cuny/csi/csc330/ajax-loader.gif");
		frame.add(new JLabel(message, icon, JLabel.CENTER));

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400, 100);
		frame.setVisible(true);
		
		Timer timer = new Timer(time, new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	            frame.setVisible(false);
	            frame.dispose();
	        }
		});
		timer.setRepeats(false);
		timer.start();
		
		frame.setVisible(true);
	}
	
};
