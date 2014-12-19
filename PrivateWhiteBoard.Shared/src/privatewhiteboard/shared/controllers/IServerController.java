/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package privatewhiteboard.shared.controllers;

import privatewhiteboard.shared.models.Brush;
import privatewhiteboard.shared.models.Point;
import java.rmi.RemoteException;
import privatewhiteboard.shared.remotes.IRemoteClient;

/**
 *
 * @author Otamegane
 */
public interface IServerController
{
    String Connect(IRemoteClient remoteClient, String nickName, String password) throws RemoteException;
    void Disconnect(String idHash) throws RemoteException;
    
    boolean BoardcastMessage(String idHash, String messageContent) throws RemoteException;
    boolean BoardcastEmotion(String idHash, String emotionId) throws RemoteException;
    
    boolean BroadcastFreeDraw(String idHash, Brush brush, Point[] sequenceOfPoints) throws RemoteException;
    boolean BroadcastLineDraw(String idHash, Brush brush, Point startPoint, Point endPoint) throws RemoteException;
    boolean BroadcastRectangleDraw(String idHash, Brush brush, Point center, double width, double height, boolean isFilled) throws RemoteException;
    boolean BroadcastEllipseDraw(String idHash, Brush brush, Point center, double r, boolean isFilled) throws RemoteException;
    boolean BroadcastPolygonDraw(String idHash, Brush brush, Point[] sequenceOfPoints, boolean isFilled) throws RemoteException;
    boolean BroadcastTextDraw(String idHash, Brush brush, String text, double fontSize, double fontName) throws RemoteException;
}
