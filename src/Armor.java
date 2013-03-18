/**
 * Inventory.java, the data structure holding
 * all public inventory items for a tank
 * @author Team QERM
 */
public class Armor implements Constants {

  /**
   * The array of all the defensive inventory
   */
  private int[] armor;

  /**
   * default constructor
   */
  public Armor() {
    armor = new int[ARMOR.length];
  }
  
  /**
   * Accessor for Sandbags
   * @return sandbags
   */
  public int getSandbags() {
    return armor[SAND_BAGS];
  }

  /**
   * Mutator for sandbags
   * @param newSandbags the new armor1 value
   */
  public void setSandbags(int newSandbags) {
    armor[SAND_BAGS] = newSandbags;
  }

  /**
   * Accessor for camoPaint
   * @return camoPaint
   */
  public int getCamoPaint() {
    return armor[CAMO_PAINT];
  }

  /**
   * Mutator for camoPaint
   * @param newCamoPaint the new camoPaint value
   */
  public void setCamoPaint(int newCamoPaint) {
    armor[CAMO_PAINT] = newCamoPaint;
  }

  /**
   * Accessor for activeArmor
   * @return activeArmor
   */
  public int getActiveArmor() {
    return armor[ACTIVE_ARMOR];
  }

  /**
   * Mutator for activeArmor
   * @param newActiveArmor the new armor1 value
   */
  public void setActiveArmor(int newActiveArmor) {
    armor[ACTIVE_ARMOR] = newActiveArmor;
  }
  
  /**
   * gets the value of a given index of the array
   * @param index the array index, use an Armor Constant
   * @return the value at that index in the array
   */
  public int getInvItem(int index) {
    if ((index < armor.length) && (index >= 0)) {
      return armor[index];
    }
    return -1;
  }

  /**
   * sets the value of a given index of the array
   * @param index the array index, use an Armor Constant
   * @param newVal the new value to set that index to
   */
  public void setInvItem(int index, int newVal) {
    if ((index < armor.length) && (index >= 0)) {    
      armor[index] = newVal;
    }
  }
}



