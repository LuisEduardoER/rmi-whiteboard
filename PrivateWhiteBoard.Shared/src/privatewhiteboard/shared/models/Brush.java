package privatewhiteboard.shared.models;


import java.awt.Color;
import java.io.Serializable;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Otamegane
 */
public class Brush implements Serializable
{
    Color _color;
    double _brushSize;
    
    public Brush(Color color, double brushSize)
    {
        _color=color;
        _brushSize=brushSize;
    }
    
    public Color GetColor()
    {
        return _color;
    }
    
    public double GetBrushSize()
    {
        return _brushSize;
    }
}
