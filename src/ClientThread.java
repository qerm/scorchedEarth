import java.net.Socket;
import java.io.BufferedReader;
import java.io.PrintWriter;
import java.io.IOException;
import java.io.InputStreamReader;

import java.util.StringTokenizer;
/**
 * ClientThread.java, the class that communicates between
 * This client and the server.  Sends outgoing messages, 
 * recieves incoming ones and dispatches them to the proper 
 * places.
 * @author Team QERM
 */
public class ClientThread extends Thread implements Protocol {
 
  /**
   * Name of the user.
   */
 private String userName;
  
  /**
   * The socket this uses as its connection
   */
  private Socket socket;

  /**
   * wraps the input sream of the socket
   */
  private BufferedReader br;
  
  /**
   * wraps the output stream of the socket
   */
  private PrintWriter pw;

  /**
   * the in-game window
   */
  private GameView gv;

  /**
   * the game chat window
   */
  private GameChatWindow gameChat = null;

  /**
   * the global chat window
   */
  private GlobalChatWindow globalChat;

  /**
   * The list of players
   */
  private String gamePlayerList;



  /**
   * Accesor for userName
   * @return userName
   */
  public String getUserName() {
    return userName;
  }

  /**
   * Mutator for userName
   * @param newName the new name value
   */
  public void setUserName(String newName) {
    userName = newName;
  }

  /**
   * Accesor for socket
   * @return socket
   */
  public Socket getSocket() {
    return socket;
  }

  /**
   * Mutator for socket
   * @param s the new Socket value
   */
  public void setSocket(Socket s) {
    socket = s;
  }

  /**
   * Accesor for gv
   * @return gv
   */
  public GameView getGv() {
    return gv;
  }

  /**
   * Mutator for gv
   * @param newGv the new value of gv
   */
  public void setGv(GameView newGv) {
    gv = newGv;
  }

  /**
   * Accessor for gameChat
   * @return gameChat
   */
  public GameChatWindow getGameChat() {
    return gameChat;
  }

  /**
   * Mutator for gameChat
   * @param newGameChat the new value of gameChat
   */
  public void setGameChat(GameChatWindow newGameChat) {
    gameChat = newGameChat;
  }

 /**
   * Accessor for globalChat
   * @return globalChat
   */
  public GlobalChatWindow getGlobalChat() {
    return globalChat;
  }

  /**
   * Mutator for globalChat
   * @param newGlobalChat the new value of globalChat
   */
  public void setGlobalChat(GlobalChatWindow newGlobalChat) {
    globalChat = newGlobalChat;
  }

  /**
   * Accessor for gamePlayerList
   * @return gamePlayerList
   */
  public String getGamePlayerList() {
    return gamePlayerList;
  }

  /**
   * Mutator for gamePlayerList
   * @param newPL the new value for gamePlayerList
   */
  public void setGamePlayerList(String newPL) {
    gamePlayerList = newPL;
  }

  /**
   * default constructor for ClientThread. for JUniting ONLY.
   */
  public ClientThread() {
    this(null, null);
  }

  /**
   * constructor that takes in a username
   * @param newSocket the Socket to use
   * @param newGcw pointer to the global chat window
   */
  public ClientThread(Socket newSocket, GlobalChatWindow newGcw) {
    socket = newSocket;
    globalChat = newGcw;
  }
    
  /**
   * The thread method.
   */
  public void run() {
    String line;
    try {
      br = new BufferedReader(new 
    InputStreamReader(socket.getInputStream()));
      pw = new PrintWriter(socket.getOutputStream());
      line = br.readLine();
      while (line != null) {
    parseCommand(line);
    line = br.readLine();
      }
      globalChat.killIt();
    } catch (IOException ioe) {
      /* not good */
    }
  }
   
  /**
   * The main method of the ClientThread.  It interprets
   * The input stream and calls the appropriate method, depending
   * on the command sent.
   * @param line the String just read from the InputStream
   */
  private void parseCommand(String line) {
    StringTokenizer st = new StringTokenizer(line, DELIM);
    String command = st.nextToken();
    if (command.equals(ACCEPT_CONNECT)) {
      globalChat.promptLogin();
    } else if (command.equals(NAME_INVALID)) {
      globalChat.loginError(st.nextToken());
    } else if (command.equals(PLAYER_LIST)) {
      updateUserList(line);
    } else if (command.equals(GAME_LIST)) {
      updateGameList(line);
    } else if (command.equals(MAIN_CHAT)) {
      globalChatRecieve(line);
    } else if (command.equals(GAME_INFO)) {
      GameParameters gParam = new GameParameters();
      gParam.parseFormattedString(st);
      globalChat.setGParam(gParam);
      globalChat.displayGParam();
    } else if (command.equals(DENY_JOIN)) {
      globalChat.gameError(st.nextToken());
    } else if (command.equals(ACCEPT_JOIN)) {
      GameParameters gParam = new GameParameters();
      gParam.parseFormattedString(st);
      globalChat.setGParam(gParam);
      globalChat.lobbyTime(false);
    } else if (command.equals(ACCEPT_CREATE)) {
      GameParameters gParam = new GameParameters();
      gParam.parseFormattedString(st);
      globalChat.setGParam(gParam);
      globalChat.lobbyTime(true);
    } else if (command.equals(DENY_CREATE)) {
      globalChat.gameError(st.nextToken());
    } else if (command.equals(GAME_CHAT)) {
      gameChatRecieve(line);
    } else if (command.equals(GAME_PLAYERS)) {
      updatePlayerList(line);
    } else if (command.equals(START_GAME)) {
      gameChat.flipOutAndDoSomethingTotallyAwesome();
    } else if (command.equals(END_GAME)) {
      if (gameChat != null) {
      gameChat.logoutUser("Game is Over!!");
      }
    } else if (command.equals(PUBLIC_INVENTORY)) {
      updatePublicInventory(line);
    } else if (command.equals(PROMPT_MOVE)) {
      turnPrompt();
    } else if (command.equals(TAUNT)) {
        System.out.println("TAUNT recognized");
      doTaunt(st);
    } else if (command.equals(FIRE)) {
      updateFire(line);
    } else if (command.equals(MOVE_TURRET)) {
      updateTurret(line);
    } else if (command.equals(LEAVE_GAME)) {
        System.out.println("LEAVE GAME");
      doLeaveGame(st);
    } else if (command.equals(DO_ROUND)) {
      if (gv != null) {
    gv.doRound();
      }
    }
  }
  
  /**
   * sends the command on to the GlobalChatWindow
   * @param message the String just read from the InputStream
   */
  private void globalChatRecieve(String message) {
    globalChat.updateChatWindow(message);
  }

  /**
   * sends the command on to the GameChatwindow
   * @param message the String just read from the InputSteam
   */
  private void gameChatRecieve(String message) {
    gameChat.updateChatWindow(message);
  }

  /**
   * sends the command on to GlobalChatWindow
   * @param list the new list of Games
   */
  private void updateGameList(String list) {
    globalChat.updateGameList(list);
  }

  /**
   * sends the command on to the GlobalChatWindow
   * @param list the new list of users logged on
   */
  private void updateUserList(String list) {
    globalChat.updateUserList(list);
  }

  /**
   * sends the command on to the GameChatWindow
   * @param list the new list of players joined in this game
   */
  private void updatePlayerList(String list) {
    if (gameChat != null) {
      gameChat.updatePlayerList(list);
    } else {
      gamePlayerList = list;
    }
  }

  /**
   * handles a recieved MOVE_TURRET command
   * @param message the command string
   */
  private void updateTurret(String message) {
    gv.animateTurret(message);
  }

  /**
   * handles a USER_FIRED command
   * @param message the command string
   */
  private void updateFire(String message) {
    gv.fire(message);
  }

  /**
   * handles a PUBLIC_INVENTORY command
   * @param message the command string
   */
  private void updatePublicInventory(String message) {
    gv.updatePublicInventory(message);
  }

  /**
   * handles a PROMPT_MOVE command
   */
  private void turnPrompt() {
    gv.doTurn();
  }

  /**
   * Sends a chat that can only be read by other people
   * in the game. This function should only be called from
   * components in GameChatWindow
   * @param message the message to send, UNFORMATTED.
   */
  public void gameChatSend(String message) {
    pw.println(GAME_CHAT + DELIM + userName + DELIM + message);
    pw.flush();
  }
  
  /**
   * Sends a chat into the global chat room. 
   * This function should only be called from
   * components in GlobalChatWindow.
   * @param message the message to send, UNFORMATTED.
   */
  public void globalChatSend(String message) {
    pw.println(MAIN_CHAT + DELIM + userName + DELIM + message);
    pw.flush();
  }

  /**
   * Sends a CREATE_GAME request across the server, with the
   * given game parameters
   * @param gParam the GameParameters object of the game to create
   */
  public void createGame(GameParameters gParam) {
    pw.println(CREATE_GAME + DELIM 
           + userName + DELIM + gParam.getFormattedString());
    pw.flush();
  }

  /**
   * Sends a JOIN_GAME request across the server, with the given
   * game name
   * @param gameName the game name
   */
  public void joinGame(String gameName) {
    pw.println(JOIN_GAME + DELIM + userName + DELIM + gameName);
    pw.flush();
  }

  /**
   * Sends a JOIN_GAME request across the server, with the given
   * game name
   * @param gameName the game name
   * @param password the password to the game
   */
  public void joinGame(String gameName, String password) {
    pw.println(JOIN_GAME + DELIM + userName + DELIM + gameName + DELIM
         + password);
    pw.flush();
  }

  /**
   * sends a GAME_INFO request across the server
   * @param gameName the game name
   */
  public void getGameInfo(String gameName) {
    pw.println(GAME_INFO + DELIM + userName + DELIM + gameName);
    pw.flush();
  }

  /**
   * sends a LEAVE_GAME command to the server.  should 
   * only be called by components within GameChatWindow
   */
  public void leaveGame() {
    pw.println(LEAVE_GAME + DELIM + userName);
    pw.flush();
    globalChat.setInGame(false);
    gameChat = null;
    gv = null;
    gamePlayerList = null;
  }

  /**
   * sends a start game command.  should only be called
   * if this client is the host, or is a h4x0r.
   */
  public void startGame() {
    pw.println(START_GAME);
    pw.flush();
  }

  /**
   * sends an existing user's login info to the server.
   * @param userName the user's name
   * @param password the user's password
   */
  public void sendLoginInfo(String userName, String password) {
    pw.println(USER_NAME + DELIM + userName + DELIM + password);
    pw.flush();
    this.userName = userName;
  }

  /**
   * sends a new user's login info to the server.
   * @param userName the user's name
   * @param password the user's password
   */
  public void sendNewLoginInfo(String userName, String password) {
    pw.println(NEW_USER_NAME + DELIM + userName + DELIM + password);
    pw.flush();
    this.userName = userName;
  }

  /**
   * sends a color selction request across the server
   * @param color the color the client is requesting
   */
  public void requestColor(int color) {
    pw.println(CHOOSE_COLOR + DELIM + userName + DELIM + color);
    pw.flush();
  }
  
  /**
   * sends this user's public inventory across the server
   * @param invt the formatted string of the person's public
   * inventory
   */
  public void sendPublicInventory(String invt) {
    pw.println(PUBLIC_INVENTORY + DELIM + userName + DELIM + invt);
    pw.flush();
  }

  /**
   * sends the done command
   */
  public void sendDone() {
    pw.println(DONE + DELIM + userName);
    pw.flush();
  }




  /**
   * sends a taunt
   * @param sound int
   */
  public void sendTaunt(int sound) {
    pw.println(TAUNT + DELIM + sound);
    pw.flush();
  }




  /**
   * sends the PLAYER_DIED across the server
   * @param killer the name of the user that killed this client
   */
  public void sendDead(String killer) {
    pw.println(PLAYER_DIED + DELIM + userName + DELIM + killer);
    pw.flush();
  }

  /**
   * sends the FIRE command
   * @param power the power
   * @param angle the angle
   * @param type the type of shot fired
   */
  public void sendFire(int power, int angle, int type) {
    pw.println(FIRE + DELIM + userName + DELIM + angle
            + DELIM + power + DELIM + type);
    pw.flush();
  }

  /**
   * sends the MOVE_TURRET command
   * @param angle the turret's at right now
   */
  public void sendTurretMove(int angle) {
    pw.println(MOVE_TURRET + DELIM + userName + DELIM + angle);
    pw.flush();
  }

  /**
   * Sends out a END_GAME command
   */
  public void endGame() {
    pw.println(END_GAME);
    pw.flush();
  }

  /**
   * calls the gameView with a TAUNT message
   * @param st the tokenized command line
   */
  private void doTaunt(StringTokenizer st) {
    if (gv != null) {
      try {
    int i = Integer.parseInt(st.nextToken());
    System.out.println("TAUNT recognized and made into a int " + i);
    gv.doTaunt(i);
      } catch (Exception e) {
        int i = 0;
      }
    }
  }


  /**
   * removes users who suddenly go away.
   * @param st the tokenized command line
   */
  private void doLeaveGame(StringTokenizer st) {
    if (gv != null) {
       gv.userLeft(st.nextToken());
    }
  }


  /**
   * Sent when the user leaves the entire
   * server
   */
  public void disconnect() {
    pw.println(DISCONNECT);
    pw.flush();
  }
}






