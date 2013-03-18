import junit.framework.TestCase;
import junit.framework.TestSuite;
import junit.framework.Test;

import java.net.Socket;

/**
 * @author Wesley Reynolds and David McCann
 */
public class TestClientThread extends TestCase {

  /**
   * 
   */
  private ClientThread testThread;
  
  /**
   * Constructor for class
   * @param s name of test to add to a suite
     */
  public TestClientThread (String s) {
    super(s);
  }
  
  /**
   * Initializes any variables needed
   */
  protected void setUp() {
    testThread = new ClientThread();
  }
  
  /**
   * Closes any holes and tears down variables if needed
   */
  protected void tearDown() {
  }
  
  /**
   * Tests setUserName and getUserName methods
   */
  public void testSetGetUserName() {
    testThread.setUserName("JoeUser");
    assertTrue(testThread.getUserName().equals("JoeUser"));
    assertFalse(testThread.getUserName().equals("MikeUser"));
  }

  /**
   * Tests setSocket and getSocket methods
   */
  public void testSetGetSocket() {
    Socket socket = new Socket();

    testThread.setSocket(socket);
    assertTrue(testThread.getSocket() == socket);
  }

  /**
   * Tests setGv and getGv methods
   */
  public void testSetGetGv() {
    GameView gv = new GameView();

    testThread.setGv(gv);
    assertTrue(testThread.getGv() == gv);
  }

  /**
   * Tests setGameChat and getGameChat methods
   */
  public void testSetGetGameChat() {
    GameChatWindow gc = new GameChatWindow();

    testThread.setGameChat(gc);
    assertTrue(testThread.getGameChat() == gc);
  }

  /**
   * Tests setGlobalChat and getGlobalChat methods
   */
  public void testSetGetGlobalChat() {
    GlobalChatWindow gc = new GlobalChatWindow(false);
    
    testThread.setGlobalChat(gc);
    assertTrue(testThread.getGlobalChat() == gc);
  }

  /**
   * Tests setGamePlayerList and getGamePlayerList methods
   */
  public void testSetGetGamePlayerList() {
    testThread.setGamePlayerList("PlayerOne|PlayerTwo");
    assertTrue(testThread.getGamePlayerList().equals("PlayerOne|PlayerTwo"));
  }
  
  /**
   * Creates a new test suite
   * @return The new Test class with the test suite in it
   */
  public static Test suite() {
    TestSuite suite = new TestSuite();

    suite.addTest(new TestClientThread("testSetGetUserName"));
    suite.addTest(new TestClientThread("testSetGetSocket"));
    suite.addTest(new TestClientThread("testSetGetGv"));
    suite.addTest(new TestClientThread("testSetGetGameChat"));
    suite.addTest(new TestClientThread("testSetGetGlobalChat"));
    suite.addTest(new TestClientThread("testSetGetGamePlayerList"));
    
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
  
} // class TestClientThread
