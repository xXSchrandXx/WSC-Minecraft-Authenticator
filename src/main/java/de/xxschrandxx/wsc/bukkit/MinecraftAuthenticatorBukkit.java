package de.xxschrandxx.wsc.bukkit;

import java.net.MalformedURLException;
import java.util.logging.Level;

import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import de.xxschrandxx.wsc.bukkit.api.MinecraftAuthenticatorBukkitAPI;
import de.xxschrandxx.wsc.bukkit.commands.*;
import de.xxschrandxx.wsc.bukkit.listeners.*;
import de.xxschrandxx.wsc.core.MinecraftAuthenticatorVars.Configuration;

public class MinecraftAuthenticatorBukkit extends JavaPlugin {

    // start of api part
    private static MinecraftAuthenticatorBukkit instance;
    public static MinecraftAuthenticatorBukkit getInstance() {
        return instance;
    }
    private MinecraftAuthenticatorBukkitAPI api;
    public MinecraftAuthenticatorBukkitAPI getAPI() {
        return this.api;
    }
    // end of api part

    // start of plugin part
    @Override
    public void onEnable() {
        instance = this;

        if (!reloadConfiguration()) {
            getLogger().log(Level.WARNING, "Could not load config.yml, disabeling plugin!");
            onDisable();
            return;
        }

        // set api
        try {
            this.api = new MinecraftAuthenticatorBukkitAPI(
                getConfiguration().getString(Configuration.URL),
                getConfiguration().getString(Configuration.Key),
                getConfiguration().getBoolean(Configuration.SessionsEnabled),
                getConfiguration().getLong(Configuration.SessionLength),
                getLogger()
                );
        }
        catch (MalformedURLException e) {
            e.printStackTrace();
            return;
        }

        // register command
        getCommand("login").setExecutor(new LoginCommand());
        getCommand("logout").setExecutor(new LogoutCommand());

        // register listener
        getServer().getPluginManager().registerEvents(new MABListener(), getInstance());
        getServer().getPluginManager().registerEvents(new AuthenticationListener(), getInstance());
        getServer().getPluginManager().registerEvents(new BlockListener(), getInstance());
        getServer().getPluginManager().registerEvents(new PlayerListener(), getInstance());
        try {
            Class.forName("org.bukkit.event.player.PlayerSwapHandItemsEvent");
            getServer().getPluginManager().registerEvents(new PlayerListener19(), getInstance());
        } catch (ClassNotFoundException e) {}
        try {
            Class.forName("org.bukkit.event.entity.EntityAirChangeEvent");
            getServer().getPluginManager().registerEvents(new PlayerListener111(), getInstance());
        } catch (ClassNotFoundException e) {}
        try {
            Class.forName("org.bukkit.event.entity.EntityPickupItemEvent");
            getServer().getPluginManager().registerEvents(new EntityListener112(), getInstance());
        } catch (ClassNotFoundException e) {
            getServer().getPluginManager().registerEvents(new PlayerListener17(), getInstance());
        }
    }
    // end of plugin part

    // start config part
    public FileConfiguration getConfiguration() {
        return getInstance().getConfig();
    }

    public boolean reloadConfiguration() {
        reloadConfig();

        boolean error = false;

        // start config data

        // URL
        if (checkConfiguration(Configuration.URL, Configuration.defaults.URL))
            error = true;
        // Key
        if (checkConfiguration(Configuration.Key, Configuration.defaults.Key))
            error = true;

        // Sessions
        // SessionsEnabled
        if (checkConfiguration(Configuration.SessionsEnabled, Configuration.defaults.SessionsEnabled))
            error = true;
        // SessionLength
        if (checkConfiguration(Configuration.SessionLength, Configuration.defaults.SessionLength))
            error = true;

        // LoginCommand
        // LoginCommandOnlyPlayers
        if (checkConfiguration(Configuration.LoginCommandOnlyPlayers, Configuration.defaults.LoginCommandOnlyPlayers))
            error = true;
        // LoginCommandUsage
        if (checkConfiguration(Configuration.LoginCommandUsage, Configuration.defaults.LoginCommandUsage))
            error = true;
        // LoginCommandSuccess
        if (checkConfiguration(Configuration.LoginCommandSuccess, Configuration.defaults.LoginCommandSuccess))
            error = true;
        // LoginCommandFailure
        if (checkConfiguration(Configuration.LoginCommandFailure, Configuration.defaults.LoginCommandFailure))
            error = true;
        // LoginCommandAlreadyIn
        if (checkConfiguration(Configuration.LoginCommandAlreadyIn, Configuration.defaults.LoginCommandAlreadyIn))
            error = true;

        // LogoutCommand
        // LogoutCommandOnlyPlayers
        if (checkConfiguration(Configuration.LogoutCommandOnlyPlayers, Configuration.defaults.LogoutCommandOnlyPlayers))
            error = true;
        // LogoutCommandAlreadyOut
        if (checkConfiguration(Configuration.LogoutCommandAlreadyOut, Configuration.defaults.LogoutCommandAlreadyOut))
            error = true;
        // LogoutCommandSuccess
        if (checkConfiguration(Configuration.LogoutCommandSuccess, Configuration.defaults.LogoutCommandSuccess))
            error = true;

        // LoginViaSession
        if (checkConfiguration(Configuration.LoginViaSession, Configuration.defaults.LoginViaSession))
            error = true;

        // Protection
        // AllowMessageReceive
        if (checkConfiguration(Configuration.AllowMessageReceive, Configuration.defaults.AllowMessageReceive))
            error = true;
        // AllowMessageSend
        if (checkConfiguration(Configuration.AllowMessageSend, Configuration.defaults.AllowMessageSend))
            error = true;
        // AllowMessageSendLocale
        if (checkConfiguration(Configuration.AllowMessageSendLocale, Configuration.defaults.AllowMessageSendLocale))
            error = true;
        // AllowedCommands
        if (checkConfiguration(Configuration.AllowedCommands, Configuration.defaults.AllowedCommands))
            error = true;
        // DenyCommandSendLocale
        if (checkConfiguration(Configuration.DenyCommandSendLocale, Configuration.defaults.DenyCommandSendLocale))
            error = true;
        // AllowMovement
        if (checkConfiguration(Configuration.AllowMovement, Configuration.defaults.AllowMovement))
            error = true;


        // Teleportation
        Location defaultLocation = getServer().getWorlds().iterator().next().getSpawnLocation();
        // TeleportUnauthedEnabled
        if (checkConfiguration(Configuration.TeleportUnauthedEnabled, Configuration.defaults.TeleportUnauthedEnabled))
            error = true;
        // TeleportUnauthedLocation
        if (checkConfiguration(Configuration.TeleportUnauthedLocation, defaultLocation))
            error = true;
        // TeleportAuthedEnabled
        if (checkConfiguration(Configuration.TeleportAuthedEnabled, Configuration.defaults.TeleportAuthedEnabled))
            error = true;
        // TeleportAuthedLocation
        if (checkConfiguration(Configuration.TeleportAuthedLocation, defaultLocation))
            error = true;

        // end config data

        if (error) {
            if (!saveConfiguration()) {
                return false;
            }
            return reloadConfiguration();
        }
        return true;
    }

    public boolean checkConfiguration(String path, Object def) {
        if (getConfiguration().get(path) == null) {
            getLogger().log(Level.WARNING, path + " is not set. Resetting it.");
            getConfiguration().set(path, def);
            return true;
        }
        else {
            return false;
        }
    }

    public boolean saveConfiguration() {
        saveConfig();
        return true;
    }
    // end config part
}
