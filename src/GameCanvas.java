import javax.swing.JPanel;
import java.awt.image.BufferedImage;
import java.awt.Color;
import java.awt.Graphics;   
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.util.Vector;
import javax.swing.ImageIcon;
import java.awt.Image;
import java.awt.image.ImageObserver;
//import java.awt.AlphaComposite;
//import java.awt.Canvas;
//import java.awt.GraphicsConfiguration;

/**
 * The actual board of the game
 * @author Team QERM
 */
public class GameCanvas extends JPanel implements Constants {

  /**
   * The background BufferedImage this
   * canvas paints
   */
  private BufferedImage background;

  /**
   * The terrain BufferedImage this
   * canvas paints
   */
  private BufferedImage terrain;

  /**
   * The BufferedImage that is
   * actually drawn to the screen
   */
  private BufferedImage canvas;
  
  /**
   * inits the bufferedImage
   */
  private boolean firsttime = true;

  /**
   * the GameModel this canvas will render
   */
  private GameModel gameModel;

  /**
   * the array of images of tanks
   */   
  private Image tankImage[];

  /**
   * the array of ImageObservers for the tanks
   */
  private ImageObserver[] tankObserver;


  /**
   * the image of a dead tank
   */   
  private Image deadTank;

  /**
   * the ImageObservers of a dead tank
   */
  private ImageObserver deadTankObserver;


  /**
   * Constructor for GameCanvas
   * @param newGameModel the gameModel for this canvas
   * to render
   */
  public GameCanvas(GameModel newGameModel) {
    gameModel = newGameModel;
    
    tankImage = new Image[MAX_PLAYERS];
    tankObserver = new ImageObserver[MAX_PLAYERS];
    
    ImageIcon temp = new ImageIcon("../media/pink.gif");
    tankImage[0] = temp.getImage();
    tankObserver[0] = temp.getImageObserver();
    
    temp = new ImageIcon("../media/blue.gif");
    tankImage[1] = temp.getImage();
    tankObserver[1] = temp.getImageObserver();
    
    temp = new ImageIcon("../media/cyan.gif");
    tankImage[2] = temp.getImage();
    tankObserver[2] = temp.getImageObserver();

    temp = new ImageIcon("../media/gray.gif");
    tankImage[3] = temp.getImage();
    tankObserver[3] = temp.getImageObserver();

    temp = new ImageIcon("../media/green.gif");
    tankImage[4] = temp.getImage();
    tankObserver[4] = temp.getImageObserver();

    temp = new ImageIcon("../media/orange.gif");
    tankImage[5] = temp.getImage();
    tankObserver[5] = temp.getImageObserver();

    temp = new ImageIcon("../media/red.gif");
    tankImage[6] = temp.getImage();
    tankObserver[6] = temp.getImageObserver();

    temp = new ImageIcon("../media/yellow.gif");
    tankImage[7] = temp.getImage();
    tankObserver[7] = temp.getImageObserver();

    temp = new ImageIcon("../media/deadtank.gif");
    deadTank = temp.getImage();
    deadTankObserver = temp.getImageObserver();
    
    
    canvas = new BufferedImage(SCREEN_WIDTH, SCREEN_HEIGHT,
                   BufferedImage.TYPE_INT_ARGB);
    
    
    background = new BufferedImage(SCREEN_WIDTH, SCREEN_HEIGHT, 
                   BufferedImage.TYPE_INT_ARGB);
    
    Graphics2D bg2d = background.createGraphics();
    if (gameModel.isCaveLevel()) {
      bg2d.setColor(Color.BLACK);
    } else {
      bg2d.setPaint(new GradientPaint(0, SCREEN_HEIGHT, 
                    Color.RED, 0, 0, Color.BLUE));
    }
    bg2d.fillRect(0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);


    renderTerrain();    


  }





    /**
     * man i hate this crap
     * @param g graphics
     */
     
    protected void paintComponent(Graphics g) {    
    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D) g;
    
 
    //AlphaComposite ac = 
    //  AlphaComposite.getInstance(AlphaComposite.SRC_OVER);
    //g2d.setComposite(ac);
    //g2d.drawImage(background,  null, 0, 0);
    //ac = 
    //  AlphaComposite.getInstance(AlphaComposite.SRC_OVER);
    //g2d.setComposite(ac);
    

    if (gameModel.getRerender()) {
        renderTerrain();
        gameModel.setRerender(false);
    }
    g2d.drawImage(canvas, null, 0, 0);  
    
    
    //redraw all the tanks
    renderTanks(g2d);
    //redraw all the shots
    renderShots(g2d);
    
    }
  




    /**
     * checkstyle
     */  
    public void renderTerrain() {
        int[] heights = gameModel.getHeights();
        int[] cave = gameModel.getCave();        
    int[] dirtChunk1Top = gameModel.getDirtChunk1Top();
    int[] dirtChunk2Top = gameModel.getDirtChunk2Top();
    int[] dirtChunk1Bottom = gameModel.getDirtChunk1Bottom();
    int[] dirtChunk2Bottom = gameModel.getDirtChunk2Bottom();
        terrain = new BufferedImage(SCREEN_WIDTH, SCREEN_HEIGHT, 
                BufferedImage.TYPE_INT_ARGB);    
        Graphics2D bg2d  = terrain.createGraphics();
        for (int i = 0; i < SCREEN_WIDTH; i++) {

            bg2d.setColor(Color.GREEN);
            //bg2d.setPaint(new GradientPaint(i, (SCREEN_HEIGHT - 
            //                  heights[i]),Color.GREEN,i, 639,
            //                  new Color(0, 128, 0)));
      
        bg2d.drawLine(i, SCREEN_HEIGHT, i, (SCREEN_HEIGHT - heights[i]));
        if (gameModel.isCaveLevel()) {
          //bg2d.setPaint(new GradientPaint(i, 0 ,Color.GREEN,i,cave[i],
          //              new Color(0, 128, 0)));
          
          bg2d.drawLine(i, 0, i, cave[i]);
        }
        if (dirtChunk1Top[i] != -1) {
          bg2d.setColor(Color.GREEN);
          bg2d.drawLine(i, (SCREEN_HEIGHT - dirtChunk1Top[i]), i,
                (SCREEN_HEIGHT - dirtChunk1Bottom[i]));
        }
        if (dirtChunk2Top[i] != -1) {
           bg2d.setColor(Color.GREEN);
           bg2d.drawLine(i, (SCREEN_HEIGHT - dirtChunk2Top[i]), i,
                (SCREEN_HEIGHT - dirtChunk2Bottom[i]));
        }
    }
    Graphics2D cg2d = canvas.createGraphics();
    cg2d.drawImage(background, null, 0, 0);
    cg2d.drawImage(terrain, null, 0, 0);
    }



    /**
     * Hey world i'm still awake
     * @param g2d you know what it is
     */
    public void renderTanks(Graphics2D g2d) {
        Vector playerList = gameModel.getPlayerList();
        Tank temp;
    
        ///Tank loop
        for (int i = 0; i < playerList.size(); i++) {
            temp = (Tank) playerList.get(i);
      
            //draw tank image from file
            
            if (temp.getIsDead()) {
                g2d.drawImage(deadTank, 
                    temp.getXPos(),
                    (SCREEN_HEIGHT - temp.getYPos()),
                     deadTankObserver);
            } else {
                
            
            g2d.setColor(COLORS[temp.getColor()]);
      
      
            int dx = (int) 
            (.75 * TANK_WIDTH 
         * Math.cos(Math.toRadians((double) temp.getTurretAngle())));
            int dy = (int) 
            (.75 * TANK_WIDTH 
         * Math.sin(Math.toRadians((double) temp.getTurretAngle())));
    
            if (temp.getTurretAngle() >= 90) {
                g2d.drawLine(temp.getXPos() + (TANK_WIDTH / 2), 
                        (SCREEN_HEIGHT - (temp.getYPos() - 5)),
                        temp.getXPos() + dx + (TANK_WIDTH / 2), 
                        (SCREEN_HEIGHT - (temp.getYPos() - 5) - dy));
             
            } else {
        
                g2d.drawLine(temp.getXPos() + (TANK_WIDTH / 2), 
                       (SCREEN_HEIGHT - (temp.getYPos() - 5)),
                    temp.getXPos() + dx + (TANK_WIDTH / 2), 
                    (SCREEN_HEIGHT - (temp.getYPos() - 5) - dy));
            }
            
            g2d.drawImage(tankImage[temp.getColor()], 
                temp.getXPos(),
                (SCREEN_HEIGHT - temp.getYPos()),
                 tankObserver[temp.getColor()]);
        }
        }
    }
    
    
    
    /**
     * does stuff
     * @param g2d Graphics2D
     */
    public void renderShots(Graphics2D g2d) {
        Vector shots = gameModel.getCurrentShots();
        Shot shot;
        
        for (int i = 0; i < shots.size(); i++) {
            shot = (Shot) shots.get(i);
            if (!shot.isExploded()) {
                g2d.setColor(Color.WHITE);
                g2d.fillOval(shot.getX(), 
                    SCREEN_HEIGHT - shot.getY(), 
                    6, 
                    6);
            } else {
                int radius = shot.getRadius();
                int mult = 1;
                if (radius != 0) {
                    mult = 255 / radius;
                }
                
                    
                for (int j = radius; j > 0; j--) {
                    
                    g2d.setColor(new Color(255, j * mult, 0));
                    g2d.fillOval(shot.getX() - j, 
                        SCREEN_HEIGHT - shot.getY() - j, 
                        j * 2, 
                        j * 2);
                }
            }
        }
    }

}


