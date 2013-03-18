import junit.framework.*;

public class TestScorchedAmp extends TestCase
{

//////any global variables needed///////	
	ScorchedAmp tester1;


/////////junit startup stuff///////////		
	
	public TestScorchedAmp(String name){
	 	super(name);
    }

    public static Test suite(){
		return new TestSuite(TestScorchedAmp.class);
    }

	public void setUp(){
		tester1 = new ScorchedAmp();
	}


////////////test contsructor//////////////


//////test accessors and modifiers////////
	
	public void testgetIsSoundOn() {
        tester1.setIsSoundOn(true);
     	Assert.assertTrue(tester1.getIsSoundOn());
    }


//////test real methods////////



	
	
/////////main with junit call////////////

	public static void main(String[] args) {
		junit.textui.TestRunner.run(suite());  
	}


}
