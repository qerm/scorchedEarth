
 
import java.net.ServerSocket;
import java.net.Socket;

import java.util.Hashtable;
import java.util.Enumeration;
import java.util.Vector;
//import java.util.Comparator;
//import java.util.TreeSet;

import java.io.ObjectOutputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.FileInputStream;

import java.io.IOException;
//import java.lang.ClassNotFoundException;

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
public class GateKeeper extends Thread implements Protocol, 
                    Constants, Runnable {


   /**
    * int control for the gui
    */    
    private ServerGUIControl sgc;




   /**
    * int port num that we're gonna listen on
    */    
    private int portNum;



   /**
    * ServerSocket used to spawn new connections
    */    
    private ServerSocket serverSocket;
    

   /**
    * HashTable of ServerThreads, used to keep track of online users
    */
    private Hashtable serverThreads;
    

   /**
    * Hashtable of GameMasters, used to keep track of all games
    */    
    private Hashtable gameMasters;
    
   
   /**
    * Hashtable of Profiles
    */
    private Hashtable profiles;
    
   
   /**
    * ServerGUI that monitors the GK
    */
    private ServerGUI serverGUI;
    
   
   /**
    * LoudMouth used to broadcast messages
    */
    private LoudMouth loudMouth;
    

   /**
    * boolean running used to stop
    * wait()
    */
    private boolean running = true;
    
    
/***************************************************************************/
/*                                MODIFIERS                                */
/***************************************************************************/


   /**
    * Modifier
    * @param n ServerGUIControl
    */
    public void setSgc(ServerGUIControl n) {
        sgc = n;
    }

   /**
    * Modifier
    * @param n int
    */
    public void setPortNum(int n) {
        portNum = n;
    }



   /**
    * Modifier
    * @param r boolean
    */
    public void setRunning(boolean r) {
        running = r;
    }


   /**
    * Modifier
    * @param gm Hashtable
    */
    public void setGameMasters(Hashtable gm) {
        gameMasters = gm;
    }


   /**
    * Modifier
    * @param lm LoudMouth
    */    
    public void setLoudMouth(LoudMouth lm) {
        loudMouth = lm;
    }


   /**
    * Modifier
    * @param p Hashtable
    */
    public void setProfiles(Hashtable p) {
        profiles = p;
    }


   /**
    * Modifier
    * @param sg SeverGUI
    */
    public void setServerGUI(ServerGUI sg) {
        serverGUI = sg;
    }


   /**
    * Modifier
    * @param ss ServerSocket
    */
    public void setServerSocket(ServerSocket ss) {
        serverSocket = ss;
    }


   /**
    * Modifier
    * @param st Hashtable
    */
    public void setServerThreads(Hashtable st) {
        serverThreads = st;
    }
    



/***************************************************************************/
/*                                ACCESSORS                                */
/***************************************************************************/


   /**
    * Accessor
    * @return ServerGUIControl
    */
    public ServerGUIControl getSgc() {
        return sgc;
    }


   /**
    * Accessor
    * @return n int
    */
    public int getPortNum() {
        return portNum;
    }




   /**
    * Accessor
    * @return boolean r
    */
    public boolean getRunning() {
        return running;
    }


   /**
    * Accessor
    * @return Hashtable gameMaster
    */
    public Hashtable getGameMasters() {
        return gameMasters;
    }


   /**
    * Accessor
    * @return LoudMouth loudMouth
    */    
    public LoudMouth getLoudMouth() {
        return loudMouth;
    }


   /**
    * Accessor
    * @return Hashtable profiles
    */
    public Hashtable getProfiles() {
        return profiles;
    }


   /**
    * Accessor
    * @return ServerGUI serverGUI
    */
    public ServerGUI getServerGUI() {
        return serverGUI;
    }


   /**
    * Accessor
    * @return ServerSocket serverSocket
    */
    public ServerSocket getServerSocket() {
        return serverSocket;
    }


   /**
    * Accessor
    * @return Hashtable serverThreads
    */
    public Hashtable getServerThreads() {
        return serverThreads;
    }
        


/***************************************************************************/
/*                      METHODS THAT DO REAL STUFF                         */
/***************************************************************************/

   /**
    * Constructor
    * @param ss ServerSocket
    * @param gui ServerGUI
    * @param c ServerGUIControl
    */
    public GateKeeper(ServerSocket ss, ServerGUI gui, ServerGUIControl c) {
        /*make instances of stuff*/
        serverGUI = gui;
        sgc = c;
        if (sgc != null) {
            sgc.setGateKeeper(this);
        }
        if (serverGUI != null) {
            serverGUI.setGateKeeper(this);
        }

        
        gameMasters = new Hashtable();
        profiles = new Hashtable();
        serverThreads = new Hashtable();
        loudMouth = new LoudMouth(serverThreads);
        serverSocket = ss; 
        
        /*load info from disk*/
        loadInfo("profiles.dat");
        
        
        
    }


   /**
    * Constructor
    */
    public GateKeeper() {
        /*make instances of stuff*/
        gameMasters = new Hashtable();
        profiles = new Hashtable();
        serverThreads = new Hashtable();
        loudMouth = new LoudMouth(serverThreads);
                
        /*load info from disk*/
        //loadInfo("profiles.dat"); //uncomment this out//////////////////
        
        
        
    }




   /**
    * returns game parameters to the gui
    * @param gameName of name Game to get data about
    * @return GameParameters of requested game
    */
    public GameParameters getGameParams(String gameName) {
        
        GameMaster tempGM;
        
        if (gameMasters.containsKey(gameName)) {
            tempGM = (GameMaster) gameMasters.get(gameName);
            return tempGM.getGameParams();
        } else {
            return null;
        }
    }


   /**
    * returns user profile to the gui
    * @param userName of name user to get data about
    * @return Profile of requested user
    */
    public Profile getUserProfile(String userName) {
        
        if (profiles.containsKey(userName)) {
            return (Profile) profiles.get(userName);
        } else {
            return null;
        }
    }




   /**
    * loads info from file
    * @param file String name of the data file
    */
    private void loadInfo(String file) {
        
        try {
            FileInputStream inStream =
                new FileInputStream(file);
            
            ObjectInputStream inObject = 
                new ObjectInputStream(inStream);
                
            profiles = (Hashtable) inObject.readObject();
            
            inObject.close();
            inStream.close();
            

        } catch (IOException e) {
            System.out.println();
        } catch (ClassNotFoundException e) {
            System.out.println();
        }
    }


   /**
    * removes a users SeverThread from the hashtable
    * @param name String
    */    
    public void removeUser(String name) {
        ServerThread st;
        GameMaster gm;
        
        if (serverGUI != null) {
            serverGUI.updateConsel("Removing " + name);
        }
        
        //Makes sure the user is still in here
        if (serverThreads.containsKey(name)) {
            
            st = (ServerThread) serverThreads.get(name);
            serverThreads.remove(name); //boy is gone

            gm = st.getGameMaster();
            
            //if it's in a game tells the game he's leaving
            if (gm != null) {
                gm.removeUser(name);
            }
            sendUserList();
        }       
    }


   /**
    * saves info to a file
    * @param file String
    */    
    public void saveInfo(String file) {

        try {
            FileOutputStream outStream =
                new FileOutputStream(file);
            
            ObjectOutputStream outObject = 
                new ObjectOutputStream(outStream);
                
            outObject.writeObject(profiles);
            
            outObject.close();
            
            outStream.close();
            if (serverGUI != null) {
                serverGUI.updateConsel("info saved to file");
            }
                        

        } catch (IOException e) {
            System.out.println();
        } 
    }


   /**
    * Used to create a game
    * @param gParam GameParameters
    * @param st ServerThread
    */        
    public void createGame(GameParameters gParam, ServerThread st) {
        if (serverGUI != null) {
            serverGUI.updateConsel("create game request from" + st 
                               + " for " + gParam);
        }
                               
        if (gameMasters.containsKey(gParam.getName())) {
            st.badGame(NAME_INVALID + DELIM + "Name already used");
            if (serverGUI != null) {
                serverGUI.updateConsel("create game denied for " + st);
            }
            
        } else {
            
            
            GameMaster gm = new GameMaster(gParam, this);
            gameMasters.put(gParam.getName(), gm);
            gm.run();
            gm.setHeadPlayer(st);
            gm.addUser(st, gParam.getPassword(), true);
            
            if (serverGUI != null) {
                serverGUI.updateConsel("create game accepted for " + st);
            }
            sendGameList();
        }
    }


   /**
    * Removes a game from the hashtable
    * @param gameName String
    */        
    public void removeGame(String gameName) {
        if (gameMasters.containsKey(gameName)) {
            GameMaster gm  = (GameMaster) gameMasters.get(gameName);
            //gm.gameExit();
            gameMasters.remove(gameName);
            if (serverGUI != null) {
                serverGUI.updateConsel("removing game " + gameName);
            }
            sendGameList();
        }
    }


   /**
    * called to let a user join a game
    * @param gameName String
    * @param password String
    * @param st ServerThread
    */        
    public void joinGame(String gameName, String password, ServerThread st) {
        
        
        if (serverGUI != null) {
            serverGUI.updateConsel(st + " wants to join " 
                               + gameName + " with password "
                               + password);
        }                               
        
        if (gameMasters.containsKey(gameName)) {
            GameMaster gm;
            gm = (GameMaster) gameMasters.get(gameName);
            gm.addUser(st, password, false);
        } else {
            if (serverGUI != null) {
                serverGUI.updateConsel(st 
                + " rejected from joining: bad name");
            }
            st.badGame(NAME_INVALID + DELIM + "No game with that name");
        }
    }


   /**
    * adds a new user to the server
    * @param st ServerThread
    */            
    public void addUser(ServerThread st) {
        Profile pNew;
        pNew = st.getProfile();
        serverGUI.updateConsel("add user request from " + pNew.getUserName());
        if (profiles.containsKey(pNew.getUserName())) {
            st.badLogin(NAME_INVALID + DELIM + "Username already used");
            if (serverGUI != null) {
                serverGUI.updateConsel(pNew.getUserName() 
                + " name already used");
            }
        } else {
            profiles.put(pNew.getUserName(), pNew);
            serverThreads.put(pNew.getUserName(), st);
            sendUserList();
            if (serverGUI != null) {
                serverGUI.updateConsel(st + " joining server");
            }
            sendGameList();
            saveInfo("profiles.dat");
            getScore(st);
        }
    }



   /**
    * adds a new user to the server
    * @param st ServerThread
    */            
    public void verifyUser(ServerThread st) {
        Profile pNew;
        pNew = st.getProfile();
        
        if (serverGUI != null) {
            serverGUI.updateConsel("verify user request for " + st);
        }
         
        if (serverThreads.containsKey(pNew.getUserName())) {
            st.badLogin(NAME_INVALID + DELIM + "Your already logged in!!");
            return;
        }
            
            
            
        if (!profiles.containsKey(pNew.getUserName())) {
            st.badLogin(NAME_INVALID + DELIM + "Name doesn't exists");
            if (serverGUI != null) {
                serverGUI.updateConsel("name doesn't exists for " + st);
            }
        } else {
            Profile pCurrent;
            pCurrent = (Profile) profiles.get(pNew.getUserName());
            if (pNew.getPassword().equals(pCurrent.getPassword())) {
                serverThreads.put(pNew.getUserName(), st);
                if (serverGUI != null) {
                    serverGUI.updateConsel(st + " joining server");
                }
                sendUserList();
                sendGameList();
                st.setProfile(pCurrent);
                getScore(st);
            } else {
                if (serverGUI != null) {
                    serverGUI.updateConsel("bad password for " + st);
                }
                st.badLogin(NAME_INVALID + DELIM + "Password incorrect");
            }
        }
    }
    
    

   /**
    * Shuts the whole thing down, gracefully
    */            
    public void shutDown() {
        
        if (serverGUI != null) {
            serverGUI.updateConsel("SERVER IS SHUTTING DOWN!!!!!!!!!!!!!!!");
        }
        loudMouth.broadcast(/*SERVER_EXIT*/"");
        
        Enumeration e;
        e = gameMasters.elements();
        GameMaster gm;

        while (e.hasMoreElements()) {
            gm = (GameMaster) e.nextElement();
            gm.gameExit();
        }
        
        saveInfo("profiles.dat");
        
    }


   /**
    * Sends a chat message to all ServerThreads
    * @param msg String
    */            
    public void sendChat(String msg) {
        if (serverGUI != null) {
            serverGUI.updateConsel("sending chat msg: " + msg);
        }
        loudMouth.broadcast(msg);
    }


   /**
    * returns info on a game
    * @param gameName String
    * @param st ServerThread
    */            
    public void getGameInfo(String gameName, ServerThread st) {
      System.out.println(gameName);

        if (gameMasters.containsKey(gameName)) {
            GameMaster gm;
            gm = (GameMaster) gameMasters.get(gameName);
            st.gameInfo(gm.getGameParams());
        } else {
            st.badGame(NAME_INVALID + DELIM + "Game doesn't exists");
        }
    }


   /**
    * updates a users profile
    * @param name String
    * @param money int
    */            
    public void updateStats(String name, long money) {
        if (serverGUI != null) {
            serverGUI.updateConsel("updating stats for " + name
                               + "current money: $" + money);
        }
        
        
        if (profiles.containsKey(name)) {
            Profile p = (Profile) profiles.get(name);
            p.setMoney(money);
            saveInfo("profiles.dat");
        } else {
        }
    }


   /**
    * Broadcasts user list to everyone
    */            
    public void sendUserList() {
        String retval = PLAYER_LIST;
        Enumeration e = serverThreads.elements();
        ServerThread s;
        
        while (e.hasMoreElements()) {
            s = (ServerThread) e.nextElement();
            retval = retval + DELIM + (s.getProfile()).getUserName();
        }
        
        loudMouth.broadcast(retval);
        
        Vector v = new Vector(serverThreads.values());
        if (serverGUI != null) {
            serverGUI.updateUserList(v);
        }
    }


   /**
    * Broadcasts game list to everyone
    */            
    public void sendGameList() {
        String retval = GAME_LIST;
        Enumeration e = gameMasters.elements();
        GameMaster gm;
        
        while (e.hasMoreElements()) {
            gm = (GameMaster) e.nextElement();
            if (!gm.getIsGameStarted()) {
                retval = retval + DELIM + (gm.getGameParams()).getName();
            }
        }
        
        loudMouth.broadcast(retval);
        Vector v = new Vector(gameMasters.values());
        if (serverGUI != null) {
            serverGUI.updateGameList(v);
        }
    }


   /**
    * pretty much just calls waitForUser
    */            
    public void run() {
        try {
            while (true) {
                Socket s = serverSocket.accept();
                if (serverGUI != null) {
                    serverGUI.updateConsel("incoming connection from " + s);
                }
                new ServerThread(this, s).start();
                
            }
        } catch (IOException e) {
            System.out.println(e);
        }
        
    }
    
   /**
    * gets score
    * @param st ServerThread
    */            
    public void getScore(ServerThread st) {
       Profile myPro = st.getProfile();

        st.send(MAIN_CHAT + DELIM + "SERVER" + DELIM
                + "YOUR CURRENT SCORE: " + myPro.getMoney());

    }
    
}
