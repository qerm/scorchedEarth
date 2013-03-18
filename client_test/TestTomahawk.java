import junit.framework.*;
import java.util.Vector;

public class TestTomahawk extends TestCase implements Constants
{

//////any global variables needed///////	
	Tomahawk tester;
	////Tomahawk tester2;
	////Tomahawk tester3;


/////////junit startup stuff///////////		
	
	public TestTomahawk(String name){
	 	super(name);
    }

    public static Test suite(){
		return new TestSuite(TestTomahawk.class);
    }

	public void setUp(){
        Vector v = new Vector();
        GameParameters gp = new GameParameters();
        GameModel gm = new GameModel((long)1,v,gp,true);
        tester = new Tomahawk(5,5,5,5,gm);	
        }


////////////test contsructor//////////////
//GameModel(long newRandomSeed, Vector newPlayerList,
//		   GameParameters newGParam, boolean newIsCave)	
	public void testTomahawk(){
        boolean result = false;
        Vector v = new Vector();
        GameParameters gp = new GameParameters();
        GameModel gm = new GameModel((long)1,v,gp,true);
        Tomahawk tester = new Tomahawk(5,5,5,5,gm);
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
        if (test == TOMAHAWK)
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