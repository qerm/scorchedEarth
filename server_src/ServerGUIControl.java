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
 

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * 
 * @author John Etherton
 *
 */
public class ServerGUIControl implements Protocol, Constants, ActionListener {
    

   /**
    * the gui
    */    
    private ServerGUI serverGUI;


   /**
    * the gate keeper
    */    
    private GateKeeper gateKeeper;
    
    

/***************************************************************************/
/*                                MODIFIERS                                */
/***************************************************************************/


   /**
    * Modifier
    * @param a ServerGUI
    */
    public void setServerGUI(ServerGUI a) {
        serverGUI = a;
        }    


   /**
    * Modifier
    * @param a GateKeeper
    */
    public void setGateKeeper(GateKeeper a) {
        gateKeeper = a;
        }    
        
        
/***************************************************************************/
/*                                ACCESSORS                                */
/***************************************************************************/


   /**
    * Accessor
    * @return ServerGUI
    */
    public ServerGUI getServerGUI() {
        return serverGUI;
        }    


   /**
    * Accessor
    * @return GateKeeper
    */
    public GateKeeper getGateKeeper() {
        return gateKeeper;
        }    
        


/***************************************************************************/
/*                      METHODS THAT DO REAL STUFF                         */
/***************************************************************************/


   /**
    * constructor
    * @param sg gui
    * @param gk gakekeeper
    */
    public ServerGUIControl(ServerGUI sg, GateKeeper gk) {
        gateKeeper = gk;
        serverGUI = sg;
    }
    

   /**
    * does stuff
    * @param ae ActionEvent
    */
    public void actionPerformed(ActionEvent ae) {
        /*
        if(( ae == null ) || ( ae.getActionCommand() == null ))
            } */
            

        if (ae.getActionCommand().equals("Close")) {
            gateKeeper.shutDown();
            System.exit(0);
            
        }
        
        
        
        if (ae.getActionCommand().equals("Game Info")) {
            serverGUI.displayGameInfo();
            
        }


        if (ae.getActionCommand().equals("User Info")) {
            serverGUI.displayUserInfo();
            
        }
        
    }
            
        

    
}