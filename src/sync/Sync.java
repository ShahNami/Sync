/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/

package sync;

import java.io.IOException;
import recognition.SyncListener;
import recognition.CommandExecutor;

/**
 *
 * @author Nami
 */
public class Sync {
    
    /**
     * @param args the command line arguments
     */
    private static CommandExecutor exec;
    private static Display display;
    public static void main(String[] args) throws IOException, InterruptedException, Exception {
        display = Display.getInstance();
        display.setInfo(Constants.loadMessage);
        display.setImageText("");
        display.setImage(null);
        exec = new CommandExecutor(display);
        display.setVisible(true);
        display.setExecutor(exec);
        String command;
        SyncListener listener = SyncListener.getInstance();
        while(true){
            if(!listener.isListening()){
                display.setInfo(Constants.speepMessage);
            } else {
                display.setInfo(Constants.listenMessage);
            }
            command = listener.Listen();
            try{
                exec.parseCommand(command);
            } catch(Exception ex){
                display.setInfo(Constants.errorParseMessage);
            }
        }
    }
    
}
