/**
 *  <PRE>
 *  BabyMissile.java
 *
 *  Revisions: 1.0 Apr. 22, 2003
 *               Created the BabyMissile class
 *
 *  </PRE>
 *
 *  @author Team QERM
 *  @version Version 1.0, Apr. 22, 2003
 */

public class BabyMissile extends Shot {
  
  


  /**
   * Does stuff
   * @return int bal
   */  
  public int getType() {
    return BABY_MISSILE;
  }
  
  
  
  /**
   * constructor
   * @param vel int
   * @param angle int
   * @param xi int
   * @param yi int
   * @param gm GameModel
   */
  public BabyMissile(int vel, int angle, int xi, int yi,
              GameModel gm) {
    gameModel = gm;
    x = xi;
    y = yi;
    vy = (float) vel * (float) Math.sin(Math.toRadians(angle)) / ONE_METER; 
    vx = (float) vel * (float) Math.cos(Math.toRadians(angle)) / ONE_METER; 
    exploded = false;
  }



 /**
  * does stuff
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
      if (radius >= (gp.getExplosionSize() * BABY_MISSILE_RAD)) {
    doEllipticalExplosion(x, y, radius);
    gameModel.setRerender(true);
    gameModel.removeShot(this);
      }
    // }
    //  }
    }
  }

}// end of class BabyMissile
