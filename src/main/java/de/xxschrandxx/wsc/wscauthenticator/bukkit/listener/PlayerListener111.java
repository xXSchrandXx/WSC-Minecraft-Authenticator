package de.xxschrandxx.wsc.wscauthenticator.bukkit.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityAirChangeEvent;

import de.xxschrandxx.wsc.wscauthenticator.bukkit.MinecraftAuthenticatorBukkit;
import de.xxschrandxx.wsc.wscbridge.bukkit.api.command.SenderBukkit;

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
        SenderBukkit sender = new SenderBukkit(player, mab);
        if (this.mab.getAPI().isAuthenticated(sender)) {
            return;
        }
        event.setCancelled(true);
    }
}
