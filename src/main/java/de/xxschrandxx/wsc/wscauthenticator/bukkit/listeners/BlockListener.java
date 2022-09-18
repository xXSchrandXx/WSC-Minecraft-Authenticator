package de.xxschrandxx.wsc.wscauthenticator.bukkit.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

import de.xxschrandxx.wsc.wscauthenticator.bukkit.MinecraftAuthenticatorBukkit;
import de.xxschrandxx.wsc.wscbridge.bukkit.api.command.SenderBukkit;

public class BlockListener implements Listener {

    private final MinecraftAuthenticatorBukkit mab;

    public BlockListener() {
        this.mab = MinecraftAuthenticatorBukkit.getInstance();
    }

    @EventHandler(ignoreCancelled = true)
    public void onBlockPlace(BlockPlaceEvent event) {
        SenderBukkit sender = new SenderBukkit(event.getPlayer(), mab);
        if (this.mab.getAPI().isAuthenticated(sender)) {
            return;
        }
        event.setCancelled(true);
    }

    @EventHandler(ignoreCancelled = true)
    public void onBlockBreak(BlockBreakEvent event) {
        SenderBukkit sender = new SenderBukkit(event.getPlayer(), mab);
        if (this.mab.getAPI().isAuthenticated(sender)) {
            return;
        }
        event.setCancelled(true);
    }

}
