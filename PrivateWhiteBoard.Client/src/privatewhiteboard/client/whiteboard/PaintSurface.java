/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package privatewhiteboard.client.whiteboard;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import javax.swing.JComponent;

/**
 *
 * @author Bui Thi Mai
 */
public class PaintSurface extends JComponent {

    ArrayList<Shape> shapes = new ArrayList<Shape>();

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

        this.setSize(1000, 1000);
        this.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                startDrag = new Point(e.getX(), e.getY());
                endDrag = startDrag;
                if (shapeType == ShapeTypes.NONE) {
                    Shape r = makeShape(startDrag.x, startDrag.y, e.getX(), e.getY());
                    shapes.add(r);
                }
                repaint();
            }

            public void mouseReleased(MouseEvent e) {
                Shape r = makeShape(startDrag.x, startDrag.y, e.getX(), e.getY());
                shapes.add(r);
                startDrag = null;
                endDrag = null;
                repaint();
            }
        });

        this.addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {
                endDrag = new Point(e.getX(), e.getY());
                if (shapeType == ShapeTypes.NONE) {
                    startDrag = endDrag;
                    Shape r = makeShape(startDrag.x, startDrag.y, e.getX(), e.getY());
                    shapes.add(r);
                }
                switch (option) {
                    case DRAW:
                        repaint();
                        break;
                    case HAND:
                        setLocation(endDrag);
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
        this.setLocation(newPoint);
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        paintBackground(g2);

        g2.setStroke(new BasicStroke(strokeSize));
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.50f));

        for (Shape s : shapes) {
            g2.setPaint(paintColor);
            g2.draw(s);
            g2.setPaint(paintingPanel.getColorChooser().getCurrentColor());
            g2.fill(s);
        }
//        g2.setPaint(paintColor);
//        g2.draw(shapes.get(shapes.size() - 1));
//        g2.setPaint(paintingPanel.getColorChooser().getCurrentColor());
//        g2.fill(shapes.get(shapes.size() - 1));

        if (startDrag != null && endDrag != null) {
            g2.setPaint(Color.LIGHT_GRAY);
            Shape r = makeShape(startDrag.x, startDrag.y, endDrag.x, endDrag.y);
            g2.setStroke(new BasicStroke(2));
            g2.draw(r);
        }
    }

    private Shape makeShape(int x1, int y1, int x2, int y2) {
        switch (shapeType) {
            case ELLIPE:
                return makeEllipse(x1, y1, x2, y2);
            case LINE:
                return makeLine(x1, y1, x2, y2);
            case RECTANGLE:
                return makeRectangle(x1, y1, x2, y2);
            case NONE:
                return makeFreeLine(x1, y1, x2, y2);
            case TEXT:
                return makeTextArea(x1, y1, x2, y2);
            default:
                return makeRectangle(x1, y1, x2, y2);

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
}

