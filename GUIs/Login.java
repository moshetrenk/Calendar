package cal;

/* This class gets a username and password from the user's input
 * and queries them to the SQL database. If it is a valid input
 * then a new instance of User is created by that username
 */

import java.io.IOException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

@SuppressWarnings({"serial", "static-access"})
public class Login extends JFrame {
    private static JButton submitButton = new JButton(), createNewUserButton = new JButton();
    private static JLabel usernameLabel = new JLabel(), passwordLabel = new JLabel(),
    		titleLabel = new JLabel("<HTML><font face = 'Sitka Heading' size = '36'><I><U>The Calendar</U></I></font></HTML>", SwingConstants.CENTER),
    		notUsedLabel = new JLabel(), warningLabel = new JLabel();    
    private static JPanel jPanel1 = new JPanel();
    private static JTextField jTextField2 = new JTextField();
    private static JPasswordField jp1 = new JPasswordField();
    private static Login one;
    private static SQLHelper sqlHelper = new SQLHelper();

    public Login() {
    	//the following checks the operating system and displays the menubar accordingly
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
            }
        });
    }
    
    private void initComponents() {
    	setTitle("Login");
    	setJMenuBar(MenuExp.PrepareMenuBar(" "));
        warningLabel.setVisible(false);
        jPanel1.setPreferredSize(new Dimension(500, 385));
        jTextField2.setText("");
        jTextField2.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        jTextField2.setName("user"); 
        jTextField2.setSelectedTextColor(new Color(153, 153, 153));
        jTextField2.setVerifyInputWhenFocusTarget(false);
        usernameLabel.setFont(new Font("Tahoma", 0, 14));
        usernameLabel.setText("Username:");
        passwordLabel.setFont(new Font("Tahoma", 0, 14));
        passwordLabel.setText("Password:"); 
        notUsedLabel.setFont(new Font("Sitka Heading", 2, 36));
        warningLabel.setForeground(new Color(204, 0, 0));
        warningLabel.setText("Invalid Username/Password");
        submitButton.setFont(new Font("Franklin Gothic Heavy", 0, 18));
        submitButton.setText("Login");
        
        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                submitButtonActionPerformed(evt);
            }
        });
        
        createNewUserButton.setForeground(new Color(0, 102, 204));//___________
        createNewUserButton.setText("<HTML><U>Create New User</U>"/* + "<font color = EEEEEE>___________" + "<font color = 'black'> + "</HTML>"*/);
        createNewUserButton.setBorderPainted(false);
        createNewUserButton.setContentAreaFilled(false);
        createNewUserButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
            	  NewUser.paintFrame();
          		  one.rootPane.repaint();
          		  one.setVisible(false);

            }
        });

        //allows user to press enter instead of clicking the "Login" button
        jp1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
            	validateInput();	
            }
        });
        
        jTextField2.setCursor(new Cursor(Cursor.TEXT_CURSOR));
        
        GroupLayout jPanel1Layout = new GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup( GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(notUsedLabel,  GroupLayout.DEFAULT_SIZE,  GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup( GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap( GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup( GroupLayout.Alignment.LEADING, false)
                    .addComponent(usernameLabel,  GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE)
                    .addComponent(passwordLabel,  GroupLayout.DEFAULT_SIZE,  GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(30, 30, 30)
                .addGroup(jPanel1Layout.createParallelGroup( GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextField2,  GroupLayout.DEFAULT_SIZE, 159, Short.MAX_VALUE)
                    .addComponent(jp1))
                .addGap(132, 132, 132))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup( GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(173, 173, 173)
                        .addComponent(warningLabel))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(139, 139, 139)
                        .addComponent(submitButton,  GroupLayout.PREFERRED_SIZE, 213,  GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(createNewUserButton,  GroupLayout.PREFERRED_SIZE + 200, 194 + 200,  GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(148, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup( GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(notUsedLabel,  GroupLayout.PREFERRED_SIZE, 51,  GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addGroup(jPanel1Layout.createParallelGroup( GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField2,  GroupLayout.PREFERRED_SIZE,  GroupLayout.DEFAULT_SIZE,  GroupLayout.PREFERRED_SIZE)
                    .addComponent(usernameLabel,  GroupLayout.PREFERRED_SIZE, 20,  GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup( GroupLayout.Alignment.BASELINE)
                    .addComponent(jp1,  GroupLayout.PREFERRED_SIZE,  GroupLayout.DEFAULT_SIZE,  GroupLayout.PREFERRED_SIZE)
                    .addComponent(passwordLabel,  GroupLayout.PREFERRED_SIZE, 20,  GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addComponent(warningLabel)
                .addGap(31, 31, 31)
                .addComponent(submitButton,  GroupLayout.PREFERRED_SIZE, 55,  GroupLayout.PREFERRED_SIZE)
                .addPreferredGap( LayoutStyle.ComponentPlacement.RELATED, 61, Short.MAX_VALUE)
                .addComponent(createNewUserButton)
                .addContainerGap())
        );

         GroupLayout layout = new  GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup( GroupLayout.Alignment.LEADING)
            .addGroup( GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(titleLabel,  GroupLayout.DEFAULT_SIZE,  GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createParallelGroup( GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel1,  GroupLayout.PREFERRED_SIZE,  GroupLayout.DEFAULT_SIZE,  GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup( GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(titleLabel,  GroupLayout.PREFERRED_SIZE, 51,  GroupLayout.PREFERRED_SIZE)
                .addContainerGap(323, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup( GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel1,  GroupLayout.PREFERRED_SIZE,  GroupLayout.DEFAULT_SIZE,  GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        setResizable(false);
        pack();
    }                     

    private void submitButtonActionPerformed(java.awt.event.ActionEvent evt) {    
    	validateInput();    
    }                                        
    
    public static void paintFrame(){
    	java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
            	one = new Login();
            	 JFrame frame = (JFrame) SwingUtilities.getRoot(submitButton);
               	 one.centerWindow(frame);
                    one.setVisible(true);
            }
        });
    }
    
    public static void validateInput(){
      //if the username-password combination were valid then paintFrame to the
      //profile screen and pass along a new instance of the corresponding user
    	
  	  if (sqlHelper.queryUsernamePassword(jTextField2.getText(), sqlHelper.encrypt(new String(jp1.getPassword())))){
    		  warningLabel.setVisible(false);	

    		try {
    			SelfClosingMessage.loadingSymbol("         Loading... One moment", "Logging in", 2000);
    			Profile.paintFrame(new User(jTextField2.getText()));
    			
    		} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		  one.rootPane.repaint();
    		  one.setVisible(false);
  	  }
  	  else
  	      warningLabel.setVisible(true);	
    }
    
    public static void centerWindow(Window frame){
    	Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
    	int x = (int) ((dimension.getWidth() - frame.getWidth())/2);
    	int y = (int) ((dimension.getHeight() - frame.getHeight())/2);
    
    	frame.setLocation(x, y);
    }
    
    public static void main(String args[]) {
         try {
            for (UIManager.LookAndFeelInfo info :  UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                     UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch ( UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

         java.awt.EventQueue.invokeLater(new Runnable() {
             public void run() {
     			SelfClosingMessage.loadingSymbol("Fetching data... one moment", "Loading...", 1);
             	paintFrame();
             }
         });
    }
}
