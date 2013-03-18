import junit.framework.TestCase;
import junit.framework.TestSuite;
import junit.framework.Test;

import java.awt.Container;
import javax.swing.JPanel;
import javax.swing.JFrame;
import java.util.Vector;
import java.util.Random;

/**
 * @author Wesley Reynolds and David McCann
 */
public class TestGameView extends TestCase {

  /**
   * 
   */
  private GameView testView;
  
  /**
   * Constructor for class
   * @param s name of test to add to a suite
     */
  public TestGameView (String s) {
    super(s);
  }
  
  /**
   * Initializes any variables needed
   */
  protected void setUp() {
    testView = new GameView();
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
    testView.setTheFrame(frame);
    assertTrue(testView.getTheFrame() == frame);
  }

  /**
   * Tests setCurrentPanel and getCurrentPanel methods
   */
  public void testSetGetCurrentPanel() {
    JPanel panel = new JPanel();

    testView.setCurrentPanel(panel);
    assertTrue(testView.getCurrentPanel() == panel);
  }

  /**
   * Tests setPlayerList and getPlayerList methods
   */
  public void testSetGetPlayerList() {
    Vector playerList = new Vector();
    playerList.add("Player1");
    playerList.add("Player2");
    
    testView.setPlayerList(playerList);
    assertTrue(
        testView.getPlayerList().toString().equals("[Player1, Player2]"));
  }

  /**
   * Tests setClient and getClient methods
   */
  public void testSetGetClient() {
    ClientThread client = new ClientThread();

    testView.setClient(client);
    assertTrue(testView.getClient() == client);
  }

  /**
   * Tests setGameChat and getGameChat methods
   */
  public void testSetGetGameChat() {
    GameChatWindow gc = new GameChatWindow();
    
    testView.setGameChat(gc);
    assertTrue(testView.getGameChat() == gc);
  }

  /**
   * Tests setC and getC methods
   */
  public void testSetGetC() {
    Container c = new Container();

    testView.setC(c);
    assertTrue(testView.getC() == c);
  }

  /**
   * Tests setCurrentRound and getCurrentRound methods
   */
  public void testSetGetCurrentRound() {
    testView.setCurrentRound(12);
    assertTrue(testView.getCurrentRound() == 12);
  }

  /**
   * Tests setGParam and getGParam methods
   */
  public void testSetGetGParam() {
    GameParameters gp = new GameParameters();

    testView.setGParam(gp);
    assertTrue(testView.getGParam() == gp);
  }
 
 /**
  * Tests the setSeedGen and getSeedGen methods
  */
  public void testSetGetSeedGen() {
    Random seed = new Random();
    
    testView.setSeedGen(seed);
    assertTrue(testView.getSeedGen() == seed);    
  }
 
 /**
  * Tests the setGameModel and getGameModel methods
  */
  public void testSetGetGameModel() {
      GameParameters gp = new GameParameters();
      Vector v = new Vector();
    GameModel m = new GameModel(1,v,gp,false);
    
    testView.setGameModel(m);
    assertTrue(testView.getGameModel() == m);
  }
 
  /**
   * Creates a new test suite
   * @return The new Test class with the test suite in it
   */
  public static Test suite() {
    TestSuite suite = new TestSuite();

    suite.addTest(new TestGameView("testSetGetTheFrame"));
    suite.addTest(new TestGameView("testSetGetCurrentPanel"));
    suite.addTest(new TestGameView("testSetGetPlayerList"));
    suite.addTest(new TestGameView("testSetGetClient"));
    suite.addTest(new TestGameView("testSetGetGameChat"));
    suite.addTest(new TestGameView("testSetGetC"));
    suite.addTest(new TestGameView("testSetGetCurrentRound"));
    suite.addTest(new TestGameView("testSetGetGParam"));
    suite.addTest(new TestGameView("testSetGetSeedGen"));
    suite.addTest(new TestGameView("testSetGetGameModel"));

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
  
} // class TestGameView
