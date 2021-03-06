//======================================================
// Source code generated by jvider v1.2.3 UNREGISTERED version.
// http://www.jvider.com/
//======================================================
//import javax.swing.JFrame;
//import javax.swing.UIManager;
//import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Dimension;
/**
 * @author Team QERM
 * @created April 10, 2003
 */
public class PromptPanel extends JPanel implements ActionListener {

  /**
   * The connect button
   */
  private JButton btConnectButton;
  
  /**
   * clears the text fields
   */
  private JButton btClearButton;
  
  /**
   * place to type in port number
   */
  private JTextField tfPortField;
  
  /**
   * labels the port field as such
   */
  private JLabel lbPortLabel;
  
  /**
   * labels the ip field as such
   */
  private JLabel lbServerLabel;
  
  /**
   * place where you type in the server name
   */
  private JTextField tfServerField;
  
  /**
   * The GlobalChatWindow displaying this panel
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
   * Default Constructor
   */
  public PromptPanel() {
    //    this(null);
  }
  
  /**
   *Constructor for the PromptPanel object
   * @param newGcw GlobalChatWindow
   */
  public PromptPanel(GlobalChatWindow newGcw) {
    super();
    gcw = newGcw;
    setBorder(BorderFactory.createTitledBorder("Connect"));
    GridBagLayout gbPromptPanel = new GridBagLayout();
    GridBagConstraints gbcPromptPanel = new GridBagConstraints();
    setLayout(gbPromptPanel);
    
    btConnectButton = new JButton("Connect");
    btConnectButton.addActionListener(this) ;
    gbcPromptPanel.gridx = 8;
    gbcPromptPanel.gridy = 11;
    gbcPromptPanel.gridwidth = 4;
    gbcPromptPanel.gridheight = 2;
    gbcPromptPanel.fill = GridBagConstraints.BOTH;
    gbcPromptPanel.weightx = 1;
    gbcPromptPanel.weighty = 0;
    gbcPromptPanel.anchor = GridBagConstraints.NORTH;
    gbPromptPanel.setConstraints(btConnectButton, gbcPromptPanel);
    add(btConnectButton);
    
    btClearButton = new JButton("Clear");
    btClearButton.addActionListener(this);
    gbcPromptPanel.gridx = 12;
    gbcPromptPanel.gridy = 11;
    gbcPromptPanel.gridwidth = 4;
    gbcPromptPanel.gridheight = 2;
    gbcPromptPanel.fill = GridBagConstraints.BOTH;
    gbcPromptPanel.weightx = 1;
    gbcPromptPanel.weighty = 0;
    gbcPromptPanel.anchor = GridBagConstraints.NORTH;
    gbPromptPanel.setConstraints(btClearButton, gbcPromptPanel);
    add(btClearButton);
    
    tfPortField = new JTextField();
    gbcPromptPanel.gridx = 8;
    gbcPromptPanel.gridy = 9;
    gbcPromptPanel.gridwidth = 8;
    gbcPromptPanel.gridheight = 2;
    gbcPromptPanel.fill = GridBagConstraints.BOTH;
    gbcPromptPanel.weightx = 1;
    gbcPromptPanel.weighty = 0;
    gbcPromptPanel.anchor = GridBagConstraints.NORTH;
    gbPromptPanel.setConstraints(tfPortField, gbcPromptPanel);
    add(tfPortField);
    
    lbPortLabel = new JLabel("Port Number:");
    gbcPromptPanel.gridx = 8;
    gbcPromptPanel.gridy = 7;
    gbcPromptPanel.gridwidth = 8;
    gbcPromptPanel.gridheight = 2;
    gbcPromptPanel.fill = GridBagConstraints.BOTH;
    gbcPromptPanel.weightx = 1;
    gbcPromptPanel.weighty = 0;
    gbcPromptPanel.anchor = GridBagConstraints.NORTH;
    gbPromptPanel.setConstraints(lbPortLabel, gbcPromptPanel);
    add(lbPortLabel);
    
    lbServerLabel = new JLabel("Server name/IP:");
    gbcPromptPanel.gridx = 8;
    gbcPromptPanel.gridy = 3;
    gbcPromptPanel.gridwidth = 8;
    gbcPromptPanel.gridheight = 2;
    gbcPromptPanel.fill = GridBagConstraints.BOTH;
    gbcPromptPanel.weightx = 1;
    gbcPromptPanel.weighty = 0;
    gbcPromptPanel.anchor = GridBagConstraints.NORTH;
    gbPromptPanel.setConstraints(lbServerLabel, gbcPromptPanel);
    add(lbServerLabel);
    
    tfServerField = new JTextField();
    gbcPromptPanel.gridx = 8;
    gbcPromptPanel.gridy = 5;
    gbcPromptPanel.gridwidth = 8;
    gbcPromptPanel.gridheight = 2;
    gbcPromptPanel.fill = GridBagConstraints.BOTH;
    gbcPromptPanel.weightx = 1;
    gbcPromptPanel.weighty = 0;
    gbcPromptPanel.anchor = GridBagConstraints.NORTH;
    gbPromptPanel.setConstraints(tfServerField, gbcPromptPanel);
    add(tfServerField);
    setPreferredSize(new Dimension(175, 125));
  } 
  
  /**
   * our actionPerformed method for PromptPanel
   * @param e ActionEvent
   */
  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == btConnectButton) {
      try {
    int port = Integer.parseInt(tfPortField.getText());
    gcw.connect(tfServerField.getText(), port);
      } catch (NumberFormatException nfe) {
    gcw.loginError("Specified port is not a number!");
    tfPortField.setText("");
      }
    }
    if (e.getSource() == btClearButton) {
      tfPortField.setText("");
      tfServerField.setText("");
    }
  }
} 

