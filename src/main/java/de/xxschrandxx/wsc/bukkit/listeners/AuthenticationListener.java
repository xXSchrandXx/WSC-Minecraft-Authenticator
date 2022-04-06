package de.xxschrandxx.wsc.bukkit.listeners;

import org.bukkit.Location;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;

import de.xxschrandxx.wsc.bukkit.MinecraftAuthenticatorBukkit;
import de.xxschrandxx.wsc.bukkit.api.events.LoginEvent;
import de.xxschrandxx.wsc.bukkit.api.events.LogoutEvent;
import de.xxschrandxx.wsc.core.MinecraftAuthenticatorVars.Configuration;

public class AuthenticationListener implements Listener {

    private final MinecraftAuthenticatorBukkit mab;

    public AuthenticationListener() {
        this.mab = MinecraftAuthenticatorBukkit.getInstance();
    }

    @EventHandler(priority = EventPriority.LOW)
    public void onJoin(PlayerJoinEvent event) {
        if (!this.mab.getConfiguration().getBoolean(Configuration.TeleportUnauthedEnabled)) {
            return;
        }
        Location TeleportUnauthedLocation = this.mab.getConfiguration().getLocation(Configuration.TeleportUnauthedLocation);
        if (TeleportUnauthedLocation == null) {
            return;
        }
        if (!TeleportUnauthedLocation.isWorldLoaded()) {
            return;
        }
        if (this.mab.isAuthenticated(event.getPlayer())) {
            return;
        }
        event.getPlayer().teleport(TeleportUnauthedLocation, TeleportCause.PLUGIN);
    }

    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    public void onLogout(LogoutEvent event) {
        if (!this.mab.getConfiguration().getBoolean(Configuration.TeleportUnauthedEnabled)) {
            return;
        }
        Location TeleportUnauthedLocation = this.mab.getConfiguration().getLocation(Configuration.TeleportUnauthedLocation);
        if (TeleportUnauthedLocation == null) {
            return;
        }
        if (!TeleportUnauthedLocation.isWorldLoaded()) {
            return;
        }
        event.get().teleport(TeleportUnauthedLocation, TeleportCause.PLUGIN);
    }

    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    public void onLogin(LoginEvent event) {
        if (!this.mab.getConfiguration().getBoolean(Configuration.TeleportAuthedEnabled)) {
            return;
        }
        Location TeleportAuthedLocation = this.mab.getConfiguration().getLocation(Configuration.TeleportAuthedLocation);
        if (TeleportAuthedLocation == null) {
            return;
        }
        if (!TeleportAuthedLocation.isWorldLoaded()) {
            return;
        }
        event.get().teleport(TeleportAuthedLocation, TeleportCause.PLUGIN);
    }

}
