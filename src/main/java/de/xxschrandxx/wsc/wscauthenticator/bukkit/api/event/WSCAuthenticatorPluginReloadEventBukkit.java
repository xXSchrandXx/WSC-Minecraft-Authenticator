package de.xxschrandxx.wsc.wscauthenticator.bukkit.api.event;

import de.xxschrandxx.wsc.wscbridge.bukkit.api.event.AbstractWSCPluginReloadEventBukkit;
import de.xxschrandxx.wsc.wscbridge.core.api.command.ISender;

public final class WSCAuthenticatorPluginReloadEventBukkit extends AbstractWSCPluginReloadEventBukkit {
    public WSCAuthenticatorPluginReloadEventBukkit(ISender<?> sender) {
        super(sender);
    }    
}
