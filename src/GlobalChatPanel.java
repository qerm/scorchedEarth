import javax.swing.JPanel;
import javax.swing.BorderFactory;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JList;
import java.awt.Dimension;
import javax.swing.text.TextAction;
import javax.swing.JScrollPane;
import javax.swing.JScrollBar;
import javax.swing.JPasswordField;

import java.util.Vector;
/**
 * @author  Team QERM
 * @created April 9, 2003
 */
class GlobalChatPanel extends JPanel implements ActionListener {
  /**
   * The area where you type chat messages
   */
  private JTextField tfChatField;
  
  /**
   * The area where the list of users appears
   */
  private JTextArea taPlayerList;
  
  /**
   * So that the player list can scroll
   */
  private JScrollPane spPlayerPane; 

  /**
   * the "Games" heading above the game list
   */
  private JLabel lbGameHeading;
  
  /**
   * the "Players" heading above the players list
   */
  private JLabel lbPlayerHeading;
  
  /**
   * requests game info
   */
  private JButton btGameInfoButton;
  
  /**
   * requests a create game
   */
  private JButton btCreateGameButton;
  
  /**
   * requests a join game
   */
  private JButton btJoinGameButton;
  
  /**
   * the lists of games available
   */
  private JList lsGameList;
  
  /**
   * the area where chat conversations appear
   */
  private JTextArea taChatArea;

  /**
   * so that the chat area can scroll
   */
  private JScrollPane spChatPane;
  
  /**
   * the ClientThread
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
   * The GlobalChatWindow that displays this panel
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
   * The GameOptionsDialog this panel will create
   */
  private GameOptionsDialog gd;
  
  /**
   * Get the Gd value.
   * @return the Gd value.
   */
  public GameOptionsDialog getGd() {
    return gd;
  }

  /**
   * Set the Gd value.
   * @param newGd The new Gd value.
   */
  public void setGd(GameOptionsDialog newGd) {
    this.gd = newGd;
  }

  /**
   * status variable, allows gd to be non-modal.
   */
  private boolean creating = false;
  
  /**
   * Get the Creating value.
   * @return the Creating value.
   */
  public boolean isCreating() {
    return creating;
  }

  /**
   * Set the Creating value.
   * @param newCreating The new Creating value.
   */
  public void setCreating(boolean newCreating) {
    this.creating = newCreating;
  }

/**
* the password field
*/
  private JPasswordField passwordField;

  /**
   * default constructor. Junit only
   */
  public GlobalChatPanel() {
    //    this(null, null);
  }

  /**
    *Constructor for the GlobalChatPanel object
    * @param newClient the ClientThread
    * @param newGcw The GlobalChatWindow
    */
   public GlobalChatPanel(ClientThread newClient, 
              GlobalChatWindow newGcw) {
      super();
      passwordField = new JPasswordField();
      client = newClient;
      gcw = newGcw;
      setBorder(BorderFactory.createTitledBorder(""));
      GridBagLayout gbGlobalChatPanel = new GridBagLayout();
      GridBagConstraints gbcGlobalChatPanel = new GridBagConstraints();
      setLayout(gbGlobalChatPanel);
      tfChatField = new JTextField();
      tfChatField.setActionCommand("Chat");
      tfChatField.addActionListener(new TextAction("") {
    public void actionPerformed(ActionEvent e) {
      if (e.getActionCommand().equals("Chat")) {
      client.globalChatSend(tfChatField.getText());
        tfChatField.setText("");
      } 
    }
      });
      gbcGlobalChatPanel.gridx = 3;
      gbcGlobalChatPanel.gridy = 21;
      gbcGlobalChatPanel.gridwidth = 20;
      gbcGlobalChatPanel.gridheight = 1;
      gbcGlobalChatPanel.fill = GridBagConstraints.BOTH;
      gbcGlobalChatPanel.weightx = 1;
      gbcGlobalChatPanel.weighty = 0;
      gbcGlobalChatPanel.anchor = GridBagConstraints.NORTH;
      gbGlobalChatPanel.
    setConstraints(tfChatField, gbcGlobalChatPanel);
      add(tfChatField);
      taPlayerList = new JTextArea(2, 10);
      taPlayerList.setEditable(false);
      spPlayerPane = new JScrollPane(taPlayerList);
      gbcGlobalChatPanel.gridx = 0;
      gbcGlobalChatPanel.gridy = 1;
      gbcGlobalChatPanel.gridwidth = 1;
      gbcGlobalChatPanel.gridheight = 17;
      gbcGlobalChatPanel.fill = GridBagConstraints.VERTICAL;
      gbcGlobalChatPanel.weightx = 0;
      gbcGlobalChatPanel.weighty = 1;
      gbcGlobalChatPanel.anchor = GridBagConstraints.NORTH;
      gbGlobalChatPanel.
      setConstraints(spPlayerPane, gbcGlobalChatPanel);
      add(spPlayerPane);
      lbGameHeading = new JLabel("Games:");
      gbcGlobalChatPanel.gridx = 1;
      gbcGlobalChatPanel.gridy = 0;
      gbcGlobalChatPanel.gridwidth = 1;
      gbcGlobalChatPanel.gridheight = 1;
      gbcGlobalChatPanel.fill = GridBagConstraints.NONE;
      gbcGlobalChatPanel.weightx = 0;
      gbcGlobalChatPanel.weighty = 0;
      gbcGlobalChatPanel.anchor = GridBagConstraints.NORTH;
      gbGlobalChatPanel.
    setConstraints(lbGameHeading, gbcGlobalChatPanel);
      add(lbGameHeading);
      lbPlayerHeading = new JLabel("Users Online:");
      gbcGlobalChatPanel.gridx = 0;
      gbcGlobalChatPanel.gridy = 0;
      gbcGlobalChatPanel.gridwidth = 1;
      gbcGlobalChatPanel.gridheight = 1;
      gbcGlobalChatPanel.fill = GridBagConstraints.NONE;
      gbcGlobalChatPanel.weightx = 0;
      gbcGlobalChatPanel.weighty = 0;
      gbcGlobalChatPanel.anchor = GridBagConstraints.NORTH;
      gbGlobalChatPanel.
    setConstraints(lbPlayerHeading, gbcGlobalChatPanel);
      add(lbPlayerHeading);
      btGameInfoButton = new JButton("Game Info");
      btGameInfoButton.addActionListener(this);
      gbcGlobalChatPanel.gridx = 0;
      gbcGlobalChatPanel.gridy = 18;
      gbcGlobalChatPanel.gridwidth = 2;
      gbcGlobalChatPanel.gridheight = 1;
      gbcGlobalChatPanel.fill = GridBagConstraints.HORIZONTAL;
      gbcGlobalChatPanel.weightx = 0;
      gbcGlobalChatPanel.weighty = 0;
      gbcGlobalChatPanel.anchor = GridBagConstraints.NORTH;
      gbGlobalChatPanel.
    setConstraints(btGameInfoButton, gbcGlobalChatPanel);
      add(btGameInfoButton);
      btCreateGameButton = new JButton("Create Game");
      btCreateGameButton.addActionListener(this);
      gbcGlobalChatPanel.gridx = 0;
      gbcGlobalChatPanel.gridy = 19;
      gbcGlobalChatPanel.gridwidth = 2;
      gbcGlobalChatPanel.gridheight = 1;
      gbcGlobalChatPanel.fill = GridBagConstraints.HORIZONTAL;
      gbcGlobalChatPanel.weightx = 0;
      gbcGlobalChatPanel.weighty = 0;
      gbcGlobalChatPanel.anchor = GridBagConstraints.NORTH;
      gbGlobalChatPanel.
    setConstraints(btCreateGameButton, gbcGlobalChatPanel);
      add(btCreateGameButton);
      btJoinGameButton = new JButton("Join Game");
      btJoinGameButton.addActionListener(this);
      gbcGlobalChatPanel.gridx = 0;
      gbcGlobalChatPanel.gridy = 21;
      gbcGlobalChatPanel.gridwidth = 2;
      gbcGlobalChatPanel.gridheight = 1;
      gbcGlobalChatPanel.fill = GridBagConstraints.HORIZONTAL;
      gbcGlobalChatPanel.weightx = 0;
      gbcGlobalChatPanel.weighty = 0;
      gbcGlobalChatPanel.anchor = GridBagConstraints.NORTH;
      gbGlobalChatPanel.
    setConstraints(btJoinGameButton, gbcGlobalChatPanel);
      add(btJoinGameButton);
      gbcGlobalChatPanel.gridx = 0;
      gbcGlobalChatPanel.gridy = 23;
      gbcGlobalChatPanel.gridwidth = 2;
      gbcGlobalChatPanel.gridheight = 1;
      gbcGlobalChatPanel.fill = GridBagConstraints.HORIZONTAL;
      gbcGlobalChatPanel.weightx = 0;
      gbcGlobalChatPanel.weighty = 0;
      gbcGlobalChatPanel.anchor = GridBagConstraints.NORTH;
      gbGlobalChatPanel.
    setConstraints(passwordField, gbcGlobalChatPanel);
      add(passwordField);
      String []sGameList = {};
      lsGameList = new JList(sGameList);
      JScrollPane jsp = new JScrollPane(lsGameList);
      gbcGlobalChatPanel.gridx = 1;
      gbcGlobalChatPanel.gridy = 1;
      gbcGlobalChatPanel.gridwidth = 1;
      gbcGlobalChatPanel.gridheight = 17;
      gbcGlobalChatPanel.fill = GridBagConstraints.VERTICAL;
      gbcGlobalChatPanel.weightx = 0;
      gbcGlobalChatPanel.weighty = 1;
      gbcGlobalChatPanel.anchor = GridBagConstraints.NORTH;
      gbGlobalChatPanel.setConstraints(lsGameList, gbcGlobalChatPanel);
      add(lsGameList);
      taChatArea = new JTextArea(2, 10);
      taChatArea.setEditable(false);
      taChatArea.setLineWrap(true);
      taChatArea.setWrapStyleWord(true);
      spChatPane = new JScrollPane(taChatArea);
      gbcGlobalChatPanel.gridx = 3;
      gbcGlobalChatPanel.gridy = 0;
      gbcGlobalChatPanel.gridwidth = 20;
      gbcGlobalChatPanel.gridheight = 20;
      gbcGlobalChatPanel.fill = GridBagConstraints.BOTH;
      gbcGlobalChatPanel.weightx = 1;
      gbcGlobalChatPanel.weighty = 1;
      gbcGlobalChatPanel.anchor = GridBagConstraints.NORTH;
      gbGlobalChatPanel.
    setConstraints(spChatPane, gbcGlobalChatPanel);
      add(spChatPane);
      setPreferredSize(new Dimension(580, 380));
   } 

   /**
    * The event handler for the buttons
    * @param e the ActionEvent
    */
   public void actionPerformed(ActionEvent e) {
      
     if (e.getSource() == btGameInfoButton) { //Get Game Info
       if (lsGameList.getSelectedValue() != null) {
     client.getGameInfo((String) lsGameList.getSelectedValue());
       }
     }
     //The following buttons have to be turned off
     //while in a game.
     if (!gcw.getInGame()) {
       if ((e.getSource() == btCreateGameButton) && !creating) { //Create Game
     //Pops up a dialog for setting parameters.
     gd = new GameOptionsDialog(gcw); 
     creating = true;
       }
       if (e.getSource() == btJoinGameButton) { //Join Game
     //check that one's selected.
     if (lsGameList.getSelectedValue() != null) {
       if (String.valueOf(passwordField.getPassword()).equals("")) {
         // System.out.println("NO PASSWORD IN FIELD");
         client.joinGame((String) lsGameList.getSelectedValue());
       } else {
         // System.out.println("PASSWORD FIELD NOT EMPTY");
         client.joinGame((String) lsGameList.getSelectedValue(),
                 String.valueOf(passwordField.getPassword()));
       }
     }
       }
     }
   }
  
  /**
   * kills the GameOptionsDialog
   */
  public void closeDialog() {
    if (gd != null) {
      gd.hide();
      gd.dispose();
      gd = null;
      creating = false;
    }
  }
  
  /**
   * updates the taPlayerList text area
   * @param userList the vector of users
   */ 
  public void updatePlayerList(Vector userList) {
    String newList = "";
    for (int i = 0; i < userList.size(); i++) {
      newList += ((String) userList.get(i)) + "\n";
    }
    taPlayerList.setText(newList);
  }

  /**
   * updates the lsGameList list of games
   * @param gameList the vector of game names
   */
  public void updateGameList(Vector gameList) {
    lsGameList.setListData(gameList);
  }

  /**
   * updates the chat window
   * @param name the name of the person who's sending a message
   * @param message the message they sent
   */
  public void updateChatWindow(String name, String message) {
    taChatArea.append(name + ":" + message + "\n");
    JScrollBar temp = spChatPane.getVerticalScrollBar();
    temp.setValue(temp.getMaximum());
  }
}









