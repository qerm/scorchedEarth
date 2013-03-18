import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JMenuBar;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;

import java.awt.Container;
import java.awt.GridLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Dimension;

import java.net.Socket;
import java.net.UnknownHostException;
import java.io.IOException;
//import java.lang.SecurityException;

import java.util.StringTokenizer;
import java.util.Vector;
/**
 * GlobalChatWindow.java, the main window of the 
 * game (i.e. the game is over when this one closes).
 * It will display all login screens, and the main
 * chat area.
 * @author Team QERM
 */
public class GlobalChatWindow implements Protocol, 
                     Constants, ActionListener {

  /**
   * the window
   */
  private JFrame theFrame = null;
  
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
   * a GameChatWindow
   */
  private GameChatWindow gcw;
  
  /**
   * Get the Gcw value.
   * @return the Gcw value.
   */
  public GameChatWindow getGcw() {
    return gcw;
  }

  /**
   * Set the Gcw value.
   * @param newGcw The new Gcw value.
   */
  public void setGcw(GameChatWindow newGcw) {
    this.gcw = newGcw;
  }

  /**
   * the client
   */
  private ClientThread client = null;
  
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
   * reference that will hold theFrame's content pane
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
   * the current panel being displayed by GlobalChatWindow
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
   * a reference to the last game info the user requested
   */
  private GameParameters gParam;
  
  /**
   * Get the GParam value.
   * @return the GParam value.
   */
  public GameParameters getGParam() {
    return gParam;
  }

  /**
   * Set the GParam value.
   * @param newGParam The new GParam value.
   */
  public void setGParam(GameParameters newGParam) {
    this.gParam = newGParam;
  }

  /**
   * a state variable for whether the user
   * is logged in or not
   */
  private boolean loggedIn = false;
  
  /**
   * Get the LoggedIn value.
   * @return the LoggedIn value.
   */
  public boolean isLoggedIn() {
    return loggedIn;
  }

  /**
   * Set the LoggedIn value.
   * @param newLoggedIn The new LoggedIn value.
   */
  public void setLoggedIn(boolean newLoggedIn) {
    this.loggedIn = newLoggedIn;
  }

  /**
   * a state variable for whether the user
   * is joined into a game or not
   */
  private boolean inGame = false;
  
  /**
   * Get the InGame value.
   * @return the InGame value.
   */
  public boolean getInGame() {
    return inGame;
  }

  /**
   * Set the InGame value.
   * @param newInGame The new InGame value.
   */
  public void setInGame(boolean newInGame) {
    this.inGame = newInGame;
  }

  /**
   * Constructor that doesn't do anything so this class can junit
   * @param bool Boolean
   */
  public GlobalChatWindow(boolean bool) {
    //Used for junit
    setInGame(bool);
  }
  

  /**
   * constructor for GlobalChatWindow
   */
  public GlobalChatWindow() {
    init();
    promptConnect();
  }
 
  /**
   * Initializes all the JComponents
   */
  public void init() {
    theFrame = new JFrame("Scorched Earth");
    c = theFrame.getContentPane();
    c.setLayout(new GridLayout(1, 1));
    theFrame.setResizable(true); /* FIX: set to false when ready */
    theFrame.addWindowListener(new WindowAdapter() {
    public void windowClosing(WindowEvent e) {
      theFrame.setVisible(false);
      theFrame.dispose();
      if (client != null) {
        if (inGame) {
          client.leaveGame();
        }
        client.disconnect();
      }
      System.exit(1);
    }});
    currentPanel = new JPanel();
    c.add(currentPanel);
    

    JMenuBar menuBar = new JMenuBar();
    JMenu helpMenu = new JMenu("Help");
    JMenuItem help = new JMenuItem("Help");
    JMenuItem about = new JMenuItem("About");

    help.addActionListener(this);
    about.addActionListener(this);
    
    helpMenu.add(help);
    helpMenu.add(about);
    menuBar.add(helpMenu);
    
    theFrame.setJMenuBar(menuBar); 

    

    theFrame.setSize(new Dimension(175, 125));
    theFrame.pack();
    theFrame.setResizable(false);
    theFrame.setVisible(true);
  }
                
  /**
   * flips in the PromptPanel pane, and waits for a connect request
   */ 
  public void promptConnect() {
    JPanel temp = new PromptPanel(this);
    /* theFrame.setSize(temp.getPreferredSize()); */
    c.invalidate();
    c.remove(currentPanel);
    currentPanel = temp;
    c.add(currentPanel);
    c.validate();
    theFrame.pack();
  }

  /**
   * creates a Socket with the given ip and port,
   * then wraps a ClientThread around it if successful
   * @param ip the server's ip
   * @param port the server's port #
   */  
  public void connect(String ip, int port) {
    System.out.println("connecting to " + ip + ":" + port);
    try {
      Socket s = new Socket(ip, port);
      client = new ClientThread(s, this);
      client.start();
    } catch (UnknownHostException uhe) {
      this.loginError("Invalid Server name!");
    } catch (IOException ioe) {
      this.loginError("Error opening connection\n" 
              + "restart and try again");
    } catch (SecurityException se) {
      this.loginError("You are not allowed to connect\n" 
             + "to this server!");
    }
  }
  
  /**
   * called by ClientThread after a SERVER_ACCEPT.
   * flips the panel to a LoginPanel.
   */
  public void promptLogin() {
    JPanel temp = new LoginPanel(client, this);
    theFrame.setSize(temp.getPreferredSize());
    c.invalidate();
    c.remove(currentPanel);
    currentPanel = temp;
    c.add(currentPanel);
    c.validate();
    theFrame.pack();
  }    
  

  /**
   * displays the error in a popup window
   * @param errCode the error message to display
   */
    public void loginError(String errCode) {
      JOptionPane.
    showMessageDialog(theFrame,
              errCode,
              "Login Error",
              JOptionPane.ERROR_MESSAGE);
      /* System.out.println(errCode); */
    }


  
  /**
   * updates the game list
   * @param list the command recieved from server.
   */
  public void updateGameList(String list) {
    StringTokenizer st = new StringTokenizer(list, DELIM);
    String temp = st.nextToken(); //throw away first token,it's for protocol
    Vector v = new Vector();
    while (st.hasMoreTokens()) {
      v.add(st.nextToken());
    }
    GlobalChatPanel g = (GlobalChatPanel) currentPanel;
    g.updateGameList(v);
  }

  /**
   * updates the users logged on list
   * @param list the command recieved from server.
   */
  public void updateUserList(String list) {
    if (!loggedIn) {      //if this is the first call to
      studMuffinButton(); //updateUserList, the GlobalChatPanel
      loggedIn = true;    //must be flipped in.
    }
    StringTokenizer st = new StringTokenizer(list, DELIM);
    String temp = st.nextToken(); //throw away the first, it's the command
    Vector v = new Vector();
    while (st.hasMoreTokens()) {
      v.add(st.nextToken());
    }
    GlobalChatPanel g = (GlobalChatPanel) currentPanel;
    g.updatePlayerList(v);
  }

  
  /**
   * flips out the LobbyPanel and flips in
   * a GlobalChatPanel
   */
  public void studMuffinButton() {
    JPanel temp = new GlobalChatPanel(client, this);
    theFrame.setSize(temp.getPreferredSize());
    c.invalidate();
    c.remove(currentPanel);
    currentPanel = temp;
    c.add(currentPanel);
    c.validate();    
    theFrame.pack();
  }

  /**
   * updates the chat field
   * @param chat the command recieved from the server.
   */
  public void updateChatWindow(String chat) {
    StringTokenizer st = new StringTokenizer(chat, DELIM);
    String temp = st.nextToken();
    String name = st.nextToken();
    String message = st.nextToken();
    GlobalChatPanel g = (GlobalChatPanel) currentPanel;
    g.updateChatWindow(name, message);
  }

  /**
   * displays a pop-up window of the error
   * @param errorCode String
   */
  public void gameError(String errorCode) {
    JOptionPane.
      showMessageDialog(theFrame,
            errorCode,
            "Join/Create Error",
            JOptionPane.ERROR_MESSAGE);
    /* System.out.println(errorCode); */
  }

  /**
   * called when the user clicks create in
   * the GameOptionsDialog
   * @param gameParam GameParameters
   */
  public void createGame(GameParameters gameParam) {
    client.createGame(gameParam);
  }



  /**
   * displays the current gParam in a window
   * called by ClientThread after recieving a GAME_INFO
   * command from the server
   */
  public void displayGParam() {
    JOptionPane.
      showMessageDialog(theFrame, 
            gParam,
            "Game Info",
            JOptionPane.INFORMATION_MESSAGE);
  }
  
  /**
   * changes the state vars, and 
   * opens up a GameChatWindow
   * @param isHost true if this Client is the host
   */
  public void lobbyTime(boolean isHost) {
    inGame = true;
    GlobalChatPanel g = (GlobalChatPanel) currentPanel;
    g.closeDialog();
    gcw = new GameChatWindow(client, isHost, this);
  }

  /**
   * tester main
   * @param argv IGNORED
   */
  public static void main(String argv[]) {
    GlobalChatWindow test = new GlobalChatWindow();
  }



  /**
   * handles menu action events
   * @param ae ActionEvent
   */
  public void actionPerformed(ActionEvent ae) {
   

        if (ae.getActionCommand().equals("About")) {
            JTextArea ta = new JTextArea();
            ta.setText("Operation Scorched Earth Freedom\n"
            + "Written by:    Team QERM\n\n\n"
            + "Micheal Quiggle\n"
            + "John Etherton\n"
            + "Wesley Reynolds\n"
            + "David McCann\n\n\n\n"
            + "We'd like to thank the following: \n"
            + "Kroger, Quiggle's X and David's X who let "
            + "them go so they could program more, Bunker 1087 "
            + "HomeDepot, Salvation Army for the clothes we "
            + "wore at the world premier, Wes for introducing "
            + "us to the word man crush, all those kids "
            + "in the front row who inspire us to strive for bigger "
            + "and better, you know who you are\n\n\n"
            + "Wes would like to thank Diet Coke, however since "
            + "the rest of us are hetrosexual we do not share "
            + "Wes's feelings on this subject. Instead we'd like "
            + "to thank Citrus Drop"
            + "\n\n\nand Doom\n\n and of course we'd like to say a"
            + "special thanks to Wendell Hicken");
            ta.setEditable(false);
            ta.setColumns(40);
            ta.setLineWrap(true);
            ta.setWrapStyleWord(true);
            JScrollPane jsp = new JScrollPane(ta);
            JOptionPane.showMessageDialog(theFrame, jsp);
            
        } else if (ae.getActionCommand().equals("Help")) {
            JTextArea ta = new JTextArea();
            ta.setText("A little about the game. \n"
            + "There are three major components to "
            + "this game. The global chat window, which allows you to talk "
            + "with every person on the server, as well as browse through "
            + "different games that are available. The 2nd major component "
            + "is the Game chat window, which is where you'll pick your "
            + "colors once you've joined a game and haven't started play yet "
            + "as well as talk directly with your opponents in the game. "
            + "Finally and most importantly is the game view, which is "
            + "where you'll play the game and buy and sell weapons in the "
            + "shop.\n\n"
            + "To start a game you'll need to logon onto the server. "
            + "If you're a first time user you'll want to create a new "
            + "account. Otherwise logon as normal. Once you're in the "
            + "global chat window you can click create game and then "
            + "specify the options you'd like to use for your game. Once "
            + "you've started the game you'll be in the lobby. This "
            + "is where you and other players will pick their colors and "
            + "talk before the game is started. Only the person who "
            + "started the game can start the game and if the game "
            + "creator leaves a game that is in the lobby the game will "
            + "end. To join a game, find a game from the list of games "
            + "and click join. If the game requires a password you'll "
            + "need to enter it into the password field at the bottom "
            + "left of the global chat window.\n\n"
            + "Once the game has stated you'll go to the shop and buy "
            + "the any weapons you'll need. You'll have an unlimited "
            + "number of baby missiles to use. I'd like to take this time "
            + "to point out that tanks don't shoot missiles. They shoot "
            + "shells. I have never understood why both the original "
            + "Scorched Earth and our wonderful teaching staff hasn't "
            + "corrected this blunder, but anyway... You can also buy "
            + "defensive shields and weapons but more about that latter. "
            + "You can also sell back weapons for a slightly less money "
            + "than you purchased them for. In this release of the game "
            + "we have 6 different weapons:\n\n"
            + "Baby Missiles are the weakest of all ammo and you have "
            + "an unlimited supply.\n\n"
            + "Missiles are more powerful than Baby "
            + "Missiles but cost more\n\n"
            + "Nukes are the most powerful of the traditional parabolic "
            + "trajectory based weaponry\n\n"
            + "MIRVs function like a missile except that at the apex "
            + "of their flight they break into 5 sub shots each with "
            + "the power of a missile.\n\n"
            + "Spiders function like a MIRV but the trajectory of the "
            + "sub shots is different.\n\n"
            + "Tomahawks function much like the super sweet weapon "
            + "of the same name developed by the U.S. military. They "
            + "will fly slightly above the surface of the ground until "
            + "they come across a tank and then detonate.\n\n"
            + "Once your in actual game play you'll notice the buttons "
            + "that allow you to move your turret and adjust your power. "
            + "As well as the drop down menu to select your weapons. "
            + "You'll be prompted to shoot by the status bar in the top "
            + "left corner. And the rest you should be able to figure "
            + "every thing else out on your own. Cya.");
            ta.setEditable(false);
            ta.setColumns(40);
            ta.setLineWrap(true);
            ta.setWrapStyleWord(true);
            JScrollPane jsp = new JScrollPane(ta);
            JOptionPane.showMessageDialog(theFrame, jsp);
        }
            
  }

  /**
   * closes everything, called by client if it gets a null
   */
  public void killIt() {
    theFrame.setVisible(false);
    theFrame.dispose();
    if (client != null) {
      if (inGame) {
    client.leaveGame();
      }
      client.disconnect();
    }
    System.exit(1);
  }

}









