package de.xxschrandxx.wsc.wscauthenticator.core.api;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.UnknownServiceException;

import de.xxschrandxx.wsc.wscbridge.core.api.IMinecraftBridgeCoreAPI;
import de.xxschrandxx.wsc.wscbridge.core.api.Response;
import de.xxschrandxx.wsc.wscbridge.core.api.command.ISender;

public interface IMinecraftAuthenticatorCoreAPI extends IMinecraftBridgeCoreAPI {

    public Response<String, Object> checkPassword(ISender<?> sender, String password) throws SocketTimeoutException, MalformedURLException, UnknownServiceException, IOException;

    // start session part
    public void callLoginEvent(ISender<?> sender);

    public void callLogoutEvent(ISender<?> sender);

    public Boolean hasOpenSession(ISender<?> sender, String address);

    public SessionData removeSession(ISender<?> sender);

    public SessionData addSession(ISender<?> palyer, String address);
    // end session part

    // start of authentication part
    public Boolean isAuthenticated(ISender<?> sender);

    public Boolean setAuthenticated(ISender<?> sender, Boolean authenticated);
    // end of authentication part
}
