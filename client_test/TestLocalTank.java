import junit.framework.TestCase;
import junit.framework.TestSuite;
import junit.framework.Test;

/**
 * @author Wesley Reynolds and David McCann
 */
public class TestLocalTank extends TestCase {

  /**
   * 
   */
  private LocalTank testTank;
  
  /**
   * Constructor for class
   * @param s name of test to add to a suite
     */
  public TestLocalTank (String s) {
    super(s);
  }
  
  /**
   * Initializes any variables needed
   */
  protected void setUp() {
    testTank = new LocalTank();
  }
  
  /**
   * Closes any holes and tears down variables if needed
   */
  protected void tearDown() {
  }
  
  /**
   * Tests setWeapons and getWeapons methods
   */
  public void testSetGetWeapons() {
    Weapons weapons = new Weapons();
   
    testTank.setWeapons(weapons);
    assertTrue(testTank.getWeapons() == weapons);
  }

  /**
   * Tests setMoney and getMoney methods
   */
  public void testSetGetMoney() {
    testTank.setMoney(123456);
    assertTrue(testTank.getMoney() == 123456);
  }
 
  /**
   * Creates a new test suite
   * @return The new Test class with the test suite in it
   */
  public static Test suite() {
    TestSuite suite = new TestSuite();

    suite.addTest(new TestLocalTank("testSetGetWeapons"));
    suite.addTest(new TestLocalTank("testSetGetMoney"));

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
  
} // class TestLocalTank
