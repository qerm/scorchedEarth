import junit.framework.*;

import java.util.Hashtable;

public class LoudMouthTest extends TestCase
{

//////any global variables needed///////	
	LoudMouth tester1;
	LoudMouth tester2;
	LoudMouth tester3;


/////////junit startup stuff///////////		
	
	public LoudMouthTest(String name){
	 	super(name);
    }

    public static Test suite(){
		return new TestSuite(LoudMouthTest.class);
    }

	public void setUp(){
		Hashtable ht = new Hashtable();
		tester1 = new LoudMouth(ht);
		tester2 = new LoudMouth(ht);
		tester3 = new LoudMouth(ht);
	}


////////////test contsructor//////////////
	
	public void testLoudMouth(){
		Hashtable ht = new Hashtable();
		boolean result = true;
		LoudMouth tester = new LoudMouth(ht);
		if (tester.getServerThreads().equals(null))
		{
			result = false;
		}
		Assert.assertTrue(result);
    }

//////test accessors and modifiers////////

	public void setServerThreads() {
        Hashtable test = new Hashtable();
		Hashtable test2;
		boolean result = false;
		tester1.setServerThreads(test);
		test2 = tester1.getServerThreads();
		if (test == test2)
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
