package de.xxschrandxx.wsc.wscauthenticator.bukkit.listener;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import de.xxschrandxx.wsc.wscauthenticator.bukkit.MinecraftAuthenticatorBukkit;
import de.xxschrandxx.wsc.wscauthenticator.core.MinecraftAuthenticatorVars.Configuration;
import de.xxschrandxx.wsc.wscbridge.bukkit.api.command.SenderBukkit;

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
        SenderBukkit sender = new SenderBukkit(event.getPlayer(), mab);
        if (!this.mab.getAPI().hasOpenSession(sender, event.getPlayer().getAddress().getAddress().getHostAddress())) {
            return;
        }
        this.mab.getAPI().setAuthenticated(sender, true);
        this.mab.getAPI().removeSession(sender);
        event.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&',
            this.mab.getConfiguration().getString(Configuration.LoginViaSession)
        ));
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        SenderBukkit sender = new SenderBukkit(event.getPlayer(), mab);
        if (!this.mab.getAPI().isAuthenticated(sender)) {
            return;
        }
        this.mab.getAPI().setAuthenticated(sender, false);
        if (!this.mab.getConfiguration().getBoolean(Configuration.SessionsEnabled)) {
            return;
        }
        this.mab.getAPI().addSession(sender, event.getPlayer().getAddress().getAddress().getHostAddress());
    }
}
