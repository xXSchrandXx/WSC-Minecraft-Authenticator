package de.xxschrandxx.wsc.wscauthenticator.bukkit.listener;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;

import de.xxschrandxx.wsc.wscauthenticator.bukkit.MinecraftAuthenticatorBukkit;
import de.xxschrandxx.wsc.wscauthenticator.bukkit.api.event.LoginEvent;
import de.xxschrandxx.wsc.wscauthenticator.bukkit.api.event.LogoutEvent;
import de.xxschrandxx.wsc.wscauthenticator.core.MinecraftAuthenticatorVars.Configuration;
import de.xxschrandxx.wsc.wscbridge.bukkit.api.command.SenderBukkit;

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
        World world = Bukkit.getWorld(this.mab.getConfiguration().getString(Configuration.TeleportUnauthedLocationWorld));
        if (world == null) {
            return;
        }
        double x = this.mab.getConfiguration().getDouble(Configuration.TeleportUnauthedLocationX);
        double y = this.mab.getConfiguration().getDouble(Configuration.TeleportUnauthedLocationY);
        double z = this.mab.getConfiguration().getDouble(Configuration.TeleportUnauthedLocationZ);
        float yaw = this.mab.getConfiguration().getFloat(Configuration.TeleportUnauthedLocationYaw);
        float pitch = this.mab.getConfiguration().getFloat(Configuration.TeleportUnauthedLocationPitch);
        Location TeleportUnauthedLocation = new Location(
            world,
            x,
            y,
            z,
            yaw,
            pitch
        );
        if (!TeleportUnauthedLocation.isWorldLoaded()) {
            return;
        }
        SenderBukkit sender = new SenderBukkit(event.getPlayer(), mab);
        if (this.mab.getAPI().isAuthenticated(sender)) {
            return;
        }
        event.getPlayer().teleport(TeleportUnauthedLocation, TeleportCause.PLUGIN);
    }

    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    public void onLogout(LogoutEvent event) {
        if (!this.mab.getConfiguration().getBoolean(Configuration.TeleportUnauthedEnabled)) {
            return;
        }
        World world = Bukkit.getWorld(this.mab.getConfiguration().getString(Configuration.TeleportUnauthedLocationWorld));
        if (world == null) {
            return;
        }
        double x = this.mab.getConfiguration().getDouble(Configuration.TeleportUnauthedLocationX);
        double y = this.mab.getConfiguration().getDouble(Configuration.TeleportUnauthedLocationY);
        double z = this.mab.getConfiguration().getDouble(Configuration.TeleportUnauthedLocationZ);
        float yaw = this.mab.getConfiguration().getFloat(Configuration.TeleportUnauthedLocationYaw);
        float pitch = this.mab.getConfiguration().getFloat(Configuration.TeleportUnauthedLocationPitch);
        Location TeleportUnauthedLocation = new Location(
            world,
            x,
            y,
            z,
            yaw,
            pitch
        );
        if (!TeleportUnauthedLocation.isWorldLoaded()) {
            return;
        }
        Player player = (Player) event.get();
        player.teleport(TeleportUnauthedLocation, TeleportCause.PLUGIN);
    }

    @EventHandler(priority = EventPriority.LOWEST, ignoreCancelled = true)
    public void onLogin(LoginEvent event) {
        if (!this.mab.getConfiguration().getBoolean(Configuration.TeleportAuthedEnabled)) {
            return;
        }
        World world = Bukkit.getWorld(this.mab.getConfiguration().getString(Configuration.TeleportAuthedLocationWorld));
        if (world == null) {
            return;
        }
        double x = this.mab.getConfiguration().getDouble(Configuration.TeleportAuthedLocationX);
        double y = this.mab.getConfiguration().getDouble(Configuration.TeleportAuthedLocationY);
        double z = this.mab.getConfiguration().getDouble(Configuration.TeleportAuthedLocationZ);
        float yaw = this.mab.getConfiguration().getFloat(Configuration.TeleportAuthedLocationYaw);
        float pitch = this.mab.getConfiguration().getFloat(Configuration.TeleportAuthedLocationPitch);
        Location TeleportAuthedLocation = new Location(
            world,
            x,
            y,
            z,
            yaw,
            pitch
        );
        if (!TeleportAuthedLocation.isWorldLoaded()) {
            return;
        }
        Player player = (Player) event.get();
        player.teleport(TeleportAuthedLocation, TeleportCause.PLUGIN);
    }

}
