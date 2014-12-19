/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package privatewhiteboard.client.controllers;

import java.rmi.RemoteException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import privatewhiteboard.shared.controllers.IClientController;
import privatewhiteboard.shared.models.Brush;
import privatewhiteboard.shared.models.Point;

import privatewhiteboard.shared.interfaces.*;
import privatewhiteboard.shared.remotes.IRemoteClient;
import privatewhiteboard.shared.remotes.IRemoteServer;
/**
 *
 * @author Otamegane
 */
public class ClientController implements IClientController
{
    IChatBoard _chatBoard;
    IWhiteBoard _whiteBoard;
    IRemoteServer _remoteServer;
    
    String idHash;
    
    public void Initialize(IChatBoard chatBoard, IWhiteBoard whiteBoard, IRemoteServer remoteServer)
    {
        _chatBoard=chatBoard;
        _whiteBoard=whiteBoard;
        _remoteServer=remoteServer;
    }
    
    
    @Override
    public void PostMessage(int messageId, String senderName, Date sentTime, String messageContent) throws RemoteException
    {
        _chatBoard.PostMessage(messageId, senderName, sentTime, messageContent);
    }

    @Override
    public void PostEmotion(int messageId, String senderName, Date sentTime, String emotionId) throws RemoteException
    {
        _chatBoard.PostEmotion(messageId, senderName, sentTime, emotionId);
    }

    @Override
    public void PostFreeDraw(int drawId, String senderName, Date sentTime, Brush brush, Point[] sequenceOfPoints) throws RemoteException
    {
        _whiteBoard.PostFreeDraw(drawId, senderName, sentTime, brush, sequenceOfPoints);
    }

    @Override
    public void PostLineDraw(int drawId, String senderName, Date sentTime, Brush brush, Point startPoint, Point endPoint) throws RemoteException
    {
        _whiteBoard.PostLineDraw(drawId, senderName, sentTime, brush, startPoint, endPoint);
    }

    @Override
    public void PostRectangleDraw(int drawId, String senderName, Date sentTime, Brush brush, Point center, double width, double height, boolean isFilled) throws RemoteException
    {
        _whiteBoard.PostRectangleDraw(drawId, senderName, sentTime, brush, center, width, height, isFilled);
    }

    @Override
    public void PostEllipseDraw(int drawId, String senderName, Date sentTime, Brush brush, Point center, double r, boolean isFilled) throws RemoteException
    {
        _whiteBoard.PostEllipseDraw(drawId, senderName, sentTime, brush, center, r, isFilled);
    }

    @Override
    public void PostPolygonDraw(int drawId, String senderName, Date sentTime, Brush brush, Point[] sequenceOfPoints, boolean isFilled) throws RemoteException
    {
        _whiteBoard.PostPolygonDraw(drawId, senderName, sentTime, brush, sequenceOfPoints, isFilled);
    }

    @Override
    public void PostTextDraw(int drawId, String senderName, Date sentTime, Brush brush, String text, double fontSize, double fontName) throws RemoteException
    {
        _whiteBoard.PostTextDraw(drawId, senderName, sentTime, brush, text, fontSize, fontName);
    }

    @Override
    public boolean BoardcastMessage(String messageContent) throws RemoteException
    {
       return _remoteServer.BroadcastMessage(idHash, messageContent);
    }

    @Override
    public boolean BoardcastEmotion(String emotionId) throws RemoteException
    {
        return _remoteServer.BroadcastEmotion(idHash, emotionId);
    }

    @Override
    public boolean BroadcastFreeDraw(Brush brush, Point[] sequenceOfPoints) throws RemoteException
    {
        return _remoteServer.BroadcastFreeDraw(idHash, brush, sequenceOfPoints);
    }

    @Override
    public boolean BroadcastLineDraw(Brush brush, Point startPoint, Point endPoint) throws RemoteException
    {
        return _remoteServer.BroadcastLineDraw(idHash, brush, startPoint, endPoint);
    }

    @Override
    public boolean BroadcastRectangleDraw(Brush brush, Point center, double width, double height, boolean isFilled) throws RemoteException
    {
        return _remoteServer.BroadcastRectangleDraw(idHash, brush, center, width, height, isFilled);
    }

    @Override
    public boolean BroadcastEllipseDraw(Brush brush, Point center, double r, boolean isFilled) throws RemoteException
    {
        return _remoteServer.BroadcastEllipseDraw(idHash, brush, center, r, isFilled);
    }

    @Override
    public boolean BroadcastPolygonDraw(Brush brush, Point[] sequenceOfPoints, boolean isFilled) throws RemoteException
    {
        return _remoteServer.BroadcastPolygonDraw(idHash, brush, sequenceOfPoints, isFilled);
    }

    @Override
    public boolean BroadcastTextDraw(Brush brush, String text, double fontSize, double fontName) throws RemoteException
    {
        return _remoteServer.BroadcastTextDraw(idHash, brush, text, fontSize, fontName);
    }

    @Override
    public boolean Connect(IRemoteClient remoteClient, String nickName, String password)
    {
        try
        {
            idHash=_remoteServer.Connect(remoteClient, nickName, password);
            return true;
        }
        catch (RemoteException ex)
        {
            Logger.getLogger(ClientController.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
    
}
