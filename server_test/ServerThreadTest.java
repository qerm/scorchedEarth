import junit.framework.*;

import java.net.Socket;
import java.io.PrintWriter;
import java.io.OutputStreamWriter;
import java.io.IOException;

public class ServerThreadTest extends TestCase
{

	ServerThread tester1;
	ServerThread tester2;
	ServerThread tester3;
		
	public ServerThreadTest(String name){
	 	super(name);
    }

    public static Test suite(){
		return new TestSuite(ServerThreadTest.class);
    }


	public void setUp(){
		GateKeeper gk = new GateKeeper();
		Socket s = new Socket();
		tester1 = new ServerThread(gk,s);
		tester2 = new ServerThread(gk,s);
		tester3 = new ServerThread(gk,s);
	}/*end of setUp()*/

    public void testServerThread() {
        boolean result = false;
        Socket s = new Socket();
        GateKeeper gk = new GateKeeper();
        ServerThread tester = new ServerThread(gk, s);
        if (tester.getGateKeeper().equals(gk))
        {
            result = true;
        }
        if (tester.getSocket().equals(s))
        {
            result = true;
        }
        if (tester.getIsSynched()==false)
        {
            result = true;
        }
        if (tester.getProfile() != null)
        {
            result = true;
        }
        Assert.assertTrue(result);
    }

    public void testhashCode() {
        boolean result = false;
        Socket s = new Socket();
        GateKeeper gk = new GateKeeper();
        ServerThread tester = new ServerThread(gk, s);
        Profile p = new Profile();
        p.setUserName("asdf");
        tester.setProfile(p);
        int test = ((tester.getProfile()).getUserName()).hashCode();
        if (test == tester.hashCode()) {
            result = true;
        }
        Assert.assertTrue(result);
    }

    public void testtoString() {
        boolean result = false;
        Profile p = new Profile();
        p.setUserName("asdf");
        tester3.setProfile(p);
        if (tester3.toString() == "asdf")
        {
            result = true;
        }
        Assert.assertTrue(result);
    }

    public void testsetPw() {
		try
		{
			Socket s = new Socket();
	        PrintWriter test = new PrintWriter(new OutputStreamWriter(s.getOutputStream()));
			PrintWriter test2;
		    boolean result = false;
		    tester1.setPw(test);
			test2 = tester1.getPw();
			if (test.equals(test2))
			{
				result = true;
			}
			Assert.assertTrue(result);	
		}
		catch (IOException ie)
		{
			System.out.println("IO EXCEPTION!!");
		}
		
    }//end of testsetPw()

	public void testsetSocket() {
		Socket test = new Socket();
		Socket test2;
		boolean result = false;
		tester1.setSocket(test);
		test2 = tester1.getSocket();
		if (test.equals(test2))
			{
				result = true;
			}
        Assert.assertTrue(result);
	}//end of testsetSocket()

	public void testsetIsAlive() {
		boolean test = true;
		boolean test2;
		boolean result = false;
		tester1.setIsAlive(test);
		test2 = tester1.getIsAlive();
		if (test == test2)
			{
				result = true;
			}
        Assert.assertTrue(result);
	}//end of testsetIsAlive

	public void testsetGameMaster() {
		GameParameters gparam = new GameParameters();
		GateKeeper gk = new GateKeeper();
		GameMaster test = new GameMaster(gparam, gk);
		GameMaster test2;
		boolean result = false;
		tester1.setGameMaster(test);
		test2 = tester1.getGameMaster();
		if (test.equals(test2))
			{
				result = true;
			}
        Assert.assertTrue(result);
	}//end of testsetGameMaster()

	public void testsetGateKeeper() {
		GateKeeper test = new GateKeeper();
		GateKeeper test2;
		boolean result = false;
		tester1.setGateKeeper(test);
		test2 = tester1.getGateKeeper();
		if (test.equals(test2))
			{
				result = true;
			}
        Assert.assertTrue(result);
	}//end of testsetGateKeeper()

	public void testsetColor() {
		int test = 5;
		int test2;
		boolean result = false;
		tester1.setColor(test);
		test2 = tester1.getColor();
   		if (test == test2)
			{
				result = true;
			}
        Assert.assertTrue(result);
	}//end of testsetColor()

	public void testsetProfile() {
		Profile test = new Profile();
		Profile test2;
		boolean result = false;
		tester1.setProfile(test);
		test2 = tester1.getProfile();
		if (test.equals(test2))
			{
				result = true;
			}
        Assert.assertTrue(result);
	}//end of testsetProfile

	public static void main(String[] args) {
		junit.textui.TestRunner.run(suite());  
	}


}
