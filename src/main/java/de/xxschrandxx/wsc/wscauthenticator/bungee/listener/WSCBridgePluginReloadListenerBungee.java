package de.xxschrandxx.wsc.wscauthenticator.bungee.listener;

import de.xxschrandxx.wsc.wscauthenticator.bungee.MinecraftAuthenticatorBungee;
import de.xxschrandxx.wsc.wscauthenticator.bungee.api.event.WSCAuthenticatorPluginReloadEventBungee;
import de.xxschrandxx.wsc.wscauthenticator.core.MinecraftAuthenticatorVars;
import de.xxschrandxx.wsc.wscbridge.bungee.api.event.WSCBridgePluginReloadEventBungee;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

public class WSCBridgePluginReloadListenerBungee implements Listener {
    @EventHandler
    public void onPluginReload(WSCBridgePluginReloadEventBungee event) {
        MinecraftAuthenticatorBungee instance = MinecraftAuthenticatorBungee.getInstance();
        String apiStart = instance.getConfiguration().getString(MinecraftAuthenticatorVars.Configuration.LangCmdReloadAPIStart);
        event.getSender().sendMessage(apiStart);
        instance.loadAPI(event.getSender());
        String apiSuccess = instance.getConfiguration().getString(MinecraftAuthenticatorVars.Configuration.LangCmdReloadAPISuccess);
        event.getSender().sendMessage(apiSuccess);
        instance.getProxy().getPluginManager().callEvent(new WSCAuthenticatorPluginReloadEventBungee(event.getSender()));
    }
}
