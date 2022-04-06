package de.xxschrandxx.wsc.bungee.api;

import java.io.IOException;
import java.net.MalformedURLException;

import de.xxschrandxx.wsc.core.MinecraftAuthenticatorCoreAPI;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class MinecraftAuthenticatorBungeeAPI extends MinecraftAuthenticatorCoreAPI {
    public MinecraftAuthenticatorBungeeAPI(String url, String key) throws MalformedURLException {
        super(url, key);
    }
    public boolean checkPassword(ProxiedPlayer player, String password) throws IOException, Exception {
        return checkPassword(player.getUniqueId(), password);
    }
}
