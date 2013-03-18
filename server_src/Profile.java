
 
 
import java.io.Serializable;
import java.io.IOException;
//import java.lang.ClassNotFoundException;

/**
 *
 * Profile - structure to store users info
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
public class Profile implements Protocol, Constants, Serializable {


   /**
    * user's name
    */
    private String userName;    


   /**
    * user's total money earned
    */    
    private long money;


   /**
    * user's passowrd
    */    
    private String password;
    
    
/***************************************************************************/
/*                                MODIFIERS                                */
/***************************************************************************/


   /**
    * Modifier
    * @param un String
    */
    public void setUserName(String un) {
        userName = un;
    }


   /**
    * Modifier
    * @param m long
    */
    public void setMoney(long m) {
        money = m;
    }


   /**
    * Modifier
    * @param p String
    */
    public void setPassword(String p) {
        password = p;
    }


/***********************************************************************/
/*                                ACCESSORS                            */
/***********************************************************************/    


   /**
    * Accessor
    * @return String
    */
    public String getUserName() {
        return userName;
    }


   /**
    * Accessor
    * @return int
    */
    public long getMoney() {
        return money;
    }


   /**
    * Accessor
    * @return String
    */
    public String getPassword() {
        return password;
    }


   /**
    * toString
    * @return String
    */
    public String toString() {
        return userName;
    }
 

   /**
    * used to make this serializable
    * @throws IOException fgfdgfdg
    * @param out hey
    *
    */ 
    private void writeObject(java.io.ObjectOutputStream out)
     throws IOException {
        out.writeObject(userName);
        out.writeObject(password);
        out.writeLong(money);
    }

   /**
    * used to make this serializable
    * @param in input stream
    * @throws IOException fgfgf
    * @throws ClassNotFoundException gfgf
    */ 
    private void readObject(java.io.ObjectInputStream in)
     throws IOException, ClassNotFoundException {
        userName = (String) in.readObject();
        password = (String) in.readObject();
        money = in.readLong();
        
    }
 
    
}