package de.xxschrandxx.wsc.wscauthenticator.bukkit.api.event;

import de.xxschrandxx.wsc.wscbridge.bukkit.api.event.WSCBridgeConfigReloadEventBukkit;
import de.xxschrandxx.wsc.wscbridge.core.api.command.ISender;

public class WSCAuthenticatorConfigReloadEventBukkit extends WSCBridgeConfigReloadEventBukkit {
    public WSCAuthenticatorConfigReloadEventBukkit(ISender<?> sender) {
        super(sender);
    }   
}
