/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package privatewhiteboard.client.whiteboard;

import privatewhiteboard.client.whiteboard.PaintSurface;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Rectangle;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import privatewhiteboard.shared.controllers.IClientController;

/**
 *
 * @author Bui Thi Mai
 */
public class PaintingPanel extends JPanel{
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JList jlPaintOptions;
    private PaintSurface paint;
    private JScrollPane paintScrollPanel;
    private ColorChooser colorChooser;
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
        paintScrollPanel = new JScrollPane();
        colorChooser = new ColorChooser();
        paint = new PaintSurface(this);
        
        paint.fillColor = colorChooser.getCurrentColor();
        
        paintScrollPanel.scrollRectToVisible(new Rectangle(0, 0, 400, 400));

        //jlPaintOptions set up
        jlPaintOptions.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jlPaintOptions.setModel(new javax.swing.AbstractListModel() {
            String[] strings = {" Pointer", " FreeHand", " Line  ", " Rectangle", " Ellipse", " Text", " Eraser", " Hand"};

            @Override
            public int getSize() {
                return strings.length;
            }

            @Override
            public Object getElementAt(int i) {
                return strings[i];
            }
        });
        jlPaintOptions.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jlPaintOptions.setLayoutOrientation(javax.swing.JList.VERTICAL_WRAP);
        jlPaintOptions.setSelectedIndex(1);
        jlPaintOptions.setVisibleRowCount(1);
        jlPaintOptions.addListSelectionListener(new PaintOptionListener());
        jScrollPane1.setViewportView(jlPaintOptions);
        
        //add list paint options
        contraint.weightx = 0.5;
        contraint.weighty = 0.5;
        contraint.gridx = 0;
        contraint.gridy = 0;
        contraint.anchor = GridBagConstraints.BASELINE_LEADING;
        this.add(jScrollPane1, contraint);
        
        
        contraint.gridx = 0;
        contraint.gridy = 2;
        contraint.ipadx = 5;
        contraint.anchor = GridBagConstraints.ABOVE_BASELINE_LEADING;
        this.add(colorChooser, contraint);
        

        //add paint to panel
        contraint.ipady = 400;
        contraint.weightx = 0.5;
        contraint.weighty = 0.5;
        contraint.gridx = 0;
        contraint.gridy = 3;
        contraint.anchor = GridBagConstraints.ABOVE_BASELINE_LEADING;

        paintScrollPanel.setViewportView(paint);

        this.add(paintScrollPanel, contraint);
        
    }
    
    private PaintOptions getOption(int index) {
        PaintOptions result = PaintOptions.DRAW;
        switch (index) {
            case 0:
                result = PaintOptions.POINTER;
                break;
            case 1:
                paint.shapeType =  getShapeType(index);
                result = PaintOptions.DRAW;
                break;
            case 2:
                paint.shapeType =  getShapeType(index);
                result = PaintOptions.DRAW;
                break;
            case 3:
                paint.shapeType =  getShapeType(index);
                result = PaintOptions.DRAW;
                break;
            case 4:
                paint.shapeType =  getShapeType(index);
                result = PaintOptions.DRAW;
                break;
            case 5:
                paint.shapeType =  getShapeType(index);
                result = PaintOptions.DRAW;
                break;
            case 6:
                result = PaintOptions.ERASER;
                break;
            case 7:
                result = PaintOptions.HAND;
                break;
            default:
                result = PaintOptions.DRAW;
                break;
        }
        return result;
    }

    private ShapeTypes getShapeType(int index) {
        ShapeTypes result = ShapeTypes.RECTANGLE;
        switch (index) {
            case 1:
                result = ShapeTypes.NONE;
                break;
            case 2:
                result = ShapeTypes.LINE;
                break;
            case 3:
                result = ShapeTypes.RECTANGLE;
                break;
            case 4:
                result = ShapeTypes.ELLIPE;
                break;
            case 5:
                result = ShapeTypes.TEXT;
                break;
            default:
                result = ShapeTypes.RECTANGLE;
                break;
        }

        return result;
    }

    public ColorChooser getColorChooser() {
        return colorChooser;
    }

    //Event controler
    private class PaintOptionListener implements ListSelectionListener {

        @Override
        public void valueChanged(ListSelectionEvent e) {
            paint.option = getOption(jlPaintOptions.getSelectedIndex());
        }
    }
}

