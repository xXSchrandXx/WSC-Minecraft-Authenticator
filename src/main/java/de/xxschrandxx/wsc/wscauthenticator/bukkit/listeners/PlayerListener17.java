package de.xxschrandxx.wsc.wscauthenticator.bukkit.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPickupItemEvent;

/**
 * Listener for pre 1.12
 */
import de.xxschrandxx.wsc.wscauthenticator.bukkit.MinecraftAuthenticatorBukkit;

public class PlayerListener17 implements Listener {
    private final MinecraftAuthenticatorBukkit mab;
    public PlayerListener17() {
        this.mab = MinecraftAuthenticatorBukkit.getInstance();
    }
    @EventHandler(ignoreCancelled = true, priority = EventPriority.LOWEST)
    public void onPlayerPickupItem(PlayerPickupItemEvent event) {
        if (this.mab.getAPI().isAuthenticated(event.getPlayer())) {
            return;
        }
        event.setCancelled(true);
    }
}
