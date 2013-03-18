import junit.framework.TestCase;
import junit.framework.TestSuite;
import junit.framework.Test;

import javax.swing.JLabel;

/**
 * @author Wesley Reynolds and David McCann
 */
public class TestShopPanel extends TestCase {

  /**
   * 
   */
  private ShopPanel testPanel;
  
  /**
   * Constructor for class
   * @param s name of test to add to a suite
     */
  public TestShopPanel (String s) {
    super(s);
  }
  
  /**
   * Initializes any variables needed
   */
  protected void setUp() {
    testPanel = new ShopPanel();
  }
  
  /**
   * Closes any holes and tears down variables if needed
   */
  protected void tearDown() {
  }
  
  /**
   * Tests setWeaponsButtons and getWeaponsButtons methods
   */
  public void testSetGetMyWeapons() {
      JLabel jl = new JLabel();
    testPanel.setMyWeapons(jl);
    assertTrue(testPanel.getMyWeapons() == jl);
  }

  /**
   * Tests setArmorButtons and getArmorButtons methods
   */
  public void testSetGetMyArmor() {
    JLabel jl = new JLabel();
    testPanel.setMyArmor(jl);
    assertTrue(testPanel.getMyArmor() ==jl);
  }

  /**
   * Tests setMyTank and getMyTank methods
   */
  public void testSetGetMyTank() {
    LocalTank tank = new LocalTank();

    testPanel.setMyTank(tank);
    assertTrue(testPanel.getMyTank() == tank);
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
   * Tests setGv and getGv methods
   */
  public void testSetGetGv() {
    GameView gv = new GameView();

    testPanel.setGv(gv);
    assertTrue(testPanel.getGv() == gv);
  }
 
 public void testsetWaiting() {
    testPanel.setWaiting(false);
    assertTrue(testPanel.isWaiting() == false);
 }
  /**
   * Creates a new test suite
   * @return The new Test class with the test suite in it
   */
  public static Test suite() {
    TestSuite suite = new TestSuite();

    suite.addTest(new TestShopPanel("testSetGetMyWeapons"));
    suite.addTest(new TestShopPanel("testSetGetMyArmor"));
    suite.addTest(new TestShopPanel("testSetGetMyTank"));
    suite.addTest(new TestShopPanel("testSetGetClient"));
    suite.addTest(new TestShopPanel("testSetGetGv"));

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
  
} // class TestShopPanel
