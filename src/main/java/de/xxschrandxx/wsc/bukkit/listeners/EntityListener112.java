package de.xxschrandxx.wsc.bukkit.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;

import de.xxschrandxx.wsc.bukkit.MinecraftAuthenticatorBukkit;

public class EntityListener112 implements Listener {
    private final MinecraftAuthenticatorBukkit mab;
    public EntityListener112() {
        this.mab = MinecraftAuthenticatorBukkit.getInstance();
    }
    @EventHandler
    public void onPickupItem(EntityPickupItemEvent event) {
        if (!(event.getEntity() instanceof Player)) {
            return;
        }
        if (this.mab.isAuthenticated((Player) event.getEntity())) {
            return;
        }
        event.setCancelled(true);
    }
}
