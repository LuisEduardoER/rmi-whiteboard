/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package privatewhiteboard.server.controllers;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.rmi.RemoteException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import privatewhiteboard.server.model.RemoteClientData;
import privatewhiteboard.shared.controllers.IServerController;
import privatewhiteboard.shared.exceptions.NotConnectedException;
import privatewhiteboard.shared.exceptions.WrongPasswordException;
import privatewhiteboard.shared.models.Brush;
import privatewhiteboard.shared.models.Point;
import privatewhiteboard.shared.remotes.IRemoteClient;

/**
 *
 * @author Otamegane
 */
public class ServerController implements IServerController
{
    List<RemoteClientData> _remoteClientDataList;
    int _nextMessageId;
    int _nextDrawId;
    
    String _password;
    
    public ServerController(String password)
    {
        _remoteClientDataList=new ArrayList<>();
        _nextMessageId=1;
        _nextDrawId=1;
        _password=password;
    }
    
    @Override
    public String Connect(IRemoteClient remoteClient, String nickName, String password) throws RemoteException
    {
        try
        {
            if(!_password.equals(password))
                throw new WrongPasswordException();
            
            //Get MD5 hash:
            MessageDigest messageMD5=MessageDigest.getInstance("MD5");
            String textToGetHash=nickName + (new Date()).toString();
            byte[] bytesToGetHash=textToGetHash.getBytes("UTF-8");
            byte[] hash=messageMD5.digest(bytesToGetHash);
            String hashString = (new BigInteger(1, hash)).toString(16);
            
            //Notify clients:
            for(int i=0;i<_remoteClientDataList.size();i++)
            {
                _remoteClientDataList.get(i).GetRemoteClient().PostMessage(_nextMessageId++, "Server", new Date(), nickName + " has just connected!");
            }
            
            //Add to client list:
            _remoteClientDataList.add(new RemoteClientData(hashString, nickName, remoteClient));
            
            return hashString;
        }
        catch (NoSuchAlgorithmException ex)
        {
            Logger.getLogger(ServerController.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (UnsupportedEncodingException ex)
        {
            Logger.getLogger(ServerController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public void Disconnect(String idHash) throws RemoteException
    {
        //Find and remove:
        RemoteClientData remoteClientData=null;
        for(int i=0;i<_remoteClientDataList.size();i++)
        {
            if(_remoteClientDataList.get(i).GetIdHash().equals(idHash))
            {
                remoteClientData=_remoteClientDataList.get(i);
            }
        }
        if(remoteClientData==null)
            throw new NotConnectedException();
        _remoteClientDataList.remove(remoteClientData);
        
        //Notify disconnected client:
        remoteClientData.GetRemoteClient().PostMessage(_nextMessageId++, "Server", new Date(), "You are disconnected from server!");
        
        //Notify clients:
        for(int i=0;i<_remoteClientDataList.size();i++)
        {
            _remoteClientDataList.get(i).GetRemoteClient().PostMessage(_nextMessageId++, "Server", new Date(), remoteClientData.GetNickName() + " has just disconnected!");
        }
    }
    
    private RemoteClientData GetRemoteClientDataByIdHash(String idHash)
    {
        for(int i=0;i<_remoteClientDataList.size();i++)
        {
            if(_remoteClientDataList.get(i).GetIdHash().equals(idHash))
            {
                return _remoteClientDataList.get(i);
            }
        }
        return null;
    }

    @Override
    public boolean BoardcastMessage(String idHash, String messageContent) throws RemoteException
    {
        Date currentTime=new Date();
        
        //Check authentication:
        RemoteClientData sentClientData = GetRemoteClientDataByIdHash(idHash);
        if(sentClientData==null)
            throw new NotConnectedException();
        
        for(int i=0;i<_remoteClientDataList.size();i++)
        {
            if(!_remoteClientDataList.get(i).GetIdHash().equals(idHash))
            {
                _remoteClientDataList.get(i).GetRemoteClient().PostMessage(_nextMessageId++, sentClientData.GetNickName(), currentTime, messageContent);
            }
        }
        
        System.out.println("Received!");
        
        return true;
    }

    @Override
    public boolean BoardcastEmotion(String idHash, String emotionId) throws RemoteException
    {
        Date currentTime=new Date();
        
        RemoteClientData sentClientData = GetRemoteClientDataByIdHash(idHash);
        if(sentClientData==null)
            throw new NotConnectedException();
        
        for(int i=0;i<_remoteClientDataList.size();i++)
        {
            if(!_remoteClientDataList.get(i).equals(idHash))
            {
                _remoteClientDataList.get(i).GetRemoteClient().PostEmotion(_nextMessageId++, sentClientData.GetNickName(), currentTime, emotionId);
            }
        }
        
        System.out.println("Received!");
        
        return true;
    }

    @Override
    public boolean BroadcastFreeDraw(String idHash, Brush brush, Point[] sequenceOfPoints) throws RemoteException
    {
        Date currentTime=new Date();
        
        RemoteClientData sentClientData=GetRemoteClientDataByIdHash(idHash);
        if(sentClientData==null)
            throw new NotConnectedException();
        
        for(int i=0;i<_remoteClientDataList.size();i++)
        {
            if(!_remoteClientDataList.get(i).equals(idHash))
            {
                _remoteClientDataList.get(i).GetRemoteClient().PostFreeDraw(_nextDrawId++, sentClientData.GetNickName(), currentTime, brush, sequenceOfPoints);
            }
        }
        
        System.out.println("Received!");
        
        return true;
    }

    @Override
    public boolean BroadcastLineDraw(String idHash, Brush brush, Point startPoint, Point endPoint) throws RemoteException
    {
        Date currentTime=new Date();
        
        RemoteClientData sentClientData=GetRemoteClientDataByIdHash(idHash);
        if(sentClientData==null)
            throw new NotConnectedException();
        
        for(int i=0;i<_remoteClientDataList.size();i++)
        {
            if(!_remoteClientDataList.get(i).equals(idHash))
            {
                _remoteClientDataList.get(i).GetRemoteClient().PostLineDraw(_nextDrawId++, sentClientData.GetNickName(), currentTime, brush, startPoint, endPoint);
            }
        }
        
        System.out.println("Received!");
        
        return true;
    }

    @Override
    public boolean BroadcastRectangleDraw(String idHash, Brush brush, Point center, double width, double height, boolean isFilled) throws RemoteException
    {
        Date currentTime=new Date();
        
        RemoteClientData sentClientData=GetRemoteClientDataByIdHash(idHash);
        if(sentClientData==null)
            throw new NotConnectedException();
        
        for(int i=0;i<_remoteClientDataList.size();i++)
        {
            if(!_remoteClientDataList.get(i).equals(idHash))
            {
                _remoteClientDataList.get(i).GetRemoteClient().PostRectangleDraw(_nextDrawId++, sentClientData.GetNickName(), currentTime, brush, center, width, height, isFilled);
            }
        }
        
        System.out.println("Received!");
        
        return true;
    }

    @Override
    public boolean BroadcastEllipseDraw(String idHash, Brush brush, Point center, double r, boolean isFilled) throws RemoteException
    {
        Date currentTime=new Date();
        
        RemoteClientData sentClientData=GetRemoteClientDataByIdHash(idHash);
        if(sentClientData==null)
            throw new NotConnectedException();
        
        for(int i=0;i<_remoteClientDataList.size();i++)
        {
            if(!_remoteClientDataList.get(i).equals(idHash))
            {
                _remoteClientDataList.get(i).GetRemoteClient().PostEllipseDraw(_nextDrawId++, sentClientData.GetNickName(), currentTime, brush, center, r, isFilled);
            }
        }
        
        System.out.println("Received!");
        
        return true;
    }

    @Override
    public boolean BroadcastPolygonDraw(String idHash, Brush brush, Point[] sequenceOfPoints, boolean isFilled) throws RemoteException
    {
        Date currentTime=new Date();
        
        RemoteClientData sentClientData=GetRemoteClientDataByIdHash(idHash);
        if(sentClientData==null)
            throw new NotConnectedException();
        
        for(int i=0;i<_remoteClientDataList.size();i++)
        {
            if(!_remoteClientDataList.get(i).equals(idHash))
            {
                _remoteClientDataList.get(i).GetRemoteClient().PostPolygonDraw(_nextDrawId++, sentClientData.GetNickName(), currentTime, brush, sequenceOfPoints, isFilled);
            }
        }
        
        System.out.println("Received!");
        
        return true;
    }

    @Override
    public boolean BroadcastTextDraw(String idHash, Brush brush, String text, double fontSize, double fontName) throws RemoteException
    {
        Date currentTime=new Date();
        
        RemoteClientData sentClientData=GetRemoteClientDataByIdHash(idHash);
        if(sentClientData==null)
            throw new NotConnectedException();
        
        for(int i=0;i<_remoteClientDataList.size();i++)
        {
            if(!_remoteClientDataList.get(i).equals(idHash))
            {
                _remoteClientDataList.get(i).GetRemoteClient().PostTextDraw(_nextDrawId++, sentClientData.GetNickName(), currentTime, brush, text, fontSize, fontName);
            }
        }
        
        System.out.println("Received!");
        
        return true;
    }
    
}
