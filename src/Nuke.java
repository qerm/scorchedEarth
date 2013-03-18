/**
 *  <PRE>
 *  Nuke.java
 *
 *  Revisions: 1.0 Apr. 22, 2003
 *               Created the BabyMissile class
 *
 *  </PRE>
 *
 *  @author Team QERM
 *  @version Version 1.0, Apr. 22, 2003
 */

public class Nuke extends Shot {
  
  /**
   * what type am I?
   * @return int
   */
  public int getType() {
    return NUKE;
  }
  
  
  
  /**
   * makes a nuke
   * @param vel int
   * @param angle int
   * @param xi int
   * @param yi int
   * @param gm GameModel
   */
  public Nuke(int vel, int angle, int xi, int yi,
              GameModel gm) {
    gameModel = gm;
    x = xi;
    y = yi;
    vy = (float) vel * (float) Math.sin(Math.toRadians(angle)) / ONE_METER;
    vx = (float) vel * (float) Math.cos(Math.toRadians(angle)) / ONE_METER; 
    exploded = false;
  }


  /**
   * updates the junk
   */
  public void update() {
    GameParameters gp = gameModel.getGParam();
    float gravity = G * gp.getGravity() * ONE_METER;
    vy = vy + (gravity * DT);
    vx = vx + (gameModel.getWindSpeed() / 20);
    if (!exploded) {
      updateMove();
    } else {
      radius++;
      if (radius >= (gp.getExplosionSize() * NUKE_RAD)) {
    doEllipticalExplosion(x, y, radius);
    gameModel.setRerender(true);
    gameModel.removeShot(this);
      }
    // }
    //  }
    }
  }

}// end of class BabyMissile
