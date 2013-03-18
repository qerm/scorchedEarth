import javax.swing.JFrame;
import javax.swing.JPanel;
//import javax.swing.JButton;
import java.awt.Container;
import java.awt.GridLayout;
import javax.swing.JOptionPane;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//import java.awt.Dimension;

import java.util.Vector;
import java.util.StringTokenizer;
import java.util.Random;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;


/**
 * GameView.java The in-game window.  
 * It will display the shop and the 
 * battlefield at various times in 
 * the game.
 * @author Team QERM
 */
public class GameView implements Protocol, 
                 Constants, ActionListener {



 /**
   * is it my turn
   */  
  private boolean isMyTurn = false;




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
   * the content pane
   */
  private JPanel currentPanel = null;
  
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
   * the list of players
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
   * the ClientThread
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
   * the spawning GameChatWindow
   */
  private GameChatWindow gameChat;
  
  /**
   * Get the GameChat value.
   * @return the GameChat value.
   */
  public GameChatWindow getGameChat() {
    return gameChat;
  }

  /**
   * Set the GameChat value.
   * @param newGameChat The new GameChat value.
   */
  public void setGameChat(GameChatWindow newGameChat) {
    this.gameChat = newGameChat;
  }

  /**
   * will hold theFrame's content pane
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
   * holds the currentRound
   */
  private int currentRound = 0;
  
  /**
   * Get the CurrentRound value.
   * @return the CurrentRound value.
   */
  public int getCurrentRound() {
    return currentRound;
  }

  /**
   * Set the CurrentRound value.
   * @param newCurrentRound The new CurrentRound value.
   */
  public void setCurrentRound(int newCurrentRound) {
    this.currentRound = newCurrentRound;
  }

  /**
   * the game Parameters
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
   * random seed gen
   */
  private Random seedGen;

  /**
   * Get the SeedGen value.
   * @return the SeedGen value.
   */
  public Random getSeedGen() {
    return seedGen;
  }

  /**
   * Set the SeedGen value.
   * @param newSeedGen The new SeedGen value.
   */
  public void setSeedGen(Random newSeedGen) {
    this.seedGen = newSeedGen;
  }

  /**
   * the gameModel
   */
  private GameModel gameModel;
  
  /**
   * Get the value of gameModel.
   * @return value of gameModel.
   */
  public GameModel getGameModel() {
    return gameModel;
  }
  
  /**
   * Set the value of gameModel.
   * @param v  Value to assign to gameModel.
   */
  public void setGameModel(GameModel  v) {
    this.gameModel = v;
  }
  


  /**
   * default constructor. JUNITING only
   */
  public GameView() {
    //    this(null, null, null);
  }

  /**
   * constructor for GameView
   * @param newClient the ClientThread
   * @param newGameChat the spawning GameChatWindow
   * @param newGParam the new Game Parameters
   */
  public GameView(ClientThread newClient, 
          GameChatWindow newGameChat,
          GameParameters newGParam) {
    client = newClient;
    client.setGv(this);
    gameChat = newGameChat;
    playerList = gameChat.getPlayerList();    
    gParam = newGParam;
    seedGen = new Random(gParam.getRandomSeed());
    init();
    operationScorchedEarthFreedom();
  }



  /**
   * inits everything
   */
  private void init() {
    
    
    theFrame = new JFrame(gParam.getName());
    c = theFrame.getContentPane();
    c.setLayout(new GridLayout(1, 1));
    currentPanel = new JPanel();
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

    LocalTank t = null;
    for (int i = 0; i < playerList.size(); i++) {
      if (playerList.get(i) instanceof LocalTank) {
    t = (LocalTank) playerList.get(i);
    t.setMoney(gParam.getStartingMoney());
      }
    }
  }
  

  /**
   * logs out the user when this window is closed
   */
  public void logoutUser() {
    theFrame.setVisible(false);
    theFrame.dispose();
    gameChat.leavingGameView();
    if (currentPanel instanceof GamePanel) {
        ((GamePanel) currentPanel).killTimer();
    }
  }


  /**
   * logs out the user when this window is closed
   * @param reason String
   */
  public void logoutUser(String reason) {
    JOptionPane.showMessageDialog(theFrame, reason);    
    theFrame.setVisible(false);
    theFrame.dispose();
    gameChat.leavingGameView();
    if (currentPanel instanceof GamePanel) {
        ((GamePanel) currentPanel).killTimer();
    }
  }







  /**
   * the driver of the game
   */
  public void operationScorchedEarthFreedom() {
    currentRound++;
    System.out.println("currentRound num = " + currentRound);
    if (currentRound < (gParam.getNumRounds() + 1)) {
      doShop();
    } else {
        System.out.println("game is over!!!!!!!!!!!!!!!!1");
    }
        
  }
  

  /**
   * executes the shop part of this round
   */
  private void doShop() {
    if (currentPanel instanceof GamePanel) {
        ((GamePanel) currentPanel).killTimer();
    }
    LocalTank t = null;
    for (int i = 0; i < playerList.size(); i++) {
      if (playerList.get(i) instanceof LocalTank) {
    t = (LocalTank) playerList.get(i);
      }
    }

    c.invalidate();
    c.remove(currentPanel);
    currentPanel = new ShopPanel(client, t, this);
    
    JMenuBar menuBar = new JMenuBar();
    JMenu sounds = new JMenu("Sounds");
    JMenuItem soundOn = new JMenuItem("Sound On");
    JMenuItem soundOff = new JMenuItem("Sound Off");

    soundOn.addActionListener(this);
    soundOff.addActionListener(this);
    
    sounds.add(soundOn);
    sounds.add(soundOff);
    
    isMyTurn = false;
    
    theFrame.setJMenuBar(menuBar); 

    c.add(currentPanel);
    theFrame.pack();
    c.validate();
  }

  /**
   * updates the inventory of some remoteTank
   * @param message the String recieved by the server
   */
  public void updatePublicInventory (String message) {
    StringTokenizer st = new StringTokenizer(message, DELIM);
    st.nextToken();
    String tName = st.nextToken();
    Tank temp = null;
    Tank t = null;
    for (int i = 0; i < playerList.size(); i++) {
      temp = (Tank) playerList.get(i);
      if (temp.getName().equals(tName)) {
    t = temp;
      }
    }
    if (t != null) {
      while (st.hasMoreTokens()) {
    int type = -1, qty = -1;
    try {
      type = Integer.parseInt(st.nextToken());
      qty = Integer.parseInt(st.nextToken());
    } catch (NumberFormatException nfe) {
        int i = 0;
    }
    t.getArmor().setInvItem(type, qty);
      }
    }
  }

  /**
   * executres the actual game playing part of the round
   */
  public void doRound() {
    Tank temp;
    for (int i = 0; i < playerList.size(); i++) {
      temp = (Tank) playerList.get(i);
      temp.setIsDead(false);
      temp.setHp(100);
      temp.setXPos(-1);
      if (temp instanceof LocalTank) {
        float money = (int) (((LocalTank) temp).getMoney());
        float interest = (float) gParam.getInterestRate();
        
        money = (float) (money * (float) (1.0 + (interest / 100.0)));
        
        ((LocalTank) temp).setMoney((int) money);
      }
    }    
    
    gameModel = initGameBoard();
    c.invalidate();
    c.remove(currentPanel);
    currentPanel = new GamePanel(client, gameModel, this);


    if (isMyTurn) {
        ((GamePanel) currentPanel).setIsMyTurn(true);
        isMyTurn = false;
    }



    
    
    /*game menu crap goes here*/
    JMenuBar menuBar = new JMenuBar();
    JMenu sounds = new JMenu("Sounds");
    JMenu taunts = new JMenu("Taunts");
    JMenu playerInfo = new JMenu("Player Info");
    
    JMenuItem goNDown = new JMenuItem("Ah crap");     
    JMenuItem die = new JMenuItem("You suck");
    JMenuItem eat = new JMenuItem("I'm good");
    JMenuItem mom = new JMenuItem("Yipie");
    
    JMenuItem soundOn = new JMenuItem("Sound On");
    JMenuItem soundOff = new JMenuItem("Sound Off");
    
    Vector pList = gameModel.getPlayerList();
    Tank tank;
    for (int i = 0; i < pList.size(); i++) {
        tank = (Tank) pList.get(i);
        JMenuItem jmi = new JMenuItem(tank.getName());
        jmi.addActionListener(this);
        playerInfo.add(jmi);
    }
    
    goNDown.addActionListener(this);
    die.addActionListener(this);
    eat.addActionListener(this);
    mom.addActionListener(this);
    soundOn.addActionListener(this);
    soundOff.addActionListener(this);
    
    sounds.add(soundOn);
    sounds.add(soundOff);
    
    taunts.add(goNDown);    
    taunts.add(die);
    taunts.add(eat);
    taunts.add(mom);
    
    menuBar.add(sounds);
    menuBar.add(taunts);
    menuBar.add(playerInfo);
    
    theFrame.setJMenuBar(menuBar); 
    
    /*game menu crap stops here*/    
    c.add(currentPanel);
    theFrame.pack();
    c.validate();
    
  }

  /**
   * makes the board
   * @return GameModel
   */
  private GameModel initGameBoard() {
    boolean isCave = false;
    if (gParam.getIsCave()) {
      isCave = seedGen.nextBoolean();
    } 
    GameModel retval = new GameModel(seedGen.nextLong(),
                     playerList,
                     gParam,
                     isCave);
    retval.setGParam(gParam);
    if (gParam.getWindBehavior() != NO_WIND) {
      retval.setWindSpeed((seedGen.nextInt(2 * gParam.getMaxWind())) 
             - gParam.getMaxWind());
    }  

    String wallBehavior = "" + gParam.getWallBehavior();
    if (wallBehavior == "0") {
      retval.setWallType(NO_WALLS);
    } else {
      int walls;
      boolean wtype[] = new boolean[4];
      while (wallBehavior.length() != 4) {
    wallBehavior = "0" + wallBehavior;
      }
      for (int i = 0; i < wallBehavior.length(); i++) {
    if (wallBehavior.charAt(i) == '1') {
      wtype[i] = true;
    } else {
      wtype[i] = false;
    }
      }
      do {
    walls = seedGen.nextInt(4);
      } while (wtype[walls] == false);
      retval.setWallType((int) Math.pow(10, (3 - walls)));
      System.out.println("level wall type:" + retval.getWallType());
    }
    return retval;
 }
  
    
  /**
   * Called by ClientThread.  makes the actionListener
   * and keyListener in gamePanel do things when 
   * buttons are clicked
   */
  public void doTurn() {
    if (currentPanel instanceof GamePanel) {
        GamePanel gp = (GamePanel) currentPanel;
        gp.setIsMyTurn(true);
    } else {
        System.out.println("not a game panel do turn ignored!!!!!!");
        isMyTurn = true; //need to make it so this will
                         //set the turn of the person
    }
  }

  /**
   * called by ClientThread when someone's turret has moved
   * @param message String
   */
  public void animateTurret(String message) {
    StringTokenizer st = new StringTokenizer(message, DELIM);
    st.nextToken();
    String name = st.nextToken();
    int angle;
    try {
      angle = Integer.parseInt(st.nextToken());
    } catch (NumberFormatException nfe) {
      return;
    }
    Tank temp;
    for (int i = 0; i < playerList.size(); i++) {
      temp = (Tank) playerList.get(i);
      if (temp.getName().equals(name)) {
           temp.setTurretAngle(angle);
           if (currentPanel instanceof GamePanel) {
               ((GamePanel) currentPanel).repaint();
           }
      }
    }
  }
  
  /**
   * called by ClientThread when someone fired
   * @param message String
   */
  public void fire(String message) {
    StringTokenizer st = new StringTokenizer(message, DELIM);
    String name;
    int power, angle, type, x = -1, y = -1;
    Tank shooter;
    try {
      st.nextToken();
      name = st.nextToken();
      Shot toAdd;
      angle = Integer.parseInt(st.nextToken());
      power = Integer.parseInt(st.nextToken());
      type = Integer.parseInt(st.nextToken());
      for (int i = 0; i < playerList.size(); i++) {
    shooter = (Tank) playerList.get(i);
    if (name.equals(shooter.getName())) {
      System.out.println(shooter.getName());
      int dx = (int) 
            (.75 * TANK_WIDTH 
         * Math.cos(Math.toRadians((double) angle)));
      int dy = (int) 
            (.75 * TANK_WIDTH 
         * Math.sin(Math.toRadians((double) angle)));
      x = shooter.getXPos() + dx + (TANK_WIDTH / 2);
      y = shooter.getYPos() + 5 + dy;
    }
      }
      switch (type) {
      case MISSILE:
    toAdd = new Missile(power, angle, x, y, gameModel);
    ScorchedAmp.playSound(FIRE1_SOUND);
    break;
      case NUKE:
    toAdd = new Nuke(power, angle, x, y, gameModel);
    ScorchedAmp.playSound(FIRE2_SOUND);
    break;
      case MIRV:
    toAdd = new Mirv(power, angle, x, y, gameModel);
    ScorchedAmp.playSound(MIRV_SHOOT);
    break;
      case SPIDER:
    toAdd = new Spider(power, angle, x, y, gameModel);
    ScorchedAmp.playSound(FIRE3_SOUND);
    break;
      case TOMAHAWK:      
    toAdd = new Tomahawk(power, angle, x, y, gameModel);
    ScorchedAmp.playSound(TOMAHAWK_FIRE);
    toAdd.setShooter(name);
    ((Tomahawk) toAdd).setUp();
    break;
      default:
      ScorchedAmp.playSound(FIRE4_SOUND);
    toAdd = new BabyMissile(power, angle, x, y, gameModel);
      }
      toAdd.setShooter(name);
      gameModel.newShot(toAdd);
    } catch (Exception e) {
        int i = 0;
    }
  }



  /**
   * doTaunt
   * @param tauntNum int
   */
  public void doTaunt(int tauntNum) {
    ScorchedAmp.playSound(tauntNum);
  }
  
  

  /**
   * called when someone plays with the menus
   * @param ae Action event
   */  
  public void actionPerformed(ActionEvent ae) {
        
        if (ae.getActionCommand().equals("Sound On")) {
            ScorchedAmp.setIsSoundOn(true);
            

            
        } else if (ae.getActionCommand().equals("Ah crap")) {            
            client.sendTaunt(TAUNT1_SOUND);
        } else if (ae.getActionCommand().equals("You suck")) {
            client.sendTaunt(TAUNT2_SOUND);
        } else if (ae.getActionCommand().equals("I'm good")) {
            client.sendTaunt(TAUNT3_SOUND);
        } else if (ae.getActionCommand().equals("Yipie")) {
            client.sendTaunt(TAUNT4_SOUND);



        } else if (ae.getActionCommand().equals("Sound Off")) {
            ScorchedAmp.setIsSoundOn(false);
        } else {
            Vector v = gameModel.getPlayerList();
            Tank tank = null;
            boolean found = false;
            for (int i = 0; i < v.size(); i++) {
                tank = (Tank) v.get(i);
                if (ae.getActionCommand().equals(tank.getName())) {
                    found = true;
                    break;
                }
            }
            
            if (found) {
                Armor arm = tank.getArmor();
                JOptionPane.showMessageDialog(theFrame, "Player: "
                    + tank.getName()
                    + "\nColor: "
                    + STRING_COLORS[tank.getColor()]
                    + "\nHealth: "
                    + tank.getHp()
                    + "\nActive Armor: "
                    + arm.getActiveArmor()
                    + "\nSandbags: "
                    + arm.getSandbags()
                    + "\nCamo paint: "
                    + arm.getCamoPaint());
                
            } else {
                JOptionPane.showMessageDialog(theFrame, ae.getActionCommand()
                    + " is no longer in the game");
            }
        }
                    
    }
    
    
  /**
   * handles a player randomly leaving the game
   * @param userName string
   */  
    public void userLeft(String userName) {
        
        Tank temp;
        int i;
        
        for (i = 0; i < playerList.size(); i++) {
            temp = (Tank) playerList.get(i);
            if ((temp.getName()).equals(userName)) {
                playerList.remove(i);
                break;
            }
        }
        
        if (currentPanel instanceof GamePanel) {
            currentPanel.repaint();
        }
            
            
            
    }

}








