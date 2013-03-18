import junit.framework.*;

public class ServerGUIControlTest extends TestCase
{

//////any global variables needed///////	
	ServerGUIControl tester1;
//	ServerGUIControl tester2;
//	ServerGUIControl tester3;


/////////junit startup stuff///////////		
	
	public ServerGUIControlTest(String name){
	 	super(name);
    }

    public static Test suite(){
		return new TestSuite(ServerGUIControlTest.class);
    }

	public void setUp(){
		ServerGUI sg = new ServerGUI();
		GateKeeper gk = new GateKeeper();
		tester1 = new ServerGUIControl(sg,gk);
	//	tester2 = new ServerGUIControl(sg,gk);
	//	tester3 = new ServerGUIControl(sg,gk);
	}


////////////test contsructor//////////////

	public void testServerGUIControl(){
		GateKeeper gk = new GateKeeper();
		ServerGUI sg = new ServerGUI();
		boolean result = true;
		ServerGUIControl tester = new ServerGUIControl(sg,gk);
		if (tester.getServerGUI().equals(null))
		{
			result = false;
		}
		if (tester.getGateKeeper().equals(null))
		{
			result = false;
		}
		Assert.assertTrue(result);
    }
	


//////test accessors and modifiers////////
	
    public void testsetServerGUI() {
        ServerGUI test = new ServerGUI();
		ServerGUI test2;
		boolean result = false;
		tester1.setServerGUI(test);
		test2 = tester1.getServerGUI();
		if (test == test2)
		{
			result = true;
		}
		Assert.assertTrue(result);
    }    

    public void testsetGateKeeper() {
        GateKeeper test = new GateKeeper();
		GateKeeper test2;
		boolean result = false;
		tester1.setGateKeeper(test);
		test2 = tester1.getGateKeeper();
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

	//	junit.textui.TestRunner
   //   .run(new TestSuite(ServerGUIControlTest.class));
	}


}