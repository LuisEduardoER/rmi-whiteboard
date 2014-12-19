/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package privatewhiteboard.server.model;

import privatewhiteboard.shared.remotes.IRemoteClient;

/**
 *
 * @author Otamegane
 */
public class RemoteClientData
{
    String _idHash;
    IRemoteClient _remoteClient;
    String _nickName;
    
    public RemoteClientData(String idHash, String nickName, IRemoteClient remoteClient)
    {
        _idHash=idHash;
        _nickName=nickName;
        _remoteClient=remoteClient;
    }
    
    public String GetIdHash()
    {
        return _idHash;
    }
    
    public IRemoteClient GetRemoteClient()
    {
        return _remoteClient;
    }
    
    public String GetNickName()
    {
        return _nickName;
    }
}
