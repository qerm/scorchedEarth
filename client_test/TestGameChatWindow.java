import junit.framework.TestCase;
import junit.framework.TestSuite;
import junit.framework.Test;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Container;

import java.util.Vector;

/**
 * @author Wesley Reynolds and David McCann
 */
public class TestGameChatWindow extends TestCase implements Constants {

  /**
   * 
   */
  private GameChatWindow testWindow;
  
  /**
   * Constructor for class
   * @param s name of test to add to a suite
     */
  public TestGameChatWindow (String s) {
    super(s);
  }
  
  /**
   * Initializes any variables needed
   */
  protected void setUp() {
    testWindow = new GameChatWindow();
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
   * Tests setPlayerList and getPlayerList methods
   */
  public void testSetGetPlayerList() {
    Vector playerlist = new Vector();
    playerlist.add("Charlie");
    playerlist.add("Mike");
    
    testWindow.setPlayerList(playerlist);
    assertTrue(testWindow.getPlayerList().toString().equals("[Charlie, Mike]"));
  }

  /**
   * Tests setGameStarted and isGameStarted methods
   */
  public void testIsSetGameStarted() {
    testWindow.setGameStarted(true);
    assertTrue(testWindow.isGameStarted());
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
   * Tests isIsHost and setIsHost methods
   */
  public void testIsSetIsHost() {
    testWindow.setIsHost(true);
    assertTrue(testWindow.isIsHost());
  }
 
  /**
   * Tests setGameView and getGameView methods
   */
  public void testSetGetGameView() {
    GameView gv = new GameView();

    testWindow.setGameView(gv);
    assertTrue(testWindow.getGameView() == gv);
  }

  /**
   * Tests setGcw and getGcw methods
   */
  public void testSetGetGcw() {
    GlobalChatWindow gcw = new GlobalChatWindow(true);

    testWindow.setGcw(gcw);
    assertTrue(testWindow.getGcw() == gcw);
  }

/*
 * Tests To see player list is updated from DELIM delimited input
 
 public void testUpdatePlayerList() {
    String msg = "PLAYER_LIST|MIKE|2|JOHN|3|WES|1";
    Vector playerList;
    ClientThread ct = new ClientThread();
    ct.setUserName("MIKE");
    
    testWindow.updatePlayerList(msg);
    playerList = testWindow.getPlayerList();
    Tank tank = playerList.remove();
    assertTrue(tank.getName().equals("MIKE"));
    tank = playerList.remove();
    assertTrue(tank.getName().equals("JOHN"));
    tank = playerList.remove();
    assertTrue(tank.getName().equals("WES"));
}
*/
  /**
   * Creates a new test suite
   * @return The new Test class with the test suite in it
   */
  public static Test suite() {
    TestSuite suite = new TestSuite();

    suite.addTest(new TestGameChatWindow("testSetGetTheFrame"));
    suite.addTest(new TestGameChatWindow("testSetGetCurrentPanel"));
    suite.addTest(new TestGameChatWindow("testSetGetC"));
    suite.addTest(new TestGameChatWindow("testSetGetPlayerList"));
    suite.addTest(new TestGameChatWindow("testIsSetGameStarted"));
    suite.addTest(new TestGameChatWindow("testSetGetClient"));
    suite.addTest(new TestGameChatWindow("testIsSetIsHost"));
    suite.addTest(new TestGameChatWindow("testSetGetGameView"));
    suite.addTest(new TestGameChatWindow("testSetGetGcw"));

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
  
} // class TestGameChatWindow
