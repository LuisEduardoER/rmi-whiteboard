/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package privatewhiteboard.shared.interfaces;

import java.rmi.RemoteException;
import java.util.Date;

/**
 *
 * @author Otamegane
 */
public interface IChatBoard
{
    void PostMessage(int messageId, String senderName, Date sentTime, String messageContent) throws RemoteException;
    void PostEmotion(int messageId, String senderName, Date sentTime, String emotionId) throws RemoteException;
}
