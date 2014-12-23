/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package privatewhiteboard.client;

import java.awt.*;
import javax.swing.*;
import java.util.*;
import java.awt.event.*;
import java.rmi.RemoteException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.plaf.metal.MetalBorders;

/**
 *
 * @author Sherry
 */
public class EmoTabbedPane extends JFrame implements MouseListener
{
    ChatBoardPanel _chatPanel;
    
    public EmoTabbedPane(ChatBoardPanel chatPanel)
    {
        _chatPanel = chatPanel;
        JTabbedPane emoTabbedPane = new JTabbedPane();
        emoTabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
        getContentPane().add(emoTabbedPane);
        //System.out.println(EmoticonLibrary.IdToPath.get(149));
        emoTabbedPane.addTab(null, resizeImage(EmoticonLibrary.IdToPath.get(0), 40, 40), getScrollPane(getLabels(0, EmoticonLibrary.GIRL_NUM)));
        emoTabbedPane.addTab(null, resizeImage(EmoticonLibrary.IdToPath.get(43), 40, 40), getScrollPane(getLabels(43, EmoticonLibrary.BOY_NUM)));
        emoTabbedPane.addTab(null, resizeImage(EmoticonLibrary.IdToPath.get(86), 40, 40), getScrollPane(getLabels(86, EmoticonLibrary.CHRISTMAS_NUM)));
        emoTabbedPane.addTab(null, resizeImage(EmoticonLibrary.IdToPath.get(106), 40, 40), getScrollPane(getLabels(106, EmoticonLibrary.HAPPY_NUM)));
    }
    
    @Override
    public void setVisible(boolean value)
    {
        if(value == true)
        {
            _chatPanel.setEnabled(false);
            _chatPanel.setFocusCycleRoot(false);
        }
        super.setVisible(value);
    }
    
    private ImageIcon resizeImage(String s, int w, int h)
    {
        ImageIcon iIcon = new ImageIcon(ClassLoader.getSystemResource(s));
        Image image = iIcon.getImage();
        Image newImage = image.getScaledInstance(w, h, Image.SCALE_SMOOTH);
        iIcon = new ImageIcon(newImage);
        return iIcon;
    }
    
    private JPanel getLabels(int startId, int count )
    {
        JPanel panel = new JPanel(new FlowLayout());
        panel.setPreferredSize(new Dimension(300, 361));
        for (int i = 0; i < count; i++)
        {
            EmoticonLabel emoLabel = new EmoticonLabel();
            emoLabel.EmoId = startId + i;
            emoLabel.imageUpdate(null, i, i, i, i, i);
            emoLabel.setIcon(resizeImage(EmoticonLibrary.IdToPath.get(startId + i), 64, 64));
            panel.add(emoLabel);
            emoLabel.addMouseListener(this);
        }
        return panel;
    }
    
    private JScrollPane getScrollPane(JPanel emPanel)
    {
        JScrollPane emScrollPane = new JScrollPane();
        emScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        emScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        emScrollPane.setViewportView(emPanel);
        return emScrollPane;
    }
    
    @Override
    public void mouseClicked(MouseEvent me) 
    {
        if(me.getSource() instanceof EmoticonLabel)
        {
            EmoticonLabel clickedLabel = (EmoticonLabel)me.getSource();
            try {
                _chatPanel.BroadcastEmoticon(clickedLabel.EmoId);
            } catch (RemoteException e) 
            {
                e.printStackTrace();
            }
            finally
            {
                _chatPanel.setEnabled(true);
                _chatPanel.setFocusable(true);
                this.setVisible(false);
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent me) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseReleased(MouseEvent me) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(MouseEvent me) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(MouseEvent me) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

class EmoticonLabel extends JLabel
{
    public int EmoId;
}