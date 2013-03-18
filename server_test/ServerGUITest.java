import junit.framework.*;
import java.util.Vector;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import java.awt.Container;

public class ServerGUITest extends TestCase
{

//////any global variables needed///////	
	ServerGUI tester1;
	ServerGUI tester2;
	ServerGUI tester3;


/////////junit startup stuff///////////		
	
	public ServerGUITest(String name){
	 	super(name);
    }

    public static Test suite(){
		return new TestSuite(ServerGUITest.class);
    }

	public void setUp(){
		tester1 = new ServerGUI();
		tester2 = new ServerGUI();
		tester3 = new ServerGUI();
	}


////////////test contsructor//////////////

	public void testServerGUI(){
		ServerGUI tester = new ServerGUI();
		boolean result = true;
		if (tester.equals(null))
		{
			result = false;
		}
		Assert.assertTrue(result);
    }
	

//////test accessors and modifiers////////

    public void testsetSP() {
        JScrollPane test = new JScrollPane();
        JScrollPane test2;
        boolean result = false;
        tester1.setSp(test);
        test2 = tester1.getSp();
        if (test.equals(test2))
        {
            result = true;
        }
        Assert.assertTrue(result);
    }

	public void testsetEvent() {
		GateKeeper gk = new GateKeeper();
		ServerGUI sg = new ServerGUI();
        ServerGUIControl test = new ServerGUIControl(sg,gk);
		ServerGUIControl test2;
		boolean result = false;
		tester1.setEvent(test);
		test2 = tester1.getEvent();
		if (test.equals(test2))
		{
			result = true;
		}
		Assert.assertTrue(result);
    }

	public void testsetBtClose() {
        JButton test = new JButton();
		JButton test2;
		boolean result = false;
		tester1.setBtClose(test);
		test2 = tester1.getBtClose();
		if (test.equals(test2))
		{
			result = true;
		}
		Assert.assertTrue(result);
		
    }

	public void testsetBtUserInfo() {
        JButton test = new JButton();
		JButton test2;
		boolean result = false;
		tester1.setBtUserInfo(test);
		test2 = tester1.getBtUserInfo();
		if (test.equals(test2))
		{
			result = true;
		}
		Assert.assertTrue(result);
    }

    public void testsetBtGameInfo() {
        JButton test = new JButton();
		JButton test2;
		boolean result = false;
		tester1.setBtGameInfo(test);
		test2 = tester1.getBtGameInfo();
		if (test.equals(test2))
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

    public void testsetLbConselLabel() {
        JLabel test = new JLabel();
		JLabel test2;
		boolean result = false;
		tester1.setLbConselLabel(test);
		test2 = tester1.getLbConselLabel();
		if (test.equals(test2))
		{
			result = true;
		}
		Assert.assertTrue(result);
    }

    public void testsetLbGamesLabel() {
        JLabel test = new JLabel();
		JLabel test2;
		boolean result = false;
		tester1.setLbGamesLabel(test);
		test2 = tester1.getLbGamesLabel();
		if (test.equals(test2))
		{
			result = true;
		}
		Assert.assertTrue(result);
    }

    public void testsetLbUsersLabel() {
        JLabel test = new JLabel();
		JLabel test2;
		boolean result = false;
		tester1.setLbUsersLabel(test);
		test2 = tester1.getLbUsersLabel();
		if (test.equals(test2))
		{
			result = true;
		}
		Assert.assertTrue(result);
    }

    public void testsetLsGames() {
        JList test = new JList();
		JList test2;
		boolean result = false;
		tester1.setLsGames(test);
		test2 = tester1.getLsGames();
		if (test.equals(test2))
		{
			result = true;
		}
		Assert.assertTrue(result);
    }

    public void testsetLsUsers() {
        JList test = new JList();
		JList test2;
		boolean result = false;
		tester1.setLsUsers(test);
		test2 = tester1.getLsUsers();
		if (test.equals(test2))
		{
			result = true;
		}
		Assert.assertTrue(result);
    }

    public void testsetPnFrame() {
		Container test = new Container();
		Container test2;
		boolean result = false;
		tester1.setPnFrame(test);
		test2 = tester1.getPnFrame();
		if (test.equals(test2))
		{
			result = true;
		}
		Assert.assertTrue(result);
    }

    public void testsetTaConsel() {
        JTextArea test = new JTextArea();
		JTextArea test2;
		boolean result = false;
		tester1.setTaConsel(test);
		test2 = tester1.getTaConsel();
		if (test.equals(test2))
		{
			result = true;
		}
		Assert.assertTrue(result);
    }

    public void testsetTheServerGui() {
        ServerGUI test = new ServerGUI();
		ServerGUI test2;
		boolean result = false;
		tester1.setTheServerGui(test);
		test2 = tester1.getTheServerGui();
		if (test.equals(test2))
		{
			result = true;
		}
		Assert.assertTrue(result);
    }


//////test real methods////////


    public void testupdateUserList() {
        JList list = new JList();
        Vector v = new Vector();
        boolean result = false;
        v.add("one");
        v.add("two");
        tester1.setLsUsers(list);
        tester1.updateUserList(v);
        String test = 
            (String) tester1.getLsUsers().getModel().getElementAt(0);
        if (test.equals("one"))
        {
            result = true;
        }
        Assert.assertTrue(result);
    }

    public void testupdateGameList() {
        JList list = new JList();
        Vector v = new Vector();
        boolean result = false;
        v.add("one");
        v.add("two");
        tester1.setLsGames(list);
        tester1.updateGameList(v);
        String test = 
            (String) tester1.getLsGames().getModel().getElementAt(0);
        if (test.equals("one"))
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