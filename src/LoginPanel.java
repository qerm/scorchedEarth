import javax.swing.JPanel;
import javax.swing.BorderFactory;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import java.awt.Dimension;
/**
 * @author Team QERM
 * @created April 10, 2003
 */
public class LoginPanel extends JPanel implements ActionListener {

  /**
   * sends a login request
   */
  private JButton btLoginButton;
  
  /**
   * clears the Text Fields
   */
  private JButton btClearButton;
  
  /**
   * field for entering password
   */
  private JTextField tfPasswordField;
  
  /**
   * says "Password:"
   */
  private JLabel lbPasswordLabel;
  
  /**
   * says "User Name:"
   */
  private JLabel lbUserLabel;
  
  /**
   * field for entering user name
   */
  private JTextField tfUserField;
  
  /**
   * sends a new user request
   */
  private JButton btCreateNewButton;
  
  /**
   * the CleintThread
   */
  private ClientThread client;
  
  /**
   * Get the Client value.
   * @return the Client value.
   */
  public ClientThread getClient() {
    return client;
  }

  /**
   * Set the Client value.
   * @param newClient The new Client value.
   */
  public void setClient(ClientThread newClient) {
    this.client = newClient;
  }

  /**
   * the GlobalChatWindow that holds this panel
   */
  private GlobalChatWindow gcw;
  
  /**
   * Get the Gcw value.
   * @return the Gcw value.
   */
  public GlobalChatWindow getGcw() {
    return gcw;
  }

  /**
   * Set the Gcw value.
   * @param newGcw The new Gcw value.
   */
  public void setGcw(GlobalChatWindow newGcw) {
    this.gcw = newGcw;
  }

  
  /**
   * default contructor, Junit only
   */
  public LoginPanel() {
    this(null, null);
  }

  
  /**
   * Constructor for the LoginPanel object
   * @param newClient the ClientThread
   * @param newGcw the GlobalChatWindow
   */
  public LoginPanel(ClientThread newClient,
            GlobalChatWindow newGcw) {
    super();
    
    gcw = newGcw;
    client = newClient;
    setBorder(BorderFactory.createTitledBorder("Log in"));
    GridBagLayout gbLoginPanel = new GridBagLayout();
    GridBagConstraints gbcLoginPanel = new GridBagConstraints();
    setLayout(gbLoginPanel);
    
    btLoginButton = new JButton("Login");
    btLoginButton.addActionListener(this);
    gbcLoginPanel.gridx = 8;
    gbcLoginPanel.gridy = 11;
    gbcLoginPanel.gridwidth = 4;
    gbcLoginPanel.gridheight = 2;
    gbcLoginPanel.fill = GridBagConstraints.BOTH;
    gbcLoginPanel.weightx = 1;
    gbcLoginPanel.weighty = 0;
    gbcLoginPanel.anchor = GridBagConstraints.NORTH;
    gbLoginPanel.setConstraints(btLoginButton, gbcLoginPanel);
    add(btLoginButton);
    
    btClearButton = new JButton("Clear");
    btClearButton.addActionListener(this);
    gbcLoginPanel.gridx = 12;
    gbcLoginPanel.gridy = 11;
    gbcLoginPanel.gridwidth = 4;
    gbcLoginPanel.gridheight = 2;
    gbcLoginPanel.fill = GridBagConstraints.BOTH;
    gbcLoginPanel.weightx = 1;
    gbcLoginPanel.weighty = 0;
    gbcLoginPanel.anchor = GridBagConstraints.NORTH;
    gbLoginPanel.setConstraints(btClearButton, gbcLoginPanel);
    add(btClearButton);
    
    tfPasswordField = new JPasswordField();
    gbcLoginPanel.gridx = 8;
    gbcLoginPanel.gridy = 9;
    gbcLoginPanel.gridwidth = 8;
    gbcLoginPanel.gridheight = 2;
    gbcLoginPanel.fill = GridBagConstraints.BOTH;
    gbcLoginPanel.weightx = 1;
    gbcLoginPanel.weighty = 0;
    gbcLoginPanel.anchor = GridBagConstraints.NORTH;
    gbLoginPanel.setConstraints(tfPasswordField, gbcLoginPanel);
    add(tfPasswordField);
    
    lbPasswordLabel = new JLabel("Password:");
    gbcLoginPanel.gridx = 8;
    gbcLoginPanel.gridy = 7;
    gbcLoginPanel.gridwidth = 8;
    gbcLoginPanel.gridheight = 2;
    gbcLoginPanel.fill = GridBagConstraints.BOTH;
    gbcLoginPanel.weightx = 1;
    gbcLoginPanel.weighty = 0;
    gbcLoginPanel.anchor = GridBagConstraints.NORTH;
    gbLoginPanel.setConstraints(lbPasswordLabel, gbcLoginPanel);
    add(lbPasswordLabel);
    
    lbUserLabel = new JLabel("User Name:");
    gbcLoginPanel.gridx = 8;
    gbcLoginPanel.gridy = 3;
    gbcLoginPanel.gridwidth = 8;
    gbcLoginPanel.gridheight = 2;
    gbcLoginPanel.fill = GridBagConstraints.BOTH;
    gbcLoginPanel.weightx = 1;
    gbcLoginPanel.weighty = 0;
    gbcLoginPanel.anchor = GridBagConstraints.NORTH;
    gbLoginPanel.setConstraints(lbUserLabel, gbcLoginPanel);
    add(lbUserLabel);
    
    tfUserField = new JTextField();
    gbcLoginPanel.gridx = 8;
    gbcLoginPanel.gridy = 5;
    gbcLoginPanel.gridwidth = 8;
    gbcLoginPanel.gridheight = 2;
    gbcLoginPanel.fill = GridBagConstraints.BOTH;
    gbcLoginPanel.weightx = 1;
    gbcLoginPanel.weighty = 0;
    gbcLoginPanel.anchor = GridBagConstraints.NORTH;
    gbLoginPanel.setConstraints(tfUserField, gbcLoginPanel);
    add(tfUserField);
    
    btCreateNewButton = new JButton("Create New User");
    btCreateNewButton.addActionListener(this);
    gbcLoginPanel.gridx = 8;
    gbcLoginPanel.gridy = 13;
    gbcLoginPanel.gridwidth = 8;
    gbcLoginPanel.gridheight = 2;
    gbcLoginPanel.fill = GridBagConstraints.BOTH;
    gbcLoginPanel.weightx = 1;
    gbcLoginPanel.weighty = 0;
    gbcLoginPanel.anchor = GridBagConstraints.NORTH;
    gbLoginPanel.setConstraints(btCreateNewButton, gbcLoginPanel);
    add(btCreateNewButton);
    setPreferredSize(new Dimension(160, 150));
    tfUserField.requestFocus();
    
  } 
  
  /**
   * the actionlistener for LoginPanel
   * @param e the Action Event
   */
  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == btLoginButton) {
      if ((tfPasswordField.getText() != null)
            && isValidString(tfPasswordField.getText())) {
    if ((tfUserField.getText() != null)
            && isValidString(tfUserField.getText())) {
      client.sendLoginInfo(tfUserField.getText(), 
                   tfPasswordField.getText());
    } else {
      gcw.loginError("Invalid user name\nUse alphanumerics only");
    }
      } else {
    gcw.loginError("Invalid password\nUse alphanumerics only");
      }
    }
    if (e.getSource() == btClearButton) {
      tfPasswordField.setText("");
      tfUserField.setText("");
    }
    if (e.getSource() == btCreateNewButton) {
      if ((tfPasswordField.getText() != null)
            && isValidString(tfPasswordField.getText())) {
    if ((tfUserField.getText() != null)
            && isValidString(tfUserField.getText())) {
      client.sendNewLoginInfo(tfUserField.getText(), 
                  tfPasswordField.getText());
    } else {
      gcw.loginError("Invalid user name\nUse alphanumerics only");
    }
      } else {
    gcw.loginError("Invalid password\nUse alphanumerics only");
      } 
    }
  } 
  
  /**
   * checks to see if a String is alphanumeric
   * @param s the String to check
   * @return true if alphanumeric, false if not
   */
  private boolean isValidString(String s) {
    for (int i = 0; i < s.length(); i++) {
      char c = s.charAt(i);
      if (!(Character.isDigit(c)) 
            && !(Character.isLetter(c))) {
    return false;
      }
    }
    return true;
  }
  
  
} 


