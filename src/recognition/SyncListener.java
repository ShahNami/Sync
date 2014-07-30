package recognition;

import edu.cmu.sphinx.frontend.util.Microphone;
import edu.cmu.sphinx.recognizer.Recognizer;
import edu.cmu.sphinx.result.Result;
import edu.cmu.sphinx.util.props.ConfigurationManager;
import java.io.IOException;
import sync.Constants;
import sync.Display;

/**
 *
 * @author Nami
 * TODO: SPHINX5 PREALPHA CONFIGURATION
 */
public class SyncListener {
    private static ConfigurationManager cm;
    private static Recognizer recognizer;
    private static Microphone microphone;
    private boolean isListening = true;
    
    private static SyncListener _instance = null;
    
    private synchronized static void createInstance () {
        if (_instance == null) _instance = new SyncListener();
    }
    public static SyncListener getInstance(){
        if(_instance == null)
            createInstance();
        return _instance;
    }
    
    public void setListening(boolean l){
        isListening = l;
    }
    
    private SyncListener(){
        cm = new ConfigurationManager(SyncListener.class.getResource("config.xml"));
        recognizer = (Recognizer) cm.lookup("recognizer");
        microphone = (Microphone) cm.lookup("microphone");
        recognizer.allocate();
    }
    
    public boolean isListening(){
        return isListening;
    }
    
    public String Listen() throws IOException {
        try{
            microphone.clear();
            if (!microphone.startRecording()) {
                //this.microphone = (Microphone) cm.lookup("microphone");
                microphone.initialize();
            }
            Result result = recognizer.recognize(); //!Important DO NOT REMOVE!
            String command = "";
            while(command.equalsIgnoreCase("")){
                //if(isListening){
                    microphone.clear();
                    result = recognizer.recognize();
                    command = result.getBestFinalResultNoFiller();
                    if(command.equalsIgnoreCase(""))
                        Display.getInstance().setInfo(Constants.emptyCommandMessage);
                //} else {
                    //command = "go to sleep";
                //}
            }
            microphone.stopRecording();
            microphone.clear();
            //if(command.contains("sleep") || command.contains("shut up") || command.contains("shht")){
                //isListening = false;
            //}
            if((!isListening && command.contains(Constants.wakeUpCommand)) || isListening && Display.getInstance().isCommandEmpty()){
                isListening = true;
                return command;
            }

        } catch (IllegalStateException ex){
            System.out.println(ex);
        }
        return null;
    }
}

