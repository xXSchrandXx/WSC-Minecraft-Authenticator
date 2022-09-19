package de.xxschrandxx.wsc.wscauthenticator.bukkit.listener;

import java.util.logging.Level;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import de.xxschrandxx.wsc.wscauthenticator.bukkit.MinecraftAuthenticatorBukkit;
import de.xxschrandxx.wsc.wscauthenticator.bukkit.api.event.WSCAuthenticatorConfigReloadEventBukkit;
import de.xxschrandxx.wsc.wscauthenticator.core.MinecraftAuthenticatorVars;
import de.xxschrandxx.wsc.wscbridge.bukkit.api.event.WSCBridgeConfigReloadEventBukkit;

public class WSCBridgeConfigReloadListenerBukkit implements Listener {
    @EventHandler
    public void onConfigReload(WSCBridgeConfigReloadEventBukkit event) {
        MinecraftAuthenticatorBukkit instance = MinecraftAuthenticatorBukkit.getInstance();
        String configStart = instance.getConfiguration().getString(MinecraftAuthenticatorVars.Configuration.LangCmdReloadConfigStart);
        event.getSender().sendMessage(configStart);
        if (!instance.reloadConfiguration(event.getSender())) {
                String configError = instance.getConfiguration().getString(MinecraftAuthenticatorVars.Configuration.LangCmdReloadConfigError);
                event.getSender().sendMessage(configError);
                instance.getLogger().log(Level.WARNING, "Could not load config.yml!");
            return;
        }
        String configSuccess = instance.getConfiguration().getString(MinecraftAuthenticatorVars.Configuration.LangCmdReloadConfigSuccess);
        event.getSender().sendMessage(configSuccess);
        instance.getServer().getPluginManager().callEvent(new WSCAuthenticatorConfigReloadEventBukkit(event.getSender()));
    }
}
