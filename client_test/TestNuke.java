import junit.framework.*;
import java.util.Vector;

public class TestNuke extends TestCase implements Constants
{

//////any global variables needed///////	
	Nuke tester;
	////Tomahawk tester2;
	////Tomahawk tester3;


/////////junit startup stuff///////////		
	
	public TestNuke(String name){
	 	super(name);
    }

    public static Test suite(){
		return new TestSuite(TestNuke.class);
    }

	public void setUp(){
        Vector v = new Vector();
        GameParameters gp = new GameParameters();
        GameModel gm = new GameModel(1,v,gp,true);
        tester = new Nuke(5,5,5,5,gm);	
        }


////////////test contsructor//////////////
//GameModel(long newRandomSeed, Vector newPlayerList,
//		   GameParameters newGParam, boolean newIsCave)	
	public void testNuke(){
        boolean result = false;
        Vector v = new Vector();
        GameParameters gp = new GameParameters();
        GameModel gm = new GameModel(1,v,gp,true);
        Nuke tester = new Nuke(5,5,5,5,gm);
        if (tester.isExploded()==false)
        {
            result = true;
        }
		
		Assert.assertTrue(result);
    }
	



//////test accessors and modifiers////////

    public void testgetType() {
        boolean result = false;
        int test = tester.getType();
        if (test == NUKE)
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
