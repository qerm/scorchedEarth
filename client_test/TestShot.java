import junit.framework.*;
import java.util.Vector;
public class TestShot extends TestCase implements Constants
{

//////any global variables needed///////	
	Shot tester1;


/////////junit startup stuff///////////		
	
	public TestShot(String name){
	 	super(name);
    }

    public static Test suite(){
		return new TestSuite(TestShot.class);
    }

	public void setUp(){
		Vector v = new Vector();
        GameParameters gp = new GameParameters();
        GameModel gm = new GameModel(1,v,gp,true);
        tester1 = new Spider(5,5,5,5,gm);	
	}


////////////test contsructor//////////////

//////test accessors and modifiers////////
	
	public void testsetX() {
        boolean result = true;
        tester1.setY(5);
        tester1.setX(3);
        int y = tester1.getY();
        int x = tester1.getX();
        if (y != 5)
        {
            result = false;
        }
        if (x != 3)
        {
            result = false;
        }
        Assert.assertTrue(result);
    }

    public void testsetGameModel() {
        boolean result = false;
        Vector v = new Vector();
        GameParameters gp = new GameParameters();
        GameModel gm = new GameModel(1,v,gp,true);
        tester1.setGameModel(gm);
        if (tester1.getGameModel().equals(gm))
        {
            result = true;
        }
        Assert.assertTrue(result);
    }

    public void testsetVx() {
        boolean result = true;
        tester1.setVx(1.5f);
        tester1.setVy(1.4f);
        if (tester1.getVx() != (1.5f))
        {
            result = false;
        }
        if (tester1.getVy() != (1.4f))
        {
            result = false;
        }
        Assert.assertTrue(result);
    }

    public void testsetExploded() {
        boolean result = false;
        tester1.setExploded(false);
        if (tester1.isExploded() == false)
        {
            result = true;
        }
        Assert.assertTrue(result);
    }

    public void testsetRadius() {
        boolean result = false;
        tester1.setRadius(5);
        if (tester1.getRadius() == 5)
        {
            result = true;
        }
    }

    public void testsetShooter() {
        boolean result = false;
        tester1.setShooter("thisguy");
        if (tester1.getShooter().equals("thisguy"))
        {
            result = true;
        }
        Assert.assertTrue(result);
    }


//////test real methods////////



	
	
/////////main with junit call////////////

	public static void main(String[] args) {
		junit.textui.TestRunner.run(suite());  
	}


}
