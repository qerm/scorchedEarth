import junit.framework.TestCase;
import junit.framework.TestSuite;
import junit.framework.Test;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Container;

/**
 * @author Wesley Reynolds and David McCann
 */
public class TestGlobalChatWindow extends TestCase {

  /**
   * 
   */
  private GlobalChatWindow testWindow;
  
  /**
   * Constructor for class
   * @param s name of test to add to a suite
     */
  public TestGlobalChatWindow (String s) {
    super(s);
  }
  
  /**
   * Initializes any variables needed
   */
  protected void setUp() {
    testWindow = new GlobalChatWindow(false);
  }
  
  /**
   * Closes any holes and tears down variables if needed
   */
  protected void tearDown() {
  }

  /**
   * Tests setTheFrame and getTheFrame methods
   */
  public void testSetGetTheFrame() {
    JFrame frame = new JFrame();
    
    testWindow.setTheFrame(frame);
    assertTrue(testWindow.getTheFrame() == frame);
  }

  /**
   * Tests setCurrentPanel and getCurrentPanel methods
   */
  public void testSetGetCurrentPanel() {
    JPanel panel = new JPanel();

    testWindow.setCurrentPanel(panel);
    assertTrue(testWindow.getCurrentPanel() == panel);
  }

  /**
   * Tests setC and getC methods
   */
  public void testSetGetC() {
    Container c = new Container();

    testWindow.setC(c);
    assertTrue(testWindow.getC() == c);
  }

  /**
   * Tests setClient and getClient methods
   */
  public void testSetGetClient() {
    ClientThread client = new ClientThread();

    testWindow.setClient(client);
    assertTrue(testWindow.getClient() == client);
  }

  /**
   * Tests setGcw and getGcw methods
   */
  public void testSetGetGcw() {
    GameChatWindow gcw = new GameChatWindow();

    testWindow.setGcw(gcw);
    assertTrue(testWindow.getGcw() == gcw);
  }

  /**
   * Tests setGParam and getGParam methods
   */
  public void testSetGetGParam() {
    GameParameters params = new GameParameters();

    testWindow.setGParam(params);
    assertTrue(testWindow.getGParam() == params);
  }

  /**
   * Tests setLoggedIn and isLoggedIn methods
   */
  public void testSetIsLoggedIn() {
    testWindow.setLoggedIn(true);
    assertTrue(testWindow.isLoggedIn());
  }

  /**
   * Tests setInGame and getInGame methods
   */
  public void testSetGetInGame() {
    testWindow.setInGame(true);
    assertTrue(testWindow.getInGame());
  }

  /**
   * Creates a new test suite
   * @return The new Test class with the test suite in it
   */
  public static Test suite() {
    TestSuite suite = new TestSuite();

    suite.addTest(new TestGlobalChatWindow("testSetGetTheFrame"));
    suite.addTest(new TestGlobalChatWindow("testSetGetCurrentPanel"));
    suite.addTest(new TestGlobalChatWindow("testSetGetC"));
    suite.addTest(new TestGlobalChatWindow("testSetGetClient"));
    suite.addTest(new TestGlobalChatWindow("testSetGetGcw"));
    suite.addTest(new TestGlobalChatWindow("testSetGetGParam"));
    suite.addTest(new TestGlobalChatWindow("testSetIsLoggedIn"));
    suite.addTest(new TestGlobalChatWindow("testSetGetInGame"));

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
  
} // class TestGlobalChatWindow
