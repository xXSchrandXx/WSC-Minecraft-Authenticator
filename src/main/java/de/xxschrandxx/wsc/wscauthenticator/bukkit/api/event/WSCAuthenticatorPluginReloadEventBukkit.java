package de.xxschrandxx.wsc.wscauthenticator.bukkit.api.event;

import de.xxschrandxx.wsc.wscbridge.bukkit.api.event.WSCBridgePluginReloadEventBukkit;
import de.xxschrandxx.wsc.wscbridge.core.api.command.ISender;

public class WSCAuthenticatorPluginReloadEventBukkit extends WSCBridgePluginReloadEventBukkit {
    public WSCAuthenticatorPluginReloadEventBukkit(ISender<?> sender) {
        super(sender);
    }    
}
