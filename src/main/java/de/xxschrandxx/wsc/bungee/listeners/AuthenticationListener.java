package de.xxschrandxx.wsc.bungee.listeners;

import java.util.List;
import java.util.logging.Level;

import de.xxschrandxx.wsc.bungee.MinecraftAuthenticatorBungee;
import de.xxschrandxx.wsc.bungee.api.events.LoginEvent;
import de.xxschrandxx.wsc.bungee.api.events.LogoutEvent;
import de.xxschrandxx.wsc.core.MinecraftAuthenticatorVars.Configuration;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.event.ServerConnectEvent;
import net.md_5.bungee.api.event.ServerConnectEvent.Reason;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class AuthenticationListener implements Listener {
    private final MinecraftAuthenticatorBungee mab;
    public AuthenticationListener() {
        this.mab = MinecraftAuthenticatorBungee.getInstance();
    }

    @EventHandler
    public void onJoin(ServerConnectEvent event) {
        if (event.getReason() != Reason.JOIN_PROXY) {
            return;
        }
        if (!this.mab.getConfiguration().getBoolean(Configuration.AuthenticationServerEnabled)) {
            return;
        }
        String toName = this.mab.getConfiguration().getString(Configuration.AuthenticationServerName);
        if (event.getTarget().getName().equals(toName)) {
            return;
        }
        ServerInfo to = this.mab.getProxy().getServerInfo(toName);
        if (to == null) {
            this.mab.getLogger().log(Level.WARNING, "No serverinfo with \"" + toName + "\" found.");
            return;
        }
        if (!to.canAccess(event.getPlayer())) {
            this.mab.getLogger().log(Level.WARNING, "\"" + event.getPlayer().getName() + "\" cannot access \"" + toName + "\".");
            return;
        }
        event.setTarget(to);
    }

    @EventHandler
    public void onLogout(LogoutEvent event) {
        if (!this.mab.getConfiguration().getBoolean(Configuration.AuthenticationServerEnabled)) {
            return;
        }
        ServerInfo on = event.get().getServer().getInfo();
        String toName = this.mab.getConfiguration().getString(Configuration.AuthenticationServerName);
        if (on.getName().equals(toName)) {
            return;
        }
        ServerInfo to = this.mab.getProxy().getServerInfo(toName);
        if (to == null) {
            this.mab.getLogger().log(Level.WARNING, "No serverinfo with \"" + toName + "\" found.");
            return;
        }
        if (!to.canAccess(event.get())) {
            this.mab.getLogger().log(Level.WARNING, "\"" + event.get().getName() + "\" cannot access \"" + toName + "\".");
            return;
        }
        event.get().connect(to, Reason.PLUGIN);
    }

    @EventHandler
    public void onLogin(LoginEvent event) {
        if (!this.mab.getConfiguration().getBoolean(Configuration.LobbyServerEnabled)) {
            return;
        }
        ServerInfo on = event.get().getServer().getInfo();
        List<String> toNames = this.mab.getConfiguration().getStringList(Configuration.LobbyServerList);
        if (toNames.contains(on.getName())) {
            return;
        }
        for (String toName : toNames) {
            ServerInfo to = this.mab.getProxy().getServerInfo(toName);
            if (to == null) {
                this.mab.getLogger().log(Level.WARNING, "No serverinfo with \"" + toName + "\" found.");
                continue;
            }
            if (!to.canAccess(event.get())) {
                this.mab.getLogger().log(Level.WARNING, "\"" + event.get().getName() + "\" cannot access \"" + toName + "\".");
                continue;
            }
            event.get().connect(to, Reason.PLUGIN);
            break;
        }
    }
}
