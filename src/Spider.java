/**
 *  <PRE>
 *  Missile.java
 *
 *  Revisions: 1.0 Apr. 22, 2003
 *               Created the BabyMissile class
 *
 *  </PRE>
 *
 *  @author Team QERM
 *  @version Version 1.0, Apr. 22, 2003
 */

public class Spider extends Shot {
  

  /** 
   * gets the type
   * @return int
   */
  public int getType() {
    return SPIDER;
  }
  
  /**
   * head is the head of it all
   */
  private boolean head;

  /**
   * Get the value of head.
   * @return value of head.
   */
  public boolean isHead() {
    return head;
  }
  
  /**
   * Set the value of head.
   * @param v  Value to assign to head.
   */
  public void setHead(boolean  v) {
    this.head = v;
  }
  


  /**
   * constructor
   * @param vel int
   * @param angle int
   * @param xi int
   * @param yi int
   * @param gm GameModel
   */
   
  public Spider(int vel, int angle, int xi, int yi,
              GameModel gm) {
    this(vel, angle, xi, yi, gm, true);
  }

 /**
  * does some stuff for checkstyle
  * @param vel int
  * @param angle int
  * @param xi int
  * @param yi int
  * @param gm GameModel
  * @param isHead boolean
  */
  public Spider(int vel, int angle, int xi, int yi, GameModel gm,
         boolean isHead) {
    gameModel = gm;
    x = xi;
    y = yi;
    vy = (float) vel * (float) Math.sin(Math.toRadians(angle)) / ONE_METER; 
    vx = (float) vel * (float) Math.cos(Math.toRadians(angle)) / ONE_METER;
    exploded = false;
    head = isHead;
  }
  
  /**
   *
   * public void update
   */
  public void update() {
    GameParameters gp = gameModel.getGParam();
    float gravity = G * gp.getGravity() * ONE_METER;
    if (head) {
      vy = vy + (gravity * DT);
      vx = vx + (gameModel.getWindSpeed() / 20);
    }
    if (!exploded) {
      if (((vy > 0.0f) & (head)) | (!head)) {
    updateMove();
    if (exploded & head) {
      exploded = false;
      ScorchedAmp.playSound(MISS_SOUND);
      gameModel.removeShot(this);
    } 
      } else {
    if (head) {
      int angle;
      if ((int) vx < 0) {
        angle = 235;
      } else {
        angle = 335;
      }
      Spider s0 = 
        new Spider(300, angle, getX(), getY(), gameModel, false);
      Spider s1 = 
        new Spider(300, angle - 10, getX(), getY(), gameModel, false);
      Spider s2 = 
        new Spider(300, angle - 20, getX(), getY(), gameModel, false);
      Spider s3 = 
        new Spider(300, angle - 30, getX(), getY(), gameModel, false);
      Spider s4 = 
        new Spider(300, angle - 40, getX(), getY(), gameModel, false);
      s0.setShooter(shooter);
      s1.setShooter(shooter);
      s2.setShooter(shooter);
      s3.setShooter(shooter);
      s4.setShooter(shooter);
      
      gameModel.removeShot(this);
      
      gameModel.newShot(s0);
      gameModel.newShot(s1);
      gameModel.newShot(s2);
      gameModel.newShot(s3);
      gameModel.newShot(s4);
    }
      }
    } else {
      radius++;
      if (radius >= (gp.getExplosionSize() * MISSILE_RAD)) {
    doEllipticalExplosion(x, y, radius);
    gameModel.setRerender(true);
    gameModel.removeShot(this);
      }
    }
  }
  
}// end of class Missile
