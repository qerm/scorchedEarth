import java.awt.Color;
/**
 * Constants.java, all the Constants in the game.
 * @author Team QERM
 */
public interface Constants {

 /**
   * The width of the game canvas
   */
  public static final int SCREEN_WIDTH = 800;
  
  /**
   * the height of the game canvas
   */
  public static final int SCREEN_HEIGHT = 640;

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
   * Another array of tank colors
   */
  public static final String[] STRING_COLORS = {"Pink", "Blue", "Cyan",
                    "Gray", "Green", "Orange",
                    "Red",  "Yellow"};




    /**
     * The "in lobby" state constant
     */ 
  public static final int LOBBY_STATE = 8;                   
  
  /**
   * The "in shop" state constant
   */ 
  public static final int SHOP_STATE = 9;      
  
  /**
   * The "waiting for shot" state constant
   */ 
  public static final int WAITING_FOR_SHOT_STATE = 10;
  
  
  /**
   * The "waiting for death" state constant
   */ 
  public static final int WAITING_FOR_DEATH_STATE = 11;  

  /**
   * The array of all the different types of armor
   */
  public static final String[] WEAPONS = {"Baby Missile", 
                      "Missile",
                      "Nuke",
                      "Mirv",
                      "Spider",
                      "Tomahawk"};

  /**
   * Used in shop, this holds each of the weapons
   * quantity increments that they're sold in,
   * as well as price.
   */
  public static final int[][] WEAPONS_QTY_PRICE = {{10000, 0},
                           {25, 100},
                           {1, 1000},
                           {5, 1000},
                           {5, 1000},
                           {1, 10000}};
  

  /**
   * game constant
   */
  public static final int[][] WEAPONS_SELL_QTY_PRICE = {{10000, 0},
                            {1, 3},
                            {1, 900},
                            {1, 180},
                            {1, 180},
                            {1, 9000}};
  
  /**
   * The baby missile explosion radius
   */
  public static final int BABY_MISSILE_RAD = SCREEN_HEIGHT / 64;
  
  /**
   * The missile explosion radius
   */
  public static final int MISSILE_RAD = SCREEN_HEIGHT / 32;

  /**
   * the nuke explosion radius
   */
  public static final int NUKE_RAD = SCREEN_HEIGHT / 8;
 

 /**
   * The baby missle constant 
   * (also its position in the name array)
   */
  public static final int BABY_MISSILE = 0; 

  /**
   * The missile constant
   * (also its position in the name array)
   */
  public static final int MISSILE = 1;
 
  /**
   * The nuke constant (also its position in the name array)
   */  
  public static final int NUKE = 2;

  /**
   * The mirv constant (also its position in the name array)
   */
  public static final int MIRV = 3;

  /**
   * the spider constant (also its position in the name array)
   */
  public static final int SPIDER = 4;

  /**
   * the tomohawk constant (also its position in the name array)
   */
  public static final int TOMAHAWK = 5;

  /**
   * The array of names of all the different types of armor
   */
  public static final String[] ARMOR = {"Sand Bags",
                    "Camouflage paint", 
                    "Active Armour"};

 /**
   * Used in shop, this holds each of the armor
   * quantity increments that they're sold in,
   * as well as price.
   */
  public static final int[][] ARMOR_QTY_PRICE = {{1, 3000},
                         {1, 1087},
                         {1, 5000}};


  /**
   * game constant
   */
  public static final int[][] ARMOR_SELL_QTY_PRICE = {{1, 2700},
                              {1, 900},
                              {1, 4500}};
                             

  /**
   * The sand bag constant (also its position in the name array)
   */
  public static final int SAND_BAGS = 0;

  /**
   * The camo paint constant (also its position in the name array)
   */
  public static final int CAMO_PAINT = 1;
  
  /**
   * The active armor constant 
   * (also its position in the name array)
   */
  public static final int ACTIVE_ARMOR = 2;
  
  /**
   * number of HP the sand bags add
   */
  public static final int SAND_BAGS_HP = 50;

  /**
   * number of HP the camo paint add
   */
  public static final int CAMO_PAINT_HP = 25;

  /**
   * the HP that Active Armor adds
   */
  public static final int ACTIVE_ARMOR_HP = 100;

  /**
   * The height of the tank bitmap, not counting turret
   */
  public static final int TANK_HEIGHT = 16;
  
  /**
   * The width of the tank bitmap
   */
  public static final int TANK_WIDTH = 32;

  /**
   * sell sound array index
   */
  public static final int SELL_SOUND = 0;

  /**
   * buy sound array index
   */
  public static final int BUY_SOUND = 1;

  /**
   * fire 1 sound array index
   */
  public static final int FIRE1_SOUND = 2;

  /**
   * fire 2 sound array index
   */
  public static final int FIRE2_SOUND = 3;

  /**
   * fire 3 sound array index
   */
  public static final int FIRE3_SOUND = 4;

  /**
   * fire 4 sound array index
   */
  public static final int FIRE4_SOUND = 5;

  /**
   * miss sound array index
   */
  public static final int MISS_SOUND = 6;

  /**
   * hit sound array index
   */
  public static final int HIT_SOUND = 7;

  /**
   * game constant
   */
    public static final int TAUNT1_SOUND = 8;
  /**
   * game constant
   */
    public static final int TAUNT2_SOUND = 9;
  /**
   * game constant
   */
    public static final int TAUNT3_SOUND = 10;
  /**
   * game constant
   */
    public static final int TAUNT4_SOUND = 11;
  /**
   * game constant
   */    
    public static final int MIRV_SHOOT = 12;

  /**
   * game constant
   */
   public static final int TOMAHAWK_FIRE = 13;
  /**
   * game constant
   */
    public static final int MIRV_PEAK = 14;
    
  /**
   * sound filename array
   */
  public static final String[] SOUND_ARRAY = {"sell.wav", //0
                          "buy.wav",       //1
                          "fire1.wav",     //2
                          "fire2.wav",     //3
                          "fire3.wav",     //4
                          "fire4.wav",     //5
                          "miss.wav",      //6
                          "hit.wav",       //7
                          "taunt1.wav",    //8
                          "taunt2.wav",    //9
                          "taunt3.wav",    //10
                          "taunt4.wav",    //11
                          "mirvshoot.wav", //12
                          "tomahawk.wav",  //13
                          "mirvpeak.wav"}; //14


  /**
   * Wind behaviour constants
   * the windBehavior int in GameParameters should
   * always be one of these values.
   */
  public static final int NO_WIND = 0;

  /**
   * game constant
   */
  public static final int CONST_WIND = 1;
  /**
   * game constant
   */
  public static final int VAR_WIND = 2;

  /**
   * the Wall behviour constants
   * the wallBehvior int in GameParameters should
   * be a sum of any combination of these 
   * four constants
   */
  public static final int STICKY_WALLS = 1;

  /**
   * game constant
   */
  public static final int WRAP_WALLS = 10;

  /**
   * game constant
   */
   public static final int BOUNCY_WALLS = 100;
  /**
   * game constant
   */
  public static final int NO_WALLS = 1000;

  /**
   * Explosion size constants
   */
  public static final float SMALL = 1f;
  /**
   * game constant
   */
  public static final float MEDIUM = 1.5f;
  /**
   * game constant
   */
  public static final float LARGE = 2f;


  /**
   * game constant
   */
  public static final int MIN_START_MONEY = 0;
  /**
   * game constant
   */  
  public static final int MAX_START_MONEY = 1000000;

  /**
   * game constant
   */
  public static final int MIN_INT_RATE = 0;
  /**
   * game constant
   */  
  public static final int MAX_INT_RATE = 30;

  /**
   * game constant
   */  
  public static final int MIN_MAX_WIND = 1;
  /**
   * game constant
   */  
  public static final int MAX_MAX_WIND = 100;

  /**
   * game constant
   */  
  public static final int MIN_NUM_ROUNDS = 1;
  /**
   * game constant
   */  
  public static final int MAX_NUM_ROUNDS = 20;

  /**
   * game constant
   */
  public static final int MIN_TERRAIN_COMPLEXITY = 0;
  /**
   * game constant
   */
  public static final int MAX_TERRAIN_COMPLEXITY = 100;
  /**
   * game constant
   */
  public static final float TERRAIN_COMPLEXITY_DIVIDER =
                               MAX_TERRAIN_COMPLEXITY - MIN_TERRAIN_COMPLEXITY;
  /**
   * game constant
   */
  public static final int MIN_TERRAIN_STEEPNESS = 0;
  /**
   * game constant
   */
  public static final int MAX_TERRAIN_STEEPNESS = 450;
  /**
   * game constant
   */
  public static final int TERRAIN_STEEPNESS_OFFSET = 50;
  /**
   * game constant
   */
  public static final int MIN_GRAV = 1;
  /**
   * game constant
   */
  public static final int MAX_GRAV = 5;


  /**
   * game constant
   */
  public static final int KILL_MONEY = 1000;
}
