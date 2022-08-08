package de.xxschrandxx.wsc.wscauthenticator.bukkit.listeners;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import de.xxschrandxx.wsc.wscauthenticator.bukkit.MinecraftAuthenticatorBukkit;
import de.xxschrandxx.wsc.wscauthenticator.core.MinecraftAuthenticatorVars.Configuration;

public class MABListener implements Listener {
    private final MinecraftAuthenticatorBukkit mab;
    public MABListener() {
        this.mab = MinecraftAuthenticatorBukkit.getInstance();
    }
    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        if (!this.mab.getConfiguration().getBoolean(Configuration.SessionsEnabled)) {
            return;
        }
        if (!this.mab.getAPI().hasOpenSession(event.getPlayer(), event.getPlayer().getAddress().getAddress().getHostAddress())) {
            return;
        }
        this.mab.getAPI().setAuthenticated(event.getPlayer(), true);
        this.mab.getAPI().removeSession(event.getPlayer());
        event.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&',
            this.mab.getConfiguration().getString(Configuration.LoginViaSession)
        ));
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        if (!this.mab.getAPI().isAuthenticated(event.getPlayer())) {
            return;
        }
        this.mab.getAPI().setAuthenticated(event.getPlayer(), false);
        if (!this.mab.getConfiguration().getBoolean(Configuration.SessionsEnabled)) {
            return;
        }
        this.mab.getAPI().addSession(event.getPlayer(), event.getPlayer().getAddress().getAddress().getHostAddress());
    }
}
