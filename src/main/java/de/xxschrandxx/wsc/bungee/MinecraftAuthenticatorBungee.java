package de.xxschrandxx.wsc.bungee;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;

import de.xxschrandxx.wsc.bungee.api.MinecraftAuthenticatorBungeeAPI;
import de.xxschrandxx.wsc.bungee.commands.*;
import de.xxschrandxx.wsc.bungee.listeners.*;
import de.xxschrandxx.wsc.core.MinecraftAuthenticatorVars;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

public class MinecraftAuthenticatorBungee extends Plugin {

    // start of api part
    private static MinecraftAuthenticatorBungee instance;
    public static MinecraftAuthenticatorBungee getInstance() {
        return instance;
    }
    private MinecraftAuthenticatorBungeeAPI api;
    public MinecraftAuthenticatorBungeeAPI getAPI() {
        return this.api;
    }
    // end of api part

    // start of authentication part
    private ConcurrentHashMap<UUID, Boolean> authenticated = new ConcurrentHashMap<UUID, Boolean>();

    public Boolean isAuthenticated(ProxiedPlayer player) {
        return this.isAuthenticated(player.getUniqueId());
    }
    public Boolean isAuthenticated(UUID uuid) {
        return this.authenticated.getOrDefault(uuid, false);
    }

    public Boolean setAuthenticated(UUID uuid, Boolean authenticated) {
        return this.authenticated.put(uuid, authenticated);
    }
    // end of authentication part

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
            this.api = new MinecraftAuthenticatorBungeeAPI(
                getConfiguration().getString(MinecraftAuthenticatorVars.Configuration.URL),
                getConfiguration().getString(MinecraftAuthenticatorVars.Configuration.Key)
                );
        }
        catch (MalformedURLException e) {
            e.printStackTrace();
            return;
        }

        // register command
        getProxy().getPluginManager().registerCommand(getInstance(), new LoginCommand());
        getProxy().getPluginManager().registerCommand(getInstance(), new LogoutCommand());

        // register listener
        getProxy().getPluginManager().registerListener(getInstance(), new MABListener());
        getProxy().getPluginManager().registerListener(getInstance(), new AuthenticationListener());
        getProxy().getPluginManager().registerListener(getInstance(), new PlayerListener());
    }
    // end of plugin part

    // start config part
    private File configFile = new File(getDataFolder(), "config.yml");
    private Configuration config;

    public Configuration getConfiguration() {
        return getInstance().config;
    }

    public boolean reloadConfiguration() {
        if (!getDataFolder().exists()) {
            getDataFolder().mkdir();
        }
        if (configFile.exists()) {
            try {
                config = ConfigurationProvider.getProvider(YamlConfiguration.class).load(configFile);
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
            config = new Configuration();
        }

        boolean error = false;

        // start config data

        // URL
        if (checkConfiguration(MinecraftAuthenticatorVars.Configuration.URL, MinecraftAuthenticatorVars.Configuration.defaults.URL))
            error = true;
        // Key
        if (checkConfiguration(MinecraftAuthenticatorVars.Configuration.Key, MinecraftAuthenticatorVars.Configuration.defaults.Key))
            error = true;

        // LoginCommand
        // LoginCommandOnlyPlayers
        if (checkConfiguration(MinecraftAuthenticatorVars.Configuration.LoginCommandOnlyPlayers, MinecraftAuthenticatorVars.Configuration.defaults.LoginCommandOnlyPlayers))
            error = true;
        // LoginCommandUsage
        if (checkConfiguration(MinecraftAuthenticatorVars.Configuration.LoginCommandUsage, MinecraftAuthenticatorVars.Configuration.defaults.LoginCommandUsage))
            error = true;
        // LoginCommandSuccess
        if (checkConfiguration(MinecraftAuthenticatorVars.Configuration.LoginCommandSuccess, MinecraftAuthenticatorVars.Configuration.defaults.LoginCommandSuccess))
            error = true;
        // LoginCommandFailure
        if (checkConfiguration(MinecraftAuthenticatorVars.Configuration.LoginCommandFailure, MinecraftAuthenticatorVars.Configuration.defaults.LoginCommandFailure))
            error = true;
        // LoginCommandAlreadyIn
        if (checkConfiguration(MinecraftAuthenticatorVars.Configuration.LoginCommandAlreadyIn, MinecraftAuthenticatorVars.Configuration.defaults.LoginCommandAlreadyIn))
            error = true;

        // LogoutCommand
        // LogoutCommandOnlyPlayers
        if (checkConfiguration(MinecraftAuthenticatorVars.Configuration.LogoutCommandOnlyPlayers, MinecraftAuthenticatorVars.Configuration.defaults.LogoutCommandOnlyPlayers))
            error = true;
        // LogoutCommandAlreadyOut
        if (checkConfiguration(MinecraftAuthenticatorVars.Configuration.LogoutCommandAlreadyOut, MinecraftAuthenticatorVars.Configuration.defaults.LogoutCommandAlreadyOut))
            error = true;
        // LogoutCommandSuccess
        if (checkConfiguration(MinecraftAuthenticatorVars.Configuration.LogoutCommandSuccess, MinecraftAuthenticatorVars.Configuration.defaults.LogoutCommandSuccess))
            error = true;

        // Protection
        // TODO
        // AllowServerSwitch
        if (checkConfiguration(MinecraftAuthenticatorVars.Configuration.AllowServerSwitch, MinecraftAuthenticatorVars.Configuration.defaults.AllowServerSwitch))
            error = true;
        // AllowMessageSend
        if (checkConfiguration(MinecraftAuthenticatorVars.Configuration.AllowMessageSend, MinecraftAuthenticatorVars.Configuration.defaults.AllowMessageSend))
            error = true;
        // AllowMessageSendLocale
        if (checkConfiguration(MinecraftAuthenticatorVars.Configuration.AllowMessageSendLocale, MinecraftAuthenticatorVars.Configuration.defaults.AllowMessageSendLocale))
            error = true;
        // AllowedCommands
        if (checkConfiguration(MinecraftAuthenticatorVars.Configuration.AllowedCommands, MinecraftAuthenticatorVars.Configuration.defaults.AllowedCommands))
            error = true;
        // DenyCommandSendLocale
        if (checkConfiguration(MinecraftAuthenticatorVars.Configuration.DenyCommandSendLocale, MinecraftAuthenticatorVars.Configuration.defaults.DenyCommandSendLocale))
            error = true;
        // AllowMessageReceive
        if (checkConfiguration(MinecraftAuthenticatorVars.Configuration.AllowMessageReceive, MinecraftAuthenticatorVars.Configuration.defaults.AllowMessageReceive))
            error = true;

        // Server
        // AuthenticationServerEnabled
        if (checkConfiguration(MinecraftAuthenticatorVars.Configuration.AuthenticationServerEnabled, MinecraftAuthenticatorVars.Configuration.defaults.AuthenticationServerEnabled))
            error = true;
        // AuthenticationServerName
        if (checkConfiguration(MinecraftAuthenticatorVars.Configuration.AuthenticationServerName, MinecraftAuthenticatorVars.Configuration.defaults.AuthenticationServerName))
            error = true;
        // LobbyServerEnabled
        if (checkConfiguration(MinecraftAuthenticatorVars.Configuration.LobbyServerEnabled, MinecraftAuthenticatorVars.Configuration.defaults.LobbyServerEnabled))
            error = true;
        // LobbyServerList
        if (checkConfiguration(MinecraftAuthenticatorVars.Configuration.LobbyServerList, MinecraftAuthenticatorVars.Configuration.defaults.LobbyServerList))
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
        try {
            ConfigurationProvider.getProvider(YamlConfiguration.class).save(config, configFile);
        }
        catch (IOException e) {
            getLogger().log(Level.WARNING, "Could not save config.yml.", e);
            return false;
        }
        return true;
    }
    // end config part
}
