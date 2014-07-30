package synthesis;

import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import marytts.LocalMaryInterface;
import marytts.MaryInterface;
import marytts.exceptions.MaryConfigurationException;
import marytts.exceptions.SynthesisException;
import marytts.util.data.audio.AudioPlayer;
import recognition.SyncListener;
import sync.Constants;
import sync.MediaEnum;
import sync.MediaPlayer;

/**
 *
 * @author Nami
 */
public class SyncTalker {
    private MaryInterface marytts;
    private AudioPlayer ap;
    
    
    private static SyncTalker _instance = null;
 
    //gesynchroniseerde creator om multi-threading-problemen te voorkomen
    //nog een controle om te voorkomen dat er meer dan een object wordt geïnstantieerd
    private synchronized static void createInstance () throws IOException {
        if (_instance == null) _instance = new SyncTalker ();
    }
 
    public static SyncTalker getInstance () throws IOException {
        if (_instance == null) createInstance ();
        return _instance;
    }
    
    private SyncTalker() throws IOException
    {
        try
        {
            marytts = new LocalMaryInterface();
            marytts.setVoice(Constants.voice); //cmu-slt-hsmm//
            ap = new AudioPlayer();
        } catch (MaryConfigurationException ex) {
        }
    }
    
    public void say(String input) throws InterruptedException, IOException
    {
        try
        {
            if(SyncListener.getInstance().isListening()){
                if(ap.isAlive())
                    ap.cancel();
                AudioInputStream audio = marytts.generateAudio(input);
                ap = new AudioPlayer(audio);
                MediaPlayer.getInstance().getPlayer().pause();
                Thread.sleep(500);  //Stops music just before speaking.
                SyncListener.getInstance().setListening(false); //So it doesn't think the answer is a new command.
                ap.start();
                SyncListener.getInstance().setListening(true);
                if(MediaPlayer.getInstance().getStatus() != MediaEnum.wasPlaying && MediaPlayer.getInstance().getPlayer().isPaused()){
                    Thread.sleep(Constants.sleepTimer); //For some reason it doesn't listen to your commands as well.
                    MediaPlayer.getInstance().getPlayer().play();
                }
            }
        } catch (SynthesisException ex) {
            //System.err.println("Error saying: " + input);
        }
    }
}
