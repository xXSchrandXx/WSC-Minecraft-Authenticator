package de.xxschrandxx.wsc.bukkit.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;

import de.xxschrandxx.wsc.bukkit.MinecraftAuthenticatorBukkit;

public class PlayerListener19 implements Listener {

    private final MinecraftAuthenticatorBukkit mab;

    public PlayerListener19() {
        this.mab = MinecraftAuthenticatorBukkit.getInstance();
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.LOWEST)
    public void onPlayerSwapHandItems(PlayerSwapHandItemsEvent event) {
        if (this.mab.isAuthenticated(event.getPlayer())) {
            return;
        }
        event.setCancelled(true);
    }
}
