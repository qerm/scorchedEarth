import java.util.Vector;
/**
 *  <PRE>
 *  Shot.java, the abstract class of all shots fired
 *  in our rockin' cool game.
 *  Revisions: 1.0 Apr. 22, 2003
 *               Created the Shot class
 *
 *  </PRE>
 *
 *  @author Team QERM
 *  @version Version 1.0, Apr. 22, 2003
 * 
 */

public abstract class Shot implements Constants {

  /**
   * the width of one meter, in pixels
   */
  static final int ONE_METER = TANK_WIDTH / 10;

  /**
   * the constant gravity, in m/s^2
   */
  static final float G = -9.8f;
  
  /**
   * The time increment between each update call, in s.
   */
  static final float DT = .2f;

/**
* a shot within the height array
*/
  static final int BOTTOM = 0;
  
  /**
  * place constant
  */
  static final int BELOW_CHUNK2 = 1;
 
   /**
  * place constant
  */
  static final int IN_CHUNK2 = 2;
  
   /**
  * place constant
  */
  static final int MIDDLE = 3;
  
   /**
  * place constant
  */
  static final int IN_CHUNK1 = 4;
  
   /**
  * place constant
  */
  static final int ABOVE_CHUNK1 = 5;
  
   /**
  * place constant
  */
  static final int TOP = 6;
    
  /**
   * the gravity, in m/s^2
   */
  protected float gravity;
  
  /**
   * the wind velocity, in m/s
   */
  protected float vw;

/**
* the x coordinate
*/
 public float x;

  /**
   * Get the value of x.
   * @return value of x.
   */
  public int getX() {
    return (int) x;
  }
  
  /**
   * Set the value of x.
   * @param v  Value to assign to x.
   */
  public void setX(int  v) {
    this.x = (float) v;
  }
  
  /**
  * the y coordinate
  */
  public float y;
    
  /**
   * Get the value of y.
   * @return value of y.
   */
  public int getY() {
    return (int) y;
  }
  
  /**
   * Set the value of y.
   * @param v  Value to assign to y.
   */
  public void setY(int  v) {
    this.y = (float) v;
  }
  
  /**
  * the game Model
  */
  public GameModel gameModel;
    
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
   * the x velocity 
   */
  public float vx;
  
  /**
   * Get the value of vx.
   * @return value of vx.
   */
  public float getVx() {
    return vx;
  }
  
  /**
   * Set the value of vx.
   * @param v  Value to assign to vx.
   */
  public void setVx(float  v) {
    this.vx = v;
  }
  
  /**
  * the y velocity
  */
  public float vy;
  
  /**
   * Get the value of vy.
   * @return value of vy.
   */
  public float getVy() {
    return vy;
  }
  
  /**
   * Set the value of vy.
   * @param v  Value to assign to vy.
   */
  public void setVy(float  v) {
    this.vy = v;
  }

  /**
   * state variable, set to true when the velocity of the 
   * shot is 0
   */
  public boolean exploded;
  
  /**
   * Get the value of exploded.
   * @return value of exploded.
   */
  public boolean isExploded() {
    return exploded;
  }
  
  /**
   * Set the value of exploded.
   * @param v  Value to assign to exploded.
   */
  public void setExploded(boolean  v) {
    this.exploded = v;
  }
  
  /**
   * the explosion radius when exploded=true
   */
  public int radius;
  
  /**
   * Get the value of radius.
   * @return value of radius.
   */
  public int getRadius() {
    return radius;
  }
  
  /**
   * Set the value of radius.
   * @param v  Value to assign to radius.
   */
  public void setRadius(int  v) {
    this.radius = v;
  }

/**
* the name of the shooter
*/
  public String shooter;

  /**
   * Get the Shooter value.
   * @return the Shooter value.
   */
  public String getShooter() {
    return shooter;
  }

  /**
   * Set the Shooter value.
   * @param newShooter The new Shooter value.
   */
  public void setShooter(String newShooter) {
    this.shooter = newShooter;
  }

/**
* returns the type constant of the shot
* @return int s
*/
  public abstract int getType();
  
  /**
  * the update method called by gameModel
  */
  public abstract void update();

/**
* the equals method for the shot class
* @param o if same type and position
* @return boolean f
*/
  public boolean equals(Object o) {
    if (o instanceof Shot) {
      Shot temp = (Shot) o;
      return (((int) x == temp.getX())
            & ((int) y == temp.getY())
            & (this.getType() == temp.getType()));
    }
    return false;
  }

  /**
   * checks if the shot has hit anything
   * @param tx the x coordinate
   * @param ty the y coordinate
   * @return boolean
   */
  boolean isaHit(int tx, int ty) {
    int[] cave, heights, dirtChunk1Top, dirtChunk1Bottom; 
    int[] dirtChunk2Top, dirtChunk2Bottom;
    if ((tx < 0) | (tx >= SCREEN_WIDTH)) {
      return false;
    }
    if (gameModel.isCaveLevel()) {
      cave = gameModel.getCave();
      if ((SCREEN_HEIGHT - ty) < cave[tx]) {
    return true;
      }
    }
    dirtChunk1Top = gameModel.getDirtChunk1Top();
    if (dirtChunk1Top[tx] != -1) {
      dirtChunk1Bottom = gameModel.getDirtChunk1Bottom();
      if ((ty < dirtChunk1Top[tx]) & (ty > dirtChunk1Bottom[tx])) {
    return true;
      }
    }
    dirtChunk2Top = gameModel.getDirtChunk2Top();
    if (dirtChunk2Top[tx] != -1) {
      dirtChunk2Bottom = gameModel.getDirtChunk2Bottom();
      if ((ty < dirtChunk2Top[tx]) & (ty > dirtChunk2Bottom[tx])) {
    return true;
      }
    }
    heights = gameModel.getHeights();
    if (ty < heights[tx]) {
      return true;
    }

    Vector playerList = gameModel.getPlayerList();
    Tank t;
    for (int k = 0; k < playerList.size(); k++) {
      t = (Tank) playerList.get(k);
      if ((tx >= t.getXPos())
        & (tx <= (t.getXPos() + TANK_WIDTH))) {
      if ((ty <= t.getYPos()) & (ty >= (t.getYPos() - TANK_HEIGHT))) {
        if (!t.getIsDead()) {
          return true;
        }
      }
      }
    }

    return false;
  }

  /**
   * returns a constant that is the position of this
   * height relative to the heights of the board
   * and the chunks.
   * @param tx the x coordinate
   * @param ty the y coordinate
   * @return the place constant
   */
  int place(int tx, int ty) {
    int[] cave, heights, dirtChunk1Top, dirtChunk1Bottom,
      dirtChunk2Top, dirtChunk2Bottom;
    heights = gameModel.getHeights();
    dirtChunk1Top = gameModel.getDirtChunk1Top();
    dirtChunk2Top = gameModel.getDirtChunk2Top();
    dirtChunk1Bottom = gameModel.getDirtChunk1Bottom();
    dirtChunk2Bottom = gameModel.getDirtChunk2Bottom();

    if (gameModel.isCaveLevel()) {
      cave = gameModel.getCave();
      if ((SCREEN_HEIGHT - ty) < cave[tx]) {
    return TOP;
      }
    }
    if (dirtChunk1Top[tx] != -1) {
      dirtChunk1Bottom = gameModel.getDirtChunk1Bottom();
      if ((ty < dirtChunk1Top[tx]) & (ty > dirtChunk1Bottom[tx])) {
    return IN_CHUNK1;
      } else if (ty > dirtChunk1Top[tx]) {
    return ABOVE_CHUNK1;
      } 
    }
    if (dirtChunk2Top[tx] != -1) {
      dirtChunk2Bottom = gameModel.getDirtChunk2Bottom();
      if ((ty < dirtChunk2Top[tx]) & (ty > dirtChunk2Bottom[tx])) {
    return IN_CHUNK2;
      } else if (ty > dirtChunk2Top[tx]) {
    return MIDDLE;
      }
    }
    if (ty < heights[tx]) {
      return BOTTOM;
    } else if (dirtChunk2Top[tx] != -1) {
      if (ty < dirtChunk2Bottom[tx]) {
    return BELOW_CHUNK2;
      } 
    } else {
      return MIDDLE;
    }
    System.out.println("flagrant system error!");
    return -1;
  }

/**
* updates the terrain after an explosion
* @param ex the x coordinate of the center of explosion
* @param ey the y coordinate of the center of explosion
* @param radius the radius
*/
  void doEllipticalExplosion(float ex, float ey, int radius) {
    int[] cave, heights, dirtChunk1Top, dirtChunk1Bottom,
      dirtChunk2Top, dirtChunk2Bottom;
    Vector playerList = gameModel.getPlayerList();
    Tank t;
    int dhp[] = new int[playerList.size()];    
    heights = gameModel.getHeights();
    dirtChunk1Top = gameModel.getDirtChunk1Top();
    dirtChunk2Top = gameModel.getDirtChunk2Top();
    dirtChunk1Bottom = gameModel.getDirtChunk1Bottom();
    dirtChunk2Bottom = gameModel.getDirtChunk2Bottom();
    cave = gameModel.getCave();
    int expTop, topPlace;
    int expBottom, bottomPlace;
    for (int i = ((int) ex - radius); ((i <= ((int) ex + radius))
                        & (i < SCREEN_WIDTH)); i++) {
      if (i < 0) {
    i = 0;
      }
      double r2 = Math.pow(radius, 2);
      double x2 = Math.pow((i - (int) ex), 2);
      double rt = Math.sqrt(r2 - x2);
      expTop = (int) (ey +  rt);
      expBottom = (int) (ey -  rt);
      topPlace = place(i, expTop);
      bottomPlace = place(i, expBottom);
      int temp = heights[i];
      int temp2 = dirtChunk2Top[i];
      int temp3 = dirtChunk1Top[i];
      switch (bottomPlace) {
      case BOTTOM:
    heights[i] = expBottom;
      case BELOW_CHUNK2:
    switch (topPlace) {
    case BOTTOM:
      if (dirtChunk2Top[i] != -1) {
        dirtChunk2Bottom[i] -= (temp - expTop);
      } else { 
        dirtChunk2Top[i] = temp;
        dirtChunk2Bottom[i] = expTop;
      }
      dirtChunk2Top[SCREEN_WIDTH] = 0;
      break;
    case IN_CHUNK2:
      dirtChunk2Bottom[i] = expTop;
      break; 
    case MIDDLE:
      dirtChunk2Top[i] = -1;
      dirtChunk2Bottom[i] = -1;
      break;
    case IN_CHUNK1:
      dirtChunk1Bottom[i] = expTop;
      dirtChunk1Bottom[SCREEN_WIDTH] = 0;
      break;
    case TOP:
      cave[i] = (SCREEN_HEIGHT - expTop);
    case ABOVE_CHUNK1:
      dirtChunk1Bottom[i] = -1;
      dirtChunk2Bottom[i] = -1;
      dirtChunk2Top[i] = -1;
      dirtChunk1Top[i] = -1;
      break;
    }
    break;     
      case IN_CHUNK2: 
    dirtChunk2Top[i] = expBottom;
      case MIDDLE:
    switch (topPlace) {
    case IN_CHUNK2:
      if (dirtChunk1Top[i] != -1) {
        dirtChunk1Bottom[i] -= (temp2 - expTop);
      } else { 
        dirtChunk1Top[i] = temp2;
        dirtChunk1Bottom[i] = expTop;
      }
      dirtChunk2Top[SCREEN_WIDTH] = 0;
      break;
    case MIDDLE:
      break;
    case IN_CHUNK1:
      dirtChunk1Bottom[i] = expTop;
      dirtChunk1Bottom[SCREEN_WIDTH] = 0;
      break;
    case TOP:
      cave[i] = (SCREEN_HEIGHT - expTop);
    case ABOVE_CHUNK1:
      dirtChunk1Top[i] = -1;
      dirtChunk1Bottom[i] = -1;
      break;
    }
    break;    
      case IN_CHUNK1:
    dirtChunk1Top[i] = expBottom;
      case ABOVE_CHUNK1:
    switch (topPlace) {
    case IN_CHUNK1:
      dirtChunk1Top[i] -= expTop - expBottom;
      dirtChunk1Top[SCREEN_WIDTH] = 0;
      break;
    case TOP:
      cave[i] = (SCREEN_HEIGHT - expTop);
    case ABOVE_CHUNK1:
      break;
    }       
    break;
      case TOP:
    if (dirtChunk1Top[i] != -1) {
      dirtChunk1Top[i] += cave[i] - expBottom;
    } else {
      dirtChunk1Bottom[i] = (SCREEN_HEIGHT - cave[i]);
      dirtChunk1Top[i] = expBottom;
      cave[i] = SCREEN_HEIGHT - expTop;
    }
    dirtChunk1Top[SCREEN_WIDTH] = 0;
    break;
      default:
    break;
      }
  
      hurtTanks(expTop, expBottom, i, playerList, dhp);
    }
    Tank takeOff;
    int hp;
    for (int l = 0; l < dhp.length; l++) {
      takeOff = (Tank) playerList.get(l);
      hp = takeOff.getHp();
      takeOff.setHp(hp - dhp[l]);
      if (takeOff.getHp() < 0) {
    takeOff.setHp(0);
    takeOff.setKiller(shooter);
    Tank tempT;
    for (int g = 0; g < gameModel.getPlayerList().size(); g++) {
      tempT = (Tank) gameModel.getPlayerList().get(g);
      if (tempT.getName().equals(shooter)) {
        if (tempT instanceof LocalTank) {
          if (!takeOff.equals(tempT)) { 
        LocalTank lt = (LocalTank) tempT;
        lt.setMoney(lt.getMoney() + KILL_MONEY);
          }
        }
      }
    }
      }
    }
    
  }
  
/**
* The main update move method called by all parabolic-style weapons
*/
  public void updateMove() {
    double dx = Math.sqrt(Math.pow(vx, 2) + Math.pow(vy, 2)) / ONE_METER;
    parabolicMove((int) dx);
  }

/**
* A recursive move-updating function
* @param dx the number of times left to recurse
*/
  public void parabolicMove(int dx) {
    if (dx == 0) {
      return;
    }
    if (((int) x <= 0) | ((int) x >= (SCREEN_WIDTH - 1))) {
      hitWall();
    }
    if (exploded) {
      return;
    }
    float x2 = x + (vx * DT);
    float y2 = y + (vy * DT);
    float slope = ((y2 - y) / (x2 - x));
    if (isaHit((int) x, getY())) {
      exploded = true;
      ScorchedAmp.playSound(HIT_SOUND);
      return;
    }
    if ((int) vx > 0) {
      x += 1;
      y += (slope * 1);
    } else if (vx == 0) {
      if (vy > 0) {
    y += 1;
      } else {
    y -= 1;
      }
    } else {
      x -= 1;
      y += (slope * -1);
    }
    parabolicMove((dx - 1));
  }

/**
* Checks hitting wall conditions,
* action based on wallBehavior
*/
  public void hitWall() {
    switch (gameModel.getWallType()) {
    case NO_WALLS:
      gameModel.removeShot(this);
      break;
    case BOUNCY_WALLS:
      vx = vx * -1;
      break;
    case WRAP_WALLS:
      if (getX() <= 0) {
    setX(SCREEN_WIDTH - 1);
      } else {
    setX(0);
      }
      break;
    case STICKY_WALLS:
      exploded = true;
      ScorchedAmp.playSound(MISS_SOUND);
      break;
    }
  }

/**
* hurts the tanks
* @param expTop the explosion top
* @param expBottom the explosion bottom
* @param i the current sliver
* @param playerList the playerList
* @param dhp the hp array
*/
public void hurtTanks(int expTop, int expBottom, int i,
                                        Vector playerList, 
                                                    int[] dhp) {
 for (int k = 0; k < playerList.size(); k++) {
    Tank t = (Tank) playerList.get(k);
    if (!t.getIsDead()) {
      if ((i >= t.getXPos())
           & (i <= (t.getXPos() + TANK_WIDTH))) {
        if ((t.getYPos() < expTop)
                & ((t.getYPos() - TANK_HEIGHT) > expBottom)) {
          System.out.print("full force:");
          System.out.println(t.getName() + "takes "
                + (((expTop - expBottom) 
                * (Math.abs((int) y - t.getYPos()))) / 8));
          dhp[k] += (((expTop - expBottom) 
                    * (Math.abs((int) y - t.getYPos()))) / 8);
        } else if ((t.getYPos() > expTop)
                    & ((t.getYPos() - TANK_HEIGHT) < expTop)) {
          System.out.print("bottom half:");
          System.out.println(t.getName() + "takes "
                + (((expTop - expBottom)
                    * (Math.abs((int) expTop
                    - (t.getYPos()
                    - TANK_HEIGHT)))) / 8));
          dhp[k] += (((expTop - expBottom)
                    * (Math.abs((int) expTop - (t.getYPos()
                    - TANK_HEIGHT)))) / 8);
        } else if ((t.getYPos() > expBottom)
                & ((t.getYPos() - TANK_HEIGHT) < expBottom)) {
          System.out.print("bottom half:");
          System.out.println(t.getName() + "takes " 
                     + (((expTop - expBottom) 
                     * (Math.abs((int) t.getYPos()
                     - expBottom))) / 8));
          dhp[k] += (((expTop - expBottom) 
             * (Math.abs((int) t.getYPos() - expBottom))) / 8);
        }
      }
    } 
  } // end check tank collisions

}
}// end of class Shot
