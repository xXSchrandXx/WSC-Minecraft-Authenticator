package de.xxschrandxx.wsc.bungee.api;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.logging.Logger;

import de.xxschrandxx.wsc.core.MinecraftAuthenticatorCoreAPI;
import de.xxschrandxx.wsc.core.SessionData;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class MinecraftAuthenticatorBungeeAPI extends MinecraftAuthenticatorCoreAPI {
    public MinecraftAuthenticatorBungeeAPI(String url, String key, Boolean sessionEnabled, Long sessionLength, Logger logger) throws MalformedURLException {
        super(url, key, sessionEnabled, sessionLength, logger);
    }

    public boolean checkPassword(ProxiedPlayer player, String password) throws IOException, Exception {
        return checkPassword(player.getUniqueId(), password);
    }

    // start session part
    public Boolean hasOpenSession(ProxiedPlayer player, String address) {
        return this.hasOpenSession(player.getUniqueId(), address);
    }
    public SessionData removeSession(ProxiedPlayer player) {
        return this.removeSession(player.getUniqueId());
    }
    public SessionData addSession(ProxiedPlayer palyer, String address) {
        return this.addSession(palyer.getUniqueId(), address);
    }
    // end session part

    // start of authentication part
    public Boolean isAuthenticated(ProxiedPlayer player) {
        return this.isAuthenticated(player.getUniqueId());
    }
    public Boolean setAuthenticated(ProxiedPlayer player, Boolean authenticated) {
        return this.setAuthenticated(player.getUniqueId(), authenticated);
    }
    // end of authentication part
}
