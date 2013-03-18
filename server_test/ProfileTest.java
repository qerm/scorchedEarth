import junit.framework.*;

public class ProfileTest extends TestCase
{

//////any global variables needed///////	
	Profile tester1;
	Profile tester2;
	Profile tester3;


/////////junit startup stuff///////////		
	
	public ProfileTest(String name){
	 	super(name);
    }

    public static Test suite(){
		return new TestSuite(ProfileTest.class);
    }

	public void setUp(){
		tester1 = new Profile();
		tester2 = new Profile();
		tester3 = new Profile();
	}


////////////test contsructor//////////////
	


//////test accessors and modifiers////////
	
    public void testsetUserName() {
        String test = "tester1";
		String test2;
		boolean result = false;
		tester1.setUserName(test);
		test2 = tester1.getUserName();
		if (test.equals(test2))
		{
			result = true;
		}
		Assert.assertTrue(result);
    }

	public void testsetMoney() {
        long test = 4523;
		long test2;
		boolean result = false;
		tester1.setMoney(test);
		test2 = tester1.getMoney();
		if (test == test2)
		{
			result = true;
		}
		Assert.assertTrue(result);
    }

    public void testsetPassword() {
		String test = "tester2";
		String test2;
		boolean result = false;
		tester1.setPassword(test);
		test2 = tester1.getPassword();
		if (test.equals(test2))
		{
			result = true;
		}
		Assert.assertTrue(result);
    }


//////test real methods////////

    public void testtoString() {
        String test;
        boolean result = false;
        tester1.setUserName("bad2theBone");
        test = tester1.toString();
        if (test.equals("bad2theBone"))
        {
            result = true;
        }
        Assert.assertTrue(result);
    }


	
/////////main with junit call////////////

	public static void main(String[] args) {
		junit.textui.TestRunner.run(suite());  
	}


}