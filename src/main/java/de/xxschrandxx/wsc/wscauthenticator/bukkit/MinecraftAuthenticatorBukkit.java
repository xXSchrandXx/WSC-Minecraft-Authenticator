package de.xxschrandxx.wsc.wscauthenticator.bukkit;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.logging.Level;

import org.bukkit.plugin.java.JavaPlugin;

import de.xxschrandxx.wsc.wscauthenticator.bukkit.api.MinecraftAuthenticatorBukkitAPI;
import de.xxschrandxx.wsc.wscauthenticator.bukkit.commands.*;
import de.xxschrandxx.wsc.wscauthenticator.bukkit.listener.*;
import de.xxschrandxx.wsc.wscauthenticator.core.MinecraftAuthenticatorVars;
import de.xxschrandxx.wsc.wscbridge.bukkit.MinecraftBridgeBukkit;
import de.xxschrandxx.wsc.wscbridge.bukkit.api.ConfigurationBukkit;
import de.xxschrandxx.wsc.wscbridge.bukkit.api.command.SenderBukkit;
import de.xxschrandxx.wsc.wscbridge.core.IBridgePlugin;
import de.xxschrandxx.wsc.wscbridge.core.api.MinecraftBridgeLogger;
import de.xxschrandxx.wsc.wscbridge.core.api.command.ISender;

public class MinecraftAuthenticatorBukkit extends JavaPlugin implements IBridgePlugin<MinecraftAuthenticatorBukkitAPI> {
    // start of api part
    public String getInfo() {
        return null;
    }

    private static MinecraftAuthenticatorBukkit instance;
    public static MinecraftAuthenticatorBukkit getInstance() {
        return instance;
    }

    private MinecraftAuthenticatorBukkitAPI api;

    private MinecraftBridgeLogger bridgeLogger;

    @Override
    public MinecraftBridgeLogger getBridgeLogger() {
        return bridgeLogger;
    }

    public void loadAPI(ISender<?> sender) {
        String urlString = getConfiguration().getString(MinecraftAuthenticatorVars.Configuration.url);
        URL url;
        try {
            url = URI.create(urlString).toURL();
        } catch (MalformedURLException e) {
            getLogger().log(Level.INFO, "Could not load api, disabeling plugin!.", e);
            return;
        }

        MinecraftBridgeBukkit wsc = MinecraftBridgeBukkit.getInstance();
        this.api = new MinecraftAuthenticatorBukkitAPI(
            url,
            getBridgeLogger(),
            wsc.getAPI()
        );
    }

    public MinecraftAuthenticatorBukkitAPI getAPI() {
        return this.api;
    }
    // end of api part

    // start of plugin part
    @Override
    public void onEnable() {
        instance = this;
        bridgeLogger = new MinecraftBridgeLogger(getLogger());

        // Load configuration
        getLogger().log(Level.INFO, "Loading Configuration.");
        SenderBukkit sender = new SenderBukkit(getServer().getConsoleSender(), getInstance());
        if (!reloadConfiguration(sender)) {
            getLogger().log(Level.WARNING, "Could not load config.yml, disabeling plugin!");
            onDisable();
            return;
        }

        // Load api
        getLogger().log(Level.INFO, "Loading API.");
        loadAPI(sender);

        // register command
        getLogger().log(Level.INFO, "Loading Commands.");
        getCommand("login").setExecutor(new LoginCommand());
        getCommand("logout").setExecutor(new LogoutCommand());

        // register listener
        getLogger().log(Level.INFO, "Loading Listener.");
        getServer().getPluginManager().registerEvents(new WSCBridgeConfigReloadListenerBukkit(), getInstance());
        getServer().getPluginManager().registerEvents(new WSCBridgePluginReloadListenerBukkit(), getInstance());
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


    @Override
    public void onDisable() {
    }
    // end of plugin part

    // start config part
    public ConfigurationBukkit getConfiguration() {
        return new ConfigurationBukkit(getInstance().getConfig());
    }

    public boolean reloadConfiguration(ISender<?> sender) {
        reloadConfig();

        if (MinecraftAuthenticatorVars.startConfig(getConfiguration(), getBridgeLogger())) {
            if (!saveConfiguration()) {
                return false;
            }
            return reloadConfiguration(sender);
        }
        return true;
    }

    public boolean saveConfiguration() {
        saveConfig();
        return true;
    }
    // end config part
}
