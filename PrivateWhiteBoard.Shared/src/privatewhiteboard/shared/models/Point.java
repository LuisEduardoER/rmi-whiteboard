package privatewhiteboard.shared.models;


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
public class Point implements Serializable
{
    double _x;
    double _y;
    
    public Point(double x, double y)
    {
        _x=x;
        _y=y;
    }
    
    public double GetX()
    {
        return _x;
    }
    
    public double GetY()
    {
        return _y;
    }
}
