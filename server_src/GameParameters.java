


import java.util.StringTokenizer;


/**
 *
 * GateKeeper - the master of the Scorched Earth realm
 * 
 * @author Team QERM
 *
 *
 * Quiggle, Micheal
 * Etherton, John
 * Reynolds, Wes
 * McCann, David
 *
 */ 
 
public class GameParameters implements Protocol, Constants {


   /**
    * Random seed used to make up random stuff
    */    
    private long randomSeed = 31415;



   /**
    * Money the players start with
    */    
    private int startingMoney = 10000;


   /**
    * interest rate
    */    
    private int interestRate = 0;


   /**
    * how the wind behaves
    */    
    private int windBehavior = 0;
    
   
   /**
    * max wind velocity
    */    
    private int maxWind = 0;    


   /**
    * how the walls behave
    */    
    private int wallBehavior = 0;


   /**
    * are the levels caves?
    */    
    private boolean isCave = false;


   /**
    * number of rounds before game is over
    */    
    private int numRounds = 10;


   /**
    * max number of players in game at once
    */    
    private int maxPlayers = MAX_PLAYERS;


   /**
    * name of game
    */    
    private String name = "NOOBS ONLY";


   /**
    * a.k.a. roughness 
    */    
    private float levelComplexity = 1.0f;


   /**
    * average magnitude of levels
    */    
    private int levelSteepness = 200;


   /**
    * strength of gravity
    */    
    private int gravity = 1;


   /**
    * size of explosions
    */    
    private float explosionSize = 2;


   /**
    * is game private
    */    
    private boolean isGamePrivate = false;


   /**
    * password to join game
    */    
    private String password = "";
    



/***************************************************************************/
/*                                MODIFIERS                                */
/***************************************************************************/

   /**
    * Modifier
    * @param i long
    */
    public void setRandomSeed(long  i) {
        randomSeed = i;
    }


   /**
    * Modifier
    * @param i int
    */
    public void setStartingMoney(int i) {
        startingMoney = i;
    }



   /**
    * Modifier
    * @param i int
    */   
    public void setInterestRate(int i) {
        interestRate = i;
    }


   /**
    * Modifier
    * @param i int
    */
    public void setWindBehavior(int i) {
        windBehavior = i;
    }
    
   
   /**
    * Modifier
    * @param i int
    */
    public void setMaxWind(int i) {
        maxWind = i;
    }


   /**
    * Modifier
    * @param i int
    */
    public void setWallBehavior(int i) {
        wallBehavior = i;
    }
    

   /**
    * Modifier
    * @param b boolean
    */
    public void setIsCave(boolean b) {
        isCave = b;
    }


   /**
    * Modifier
    * @param i int
    */
    public void setNumRounds(int i) {
        numRounds = i;
    }
    

   /**
    * Modifier
    * @param i int
    */
    public void setMaxPlayers(int i) {
        maxPlayers = i;
    }


   /**
    * Modifier
    * @param s String
    */
    public void setName(String s) {
        name = s;
    }


   /**
    * Modifier
    * @param i int
    */
    public void setLevelComplexity(float i) {
        levelComplexity = i;
    }


   /**
    * Modifier
    * @param i int
    */
    public void setLevelSteepness(int i) {
        levelSteepness = i;
    }


   /**
    * Modifier
    * @param i int
    */
    public void setGravity(int i) {
        gravity = i;
    }


   /**
    * Modifier
    * @param i int
    */
    public void setExplosionSize(float i) {
        explosionSize = i;
    }



   /**
    * Modifier
    * @param b boolean
    */
    public void setIsGamePrivate(boolean b) {
        isGamePrivate = b;
    }


   /**
    * Modifier
    * @param s String
    */
    public void setPassword(String s) {
        password = s;
    }






/***************************************************************************/
/*                                ACCESSORS                                */
/***************************************************************************/


   /**
    * Accessor
    * @return long
    */
    public long getRandomSeed() {
        return randomSeed;
    }


   /**
    * Accessor
    * @return int
    */
    public int getStartingMoney() {
        return startingMoney;
    }


   /**
    * Accessor
    * @return int
    */   
    public int getInterestRate() {
        return interestRate;
    }


   /**
    * Accessor
    * @return int
    */
    public int getWindBehavior() {
        return windBehavior;
    }
    
   
   /**
    * Accessor
    * @return int
    */
    public int getMaxWind() {
        return maxWind;
    }


   /**
    * Accessor
    * @return int
    */
    public int getWallBehavior() {
        return wallBehavior;
    }
    

   /**
    * Accessor
    * @return boolean
    */
    public boolean getIsCave() {
        return isCave;
    }


   /**
    * Accessor
    * @return int
    */
    public int getNumRounds() {
        return numRounds;
    }

   /**
    * Accessor
    * @return int
    */
    public int getMaxPlayers() {
        return maxPlayers;
    }


   /**
    * Accessor
    * @return String
    */
    public String getName() {
        return name;
    }


   /**
    * Accessor
    * @return int
    */
    public float getLevelComplexity() {
        return levelComplexity;
    }


   /**
    * Accessor
    * @return int
    */
    public int getLevelSteepness() {
        return levelSteepness;
    }


   /**
    * Accessor
    * @return int
    */
    public int getGravity() {
        return gravity;
    }


   /**
    * Accessor
    * @return int
    */
    public float getExplosionSize() {
        return explosionSize;
    }



   /**
    * Accessor
    * @return boolean
    */
    public boolean getIsGamePrivate() {
        return isGamePrivate;
    }


   /**
    * Accessor
    * @return String
    */
    public String getPassword() {
        return password;
    }


   /**
    * Parse a StringTokenizer
    * @param params StringTokenizer
    */
    public void parseFormattedString(StringTokenizer params) {
        name = params.nextToken();

        startingMoney = Integer.parseInt(params.nextToken());

        interestRate = Integer.parseInt(params.nextToken());

        windBehavior = Integer.parseInt(params.nextToken());

        wallBehavior = Integer.parseInt(params.nextToken());

        
    if ((params.nextToken()).equals("true")) {
      isCave = true;
    } else {
      isCave = false;
    }

        numRounds = Integer.parseInt(params.nextToken());

        maxPlayers = Integer.parseInt(params.nextToken());

        levelComplexity = Float.parseFloat(params.nextToken());

        levelSteepness = Integer.parseInt(params.nextToken());

        gravity = Integer.parseInt(params.nextToken());

        explosionSize = Float.parseFloat(params.nextToken());

    if ((params.nextToken()).equals("true")) {
      isGamePrivate = true;
    } else {
      isGamePrivate = false;
    }

    if (isGamePrivate) {

       password = params.nextToken();

    }

        maxWind = Integer.parseInt(params.nextToken());

        randomSeed = Long.parseLong(params.nextToken());

    }



   /**
    * returns a string representation good for sending over the network
    * @return String representation of the whole deal
    */
    public String getFormattedString() {
        return name + DELIM + startingMoney + DELIM + interestRate + DELIM
        + windBehavior + DELIM + wallBehavior + DELIM + isCave + DELIM 
        + numRounds + DELIM + maxPlayers + DELIM + levelComplexity + DELIM 
        + levelSteepness + DELIM + gravity + DELIM + explosionSize + DELIM 
        + isGamePrivate + DELIM + password + DELIM + maxWind + DELIM 
        + randomSeed;
    }
    
   /**
    * Used to make a human readable string
    * @return String representation of the whole deal
    */
    public String toString() {
        return "Name: " + name 
            + "\nStarting Money: " + startingMoney 
            + "\nInterest Rate: " + interestRate 
            + "\nWind Behavior: " + windBehavior 
            + "\nWall Behavior: " + wallBehavior 
            + "\nCave: " + isCave 
            + "\nNumber of Rounds: " + numRounds 
            + "\nMax Players: " + maxPlayers 
            + "\nLevel Complexity Factor: " + levelComplexity 
            + "\nLevel Steepness Factor: " + levelSteepness 
            + "\nGravity Factor: " + gravity 
            + "\nExplosion Size Factor: " + explosionSize 
            + "\nPrivate Game: " + isGamePrivate 
            + "\nMax Wind Velocity: " + maxWind;
            
    }    
    
}
    
  











