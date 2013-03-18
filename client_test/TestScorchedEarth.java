import junit.framework.*;
import java.awt.Container;

public class TestScorchedEarth extends TestCase
{

//////any global variables needed///////	
	ScorchedEarth tester1;



/////////junit startup stuff///////////		
	
	public TestScorchedEarth(String name){
	 	super(name);
    }

    public static Test suite(){
		return new TestSuite(TestScorchedEarth.class);
    }

	public void setUp(){
		tester1 = new ScorchedEarth();
	}


////////////test contsructor//////////////


//////test accessors and modifiers////////
	
	public void testsetC() {
        Container c = new Container();
        tester1.setC(c);
        Assert.assertTrue(tester1.getC().equals(c));
    }


//////test real methods////////



	
	
/////////main with junit call////////////

	public static void main(String[] args) {
		junit.textui.TestRunner.run(suite());  
	}


}
