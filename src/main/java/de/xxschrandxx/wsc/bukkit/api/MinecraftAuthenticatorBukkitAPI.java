package de.xxschrandxx.wsc.bukkit.api;

import java.io.IOException;
import java.net.MalformedURLException;

import org.bukkit.entity.Player;

import de.xxschrandxx.wsc.core.MinecraftAuthenticatorCoreAPI;

public class MinecraftAuthenticatorBukkitAPI extends MinecraftAuthenticatorCoreAPI {
    public MinecraftAuthenticatorBukkitAPI(String url, String key) throws MalformedURLException {
        super(url, key);
    }
    public boolean checkPassword(Player player, String password) throws IOException, Exception {
        return checkPassword(player.getUniqueId(), password);
    }
}
