/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package privatewhiteboard.client.whiteboard;

import privatewhiteboard.client.whiteboard.PaintSurface;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.JPanel;
import privatewhiteboard.shared.controllers.IClientController;

/**
 *
 * @author Bui Thi Mai
 */
public class PaintingPanel extends JPanel{
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JList jlPaintOptions;
    PaintSurface paint = new PaintSurface();
    
    IClientController _clientController;
    
    public PaintingPanel(){
    }
    
    public void Initialize(IClientController clientController){
        _clientController=clientController;
        
        this.setLayout(new GridBagLayout());
        GridBagConstraints contraint = new GridBagConstraints();
        contraint.fill = GridBagConstraints.HORIZONTAL;
        
        jScrollPane1 = new javax.swing.JScrollPane();
        jlPaintOptions = new javax.swing.JList();
        
        jlPaintOptions.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jlPaintOptions.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { " Pointer", " FreeHand", " Line  ", " Rectangle", " Ellipse", " Text", " Erase", " Hand" };
            @Override
            public int getSize() { return strings.length; }
            @Override
            public Object getElementAt(int i) { return strings[i]; }
        });
        jlPaintOptions.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jlPaintOptions.setLayoutOrientation(javax.swing.JList.VERTICAL_WRAP);
        jlPaintOptions.setSelectedIndex(1);
        jlPaintOptions.setVisibleRowCount(1);
        jScrollPane1.setViewportView(jlPaintOptions);
        
        //add list paint options
        contraint.weightx = 0.5;
        contraint.weighty = 0.5;
        contraint.gridx = 0;
        contraint.gridy = 0;
        contraint.anchor = GridBagConstraints.BASELINE_LEADING;
        this.add(jScrollPane1, contraint);
        
        //add paint to panel
        contraint.ipady = 400;
        contraint.weightx = 0.5;
        contraint.weighty = 0.5;
        contraint.gridx = 0;
        contraint.gridy = 1;
        contraint.anchor = GridBagConstraints.ABOVE_BASELINE_LEADING;
        this.add(paint, contraint);
        
    }
}

