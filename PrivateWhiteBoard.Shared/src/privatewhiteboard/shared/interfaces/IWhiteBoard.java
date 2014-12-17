/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package privatewhiteboard.shared.interfaces;

import privatewhiteboard.shared.models.Brush;
import privatewhiteboard.shared.models.Point;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;

/**
 *
 * @author Otamegane
 */
public interface IWhiteBoard extends Remote
{
    void PostFreeDraw(int drawId, String senderName, Date sentTime, Brush brush, Point[] sequenceOfPoints) throws RemoteException;
    void PostLineDraw(int drawId, String senderName, Date sentTime, Brush brush, Point startPoint, Point endPoint) throws RemoteException;
    void PostRectangleDraw(int drawId, String senderName, Date sentTime, Brush brush, Point center, double width, double height, boolean isFilled) throws RemoteException;
    void PostEllipseDraw(int drawId, String senderName, Date sentTime, Brush brush, Point center, double r, boolean isFilled) throws RemoteException;
    void PostPolygonDraw(int drawId, String senderName, Date sentTime, Brush brush, Point[] sequenceOfPoints, boolean isFilled) throws RemoteException;
    void PostTextDraw(int drawId, String senderName, Date sentTime, Brush brush, String text, double fontSize, double fontName) throws RemoteException;
}
