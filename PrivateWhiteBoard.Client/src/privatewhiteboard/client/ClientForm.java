/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package privatewhiteboard.client;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.rmi.RemoteException;
import java.util.Date;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import privatewhiteboard.client.whiteboard.PaintingPanel;
import privatewhiteboard.shared.interfaces.IChatBoard;
import privatewhiteboard.shared.interfaces.IWhiteBoard;
import privatewhiteboard.shared.models.Brush;
import privatewhiteboard.shared.models.Point;

/**
 *
 * @author Otamegane
 */
public class ClientForm extends JFrame implements IWhiteBoard, IChatBoard
{
    public PaintingPanel Painting;
    public JTabbedPane  ChatOptions;
    
    public ClientForm(){
        Initialize();
        
    }
    
    private void Initialize(){
        this.setSize(new Dimension(400,400));
        Painting = new PaintingPanel();
        initChatOptions();
        
        GridBagLayout layout=new GridBagLayout();
        this.setLayout(new BorderLayout());
        
        this.add(Painting, BorderLayout.CENTER);
        
        this.add(ChatOptions, BorderLayout.LINE_END);
    }
    
    private void initChatOptions(){
        ChatOptions = new JTabbedPane(JTabbedPane.TOP);
        ChatOptions.setPreferredSize(new Dimension(300,600));
        
        ImageIcon sharingIcon = new ImageIcon("D:\\Whiteboard data\\icon_sharing.jpg");
        ImageIcon chatIcon = new ImageIcon("D:\\Whiteboard data\\icon_sharing.jpg");
        ImageIcon documentIcon = new ImageIcon("D:\\Whiteboard data\\icon_sharing.jpg");
        ImageIcon imageIcon = new ImageIcon("D:\\Whiteboard data\\icon_sharing.jpg");
        ImageIcon settingIcon = new ImageIcon("D:\\Whiteboard data\\icon_sharing.jpg");
        
        JComponent sharingPanel = makePanel();
        JComponent chatPanel = makePanel();
        JComponent documentPanel = makePanel();
        JComponent imagePanel = makePanel();
        JComponent settingPanel = makePanel();
        
        ChatOptions.addTab("", sharingIcon, sharingPanel);
        ChatOptions.addTab("", chatIcon, chatPanel);
        ChatOptions.addTab("", documentIcon, documentPanel);
        ChatOptions.addTab("", imageIcon, imagePanel);
        ChatOptions.addTab("", settingIcon, settingPanel);
    }
    
    private JComponent makePanel()  {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1, 1));
        
        return panel;
    }
    
    @Override
    public void PostFreeDraw(int drawId, String senderName, Date sentTime, Brush brush, Point[] sequenceOfPoints) throws RemoteException
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void PostLineDraw(int drawId, String senderName, Date sentTime, Brush brush, Point startPoint, Point endPoint) throws RemoteException
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void PostRectangleDraw(int drawId, String senderName, Date sentTime, Brush brush, Point center, double width, double height, boolean isFilled) throws RemoteException
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void PostEllipseDraw(int drawId, String senderName, Date sentTime, Brush brush, Point center, double r, boolean isFilled) throws RemoteException
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void PostPolygonDraw(int drawId, String senderName, Date sentTime, Brush brush, Point[] sequenceOfPoints, boolean isFilled) throws RemoteException
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void PostTextDraw(int drawId, String senderName, Date sentTime, Brush brush, String text, double fontSize, double fontName) throws RemoteException
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void PostMessage(int messageId, String senderName, Date sentTime, String messageContent) throws RemoteException
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void PostEmotion(int messageId, String senderName, Date sentTime, String emotionId) throws RemoteException
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
