package de.xxschrandxx.wsc.wscauthenticator.bungee.listener;

import java.util.logging.Level;

import de.xxschrandxx.wsc.wscauthenticator.bungee.MinecraftAuthenticatorBungee;
import de.xxschrandxx.wsc.wscauthenticator.bungee.api.event.WSCAuthenticatorConfigReloadEventBungee;
import de.xxschrandxx.wsc.wscauthenticator.core.MinecraftAuthenticatorVars;
import de.xxschrandxx.wsc.wscbridge.bungee.api.event.WSCBridgeConfigReloadEventBungee;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class WSCBridgeConfigReloadListenerBungee implements Listener {
    @EventHandler
    public void onConfigReload(WSCBridgeConfigReloadEventBungee event) {
        MinecraftAuthenticatorBungee instance = MinecraftAuthenticatorBungee.getInstance();
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
        instance.getProxy().getPluginManager().callEvent(new WSCAuthenticatorConfigReloadEventBungee(event.getSender()));
    }
}
