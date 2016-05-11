package calendarTest;

import java.util.ArrayList;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Window;
import java.sql.Time;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
public class EventDetails extends javax.swing.JFrame {

    public EventDetails(User user1) {
        initComponents();
        user = user1;
    }

    private void initComponents() {
    	DailyEvents.setRowClickedToZero();
    	
    	
        titleLabel = new javax.swing.JLabel();
        eventNameLabel = new javax.swing.JLabel();
        eventDateLabel = new javax.swing.JLabel();
        eventStartTimeLabel = new javax.swing.JLabel();
        eventEndTimeLabel = new javax.swing.JLabel();
        eventGroupLabel = new javax.swing.JLabel();
        getNameLabel = new javax.swing.JLabel();
        getDateLabel = new javax.swing.JLabel();
        getStartTimeLabel = new javax.swing.JLabel();
        getEndTimeLabel = new javax.swing.JLabel();
        getGroupLabel = new javax.swing.JLabel();
        deleteEventButton = new javax.swing.JButton();
        acceptEventButton = new javax.swing.JButton();
        rejectEventButton = new javax.swing.JButton();
        eventDescLabel = new javax.swing.JLabel();
        getDescLabel = new javax.swing.JLabel();
        getDescLabel2 = new javax.swing.JLabel();
        getDescLabel3 = new javax.swing.JLabel();

        //setDefaultCloseOperation(DailyEvents.setRowClickedToZero());

        titleLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        
        //apparently you don't need to close these html tags
        titleLabel.setText("<HTML><B>Event Details:");
        eventDescLabel.setText("<HTML><B>Event Description:");
        getDescLabel.setText("getDesc");
        getDescLabel2.setText("getDescPart2");
        getDescLabel3.setText("getDescPart3");
        getNameLabel.setText("getName");
        getDateLabel.setText("getDate");
        getStartTimeLabel.setText("getStartTime");
        getEndTimeLabel.setText("getEndTime");
        getGroupLabel.setText("getGroup");
        
        eventNameLabel.setText("<HTML><B>Event Name:");
        eventDateLabel.setText("<HTML><B>Event Date:");
        eventStartTimeLabel.setText("<HTML><B>Event Start Time:");
        eventEndTimeLabel.setText("<HTML><B>Event End Time:");
        eventGroupLabel.setText("<HTML><B>Event Group:");

        //need to display times as 6:30 PM, not 18:30:00
        //need to get event data from user, not from sql
        
        Object obj = list.get(4), obj2 = list.get(5);
        
        getNameLabel.setText((String) list.get(1));
        getDateLabel.setText((String) list.get(3));
        getStartTimeLabel.setText(obj.toString());
        getEndTimeLabel.setText(obj2.toString());
        getGroupLabel.setText((String) list.get(2));
        
        if (list.get(6).toString().length() > 90)
        {
        	getDescLabel.setText((String) list.get(6).toString().substring(0, 29));	
        	getDescLabel2.setText((String) list.get(6).toString().substring(30, 90));
        	getDescLabel3.setText((String) list.get(6).toString().substring(90, list.get(6).toString().length()));
        }
        else if (list.get(6).toString().length() > 30){
        	getDescLabel.setText((String) list.get(6).toString().substring(0, 29));	
        	getDescLabel2.setText((String) list.get(6).toString().substring(30, list.get(6).toString().length()));
        	getDescLabel3.setText("");
        }
        else{
        	getDescLabel.setText((String) list.get(6).toString().substring(0, list.get(6).toString().length()));	
        	getDescLabel2.setText("");
        	getDescLabel3.setText("");
        }
        deleteEventButton.setText("<HTML><B>Delete Event");
        deleteEventButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteEventButtonActionPerformed(evt);
            }
        });
        acceptEventButton.setText("<HTML><B>Accept Event");
        rejectEventButton.setText("<HTML><B>Reject Event");
    	
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(getDescLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(eventDateLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(eventNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(eventStartTimeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(eventGroupLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(eventEndTimeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(eventDescLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(getDescLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(getGroupLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(getStartTimeLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(getEndTimeLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(getDateLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(getNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(0, 0, Short.MAX_VALUE))))
                            .addComponent(getDescLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 311, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(87, 87, 87)
                                .addComponent(deleteEventButton, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(131, 131, 131)
                                .addComponent(titleLabel)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(titleLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(eventNameLabel)
                    .addComponent(getNameLabel))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(eventDateLabel)
                    .addComponent(getDateLabel))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(eventStartTimeLabel)
                    .addComponent(getStartTimeLabel))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(eventEndTimeLabel)
                    .addComponent(getEndTimeLabel))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(eventGroupLabel)
                    .addComponent(getGroupLabel))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(eventDescLabel)
                    .addComponent(getDescLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(getDescLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(getDescLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addComponent(deleteEventButton)
                .addContainerGap())
        );
        setResizable(false);
        pack();
    }// </editor-fold>                        

    private void deleteEventButtonActionPerformed(java.awt.event.ActionEvent evt) {        
        int i = okcancel();
        
        if (i == 0){
        	user.deleteEvent(locTimeDate);
        	/**********************************************************************************/
        	/**********************************************************************************/
        	/**********************************************************************************/
        	/**********************************************************************************/
        	//remove from the user's hashmaps
        	/**********************************************************************************/
        	/**********************************************************************************/
        	/**********************************************************************************/
        	/**********************************************************************************/
        
        setVisible(false);
        DailyEvents.makeFrameVisible(false);
        String dateToView = ((String) list.get(3)).substring(0, 2) + ((String) list.get(3)).substring(3, 5) + ((String) list.get(3)).substring(6);
        
        DailyEvents.paintFrame(dateToView, user);
        
        }
    }                                                 

    public static int okcancel() {
         int result = JOptionPane.showConfirmDialog((Component) null, "Are You Sure? Once Deleted, Events Cannot Be Returned",
            "Delete Event", JOptionPane.OK_CANCEL_OPTION);
    return result;
  }
    /*public static void makeFrameVisible(boolean decision){
    	
    	one.getContentPane().setVisible(decision);
    }
    */
    public static void centerWindow(Window frame){
   	 Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
   	 int x = (int) ((dimension.getWidth() - frame.getWidth())/4);
   	 int y = (int) ((dimension.getHeight() - frame.getHeight())/4);
   	 
   	 frame.setLocation(x, y);
    }
   /* public static void test(){
    	JFrame frame = (JFrame) SwingUtilities.getRoot(deleteEventButton);
    	frame.remove(deleteEventButton);
    	frame.getContentPane().add(acceptEventButton);
    	frame.getContentPane().add(rejectEventButton);
    	acceptEventButton.setBounds(110 ,frame.getHeight() -  75, 75, 50);
    	rejectEventButton.setBounds(215 ,frame.getHeight() - 75, 75, 50);
    	
    }*/
    
    public static void paintFrame(Event event, User user){
    		list = event.getEventDetails();
    		locTimeDate = event.getDateTime();
    	 	one = new EventDetails(user);
        	JFrame frame = (JFrame) SwingUtilities.getRoot(deleteEventButton);
        	one.centerWindow(frame);
        	frame.setSize(400, 450);
        	one.setVisible(true);
        	
    }

    public static void paintFrame(User user, String timeDate){
   	 java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
            locTimeDate = timeDate;
            Event event = user.getEvent(timeDate);
            list = event.getEventDetails();
            one = new EventDetails(user);
           	JFrame frame = (JFrame) SwingUtilities.getRoot(deleteEventButton);
           	one.centerWindow(frame);
           	frame.setSize(400, 450);
           	one.setVisible(true);
           	
            }
        });
   }
    
   public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(EventDetails.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EventDetails.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EventDetails.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EventDetails.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                paintFrame(new User("moshe"), "04/07/201613:15:00");
            }
        });
    }

    // Variables declaration - do not modify
    static ArrayList list = new ArrayList(); 
    private User user;
    private static String locTimeDate;
    private static javax.swing.JButton deleteEventButton;
    private static javax.swing.JButton acceptEventButton;
    private static javax.swing.JButton rejectEventButton;
    private javax.swing.JLabel eventDateLabel;
    private javax.swing.JLabel eventDescLabel;
    private javax.swing.JLabel eventEndTimeLabel;
    private javax.swing.JLabel eventGroupLabel;
    private javax.swing.JLabel eventNameLabel;
    private javax.swing.JLabel eventStartTimeLabel;
    private javax.swing.JLabel getDateLabel;
    private javax.swing.JLabel getDescLabel;
    private javax.swing.JLabel getDescLabel2;
    private javax.swing.JLabel getDescLabel3;
    private javax.swing.JLabel getEndTimeLabel;
    private javax.swing.JLabel getGroupLabel;
    private javax.swing.JLabel getNameLabel;
    private javax.swing.JLabel getStartTimeLabel;
    private javax.swing.JLabel titleLabel;
    
    static EventDetails one;
    // End of variables declaration                   
}
