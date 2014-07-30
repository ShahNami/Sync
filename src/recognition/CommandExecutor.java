/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/

package recognition;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Component;
import java.awt.Frame;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import sync.Display;
import synthesis.SyncTalker;
import java.lang.reflect.*;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
import javax.xml.bind.JAXBException;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import org.fedy2.weather.YahooWeatherService;
import org.fedy2.weather.data.Channel;
import org.fedy2.weather.data.unit.DegreeUnit;
import sync.MediaEnum;
import sync.MediaPlayer;

/**
 *
 * @author Nami
 */
public class CommandExecutor {
    SyncListener listener;
    private static Display display;
    public static Object[] infoTable = new String[4];
    
    public static Object[] getInfoTable(){
        return infoTable;
    }
    public CommandExecutor(SyncListener listener) throws IOException{
        this.listener = listener;
    }
    
    public CommandExecutor(Display display) throws IOException{
        CommandExecutor.display = display;
    }
    
    public static Display getDisplay(){
        return display;
    }
    
    
    public void parseCommand(String c) throws IOException, Exception{
        parseFile(c);
    }
    
    
    private void execute(String method, List<String> params) throws Exception{
        if(!params.isEmpty()){
            //Loop through <PARAM>
            Class[] c=new Class[params.size()];
            for(int k=0;k<params.size();k++){
                c[k] = String.class;
            }
            for(int k=0;k<params.size();k++){
                CommandExecutor.invoke("recognition.Executor", method, c,
                        new Object[]{params.get(k)});
            }
        } else {
            CommandExecutor.invoke("recognition.Executor", method, new Class[] {},
                    new Object[]{});
        }
    }
    
    public void parseFile(String com) throws XMLStreamException, FileNotFoundException, Exception{
        
        /* STAX PARSER (SUPPOSEDLY FASTER)
        * Also see:
        * http://www.programcreek.com/2011/08/java-parse-xml-by-using-stax/
        */
        XMLInputFactory inputFactory = XMLInputFactory.newInstance();
        InputStream in = new FileInputStream(CommandExecutor.class.getResource("commands.xml").getPath());
        XMLStreamReader streamReader = inputFactory.createXMLStreamReader(in);
        streamReader.nextTag(); // Advance to "AllCommands" element
        streamReader.nextTag(); // Advance to "Command" element
        String name=null;
        List<String> answers = new ArrayList<>();
        String execution = null;
        List<String> params = new ArrayList<>();
        Map<String, List<String>> executions = new HashMap<>();
        while (streamReader.hasNext()) {
            if (streamReader.isStartElement()) {
                switch (streamReader.getLocalName()) {
                    case "name":{
                        name = streamReader.getElementText();
                        break;
                    }
                    case "descr":{
                        //streamReader.nextTag();
                        break;
                    }
                    case "Answer": {
                        //Atribute
                        if(com.toLowerCase().contains(name.toLowerCase())){
                            answers.add(streamReader.getAttributeValue(0));
                        }
                        break;
                    }
                    case "Execute": {
                        //Atribute
                        if(com.toLowerCase().contains(name.toLowerCase())){
                            execution = streamReader.getAttributeValue(0);
                        }
                        break;
                    }
                    case "Param":{
                        if(com.toLowerCase().contains(name.toLowerCase())){
                            params.add(streamReader.getAttributeValue(0));
                        }
                        break;
                    }
                }
                if(execution != null)
                    executions.put(execution, params);
            }
            streamReader.next();
        }
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        String formattedDate = sdf.format(date);
        infoTable[0] = formattedDate; //#
        infoTable[1] = com; //Command
        //Answer the command if necessary
        if(answers.size() > 0){
            Random generator = new Random();
            String answer = answers.get(generator.nextInt(answers.size()));
            display.setInfo("Answering...");
            SyncTalker.getInstance().say(answer);
            infoTable[2] = answer; //Answer
        } else {
            infoTable[2] = "-";
        }
        infoTable[3] = "-";
        for (Map.Entry<String, List<String>> entry : executions.entrySet())
        {
            execute(entry.getKey(), entry.getValue());
        }
        if(executions.isEmpty())
            display.setDisplay(infoTable, false);
    }
    
    public static void invoke(String aClass, String aMethod, Class[] params, Object[] args) throws Exception {
        Class c = Class.forName(aClass);
        Method m = c.getDeclaredMethod(aMethod, params);
        Object i = c.newInstance();
        Object r = m.invoke(i, args);
    }
}


class Executor {
    Display display;
    public Executor() throws IOException{
        display = CommandExecutor.getDisplay();
    }
    
    /**
     * DISPLAY AND INFORMATION
     * @param talk
     * @param answer
     * @param info
     * @param clear
     * @throws IOException
     */
    private void setExecuted(boolean talk, String answer, String info, boolean clear) throws IOException, InterruptedException{
        if(talk)
            SyncTalker.getInstance().say(answer);
        if(!answer.isEmpty()){
            CommandExecutor.getInfoTable()[2] = answer;
        }
        this.display.setDisplay(CommandExecutor.getInfoTable(), clear);
        this.display.setInfo(info+"...");
    }
    
    /**
     * Play Music
     */
    public void playMusic(String action) throws IOException, InterruptedException{
        //exec("start wmplayer \"C:\\Users\\Nami.Nami-PC\\Google Drive\\Public\\Sync\\music\\A.wpl\" \\fullscreen");
        switch(action){
            case "play":{
                MediaPlayer.getInstance().getPlayer().play();
                setExecuted(false, "", "Playing music", false);
                MediaPlayer.getInstance().setStatus(MediaEnum.isPlaying);
            }
            break;
            case "next":{
                MediaPlayer.getInstance().getPlayer().skipForward();
                setExecuted(false, "", "Song skipped", false);
            }
            break;
            case "previous":{
                MediaPlayer.getInstance().getPlayer().skipBackward();
                setExecuted(false, "", "Song skipped", false);
            }
            break;
            case "pause":{
                MediaPlayer.getInstance().getPlayer().pause();
                setExecuted(false, "", "Song paused", false);
                MediaPlayer.getInstance().setStatus(MediaEnum.wasPlaying);
            }
            break;
            case "stop":{
                MediaPlayer.getInstance().getPlayer().stop();
                setExecuted(false, "", "Song stopped", false);
                MediaPlayer.getInstance().setStatus(MediaEnum.isNotPlaying);
            }
            break;
        }
    }
    
    /**
     * HOTKEY
     */
    public void hotKey(String key) throws AWTException, IOException, InterruptedException{
        Robot robot = new Robot();
        switch(key){
            case "close":{
                robot.keyPress(KeyEvent.VK_ALT);
                robot.keyPress(KeyEvent.VK_F4);
                robot.delay(10);
                robot.keyRelease(KeyEvent.VK_ALT);
                robot.keyRelease(KeyEvent.VK_F4);
            }
            break;
            case "screenshot":{
                SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd@HHmmss");
                Calendar now = Calendar.getInstance();
                BufferedImage image = new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
                ImageIO.write(image, "png", new File(System.getProperty("user.home") + "\\Desktop\\"+formatter.format(now.getTime())+".png"));
            }
            break;
            case "switch":{
                robot.keyPress(KeyEvent.VK_ALT);
                robot.keyPress(KeyEvent.VK_TAB);
                robot.delay(10);
                robot.keyRelease(KeyEvent.VK_ALT);
                robot.keyRelease(KeyEvent.VK_TAB);
            }
            break;
            case "desktop":{
                robot.keyPress(KeyEvent.VK_WINDOWS);
                robot.keyPress(KeyEvent.VK_D);
                robot.delay(10);
                robot.keyRelease(KeyEvent.VK_WINDOWS);
                robot.keyRelease(KeyEvent.VK_D);
            }
            break;
        }
        setExecuted(false, "", "Hotkey pressed", false);
    }
    
    /**
     * SHOW COMMANDS
     */
    public void showCommands() throws XMLStreamException, FileNotFoundException, IOException, InterruptedException{
        this.display.setDisplay(new Object[]{"","************************************", "******************************************************"}, false);
        XMLInputFactory inputFactory = XMLInputFactory.newInstance();
        InputStream in = new FileInputStream(CommandExecutor.class.getResource("commands.xml").getPath());
        XMLStreamReader streamReader = inputFactory.createXMLStreamReader(in);
        streamReader.nextTag(); // Advance to "AllCommands" element
        streamReader.nextTag(); // Advance to "Command" element
        String name;
        while (streamReader.hasNext()) {
            if (streamReader.isStartElement()) {
                switch (streamReader.getLocalName()) {
                    case "name":{
                        name = streamReader.getElementText();
                        CommandExecutor.getInfoTable()[1] = name;
                        setExecuted(false, "", "Commands shown", false);
                        break;
                    }
                    case "descr":{
                        //streamReader.nextTag();
                        break;
                    }
                    case "Answer": {
                        //Atribute
                        //CommandExecutor.getInfoTable()[2] = streamReader.getAttributeValue(0);
                        break;
                    }
                    case "Execute": {
                        //Atribute
                        //CommandExecutor.getInfoTable()[3] = streamReader.getAttributeValue(0);
                        break;
                    }
                    case "Param":{
                        //CommandExecutor.getInfoTable()[4] = streamReader.getAttributeValue(0);
                        break;
                    }
                }
            }
            streamReader.next();
        }
        this.display.setDisplay(new Object[]{"","************************************", "******************************************************"}, false);
    }
    
    public void show(String s){
        if(Integer.parseInt(s) == 0)
            this.display.setState(Frame.ICONIFIED);
        else
            this.display.setState(Frame.NORMAL);
    }
    
    
    /**
     * SLEEP AND WAKE UP
     * @throws IOException
     */
    
    public void sleep() throws IOException, InterruptedException, Exception{
        SyncListener.getInstance().setListening(false);
        display.sleepMode(true);
        setExecuted(false, "", "Sleeping", false);
    }
    
    public void wakeup() throws IOException, InterruptedException, Exception{
        SyncListener.getInstance().setListening(true);
        display.sleepMode(false);
        setExecuted(false, "", "Listening", false);
    }
    
    /**
     * WEATHER
     * @param inDay
     * @throws JAXBException
     * @throws IOException
     */
    public void weather(String inDay) throws JAXBException, IOException, InterruptedException{
        YahooWeatherService service = new YahooWeatherService();
        Channel channel = service.getForecast("966591", DegreeUnit.CELSIUS);
        double high = channel.getItem().getForecasts().get(Integer.parseInt(inDay)).getHigh();
        double low = channel.getItem().getForecasts().get(Integer.parseInt(inDay)).getLow();
        String forecast = channel.getItem().getForecasts().get(Integer.parseInt(inDay)).getText();
        int code = channel.getItem().getForecasts().get(Integer.parseInt(inDay)).getCode();
        double temp = channel.getItem().getCondition().getTemp();
        double humidity = channel.getAtmosphere().getHumidity();
        
        URL where = new URL("http://s.imwx.com/v.20131006.215409/img/wxicon/100/"+code+".png");
        ImageIcon image = new ImageIcon(where);
        display.setImage(image);
        String answer;
        if(Integer.parseInt(inDay) == 0){
            answer = "It is " + temp + " degrees and " + forecast + ".";
            display.setImageText("RealFeel"+Character.toString((char)174)+" "+Double.toString(Math.round(toCelcius(heatIndex(toFahrenheit(temp), humidity/100)))));
        } else {
            answer = "Temperatures may reach up to " + high + " degrees and " + forecast+ ".";
            display.setImageText("RealFeel"+Character.toString((char)174)+" N/A");
        }
        CommandExecutor.getInfoTable()[3] = "H: " + high + " | L: " + low;
        setExecuted(true,answer,"Listening",false);
    }
    
    private double heatIndex(double temp, double humidity){
        double answer;
        final double C1 = -42.379;
        final double C2 = 2.04901523;
        final double C3 = 10.14333127;
        final double C4 = -0.22475541;
        final double C5 = -.00683783;
        final double C6 = -5.481717E-2;
        final double C7 = 1.22874E-3;
        final double C8 = 8.5282E-4;
        final double C9 = -1.99E-6;
        double T = temp;
        double R = humidity;
        double T2 = temp * temp;
        double R2 = humidity * humidity;
        //Function of Calculating Heat Index
        answer = C1 + (C2 * T) + (C3 * R) + (C4 * T * R) + (C5 * T2) + (C6 * R2) + (C7 * T2 * R) + (C8 * T * R2) + (C9 * T2 * R2);
        
        return answer;
    }
    
    private double toFahrenheit(double temp){
        return ((temp * 9)/5) + 32;
    }
    private double toCelcius(double temp){
        return ((temp - 32) * 5)/9;
    }
    
    /**
     * FILES AND DIRECTORIES
     * @param comm
     * @throws IOException
     */
    public void exec(String comm) throws IOException{
        try{
            String command = "cmd /c " + comm;
            Process child = Runtime.getRuntime().exec(command);
            //child.destroy();
        } catch (IOException ex){
            System.out.println(ex);
        }
    }
    
    /**
     * TURN OFF
     * @throws IOException
     */
    public void cleanShutdown() throws IOException, InterruptedException{
        setExecuted(false,"","Shutting down",false);
        Thread.sleep(2100); //So it can answer before it closes.
        this.display.dispose();
        System.exit(0);
    }
    
    /**
     * TIME AND DATE
     * @param t
     * @throws IOException
     */
    public void tellTheDate(String t) throws IOException, InterruptedException{
        Date d = new Date();
        Calendar cal = Calendar.getInstance();
        String[] weekDays = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
        String[] monthNames = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        String answer;
        switch(t){
            case "time":
                answer = "it is " + d.getHours() + " hours and "+ d.getMinutes() + " minutes sir.";
                break;
            case "day":
                answer = "it is " + weekDays[d.getDay()] + " sir.";
                break;
            case "date":
                cal.setTime(d);
                answer = "Today is " + weekDays[d.getDay()] + ", " + cal.get(Calendar.DAY_OF_MONTH) + " " + monthNames[cal.get(Calendar.MONTH)] + " " + cal.get(Calendar.YEAR);
                break;
            default:
                answer = "I have no idea sir.";
                break;
        }
        setExecuted(true, answer, "Done", false);
    }
}