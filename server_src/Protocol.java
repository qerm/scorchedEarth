/**
 * Protocol.java, a class that holds all the constants for
 * scorched earth's protocol, as well as information about 
 * their formats. Any networking classes should implement this class
 *
 * @author John Etherton, David McCann, Michael Quiggle, and Wesley Reynolds 
 */

public interface Protocol {
  
  /**
   * This is the delimiter for all protocol messages.
   * It should be the separator between any tokens in a message,
   * It should also be something that wouldn't be in any token
   */
  public static final String DELIM = "|";
  ////////////////////////////CLIENT COMMANDS///////////////////////////////
  
  /*
   * Client
   * ------
   * I. Out of Game
   * -Disconnect
   * -Send Global chat
   * -Create GAme
   * -Join Game
   * -Send User Name
   * -Send new user name
   * -Request GAme Info
   * II. Lobby Messages
   * -Choose color
   * -Leave Lobby
   * -Send Chat
   * -Start Game
   * -end Game
   * III. In game
   * -Fire
   * -Leave GAme
   * -Send GAme Chat
   * -Showed Result
   * -move turret
   * -public inventory info
   */
  
  //////Out of Game Commands//////
  
  /**
   * After a connection is established (server sends ACCEPT_CONNECT),
   * the first thing the client will need to do is send its user name.  
   * The format for this command is USER_NAME + DELIM + UserName + DELIM 
   * + password.  
   * Server can either send a NAME_INVALID command or PLAYER_LIST if
   * the name is accepted (followed by a GAME_LIST).
   */
  public static final String USER_NAME = "USER_NAME";

  /**
   * Same as user name, except for creating new users
   * Format is the same as well.
   */
  public static final String NEW_USER_NAME = "NEW_USER_NAME";

  /**
   * A player may want to host games.  It will send a CREATE_GAME
   * command, which the server will DENY_GAME or ACCEPT_GAME
   * The format for this command is CREATE_GAME + DELIM + username +
   * DELIM + gameName + DELIM + windBehavior + DELIM + startingMoney 
   * + DELIM + interestRate + DELIM + wallBehavior + DELIM 
   * + caves + DELIM + complexity + DELIM + roughness + DELIM + 
   * gravity + DELIM + numMaxUsers + DELIM + private + DELIM +
   * password.
   */
  public static final String CREATE_GAME = "CREATE_GAME";

  /**
   * To join a game, the user sends a JOIN_GAME command which the
   * server will respond to with DENY_JOIN or GAME_PLAYERS. The format
   * is JOIN_GAME + DELIM + username + DELIM + GameName + 
   * DELIM + password
   */
  public static final String JOIN_GAME = "JOIN_GAME";

  /**
   * Client sends a global chat message, which is echoed to all
   * clients that are logged on.
   * The format is MAIN_CHAT + DELIM + username + DELIM + message.
   */
  public static final String MAIN_CHAT = "MAIN_CHAT";

  /**
   * When a user clicks a game, it will automatically request the 
   * info for that game.  The server will send a GAME_INFO command.
   * The format is GAME_INFO + DELIM + username + DELIM + GAME_NAME
   */
  public static final String GAME_INFO = "GAME_INFO";

  /**
   * When a client exits cleanly, it will send a DISCONNECT command
   * to the server, with no other tokens.  The server will then have to
   * send a new PLAYER_LIST command to the remaining clients
   */
  public static final String DISCONNECT = "DISCONNECT";

  //////Lobby Commands//////

  /**
   * Client sends a lobby/game chat message, which echoed 
   * to all other clients in this GameMaster. 
   * Format is GAME_CHAT + DELIM + username + DELIM + Message.
   */
  public static final String GAME_CHAT = "GAME_CHAT";

  /**
   * Used within the lobby, chooses the color for the tank.
   * Acknowledged by the server with a LOBBY_LIST of all
   * users and colors. Format is CHOOSE_COLOR + DELIM + userName 
   * + DELIM + ColorNum
   */
  public static final String CHOOSE_COLOR = "CHOOSE_COLOR";

  /**
   * Used by the game host. It is used by itself with no other tokens,
   * and is echoed by a START_GAME command from the server
   */
  public static final String START_GAME = "START_GAME";

  /**
   * Used by the game host. It is used by itself with no other tokens,
   * And the entire GameMaster will be removed.
   */
  public static final String END_GAME = "END_GAME";

  //////In Game Commands//////

  /**
   * Final command in a series of input commands from a client, 
   * after it has been sent a PROMPT_MOVE command from the server
   * format is FIRE + DELIM + username + DELIM + angle + DELIM + 
   * power + DELIM + type. this command will be echoed to all
   * clients in the current GameMaster
   */
  public static final String FIRE = "FIRE";
  
  
  /**
   * Used play a taunt message to everyone
   * usage TAUNT + DELIM + sender + DELIM + taunt number
   */
  public static final String TAUNT = "TAUNT";
  
  
  /**
   * command sent by client leaving the game early.  Removes this 
   * Client from the current GameMaster it is in, can also be used
   * in the Lobby. Format is
   * LEAVE_GAME + DELIM + userName
   */
  public static final String LEAVE_GAME = "LEAVE_GAME";
  
  /**
   * command sent by all clients, acknowledging that they have 
   * done all client-side things that need to be done in terms 
   * of physics and animation, and are ready for the next turn
   * to be recieved. Format is DONE + Username
   */
  public static final String DONE = "DONE";

  /**
   * command sent by all clients at the beggining of each round,
   * signifying that they have finished their shop phase, and declaring
   * all important inventory items that everyone knows they have.
   * echoed by the server to all other clients.
   * Format is PUBLIC_INVENTORY + DELIM + username + 
   * DELIM + item1 + DELIM + qty + ...
   */
  public static final String PUBLIC_INVENTORY = "PUBLIC_INVENTORY";

  /**
   * Intermediate input command sent by client so that other clients
   * can animate the other tank's turret movement during their turns
   * Format is MOVE_TURRET + DELIM + UserName + DELIM + angle
   */  
  public static final String MOVE_TURRET = "MOVE_TURRET";
  
  /**
   * Command sent when player dies.  Only sent by the client that actually
   * died. Format is PLAYER_DIED + DELIM + username + DELIM 
   * + Username of killer.
   */
  public static final String PLAYER_DIED = "PLAYER_DIED";

  ///////////////////////////SERVER COMMANDS////////////////////////////////

  //////Out of Game Commands//////
  /**
   * First command sent by server to acknowledge connection,
   * so that client can send USER_NAME. Used without any other tokens
   */
  public static final String ACCEPT_CONNECT = "ACCEPT_CONNECT";

  /**
   * Sent if the user's name doesn't match password, or if the
   * user didn't submit an alphanumeric name.  Format is 
   * NAME_INVALID + DELIM + reason
   */
  public static final String NAME_INVALID = "NAME_INVALID";

  /**
   * Sent if an attempt to start a game failed
   * Format is:
   * DENY_GAME + DELIM + reason
   *
   * added by John Etherton | 4/15/03
   */
  public static final String DENY_GAME = "DENY_GAME";

  /**
   * <i>depracted</i>. Echo MAIN_CHAT instead.
   */
  public static final String MAIN_CHAT_SENT = "MAIN_CHAT_SENT";
  
  /**
   * Acknowledgement of valid USER_NAME, and also sent out whenever
   * the current list of players changes. Format is 
   * PLAYER_LIST + DELIM + UserName1 + DELIM + UserName2 + DELIM + ...
   */
  public static final String PLAYER_LIST = "PLAYER_LIST";
  
  /**
   * Second list sent after a valid user name and also sent out whenever
   * current list of games changes. Format is GAME_LIST + DELIM + 
   * Game1 + DELIM + Game2 + DELIM + Game3 + ....
   */
  public static final String GAME_LIST = "GAME_LIST";

  /**
   * Sent if the client tries to JOIN_GAME that has too many players
   * has already started, etc. Format is DENY_JOIN + DELIM + ErrorMessage
   */ 
  public static final String DENY_JOIN = "DENY_JOIN";
  
  /**
   * Sent when a game join game request is accepted, ensuring
   * the user will have a copy of the game parameters.
   * Format is the same as GAME_INFO.
   */
  public static final String ACCEPT_JOIN = "ACCEPT_JOIN";

  /**
   * Sent when a game is created and accepted, letting the host
   * user know to go into an in game status. Format is the
   * same as ACCEPT_JOIN.
   */
  public static final String ACCEPT_CREATE = "ACCEPT_CREATE";

  /**
   * Sent when a game is created and denyed, letting the user
   * know why. Format is DENY_CREATE + DELIM + errorCode
   */
  public static final String DENY_CREATE = "DENY_CREATE";

  /**
   * <i>depracted</i>. use GAME_INFO instead. 
   */
  public static final String SEND_GAME_INFO = "SEND_GAME_INFO";
  
  //////Lobby Commands//////
  
  /**
   * Sent in acknowledgement to a valid JOIN_GAME, also sent
   * whenever game players changes. Format is GAME_PLAYERS + DELIM
   * + UserName1 + DELIM + color1 + DELIM + UserName2 + DELIM + ...
   */
  public static final String GAME_PLAYERS = "GAME_PLAYERS";
  
  /**
   * <i>depracted</i>. echo GAME_CHAT instead.
   */
  public static final String GAME_CHAT_SENT = "GAME_CHAT_SENT";
  
  /**
   * <i>depracated</i>. use START_GAME instead.
   */
  public static final String GAME_STARTED = "GAME_STARTED";

  /**
   * <i>depracted</i>. use END_GAME instead.
   */
  public static final String GAME_ENDED = "GAME_ENDED";

  //////In Game Commands//////

  /**
   * <i>depracted</i>. echo FIRE instead.
   */
  public static final String USER_FIRED = "USER_FIRED";

  /**
   * <i>depracted</i>. use PLAYER_LIST instead.
   */
  public static final String USER_LEFT_GAME = "USER_LEFT_GAME";
  
  /**
   * <i>depracted</i>. echo MOVE_TURRET instaed.
   */
  public static final String TURRET_MOVED = "TURRET_MOVED";

  /**
   * Send after all users have clicked "done" in the 
   * Shop panel
   */
  public static final String DO_ROUND = "DO_ROUND";

  /**
   * Sent to client who's turn it is. Sent by itself with no
   * other tokens.
   */
  public static final String PROMPT_MOVE = "PROMPT_MOVE";

  /**
   * If the number of players currently in the game is already the
   * total capacity, this will by sent with the DENY_JOIN command
   */
  public static final String TOO_MANY_PLAYERS = "TOO_MANY_PLAYERS";
  
  /**
   * error message sent with DENY_JOIN
   */
  public static final String GAME_ALREADY_STARTED = "GAME_ALREADY_STARTED"; 
  
  /**
   * error message sent with GAME_CLOSED
   */
  public static final String GAME_CLOSED = "GAME_CLOSED";

  /**
   * sent if the server is about to close down
   */
  public static final String SERVER_CLOSING = "SERVER_CLOSING";

    /*
     * Server
     * ------
     * server exiting
     * I. Out of Game
     * -Accept Connect
     * -Chat sent
     * -Player List
     * -GAme List
     * -GAme join confirm
     * -Name invalid
     * -Accept new GAme
     * -Deny new GAme
     * -Deny join game
     * -Map info
     *
     * II. Lobby Messages
     * -Users in lobby
     * -User sent chat
     * -GAme started
     * -Game Ended
     *
     * III. In game
     * -user fired
     * -user left game
     * -turret change
     * -user sent game chat
     * -prompt move
     */

} //end Protocol



