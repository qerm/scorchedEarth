//import javax.swing.JFrame;
//import javax.swing.UIManager;
//import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import javax.swing.JTabbedPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Dimension;
import javax.swing.JScrollPane;
//import javax.swing.ScrollPaneLayout;
/**
 * @author  Administrator
 * @created April 16, 2003
 */
public class ShopPanel extends JPanel implements ActionListener,
                                                    Constants, Protocol {

  /**
   * The layout of the Shop panel
   */
  private GridBagLayout gbShopPanel;

  /**
   * holds the Weapon and Armor tabs
   */
  private JTabbedPane tbpShopTabs;
  
  /**
   * weapon tab
   */
  private JPanel pnWeaponPanel;
  
  /**
   * weapons to buy
   */
  private JPanel panePanelInShopWeapons;

/**
 * Weapons scroll pane
 */
  private JScrollPane paneInShopWeapons;
  
  /**
   * Weapons panel w/ scroll pane on it
   */
  private JPanel pnInShopWeapons;

  /**
   * weapons the user has
   */
  private JPanel panePanelInventoryWeapons;
  
  /**
   * User weapons scroll pane
   */
  private JScrollPane paneInventoryWeapons;
  
  /**
   * User weapons panel w/ scroll pane
   */
  private JPanel pnInventoryWeapons;
  
  /**
   * armor tab
   */
  private JPanel pnArmorPanel;
  
  /**
   * armor to buy
   */
  private JPanel panePanelInShopArmor;
  
  /**
   * armor panel w/ scroll pane
   */
  private JPanel pnInShopArmor;
  
  /**
   * armor scroll pane
   */
  private JScrollPane paneInShopArmor;
  
  /**
   * armor the user has
   */
  private JPanel panePanelInventoryArmor;
  
  /**
   * user armor scroll pane
   */
  private JScrollPane paneInventoryArmor;
  
  /**
   * user armor panel w/ scroll pane on it
   */
  private JPanel pnInventoryArmor;
 
  /**
   * the done button
   */
  private JButton btDone;
 
 /**
  * Sell weapons buttons
  */
  private JButton[] sellWeaponButtons = new JButton[WEAPONS.length];
  
  /**
   * Sell armor buttons
   */
  private JButton[] sellArmorButtons = new JButton[ARMOR.length];

  /**
   * the weapons available
   */
  private JButton[] weaponsButtons = 
    new JButton[WEAPONS.length];
  
  /**
   * Get the WeaponsButtons value.
   * @return the WeaponsButtons value.
   */
  public JButton[] getWeaponsButtons() {
    return weaponsButtons;
  }

  /**
   * Set the WeaponsButtons value.
   * @param newWeaponsButtons The new WeaponsButtons value.
   */
  public void setWeaponsButtons(JButton[] newWeaponsButtons) {
    this.weaponsButtons = newWeaponsButtons;
  }

  
  /**
   * the armor available
   */
  private JButton[] armorButtons = 
    new JButton[ARMOR.length];
  
  /**
   * Get the ArmorButtons value.
   * @return the ArmorButtons value.
   */
  public JButton[] getArmorButtons() {
    return armorButtons;
  }

  /**
   * Set the ArmorButtons value.
   * @param newArmorButtons The new ArmorButtons value.
   */
  public void setArmorButtons(JButton[] newArmorButtons) {
    this.armorButtons = newArmorButtons;
  }
  
  /**
   * a reference to Local Tank,
   * so ShopPanel can update the inventory
   */
  private LocalTank myTank;
  
  /**
   * Get the MyTank value.
   * @return the MyTank value.
   */
  public LocalTank getMyTank() {
    return myTank;
  }

  /**
   * Set the MyTank value.
   * @param newMyTank The new MyTank value.
   */
  public void setMyTank(LocalTank newMyTank) {
    this.myTank = newMyTank;
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
   * displays the armor the user has
   */
  private JLabel myArmor;
  
  /**
   * Get the MyArmor value.
   * @return the MyArmor value.
   */
  public JLabel getMyArmor() {
    return myArmor;
  }

  /**
   * Set the MyArmor value.
   * @param newMyArmor The new MyArmor value.
   */
  public void setMyArmor(JLabel newMyArmor) {
    this.myArmor = newMyArmor;
  }
  
  /**
   * displays the weapons the user has
   */
  private JLabel myWeapons;
  
  /**
   * Get the MyWeapons value.
   * @return the MyWeapons value.
   */
  public JLabel getMyWeapons() {
    return myWeapons;
  }

  /**
   * Set the MyWeapons value.
   * @param newMyWeapons The new MyWeapons value.
   */
  public void setMyWeapons(JLabel newMyWeapons) {
    this.myWeapons = newMyWeapons;
  }
  
  /** 
   * the GameView that is displaying this panel
   */
  private GameView gv;
  
  /**
   * Get the Gv value.
   * @return the Gv value.
   */
  public GameView getGv() {
    return gv;
  }

  /**
   * Set the Gv value.
   * @param newGv The new Gv value.
   */
  public void setGv(GameView newGv) {
    this.gv = newGv;
  }

  /**
   * state variable, changed after clicking done
   */
  private boolean waiting = false;
  
  /**
   * Get the value of waiting.
   * @return value of waiting.
   */
  public boolean isWaiting() {
    return waiting;
  }
  
  /**
   * Set the value of waiting.
   * @param v  Value to assign to waiting.
   */
  public void setWaiting(boolean  v) {
    this.waiting = v;
  }



  
  /**
   * default contructor for ShopPanel
   */
  public ShopPanel() {
    //    this(null, null, null);
  }

/**
 * initialized JPanels to be used by shoppanel
 */
private void initPanels() {
    
    this.setLayout(gbShopPanel);
    panePanelInShopWeapons = new JPanel();
    panePanelInShopArmor = new JPanel();
    panePanelInventoryArmor = new JPanel();
    panePanelInventoryWeapons = new JPanel();
}

  /**
   * constructor for ShopPanel 
   * @param newClient the ClientThread
   * @param newMyTank the LocalTank to change values from
   * @param newGv the GameView
   */
  public ShopPanel(ClientThread newClient, 
           LocalTank newMyTank,
           GameView newGv) {
    myTank = newMyTank;    
    client = newClient;
    gv = newGv;
    gbShopPanel = new GridBagLayout();
    GridBagConstraints gbcShopPanel = new GridBagConstraints();
    initPanels();
    tbpShopTabs = new JTabbedPane();
    pnWeaponPanel = new JPanel();
    GridBagLayout gbWeaponPanel = new GridBagLayout();
    GridBagConstraints gbcWeaponPanel = new GridBagConstraints();
    pnWeaponPanel.setLayout(gbWeaponPanel);
    //////////// Weapons in Shop Side ////////////////
    pnInShopWeapons = new JPanel();
    pnInShopWeapons.setBorder(BorderFactory.createTitledBorder("In Shop"));
    pnInShopWeapons.setLayout(new GridLayout(1, 1));
    panePanelInShopWeapons.setLayout(new GridLayout(WEAPONS.length, 1));
    for (int i = 0; i < weaponsButtons.length; i++) {
      weaponsButtons[i] = new JButton(WEAPONS[i] + ":"
                  + WEAPONS_QTY_PRICE[i][0] + "/ $" + WEAPONS_QTY_PRICE[i][1]);
      weaponsButtons[i].addActionListener(this);
      panePanelInShopWeapons.add(weaponsButtons[i]);
    }
    paneInShopWeapons = new JScrollPane(panePanelInShopWeapons);
    pnInShopWeapons.add(paneInShopWeapons);
    gbcWeaponPanel.gridx = 0;
    gbcWeaponPanel.gridy = 0;
    gbcWeaponPanel.gridwidth = 14;
    gbcWeaponPanel.gridheight = 20;
    gbcWeaponPanel.fill = GridBagConstraints.BOTH;
    gbcWeaponPanel.weightx = 1;
    gbcWeaponPanel.weighty = 1;
    gbcWeaponPanel.anchor = GridBagConstraints.NORTH;
    gbWeaponPanel.setConstraints(pnInShopWeapons, gbcWeaponPanel);
    pnWeaponPanel.add(pnInShopWeapons);
    ///////////////// Weapons in Inventory Side /////////////
    pnInventoryWeapons = new JPanel();
    pnInventoryWeapons.setBorder(BorderFactory.
        createTitledBorder("You Have Available To Sell"));
    pnInventoryWeapons.setLayout(new GridLayout(1, 1));
    panePanelInventoryWeapons.setLayout(new GridLayout(WEAPONS.length + 1, 1));
    myWeapons = new JLabel("Weapons: ");
    for (int i = 0; i < sellWeaponButtons.length; i++) {
      sellWeaponButtons[i] = new JButton(WEAPONS[i] + ":"
            + WEAPONS_QTY_PRICE[i][0] + "/ $" + WEAPONS_QTY_PRICE[i][1]);
      sellWeaponButtons[i].addActionListener(this);
      panePanelInventoryWeapons.add(sellWeaponButtons[i]);
    }
    JPanel segmentedWeaponPanel = new JPanel();
    segmentedWeaponPanel.setLayout(new FlowLayout());
    paneInventoryWeapons = new JScrollPane(panePanelInventoryWeapons);
    segmentedWeaponPanel.add(myWeapons);
    segmentedWeaponPanel.add(paneInventoryWeapons);
    pnInventoryWeapons.add(segmentedWeaponPanel);
    gbcWeaponPanel.gridx = 14;
    gbcWeaponPanel.gridy = 0;
    gbcWeaponPanel.gridwidth = 6;
    gbcWeaponPanel.gridheight = 20;
    gbcWeaponPanel.fill = GridBagConstraints.BOTH;
    gbcWeaponPanel.weightx = 1;
    gbcWeaponPanel.weighty = 1;
    gbcWeaponPanel.anchor = GridBagConstraints.NORTH;
    gbWeaponPanel.setConstraints(pnInventoryWeapons, gbcWeaponPanel);
    pnWeaponPanel.add(pnInventoryWeapons);
    tbpShopTabs.addTab("Offensive", pnWeaponPanel);
    ////////////// Armor in Shop Side ///////////////////////
    pnArmorPanel = new JPanel();
    GridBagLayout gbArmorPanel = new GridBagLayout();
    GridBagConstraints gbcArmorPanel = new GridBagConstraints();
    pnArmorPanel.setLayout(gbArmorPanel);
    pnInShopArmor = new JPanel();
    pnInShopArmor.setBorder(BorderFactory.createTitledBorder("In Shop"));
    pnInShopArmor.setLayout(new GridLayout(1, 1));
    panePanelInShopArmor.setLayout(new GridLayout(ARMOR.length, 1));
    for (int i = 0; i < armorButtons.length; i++) {
      armorButtons[i] = new JButton(ARMOR[i] + ":"
                    + ARMOR_QTY_PRICE[i][0] + "/ $" + ARMOR_QTY_PRICE[i][1]);
      armorButtons[i].addActionListener(this);
      panePanelInShopArmor.add(armorButtons[i]);
    }
    paneInShopArmor = new JScrollPane(panePanelInShopArmor);
    pnInShopArmor.add(paneInShopArmor);
    gbcArmorPanel.gridx = 0;
    gbcArmorPanel.gridy = 0;
    gbcArmorPanel.gridwidth = 14;
    gbcArmorPanel.gridheight = 20;
    gbcArmorPanel.fill = GridBagConstraints.BOTH;
    gbcArmorPanel.weightx = 1;
    gbcArmorPanel.weighty = 1;
    gbcArmorPanel.anchor = GridBagConstraints.NORTH;
    gbArmorPanel.setConstraints(pnInShopArmor, gbcArmorPanel);
    pnArmorPanel.add(pnInShopArmor);
    ///////////// Armor in Inventory Side //////////////////
    pnInventoryArmor = new JPanel();
    pnInventoryArmor.setBorder(BorderFactory.
                  createTitledBorder("You Have Available To Sell"));
    pnInventoryArmor.setLayout(new GridLayout(1, 1));
    panePanelInventoryArmor.setLayout(new GridLayout(ARMOR.length + 1, 1));
    myArmor = new JLabel("Defenses: ");
    panePanelInventoryArmor.add(myArmor);
    for (int i = 0; i < sellArmorButtons.length; i++) {
      sellArmorButtons[i] = new JButton(ARMOR[i] + ":" + ARMOR_QTY_PRICE[i][0]
                + "/ $" + ARMOR_QTY_PRICE[i][1]);
      sellArmorButtons[i].addActionListener(this);
      panePanelInventoryArmor.add(sellArmorButtons[i]);
    }
    JPanel segmentedArmorPanel = new JPanel();
    segmentedArmorPanel.setLayout(new FlowLayout());
    paneInventoryArmor = new JScrollPane(panePanelInventoryArmor);
    segmentedArmorPanel.add(myArmor);
    segmentedArmorPanel.add(paneInventoryArmor);
    pnInventoryArmor.add(segmentedArmorPanel);
    gbcArmorPanel.gridx = 14;
    gbcArmorPanel.gridy = 0;
    gbcArmorPanel.gridwidth = 6;
    gbcArmorPanel.gridheight = 20;
    gbcArmorPanel.fill = GridBagConstraints.BOTH;
    gbcArmorPanel.weightx = 1;
    gbcArmorPanel.weighty = 1;
    gbcArmorPanel.anchor = GridBagConstraints.NORTH;
    gbArmorPanel.setConstraints(pnInventoryArmor, gbcArmorPanel);
    pnArmorPanel.add(pnInventoryArmor);
    tbpShopTabs.addTab("Defensive", pnArmorPanel);
    gbcShopPanel.gridx = 0;
    gbcShopPanel.gridy = 0;
    gbcShopPanel.gridwidth = 20;
    gbcShopPanel.gridheight = 19;
    gbcShopPanel.fill = GridBagConstraints.BOTH;
    gbcShopPanel.weightx = 1;
    gbcShopPanel.weighty = 1;
    gbcShopPanel.anchor = GridBagConstraints.NORTH;
    gbShopPanel.setConstraints(tbpShopTabs, gbcShopPanel);
    this.add(tbpShopTabs);
    ///////////// Done Button ///////////////
    btDone = new JButton("Done");
    btDone.addActionListener(this);
    gbcShopPanel.gridx = 0;
    gbcShopPanel.gridy = 19;
    gbcShopPanel.gridwidth = 20;
    gbcShopPanel.gridheight = 1;
    gbcShopPanel.fill = GridBagConstraints.BOTH;
    gbcShopPanel.weightx = 1;
    gbcShopPanel.weighty = 0;
    gbcShopPanel.anchor = GridBagConstraints.NORTH;
    gbShopPanel.setConstraints(btDone, gbcShopPanel);
    this.add(btDone);
    setPreferredSize(new Dimension(480, 420));
    updateInv();
  }

/**
 * Action performed handler
 * @param e The thing that caused the event
 */
  public void actionPerformed(ActionEvent e) {
    if (!waiting) {
      if (e.getSource() == btDone) {
    String invt = "";
    for (int i = 0; i < armorButtons.length; i++) {
      invt += i + DELIM + myTank.getArmor().getInvItem(i);
      if (i < (armorButtons.length - 1)) {
        invt += DELIM;
      }
    }
    client.sendPublicInventory(invt);
    btDone.setText("Waiting for other players");
    waiting = true;
      }
      for (int i = 0; i < sellArmorButtons.length; i++) {
    if (e.getSource() == sellArmorButtons[i]) {        
      int currentVal = myTank.getArmor().getInvItem(i);
      if (currentVal >= ARMOR_SELL_QTY_PRICE[i][0]) { // have enuf to sell
        ScorchedAmp.playSound(SELL_SOUND);
        myTank.getArmor().setInvItem(i, currentVal 
                     - ARMOR_SELL_QTY_PRICE[i][0]);
        myTank.setMoney(myTank.getMoney() 
                + ARMOR_SELL_QTY_PRICE[i][1]);
        updateInv();
      }
    }
      }
      
      for (int i = 0; i < sellWeaponButtons.length; i++) {
    if (e.getSource() == sellWeaponButtons[i]) {
      
      int currentVal = myTank.getWeapons().getInvItem(i);
      if (currentVal >= WEAPONS_SELL_QTY_PRICE[i][0]) {
        ScorchedAmp.playSound(SELL_SOUND);
        myTank.getWeapons().setInvItem(i, currentVal
                       - WEAPONS_SELL_QTY_PRICE[i][0]);
        myTank.setMoney(myTank.getMoney() 
                + WEAPONS_SELL_QTY_PRICE[i][1]);
        updateInv();
      }
    }
      }
      
      for (int i = 0; i < weaponsButtons.length; i++) {
    if (e.getSource() == weaponsButtons[i]) {
      
      if (myTank.getMoney() >= WEAPONS_QTY_PRICE[i][1]) {
        ScorchedAmp.playSound(BUY_SOUND);
        if (i != BABY_MISSILE) {
          int currentVal = myTank.getWeapons().getInvItem(i);
          myTank.getWeapons().setInvItem(i, currentVal
                         + WEAPONS_QTY_PRICE[i][0]);
          myTank.setMoney(myTank.getMoney()
                  - WEAPONS_QTY_PRICE[i][1]);
        } 
        updateInv();
      }
    }
      }
      for (int i = 0; i < armorButtons.length; i++) {
    if (e.getSource() == armorButtons[i]) {
      
      if (myTank.getMoney() >= ARMOR_QTY_PRICE[i][1]) {
        ScorchedAmp.playSound(BUY_SOUND);
        int currentVal = myTank.getArmor().getInvItem(i);
        myTank.getArmor().setInvItem(i, currentVal 
                     + ARMOR_QTY_PRICE[i][0]);
        myTank.setMoney(myTank.getMoney()
                - ARMOR_QTY_PRICE[i][1]);   
        updateInv();
      }
    }
      }
    }
  }

  /**
   * updates the JLabels that display user's inventory
   */
  private void updateInv() {
    String a = "<html>Money: "
    + myTank.getMoney() + "<p>"
    + "Defenses: <p><ul>";
    
    String w = "<html>Money: "
    + myTank.getMoney() + "<p>"
      + "Weapons: <p><ul>";
    for (int i = 0; i < armorButtons.length; i++) {
      a = a + "<li>" + ARMOR[i] + ":";
      a = a + myTank.getArmor().getInvItem(i);
    }
    a = a + "</ul></html>";
    myArmor.setText(a);
    for (int i = 0; i < weaponsButtons.length; i++) {
      w = w + "<li>" + WEAPONS[i] + ":";
      if (i == BABY_MISSILE) {
    w = w + "infinite";
      } else {
    w = w + myTank.getWeapons().getInvItem(i);
      }
    }
    w = w + "</ul></html>";
    myWeapons.setText(w);
  }
}








