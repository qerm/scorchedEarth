import junit.framework.TestCase;
import junit.framework.TestSuite;
import junit.framework.Test;

/**
 * @author Wesley Reynolds and David McCann
 */
public class TestGameOptionsDialog extends TestCase {

  /**
   * 
   */
  private GameOptionsDialog testDialog;
  
  /**
   * Constructor for class
   * @param s name of test to add to a suite
     */
  public TestGameOptionsDialog (String s) {
    super(s);
  }
  
  /**
   * Initializes any variables needed
   */
  protected void setUp() {
    testDialog = new GameOptionsDialog();
  }
  
  /**
   * Closes any holes and tears down variables if needed
   */
  protected void tearDown() {
  }
  
  /**
   * Tests setGParam and getGParam methods
   */
  public void testSetGetGParam() {
    GameParameters gparam = new GameParameters();

    testDialog.setGParam(gparam);
    assertTrue(testDialog.getGParam() == gparam);
  }

  /**
   * Tests setGcw and getGcw methods
   */
  public void testSetGetGcw() {
    GlobalChatWindow gcw = new GlobalChatWindow();

    testDialog.setGcw(gcw);
    assertTrue(testDialog.getGcw() == gcw);
  }

  /**
   * Creates a new test suite
   * @return The new Test class with the test suite in it
   */
  public static Test suite() {
    TestSuite suite = new TestSuite();

    suite.addTest(new TestGameOptionsDialog("testSetGetGParam"));
    suite.addTest(new TestGameOptionsDialog("testSetGetGcw"));

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
  
} // class TestGameOptionsDialog
