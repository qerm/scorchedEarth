/**
 * Tank.java, the abstract class for all tanks.
 *
 * @author Team QERM
 */
public abstract class Tank implements Constants {
  
  /**
   * Name of user associated with this tank
   */
  private String name;

  /**
   * Name of the user asscoiated with the tank that killed you
   */
  private String killer;
  
  /**
   * Color associated with this tank
   */
  private int color;

  /**
   * the X Position on the map associated with this tank
   */
  private int xpos;

  /**
   * The Y Position on the map associated with this tank
   */
  private int ypos;

  /**
   * The current turret angle of this tank, so that the GUI
   * can animate properly.
   */
  private int turretAngle;

  /**
   * Tells if this tank is dead or not, so that if this is true
   */
  private boolean isDead;

  /**
   * The hit points of this tank
   */
  private int hp;

  /**
   * The public inventory for this tank, including armor and patriots
   */
  private Armor armor;

  /**
   * Accessor for color
   * @return the integer constant of this tank's color
   */
  public int getColor() {
    return color;
  }

  /**
   * Mutator for color
   * @param  newColor the new color value
   */
  public void setColor(int newColor) {
    color = newColor;
  }

  /**
   * Accessor for xpos
   * @return xpos
   */
  public int getXPos() {
    return xpos;
  }

  /**
   * Accessor for ypos
   * @return int
   */
  public int getYPos() {
    return ypos;
  }

  /**
   * Mutator for xpos (should only be called once, ideally)
   * @param newXpos the new xpos value
   */
  public void setXPos(int newXpos) {
    xpos = newXpos;
  }

  /**
   * Mutator for ypos
   * @param newYpos int
   */
  public void setYPos(int newYpos) {
    ypos = newYpos;
  }

 /**
   * Accessor for turretAngle
   * @return turretAngle
   */
  public int getTurretAngle() {
    return turretAngle;
  }

  /**
   * Mutator for turretAngle
   * @param newAngle the new turretAngle value
   */
  public void setTurretAngle(int newAngle) {
    turretAngle = newAngle;
  }

  /**
   * Accessor for isDead
   * @return isDead.
   */
  public boolean getIsDead() {
    return isDead;
  }
  
  /**
   * Mutator for isDead
   * @param deadVal the new isDead value
   */
  public void setIsDead(boolean deadVal) {
    isDead = deadVal;
  }

  /**
   * Accessor for name
   * @return name
   */
  public String getName() {
    return name;
  }

  /**
   * Mutator for name
   * @param newName the new name value
   */
  public void setName(String newName) {
    name = newName;
  }

  /**
   * Accessor for armor
   * @return armor
   */
  public Armor getArmor() {
    return armor;
  }

  /**
   * Mutator for inventory
   * @param newArmor the new inventory value
   */
  public void setArmor(Armor newArmor) {
    armor = newArmor;
  }
  
  /**
   * Accessor for hp
   * @return hp
   */
  public int getHp() {
    return hp;
  }

  /**
   * Mutator for hp
   * @param newHP the new hp value
   */
  public void setHp(int newHP) {
    hp = newHP;
    if (hp < 0) {
      if (armor.getActiveArmor() > 0) {
    System.out.println("active armor adding " + ACTIVE_ARMOR_HP + " hp");
    armor.setActiveArmor(armor.getActiveArmor() - 1);
    hp += ACTIVE_ARMOR_HP;
      } else if (armor.getSandbags() > 0) {
    System.out.println("sandbags adding " + SAND_BAGS_HP + " hp");
    armor.setSandbags(armor.getSandbags() - 1);
    hp += SAND_BAGS_HP;
      } else if (armor.getCamoPaint() > 0) {
    System.out.println("camo paint adding " + CAMO_PAINT_HP + " hp");
    armor.setCamoPaint(armor.getCamoPaint() - 1);
    hp += CAMO_PAINT_HP;
      }
    }
    if (hp < 0) {
      isDead = true;
    }
  }

  /**
   * Get the Killer value.
   * @return the Killer value.
   */
  public String getKiller() {
    return killer;
  }

  /**
   * Set the Killer value.
   * @param newKiller The new Killer value.
   */
  public void setKiller(String newKiller) {
    this.killer = newKiller;
  }

  


 /**
  * Default constructor
  */
  public Tank() {
    this("Stubby", PINK);
  }

  /**
   * Main constructor for tanks
   * @param newName the name of this tank
   * @param newColor the color of this tank
   */
  public Tank(String newName, int newColor) {
    isDead = false;
    xpos = -1;
    turretAngle = -1;
    hp = 100;
    name = newName;
    color = newColor;
    armor = new Armor();
  }
  
  /**
   * toString method for this object, just in case
   * @return String representation of this object
   */
  public String toString() {
    return name + "'s Tank, with " + hp + "hp";
  }

  /**
   * equals method for this object.  Since by the
   * rules of protocol everyone must have a unique user
   * name, that's all we have to compare
   * @param o the object to compare to
   * @return true if equal, false otherwise
   */
  public boolean equals(Object o) {
    if (o instanceof Tank) {
      Tank t = (Tank) o;
      return this.name.equals(t.getName());
    } else {
      return false;
    }
  }

}
  






