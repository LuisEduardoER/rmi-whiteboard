/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package privatewhiteboard.client.whiteboard;

import java.awt.Color;
import java.awt.Shape;

/**
 *
 * @author Bui Thi Mai
 */
public class MyShape {
    public Shape shape;
    public Color color;
    public int stroke;
    
    public MyShape(Shape shape, Color color, int stroke){
        this.shape = shape;
        this.color = color;
        this.stroke = stroke;
    }
}
