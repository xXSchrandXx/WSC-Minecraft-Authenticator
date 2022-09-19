package de.xxschrandxx.wsc.wscauthenticator.bukkit.listener;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import de.xxschrandxx.wsc.wscauthenticator.bukkit.MinecraftAuthenticatorBukkit;
import de.xxschrandxx.wsc.wscauthenticator.bukkit.api.event.WSCAuthenticatorPluginReloadEventBukkit;
import de.xxschrandxx.wsc.wscauthenticator.core.MinecraftAuthenticatorVars;
import de.xxschrandxx.wsc.wscbridge.bukkit.api.event.WSCBridgePluginReloadEventBukkit;

public class WSCBridgePluginReloadListenerBukkit implements Listener {
    @EventHandler
    public void onPluginReload(WSCBridgePluginReloadEventBukkit event) {
        MinecraftAuthenticatorBukkit instance = MinecraftAuthenticatorBukkit.getInstance();
        String apiStart = instance.getConfiguration().getString(MinecraftAuthenticatorVars.Configuration.LangCmdReloadAPIStart);
        event.getSender().sendMessage(apiStart);
        instance.loadAPI(event.getSender());
        String apiSuccess = instance.getConfiguration().getString(MinecraftAuthenticatorVars.Configuration.LangCmdReloadAPISuccess);
        event.getSender().sendMessage(apiSuccess);
        instance.getServer().getPluginManager().callEvent(new WSCAuthenticatorPluginReloadEventBukkit(event.getSender()));
    }
}
