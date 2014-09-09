/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sync;

/**
 *
 * @author Nami
 */
public interface Constants {
    
    /**
     * SYNC.JAVA
     * DISPLAY.JAVA
     */
    String loadMessage = "Loading...";
    String sleepMessage = "Sleeping...";
    String listenMessage = "Listening...";
    String errorParseMessage = "Could not parse the command.";
    
    /**
     * DISPLAY.JAVA
     */
    String displayTitle = "Sync User Interface";
    String internetErrorMessage = "Unable to parse command. Check your internet connection.";
    String[] sleepCommands = {"go to sleep", "shht", "shut up"};
    
    /**
     * MEDIAPLAYER.JAVA
     */
    String mp3FrameTitle = "Sync's MP3 Player";
    String musicPath = System.getProperty("user.home")+"//Google Drive//Private/Projects/Sync/music/";
    
    /**
     * SYNCLISTENER.JAVA
     * DISPLAY.JAVA
     */
    String emptyCommandMessage = "I did not quite understand...";
    String wakeUpCommand = "wake up";
    
    /**
     * SYNCTALKER.JAVA
     */
    String voice = "cmu-rms-hsmm";
    int sleepTimer = 3000; //2700
    
    /**
     * XML STAX PARSER
     * COMMAND.JAVA
     * ANSWER.JAVA
     * ALLCOMMANDS.JAVA
     * EXECUTE.JAVA
     * PARAM.JAVA
     */
    String answerTag = "Answer";
    String commandTag = "Command";
    String execTag = "Execute";
    String allCommandsTag = "AllCommands";
    String paramTag = "Param";
}
