import java.awt.Color;
/**
 * Constants.java, all the Constants in the game.
 * @author Team QERM
 */
public interface Constants {

  /**
   * The maximum number of players allowed in a game
   */
  public static final int MAX_PLAYERS = 8;
 
  /**
   * The index number of this color in the COLORS array
   */ 
  public static final int PINK = 0;
  
  /**
   * The index number of this color in the COLORS array
   */ 
  public static final int BLUE = 1;
  
  /**
   * The index number of this color in the COLORS array
   */ 
  public static final int CYAN = 2;
  
  /**
   * The index number of this color in the COLORS array
   */ 
  public static final int GRAY = 3;
  
  /**
   * The index number of this color in the COLORS array
   */ 
  public static final int GREEN = 4;
  
  /**
   * The index number of this color in the COLORS array
   */ 
  public static final int ORANGE = 5;
  
  /**
   * The index number of this color in the COLORS array
   */ 
  public static final int RED = 6;
  
  /**
   * The index number of this color in the COLORS array
   */ 
  public static final int YELLOW = 7;
  
  
  /**
   * An array of tank colors
   */
  public static final Color[] COLORS = {Color.PINK, Color.BLUE, Color.CYAN,
                    Color.GRAY, Color.GREEN, Color.ORANGE,
                    Color.RED,  Color.YELLOW};


    /**
     * The "in lobby" state constant
     */ 
  public static final int LOBBY_STATE = 8;                   
  
  /**
   * The "in shop" state constant
   */ 
  public static final int SHOP_STATE = 9;      
  
  /**
   * The "turn state" state constant
   */ 
  public static final int TURN_STATE = 10;
  
  
  /**
   * The "waiting for death" state constant
   */ 
  public static final int WAITING_FOR_SYNC_STATE = 11;  
  
  
  
  /**
   * The money you get for killing some one
   */ 
  public static final int KILL_MONEY = 1000;  
}
