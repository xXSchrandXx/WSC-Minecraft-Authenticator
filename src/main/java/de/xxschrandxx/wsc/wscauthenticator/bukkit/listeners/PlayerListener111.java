package de.xxschrandxx.wsc.wscauthenticator.bukkit.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityAirChangeEvent;

import de.xxschrandxx.wsc.wscauthenticator.bukkit.MinecraftAuthenticatorBukkit;

/**
 * Listener for 1.11 and upper
 */
public class PlayerListener111 implements Listener {
    private final MinecraftAuthenticatorBukkit mab;
    public PlayerListener111() {
        this.mab = MinecraftAuthenticatorBukkit.getInstance();
    }
    @EventHandler(ignoreCancelled = true, priority = EventPriority.LOWEST)
    public void onPlayerAirChange(EntityAirChangeEvent event) {
        if (!(event.getEntity() instanceof Player)) {
            return;
        }
        Player player = (Player) event.getEntity();
        if (this.mab.getAPI().isAuthenticated(player)) {
            return;
        }
        event.setCancelled(true);
    }
}
