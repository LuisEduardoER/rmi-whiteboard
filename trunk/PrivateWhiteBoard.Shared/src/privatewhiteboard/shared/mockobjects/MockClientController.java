/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package privatewhiteboard.shared.mockobjects;

import java.awt.Color;
import java.rmi.RemoteException;
import java.util.Date;
import privatewhiteboard.shared.controllers.IClientController;
import privatewhiteboard.shared.interfaces.IChatBoard;
import privatewhiteboard.shared.interfaces.IWhiteBoard;
import privatewhiteboard.shared.models.Brush;
import privatewhiteboard.shared.models.Point;

/**
 *
 * @author Otamegane
 */
/*
public class MockClientController implements IClientController
{
    IChatBoard _chatBoard;
    IWhiteBoard _whiteBoard;
    
    public MockClientController(IChatBoard chatBoard, IWhiteBoard whiteBoard)
    {
        _chatBoard=chatBoard;
        _whiteBoard=whiteBoard;
    }
    
    @Override
    public void PostMessage(int messageId, String senderName, Date sentTime, String messageContent) throws RemoteException
    {
        System.out.println("Message has been posted");
        _chatBoard.PostMessage(messageId, senderName, sentTime, messageContent);
    }

    @Override
    public void PostEmotion(int messageId, String senderName, Date sentTime, String emotionId) throws RemoteException
    {
        System.out.println("Message has been posted");
        _chatBoard.PostEmotion(messageId, senderName, sentTime, emotionId);
    }

    @Override
    public void PostFreeDraw(int drawId, String senderName, Date sentTime, Brush brush, Point[] sequenceOfPoints) throws RemoteException
    {
        System.out.println("Draw has been posted");
        _whiteBoard.PostFreeDraw(drawId, senderName, sentTime, brush, sequenceOfPoints);
    }

    @Override
    public void PostLineDraw(int drawId, String senderName, Date sentTime, Brush brush, Point startPoint, Point endPoint) throws RemoteException
    {
        System.out.println("Draw has been posted");
        _whiteBoard.PostLineDraw(drawId, senderName, sentTime, brush, startPoint, endPoint);
    }

    @Override
    public void PostRectangleDraw(int drawId, String senderName, Date sentTime, Brush brush, Point center, double width, double height, boolean isFilled) throws RemoteException
    {
        System.out.println("Draw has been posted");
        _whiteBoard.PostRectangleDraw(drawId, senderName, sentTime, brush, center, width, height, isFilled);
    }

    @Override
    public void PostEllipseDraw(int drawId, String senderName, Date sentTime, Brush brush, Point center, double r, boolean isFilled) throws RemoteException
    {
        System.out.println("Draw has been posted");
        _whiteBoard.PostEllipseDraw(drawId, senderName, sentTime, brush, center, r, isFilled);
    }

    @Override
    public void PostPolygonDraw(int drawId, String senderName, Date sentTime, Brush brush, Point[] sequenceOfPoints, boolean isFilled) throws RemoteException
    {
        System.out.println("Draw has been posted");
        _whiteBoard.PostPolygonDraw(drawId, senderName, sentTime, brush, sequenceOfPoints, isFilled);
    }

    @Override
    public void PostTextDraw(int drawId, String senderName, Date sentTime, Brush brush, String text, double fontSize, double fontName) throws RemoteException
    {
        System.out.println("Draw has been posted");
        _whiteBoard.PostTextDraw(drawId, senderName, sentTime, brush, text, fontSize, fontName);
    }

    @Override
    public boolean BoardcastMessage(String messageContent) throws RemoteException
    {
        System.out.println("Message has been boardcasted");
        return true;
    }

    @Override
    public boolean BoardcastEmotion(String emotionId) throws RemoteException
    {
        System.out.println("Message has been boardcasted");
        return true;
    }

    @Override
    public boolean BroadcastFreeDraw(Brush brush, Point[] sequenceOfPoints) throws RemoteException
    {
        System.out.println("Draw has been boardcasted");
        return true;
    }

    @Override
    public boolean BroadcastLineDraw(Brush brush, Point startPoint, Point endPoint) throws RemoteException
    {
        System.out.println("Draw has been boardcasted");
        return true;
    }

    @Override
    public boolean BroadcastRectangleDraw(Brush brush, Point center, double width, double height, boolean isFilled) throws RemoteException
    {
        System.out.println("Draw has been boardcasted");
        return true;
    }

    @Override
    public boolean BroadcastEllipseDraw(Brush brush, Point center, double r, boolean isFilled) throws RemoteException
    {
        System.out.println("Draw has been boardcasted");
        return true;
    }

    @Override
    public boolean BroadcastPolygonDraw(Brush brush, Point[] sequenceOfPoints, boolean isFilled) throws RemoteException
    {
        System.out.println("Draw has been boardcasted");
        return true;
    }

    @Override
    public boolean BroadcastTextDraw(Brush brush, String text, double fontSize, double fontName) throws RemoteException
    {
        System.out.println("Draw has been boardcasted");
        return true;
    }

    @Override
    public void Connect(String nickName) throws RemoteException
    {
        System.out.println("Connected!");
    }
    
}
*/