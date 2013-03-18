import junit.framework.TestCase;
import junit.framework.TestSuite;
import junit.framework.Test;

/**
 * @author Wesley Reynolds and David McCann
 */
public class TestPromptPanel extends TestCase {

  /**
   * 
   */
  private PromptPanel testPanel;
  
  /**
   * Constructor for class
   * @param s name of test to add to a suite
     */
  public TestPromptPanel (String s) {
    super(s);
  }
  
  /**
   * Initializes any variables needed
   */
  protected void setUp() {
    testPanel = new PromptPanel();
  }
  
  /**
   * Closes any holes and tears down variables if needed
   */
  protected void tearDown() {
  }
  
  /**
   * Tests setGcw and getGcw methods
   */
  public void testSetGetGcw() {
    GlobalChatWindow gcw = new GlobalChatWindow(true);

    testPanel.setGcw(gcw);
    assertTrue(testPanel.getGcw() == gcw);
  }
 
  /**
   * Creates a new test suite
   * @return The new Test class with the test suite in it
   */
  public static Test suite() {
    TestSuite suite = new TestSuite();

    suite.addTest(new TestPromptPanel("testSetGetGcw"));

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
  
} // class TestPromptPanel
