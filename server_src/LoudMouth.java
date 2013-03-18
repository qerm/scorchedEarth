

import java.util.Hashtable;
import java.util.Enumeration;

/**
 *
 * LoudMouth - tells everyone what's going on
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
 
public class LoudMouth implements Protocol, Constants {
    
    
   /**
    * serverThreads hash table
    */    
    private Hashtable serverThreads;



/***************************************************************************/
/*                                MODIFIERS                                */
/***************************************************************************/


   /**
    * Modifier
    * @param st HashTable
    */
    public void setServerThreads(Hashtable st) {
        serverThreads = st;
    }


/***************************************************************************/
/*                                ACCESSORS                                */
/***************************************************************************/


   /**
    * Accessor
    * @return HashTable
    */
    public Hashtable getServerThreads() {
        return serverThreads;
    }
    
    
/*********************************************************************/
/*                METHODS THAT DO REAL STUFF                         */
/*********************************************************************/    
    

    
   /**
    * Constructor
    * @param st HashTable
    */          
    public LoudMouth(Hashtable st) {
        serverThreads = st;
    }


   /**
    * broadcasts a message to all users in hashtable
    * @param msg String
    */    
    public void broadcast(String msg) {
        Enumeration e = serverThreads.elements();
        ServerThread st;
        
        while (e.hasMoreElements()) {
            st = (ServerThread) e.nextElement();
            st.send(msg);
        }
    }

    
    
}