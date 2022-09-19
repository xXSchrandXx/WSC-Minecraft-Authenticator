package de.xxschrandxx.wsc.wscauthenticator.bungee.api.event;

import de.xxschrandxx.wsc.wscbridge.bungee.api.event.WSCBridgeConfigReloadEventBungee;
import de.xxschrandxx.wsc.wscbridge.core.api.command.ISender;

public class WSCAuthenticatorConfigReloadEventBungee extends WSCBridgeConfigReloadEventBungee {
    public WSCAuthenticatorConfigReloadEventBungee(ISender<?> sender) {
        super(sender);
    }   
}
