package de.xxschrandxx.wsc.wscauthenticator.bungee.listeners;

import de.xxschrandxx.wsc.wscauthenticator.bungee.MinecraftAuthenticatorBungee;
import de.xxschrandxx.wsc.wscauthenticator.core.MinecraftAuthenticatorVars.Configuration;
import de.xxschrandxx.wsc.wscbridge.bungee.api.command.SenderBungee;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.event.PlayerDisconnectEvent;
import net.md_5.bungee.api.event.ServerConnectEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class MABListener implements Listener {
    private final MinecraftAuthenticatorBungee mab;
    public MABListener() {
        this.mab = MinecraftAuthenticatorBungee.getInstance();
    }
    @EventHandler
    public void onJoin(ServerConnectEvent event) {
        if (!this.mab.getConfiguration().getBoolean(Configuration.SessionsEnabled)) {
            return;
        }
        SenderBungee sender = new SenderBungee(event.getPlayer(), mab);
        if (!this.mab.getAPI().hasOpenSession(sender, event.getPlayer().getAddress().getAddress().getHostAddress())) {
            return;
        }
        this.mab.getAPI().setAuthenticated(sender, true);
        this.mab.getAPI().removeSession(sender);
        event.getPlayer().sendMessage(new TextComponent(ChatColor.translateAlternateColorCodes('&',
            this.mab.getConfiguration().getString(Configuration.LoginViaSession)
        )));
    }

    @EventHandler
    public void onDisconnect(PlayerDisconnectEvent event) {
        SenderBungee sender = new SenderBungee(event.getPlayer(), mab);
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
