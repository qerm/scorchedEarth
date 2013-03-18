import junit.framework.TestCase;
import junit.framework.TestSuite;
import junit.framework.Test;

/**
 * @author Wesley Reynolds and David McCann
 */
public class TestTank extends TestCase {

  /**
   * 
   */
  private LocalTank testTank;
  
  /**
   * Constructor for class
   * @param s name of test to add to a suite
     */
  public TestTank (String s) {
    super(s);
  }
  
  /**
   * Initializes any variables needed
   */
  protected void setUp() {
    testTank = new LocalTank();
  }
  
  /**
   * Closes any holes and tears down variables if needed
   */
  protected void tearDown() {
  }
  
  /**
   * Tests setColor and getColor methods
   */
  public void testSetGetColor() {
    testTank.setColor(1);
    assertTrue(testTank.getColor() == 1);
  }

  /**
   * Tests setXPos and getXPos methods
   */
  public void testSetGetXPos() {
    testTank.setXPos(123);
    assertTrue(testTank.getXPos() == 123);
  }

  /**
   * Tests setTurretAngle and getTurretAngle methods
   */
  public void testSetGetTurretAngle() {
    testTank.setTurretAngle(45);
    assertTrue(testTank.getTurretAngle() == 45);
  }

  /**
   * Tests setIsDead and getIsDead methods
   */
  public void testSetGetIsDead() {
    testTank.setIsDead(true);
    assertTrue(testTank.getIsDead());
  }

  /**
   * Tests setName and getName methods
   */
  public void testSetGetName() {
    testTank.setName("TankName");
    assertTrue(testTank.getName().equals("TankName"));
  }

  /**
   * Tests setArmor and getArmor methods
   */
  public void testSetGetArmor() {
    Armor testArmor = new Armor();

    testTank.setArmor(testArmor);
    assertTrue(testTank.getArmor() == testArmor);
  }

  /**
   * Tests setHp and getHp methods
   */
  public void testSetGetHp() {
    testTank.setHp(100);
    assertTrue(testTank.getHp() == 100);
  }
 
    public void testsetKiller() {
        boolean result = false;
        testTank.setKiller("me");
        if (testTank.getKiller().equals("me"))
        {
            result = true;
        }
        assertTrue(result);
    }

    public void testTank() {
        boolean result = true;
        Tank tester = new LocalTank("bill", 2);
        if (tester.getIsDead() == true)
        {
            result = false;
        }
        if (tester.getXPos() != (-1))
        {
            result = false;
        }
        if (tester.getColor()!= 2)
        {
            result = false;
        }
        if (tester.getTurretAngle() != (-1))
        {
            result = false;
        }
        if (tester.getHp() != 100)
        {
            result = false;
        }
        if (tester.getName().equals("bill"))
        {
            result = false;
        }
        if (tester.getArmor() == null)
        {
            result = false;
        }
        assertTrue(result);
        
    }

    public void testequals(){
        boolean result = true;
        Tank tank1 = new LocalTank();
        Tank tank2 = new LocalTank("bill",3);
        if (tank1.equals(tank2))
        {
            result = false;
        }
        assertTrue(result);
    }

    public void testtoString() {
        boolean result = false;
        Tank tester = new LocalTank("bill", 2);
        String test = tester.toString();
        if (test.equals("bill's Tank, with 100 hp"))
        {
            result = true;
        }
        assertTrue(result);
    }

  /**
   * Creates a new test suite
   * @return The new Test class with the test suite in it
   */
  public static Test suite() {
    TestSuite suite = new TestSuite();

    suite.addTest(new TestTank("testSetGetColor"));
    suite.addTest(new TestTank("testSetGetXPos"));
    suite.addTest(new TestTank("testSetGetTurretAngle"));
    suite.addTest(new TestTank("testSetGetIsDead"));
    suite.addTest(new TestTank("testSetGetName"));
    suite.addTest(new TestTank("testSetGetArmor"));
    suite.addTest(new TestTank("testSetGetHp"));

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
  
} // class TestTank
