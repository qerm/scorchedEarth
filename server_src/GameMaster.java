
 
import java.util.Hashtable;
//import java.util.StringTokenizer;
import java.util.Enumeration;
import java.util.Vector;

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
public class GameMaster extends Thread implements Protocol, 
                Constants, Runnable {
    


   /**
    * The user that started the game
    */    
    private Vector vector;



   /**
    * The user that started the game
    */    
    private ServerThread headPlayer;
    
    
   /**
    * all the current players
    */    
    private Hashtable serverThreads;


   /**
    * our loud mouth
    */    
    private LoudMouth loudMouth;


   /**
    * parameters of the game
    */    
    private GameParameters gameParams;


   /**
    * is the game still joinable?
    */    
    private boolean isJoinable;


   /**
    * the gate keeper
    */    
    private GateKeeper gateKeeper;


   /**
    * is the game currently in progress
    */    
    private boolean isGameStarted;


   /**
    * number of rounds played
    */    
    private int roundNumber;


   /**
    * number of players who have sent their sync signal
    */    
    private int syncCount;


   /**
    * state of the game
    */    
    private int gameState;


   /**
    * Number of folks who passed away
    */    
    private int numDead;


   /**
    * Number of players who's turn it is
    */    
    private int turn;
    



/***********************************************************************/
/*                                MODIFIERS                            */
/***********************************************************************/    




   /**
    * Modifier
    * @param a Vector
    */    
    public void setVector(Vector a) {
        vector = a;
    }




    
   /**
    * Modifier
    * @param a ServerThread
    */    
    public void setHeadPlayer(ServerThread a) {
        headPlayer = a;
    }


   /**
    * Modifier
    * @param a Hashtable
    */    
    public void setServerThreads(Hashtable a) {
        serverThreads = a;
    }
    
    
   /**
    * Modifier
    * @param a LoudMouth
    */        
    public void setLoudMouth(LoudMouth a) {
        loudMouth = a;
    }
        

   /**
    * Modifier
    * @param a GameParameters 
    */        
    public void setGameParams(GameParameters a) {
        gameParams = a;
    }
        

   /**
    * Modifier
    * @param a boolean
    */        
    public void setIsJoinable(boolean a) {
        isJoinable = a;
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
    * @param a boolean
    */        
    public void setIsGameStarted(boolean a) {
        isGameStarted = a;
    }
        

   /**
    * Modifier
    * @param a int
    */        
    public void setRoundNumber(int a) {
        roundNumber = a;
    }
        

   /**
    * Modifier
    * @param a int
    */        
    public void setSyncCount(int a) {
        syncCount = a;
    }
        

   /**
    * Modifier
    * @param a int
    */        
    public void setGameState(int a) {
        gameState = a;
    }
        

   /**
    * Modifier
    * @param a int 
    */        
    public void setNumDead(int a) {
        numDead = a;
    }
        

   /**
    * Modifier
    * @param a int 
    */        
    public void setTurn(int a) {
        turn = a;
    }
            



/***************************************************************************/
/*                                ACCESSORS                                */
/***************************************************************************/




   /**
    * Accessor
    * @return Vector
    */    
    public Vector getVector() {
        return vector;
    }









   /**
    * Accessor
    * @return ServerThread
    */    
    public ServerThread getHeadPlayer() {
        return headPlayer;
    }


   /**
    * Accessor
    * @return Hashtable
    */    
    public Hashtable getServerThreads() {
        return serverThreads;
    }
    
    

   /**
    * Accessor
    * @return LoudMouth
    */    
    public LoudMouth getLoudMouth() {
        return loudMouth;
    }
        

   /**
    * Accessor
    * @return GameParameters
    */    
    public GameParameters getGameParams() {
        return gameParams;
    }
        

   /**
    * Accessor
    * @return boolean
    */    
    public boolean getIsJoinable() {
        return isJoinable;
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
    * @return boolean
    */    
    public boolean getIsGameStarted() {
        return isGameStarted;
    }
        

   /**
    * Accessor
    * @return int
    */    
    public int getRoundNumber() {
        return roundNumber;
    }
        

   /**
    * Accessor
    * @return int
    */        
    public int getSyncCount() {
        return syncCount;
    }
        

   /**
    * Accessor
    * @return int
    */        
    public int getGameState() {
        return gameState;
    }
        

   /**
    * Accessor
    * @return int
    */        
    public int getNumDead() {
        return numDead;
    }
        

   /**
    * Accessor
    * @return int
    */        
    public int getTurn() {
        return turn;
    }


/***************************************************************************/
/*                      METHODS THAT DO REAL STUFF                         */
/***************************************************************************/

   /**
    * Constructor
    * @param gParam GameParameters
    * @param gk GateKeeper
    */
    public GameMaster(GameParameters gParam, GateKeeper gk) {
        gateKeeper = gk;
        gameParams = gParam;
        gameState = LOBBY_STATE;
        isGameStarted = false;
        isJoinable = true;
        serverThreads = new Hashtable();
        loudMouth = new LoudMouth(serverThreads);
    }



   /**
    * called by a ServerThread when a user wants to join a game
    * used when game is private
    * @param user ServerThread of user
    * @param password used to gain access to private game
    * @param isHead boolean am i the head or not?
    */
    public void addUser(ServerThread user, String password
                        , boolean isHead) {

        if (!isGameStarted &&  (serverThreads.size() 
            < gameParams.getMaxPlayers())) {
            
            
            if (gameParams.getIsGamePrivate()) {
                if (password.equals(gameParams.getPassword())) {
                    serverThreads.put((user.getProfile()).getUserName()
                        , user);
                    user.setGameMaster(this);
                    user.gameInitialized(gameParams, isHead);
                    pickColor(user);
                    sendPlayerList();        
                } else {
                    user.badGame("Wrong password");
                }
            } else {
                serverThreads.put((user.getProfile()).getUserName(), user);
                user.setGameMaster(this);
                user.gameInitialized(gameParams, isHead);
                pickColor(user);
                sendPlayerList();
            }            
        
        } else {
            if (isGameStarted) {
                user.badGame("Game already started");
            }
            if (serverThreads.size() >= gameParams.getMaxPlayers()) {
                user.badGame("Game is full");
            }
        }
    }


   /**
    * called by a ServerThread when players leaves game
    * @param userName of player who left
    */
    public void removeUser(String userName) {
        ServerThread st = null;
        if (gameState == LOBBY_STATE 
            && userName.equals(headPlayer.toString())) {
                gameExit();
        } else if (gameState == LOBBY_STATE
            && !userName.equals(headPlayer.toString())) {
            st = (ServerThread) serverThreads.remove(userName);
            sendPlayerList();
            
        } else {
            st = (ServerThread) serverThreads.remove(userName);
            loudMouth.broadcast(GAME_CHAT + DELIM + "GAME MASTER" 
                                + DELIM + userName + " has left the game");
            loudMouth.broadcast(LEAVE_GAME + DELIM + userName);
            
        }
        
        sync(null);
        updateInventory(null, null);
        
        
        if (isGameStarted) {
            vector.remove(st);
            gateKeeper.getScore(st);
            if (st.getIsMyTurn()) {
                st = (ServerThread) vector.get(turn % vector.size());
                st.setIsMyTurn(true);
                st.send(PROMPT_MOVE);
            }
        }
         
        if (serverThreads.size() < 2 && isGameStarted) {
            gameExit();
        }
        
    }


   /**
    * called by a ServerThread to send game chat to everyone
    * @param msg message to be sent
    */    
    public void sendChat(String msg) {
        loudMouth.broadcast(msg);
    }


   /**
    * called by a ServerThread to change a players color
    * @param userName name of player requesting color change
    * @param color requested
    */    
    public void changeColor(String userName, int color) {
        Enumeration e = serverThreads.elements();
        ServerThread st;
        boolean isGood = true;
        
        while (e.hasMoreElements()) {
            st = (ServerThread) e.nextElement();
            
            if (st.getColor() == color) {
                isGood = false;
                break;
            }
        }
        
        if (isGood) {
            st = (ServerThread) serverThreads.get(userName);
            st.setColor(color);
        }
        
        sendPlayerList();
        
            
    }



   /**
    * Overrides Threads run method
    */
    public void run () {
        
        /*******************************/
        /*the brains of the whole thing*/
        /*******************************/

        /************************************/
        /*maybe it's not the brains afer all*/
        /************************************/        
        
    }
    


   /**
    * used to send game info to GM and players
    * @param userName name of player sending info
    * @param message game info message
    */    
    public void receivedGameInfo(String userName, String message) {
        /*****************/
        /*what's this due*/
        /*****************/
    }


   /**
    * called by ServerThread when user sends sync signal
    * @param st ServerThread of user who sent signal
    */    
    public void sync(ServerThread st) {
        if (st != null) {
            st.setIsSynched(true);
        }


        boolean areAllSynched = true;
        int numDead = 0;
        
        Enumeration e = serverThreads.elements();
        

        
        while (e.hasMoreElements()) {
            st = (ServerThread) e.nextElement();
            if (!st.getIsSynched()) {
                areAllSynched = false;
            }
            if (!st.getIsAlive()) {
                numDead++;
            }
            
        }
        
        
        if (areAllSynched && (numDead == serverThreads.size() - 1)) {
            if (roundNumber == gameParams.getNumRounds()) {
                gameEnd();
            } else {
                gameShop();
            }
        }

        if (areAllSynched && (numDead < serverThreads.size() - 1)) {
            doTurn();
        }
        
    }


   /**
    * Called by ServerThread when a user has been
    * killed
    * @param casualty name of user who is dead
    * @param killer name of user who did the killing
    * @param st serverthread
    */    
    public void died(String casualty, String killer, ServerThread st) {
        
        
        if (!killer.equals(casualty)) {
        
            numDead++;
            st.setIsAlive(false);
            st = (ServerThread) serverThreads.get(killer);
            long m = st.getProfile().getMoney() + (long) KILL_MONEY;
            st.getProfile().setMoney(m);


            sendChat(GAME_CHAT + DELIM + "GAME MASTER" + DELIM + casualty
                + " has been killed by " 
                + killer 
                + " who's new score is $" + m);
        } else {

            sendChat(GAME_CHAT + DELIM + "GAME MASTER" + DELIM + casualty
                + " Just killed himself, what an idiot ");
        }
            
        
    }


   /**
    * goes into gameShop mode
    */    
    public void gameShop() {
        if (isGameStarted == false) {
            vector = new Vector(serverThreads.values());
            isGameStarted = true;
            isJoinable = false;
            gateKeeper.sendGameList();
            loudMouth.broadcast(START_GAME);
            roundNumber = 0;
            
        }
        gameState = SHOP_STATE;

        Enumeration e = serverThreads.elements();
        ServerThread st;
        while (e.hasMoreElements()) {
            st = (ServerThread) e.nextElement();
            st.setIsSynched(false);
        }
            
    }


   /**
    * runs through a round
    */    
    public void doRound() {
        roundNumber++;
        System.out.println("round number " + roundNumber + " rounds to play " 
            + gameParams.getNumRounds());
        if (roundNumber > gameParams.getNumRounds()) {
            gameEnd();
        } else {

            Enumeration e = serverThreads.elements();
            ServerThread st;
            while (e.hasMoreElements()) {
                st = (ServerThread) e.nextElement();
                st.setIsAlive(true);
            }
            loudMouth.broadcast(DO_ROUND);
            gameState = TURN_STATE;
            //turn = 1;
            doTurn();
        }
    }


   /**
    * runs through a players turn
    */    
    public void doTurn() {
        ServerThread st;
        turn++;  
        while (true) {
            st = (ServerThread) vector.get(turn % vector.size());
            if (st.getIsAlive()) {
                break;
            }
            turn++;
            
        }
            
        st.setIsMyTurn(true);
        st.send(PROMPT_MOVE);
        gameState = WAITING_FOR_SYNC_STATE;
        
        Enumeration e = serverThreads.elements();

        while (e.hasMoreElements()) {
            st = (ServerThread) e.nextElement();
            st.setIsSynched(false);
        }
    }


   /**
    * Called when a game is terminating
    * this differs from gameEnd() in that
    * the game may not have come to a conclusion
    * gameEnd() calls gameExit()
    */    
    public void gameExit() {

            loudMouth.broadcast(END_GAME);
            Enumeration e = serverThreads.elements();
            
            while (e.hasMoreElements()) {
                ServerThread st = (ServerThread) e.nextElement();
                st.setGameMaster(null);
                gateKeeper.updateStats(st + "", st.getProfile().getMoney());
            } 


            e = serverThreads.elements();
            
            while (e.hasMoreElements()) {
                ServerThread st = (ServerThread) e.nextElement();
                gateKeeper.getScore(st);
            } 
       
            gateKeeper.removeGame(toString());

            
    }


   /**
    * Calls the updateStats() method in GK
    * called at the end of a game to update
    * the new lifetime score of a user
    */    
    public void updateStats() {
        Enumeration e = serverThreads.elements();
        ServerThread st;
        Profile p;
        
        while (e.hasMoreElements()) {
            st = (ServerThread) e.nextElement();
            p = st.getProfile();
            
            gateKeeper.updateStats(p.getUserName(), p.getMoney());
        }
    }


   /**
    * Broadcasts the list of current players
    */    
    public void sendPlayerList() {
        Enumeration e = serverThreads.elements();
        String retval = GAME_PLAYERS;
        ServerThread st;
        
        while (e.hasMoreElements()) {
            st = (ServerThread) e.nextElement();
            retval = retval + DELIM + (st.getProfile()).getUserName()
                     + DELIM + st.getColor();
            }
            
        loudMouth.broadcast(retval);
            
    }


   /**
    * Called at the end of the game
    * updates scores and stuff like that
    */    
    public void gameEnd() {
        //do i need to do anything special here?
        gameExit();
    }


   /**
    * updates the inventory for a tank
    * @param inventory String all of what the tank has
    * @param st ServerThread
    * I changed this to be a StringTokenizer instead of a String
    * for inventory JOHN ETHERTON | 4/15/03
    */    
    public void updateInventory(String inventory, ServerThread st) {
        if (st != null) {
            loudMouth.broadcast(inventory);
            st.setIsSynched(true);
        }

        boolean areAllSynced = true;
        Enumeration e = serverThreads.elements();

        while (e.hasMoreElements()) {
            st = (ServerThread) e.nextElement();
            if (!st.getIsSynched()) {
                areAllSynced = false;
                break;
            }
        }

        if (areAllSynced) {
            e = serverThreads.elements();
            while (e.hasMoreElements()) {
                st = (ServerThread) e.nextElement();
                st.setIsSynched(false); 
            }
            
            doRound();
        }

    }


   /**
    * called by ServerThread to notify that the turret has moved
    * @param msg all the info needed to move a turret
    */    
    public void turretMoved(String msg) {
        loudMouth.broadcast(msg);
    }


   /**
    * called by ServerThread to notify that a shot has been fired
    * @param msg containing all info needed
    * @param st ServerThread
    */    
    public void shotFired(String msg, ServerThread st) {
        st.setIsMyTurn(false);
        loudMouth.broadcast(msg);
    }



   /**
    * toString method
    * @return String
    */    
    public String toString() {
        return gameParams.getName();
    }




   /**
    * picks the next available color
    * @param user ServerThread
    */    
    public void pickColor(ServerThread user) {
        Enumeration e = serverThreads.elements();
        boolean isColorLeft[] = new boolean[MAX_PLAYERS];
        int i; 
        
        for (i = 0; i < MAX_PLAYERS; i++) {
            isColorLeft[i] = true;
        }
        
        while (e.hasMoreElements()) {
            ServerThread s = (ServerThread) e.nextElement();
            isColorLeft[s.getColor()] = false;
        }
            
        for (i = 0; i < MAX_PLAYERS; i++) {
            if (isColorLeft[i] == true) {
                break;
            }
        }
        
        user.setColor(i);
        
                    
    }
    
    
   /**
    * Sends taunts to everyone
    * @param t is for taunt
    */    
    public void taunt(String t) {
        loudMouth.broadcast(t);   
    }
        
}    
