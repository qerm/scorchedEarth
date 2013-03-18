//======================================================
// Source code generated by jvider v1.2.3 UNREGISTERED version.
// http://www.jvider.com/
//======================================================
import java.net.ServerSocket;

import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.SwingConstants;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JScrollBar;
//import javax.swing.JTextField;

//import java.awt.GridBagConstraints;
//import java.awt.GridBagLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Color;
//import java.awt.Point;


//import java.util.StringTokenizer;
import java.util.Vector;
import java.util.Hashtable;

//import java.lang.NumberFormatException;





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
public class ServerGUI extends JFrame implements Protocol, Constants  {


   /**
    * the scroll pane
    */
    private JScrollPane sp;

   /**
    * event listener
    */
    private ServerGUIControl event;

   /**
    * the gui
    */
    private static ServerGUI theServerGUI;


   /**
    * a JPanel
    */
    private Container pnFrame;

    
   /**
    * a Jbutton
    */
    private JButton btClose;


   /**
    * a Jbutton
    */
    private JButton btUserInfo;


   /**
    * a Jbutton
    */
    private JButton btGameInfo;
    

   /**
    * a TextArea
    */
    private JTextArea taConsel;


   /**
    * a label
    */
    private JLabel lbUsersLabel;


   /**
    * a label
    */
    private JLabel lbGamesLabel;


   /**
    * a label
    */
    private JLabel lbConselLabel;


   /**
    * a list
    */
    private JList lsUsers;

   /**
    * a list
    */
    private JList lsGames;

   /**
    * the gatekeeper that does most of the work
    * actually more like %20
    */
    private GateKeeper gateKeeper = null;
    
    
/***************************************************************************/
/*                                MODIFIERS                                */
/***************************************************************************/



   /**
    * Modifier
    * @param a JScrollPane
    */
    public void setSp(JScrollPane a) {
        sp = a;
    }


   /**
    * Modifier
    * @param a ServerGUIControl
    */
    public void setEvent(ServerGUIControl a) {
        event = a;
    }

   /**
    * Modifier
    * @param a JButton
    */
    public void setBtClose(JButton a) {
        btClose = a;
    }



   /**
    * Modifier
    * @param a JButton
    */
    public void setBtUserInfo(JButton a) {
        btUserInfo = a;
    }


   /**
    * Modifier
    * @param a JButton
    */
    public void setBtGameInfo(JButton a) {
        btGameInfo = a;
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
    * @param a JLabel
    */
    public void setLbConselLabel(JLabel a) {
        lbConselLabel = a;
    }


   /**
    * Modifier
    * @param a JLabel
    */
    public void setLbGamesLabel(JLabel a) {
        lbGamesLabel = a;
    }


   /**
    * Modifier
    * @param a JLabel
    */
    public void setLbUsersLabel(JLabel a) {
        lbUsersLabel = a;
    }


   /**
    * Modifier
    * @param a JList
    */
    public void setLsGames(JList a) {
        lsGames = a;
    }


   /**
    * Modifier
    * @param a JList
    */
    public void setLsUsers(JList a) {
        lsUsers = a;
    }


   /**
    * Modifier
    * @param a JPanel
    */
    public void setPnFrame(Container a) {
        pnFrame = a;
    }


   /**
    * Modifier
    * @param a JTextArea
    */
    public void setTaConsel(JTextArea a) {
        taConsel = a;
    }


   /**
    * Modifier
    * @param a ServerGUI
    */
    public void setTheServerGui(ServerGUI a) {
        theServerGUI = a;
    }




/***************************************************************************/
/*                                ACCESSORS                                */
/***************************************************************************/


   /**
    * Accessor
    * @return JScrollPane
    */
    public JScrollPane getSp() {
        return sp;
    }





   /**
    * Accessor
    * @return ServerGUIControl
    */
    public ServerGUIControl getEvent() {
        return event;
    }


   /**
    * Accessor
    * @return JButton
    */
    public JButton getBtClose() {
        return btClose;
    }

   /**
    * Accessor
    * @return JButton
    */
    public JButton getBtUserInfo() {
        return btUserInfo;
    }


   /**
    * Accessor
    * @return JButton
    */
    public JButton getBtGameInfo() {
        return btGameInfo;
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
    * @return JLabel
    */
    public JLabel getLbConselLabel() {
        return lbConselLabel;
    }


   /**
    * Accessor
    * @return JLabel
    */
    public JLabel getLbGamesLabel() {
        return lbGamesLabel;
    }


   /**
    * Accessor
    * @return JLabel
    */
    public JLabel getLbUsersLabel() {
        return lbUsersLabel;
    }


   /**
    * Accessor
    * @return JList
    */
    public JList getLsGames() {
        return lsGames;
    }


   /**
    * Accessor
    * @return JList
    */
    public JList getLsUsers() {
        return lsUsers;
    }


   /**
    * Accessor
    * @return JPanel
    */
    public Container getPnFrame() {
        return pnFrame;
    }


   /**
    * Accessor
    * @return JTextArea
    */
    public JTextArea getTaConsel() {
        return taConsel;
    }


   /**
    * Accessor
    * @return ServerGUI
    */
    public ServerGUI getTheServerGui() {
        return theServerGUI;
    }








/***************************************************************************/
/*                      METHODS THAT DO REAL STUFF                         */
/***************************************************************************/
    /**
    * starts the whole thing into motion
    * @param args command line input may be used for port and such
    */
    public static void main(String args[]) {
        try {
            UIManager.setLookAndFeel(
                UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (ClassNotFoundException e) {
            int i = 0; //for checkstyle
        } catch (InstantiationException e) {
            int i = 0; //for checkstyle
        } catch (IllegalAccessException e) {
            int i = 0; //for checkstyle
        } catch (UnsupportedLookAndFeelException e) {
            int i = 0; //for checkstyle
        }
        theServerGUI = new ServerGUI(1);
    } 

   /**
    * Constructor
    */
    public ServerGUI() {
        System.out.println("hey");
    }


/**
 * Constructor
 * @param junitSucks This was added cause
 * stupid junit would make a ServerGUI
 * and then try to open a GUI
 * when all we wanted was just an instance
 * of ServerGUI so I made the above 
 * constructor to make a worthless
 * ServerGUI to pasify junit
 *
 * Junit sucks.
 */
    public ServerGUI(int junitSucks) {
        super("Scorched Earth Server");

        pnFrame = new JPanel();
        pnFrame.setLayout(new BorderLayout());
    
        btClose = new JButton("Close");
        btGameInfo = new JButton("Game Info");
        btUserInfo = new JButton("User Info");

        JPanel temp1 = new JPanel();
        temp1.setLayout(new BorderLayout());
        temp1.add(btGameInfo, BorderLayout.WEST);
        temp1.add(btUserInfo, BorderLayout.EAST);
        temp1.add(btClose, BorderLayout.CENTER);

        pnFrame.add(temp1, BorderLayout.SOUTH);

        

        
        taConsel = new JTextArea(2, 10);
        taConsel.setEditable(false);
        taConsel.setBorder(BorderFactory.createLineBorder(Color.black));
        sp = new JScrollPane(taConsel);
        pnFrame.add(sp, BorderLayout.CENTER);
        
        
               
        lbUsersLabel = new JLabel("Users", SwingConstants.CENTER);
        //pnFrame.add(lbUsersLabel, BorderLayout.NORTH);

        lbGamesLabel = new JLabel("Games", SwingConstants.CENTER);
        //pnFrame.add(lbGamesLabel, BorderLayout.NORTH);

        lbConselLabel = new JLabel("Console", SwingConstants.CENTER);
        //pnFrame.add(lbConselLabel, BorderLayout.NORTH);

        JPanel temp = new JPanel();
        temp.setLayout(new BorderLayout());
        temp.add(lbUsersLabel, BorderLayout.EAST);
        temp.add(lbGamesLabel, BorderLayout.WEST);
        temp.add(lbConselLabel, BorderLayout.CENTER);
        
        
        pnFrame.add(temp, BorderLayout.NORTH);




        String []sUsers = {"EMPTY"};
        lsUsers = new JList(sUsers);
        lsUsers.setBorder(BorderFactory.createLineBorder(Color.black));
        lsUsers.setFixedCellWidth(100);
        JScrollPane jsp1 = new JScrollPane(lsUsers);
        pnFrame.add(jsp1, BorderLayout.EAST);

        String []sGames = {"EMPTY"};
        lsGames = new JList(sGames);
        lsGames.setSize(100, 400);
        lsGames.setBorder(BorderFactory.createLineBorder(Color.black));
        lsGames.setFixedCellWidth(100);
        JScrollPane jsp2 = new JScrollPane(lsGames);
        pnFrame.add(jsp2, BorderLayout.WEST);

        //setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        
        



        eventHandling();
        
        setSize(600, 400);
        


        setContentPane(pnFrame);
        setVisible(true);        
        ServerSocket ss = null;

        while (true) {
            String p = JOptionPane.showInputDialog(pnFrame,
                "Port Number", 
                "2335");
            
            try {
                int i = Integer.parseInt(p);
                ss = new ServerSocket(i);
                break;
            } catch (IOException e) {
                JOptionPane.showMessageDialog(pnFrame, "Bad port number");
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(pnFrame, "Bad port number");
            }
                
        }

        setVisible(true);      

        gateKeeper = new GateKeeper(ss, this, event);
        gateKeeper.start();
    } 
    
    
    
    
    
    
    
    
    
   /**
    * sets up all the event handling stuff
    */    
    public void eventHandling() {
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                
                
                gateKeeper.shutDown();
                System.exit(0);
            }
        });
        
        
        
        event = new ServerGUIControl(this, gateKeeper);
                
        //newGameItem.addActionListener( event );
        btClose.addActionListener(event);
        btGameInfo.addActionListener(event);
        btUserInfo.addActionListener(event);
        
    }
    
    


   /**
    * updates user list
    * @param users Vector
    */
    public void updateUserList(Vector users) {
        lsUsers.setListData(users) ;
    }



   /**
    * updates games list
    * @param games Vector
    */
    public void updateGameList(Vector games) {
        lsGames.setListData(games) ;
    }



   /**
    * updates consel
    * @param msg String
    */
    public void updateConsel(String msg) {
        taConsel.append(msg + "\n");
        
        
        JScrollBar jsb = sp.getVerticalScrollBar();
        jsb.setValue(jsb.getMaximum());

    }
    
    
   /**
    *
    * displays users info
    */
    public void displayUserInfo() {
        Object o = lsUsers.getSelectedValue();
        if (o != null) {
            String user = o.toString();
            Hashtable h;
            h = gateKeeper.getProfiles();
            Profile p = (Profile) h.get(user);             
            if (p != null) {
                JOptionPane.showMessageDialog(pnFrame, "Name: " 
                                          + p.getUserName() + "\n"
                                          + "Password: "
                                          + p.getPassword() + "\n"
                                          + "Money: $"
                                          + p.getMoney());
            }
        }
    }


   /**
    * displays game info
    */
    public void displayGameInfo() {
        Object o = lsGames.getSelectedValue();
        if (o != null) {
            String game = o.toString();
            Hashtable h;
            h = gateKeeper.getGameMasters();
            GameMaster p = (GameMaster) h.get(game);             
            if (p != null) {   
                JOptionPane.showMessageDialog(pnFrame, p.getGameParams()
                                              + "\nPlayers in game: "
                                              + p.getServerThreads().size()
                                              + "\nIs game started: "
                                              + p.getIsGameStarted());
            }
        }
    }        
        
       
    
} 
