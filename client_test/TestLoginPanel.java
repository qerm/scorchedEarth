import junit.framework.*;

public class TestLoginPanel extends TestCase
{

//////any global variables needed///////	
	LoginPanel tester1;



/////////junit startup stuff///////////		
	
	public TestLoginPanel(String name){
	 	super(name);
    }

    public static Test suite(){
		return new TestSuite(TestLoginPanel.class);
    }

	public void setUp(){
		tester1 = new LoginPanel();
	}


////////////test contsructor//////////////

	public void testsetClient() {
        ClientThread c = new ClientThread();
        tester1.setClient(c);
        Assert.assertTrue(tester1.getClient() == c);
    }

    public void testsetGcw() {
        GlobalChatWindow g = new GlobalChatWindow(false);
        tester1.setGcw(g);
        Assert.assertTrue(tester1.getGcw() == g);

    }



//////test accessors and modifiers////////

//////test real methods////////



	
	
/////////main with junit call////////////

	public static void main(String[] args) {
		junit.textui.TestRunner.run(suite());  
	}


}