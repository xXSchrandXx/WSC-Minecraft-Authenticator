package de.xxschrandxx.wsc.wscauthenticator.bungee.api.event;

import de.xxschrandxx.wsc.wscbridge.bungee.api.event.WSCBridgePluginReloadEventBungee;
import de.xxschrandxx.wsc.wscbridge.core.api.command.ISender;

public class WSCAuthenticatorPluginReloadEventBungee extends WSCBridgePluginReloadEventBungee {
    public WSCAuthenticatorPluginReloadEventBungee(ISender<?> sender) {
        super(sender);
    }    
}
