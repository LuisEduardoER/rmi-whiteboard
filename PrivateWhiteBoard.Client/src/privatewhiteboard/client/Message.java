/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package privatewhiteboard.client;

import java.util.Date;

/**
 *
 * @author Sherry
 */
public class Message 
{
    String _senderName;
    Date _sentTime;
    String _htmlContent;
    
    public Message(String senderName, Date sentTime, String htmlContent)
    {
        _senderName = senderName;
        _sentTime = sentTime;
        _htmlContent = htmlContent;
    }
    
    public String GetSenderName()
    {
        return _senderName;
    }
    
    public Date GetSentTime()
    {
        return _sentTime;
    }
    
    public String GetHtmlContent()
    {
        return _htmlContent;
    }
}
