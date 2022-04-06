package de.xxschrandxx.wsc.bukkit.listeners;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerBedEnterEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerEditBookEvent;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerShearEntityEvent;

import de.xxschrandxx.wsc.bukkit.MinecraftAuthenticatorBukkit;
import de.xxschrandxx.wsc.core.MinecraftAuthenticatorVars.Configuration;

/**
 * Default listener
 */
public class PlayerListener implements Listener {

    private final MinecraftAuthenticatorBukkit mab;

    public PlayerListener() {
        this.mab = MinecraftAuthenticatorBukkit.getInstance();
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.LOWEST)
    public void onMessageReceive(AsyncPlayerChatEvent event) {
        if (this.mab.getConfiguration().getBoolean(Configuration.AllowMessageReceive)) {
            return;
        }
        for (Player player : event.getRecipients()) {
            if (this.mab.isAuthenticated(player)) {
                return;
            }
            event.getRecipients().remove(player);
        }
        if (!event.getRecipients().isEmpty()) {
            return;
        }
        event.setCancelled(true);
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.LOWEST)
    public void onMessageSend(AsyncPlayerChatEvent event) {
        if (this.mab.getConfiguration().getBoolean(Configuration.AllowMessageSend)) {
            return;
        }

        Player player = event.getPlayer();

        if (this.mab.isAuthenticated(player)) {
            return;
        }
        event.setCancelled(true);
        player.sendMessage(ChatColor.translateAlternateColorCodes('&',
            this.mab.getConfiguration().getString(Configuration.AllowMessageSendLocale)
        ));
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.LOWEST)
    public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent event) {
        String cmd = event.getMessage().split(" ")[0].toLowerCase();
        if (this.mab.getConfiguration().getStringList(Configuration.AllowedCommands).contains(cmd)) {
            return;
        }

        if (this.mab.isAuthenticated(event.getPlayer())) {
            return;
        }
        event.setCancelled(true);
        event.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&',
            this.mab.getConfiguration().getString(Configuration.DenyCommandSendLocale)
        ));
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.HIGHEST)
    public void onPlayerMove(PlayerMoveEvent event) {
        if (this.mab.getConfiguration().getBoolean(Configuration.AllowMovement)) {
            return;
        }

        Location from = event.getFrom();
        Location to = event.getTo();
        if (to == null) {
            return;
        }

        if (from.getBlockX() == to.getBlockX()
                && from.getBlockZ() == to.getBlockZ()
                && from.getY() - to.getY() >= 0) {
            return;
        }

        Player player = event.getPlayer();
        if (this.mab.isAuthenticated(player)) {
            return;
        }

        event.setTo(from);

    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.LOWEST)
    public void onPlayerInteract(PlayerInteractEvent event) {
        if (this.mab.isAuthenticated(event.getPlayer())) {
            return;
        }
        event.setCancelled(true);
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.LOWEST)
    public void onPlayerInteractEntity(PlayerInteractEntityEvent event) {
        if (this.mab.isAuthenticated(event.getPlayer())) {
            return;
        }
        event.setCancelled(true);
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.LOWEST)
    public void onPlayerInteractAtEntity(PlayerInteractAtEntityEvent event) {
        if (this.mab.isAuthenticated(event.getPlayer())) {
            return;
        }
        event.setCancelled(true);
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.LOWEST)
    public void onPlayerHitPlayerEvent(EntityDamageByEntityEvent event) {
        if (!(event.getEntity() instanceof Player)) {
            return;
        }
        Player player = (Player) event.getEntity();
        if (this.mab.isAuthenticated(player)) {
            return;
        }
        event.setCancelled(true);
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.LOWEST)
    public void onPlayerShear(PlayerShearEntityEvent event) {
        if (this.mab.isAuthenticated(event.getPlayer())) {
            return;
        }
        event.setCancelled(true);
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.LOWEST)
    public void onPlayerFish(PlayerFishEvent event) {
        if (this.mab.isAuthenticated(event.getPlayer())) {
            return;
        }
        event.setCancelled(true);
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.LOWEST)
    public void onPlayerBedEnter(PlayerBedEnterEvent event) {
        if (this.mab.isAuthenticated(event.getPlayer())) {
            return;
        }
        event.setCancelled(true);
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.LOWEST)
    public void onPlayerEditBook(PlayerEditBookEvent event) {
        if (this.mab.isAuthenticated(event.getPlayer())) {
            return;
        }
        event.setCancelled(true);
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.LOWEST)
    public void onSignChange(SignChangeEvent event) {
        if (this.mab.isAuthenticated(event.getPlayer())) {
            return;
        }
        event.setCancelled(true);
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.LOWEST)
    public void onPlayerDropItem(PlayerDropItemEvent event) {
        if (this.mab.isAuthenticated(event.getPlayer())) {
            return;
        }
        event.setCancelled(true);
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.LOWEST)
    public void onPlayerHeldItem(PlayerItemHeldEvent event) {
        if (this.mab.isAuthenticated(event.getPlayer())) {
            return;
        }
        event.setCancelled(true);
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.LOWEST)
    public void onPlayerConsumeItem(PlayerItemConsumeEvent event) {
        if (this.mab.isAuthenticated(event.getPlayer())) {
            return;
        }
        event.setCancelled(true);
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.LOWEST)
    public void onPlayerInventoryOpen(InventoryOpenEvent event) {
        if (!(event.getPlayer() instanceof Player)) {
            return;
        }
        final Player player = (Player) event.getPlayer();

        if (this.mab.isAuthenticated(player)) {
            return;
        }

        event.setCancelled(true);
        this.mab.getServer().getScheduler().scheduleSyncDelayedTask(this.mab, player::closeInventory, 1);
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.LOWEST)
    public void onPlayerInventoryClick(InventoryClickEvent event) {
        if (!(event.getWhoClicked() instanceof Player)) {
            return;
        }
        Player player = (Player) event.getWhoClicked();
        if (this.mab.isAuthenticated(player)) {
            return;
        }
        event.setCancelled(true);
    }

}
