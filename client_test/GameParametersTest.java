import junit.framework.*;
import java.util.StringTokenizer;

public class GameParametersTest extends TestCase
{

	
	GameParameters tester1;
	GameParameters tester2;
	GameParameters tester3;
    GameParameters tester4;
		
	public GameParametersTest(String name){
	 	super(name);
    }

    public static Test suite(){
		return new TestSuite(GameParametersTest.class);
    }

	public void setUp(){
		tester1 = new GameParameters();
		tester2 = new GameParameters();
		tester3 = new GameParameters();
        tester4 = new GameParameters();
	}/*end of setUp()*/
	
	public void testsetRandomSeed() {
		long test = 178;
		long test2;
		boolean result = false;
		tester1.setRandomSeed(test);
		test2 = tester1.getRandomSeed();
		if (test == test2)
		{
			result = true;
		}
		Assert.assertTrue(result);
	}
	
	public void testsetStartingMoney() {
		int test = 45678;
		int test2;
		boolean result = false;
		tester1.setStartingMoney(test);
		test2 = tester1.getStartingMoney();
		if (test == test2)
		{
			result = true;
		}
		Assert.assertTrue(result);
	}

	public void testsetInterestRate() {
		int test = 9;
		int test2;
		boolean result = false;
		tester1.setInterestRate(test);
		test2 = tester1.getInterestRate();
		if (test == test2)
		{
			result = true;
		}
		Assert.assertTrue(result);
	}

    public void testsetWindBehavior() {
		int test = 46;
		int test2;
		boolean result = false;
		tester1.setWindBehavior(test);
		test2 = tester1.getWindBehavior();
		if (test == test2)
		{
			result = true;
		}
		Assert.assertTrue(result);
    }
    
    public void testsetMaxWind() {
		int test = 45678;
		int test2;
		boolean result = false;
		tester1.setMaxWind(test);
		test2 = tester1.getMaxWind();
		if (test == test2)
		{
			result = true;
		}
		Assert.assertTrue(result);
    }

    public void testsetWallBehavior() {
		int test = 48;
		int test2;
		boolean result = false;
		tester1.setWallBehavior(test);
		test2 = tester1.getWallBehavior();
		if (test == test2)
		{
			result = true;
		}
		Assert.assertTrue(result);
    }
    
    public void testsetIsCave() {
		boolean test = false;
		boolean test2;
		boolean result = false;
		tester1.setIsCave(test);
		test2 = tester1.getIsCave();
		if (test == test2)
		{
			result = true;
		}
		Assert.assertTrue(result);
    }

    public void testsetNumRounds() {
		int test = 7;
		int test2;
		boolean result = false;
		tester1.setNumRounds(test);
		test2 = tester1.getNumRounds();
		if (test == test2)
		{
			result = true;
		}
		Assert.assertTrue(result);
    }

    public void testsetMaxPlayers() {
		int test = 6;
		int test2;
		boolean result = false;
		tester1.setMaxPlayers(test);
		test2 = tester1.getMaxPlayers();
		if (test == test2)
		{
			result = true;
		}
		Assert.assertTrue(result);
    }

    public void setName() {
		String test = "tester1";
		String test2;
		boolean result = false;
		tester1.setName(test);
		test2 = tester1.getName();
		if (test.equals(test2))
		{
			result = true;
		}
		Assert.assertTrue(result);
    }

	public void testsetLevelComplexity() {
		float test = 4;
		float test2;
		boolean result = false;
		tester1.setLevelComplexity(test);
		test2 = tester1.getLevelComplexity();
		if (test == test2)
		{
			result = true;
		}
		Assert.assertTrue(result);
    }

    public void testsetLevelSteepness() {
		int test = 7;
		int test2;
		boolean result = false;
		tester1.setLevelSteepness(test);
		test2 = tester1.getLevelSteepness();
		if (test == test2)
		{
			result = true;
		}
		Assert.assertTrue(result);
    }

    public void testsetGravity() {
   		int test = 58;
		int test2;
		boolean result = false;
		tester1.setGravity(test);
		test2 = tester1.getGravity();
		if (test == test2)
		{
			result = true;
		}
		Assert.assertTrue(result);
    }

    public void testsetExplosionSize() {
		float test = 1.5f;
		float test2;
		boolean result = false;
		tester1.setExplosionSize(test);
		test2 = tester1.getExplosionSize();
		if (test == test2)
		{
			result = true;
		}
		Assert.assertTrue(result);
    }

    public void testsetIsGamePrivate() {
        boolean test = false;
		boolean test2;
		boolean result = false;
		tester1.setIsGamePrivate(test);
		test2 = tester1.getIsGamePrivate();
		if (test == test2)
		{
			result = true;
		}
		Assert.assertTrue(result);
    }

    public void testsetPassword() {
        String test = "pwd";
		String test2;
		boolean result = false;
		tester1.setPassword(test);
		test2 = tester1.getPassword();
		if (test == test2)
		{
			result = true;
		}
		Assert.assertTrue(result);
    }
	
	//////////methods that do stuff////////


    public void testparseFormattedString() {
        StringTokenizer st = new StringTokenizer("NOOBS ONLY|10000|10|2|1111|true|10|8|1.37|365|1|1.5|false||29|159758", "|");
        tester2.parseFormattedString(st);
        boolean result = true;
        if (!(tester2.getName().equals("NOOBS ONLY"))) {
            result = false;
        }
        if (tester2.getStartingMoney() != 10000) {
            result = false;
        }
        if (tester2.getInterestRate() != 10) {
            result = false;
        }
        if (tester2.getWindBehavior() != 2) {
            result = false;
        }
        if (tester2.getWallBehavior() != 1111) {
            result = false;
        }
        if (tester2.getIsCave() != true) {
            result = false;
        }
        if (tester2.getNumRounds() != 10) {
            result = false;
        }
        if (tester2.getMaxPlayers() != 8) {
            result = false;
        }
        if (tester2.getLevelComplexity() != 1.37f) {
            result = false;
        }
        if (tester2.getLevelSteepness() != 365) {
            result = false;
        }
        if (tester2.getGravity() != 1) {
            result = false;
        }
        if (tester2.getExplosionSize() != 1.5) {
            result = false;
        }
        if (tester2.getIsGamePrivate() != false) {
            result = false;
        }
        if (tester2.getMaxWind() != 29) {
            result = false;
        }
        if (tester2.getRandomSeed() != 159758) {
            result = false;
        }
        Assert.assertTrue(result);

    }

    public void testgetFormattedString() {
        StringTokenizer st = new StringTokenizer("NOOBS ONLY|10000|10|2|1111|true|10|8|1.37|365|1|1.5|false||29|159758", "|");
        tester3.parseFormattedString(st);
        String test = tester3.getFormattedString();
        boolean result = false;
        if (test.equals("NOOBS ONLY|10000|10|2|1111|true|10|8|1.37|365|1|1.5|false||29|159758")) {
            result = true;
        }
        Assert.assertTrue(result);
    }

    public void testtoSting() {
        boolean result = false;
        String test;
        StringTokenizer st = new StringTokenizer("NOOBS ONLY|10000|10|2|1111|true|10|8|1.37|365|1|1.5|false||29|159758", "|");
        tester4.parseFormattedString(st);
        test = tester4.toString();
        if (test.equals("Name: NOOBS ONLY" 
            + "\nStarting Money: 10000"
            + "\nInterest Rate: 10"
            + "\nWind Behavior: 2" 
            + "\nWall Behavior: 1111" 
            + "\nCave: true" 
            + "\nNumber of Rounds: 10"
            + "\nMax Players: 8"
            + "\nLevel Complexity Factor: 1.37"
            + "\nLevel Steepness Factor: 365"
            + "\nGravity Factor: 1"
            + "\nExplosion Size Factor: 1.5"
            + "\nPrivate Game: false"
            + "\nMax Wind Velocity: 29")) {

            result = true;
        }
        Assert.assertTrue(result);
    }

	public static void main(String[] args) {
		junit.textui.TestRunner.run(suite());  
	}


}