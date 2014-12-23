/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package privatewhiteboard.client.whiteboard;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author Bui Thi Mai
 */
public class FilesPanel extends JPanel{
    private List<MyFile> files;
    
    public FilesPanel(List<MyFile> files){
        this.files = files;
        
        Initialize();
    }
    
    private void Initialize(){
        this.setLayout(new GridLayout(0, 1));
        GridBagConstraints contraint = new GridBagConstraints();
        
        
        JButton jbtnUploadFile = new JButton("Upload File");
        jbtnUploadFile.setPreferredSize(new Dimension(100, 100));
        ListItem item;
        
        for(MyFile file : files){
            item  = new ListItem(file);
            this.add(item);
        }
        
        this.add(jbtnUploadFile);
        
//        contraint.gridx = 0;
//        contraint.gridy = 0;
//        contraint.weightx = 0.5;
//        contraint.weighty = 0.5;
//        for(int i = 0; i < files.size(); i++)
//        {
//            contraint.gridx = 0;
//            contraint.gridy = i;
//            item = new ListItem(files.get(i));
//            this.add(item);
//        }
//        contraint.gridx = 0;
//        contraint.gridy++;
//        this.add(jbtnUploadFile);
    }
}
