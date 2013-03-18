import javax.swing.JFrame;
import javax.swing.JPanel;
//import javax.swing.JButton;
import java.awt.Container;
import java.awt.GridLayout;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
//import javax.swing.JOptionPane;
import java.util.Vector;
import java.util.StringTokenizer;

/**
 * GameChatWindow.java, view for the lobby and game chat.
 * opens upon join to a game
 * @author Team QERM
 */
public class GameChatWindow implements Protocol,
                       Constants {
  /**
   * The frame for the game chat window.
   * At any given time it could be displaying 
   * the Lobby or GameChat Panels.
   */
  private JFrame theFrame;

  /**
   * Get the TheFrame value.
   * @return the TheFrame value.
   */
  public JFrame getTheFrame() {
    return theFrame;
  }

  /**
   * Set the TheFrame value.
   * @param newTheFrame The new TheFrame value.
   */
  public void setTheFrame(JFrame newTheFrame) {
    this.theFrame = newTheFrame;
  }

  /**
   * The current Panel theFrame is displaying
   */
  private JPanel currentPanel;

  /**
   * Get the CurrentPanel value.
   * @return the CurrentPanel value.
   */
  public JPanel getCurrentPanel() {
    return currentPanel;
  }

  /**
   * Set the CurrentPanel value.
   * @param newCurrentPanel The new CurrentPanel value.
   */
  public void setCurrentPanel(JPanel newCurrentPanel) {
    this.currentPanel = newCurrentPanel;
  }

  /**
   * the Content pane of theFrame
   */
  private Container c;

  /**
   * Get the C value.
   * @return the C value.
   */
  public Container getC() {
    return c;
  }

  /**
   * Set the C value.
   * @param newC The new C value.
   */
  public void setC(Container newC) {
    this.c = newC;
  }

  /**
   * A vector of Tanks
   */
  private Vector playerList;

  /**
   * Get the PlayerList value.
   * @return the PlayerList value.
   */
  public Vector getPlayerList() {
    return playerList;
  }

  /**
   * Set the PlayerList value.
   * @param newPlayerList The new PlayerList value.
   */
  public void setPlayerList(Vector newPlayerList) {
    this.playerList = newPlayerList;
  }

  /**
   * self-explanatory
   */
  private boolean gameStarted;

  /**
   * Get the GameStarted value.
   * @return the GameStarted value.
   */
  public boolean isGameStarted() {
    return gameStarted;
  }

  /**
   * Set the GameStarted value.
   * @param newGameStarted The new GameStarted value.
   */
  public void setGameStarted(boolean newGameStarted) {
    this.gameStarted = newGameStarted;
  }

  /**
   * The ClientThread
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
   * self-explanatory
   */
  private boolean isHost;

  /**
   * Get the IsHost value.
   * @return the IsHost value.
   */
  public boolean isIsHost() {
    return isHost;
  }

  /**
   * Set the IsHost value.
   * @param newIsHost The new IsHost value.
   */
  public void setIsHost(boolean newIsHost) {
    this.isHost = newIsHost;
  }

  /**
   * the GameView window, that displays
   * Shop and InGame Panels
   */
  private GameView gameView;

  /**
   * Get the GameView value.
   * @return the GameView value.
   */
  public GameView getGameView() {
    return gameView;
  }

  /**
   * Set the GameView value.
   * @param newGameView The new GameView value.
   */
  public void setGameView(GameView newGameView) {
    this.gameView = newGameView;
  }

  /**
   * The GlobalChatWindow, which displays
   * the Prompt, Login, and GlobalChat Panels
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
   * default contructor. Juniting ONLY
   */
  public GameChatWindow () {
    //    this(null, false, null);
  }
  
  /**
   * Main contructor
   * @param newClient the client thread
   * @param newIsHost the new isHost value
   * @param newGcw the GlobalChatWindow
   */
  public GameChatWindow(ClientThread newClient, 
            boolean newIsHost,
            GlobalChatWindow newGcw) {
    client = newClient;
    client.setGameChat(this);
    isHost = newIsHost;
    gcw = newGcw;
    init();
  }
  
  /**
   * Initializes everything
   */
  private void init() {
    playerList = new Vector();
    theFrame = new JFrame("Lobby");
    c = theFrame.getContentPane();
    c.setLayout(new GridLayout(1, 1));
    currentPanel = new LobbyPanel(client, isHost);
    c.add(currentPanel);
    theFrame.repaint();
    theFrame.addWindowListener(new WindowAdapter() {
    public void windowClosing(WindowEvent e) {
      logoutUser();
    
    }
      });
    
    theFrame.pack();      
    theFrame.setSize(currentPanel.getPreferredSize());
    theFrame.setResizable(false);
    theFrame.setVisible(true);

    client.setGameChat(this);
    if (client.getGamePlayerList() != null) {
      updatePlayerList(client.getGamePlayerList());
    }

  }

  /**
   * called when the game is starting
   */
  public void flipOutAndDoSomethingTotallyAwesome() {
    theFrame.setTitle("Game Chat");
    LobbyPanel lp = (LobbyPanel) currentPanel;
    c.remove(currentPanel); 
    c.invalidate();                  
    currentPanel = lp.getGcp();
    c.add(currentPanel);             
    c.validate();                   
    gameStarted = true;
    gameView = new GameView(client, this, gcw.getGParam());  
 
  }

  /**
   * calls cleints endGame method
   */
  public void endGame() {
    client.endGame();
  }

  /**
   * calls the clients leaveGame() method
   */
  public void logoutUser() {

    //This is a total hack.  This one is 
    //called if this window is closing,
    //in which case gameView needs to close itself
    //leavingGameView is called by GameView, when
    //its own window is being closed.
    if (gameView != null) {
      gameView.logoutUser();
    } else {
      leavingGameView();
    }
  }
  


  /**
   * calls the clients leaveGame() method
   *
   * @param reason String the reason your getting booted
   */
  public void logoutUser(String reason) {

    //This is a total hack.  This one is 
    //called if this window is closing,
    //in which case gameView needs to close itself
    //leavingGameView is called by GameView, when
    //its own window is being closed.
    if (gameView != null) {
      gameView.logoutUser(reason);
    } else {
      leavingGameView();
    }
  }






  /**
   * called by GameView when itw
   * window is closed
   */
  public void leavingGameView() {
    theFrame.setVisible(false);
    theFrame.dispose();
    client.leaveGame();
  }
  
  /**
   * Updates the player list.
   *
   * @param newList the DELIM-delimited string of players and colors.
   */
  public void updatePlayerList(String newList) {
    System.out.println("updatePlayerList"); int i = 1;
    StringTokenizer st = new StringTokenizer(newList, DELIM);
    String temp = st.nextToken(); //get rid of first token, this is
    Tank t;
    playerList.removeAllElements();
    while (st.hasMoreTokens()) {
      String name = st.nextToken();
      String colorString = st.nextToken();
      System.out.println("Tank " + i + ":" + name);
      int color;
      try {
    color = Integer.parseInt(colorString);
      } catch (NumberFormatException nfe) {
    color = PINK;
      }
      //either it's your tank
      if (name.equals(client.getUserName())) {
    System.out.println("local tank");
    t = new LocalTank(name, color);
      } else {
    System.out.println("remote tank");
    t = new RemoteTank(name, color);
      }
      i++;
      playerList.add(t);
    }
    if (!gameStarted) {
      LobbyPanel l = (LobbyPanel) currentPanel;
      theFrame.invalidate();
      l.updatePlayerList(playerList);
      theFrame.setSize(l.getPreferredSize());
      theFrame.validate();
    }
    
  }

  /**
   * Updates the chat field
   * @param newChat the String sent from the server
   */
  public void updateChatWindow(String newChat) {
    StringTokenizer st = new StringTokenizer(newChat, DELIM);
    String temp = st.nextToken(); //throw away command
    String name = st.nextToken();
    String message = st.nextToken();
    if (!gameStarted) {
      LobbyPanel lp = (LobbyPanel) currentPanel;
      lp.updateChatWindow(name, message); 
    } else {
      GameChatPanel gc = (GameChatPanel) currentPanel;
      gc.updateChatWindow(name, message); 
    }
  }
}

  
  
  





