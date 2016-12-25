package cal;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.text.BadLocationException;

public class GroupScreen extends  JFrame {

	static GroupScreen one;
    static User user;
    List groups = new List();
    private SQLHelper sqlHelper = new SQLHelper();
    private static String username;
    private static int numGroups;
    private  JButton addGroupButton =  new JButton();
	private static JButton addMemberToGroupButton = new JButton();
    private  JTextField addMemberTextBox = new JTextField(), newGroupTextBox = new JTextField();
    private  JLabel groupTakenLabel = new JLabel(), jLabel1 = new JLabel(), jLabel2 = new JLabel(), jLabel4 = new JLabel(), jLabel5 = new JLabel();
    private  JScrollPane jScrollPane1 = new JScrollPane();
    private  JTable table = new JTable();
    
	public GroupScreen() {  	
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
        
	        setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
	        addWindowListener(new WindowAdapter() {
	            public void windowClosing(WindowEvent e) {
	                //sqlHelper.closePath();
	                //System.exit(1);
	            	Profile.makeFrameVisible(false);
	            	setVisible(false);
	            	try {
						Profile.paintFrame(user);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
	            	
	            }
	        });
	        
	    }

    @SuppressWarnings("unchecked")
    private void initComponents() {
    	
      
        setTitle("My Groups");
      
        jLabel5.setVisible(false);
        setDefaultCloseOperation( WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        jLabel1.setText("My Groups");
        
        jLabel5.setText("Member already in this group");
        
        jLabel5.setBounds(100, 50, 245, 205);
        
        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {{null}, {null}, {null}, {null}, {null}, {null}, {null}, {null}, {null}, {null}}, 
            					new String [] {"Groups"}
        ));
        
        table.setCellSelectionEnabled(true);
       
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                tableMouseReleased(evt);
            }
        });
        
        
        jScrollPane1.setViewportView(table);
        setGroups();
        jLabel2.setText("Enter a group name to create");

        newGroupTextBox.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                newGroupTextBoxKeyReleased(evt);
            }
        });

        addGroupButton.setText("Ok");
        addGroupButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addGroupButtonActionPerformed(evt);
            }
        });

        groupTakenLabel.setText("Group name taken");

        jLabel4.setText("Add members:");

        addMemberTextBox.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                addMemberTextBoxKeyReleased(evt);
            }
        });

        addMemberToGroupButton.setText("Ok");
        addMemberToGroupButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addMemberToGroupButtonActionPerformed(evt);
            }
        });
        groupTakenLabel.setVisible(false);
        addGroupButton.setEnabled(false);
        addMemberToGroupButton.setEnabled(false);
        addMemberTextBox.setEnabled(false);
        
         GroupLayout layout = new  GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup( GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup( GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1,  GroupLayout.PREFERRED_SIZE, 155,  GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1,  GroupLayout.PREFERRED_SIZE, 115,  GroupLayout.PREFERRED_SIZE))
                .addPreferredGap( LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup( GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup( GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup( GroupLayout.Alignment.LEADING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(newGroupTextBox,  GroupLayout.PREFERRED_SIZE, 107,  GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap( LayoutStyle.ComponentPlacement.RELATED,  GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(addGroupButton,  GroupLayout.PREFERRED_SIZE, 44,  GroupLayout.PREFERRED_SIZE)
                                    .addGap(8, 8, 8))
                                .addComponent(jLabel2,  GroupLayout.DEFAULT_SIZE, 189, Short.MAX_VALUE)
                                .addComponent(groupTakenLabel,  GroupLayout.DEFAULT_SIZE,  GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addComponent(jLabel4))
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(addMemberTextBox,  GroupLayout.PREFERRED_SIZE, 107,  GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap( LayoutStyle.ComponentPlacement.RELATED,  GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(addMemberToGroupButton,  GroupLayout.PREFERRED_SIZE, 44,  GroupLayout.PREFERRED_SIZE)
                        .addGap(15, 15, 15))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup( GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1)
                .addGap(15, 15, 15)
                .addComponent(jLabel2)
                .addPreferredGap( LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup( GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup( GroupLayout.Alignment.BASELINE)
                            .addComponent(newGroupTextBox,  GroupLayout.PREFERRED_SIZE,  GroupLayout.DEFAULT_SIZE,  GroupLayout.PREFERRED_SIZE)
                            .addComponent(addGroupButton))
                        .addPreferredGap( LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(groupTakenLabel,  GroupLayout.PREFERRED_SIZE, 22,  GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap( LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4)
                        .addPreferredGap( LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup( GroupLayout.Alignment.BASELINE)
                            .addComponent(addMemberTextBox,  GroupLayout.PREFERRED_SIZE,  GroupLayout.DEFAULT_SIZE,  GroupLayout.PREFERRED_SIZE)
                            .addComponent(addMemberToGroupButton)))
                    .addComponent(jScrollPane1,  GroupLayout.PREFERRED_SIZE, 175,  GroupLayout.PREFERRED_SIZE))
                .addContainerGap(46, Short.MAX_VALUE))
            
            
        );
        setResizable(false);
        JFrame frame = (JFrame) SwingUtilities.getRoot(jLabel1);
        frame.setJMenuBar(MenuExp.PrepareMenuBar(user));
        frame.add(jLabel5);
        jLabel5.setBounds(200,155, 200,50);
        pack();
    }                  
    
    private void tableMouseReleased(java.awt.event.MouseEvent evt) {                                    
    	String locGroupName = groups.getItem(table.getSelectedRow());
    	addMemberTextBox.setEnabled(true);
    	
    } 
   

    private void addMemberToGroupButtonActionPerformed(java.awt.event.ActionEvent evt) {   
    	ArrayList<String> list = new ArrayList<String>();
    	list.add(groups.getItem(table.getSelectedRow()));
    	list.add(addMemberTextBox.getText());
    	list.add(username);
    	
    	Notification_Group temp = new Notification_Group(list);
    	
    	sqlHelper.addNotificationGroupRequest(temp);
       
    	SelfClosingMessage.output(addMemberTextBox.getText() + " has been added to " + groups.getItem(table.getSelectedRow()), "Success", 2500);
    	addMemberTextBox.setText("");
    }                                                      
   
    private void newGroupTextBoxKeyReleased(java.awt.event.KeyEvent evt) {                                            
             if(newGroupTextBox.getText().length() >= 16){
	            try{
	            newGroupTextBox.setText(newGroupTextBox.getText(0, 16));
	            }catch (BadLocationException e) {
	                e.printStackTrace();
	            }
	        }
    		String input = newGroupTextBox.getText();
	    	String get = new String(sqlHelper.getGroupName(input));

	    	if((input.toLowerCase()).equals((get).toLowerCase())){
	    		groupTakenLabel.setVisible(true);
                        addGroupButton.setEnabled(false);                
	    	}
	    	else if(!(input.toLowerCase()).equals((get).toLowerCase())){
	    		groupTakenLabel.setVisible(false);
                        addGroupButton.setEnabled(true);
            }
    }                                           
                                   

    private void addMemberTextBoxKeyReleased(java.awt.event.KeyEvent evt) {                                             
       jLabel5.setVisible(false);
        if(addMemberTextBox.getText().length() >= 16){
            try{
                addMemberTextBox.setText(addMemberTextBox.getText(0, 16));
            }catch (BadLocationException e) {
                e.printStackTrace();
            }
        }
        String input = addMemberTextBox.getText();
        String get = new String(sqlHelper.getUsername(input));

       ArrayList<String> membersInSelectedGroup = sqlHelper.getGroupMembersByGroupName(groups.getItem(table.getSelectedRow()));
       // ArrayList<String> membersInSelectedGroup = null;
        boolean isMemberOfGroup = false;
        
       for(int i = 0; i < membersInSelectedGroup.size(); i++){
    	   if(input.toLowerCase().equals(membersInSelectedGroup.get(i).toLowerCase()))
    			   isMemberOfGroup = true;
       }
        
       	
        if((input.toLowerCase()).equals((get).toLowerCase()) && !isMemberOfGroup){
            addMemberToGroupButton.setEnabled(true);
        }
        else if((input.toLowerCase()).equals((get).toLowerCase()) && isMemberOfGroup ){
            addMemberToGroupButton.setEnabled(false);
            
            	jLabel5.setText(input + " is in " + groups.getItem(table.getSelectedRow()));
            	jLabel5.setVisible(true);
            
            	
        }
        else if(!(input.toLowerCase()).equals((get).toLowerCase())){
        	addMemberToGroupButton.setEnabled(false);
        }
    }
    
    private void addGroupButtonActionPerformed(java.awt.event.ActionEvent evt) {                                               
        sqlHelper.newGroup(username, newGroupTextBox.getText());
        SelfClosingMessage.output("Group Created", "", 2500);
        paintFrame(user);
    }                                              
    
    private void setGroups(){
    	groups = sqlHelper.getGroups(username);
    	numGroups = groups.getItemCount();
    	
    	for(int i = 0; i < groups.getItemCount(); i++){
    		table.setValueAt(groups.getItem(i), i , 0);
    	}
    }  
    

    public static void centerWindow(Window frame){
   	 Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
   	 int x = (int) ((dimension.getWidth() - frame.getWidth())/2);
   	 int y = (int) ((dimension.getHeight() - frame.getHeight())/2);
   	 
   	 frame.setLocation(x, y);
    }
    
    public static void paintFrame(User locUser){
   	 user = locUser;
   	 username = user.getUsername();
   	 java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
           	 one = new GroupScreen();
            	 JFrame frame = (JFrame) SwingUtilities.getRoot(addMemberToGroupButton);
           	 one.centerWindow(frame);
                one.setVisible(true);
            }
        });
   }
    
    public static void main(String args[]) {
        try {
            for ( UIManager.LookAndFeelInfo info :  UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                     UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GroupScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GroupScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GroupScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch ( UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GroupScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
               	paintFrame(new User("moshe"));
            }
        });
    }                        
}
