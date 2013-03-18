import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.Clip;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.sound.sampled.LineUnavailableException;
import java.io.File;
import java.io.IOException;

/**
 * ScorchedEarth.java, the splashscreen class
 * @author Team QERM;
 */
public class ScorchedAmp implements Constants {
    
    
    /**
     * isSoundOn
     */ 
    private static boolean isSoundOn = true;
    
    
    /**
     * sets your sounds
     * @param b boolean
     */
     
    public static void setIsSoundOn(boolean b) {
        isSoundOn = b;
    }


    /**
     * checks if sounds are on
     * @return boolean
     */
    public static boolean getIsSoundOn() {
        return isSoundOn;
    }

    
    /**
     * plays sounds
     * @param arrayindex int
     */
    public static void playSound(int arrayindex) {
        if (isSoundOn) {
           try {
               AudioInputStream stream = AudioSystem.
              getAudioInputStream(new File("../media/" 
                             + SOUND_ARRAY[arrayindex]));
               AudioFormat format = stream.getFormat();
        
                DataLine.Info info =
                new DataLine.Info(
                  Clip.class,
                  stream.getFormat(),
                  ((int) stream.getFrameLength()
                   * format.getFrameSize())
                  );
        
        Clip clip = (Clip) AudioSystem.getLine(info);
        
        clip.open(stream);
        
        clip.start();
    } catch (UnsupportedAudioFileException e) {
        int i = 0;
    } catch (LineUnavailableException e) {
        int i = 0;
    } catch (IOException e) {
        int i = 0;
    }
    }
    }
}
