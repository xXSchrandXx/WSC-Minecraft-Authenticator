package de.xxschrandxx.wsc.wscauthenticator.core.api;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.UnknownServiceException;
import java.util.HashMap;
import java.util.UUID;

import de.xxschrandxx.wsc.wscbridge.core.api.Response;

public class MinecraftAuthenticatorCoreAPI {
    public static Response<String, Object> checkPassword(IMinecraftAuthenticatorCoreAPI api, URL url, UUID uuid, String password) throws MalformedURLException, UnknownServiceException, SocketTimeoutException, IOException {
        HashMap<String, Object> postData = new HashMap<String, Object>();
        postData.put("uuid", uuid.toString());
        postData.put("password", password);
        Response<String, Object> request = api.requestObject(url, postData);
        return request;
    }
}