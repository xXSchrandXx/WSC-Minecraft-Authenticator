package de.xxschrandxx.wsc.bukkit.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import de.xxschrandxx.wsc.bukkit.MinecraftAuthenticatorBukkit;

public class MABListener implements Listener {
    private final MinecraftAuthenticatorBukkit mab;
    public MABListener() {
        this.mab = MinecraftAuthenticatorBukkit.getInstance();
    }
    @EventHandler
    public void onDisconnect(PlayerQuitEvent event) {
        if (!this.mab.isAuthenticated(event.getPlayer())) {
            return;
        }
        this.mab.setAuthenticated(event.getPlayer().getUniqueId(), false);
    }
}
