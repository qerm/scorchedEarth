import junit.framework.TestCase;
import junit.framework.TestSuite;
import junit.framework.Test;

/**
 * @author Wesley Reynolds and David McCann
 */
public class TestGlobalChatPanel extends TestCase {

  /**
   * 
   */
  private GlobalChatPanel testPanel;
  
  /**
   * Constructor for class
   * @param s name of test to add to a suite
     */
  public TestGlobalChatPanel (String s) {
    super(s);
  }
  
  /**
   * Initializes any variables needed
   */
  protected void setUp() {
    testPanel = new GlobalChatPanel();
  }
  
  /**
   * Closes any holes and tears down variables if needed
   */
  protected void tearDown() {
  }
  
  /**
   * Tests setClient and getClient methods
   */
  public void testSetGetClient() {
    ClientThread c = new ClientThread();
    
    testPanel.setClient(c);
    assertTrue(testPanel.getClient() == c);
  }
 
  /**
   * Tests setGcw and getGcw methods
   */
  public void testSetGetGcw() {
    GlobalChatWindow gcw = new GlobalChatWindow();
    
    testPanel.setGcw(gcw);
    assertTrue(testPanel.getGcw() == gcw);
  }

  /**
   * Test setGd and getGd methods
   */
  public void testSetGetGd() {
    GameOptionsDialog gd = new GameOptionsDialog();
    
    testPanel.setGd(gd);
    assertTrue(testPanel.getGd() == gd);
  }

  /**
   * Test setCreating and isCreating methods
   */
  public void testSetIsCreating() {
    testPanel.setCreating(true);
    assertTrue(testPanel.isCreating());
  }

  /**
   * Creates a new test suite
   * @return The new Test class with the test suite in it
   */
  public static Test suite() {
    TestSuite suite = new TestSuite();

    suite.addTest(new TestGlobalChatPanel("testSetGetClient"));
    suite.addTest(new TestGlobalChatPanel("testSetGetGcw"));
    suite.addTest(new TestGlobalChatPanel("testSetGetGd"));
    suite.addTest(new TestGlobalChatPanel("testSetIsCreating"));

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
  
} // class TestGlobalChatPanel
