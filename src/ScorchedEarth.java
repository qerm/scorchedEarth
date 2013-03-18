import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Container;
import java.awt.GridLayout;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Dimension;

import javax.swing.Timer;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import java.awt.Graphics;

/**
 * ScorchedEarth.java, the splashscreen class
 * @author Team QERM;
 */
public class ScorchedEarth extends JPanel implements ActionListener {

/**
 * The window
 */
  private JFrame theFrame;

/**
 * The window
 */
  private ImageIcon ii = new ImageIcon("../media/tanksplash.jpg");

/**
 * True until timer fires
 */
  private boolean notYet = true;

  /** 
  * Timer for splash screen
  */
  private Timer timer;

  /**
   * reference that will hold theFrame's content pane
   */
  private Container c;
  
  /**
   * Get the C value.
   * @return the C value.
   */
  public Container getC() {
    return c;
  }

  /**
   * Set the C value.
   * @param newC The new C value.
   */
  public void setC(Container newC) {
    this.c = newC;
  }


  /**
   * closes window
   */  
  public void closeWindow() {
    theFrame.setVisible(false);
    theFrame.dispose();
    
  }
  
  /**
   * Initializes all the JComponents
   */
  public void init() {
    theFrame = new JFrame("Scorched Earth");
    c = theFrame.getContentPane();
    c.setLayout(new GridLayout(1, 1));
    theFrame.addWindowListener(new WindowAdapter() {

    public void windowClosing(WindowEvent e) {
      theFrame.setVisible(false);
      theFrame.dispose();
      System.exit(1);
    }});
    //currentPanel = new JPanel();
    c.add(this);

    theFrame.setSize(new Dimension(640, 480));
  //  theFrame.pack();
    theFrame.setResizable(false);
    centerInScreen();
    //theFrame.invalidate();
    theFrame.repaint();
    theFrame.setUndecorated(true);
    theFrame.setVisible(true);
  }

  /**
   * performs an action
   * @param e ActionEvent
   */
  
  public void actionPerformed(ActionEvent e) {
    timer.stop();
    notYet = false;
  }


  /**
   * centers it on the screen
   */
  public void centerInScreen() {
    Toolkit tk = Toolkit.getDefaultToolkit();
    Dimension d = tk.getScreenSize(); 
    theFrame.setLocation((d.width - theFrame.getSize().width) / 2,
                        (d.height - theFrame.getSize().height) / 2); 
  }


  /**
   * paints stuff
   * @param g Graphics
   */
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    
    g.drawImage(ii.getImage(), 0, 0, ii.getImageObserver());
  }


  /**
   * Used to run Scorched Earth starting with a Splash Screen
   * @param argv IGNORED
   */
  public static void main(String argv[]) {
    ScorchedEarth se = new ScorchedEarth();
    se.init();
    se.timer = new Timer(5000, se);
    se.timer.start();
      while (se.notYet) { }
    se.closeWindow();
    
    new GlobalChatWindow();
  }
}









