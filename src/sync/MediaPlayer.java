package sync;

import jaco.mp3.player.MP3Player;

import java.io.File;
import java.io.IOException;

public final class MediaPlayer {
    private static MP3Player player;
    private static MediaPlayer _instance = null;
    private MediaEnum state;
    
    public MediaEnum getStatus(){
        return state;
    }
    public void setStatus(MediaEnum s){
        state = s;
    }
    private synchronized static void createInstance () throws IOException {
        if (_instance == null) _instance = new MediaPlayer();
    }
    public static MediaPlayer getInstance() throws IOException{
        if(_instance == null )
            createInstance();
        return _instance;
    }
    
    public MP3Player getPlayer(){
        return player;
    }
    public MediaPlayer(){
        //MP3Player.setDefaultUI(MP3PlayerUICompact.class);
        String musicLocation = Constants.musicPath;
        File musicList = new File(musicLocation);
        String[] songsResponse = musicList.list();
        
        
        player = new MP3Player();
        player.setShuffle(true);
        //N listener = new N();
        //player.addMP3PlayerListener(listener);
        player.setRepeat(true);
        for (String songsResponse1 : songsResponse) {
            player.addToPlayList(new File(musicLocation + songsResponse1));
        }
        //
        
        //player.setBorder(BorderFactory.createEmptyBorder(50, 100, 50, 100));
        //JFrame frame = new JFrame(Constants.mp3FrameTitle);
        //frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        //frame.getContentPane().add(player);
        //frame.pack();
        //frame.setLocationRelativeTo(null);
        //frame.setVisible(true);
        this.setStatus(MediaEnum.isNotPlaying);
    }
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//        * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
//        */
//        try {
//            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
//        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
//            //
//        }
//        //</editor-fold>
//        
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            @Override
//            public void run() {
//                try {
//                    MediaPlayer.getInstance();
//                } catch (IOException ex) {
//                    Logger.getLogger(MediaPlayer.class.getName()).log(Level.SEVERE, null, ex);
//                }
//            }
//        });
//    }
}
