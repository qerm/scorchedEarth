/**
 * Weapons.java, the data structure holding
 * all local inventory items for a tank
 * @author Team QERM
 */
public class Weapons implements Constants {

  /**
   * The array of all the defensive inventory
   */
  private int[] weapons;
  
  /**
   * Get the Weapons value.
   * @return the Weapons value.
   */
  public int[] getWeapons() {
    return weapons;
  }

  /**
   * Set the Weapons value.
   * @param newWeapons The new Weapons value.
   */
  public void setWeapons(int[] newWeapons) {
    this.weapons = newWeapons;
  }
  
  /**
   * constructor
   */
  public Weapons() {
    weapons = new int[WEAPONS.length];
  }

  /**
   * gets the value of a given index of the array
   * @param index the array index, use an Weapons Constant
   * @return the value at that index in the array
   */
  public int getInvItem(int index) {
    if ((index < weapons.length) && (index >= 0)) {
      return weapons[index];
    }
    return -1;
  }

  /**
   * sets the value of a given index of the array
   * @param index the array index, use an Weapons Constant
   * @param newVal the new value to set that index to
   */
  public void setInvItem(int index, int newVal) {
    if ((index < weapons.length) && (index >= 0)) {    
      weapons[index] = newVal;
    }
  }
}



