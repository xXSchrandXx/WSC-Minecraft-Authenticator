package de.xxschrandxx.wsc.core;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.UUID;

import javax.net.ssl.HttpsURLConnection;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;

public class MinecraftAuthenticatorCoreAPI {

    private final URL url;
    private final Gson gson = new Gson();
 
    public MinecraftAuthenticatorCoreAPI(String url) throws MalformedURLException {
        this.url = new URL(url);
        if (!this.url.getProtocol().equals("https")) {
            throw new MalformedURLException("Only https is supportet. Given protocol: \"" + this.url.getProtocol() + "\"");
        }
    }

    public boolean checkPassword(UUID uuid, String password) throws SocketTimeoutException, IOException, Exception {
        URLConnection c = this.url.openConnection();
        if (!(c instanceof HttpsURLConnection)) {
            throw new Exception("opened connection is not an HttpsURLConnection.");
        }
        HttpsURLConnection connection = (HttpsURLConnection) c;

        String boundary = UUID.randomUUID().toString();

        String postData =
        "--" + boundary + "\r\n" +
        "Content-Disposition: form-data; name=\"key\"" + "\r\n" +
        "Content-Type: text/plain; charset=UTF-8" + "\r\n" +
        "\r\n" +
        "MySuperSecretKey" + "\r\n" +
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
            throw new Exception("Response code is not OK.");
        }

        byte[] responseBytes = connection.getInputStream().readAllBytes();
        String responseString = new String(responseBytes, StandardCharsets.UTF_8);

        HashMap<String, Object> response = this.gson.fromJson(responseString, new TypeToken<HashMap<String, Object>>(){}.getType());

        if (!response.containsKey("valid")) {
            throw new Exception("Response does not contain key \"valid\".");
        }
        Object valid = response.get("valid");
        if (valid instanceof Boolean) {
            return (Boolean) valid;
        }
        else {
            throw new Exception("Response value from key \"valid\" is not a boolean.");
        }
    }
}