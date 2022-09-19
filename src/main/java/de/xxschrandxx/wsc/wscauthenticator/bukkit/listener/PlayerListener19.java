package de.xxschrandxx.wsc.wscauthenticator.bukkit.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;

import de.xxschrandxx.wsc.wscauthenticator.bukkit.MinecraftAuthenticatorBukkit;
import de.xxschrandxx.wsc.wscbridge.bukkit.api.command.SenderBukkit;

/**
 * Listener for 1.9 and upper
 */
public class PlayerListener19 implements Listener {
    private final MinecraftAuthenticatorBukkit mab;
    public PlayerListener19() {
        this.mab = MinecraftAuthenticatorBukkit.getInstance();
    }
    @EventHandler(ignoreCancelled = true, priority = EventPriority.LOWEST)
    public void onPlayerSwapHandItems(PlayerSwapHandItemsEvent event) {
        SenderBukkit sender = new SenderBukkit(event.getPlayer(), mab);
        if (this.mab.getAPI().isAuthenticated(sender)) {
            return;
        }
        event.setCancelled(true);
    }
}
