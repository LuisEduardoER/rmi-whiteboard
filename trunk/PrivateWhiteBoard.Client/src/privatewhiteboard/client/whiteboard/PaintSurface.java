/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package privatewhiteboard.client.whiteboard;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import static java.lang.Math.abs;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JComponent;
import javax.swing.Scrollable;
import privatewhiteboard.shared.interfaces.IWhiteBoard;
import privatewhiteboard.shared.models.Brush;

/**
 *
 * @author Bui Thi Mai
 */
public class PaintSurface extends JComponent implements IWhiteBoard, Scrollable {

    ArrayList<MyShape> shapes = new ArrayList<MyShape>();

    Point startDrag, endDrag;

    PaintingPanel paintingPanel;
    ShapeTypes shapeType;
    PaintOptions option;
    Color paintColor;
    Color fillColor;
    int strokeSize;

    public PaintSurface(PaintingPanel paintingPanel) {
        shapeType = ShapeTypes.RECTANGLE;
        option = PaintOptions.DRAW;
        paintColor = Color.BLACK;
        fillColor = Color.BLUE;
        strokeSize = 2;
        this.paintingPanel = paintingPanel;

        this.setPreferredSize(new Dimension(3000, 3000));
        this.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                startDrag = new Point(e.getX(), e.getY());
                endDrag = startDrag;
                switch (option) {
                    case DRAW:
                        if (shapeType == ShapeTypes.NONE) {
                            MyShape r = makeShape(startDrag.x, startDrag.y, e.getX(), e.getY());
                            shapes.add(r);
                        }
                        break;
                    case ERASER:
                        
                        break;
                    default:
                        break;
                }

                repaint();
            }

            public void mouseReleased(MouseEvent e) {
                switch (option) {
                    case DRAW:
                        MyShape r = makeShape(startDrag.x, startDrag.y, e.getX(), e.getY());
                        shapes.add(r);
                        break;
                    default:
                        break;
                }

                startDrag = null;
                endDrag = null;
                repaint();
            }
        });

        this.addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {
                endDrag = new Point(e.getX(), e.getY());

                switch (option) {
                    case DRAW:
                        if (shapeType == ShapeTypes.NONE) {
                            startDrag = endDrag;
                            MyShape r = makeShape(startDrag.x, startDrag.y, e.getX(), e.getY());
                            shapes.add(r);
                        }
                        repaint();
                        break;
                    case HAND:
                        break;
                    case ERASER:
                        break;
                    case POINTER:
                        break;
                }
                //repaint();
            }
        });
    }

    public void setShapeType(ShapeTypes type) {
        if (type != this.shapeType) {
            this.shapeType = type;
        }
    }

    public void setOption(PaintOptions option) {
        if (option != this.option) {
            this.option = option;
        }
    }

    private void paintBackground(Graphics2D g2) {
        g2.setPaint(Color.LIGHT_GRAY);
        for (int i = 0; i < getSize().width; i += 10) {
            Shape line = new Line2D.Float(i, 0, i, getSize().height);
            g2.draw(line);
        }

        for (int i = 0; i < getSize().height; i += 10) {
            Shape line = new Line2D.Float(0, i, getSize().width, i);
            g2.draw(line);
        }

    }

    @Override
    public void setLocation(Point newPoint) {
        int x = this.getX() + abs(startDrag.x - newPoint.x);
        int y = this.getY() + abs(startDrag.y - newPoint.y);
        //this.setBounds(, WIDTH, this.WIDTH, this.HEIGHT);
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        paintBackground(g2);

        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.50f));
        switch(option){
            case DRAW:
                break;
            case ERASER:
                shapes.removeAll(shapes);
                break;
            case POINTER:
                break;
        }
        for (MyShape s : shapes) {
            g2.setStroke(new BasicStroke(s.stroke));
            g2.setPaint(paintColor);
            g2.draw(s.shape);
            g2.setPaint(s.color);
            g2.fill(s.shape);
        }
        if (startDrag != null && endDrag != null) {
            g2.setPaint(Color.LIGHT_GRAY);
            Shape r = makeShape(startDrag.x, startDrag.y, endDrag.x, endDrag.y).shape;
            g2.setStroke(new BasicStroke(2));
            g2.draw(r);
        }
    }

    private MyShape makeShape(int x1, int y1, int x2, int y2) {
        switch (shapeType) {
            case ELLIPE:
                return new MyShape(makeEllipse(x1, y1, x2, y2), paintingPanel.getColorChooser().getCurrentColor(), strokeSize);
            case LINE:
                return new MyShape(makeLine(x1, y1, x2, y2), paintingPanel.getColorChooser().getCurrentColor(), strokeSize);
            case RECTANGLE:
                return new MyShape(makeRectangle(x1, y1, x2, y2), paintingPanel.getColorChooser().getCurrentColor(), strokeSize);
            case NONE:
                return new MyShape(makeFreeLine(x1, y1, x2, y2), paintingPanel.getColorChooser().getCurrentColor(), strokeSize);
            case TEXT:
                return new MyShape(makeTextArea(x1, y1, x2, y2), paintingPanel.getColorChooser().getCurrentColor(), strokeSize);
            default:
                return new MyShape(makeRectangle(x1, y1, x2, y2), paintingPanel.getColorChooser().getCurrentColor(), strokeSize);

        }
    }


    private Rectangle2D.Float makeRectangle(int x1, int y1, int x2, int y2) {
        return new Rectangle2D.Float(Math.min(x1, x2), Math.min(y1, y2), Math.abs(x1 - x2), Math.abs(y1 - y2));
    }

    private Ellipse2D.Float makeEllipse(int x1, int y1, int x2, int y2) {
        final float x = Math.min(x1, x2);
        final float y = Math.min(y1, y2);
        final float width = Math.abs(x1 - x2);
        final float height = Math.abs(y1 - y2);
        return new Ellipse2D.Float(x, y, width, height);
    }

    private Line2D.Float makeLine(int x1, int y1, int x2, int y2) {
        return new Line2D.Float(x1, y1, x2, y2);
    }

    private Rectangle2D.Float makeTextArea(int x1, int y1, int x2, int y2) {
        return new Rectangle2D.Float(Math.min(x1, x2), Math.min(y1, y2), Math.abs(x1 - x2), Math.abs(y1 - y2));

    }

    private Line2D.Float makeFreeLine(int x1, int y1, int x2, int y2) {
        return new Line2D.Float(x1, y1, x2, y2);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

    @Override
    public Dimension getPreferredScrollableViewportSize() {
        return new Dimension(640, 480);
    }

    @Override
    public int getScrollableUnitIncrement(Rectangle visibleRect, int orientation, int direction) {
        return 0;
    }

    @Override
    public int getScrollableBlockIncrement(Rectangle visibleRect, int orientation, int direction) {
        return 0;
    }

    @Override
    public boolean getScrollableTracksViewportWidth() {
        return false;
    }

    @Override
    public boolean getScrollableTracksViewportHeight() {
        return false;
    }

    @Override
    public void PostFreeDraw(int drawId, String senderName, Date sentTime, Brush brush, privatewhiteboard.shared.models.Point[] sequenceOfPoints) throws RemoteException {
        privatewhiteboard.shared.models.Point point;
        for(int i = 0; i < sequenceOfPoints.length - 1; i++){
            point = sequenceOfPoints[i];
            shapes.add(new MyShape(makeFreeLine((int)point.GetX(), (int)point.GetY(), (int)point.GetX(), (int)point.GetY()), paintingPanel.getColorChooser().getCurrentColor(), strokeSize));
            repaint();
        }
    }

    @Override
    public void PostLineDraw(int drawId, String senderName, Date sentTime, Brush brush, privatewhiteboard.shared.models.Point startPoint, privatewhiteboard.shared.models.Point endPoint) throws RemoteException {
        Line2D.Float line = makeLine((int)startPoint.GetX(), (int)startPoint.GetY(), (int)endPoint.GetX(), (int)endPoint.GetY());
        shapes.add(new MyShape(line, paintingPanel.getColorChooser().getCurrentColor(), strokeSize));
        repaint();
    }

    @Override
    public void PostRectangleDraw(int drawId, String senderName, Date sentTime, Brush brush, privatewhiteboard.shared.models.Point center, double width, double height, boolean isFilled) throws RemoteException {
        Rectangle2D.Float rectangle = makeRectangle((int)(center.GetX() - width/2), (int)(center.GetY() - height/2), (int)(center.GetX() + width/2), (int)(center.GetY() + height/2));
        shapes.add(new MyShape(rectangle, paintingPanel.getColorChooser().getCurrentColor(), strokeSize));
        repaint();
    }

    @Override
    public void PostEllipseDraw(int drawId, String senderName, Date sentTime, Brush brush, privatewhiteboard.shared.models.Point center, double r, boolean isFilled) throws RemoteException {
        Ellipse2D.Float ellipse = makeEllipse((int)(center.GetX() - r), (int)(center.GetY() - r), (int)(center.GetX() + r), (int)(center.GetY() + r));
        shapes.add(new MyShape(ellipse, paintingPanel.getColorChooser().getCurrentColor(), strokeSize));
        repaint();
    }

    @Override
    public void PostPolygonDraw(int drawId, String senderName, Date sentTime, Brush brush, privatewhiteboard.shared.models.Point[] sequenceOfPoints, boolean isFilled) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void PostTextDraw(int drawId, String senderName, Date sentTime, Brush brush, String text, double fontSize, double fontName) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

