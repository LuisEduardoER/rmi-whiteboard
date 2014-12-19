/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package privatewhiteboard.client.mockobjects;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.RemoteException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import privatewhiteboard.client.ClientLoginForm;
import privatewhiteboard.shared.controllers.IClientController;
import privatewhiteboard.shared.interfaces.IChatBoard;

/**
 *
 * @author Otamegane
 */
public class MockChatBoard implements IChatBoard
{
    IClientController _clientController;
    ClientLoginForm _loginForm;
    
    public void Initialize(IClientController clientController, ClientLoginForm loginForm)
    {
        _clientController=clientController;
        _loginForm=loginForm;
    }
    
    @Override
    public void PostMessage(int messageId, String senderName, Date sentTime, String messageContent) throws RemoteException
    {
        System.out.println(senderName+"("+sentTime.toGMTString()+"): "+messageContent);
    }

    @Override
    public void PostEmotion(int messageId, String senderName, Date sentTime, String emotionId) throws RemoteException
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public void Run()
    {
        BufferedReader reader=new BufferedReader(new InputStreamReader(System.in));
        String temp="";
        do
        {
            try
            {
                System.out.println("Msg:");
                temp=reader.readLine();
                if(temp.equals("Exit"))
                    break;
                _clientController.BoardcastMessage(temp);
            }
            catch (IOException ex)
            {
                Logger.getLogger(MockChatBoard.class.getName()).log(Level.SEVERE, null, ex);
            }
        }while(true);
        _loginForm.setVisible(true);
    }
}
