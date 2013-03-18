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

public class Mirv extends Shot {
  
  
  /**
   * What kind of class am i
   * @return int
   */
  public int getType() {
    return MIRV;
  }
  
  
  
  /**
   * constructor
   * @param vel int
   * @param angle int
   * @param xi int
   * @param yi int
   * @param gm GameModel
   */
  public Mirv(int vel, int angle, int xi, int yi,
              GameModel gm) {
    gameModel = gm;
    x = xi;
    y = yi;
    vy = (float) vel * (float) Math.sin(Math.toRadians(angle)) / ONE_METER; 
    vx = (float) vel * (float) Math.cos(Math.toRadians(angle)) / ONE_METER;
    exploded = false;
  }


  /**
   * updates junk
   */
  public void update() {
    GameParameters gp = gameModel.getGParam();
    float gravity = G * gp.getGravity() * ONE_METER;
    int heights[] = gameModel.getHeights();
    int cave[] = gameModel.getCave();
    int dirtChunk1Top[] = gameModel.getDirtChunk1Top();
    int dirtChunk2Top[] = gameModel.getDirtChunk2Top();
    int dirtChunk1Bottom[] = gameModel.getDirtChunk1Top();
    int dirtChunk2Bottom[] = gameModel.getDirtChunk2Top();
    vy = vy + (gravity * DT);
    vx = vx + (gameModel.getWindSpeed() / 20);
    if (vy > 0.0f) {
      updateMove();
      if (exploded) {
    exploded = false;
    ScorchedAmp.playSound(MISS_SOUND);
    gameModel.removeShot(this);
      }
    } else {
      ScorchedAmp.playSound(MIRV_PEAK);
      Missile s0 = 
    new Missile((int) vx * 2, 0, getX(), getY(), gameModel);
      Missile s1 = 
    new Missile((int) vx, 0, getX(), getY(), gameModel);
      Missile s2 = new Missile(0, 0, getX(), getY(), gameModel);
      Missile s3 = 
    new Missile((int) (vx * -1), 0, getX(), getY(), gameModel);
      Missile s4 = new 
    Missile((int) (vx * -2), 0, getX(), getY(), gameModel);
      s0.setShooter(shooter);
      s1.setShooter(shooter);
      s2.setShooter(shooter);
      s3.setShooter(shooter);
      s4.setShooter(shooter);
      
      gameModel.newShot(s0);
      gameModel.newShot(s1);
      gameModel.newShot(s2);
      gameModel.newShot(s3);
      gameModel.newShot(s4);
      
      gameModel.removeShot(this);
    }
  }
    
}// end of class Missile
