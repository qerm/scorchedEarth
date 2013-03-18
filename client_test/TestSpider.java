import junit.framework.*;
import java.util.Vector;

public class TestSpider extends TestCase implements Constants
{

//////any global variables needed///////	
	Spider tester1;


/////////junit startup stuff///////////		
	
	public TestSpider(String name){
	 	super(name);
    }

    public static Test suite(){
		return new TestSuite(TestSpider.class);
    }

	public void setUp(){
        Vector v = new Vector();
        GameParameters gp = new GameParameters();
        GameModel gm = new GameModel(1,v,gp,true);
        tester1 = new Spider(5,5,5,5,gm);	
	}


////////////test contsructor//////////////
	
	public void testSpider(){
		boolean result = true;
        if (tester1.isExploded() == true)
        {
            result = false;
        }
        if (tester1.isHead() == false)
        {
            result = false;
        }
		Assert.assertTrue(result);
    }
	



//////test accessors and modifiers////////
	
	public void testgetType() {
		int test2;
		boolean result = false;
		test2 = tester1.getType();
		if (test2 == SPIDER)
		{
			result = true;
		}
		Assert.assertTrue(result);
    }

    public void testisHead() {
        boolean r = true;
        tester1.setHead(true);
        if (tester1.isHead() == false)
        {
            r = false;
        }
        Assert.assertTrue(r);
    }


//////test real methods////////



	
	
/////////main with junit call////////////

	public static void main(String[] args) {
		junit.textui.TestRunner.run(suite());  
	}


}