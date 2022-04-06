package de.xxschrandxx.wsc.bungee.listeners;

import de.xxschrandxx.wsc.bungee.MinecraftAuthenticatorBungee;
import net.md_5.bungee.api.event.PlayerDisconnectEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class MABListener implements Listener {
    private final MinecraftAuthenticatorBungee mab;
    public MABListener() {
        this.mab = MinecraftAuthenticatorBungee.getInstance();
    }
    @EventHandler
    public void onDisconnect(PlayerDisconnectEvent event) {
        if (!this.mab.isAuthenticated(event.getPlayer())) {
            return;
        }
        this.mab.setAuthenticated(event.getPlayer().getUniqueId(), false);
    }
}
