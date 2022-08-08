package de.xxschrandxx.wsc.wscauthenticator.bukkit.api;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.logging.Logger;

import org.bukkit.entity.Player;

import de.xxschrandxx.wsc.wscauthenticator.core.MinecraftAuthenticatorCoreAPI;
import de.xxschrandxx.wsc.wscauthenticator.core.SessionData;

public class MinecraftAuthenticatorBukkitAPI extends MinecraftAuthenticatorCoreAPI {
    public MinecraftAuthenticatorBukkitAPI(String url, String key, Boolean sessionEnabled, Long sessionLength, Logger logger) throws MalformedURLException {
        super(url, key, sessionEnabled ,sessionLength, logger);
    }

    public boolean checkPassword(Player player, String password) throws IOException, Exception {
        return checkPassword(player.getUniqueId(), password);
    }

    // start session part
    public Boolean hasOpenSession(Player player, String address) {
        return this.hasOpenSession(player.getUniqueId(), address);
    }
    public SessionData removeSession(Player player) {
        return this.removeSession(player.getUniqueId());
    }
    public SessionData addSession(Player player, String address) {
        return this.addSession(player.getUniqueId(), address);
    }
    // end session part

    // start of authentication part
    public Boolean isAuthenticated(Player player) {
        return this.isAuthenticated(player.getUniqueId());
    }
    public Boolean setAuthenticated(Player player, Boolean authenticated) {
        return this.setAuthenticated(player.getUniqueId(), authenticated);
    }
    // end of authentication part
}
