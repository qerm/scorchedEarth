import java.util.Vector;
/**
 *  <PRE>
 *  Tomahawk.java
 *
 *  Revisions: 1.0 Apr. 22, 2003
 *               Created the BabyMissile class
 *
 *  </PRE>
 *
 *  @author Team QERM
 *  @version Version 1.0, Apr. 22, 2003
 */
public class Tomahawk extends Shot {
  
  /**
  * returns the type of shot
  * @return int
  */
  public int getType() {
    return TOMAHAWK;
  }

/**
* The vector that keeps track of potential countermeasures
* launchers.  shouldn't be used
*/
  private Vector potentials;

/**
* have any countermeasures been launched?
*/
  private boolean counterMeasures = false;

/**
* the displacement
*/
  private int disp;
  
/**
* Constructor for Tomahawk
* @param vel the velocity
* @param angle the angle
* @param xi the initial x coordinate
* @param yi the initial y coordinate
* @param gm the game
*/
  public Tomahawk(int vel, int angle, int xi, int yi,
              GameModel gm) {
    gameModel = gm;
    if (vel < 200) {
      vel = 200;
    }
    vx = vel / ONE_METER;
    if (angle > 90) {
      vx *= -1;
    }
    exploded = false;
  }
  
  /**
   * Sets up the position of the tomahawk, because it is different
   * from the parabolic style weapons
   */
  public void setUp() {
    //Compile a list of potential tanks that can launch patriots//
    //as well as a Vector of positions to check when you're near//
    //a patriot-launching tank//
    Vector playerList = gameModel.getPlayerList();
    int heights[] = gameModel.getHeights();
    Tank t;
    potentials = new Vector();
    for (int i = 0; i < playerList.size(); i++) {
      t = (Tank) playerList.get(i);
      if ((t.getName().equals(shooter))) {
    if (vx < 0) {
      x = t.getXPos() - 2;
      if (getX() < 0) {
        setX(0);
      }
      y = heights[(int) x] + TANK_HEIGHT;
    } else {
      x = t.getXPos() + TANK_WIDTH + 2;
      if (getX() > (SCREEN_WIDTH - 1)) {
        setX(SCREEN_WIDTH - 1);
      }
      y = heights[(int) x] + TANK_HEIGHT;
    }
      }
    }
  }

/**
* Updates the position of this shot, checking for collisions
* and killing tanks and such
*/
  public void update() {
    
    GameParameters gp = gameModel.getGParam();
    int heights[] = gameModel.getHeights();
        
    if (!exploded) {
      int dx = Math.abs((int) (vx * DT));
      for (int j = 0; j < dx; j++) {
    if (((int) x <= 0) | ((int) x >= (SCREEN_WIDTH - 1))) {
      hitWall();
      if ((getX() >= 0) & (getX() <= (SCREEN_WIDTH - 1))) {
        y = heights[getX()] + TANK_HEIGHT;
      }
      if (exploded) {
        return;
      }
    } else {
      y = heights[getX()] + TANK_HEIGHT;
    }
    if (isaHit(getX(), getY())) {
            exploded = true;
        ScorchedAmp.playSound(HIT_SOUND);
        return;
    }

    if (vx < 0) {
      x = x - 1;
    } else {
      x = x + 1;
    }
    if ((x >= 0) & (x <= (SCREEN_WIDTH - 1))) {
      y = heights[getX()] + TANK_HEIGHT;
    }
      }
    } else {
      radius++;
      if (radius >= (gp.getExplosionSize() * MISSILE_RAD)) {
    doEllipticalExplosion(x, y, radius);
    gameModel.setRerender(true);
    gameModel.removeShot(this);
      }
    // }
    //  }
    }
  }

}// end of class Tomahawk
