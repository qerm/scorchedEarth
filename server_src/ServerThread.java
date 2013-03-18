/**
 *
 * GateKeeper - the master of the Scorched Earth realm
 * 
 * @author Team QERM
 *
 *
 * Quiggle, Micheal
 * Etherton, John
 * Reynolds, Wes
 * McCann, David
 *
 */
 
import java.net.Socket;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;
import java.util.StringTokenizer;

/**
 * 
 * @author John Etherton
 *
 */ 
public class ServerThread extends Thread implements Protocol, Constants {



    /**
     * if it's my turn
     */
    private boolean isMyTurn;



    /**
     * used to keep track of who's synced up
     */
    private boolean isSynced;



    /**
     * used to write to the network
     */
    private PrintWriter pw;

    /**
     * Socket the thread is dealing with
     */
    private Socket socket;


   /**
    * is player still alive, used only in game play
    */    
    private boolean isAlive;


   /**
    * pointer to game master
    */    
    private GameMaster gameMaster;


   /**
    * pointer to gate keeper
    */    
    private GateKeeper gateKeeper;


   /**
    * player color, only used durring game play
    */    
    private int color;


   /**
    * player profile
    */    
    private Profile profile;
    
    

/***************************************************************************/
/*                                MODIFIERS                                */
/***************************************************************************/


   /**
    * Modifier
    * @param a boolean
    */    
    public void setIsMyTurn(boolean a) {
        isMyTurn = a;
    }




   /**
    * Modifier
    * @param a boolean
    */    
    public void setIsSynched(boolean a) {
        isSynced = a;
    }
    

   /**
    * Modifier
    * @param a PrintWriter
    */    
    public void setPw(PrintWriter a) {
        pw = a;
    }
    

   /**
    * Modifier
    * @param a Socket
    */    
    public void setSocket(Socket a) {
        socket = a;
    }



   /**
    * Modifier
    * @param a boolean
    */    
    public void setIsAlive(boolean a) {
        isAlive = a;
    }
    
    
   /**
    * Modifier
    * @param a GameMaster
    */    
    public void setGameMaster(GameMaster a) {
        gameMaster = a;
    }
    
    
   /**
    * Modifier
    * @param a GateKeeper
    */    
    public void setGateKeeper(GateKeeper a) {
        gateKeeper = a;
    }
    
    
   /**
    * Modifier
    * @param a int
    */    
    public void setColor(int a) {
        color = a;
    }
    
    
   /**
    * Modifier
    * @param a Profile
    */    
    public void setProfile(Profile a) {
        profile = a;
    }
    




/***************************************************************************/
/*                                ACCESSORS                                */
/***************************************************************************/


   /**
    * accessor
    * @return boolean
    */    
    public boolean getIsMyTurn() {
        return isMyTurn;
    }
    
    


   /**
    * Accessor
    * @return boolean
    */    
    public boolean getIsSynched() {
        return isSynced;
    }

   /**
    * Accessor
    * @return PrintWriter
    */    
    public PrintWriter getPw() {
        return pw;
    }




   /**
    * Accessor
    * @return Socket
    */
    public Socket getSocket() {
        return socket;
    }



   /**
    * Accessor
    * @return boolean
    */
    public boolean getIsAlive() {
        return isAlive;
    }
    

   /**
    * Accessor
    * @return GameMaster
    */   
    public GameMaster getGameMaster() {
        return gameMaster;
    }
    

   /**
    * Accessor
    * @return GateKeeper
    */       
    public GateKeeper getGateKeeper() {
        return gateKeeper;
    }
    

   /**
    * Accessor
    * @return int
    */       
    public int getColor() {
        return color;
    }
    

   /**
    * Accessor
    * @return Profile
    */       
    public Profile getProfile() {
        return profile;
    }


/***************************************************************************/
/*                      METHODS THAT DO REAL STUFF                         */
/***************************************************************************/



   /**
    * Constructor
    * @param a GateKeeper
    * @param s Socket
    */
    public ServerThread(GateKeeper a, Socket s) {
        gateKeeper = a;
        socket = s;
        isSynced = false;
        profile = new Profile();
    }

    
   /**
    * run over rides Thread method
    */    
    public void run() {

        String input;
        try {
            
            BufferedReader br = new BufferedReader(
                new InputStreamReader(socket.getInputStream()));

            pw = new PrintWriter(
                new OutputStreamWriter(socket.getOutputStream()));
            
            send(ACCEPT_CONNECT);
            while (true) {
                
                input = br.readLine();
                parseCommand(input);
            }
        } catch (IOException e) {
            disconnect();
        }
    } //end of run()


   /**
    * parses commands from network
    * @param input String from the network.
    */    
    private void parseCommand(String input) {
        
        StringTokenizer st = new StringTokenizer(input, DELIM);
        String key = st.nextToken();
        
        System.out.println("received from " + this + " : " + input);

        try {
                    
            if (key.equals(USER_NAME)) {
                Profile p = getProfile();
                p.setUserName(st.nextToken());
                p.setPassword(st.nextToken());
                gateKeeper.verifyUser(this);
                
            } else if (key.equals(TAUNT)) {
                gameMaster.sendChat(input);
    
            } else if (key.equals(NEW_USER_NAME)) {
                Profile p = getProfile();
                p.setUserName(st.nextToken());
                p.setPassword(st.nextToken());
                gateKeeper.addUser(this);
                                   
            } else if (key.equals(CREATE_GAME)) {
                
                GameParameters gp = new GameParameters();
                st.nextToken();
                gp.parseFormattedString(st);
                gateKeeper.createGame(gp, this);
                
            } else if (key.equals(JOIN_GAME)) {
                st.nextToken();
                String gameName = st.nextToken();
                String password = "";
                if (st.hasMoreTokens()) {
                    password = st.nextToken();
                }
                gateKeeper.joinGame(gameName, 
                                    password,
                                    this);
                                    
                                    
            } else if (key.equals(MAIN_CHAT)) {
                
                gateKeeper.sendChat(MAIN_CHAT + DELIM
                                    + st.nextToken()
                                    + DELIM
                                    + st.nextToken());
                                    
                                    
            } else if (key.equals(GAME_INFO)) {
             
                st.nextToken();
                gateKeeper.getGameInfo(st.nextToken(), this);
             
                
            } else if (key.equals(DISCONNECT)) {
                disconnect();
                            
            } else if (key.equals(GAME_CHAT)) {
                
                gameMaster.sendChat(GAME_CHAT + DELIM
                                    + st.nextToken()
                                    + DELIM
                                    + st.nextToken());
    
            } else if (key.equals(CHOOSE_COLOR)) {
                
                gameMaster.changeColor(st.nextToken(),
                            Integer.parseInt(st.nextToken()));
                
            } else if (key.equals(START_GAME)) {
                
                gameMaster.gameShop();
                
                
            } else if (key.equals(END_GAME)) {
                
    
                gameMaster.gameExit();
    
                
            } else if (key.equals(FIRE)) {
                gameMaster.shotFired(input, this);
    
                
            } else if (key.equals(LEAVE_GAME)) {
                
                leaveGame();
                
                
            } else if (key.equals(DONE)) {
                gameMaster.sync(this);
                
            } else if (key.equals(PUBLIC_INVENTORY)) {
                gameMaster.updateInventory(input, this);
                
            } else if (key.equals(MOVE_TURRET)) {
    
                gameMaster.turretMoved(input);
                
            } else if (key.equals(PLAYER_DIED)) {
                    
                    gameMaster.died(st.nextToken(), st.nextToken(), this);
            } else if (key.equals(TAUNT)) {
                    st.nextToken();
                    gameMaster.taunt(input);
            }
        } catch (Exception e) {
            int i = 0;
        }

    }


   /**
    * makes hashCode off of name
    * @return int hash code
    */    
    public int hashCode() {
        return (profile.getUserName()).hashCode();
    }


   /**
    * gets game info from GK
    * @param gParams game parameters
    */    
    public void gameInfo(GameParameters gParams) {
        send(GAME_INFO + DELIM + gParams.getFormattedString());
    }
    
    
   /**
    * Disconects from server and cleans up
    */    
    public void disconnect() {
        gateKeeper.removeUser(profile.getUserName());
        
        gateKeeper.updateStats(profile.getUserName(), profile.getMoney());
    }


   /**
    * Leaves a game and cleans up
    */    
    public void leaveGame() {
        if (gameMaster != null) {
            gameMaster.removeUser(profile.getUserName());
            gameMaster = null;
        }
    }
    

   /**
    * used by GK to signal bad login
    * @param err String error code
    */
    public void badLogin(String err) {
        send(err);
    }
    

   /**
    * used by client to send chat messages
    * @param msg String
    */
    public void send(String msg) {
        System.out.println("sending to " + this + " : "  + msg);
        pw.println(msg);
        pw.flush();
    }


   /**
    * used by GK to signal bad new game request
    * @param err String error code
    */    
    public void badGame(String err) {
        send(DENY_JOIN + DELIM + err);
    }


   /**
    * used by GK to signal game initialized correctly
    * @param gParam the GameParameters of the game
    * @param isHead am I the game head?
    */    
    public void gameInitialized(GameParameters gParam
                                , boolean isHead) {
                                    
        isAlive = true;
        if (isHead) {
            send(ACCEPT_CREATE + DELIM + gParam.getFormattedString());
        } else {
            send(ACCEPT_JOIN + DELIM + gParam.getFormattedString());
        }
    }
    
    
   /**
    * used by GK to signal game initialized correctly
    * @return String representation of a thread
    */
    public String toString() {
        return profile.getUserName();
    }



    
}        











