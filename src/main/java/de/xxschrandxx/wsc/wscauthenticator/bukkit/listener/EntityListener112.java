package de.xxschrandxx.wsc.wscauthenticator.bukkit.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityPickupItemEvent;

import de.xxschrandxx.wsc.wscauthenticator.bukkit.MinecraftAuthenticatorBukkit;
import de.xxschrandxx.wsc.wscbridge.bukkit.api.command.SenderBukkit;

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
        SenderBukkit sender = new SenderBukkit((Player) event.getEntity(), mab);
        if (this.mab.getAPI().isAuthenticated(sender)) {
            return;
        }
        event.setCancelled(true);
    }
}
