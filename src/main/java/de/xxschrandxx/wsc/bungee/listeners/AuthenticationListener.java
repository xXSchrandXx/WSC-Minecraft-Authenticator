package de.xxschrandxx.wsc.bungee.listeners;

import java.util.logging.Level;

import de.xxschrandxx.wsc.bungee.MinecraftAuthenticatorBungee;
import de.xxschrandxx.wsc.bungee.api.events.LoginEvent;
import de.xxschrandxx.wsc.bungee.api.events.LogoutEvent;
import de.xxschrandxx.wsc.core.MinecraftAuthenticatorVars.Configuration;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class AuthenticationListener implements Listener {
    private final MinecraftAuthenticatorBungee mab;
    public AuthenticationListener() {
        this.mab = MinecraftAuthenticatorBungee.getInstance();
    }
    @EventHandler
    public void onLogin(LoginEvent event) {
        if (!this.mab.getConfiguration().getBoolean(Configuration.LobbyServerEnabled)) {
            return;
        }
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
            this.mab.getLogger().log(Level.WARNING, toName);
            return;
        }

    }
}
