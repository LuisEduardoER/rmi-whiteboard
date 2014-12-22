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
import java.rmi.RemoteException;
import java.util.Date;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import privatewhiteboard.shared.controllers.IClientController;
import privatewhiteboard.shared.interfaces.IWhiteBoard;
import privatewhiteboard.shared.models.Brush;
import privatewhiteboard.shared.models.Point;

/**
 *
 * @author Bui Thi Mai
 */
public class PaintingPanel extends JPanel implements IWhiteBoard{
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JList jlPaintOptions;
    private PaintSurface paint;
    private JScrollPane paintScrollPanel;
    private ColorChooser colorChooser;
    private JSlider strokeSlider;
    
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
        colorChooser = new ColorChooser();
        paint = new PaintSurface(this);
        paintScrollPanel = new JScrollPane(paint);
        strokeSlider = new JSlider(2, 20, 2);
        
        paint.fillColor = colorChooser.getCurrentColor();
        
        //jlPaintOptions set up
        jlPaintOptions.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jlPaintOptions.setModel(new javax.swing.AbstractListModel() {
            String[] strings = {" Pointer", " FreeHand", " Line  ", " Rectangle", " Ellipse", " Text", " Eraser"};

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
        
        strokeSlider.setMinorTickSpacing(2);
        strokeSlider.setMajorTickSpacing(10);
        strokeSlider.setPaintTicks(true);
        strokeSlider.setPaintLabels(true);
        strokeSlider.setLabelTable(strokeSlider.createStandardLabels(10));
        strokeSlider.addChangeListener(new StrokeListener());
        
        //add list paint options
        contraint.weightx = 0.5;
        contraint.weighty = 0.5;
        contraint.gridx = 0;
        contraint.gridy = 0;
        contraint.anchor = GridBagConstraints.BASELINE_LEADING;
        this.add(jScrollPane1, contraint);
        
        
        contraint.gridx = 0;
        contraint.gridy = 2;
        this.add(colorChooser, contraint);
        
        contraint.gridx = 1;
        contraint.gridy = 3;
        this.add(strokeSlider, contraint);

        //add paint to panel
        contraint.gridx = 0;
        contraint.gridy = 4;
        contraint.gridwidth = 2;

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

    @Override
    public void PostFreeDraw(int drawId, String senderName, Date sentTime, Brush brush, Point[] sequenceOfPoints) throws RemoteException {
        paint.PostFreeDraw(drawId, senderName, sentTime, brush, sequenceOfPoints);
    }

    @Override
    public void PostLineDraw(int drawId, String senderName, Date sentTime, Brush brush, Point startPoint, Point endPoint) throws RemoteException {
        paint.PostLineDraw(drawId, senderName, sentTime, brush, startPoint, endPoint);
    }

    @Override
    public void PostRectangleDraw(int drawId, String senderName, Date sentTime, Brush brush, Point center, double width, double height, boolean isFilled) throws RemoteException {
        paint.PostRectangleDraw(drawId, senderName, sentTime, brush, center, width, height, isFilled);
    }

    @Override
    public void PostEllipseDraw(int drawId, String senderName, Date sentTime, Brush brush, Point center, double r, boolean isFilled) throws RemoteException {
        paint.PostEllipseDraw(drawId, senderName, sentTime, brush, center, r, isFilled);
    }

    @Override
    public void PostPolygonDraw(int drawId, String senderName, Date sentTime, Brush brush, Point[] sequenceOfPoints, boolean isFilled) throws RemoteException {
        paint.PostPolygonDraw(drawId, senderName, sentTime, brush, sequenceOfPoints, isFilled);
    }

    @Override
    public void PostTextDraw(int drawId, String senderName, Date sentTime, Brush brush, String text, double fontSize, double fontName) throws RemoteException {
        paint.PostTextDraw(drawId, senderName, sentTime, brush, text, fontSize, fontName);
    }

    //Event controler
    private class PaintOptionListener implements ListSelectionListener {

        @Override
        public void valueChanged(ListSelectionEvent e) {
            paint.option = getOption(jlPaintOptions.getSelectedIndex());
        }
    }
    
    private class StrokeListener implements ChangeListener{

        @Override
        public void stateChanged(ChangeEvent e) {
            JSlider sourse = (JSlider)e.getSource();
            paint.strokeSize = sourse.getValue();
        }
        
    }
}

