/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/

package sync;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableModel;
import recognition.CommandExecutor;
import recognition.SyncListener;

/**
 *
 * @author Nami
 */
public class Display extends javax.swing.JFrame {
    
    /**
     * Creates new form Display
     */
    private static CommandExecutor exec;
    private static Display _instance = null;
    
    private synchronized static void createInstance () throws IOException {
        if (_instance == null) _instance = new Display();
    }
    public static Display getInstance() throws IOException{
        if(_instance == null )
            createInstance();
        return _instance;
    }
    
    public Display() throws IOException {
        initComponents();
        MediaPlayer mp = MediaPlayer.getInstance();
        pnlPlayer.setLayout(new BorderLayout());
        pnlPlayer.add(mp.getPlayer(), BorderLayout.SOUTH);
        setTitle(Constants.displayTitle);
    }
    
    public void setExecutor(CommandExecutor exec){
        Display.exec = exec;
    }
    public void setImage(ImageIcon icon){
        lblImage.setIcon(icon);
    }
    
    public void setImageText(String text){
        lblImage.setText(text);
    }
    public void setDisplay(final Object[] toDisplay, boolean clear){
        DefaultTableModel model = (DefaultTableModel) tblDisplay.getModel();
        if(clear)
            model.setRowCount(0);
        if(!toDisplay[1].toString().isEmpty())
            model.insertRow(0, toDisplay);
        tblDisplay.setModel(model);
        tblDisplay.setBackground(new Color(224,255,224));
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlTop = new javax.swing.JPanel();
        btnOK = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();
        btnExec = new javax.swing.JButton();
        txtCommand = new javax.swing.JTextField();
        cbSleepMode = new javax.swing.JCheckBox();
        lblInfo = new javax.swing.JLabel();
        pnlPlayer = new javax.swing.JPanel();
        lblImage = new javax.swing.JLabel();
        pnlBottom = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblDisplay = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Sync User Interface");
        setMaximumSize(new java.awt.Dimension(748, 537));
        setMinimumSize(new java.awt.Dimension(748, 537));
        setName("frameSync"); // NOI18N
        setResizable(false);

        pnlTop.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnOK.setText("Hide");
        btnOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOKActionPerformed(evt);
            }
        });
        pnlTop.add(btnOK, new org.netbeans.lib.awtextra.AbsoluteConstraints(149, 65, 124, -1));

        btnClear.setText("Clear");
        btnClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnClearActionPerformed(evt);
            }
        });
        pnlTop.add(btnClear, new org.netbeans.lib.awtextra.AbsoluteConstraints(19, 65, 124, -1));

        btnExec.setText("Execute");
        btnExec.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExecActionPerformed(evt);
            }
        });
        pnlTop.add(btnExec, new org.netbeans.lib.awtextra.AbsoluteConstraints(19, 36, 254, -1));

        txtCommand.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtCommandMouseClicked(evt);
            }
        });
        txtCommand.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCommandKeyPressed(evt);
            }
        });
        pnlTop.add(txtCommand, new org.netbeans.lib.awtextra.AbsoluteConstraints(19, 10, 254, -1));

        cbSleepMode.setText("Sleep Mode");
        cbSleepMode.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbSleepModeActionPerformed(evt);
            }
        });
        pnlTop.add(cbSleepMode, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 112, -1));

        lblInfo.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblInfo.setForeground(new java.awt.Color(102, 0, 0));
        lblInfo.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        lblInfo.setText("HERE");
        pnlTop.add(lblInfo, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 100, 330, 25));

        javax.swing.GroupLayout pnlPlayerLayout = new javax.swing.GroupLayout(pnlPlayer);
        pnlPlayer.setLayout(pnlPlayerLayout);
        pnlPlayerLayout.setHorizontalGroup(
            pnlPlayerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 120, Short.MAX_VALUE)
        );
        pnlPlayerLayout.setVerticalGroup(
            pnlPlayerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 20, Short.MAX_VALUE)
        );

        pnlTop.add(pnlPlayer, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 100, 120, 20));

        lblImage.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblImage.setText("IMAGE");
        lblImage.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        lblImage.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lblImage.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        lblImage.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblImageMouseClicked(evt);
            }
        });
        pnlTop.add(lblImage, new org.netbeans.lib.awtextra.AbsoluteConstraints(629, 10, 100, 114));

        tblDisplay.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "#", "Command", "Answer", ""
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tblDisplay);
        if (tblDisplay.getColumnModel().getColumnCount() > 0) {
            tblDisplay.getColumnModel().getColumn(0).setPreferredWidth(50);
            tblDisplay.getColumnModel().getColumn(1).setPreferredWidth(250);
            tblDisplay.getColumnModel().getColumn(2).setPreferredWidth(350);
            tblDisplay.getColumnModel().getColumn(3).setPreferredWidth(100);
        }

        javax.swing.GroupLayout pnlBottomLayout = new javax.swing.GroupLayout(pnlBottom);
        pnlBottom.setLayout(pnlBottomLayout);
        pnlBottomLayout.setHorizontalGroup(
            pnlBottomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBottomLayout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 710, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );
        pnlBottomLayout.setVerticalGroup(
            pnlBottomLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBottomLayout.createSequentialGroup()
                .addGap(1, 1, 1)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 346, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(pnlTop, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pnlBottom, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlTop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnlBottom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void lblImageMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblImageMouseClicked
        lblImage.setText("");
        lblImage.setIcon(null);
    }//GEN-LAST:event_lblImageMouseClicked

    private void cbSleepModeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbSleepModeActionPerformed
        try {
            if(cbSleepMode.isSelected()){
                lblInfo.setText(Constants.speepMessage);
                SyncListener.getInstance().setListening(false);
            } else {
                lblInfo.setText(Constants.listenMessage);
                SyncListener.getInstance().setListening(true);
            }
        } catch (Exception ex) {
            setInfo(Constants.errorParseMessage);
        }
    }//GEN-LAST:event_cbSleepModeActionPerformed

    private void txtCommandMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtCommandMouseClicked
        txtCommand.setText("");
    }//GEN-LAST:event_txtCommandMouseClicked

    private void btnExecActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExecActionPerformed
        try {
            if(txtCommand.getText().toLowerCase().equalsIgnoreCase(Constants.sleepCommands[0]) ||
                    txtCommand.getText().toLowerCase().equalsIgnoreCase(Constants.sleepCommands[1]) ||
                    txtCommand.getText().toLowerCase().equalsIgnoreCase(Constants.sleepCommands[2])) {
                sleepMode(true);
            } else if(txtCommand.getText().toLowerCase().equalsIgnoreCase(Constants.wakeUpCommand)){
                sleepMode(false);
            } else {
                exec.parseCommand(txtCommand.getText().toLowerCase());
            }
            txtCommand.setText("");
        } catch (Exception ex) {
            setInfo(Constants.internetErrorMessage);
        }
    }//GEN-LAST:event_btnExecActionPerformed

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnClearActionPerformed
        DefaultTableModel model = (DefaultTableModel) tblDisplay.getModel();
        model.setRowCount(0);
        lblImage.setText("");
        lblImage.setIcon(null);
    }//GEN-LAST:event_btnClearActionPerformed

    private void btnOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOKActionPerformed
        this.hide();
    }//GEN-LAST:event_btnOKActionPerformed

    private void txtCommandKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCommandKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode() == KeyEvent.VK_ENTER){
            btnExec.doClick();
        }
    }//GEN-LAST:event_txtCommandKeyPressed
    
    public void setInfo(String info){
        lblInfo.setText(info);
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
        * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
        */
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            //
        }
        //</editor-fold>
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    Display.getInstance();
                    //new Display().setVisible(true);
                } catch (IOException ex) {
                    Logger.getLogger(Display.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnExec;
    private javax.swing.JButton btnOK;
    private javax.swing.JCheckBox cbSleepMode;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblImage;
    private javax.swing.JLabel lblInfo;
    private javax.swing.JPanel pnlBottom;
    private javax.swing.JPanel pnlPlayer;
    private javax.swing.JPanel pnlTop;
    private javax.swing.JTable tblDisplay;
    private javax.swing.JTextField txtCommand;
    // End of variables declaration//GEN-END:variables
    
    public void sleepMode(boolean b) {
        if(!cbSleepMode.isSelected() && b){
            cbSleepMode.doClick();
        } else if(cbSleepMode.isSelected() && !b) {
            cbSleepMode.doClick();
        }
    }
    
    public boolean isCommandEmpty() {
        return txtCommand.getText().isEmpty();
    }
}