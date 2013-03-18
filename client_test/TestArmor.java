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
public class TestArmor extends TestCase implements Constants {

  /**
   * 
   */
  private Armor testArmor;
  
  /**
   * Constructor for class
   * @param s name of test to add to a suite
     */
  public TestArmor (String s) {
    super(s);
  }
  
  /**
   * Initializes any variables needed
   */
  protected void setUp() {
    testArmor = new Armor();
  }
  
  /**
   * Closes any holes and tears down variables if needed
   */
  protected void tearDown() {
  }
  
  /**
   * Tests setPatriot and getPatriot methods
   */
 

  /**
   * Tests setSandbags and getSandbags methods
   */
  public void testSetGetSandbags() {
    testArmor.setSandbags(11);
    assertTrue(testArmor.getSandbags() == 11);
  }

  /**
   * Tests setCamoPaint and getCamoPaint methods
   */
  public void testSetGetCamoPaint() {
    testArmor.setCamoPaint(23);
    assertTrue(testArmor.getCamoPaint() == 23);
  }

  /**
   * Tests setActiveArmor and getActiveArmor methods
   */
  public void testSetGetActiveArmor() {
    testArmor.setActiveArmor(12);
    assertTrue(testArmor.getActiveArmor() == 12);
  }

  /**
   * Tests setInvItem and getInvItem methods
   */
  public void testSetGetInvItem() {
    testArmor.setInvItem(CAMO_PAINT, 12);
    testArmor.setInvItem(ACTIVE_ARMOR, 33);
    testArmor.setInvItem(SAND_BAGS, 11);

    assertTrue(testArmor.getInvItem(CAMO_PAINT) == 12);
    assertTrue(testArmor.getInvItem(ACTIVE_ARMOR) == 33);
    assertTrue(testArmor.getInvItem(SAND_BAGS) == 11);
  }
  
 
  /**
   * Creates a new test suite
   * @return The new Test class with the test suite in it
   */
  public static Test suite() {
    TestSuite suite = new TestSuite();

    suite.addTest(new TestArmor("testSetGetSandbags"));
    suite.addTest(new TestArmor("testSetGetCamoPaint"));
    suite.addTest(new TestArmor("testSetGetActiveArmor"));
    suite.addTest(new TestArmor("testSetGetInvItem"));

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
  
} // class TestArmor
