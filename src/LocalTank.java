/**
 * LocalTank.java, the tank object for this client
 * @author Team QERM
 */
public class LocalTank extends Tank {
  
  /**
   * all the private inventory of this player
   */
  private Weapons weapons;

  /**
   * the money of this player
   */
  private int money;

  /**
   * the power of the tank
   */
  private int power;

  /**
   * Accesor for weapons
   * @return weapons
   */
  public Weapons getWeapons() {
    return weapons;
  }

  /**
   * Mutator for weapons
   * @param newWeapons the new weapons value
   */
  public void setWeapons(Weapons newWeapons) {
    weapons = newWeapons;
  }

  /**
   * Accessor for money
   * @return money
   */
  public int getMoney() {
    return money;
  }

  /**
   * Mutator for money
   * @param newMoney the new money value
   */
  public void setMoney(int newMoney) {
    money = newMoney;
  }

  /**
   * Get the Power value.
   * @return the Power value.
   */
  public int getPower() {
    return power;
  }

  /**
   * Set the Power value.
   * @param newPower The new Power value.
   */
  public void setPower(int newPower) {
    this.power = newPower;
  }

  /**
   * default constructor
   * Junit only
   */
  public LocalTank() {
    this("STUBBY", 0);
  }
  
  /**
   * main constructor
   * @param newName the new name value
   * @param newColor the new Color value
   */
  public LocalTank(String newName, int newColor) {
    super(newName, newColor);
    money = 0;
    weapons = new Weapons();
  }
}






