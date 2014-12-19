package privatewhiteboard.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.rmi.AccessException;
import java.rmi.AlreadyBoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.ExportException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonGroup;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import privatewhiteboard.server.controllers.ServerController;
import privatewhiteboard.shared.remotes.IRemoteServer;
import privatewhiteboard.shared.remotes.RemoteServer;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Otamegane
 */
public class ServerForm extends javax.swing.JFrame
{

    /**
     * Creates new form ServerForm
     */
    public ServerForm()
    {
        initComponents();
        
        ButtonGroup buttonGroup=new ButtonGroup();
        buttonGroup.add(radioUsingLocal);
        buttonGroup.add(radioUsingExternal);
        try
        {
            String ipAddress=GetExternalIpAddress();
            if(ipAddress!=null && !ipAddress.equals(""))
                txtServerIP.setText(ipAddress);
        }
        catch (IOException ex)
        {
            JOptionPane.showMessageDialog(this, "Can't not connect to the internet, the server can now be using in local!");
            radioUsingLocal.setSelected(true);
            try
            {
                String ipAddress=GetLocalIpAddress();
                if(ipAddress!=null && !ipAddress.equals(""))
                    txtServerIP.setText(ipAddress);
            }
            catch (UnknownHostException ex1)
            {
                JOptionPane.showMessageDialog(this, "Can't not get local ip address, please check your network card!");
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        jTextField3 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        txtServerName = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtServerIP = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtPassword = new javax.swing.JPasswordField();
        btnStartServer = new javax.swing.JButton();
        btnExit = new javax.swing.JButton();
        radioUsingLocal = new javax.swing.JRadioButton();
        radioUsingExternal = new javax.swing.JRadioButton();
        labelPort = new javax.swing.JLabel();
        txtPort = new javax.swing.JTextField();

        jTextField3.setText("jTextField3");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Private Whiteboard Server");

        jLabel1.setText("Server name:");

        txtServerName.setText("PrivateWhiteboardServer");

        jLabel2.setText("Server IP:");

        txtServerIP.setEditable(false);
        txtServerIP.setText("Configuring server address...");
        txtServerIP.setToolTipText("");

        jLabel3.setText("Password:");

        btnStartServer.setText("Start server");
        btnStartServer.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnStartServerActionPerformed(evt);
            }
        });

        btnExit.setText("Exit");
        btnExit.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                btnExitActionPerformed(evt);
            }
        });

        radioUsingLocal.setText("Using local network");
        radioUsingLocal.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                radioUsingLocalActionPerformed(evt);
            }
        });

        radioUsingExternal.setSelected(true);
        radioUsingExternal.setText("Using external network");
        radioUsingExternal.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                radioUsingExternalActionPerformed(evt);
            }
        });

        labelPort.setText("Port:");

        txtPort.setText("1999");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(26, 26, 26)
                        .addComponent(txtServerIP))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnStartServer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(radioUsingLocal, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnExit, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(radioUsingExternal))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel3)
                            .addComponent(labelPort))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtServerName)
                            .addComponent(txtPassword)
                            .addComponent(txtPort))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(txtServerIP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel2)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelPort)
                    .addComponent(txtPort, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtServerName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(radioUsingLocal)
                    .addComponent(radioUsingExternal))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnStartServer)
                    .addComponent(btnExit))
                .addGap(25, 25, 25))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private boolean ValidFields()
    {
        if(txtServerIP.getText().equals("Configuring server address..."))
        {
            JOptionPane.showMessageDialog(this, "Please wait until the server completes looking for ip address!");
            return false;
        }
        
        try
        {
            Integer.parseInt(txtPort.getText());
        }
        catch(NumberFormatException ex)
        {
            JOptionPane.showMessageDialog(this, "Port must be type of integer");
            return false;
        }
        
        for(int i=0;i<txtServerName.getText().length();i++)
        {
            if(!Character.isAlphabetic(txtServerName.getText().charAt(i)) && Character.isDigit(txtServerName.getText().charAt(i)))
            {
                JOptionPane.showMessageDialog(this, "Server name can only contain alphablet and digit!");
                return false;
            }
        }
        
        if(txtPassword.getText().length()<4)
        {
            JOptionPane.showMessageDialog(this, "Password field must have at least 4 character!");
            return false;
        }
        return true;
    }
    
    static Registry currentRegistry;
    boolean isServerRunning=false;
    
    private void DisableControlsWhileRunning()
    {
        txtServerName.setEditable(false);
        txtPort.setEditable(false);
        txtPassword.setEditable(false);
        radioUsingLocal.setEnabled(false);
        radioUsingExternal.setEnabled(false);
        btnExit.setEnabled(false);
        btnStartServer.setText("Stop server");
        isServerRunning=true;
    }
    
    private void EnableControlsWhileStopping()
    {
        txtServerName.setEditable(true);
        txtPort.setEditable(true);
        txtPassword.setEditable(true);
        radioUsingLocal.setEnabled(true);
        radioUsingExternal.setEnabled(true);
        btnExit.setEnabled(true);
        btnStartServer.setText("Start server");
        isServerRunning=false;
    }
    
    private void StartUpServer()
    {
        DisableControlsWhileRunning();
        if(!ValidFields())
        {
            EnableControlsWhileStopping();
            return;
        }
            
        try
        {
            System.out.println("Starting up server!");
            try
            {
                currentRegistry=LocateRegistry.createRegistry(Integer.parseInt(txtPort.getText()));
            }
            catch(ExportException _ex)
            {
                currentRegistry=LocateRegistry.getRegistry(Integer.parseInt(txtPort.getText()));
            }
            IRemoteServer remoteServer=new RemoteServer(new ServerController(txtPassword.getText()));
            currentRegistry.rebind(txtServerName.getText(), remoteServer);
            System.out.println("The server is now started!");
        }
        catch (AccessException ex)
        {
            JOptionPane.showMessageDialog(this, "An exception occured while starting up server\nDescription:\n"+ex.getMessage());
        }
        catch(RemoteException ex)
        {
            JOptionPane.showMessageDialog(this, "An exception occured while starting up server\nDescription:\n"+ex.getMessage());
        }
    }
    
    private void StopServer()
    {
        try
        {
            currentRegistry.unbind(txtServerName.getText());
        }
        catch (RemoteException ex)
        {
            Logger.getLogger(ServerForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (NotBoundException ex)
        {
            Logger.getLogger(ServerForm.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        EnableControlsWhileStopping();
        
        System.out.println("The server is now stopped!");
    }
    
    private void btnStartServerActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnStartServerActionPerformed
    {//GEN-HEADEREND:event_btnStartServerActionPerformed
        if(!isServerRunning)
            StartUpServer();
        else
            StopServer();
    }//GEN-LAST:event_btnStartServerActionPerformed

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_btnExitActionPerformed
    {//GEN-HEADEREND:event_btnExitActionPerformed
        System.exit(0);
    }//GEN-LAST:event_btnExitActionPerformed

    private void radioUsingLocalActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_radioUsingLocalActionPerformed
    {//GEN-HEADEREND:event_radioUsingLocalActionPerformed
        if(((JRadioButton)evt.getSource()).isSelected())
        {
            try
            {
                String temp=GetLocalIpAddress();
                if(!temp.equals(""))
                {
                    txtServerIP.setText(temp);
                }
            }
            catch (UnknownHostException ex)
            {
                Logger.getLogger(ServerForm.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_radioUsingLocalActionPerformed

    private void radioUsingExternalActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_radioUsingExternalActionPerformed
    {//GEN-HEADEREND:event_radioUsingExternalActionPerformed
        if(((JRadioButton)evt.getSource()).isSelected())
        {
            try
            {
                String temp=GetExternalIpAddress();
                if(!temp.equals(""))
                {
                    txtServerIP.setText(temp);
                }
            }
            catch (UnknownHostException ex)
            {
                Logger.getLogger(ServerForm.class.getName()).log(Level.SEVERE, null, ex);
            }
            catch (IOException ex)
            {
                Logger.getLogger(ServerForm.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_radioUsingExternalActionPerformed

    private static String GetExternalIpAddress() throws MalformedURLException, IOException
    {
        URL url=new URL("http://bot.whatismyipaddress.com");
        BufferedReader reader=new BufferedReader(new InputStreamReader(url.openStream()));
        String ipAddress=reader.readLine().trim();
        return ipAddress;
    }
    
    private static String GetLocalIpAddress() throws UnknownHostException
    {
        InetAddress ip=InetAddress.getLocalHost();
        return ip.getHostAddress();
    }

    /**
     * @param args the command line arguments
     */
    public static void _main(String args[])
    {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try
        {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels())
            {
                if ("Nimbus".equals(info.getName()))
                {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        }
        catch (ClassNotFoundException ex)
        {
            java.util.logging.Logger.getLogger(ServerForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        catch (InstantiationException ex)
        {
            java.util.logging.Logger.getLogger(ServerForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        catch (IllegalAccessException ex)
        {
            java.util.logging.Logger.getLogger(ServerForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        catch (javax.swing.UnsupportedLookAndFeelException ex)
        {
            java.util.logging.Logger.getLogger(ServerForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                new ServerForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnStartServer;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JLabel labelPort;
    private javax.swing.JRadioButton radioUsingExternal;
    private javax.swing.JRadioButton radioUsingLocal;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JTextField txtPort;
    private javax.swing.JTextField txtServerIP;
    private javax.swing.JTextField txtServerName;
    // End of variables declaration//GEN-END:variables
}