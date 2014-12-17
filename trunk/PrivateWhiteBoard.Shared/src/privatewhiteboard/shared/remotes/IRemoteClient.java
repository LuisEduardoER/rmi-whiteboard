package privatewhiteboard.shared.remotes;

import privatewhiteboard.shared.models.Brush;
import privatewhiteboard.shared.models.Point;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Otamegane
 */
public interface IRemoteClient extends Remote
{
    void PostMessage(int messageId, String senderName, Date sentTime, String messageContent) throws RemoteException;
    void PostEmotion(int messageId, String senderName, Date sentTime, String emotionId) throws RemoteException;
    
    void PostFreeDraw(int drawId, String senderName, Date sentTime, Brush brush, Point[] sequenceOfPoints) throws RemoteException;
    void PostLineDraw(int drawId, String senderName, Date sentTime, Brush brush, Point startPoint, Point endPoint) throws RemoteException;
    void PostRectangleDraw(int drawId, String senderName, Date sentTime, Brush brush, Point center, double width, double height, boolean isFilled) throws RemoteException;
    void PostEllipseDraw(int drawId, String senderName, Date sentTime, Brush brush, Point center, double r, boolean isFilled) throws RemoteException;
    void PostPolygonDraw(int drawId, String senderName, Date sentTime, Brush brush, Point[] sequenceOfPoints, boolean isFilled) throws RemoteException;
    void PostTextDraw(int drawId, String senderName, Date sentTime, Brush brush, String text, double fontSize, double fontName) throws RemoteException;
}
