package de.xxschrandxx.wsc.bukkit.listeners;

import java.util.concurrent.ConcurrentHashMap;

import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;
import org.bukkit.scheduler.BukkitTask;

import de.xxschrandxx.wsc.bukkit.MinecraftAuthenticatorBukkit;
import de.xxschrandxx.wsc.bukkit.api.events.LoginEvent;
import de.xxschrandxx.wsc.bukkit.api.events.LogoutEvent;
import de.xxschrandxx.wsc.core.MinecraftAuthenticatorVars.Configuration;

public class AuthenticationListener implements Listener {

    private final MinecraftAuthenticatorBukkit mab;

    public AuthenticationListener() {
        this.mab = MinecraftAuthenticatorBukkit.getInstance();
    }

    private ConcurrentHashMap<Player, BukkitTask> login = new ConcurrentHashMap<Player, BukkitTask>();

    @EventHandler(priority = EventPriority.NORMAL, ignoreCancelled = true)
    public void onPreJoin(PlayerJoinEvent event) {
        if (this.mab.isAuthenticated(event.getPlayer())) {
            this.mab.getServer().getPluginManager().callEvent(new LoginEvent(event.getPlayer()));
            return;
        }
        if (!login.containsKey(event.getPlayer())) {
            login.put(event.getPlayer(),
                    this.mab.getServer().getScheduler().runTaskTimerAsynchronously(this.mab, new Runnable() {
                        @Override
                        public void run() {
                            if (mab.isAuthenticated(event.getPlayer())) {
                                mab.getServer().getScheduler().runTask(mab, new Runnable() {
                                    @Override
                                    public void run() {
                                        mab.getServer().getPluginManager()
                                                .callEvent(new LoginEvent(event.getPlayer()));
                                    }
                                });
                                login.get(event.getPlayer()).cancel();
                                login.remove(event.getPlayer());
                            }
                        }
                    }, 3 * 5, 3 * 1));
        }
    }

    private ConcurrentHashMap<Player, BukkitTask> logout = new ConcurrentHashMap<Player, BukkitTask>();

    @EventHandler
    public void onLoging(LoginEvent event) {
        if (!logout.containsKey(event.get())) {
            logout.put(event.get(), this.mab.getServer().getScheduler().runTaskTimerAsynchronously(this.mab, new Runnable() {
                @Override
                public void run() {
                    if (!mab.isAuthenticated(event.get())) {
                        mab.getServer().getScheduler().runTask(mab, new Runnable() {
                            @Override
                            public void run() {
                                mab.getServer().getPluginManager().callEvent(new LogoutEvent(event.get()));
                            }
                        });
                        logout.get(event.get()).cancel();
                        logout.remove(event.get());
                    }
                }
            }, 3 * 5, 3 * 1));
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent event) {
        if (login.contains(event.getPlayer())) {
            login.get(event.getPlayer()).cancel();
            login.remove(event.getPlayer());
        }
        if (logout.contains(event.getPlayer())) {
            logout.get(event.getPlayer()).cancel();
            logout.remove(event.getPlayer());
        }
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
