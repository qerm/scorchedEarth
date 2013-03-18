import junit.framework.TestCase;
import junit.framework.TestSuite;
import junit.framework.Test;

/**
 * @author Wesley Reynolds and David McCann
 */
public class TestWeapons extends TestCase {

  /**
   *
   */
  private Weapons testWeapons;
  
  /**
   * Constructor for class
   * @param s name of test to add to a suite
     */
  public TestWeapons (String s) {
    super(s);
  }
  
  /**
   * Initializes any variables needed
   */
  protected void setUp() {
    testWeapons = new Weapons();
  }
  
  /**
   * Closes any holes and tears down variables if needed
   */
  protected void tearDown() {
  }
  
  /**
   * Tests setInvItem and getInvItem methods
   */
  public void testSetGetInvItem() {
    testWeapons.setInvItem(1, 1);
    assertTrue(testWeapons.getInvItem(1) == 1);
  }

  public void testWeapons() {
    Weapons w = new Weapons();
    boolean result = true;
    if (w.getWeapons() == null) {
        result = false;
    }
    assertTrue(result);
  }

    public void testsetWeapons() {
        boolean result = false;
        Weapons w = new Weapons();
        int[] i = {1,2,3};
        w.setWeapons(i);
        if (i.equals(w.getWeapons()))
        {
            result = true;
        }
        assertTrue(result);
    }

  /**
   * Creates a new test suite
   * @return The new Test class with the test suite in it
   */
  public static Test suite() {
    TestSuite suite = new TestSuite();

    suite.addTest(new TestWeapons("testSetGetInvItem"));
    suite.addTest(new TestWeapons("testWeapons"));
    suite.addTest(new TestWeapons("testsetWeapons"));


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
  
} // class TestWeapons
