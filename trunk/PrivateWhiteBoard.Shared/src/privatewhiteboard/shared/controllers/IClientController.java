/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package privatewhiteboard.shared.controllers;

import privatewhiteboard.shared.models.Brush;
import privatewhiteboard.shared.models.Point;
import java.rmi.RemoteException;
import java.util.Date;
import privatewhiteboard.shared.remotes.IRemoteClient;

/**
 *
 * @author Otamegane
 */
public interface IClientController
{
    boolean Connect(IRemoteClient remoteClient, String nickName, String password);
    
    void PostMessage(int messageId, String senderName, Date sentTime, String messageContent) throws RemoteException;
    void PostEmotion(int messageId, String senderName, Date sentTime, String emotionId) throws RemoteException;
    
    void PostFreeDraw(int drawId, String senderName, Date sentTime, Brush brush, Point[] sequenceOfPoints) throws RemoteException;
    void PostLineDraw(int drawId, String senderName, Date sentTime, Brush brush, Point startPoint, Point endPoint) throws RemoteException;
    void PostRectangleDraw(int drawId, String senderName, Date sentTime, Brush brush, Point center, double width, double height, boolean isFilled) throws RemoteException;
    void PostEllipseDraw(int drawId, String senderName, Date sentTime, Brush brush, Point center, double r, boolean isFilled) throws RemoteException;
    void PostPolygonDraw(int drawId, String senderName, Date sentTime, Brush brush, Point[] sequenceOfPoints, boolean isFilled) throws RemoteException;
    void PostTextDraw(int drawId, String senderName, Date sentTime, Brush brush, String text, double fontSize, double fontName) throws RemoteException;
    
    boolean BoardcastMessage(String messageContent) throws RemoteException;
    boolean BoardcastEmotion(String emotionId) throws RemoteException;
    
    boolean BroadcastFreeDraw(Brush brush, Point[] sequenceOfPoints) throws RemoteException;
    boolean BroadcastLineDraw(Brush brush, Point startPoint, Point endPoint) throws RemoteException;
    boolean BroadcastRectangleDraw(Brush brush, Point center, double width, double height, boolean isFilled) throws RemoteException;
    boolean BroadcastEllipseDraw(Brush brush, Point center, double r, boolean isFilled) throws RemoteException;
    boolean BroadcastPolygonDraw(Brush brush, Point[] sequenceOfPoints, boolean isFilled) throws RemoteException;
    boolean BroadcastTextDraw(Brush brush, String text, double fontSize, double fontName) throws RemoteException;
}
