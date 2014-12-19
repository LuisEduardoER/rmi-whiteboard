/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package privatewhiteboard.shared.remotes;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import privatewhiteboard.shared.controllers.IServerController;
import privatewhiteboard.shared.models.Brush;
import privatewhiteboard.shared.models.Point;

/**
 *
 * @author Otamegane
 */
public class RemoteServer extends UnicastRemoteObject implements IRemoteServer
{
    IServerController _controller;
    
    public RemoteServer(IServerController controller) throws RemoteException
    {
        _controller=controller;
    }

    @Override
    public String Connect(IRemoteClient remoteClient, String nickName, String password) throws RemoteException
    {
        return _controller.Connect(remoteClient, nickName, password);
    }

    @Override
    public void Disconnect(String idHash) throws RemoteException
    {
        _controller.Disconnect(idHash);
    }

    @Override
    public boolean BroadcastMessage(String idHash, String messageContent) throws RemoteException
    {
        return _controller.BoardcastMessage(idHash, messageContent);
    }

    @Override
    public boolean BroadcastEmotion(String idHash, String emotionId) throws RemoteException
    {
        return _controller.BoardcastEmotion(idHash, emotionId);
    }

    @Override
    public boolean BroadcastFreeDraw(String idHash, Brush brush, Point[] sequenceOfPoints) throws RemoteException
    {
        return _controller.BroadcastFreeDraw(idHash, brush, sequenceOfPoints);
    }

    @Override
    public boolean BroadcastLineDraw(String idHash, Brush brush, Point startPoint, Point endPoint) throws RemoteException
    {
        return _controller.BroadcastLineDraw(idHash, brush, startPoint, endPoint);
    }

    @Override
    public boolean BroadcastRectangleDraw(String idHash, Brush brush, Point center, double width, double height, boolean isFilled) throws RemoteException
    {
        return _controller.BroadcastRectangleDraw(idHash, brush, center, width, height, isFilled);
    }

    @Override
    public boolean BroadcastEllipseDraw(String idHash, Brush brush, Point center, double r, boolean isFilled) throws RemoteException
    {
        return _controller.BroadcastEllipseDraw(idHash, brush, center, r, isFilled);
    }

    @Override
    public boolean BroadcastPolygonDraw(String idHash, Brush brush, Point[] sequenceOfPoints, boolean isFilled) throws RemoteException
    {
        return _controller.BroadcastPolygonDraw(idHash, brush, sequenceOfPoints, isFilled);
    }

    @Override
    public boolean BroadcastTextDraw(String idHash, Brush brush, String text, double fontSize, double fontName) throws RemoteException
    {
        return _controller.BroadcastTextDraw(idHash, brush, text, fontSize, fontName);
    }
    
}
