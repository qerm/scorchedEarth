import java.util.Hashtable;
import junit.framework.*;
import java.util.*;
import java.net.Socket;
import java.io.IOException;
import java.util.StringTokenizer;

public class GameMasterTest extends TestCase
{

	
	 GameMaster tester1;
	 GameMaster tester2;
	 GameMaster tester3;
		
	public GameMasterTest(String name){
	 	super(name);
    }

    public static Test suite(){
		return new TestSuite(GameMasterTest.class);
    }

	public void setUp(){
	//public GameMaster(GameParameters gParam, GateKeeper gk)
		GameParameters gp = new GameParameters();
		GateKeeper gk = new GateKeeper();
		tester1 = new GameMaster(gp,gk);
		tester2 = new GameMaster(gp,gk);
		tester3 = new GameMaster(gp,gk);
	}/*end of setUp()*/

    public void testsetServerThreads() {
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

    public void testsetVector() {
        Vector test = new Vector();
        Vector test2;
        boolean result = false;
        tester1.setVector(test);
        test2 = tester1.getVector();
        if (test.equals(test2))
        {
            result = true;
        }
        Assert.assertTrue(result);
    }

    public void testsetHeadPlayer() {
        GateKeeper gk = new GateKeeper();
        Socket s = new Socket();
        ServerThread test = new ServerThread(gk,s);
        ServerThread test2;
        boolean result = false;
        tester1.setHeadPlayer(test);
        test2 = tester1.getHeadPlayer();
        if (test.equals(test2))
        {
            result = true;
        }
        Assert.assertTrue(result);
    }

    public void testsetLoudMouth() {
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
    

     
    public void testsetGameParams() {
        GameParameters test = new GameParameters();
		GameParameters test2;
		boolean result = false;
		tester1.setGameParams(test);
		test2 = tester1.getGameParams();
		if (test.equals(test2))
		{
			result = true;
		}
		Assert.assertTrue(result);
    }

	public void testsetIsJoinable() {
        boolean test = false;
		boolean test2;
		boolean result = false;
		tester1.setIsJoinable(test);
		test2 = tester1.getIsJoinable();
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
		if (test.equals(test2))
		{
			result = true;
		}
		Assert.assertTrue(result);
    }
        
    public void testsetIsGameStarted() {
		boolean test = false;
		boolean test2;
		boolean result = false;
		tester1.setIsGameStarted(test);
		test2 = tester1.getIsGameStarted();
		if (test == test2)
		{
			result = true;
		}
		Assert.assertTrue(result);
    }
        
    public void testsetRoundNumber() {
        int test = 8;
		int test2;
		boolean result = false;
		tester1.setRoundNumber(test);
		test2 = tester1.getRoundNumber();
		if (test == test2)
		{
			result = true;
		}
		Assert.assertTrue(result);
    }
      
    public void testsetSyncCount() {
        int test = 673;
		int test2;
		boolean result = false;
		tester1.setSyncCount(test);
		test2 = tester1.getSyncCount();
		if (test == test2)
		{
			result = true;
		}
		Assert.assertTrue(result);
    }
        
    public void testsetGameState() {
        int test = 11;
		int test2;
		boolean result = false;
		tester1.setGameState(test);
		test2 = tester1.getGameState();
		if (test == test2)
		{
			result = true;
		}
		Assert.assertTrue(result);
    }
        
    public void testsetNumDead() {
        int test = 3;
		int test2;
		boolean result = false;
		tester1.setNumDead(test);
		test2 = tester1.getNumDead();
		if (test == test2)
		{
			result = true;
		}
		Assert.assertTrue(result);
    }
        
    public void testsetTurn() {
        int test = 2;
		int test2;
		boolean result = false;
		tester1.setTurn(test);
		test2 = tester1.getTurn();
		if (test == test2)
		{
			result = true;
		}
		Assert.assertTrue(result);
    }	

    public void testGameMaster() {
        GameParameters gp = new GameParameters();
        GateKeeper gk = new GateKeeper();
        GameMaster tester = new GameMaster(gp, gk);
        boolean result = true;
        if (tester.getGateKeeper() == null) {
            result = false;
        }
        if (tester.getGameParams() == null) {
            result = false;
        }
        if (tester.getGameState() != 8) {
            result = false;
        }
        if (tester.getIsGameStarted() == true) {
            result = false;
        }
        if (tester.getIsJoinable() == false) {
            result = false;
        }
        if (tester.getServerThreads() == null) {
            result = false;
        }
        if (tester.getLoudMouth() == null) {
            result = false;
        }
        Assert.assertTrue(result);
    }
       
    public void testtoString() {
        boolean result = false;
        GameParameters gp = new GameParameters();
        gp.setName("asdf");
        GateKeeper gk = new GateKeeper();
        GameMaster tester = new GameMaster(gp, gk);
        String test = tester.toString();
        if (test.equals("asdf")) {
            result = true;
        }
        Assert.assertTrue(result);

    }

    public void testpickColor() {
        boolean result = false;
            GameParameters gp = new GameParameters();
            gp.setName("asdf");
            GateKeeper gk = new GateKeeper();
            GameMaster tester = new GameMaster(gp, gk);
            
                Socket s = new Socket();
            
            ServerThread st = new ServerThread(new GateKeeper(), s);
            tester.pickColor(st);
            if (st.getColor() != 1) {
                result = true;
            }
        Assert.assertTrue(result);
       
    }

	public static void main(String[] args) {
		junit.textui.TestRunner.run(suite());  
	}


}