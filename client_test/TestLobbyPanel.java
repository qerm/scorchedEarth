import junit.framework.TestCase;
import junit.framework.TestSuite;
import junit.framework.Test;
import javax.swing.JLabel;

/**
 * @author Wesley Reynolds and David McCann
 */
public class TestLobbyPanel extends TestCase {

  /**
   * 
   */
  private LobbyPanel testPanel;
  
  /**
   * Constructor for class
   * @param s name of test to add to a suite
     */
  public TestLobbyPanel (String s) {
    super(s);
  }
  
  /**
   * Initializes any variables needed
   */
  protected void setUp() {
    testPanel = new LobbyPanel();
  }
  
  /**
   * Closes any holes and tears down variables if needed
   */
  protected void tearDown() {
  }
  
  /**
   * Tests setGcp and getGcp methods
   */
  public void testSetGetGcp() {
    GameChatPanel gcp = new GameChatPanel();

    testPanel.setGcp(gcp);
    assertTrue(testPanel.getGcp() == gcp);
  }

  /**
   * Tests setClient and getClient methods
   */
  public void testSetGetClient() {
    ClientThread client = new ClientThread();

    testPanel.setClient(client);
    assertTrue(testPanel.getClient() == client);
  }

  /**
   * Tests setPlayers and getPlayers methods
   */
  public void testSetGetPlayers() {
    JLabel[] players = new JLabel[2];
    players[0] = new JLabel("");
    players[1] = new JLabel("");

    JLabel[] retplayers;
    
    testPanel.setPlayers(players);
    retplayers = testPanel.getPlayers();
    
    assertTrue(retplayers[0] == players[0]);
    assertTrue(retplayers[1] == players[1]);
  }

  /**
   * Tests setIsHost and getIsHost methods
   */
  public void testSetGetIsHost() {
    testPanel.setIsHost(true);
    assertTrue(testPanel.getIsHost());
  }

  /**
   * Creates a new test suite
   * @return The new Test class with the test suite in it
   */
  public static Test suite() {
    TestSuite suite = new TestSuite();

    suite.addTest(new TestLobbyPanel("testSetGetGcp"));
    suite.addTest(new TestLobbyPanel("testSetGetClient"));
    suite.addTest(new TestLobbyPanel("testSetGetPlayers"));
    suite.addTest(new TestLobbyPanel("testSetGetIsHost"));

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
  
} // class TestLobbyPanel
