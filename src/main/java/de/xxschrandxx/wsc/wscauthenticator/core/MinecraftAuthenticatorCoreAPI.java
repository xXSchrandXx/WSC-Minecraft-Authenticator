package de.xxschrandxx.wsc.wscauthenticator.core;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;

import javax.net.ssl.HttpsURLConnection;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

public class MinecraftAuthenticatorCoreAPI {

    private final URL url;
    private final String key;
    private final Boolean sessionEnabled;
    private final Long sessionLength;
    protected final Logger logger;
    private final Gson gson = new Gson();
 
    public MinecraftAuthenticatorCoreAPI(String url, String key, Boolean sessionEnabled, Long sessionLength, Logger logger) throws MalformedURLException {
        this.url = new URL(url);
        this.key = key;
        this.sessionEnabled = sessionEnabled;
        this.sessionLength = sessionLength;
        this.logger = logger;
        if (!this.url.getProtocol().equals("https")) {
            throw new MalformedURLException("Only https is supportet. Given protocol: \"" + this.url.getProtocol() + "\"");
        }
    }

    public boolean checkPassword(UUID uuid, String password) throws SocketTimeoutException, IOException, Exception {
        URLConnection c = this.url.openConnection();
        if (!(c instanceof HttpsURLConnection)) {
            throw new IOException("opened connection is not an HttpsURLConnection.");
        }
        HttpsURLConnection connection = (HttpsURLConnection) c;

        String boundary = UUID.randomUUID().toString();

        String postData =
        "--" + boundary + "\r\n" +
        "Content-Disposition: form-data; name=\"key\"" + "\r\n" +
        "Content-Type: text/plain; charset=UTF-8" + "\r\n" +
        "\r\n" +
        this.key + "\r\n" +
        "--" + boundary + "\r\n" +
        "Content-Disposition: form-data; name=\"uuid\"" + "\r\n" +
        "Content-Type: text/plain; charset=UTF-8" + "\r\n" +
        "\r\n" +
        uuid.toString() + "\r\n" +
        "--" + boundary + "\r\n" +
        "Content-Disposition: form-data; name=\"password\"" + "\r\n" +
        "Content-Type: text/plain; charset=UTF-8" + "\r\n" +
        "\r\n" +
        password + "\r\n" +
        "--" + boundary + "--" + "\r\n"
        ;

        byte[] postDataByes = postData.getBytes();
        int postDataLength = postDataByes.length;
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);
        connection.setRequestProperty("charset", "utf-8");
        connection.setRequestProperty("Content-Length", Integer.toString(postDataLength));
        connection.setDoOutput(true);
        connection.setDoInput(true);
        connection.getOutputStream().write(postDataByes);
        connection.getOutputStream().flush();
        connection.getOutputStream().close();

        connection.connect();

        if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
            throw new IOException("Response code: " + connection.getResponseCode());
        }

        byte[] responseBytes = connection.getInputStream().readAllBytes();
        String responseString = new String(responseBytes, StandardCharsets.UTF_8);

        HashMap<String, Object> response = this.gson.fromJson(responseString, new TypeToken<HashMap<String, Object>>(){}.getType());

        if (!response.containsKey("valid")) {
            throw new IOException("Response does not contain key \"valid\".");
        }
        Object valid = response.get("valid");
        if (valid instanceof Boolean) {
            return (Boolean) valid;
        }
        else {
            throw new IOException("Response value from key \"valid\" is not a boolean.");
        }
    }

    // start session part
    protected ConcurrentHashMap<UUID, SessionData> sessions = new ConcurrentHashMap<UUID, SessionData>();

    public Boolean hasOpenSession(UUID uuid, String address) {
        if (!this.sessionEnabled) {
            return false;
        
        }
        SessionData data = this.sessions.get(uuid);
        if (data == null) {
            return false;
        }
        if (!address.equals(data.getAddress())) {
            return false;
        }
        if (data.getEnd().after(new Date())) {
            return true;
        }
        else {
            return false;
        }
    }

    public SessionData removeSession(UUID uuid) {
        return this.sessions.remove(uuid);
    }

    public SessionData addSession(UUID uuid, String address) {
        return this.sessions.put(uuid, new SessionData(address, this.sessionLength));
    }
    // end session part

    // start of authentication part
    protected ConcurrentHashMap<UUID, Boolean> authenticated = new ConcurrentHashMap<UUID, Boolean>();

    public Boolean isAuthenticated(UUID uuid) {
        return this.authenticated.getOrDefault(uuid, false);
    }

    public Boolean setAuthenticated(UUID uuid, Boolean authenticated) {
        return this.authenticated.put(uuid, authenticated);
    }
    // end of authentication part
}