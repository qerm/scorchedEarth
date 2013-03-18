import junit.framework.TestCase;
import junit.framework.TestSuite;
import junit.framework.Test;
import java.util.Vector;
/**
 * @author Wesley Reynolds and David McCann
 */
public class TestMissile extends TestCase {

  /**
   * 
   */
  private Missile testMissileThing;
  
  /**
   * Constructor for class
   * @param s name of test to add to a suite
     */
  public TestMissile (String s) {
    super(s);
  }
  
  /**
   * Initializes any variables needed
   */
  protected void setUp() {
  }
  
  /**
   * Closes any holes and tears down variables if needed
   */
  protected void tearDown() {
  }

  /**
   * Tests constructor
   */
  public void testMissile() {
      GameParameters gp = new GameParameters();
      Vector v = new Vector();    
GameModel gm = new GameModel(1,v,gp,false);
    testMissileThing = new Missile(100, 99, 1, 2, gm);
    
    assertTrue(testMissileThing.getGameModel() == gm);
    assertTrue(testMissileThing.getX() == 1);
    assertTrue(testMissileThing.getY() == 2);
}
  
 
  /**
   * Creates a new test suite
   * @return The new Test class with the test suite in it
   */
  public static Test suite() {
    TestSuite suite = new TestSuite();

    suite.addTest(new TestMissile("testMissile"));

    return suite;
  }
  
  /**
   * Main method. Run to test class
   * @param args Command line params. Not used
   */
  public static void main(String[] args) {
    Test t = suite();
    junit.textui.TestRunner.run(t);
  }
  
} // class TestMissile






