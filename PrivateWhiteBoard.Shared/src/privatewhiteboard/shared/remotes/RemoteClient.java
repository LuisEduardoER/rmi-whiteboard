/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package privatewhiteboard.shared.remotes;
import privatewhiteboard.shared.controllers.IClientController;
import privatewhiteboard.shared.models.Brush;
import privatewhiteboard.shared.models.Point;
import java.rmi.*;
import java.rmi.server.*;
import java.util.Date;
/**
 *
 * @author Otamegane
 */
public class RemoteClient extends UnicastRemoteObject implements IRemoteClient
{
    IClientController _controller;
    
    public RemoteClient(IClientController controller) throws RemoteException
    {
        _controller=controller;
    }

    @Override
    public void PostMessage(int messageId, String senderName, Date sentTime, String messageContent) throws RemoteException
    {
        _controller.PostMessage(messageId, senderName, sentTime, messageContent);
    }

    @Override
    public void PostEmotion(int messageId, String senderName, Date sentTime, String emotionId) throws RemoteException
    {
        _controller.PostEmotion(messageId, senderName, sentTime, emotionId);
    }

    @Override
    public void PostFreeDraw(int drawId, String senderName, Date sentTime, Brush brush, Point[] sequenceOfPoints) throws RemoteException
    {
        _controller.PostFreeDraw(drawId, senderName, sentTime, brush, sequenceOfPoints);
    }

    @Override
    public void PostLineDraw(int drawId, String senderName, Date sentTime, Brush brush, Point startPoint, Point endPoint) throws RemoteException
    {
        _controller.PostLineDraw(drawId, senderName, sentTime, brush, startPoint, endPoint);
    }

    @Override
    public void PostRectangleDraw(int drawId, String senderName, Date sentTime, Brush brush, Point center, double width, double height, boolean isFilled) throws RemoteException
    {
        _controller.PostRectangleDraw(drawId, senderName, sentTime, brush, center, width, height, isFilled);
    }

    @Override
    public void PostEllipseDraw(int drawId, String senderName, Date sentTime, Brush brush, Point center, double r, boolean isFilled) throws RemoteException
    {
        _controller.PostEllipseDraw(drawId, senderName, sentTime, brush, center, r, isFilled);
    }

    @Override
    public void PostPolygonDraw(int drawId, String senderName, Date sentTime, Brush brush, Point[] sequenceOfPoints, boolean isFilled) throws RemoteException
    {
        _controller.PostPolygonDraw(drawId, senderName, sentTime, brush, sequenceOfPoints, isFilled);
    }

    @Override
    public void PostTextDraw(int drawId, String senderName, Date sentTime, Brush brush, String text, double fontSize, double fontName) throws RemoteException
    {
        _controller.PostTextDraw(drawId, senderName, sentTime, brush, text, fontSize, fontName);
    }
    
}
