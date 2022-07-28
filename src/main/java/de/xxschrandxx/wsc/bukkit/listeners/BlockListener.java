package de.xxschrandxx.wsc.bukkit.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

import de.xxschrandxx.wsc.bukkit.MinecraftAuthenticatorBukkit;

public class BlockListener implements Listener {

    private final MinecraftAuthenticatorBukkit mab;

    public BlockListener() {
        this.mab = MinecraftAuthenticatorBukkit.getInstance();
    }

    @EventHandler(ignoreCancelled = true)
    public void onBlockPlace(BlockPlaceEvent event) {
        if (this.mab.getAPI().isAuthenticated(event.getPlayer())) {
            return;
        }
        event.setCancelled(true);
    }

    @EventHandler(ignoreCancelled = true)
    public void onBlockBreak(BlockBreakEvent event) {
        if (this.mab.getAPI().isAuthenticated(event.getPlayer())) {
            return;
        }
        event.setCancelled(true);
    }

}
