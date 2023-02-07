package de.xxschrandxx.wsc.wscauthenticator.bungee;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;

import de.xxschrandxx.wsc.wscauthenticator.bungee.api.MinecraftAuthenticatorBungeeAPI;
import de.xxschrandxx.wsc.wscauthenticator.bungee.commands.*;
import de.xxschrandxx.wsc.wscauthenticator.bungee.listener.*;
import de.xxschrandxx.wsc.wscauthenticator.core.MinecraftAuthenticatorVars;
import de.xxschrandxx.wsc.wscbridge.bungee.MinecraftBridgeBungee;
import de.xxschrandxx.wsc.wscbridge.bungee.api.ConfigurationBungee;
import de.xxschrandxx.wsc.wscbridge.bungee.api.command.SenderBungee;
import de.xxschrandxx.wsc.wscbridge.core.IMinecraftBridgePlugin;
import de.xxschrandxx.wsc.wscbridge.core.api.command.ISender;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

public class MinecraftAuthenticatorBungee extends Plugin implements IMinecraftBridgePlugin<MinecraftAuthenticatorBungeeAPI> {

    // start of api part
    public String getInfo() {
        return null;
    }

    private static MinecraftAuthenticatorBungee instance;
    public static MinecraftAuthenticatorBungee getInstance() {
        return instance;
    }

    private MinecraftAuthenticatorBungeeAPI api;

    public void loadAPI(ISender<?> sender) {
        String urlString = getConfiguration().getString(MinecraftAuthenticatorVars.Configuration.url);
        URL url;
        try {
            url = new URL(urlString);
        } catch (MalformedURLException e) {
            getLogger().log(Level.INFO, "Could not load api, disabeling plugin!.", e);
            return;
        }

        MinecraftBridgeBungee wsc = MinecraftBridgeBungee.getInstance();
        this.api = new MinecraftAuthenticatorBungeeAPI(
            url,
            getLogger(),
            wsc.getAPI()
        );
    }

    public MinecraftAuthenticatorBungeeAPI getAPI() {
        return this.api;
    }
    // end of api part

    // start of plugin part
    @Override
    public void onEnable() {
        instance = this;

        // Load configuration
        getLogger().log(Level.INFO, "Loading Configuration.");
        SenderBungee sender = new SenderBungee(getProxy().getConsole(), getInstance());
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
        getProxy().getPluginManager().registerCommand(getInstance(), new LoginCommand("login"));
        getProxy().getPluginManager().registerCommand(getInstance(), new LogoutCommand("logout"));

        // register listener
        getLogger().log(Level.INFO, "Loading Listener.");
        getProxy().getPluginManager().registerListener(getInstance(), new WSCBridgeConfigReloadListenerBungee());
        getProxy().getPluginManager().registerListener(getInstance(), new WSCBridgePluginReloadListenerBungee());
        getProxy().getPluginManager().registerListener(getInstance(), new MABListener());
        getProxy().getPluginManager().registerListener(getInstance(), new AuthenticationListener());
        getProxy().getPluginManager().registerListener(getInstance(), new PlayerListener());
        getProxy().getPluginManager().registerListener(getInstance(), new AddModuleListenerBungee());
    }

    @Override
    public void onDisable() {
    }
    // end of plugin part

    // start config part
    private File configFile = new File(getDataFolder(), "config.yml");
    private ConfigurationBungee config;

    public ConfigurationBungee getConfiguration() {
        return getInstance().config;
    }

    public boolean reloadConfiguration(ISender<?> sender) {
        if (!getDataFolder().exists()) {
            getDataFolder().mkdir();
        }
        if (configFile.exists()) {
            try {
                config = new ConfigurationBungee(ConfigurationProvider.getProvider(YamlConfiguration.class).load(configFile));
            }
            catch (IOException e) {
                getLogger().log(Level.WARNING, "Could not load config.yml.", e);
                return false;
            }
        }
        else {
            try {
                configFile.createNewFile();
            }
            catch (IOException e) {
                getLogger().log(Level.WARNING, "Could not create config.yml.", e);
                return false;
            }
            config = new ConfigurationBungee();
        }

        if (MinecraftAuthenticatorVars.startConfig(getConfiguration(), getLogger())) {
            if (!saveConfiguration()) {
                return false;
            }
            return reloadConfiguration(sender);
        }
        return true;
    }

    public boolean saveConfiguration() {
        try {
            ConfigurationProvider.getProvider(YamlConfiguration.class).save(config.getConfiguration(), configFile);
        }
        catch (IOException e) {
            getLogger().log(Level.WARNING, "Could not save config.yml.", e);
            return false;
        }
        return true;
    }
    // end config part
}
