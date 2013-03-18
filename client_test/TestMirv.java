import junit.framework.TestCase;
import junit.framework.TestSuite;
import junit.framework.Test;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Vector;

/**
 * @author Wesley Reynolds and David McCann
 */
public class TestMirv extends TestCase implements Constants {

  /**
   * 
   */
  private Mirv testMirvThing;
  
  /**
   * Constructor for class
   * @param s name of test to add to a suite
     */
  public TestMirv (String s) {
    super(s);
  }
  
  /**
   * Initializes any variables needed
   */
  protected void setUp() {
    //testMirvThing = new Mirv();
  }
  
  /**
   * Closes any holes and tears down variables if needed
   */
  protected void tearDown() {
  }

  /**
   * Tests constructor
   */
  public void testMirv() {
      Vector v = new Vector();
      GameParameters gp = new GameParameters();
    GameModel gm = new GameModel(1,v,gp,false);
    testMirvThing = new Mirv(100, 99, 1, 2, gm);
    
    assertTrue(testMirvThing.getGameModel() == gm);
    assertTrue(testMirvThing.getX() == 1);
    assertTrue(testMirvThing.getY() == 2);
}
  
 
  /**
   * Creates a new test suite
   * @return The new Test class with the test suite in it
   */
  public static Test suite() {
    TestSuite suite = new TestSuite();

    suite.addTest(new TestMirv("testMirv"));

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
  
} // class TestMirv
