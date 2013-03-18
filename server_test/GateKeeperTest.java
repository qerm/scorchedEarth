import junit.framework.*;

import java.net.ServerSocket;
import java.util.Hashtable;
import java.io.IOException;

public class GateKeeperTest extends TestCase
{

//////any global variables needed///////	
	GateKeeper tester1;
	GateKeeper tester2;
	GateKeeper tester3;


/////////junit startup stuff///////////		
	
	public GateKeeperTest(String name){
	 	super(name);
    }

    public static Test suite(){
		return new TestSuite(GateKeeperTest.class);
    }

	public void setUp(){
		tester1 = new GateKeeper();
		tester2 = new GateKeeper();
		tester3 = new GateKeeper();
	}

////////////test contsructor//////////////
    public void testGateKeeper(){
        try
        {
        	ServerSocket ss = new ServerSocket();
            boolean result = true;
            GateKeeper tester = new GateKeeper(ss, null, null);
            if (tester.getGameMasters().equals(null))
            {
                result = false;
            }
            if (tester.getProfiles().equals(null))
            {
                result = false;
            }
            if (tester.getServerThreads().equals(null))
            {
                result = false;
            }
            if (tester.getLoudMouth().equals(null))
            {
                result = false;
            }
            if (tester.getServerSocket().equals(null))
            {
                result = false;
            }
            Assert.assertTrue(result);
            }
        catch (IOException ie)
        {
            System.out.println("IOException");
        }
		
    }


//////test accessors and modifiers////////

    public void testsetSgc() {
        GateKeeper gk = new GateKeeper();
        ServerGUI sg = new ServerGUI(1);
        ServerGUIControl test = new ServerGUIControl(sg,gk);
        ServerGUIControl test2;
        boolean result = false;
        tester1.setSgc(test);
        test2 = tester1.getSgc();
        if (test.equals(test2))
        {
            result = true;
        }
        Assert.assertTrue(result);
    }

    public void testsetPortNum() {
        int test = 45;
		int test2;
		boolean result = false;
		tester1.setPortNum(test);
		test2 = tester1.getPortNum();
		if (test == test2)
		{
			result = true;
		}
		Assert.assertTrue(result);
    }

    public void testsetRunning() {
        boolean test = false;
		boolean test2;
		boolean result = false;
		tester1.setRunning(test);
		test2 = tester1.getRunning();
		if (test == test2)
		{
			result = true;
		}
		Assert.assertTrue(result);
    }

    public void testsetGameMasters() {
		Hashtable test = new Hashtable();
		Hashtable test2;
		boolean result = false;
		tester1.setGameMasters(test);
		test2 = tester1.getGameMasters();
		if (test.equals(test2))
		{
			result = true;
		}
		Assert.assertTrue(result);
    }

    public void testsetLoudMouth(LoudMouth lm) {
		Hashtable ht = new Hashtable();
        LoudMouth test = new LoudMouth(ht);
		LoudMouth test2;
		boolean result = false;
		tester1.setLoudMouth(test);
		test2 = tester1.getLoudMouth();
		if (test.equals(test2))
		{
			result = true;
		}
		Assert.assertTrue(result);
    }

    public void testsetProfiles() {
        Hashtable test = new Hashtable();
		Hashtable test2;
		boolean result = false;
		tester1.setProfiles(test);
		test2 = tester1.getProfiles();
		if (test.equals(test2))
		{
			result = true;
		}
		Assert.assertTrue(result);
    }

    public void testsetSeverGUI() {
		ServerGUI test = new ServerGUI();
		ServerGUI test2;
		boolean result = false;
		tester1.setServerGUI(test);
		test2 = tester1.getServerGUI();
		if (test.equals(test2))
		{
			result = true;
		}
		Assert.assertTrue(result);
    }

    public void testsetServerSocket() {
		try
		{
			ServerSocket test = new ServerSocket();
			ServerSocket test2;
			boolean result = false;
			tester1.setServerSocket(test);
			test2 = tester1.getServerSocket();
			if (test.equals(test2))
			{
				result = true;
			}
			Assert.assertTrue(result);	
		}
		catch (IOException ie)
		{
			System.out.println("IO ERROR!!");
		}
        
    }

    public void setServerThreads() {
        Hashtable test = new Hashtable();
		Hashtable test2;
		boolean result = false;
		tester1.setServerThreads(test);
		test2 = tester1.getServerThreads();
		if (test.equals(test2))
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