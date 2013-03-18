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
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.TextAction;
import javax.swing.JScrollPane;
import javax.swing.JScrollBar;

import java.util.Vector;
/**
 * @author Team QERM
 * @created April 10, 2003
 */
public class GameChatPanel extends JPanel implements ActionListener {
  /**
   * The chat area
   */
   private JTextArea taChatArea;

  /**
   * wraps the taChatArea so it can scroll
   */
  private JScrollPane spChatPane;
   
  /**
   * the field where you type game chat messages
   */
  private JTextField tfChatField;

  /**
   * the ClientThread
   */
  private ClientThread client;
   
  /**
   * Accessor for client
   * @return client
   */
  public ClientThread getClient() {
    return client;
  }

  /**
   * mutator for client
   * @param newClient the new client value
   */
  public void setClient(ClientThread newClient) {
    client = newClient;
  }

  /**
   * default constructor.
   * don't call this unless you're Juniting.
   * bad things will happen.
   */
  public GameChatPanel() {
    //    this(null);
  }

  /**
   *Constructor for the GameChatPanel object
   * @param newClient the ClientThread
   */
  public GameChatPanel(ClientThread newClient) {
    super();
    client = newClient;
    setBorder(BorderFactory.createTitledBorder("Game Chat"));
    GridBagLayout gbGameChatPanel = new GridBagLayout();
    GridBagConstraints gbcGameChatPanel = new GridBagConstraints();
    setLayout(gbGameChatPanel);
    
    taChatArea = new JTextArea(2, 10);
    taChatArea.setEditable(false);
    taChatArea.setLineWrap(true);
    taChatArea.setWrapStyleWord(true);
    

    spChatPane = new JScrollPane(taChatArea);
    gbcGameChatPanel.gridx = 1;
    gbcGameChatPanel.gridy = 1;
    gbcGameChatPanel.gridwidth = 18;
    gbcGameChatPanel.gridheight = 7;
    gbcGameChatPanel.fill = GridBagConstraints.BOTH;
    gbcGameChatPanel.weightx = 1;
    gbcGameChatPanel.weighty = 1;
    gbcGameChatPanel.anchor = GridBagConstraints.NORTH;
    gbGameChatPanel.setConstraints(spChatPane, gbcGameChatPanel);
    add(spChatPane);
    
    tfChatField = new JTextField();
    gbcGameChatPanel.gridx = 1;
    gbcGameChatPanel.gridy = 9;
    gbcGameChatPanel.gridwidth = 18;
    gbcGameChatPanel.gridheight = 1;
    gbcGameChatPanel.fill = GridBagConstraints.HORIZONTAL;
    gbcGameChatPanel.weightx = 0;
    gbcGameChatPanel.weighty = 0;
    gbcGameChatPanel.anchor = GridBagConstraints.NORTH;
    gbGameChatPanel.setConstraints(tfChatField, gbcGameChatPanel);
    
    tfChatField.setActionCommand("Game Chat");
    tfChatField.addActionListener(new TextAction("") {
    public void actionPerformed(ActionEvent e) {
      if (e.getActionCommand().equals("Game Chat")) {
        client.gameChatSend(tfChatField.getText());
        tfChatField.setText("");
      } else {
        System.out.println(e.getActionCommand());
      }
    }
      });
    
    add(tfChatField);
  } 
  
  /**
   * does stuff
   * @param e ActionEvent
   */
  public void actionPerformed(ActionEvent e) {
  } 
  
  /**
   * called to update the chat window
   * @param name the user's name 
   * @param message the message the user sent
   */
  public void updateChatWindow(String name, String message) {
    taChatArea.append(name + " : " + message + "\n");
    JScrollBar temp = spChatPane.getVerticalScrollBar();
    temp.setValue(temp.getMaximum());
  }
  
  /**
   * called when a new player list is sent
   * @param playerList Vector
   */
  public void updatePlayerList(Vector playerList) {
  }
  
} 
