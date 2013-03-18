import java.util.Vector;
import java.util.Random;

/**
 * GameModel.java, the model for the game
 * @author Team QERM
 */
public class GameModel implements Constants {

  /**********************************************************\
  |*  INSTANCE VARS, ACCESSORS, MUTATORS                    *|
  \**********************************************************/

  private Random numGen;

  /**
   * Get the NumGen value.
   * @return the NumGen value.
   */
  public Random getNumGen() {
    return numGen;
  }

  /**
   * Set the NumGen value.
   * @param newNumGen The new NumGen value.
   */
  public void setNumGen(Random newNumGen) {
    this.numGen = newNumGen;
  }

  /**
   * A vector of Tanks, all the players
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
* the height map
*/
  private int[] heights;

  /**
   * Get the Heights value.
   * @return the Heights value.
   */
  public int[] getHeights() {
    return heights;
  }

  /**
   * Set the Heights value.
   * @param newHeights The new Heights value.
   */
  public void setHeights(int[] newHeights) {
    this.heights = newHeights;
  }

  /**
  * the cave height map
  */
  private int[] cave;

  /**
   * Get the Cave value.
   * @return the Cave value.
   */
  public int[] getCave() {
    return cave;
  }

  /**
   * Set the Cave value.
   * @param newCave The new Cave value.
   */
  public void setCave(int[] newCave) {
    this.cave = newCave;
  }

/**
* the roughness of the level
*/
  private float roughness;

  /**
   * Get the Roughness value.
   * @return the Roughness value.
   */
  public float getRoughness() {
    return roughness;
  }

  /**
   * Set the Roughness value.
   * @param newRoughness The new Roughness value.
   */
  public void setRoughness(float newRoughness) {
    this.roughness = newRoughness;
  }

  /**
   * A vector of all the shots in the air at the moment
   */
  private Vector currentShots;

  /**
   * Get the CurrentShots value.
   * @return the CurrentShots value.
   */
  public Vector getCurrentShots() {
    return currentShots;
  }

  /**
   * Set the CurrentShots value.
   * @param newCurrentShots The new CurrentShots value.
   */
  public void setCurrentShots(Vector newCurrentShots) {
    this.currentShots = newCurrentShots;
  }

  /**
  * the game Parameters
  */
  private GameParameters gParam;
  
  /**
   * Get the value of gParam.
   * @return value of gParam.
   */
  public GameParameters getGParam() {
    return gParam;
  }
  
  /**
   * Set the value of gParam.
   * @param v  Value to assign to gParam.
   */
  public void setGParam(GameParameters  v) {
    this.gParam = v;
  }

/**
* true if gameview needs to rerender terrain
*/
  private boolean rerender;
  
  /**
   * Get the value of rerender.
   * @return value of rerender.
   */
  public boolean getRerender() {
    return rerender;
  }
  
  /**
   * Set the value of rerender.
   * @param v  Value to assign to rerender.
   */
  public void setRerender(boolean  v) {
    this.rerender = v;
  }
  
  /**
   * terrain array, chunk1 comes from caves
   */
  private int[] dirtChunk1Top;
  
  /**
   * Get the value of dirtChunk1Top.
   * @return value of dirtChunk1Top.
   */
  public int[] getDirtChunk1Top() {
    return dirtChunk1Top;
  }
  
  /**
   * Set the value of dirtChunk1Top.
   * @param v  Value to assign to dirtChunk1Top.
   */
  public void setDirtChunk1Top(int[]  v) {
    this.dirtChunk1Top = v;
  }
  
  /**
   * terrain array
   */ 
  private int[] dirtChunk1Bottom;
  
  /**
   * Get the value of dirt_chuck1_bottom.
   * @return value of dirt_chuck1_bottom.
   */
  public int[] getDirtChunk1Bottom() {
    return dirtChunk1Bottom;
  }
  
  /**
   * Set the value of dirt_chuck1_bottom.
   * @param v  Value to assign to dirt_chuck1_bottom.
   */
  public void setDirtChunk1Bottom(int[]  v) {
    this.dirtChunk1Bottom = v;
  }
  
  /**
   * terrain array, chunk2 comes from bottom terrain
   */
  private int[] dirtChunk2Top;
  
  /**
   * Get the value of dist_chunk2_top.
   * @return value of dist_chunk2_top.
   */
  public int[] getDirtChunk2Top() {
    return dirtChunk2Top;
  }
  
  /**
   * Set the value of dist_chunk2_top.
   * @param v  Value to assign to dist_chunk2_top.
   */
  public void setDirtChunk2Top(int[]  v) {
    this.dirtChunk2Top = v;
  }
  
  /**
   * terrain array
   */
  private int[] dirtChunk2Bottom;
  
  /**
   * Get the value of dirtChunk2Bottom.
   * @return value of dirtChunk2Bottom.
   */
  public int[] getDirtChunk2Bottom() {
    return dirtChunk2Bottom;
  }
  
  /**
   * Set the value of dirtChunk2Bottom.
   * @param v  Value to assign to dirtChunk2Bottom.
   */
  public void setDirtChunk2Bottom(int[]  v) {
    this.dirtChunk2Bottom = v;
  }
  
  /**
   * the wind speed for this round
   */
  private int windSpeed = 0;
  
  /**
   * Get the value of windSpeed.
   * @return value of windSpeed.
   */
  public int getWindSpeed() {
    return windSpeed;
  }
  
  /**
   * Set the value of windSpeed.
   * @param v  Value to assign to windSpeed.
   */
  public void setWindSpeed(int  v) {
    this.windSpeed = v;
  }

  /**
   * called if the wind is to change each round
   */
  public void changeWind() {
    this.windSpeed = numGen.nextInt(2 * gParam.getMaxWind())
            - gParam.getMaxWind();
  }
  
  /**
  * whether or not the level's a cave
  */
  private boolean caveLevel;
  
  /**
   * Get the value of cave.
   * @return value of cave.
   */
  public boolean isCaveLevel() {
    return caveLevel;
  }
  
  /**
   * Set the value of cave.
   * @param v  Value to assign to cave.
   */
  public void setCaveLevel(boolean  v) {
    this.caveLevel = v;
  }

/**
* the type of wall on this board
*/
  private int wallType;
  
  /**
   * Get the value of wallType.
   * @return value of wallType.
   */
  public int getWallType() {
    return wallType;
  }
  
  /**
   * Set the value of wallType.
   * @param v  Value to assign to wallType.
   */
  public void setWallType(int  v) {
    this.wallType = v;
  }
  

  /*********************************************************\
  |*  REAL METHODS                                         *
  \*********************************************************/

  /**
   * Constructor for GameModel
   * @param newRandomSeed the random seed for this level
   * @param newPlayerList the Vector of tanks
   * @param newGParam the GameParameters
   * @param newIsCave is this level a cave?
   */
  public GameModel(long newRandomSeed, Vector newPlayerList,
           GameParameters newGParam, boolean newIsCave) {
    numGen = new Random(newRandomSeed);
    playerList = newPlayerList;
    gParam = newGParam; 
    roughness = gParam.getLevelComplexity();
    int steepness = gParam.getLevelSteepness();
    caveLevel = newIsCave;
    
    heights = new int[SCREEN_WIDTH];
    heights[0] = (int) (.3 * SCREEN_HEIGHT);
    heights[SCREEN_WIDTH - 1] = (int) (.3 * SCREEN_HEIGHT);
    
    cave = new int[SCREEN_WIDTH];
    cave[0] = (int) (.078 * SCREEN_HEIGHT);
    cave[SCREEN_WIDTH - 1] = (int) (.078 * SCREEN_HEIGHT);

    dirtChunk1Top = new int[SCREEN_WIDTH + 1];
    dirtChunk1Bottom = new int[SCREEN_WIDTH + 1];
    dirtChunk2Top = new int[SCREEN_WIDTH + 1];
    dirtChunk2Bottom = new int[SCREEN_WIDTH + 1];
 
    for (int i = 0; i < (SCREEN_WIDTH + 1); i++) {
      dirtChunk1Top[i] = -1;
      dirtChunk1Bottom[i] = -1;
      dirtChunk2Top[i] =  -1;
      dirtChunk2Bottom[i] = -1;
    }
    
    fractalize(0, (SCREEN_WIDTH - 1), steepness);
    initTanks();
    currentShots = new Vector();

  }

/**
* puts all the tanks on the board
*/
  private void initTanks() {
    Tank current;
    Tank check;
    int pos;
    System.out.println("Init Tanks");
    System.out.println("Sector Size : " + SCREEN_WIDTH / MAX_PLAYERS);
    System.out.println("number of sectors : " + MAX_PLAYERS);
    Tank[] sectors = new Tank[MAX_PLAYERS];
    for (int i = 0; i < playerList.size(); i++) {
      current = (Tank) playerList.get(i);
      boolean isValid;
      do {
    isValid = true;
    pos = numGen.nextInt(MAX_PLAYERS);
    System.out.println("tank " + i + ": generated sector " + pos);
    if (sectors[pos] != null) {
      System.out.println("something already there.");
      isValid = false;
    }
      } while (!isValid);
      System.out.println("allocating tank " + i + " to sector " + i);
      sectors[pos] = current;
    }
    for (int j = 0; j < MAX_PLAYERS; j++) {
      if (sectors[j] != null) {
    current = sectors[j];
    int offset = numGen.nextInt((SCREEN_WIDTH / MAX_PLAYERS)
            / (2));
    pos = j * (SCREEN_WIDTH / MAX_PLAYERS);
    pos = pos + offset;
    current.setXPos(pos);
    current.setYPos(heights[pos] + TANK_HEIGHT);
    for (int k = pos + 1; k < (pos + TANK_WIDTH); k++) {
      heights[k] = heights[pos];
    }  
      }
    }
  } 
     /**
     * fractalizes the terrain model
     * @param index1 the first point
     * @param index2 the second point
     * @param range the steepness
     */
  private void fractalize(int index1, int index2, float range) {
    /**
     * Extra smoothing/terminating condition
     */
    if ((index2 - index1) <= 5) {
      float slope = (((float) 
              heights[index2] - heights[index1]) / (index2 - index1));
      for (int i = (index1 + 1); i < index2; i++) {
    heights[i] = (int) 
      (heights[index1] + slope * (i - index1));
      }
      if (caveLevel) {
    slope = (((float)
          cave[index2] - cave[index1]) / (index2 - index1));
    for (int i = (index1 + 1); i < index2; i++) {
      cave[i] = (int) (cave[index1] + slope * (i - index1));
    }
      }
    return;
    }
    /** no weird errors **/
    if ((int) range <= 0) {
      range = 1.0f;
    }
    int h, ch;
    
    /**
     * calculate midpoint and its height
     */
    int midpoint = ((index1 + index2) / 2);
    do {
      h  = (int) (((heights[index1] + heights[index2]) / 2)
          + (numGen.nextInt((int) (range * 2))) - range);
    } while ((h < 5) || (h > ((int) (SCREEN_HEIGHT * .7))));
    if (caveLevel) {
      do {
    ch = (int) (((cave[index1] + cave[index2]) / 2)
            + (numGen.nextInt((int) (range * 2))) - range);
    
      } while ((ch < 5) || (ch > ((SCREEN_HEIGHT - heights[midpoint]) - 50)));
      cave[midpoint] = ch;
    }
    /**
     * Assign the midpoint, make the recursive calls
     */
    heights[midpoint] = h;
    range = (int) (range * Math.pow(2, (roughness * -1)));
    fractalize(index1, midpoint, range);
    fractalize(midpoint, index2, range);
    return;
  }

  /**
   * updates all the stuff on the board
   * @return true if model's done updating
   */ 
  public boolean update() {
    if (updateShots()) {
      if (collapseBoard()) {
    if (checkForFalling()) {
      return true;
    }
      } else {
    rerender = true;
      }
    }
    return false;
  }

  /**
   * updates any shots in the vector of shots
   * @return true if all shots are done
    */
  private boolean updateShots() {
    if (currentShots.size() == 0) {
      return true;
    } else {
      for (int i = 0; i < currentShots.size(); i++) {
    Shot temp = (Shot) currentShots.get(i);
    temp.update();
      }
    }
    return false;
  }

  /**
   * updates the terrain arrays
   * @return true if board is collapsed
    */
  private boolean collapseBoard() {
    //if no new updates need to be made
    if ((dirtChunk1Top[SCREEN_WIDTH] == -1)
        & (dirtChunk1Bottom[SCREEN_WIDTH] == -1)
        & (dirtChunk2Top[SCREEN_WIDTH] == -1)
        & (dirtChunk2Bottom[SCREEN_WIDTH] == -1)) {
      return true;
    } else { //set all tags to -1 initially
      dirtChunk1Top[SCREEN_WIDTH] = -1;
      dirtChunk1Bottom[SCREEN_WIDTH] = -1;
      dirtChunk2Top[SCREEN_WIDTH] = -1;
      dirtChunk2Bottom[SCREEN_WIDTH] = -1;
      //visit each x coordinate
      for (int i = 0; i < SCREEN_WIDTH; i++) {
    //check the dirt chunk closest to the ground first
    //this way the higher up one won't ever collide with it
    if (dirtChunk2Top[i] != -1) { //is there one?
      dirtChunk2Top[SCREEN_WIDTH] = 0;
      dirtChunk2Bottom[SCREEN_WIDTH] = 0;
      // check for collision with the ground
      if (((dirtChunk2Bottom[i] - 6) <= heights[i])
            | ((dirtChunk2Bottom[i] - 6) <= 0)) {
        heights[i] += (dirtChunk2Top[i]
                - dirtChunk2Bottom[i]);
        dirtChunk2Top[i] = dirtChunk2Bottom[i] = -1;
      } else { //otherwise make it fall
        dirtChunk2Bottom[i] -= 6;
        dirtChunk2Top[i] -= 6;
      }
    }
    //same as above, but for the higher dirt chunk
    if (dirtChunk1Top[i] != -1) {
      dirtChunk1Top[SCREEN_WIDTH] = 0;
      dirtChunk1Bottom[SCREEN_WIDTH] = 0;
      if (((dirtChunk1Bottom[i] - 6) <= heights[i])
            | ((dirtChunk2Bottom[i] - 6) <= 0)) {
        heights[i] += (dirtChunk1Top[i]
                - dirtChunk1Bottom[i]);
        dirtChunk1Top[i] = dirtChunk1Bottom[i] = -1;
      } else {
        dirtChunk1Bottom[i] -= 6;
        dirtChunk1Top[i] -= 6;
      }
    }
      }
    }
    return false;
  }

  /**
   * updates the positions of the tanks
   * true if all tanks have fallen completely
   * @return boolean
   */
  private boolean checkForFalling() {
    Tank temp;
    boolean retval = true;
    int ypos;
    for (int i = 0; i < playerList.size(); i++) {
      temp = (Tank) playerList.get(i);
      ypos = temp.getYPos();
      if ((ypos - TANK_WIDTH - 5) > (heights[temp.getXPos()])) {
    retval = false;
    temp.setYPos(ypos - 5);
    temp.setHp(temp.getHp() - 14);
      } else if ((ypos - TANK_WIDTH) > heights[temp.getXPos()]) {
    temp.setYPos(heights[temp.getXPos()] + TANK_HEIGHT);
      }
    }
    return retval;
  }

/**
 * adds a shot to the vector
 * @param newShot the shot to add
 */
  public void newShot(Shot newShot) {
    currentShots.add(newShot);
  }

/**
* removes a shot from vector
* @param toRemove the shot to remove
*/
  public void removeShot(Shot toRemove) {
    currentShots.remove(toRemove);
  }
}



