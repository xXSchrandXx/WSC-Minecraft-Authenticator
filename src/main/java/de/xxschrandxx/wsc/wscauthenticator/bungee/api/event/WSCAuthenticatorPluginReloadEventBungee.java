package de.xxschrandxx.wsc.wscauthenticator.bungee.api.event;

import de.xxschrandxx.wsc.wscbridge.bungee.api.event.AbstractWSCPluginReloadEventBungee;
import de.xxschrandxx.wsc.wscbridge.core.api.command.ISender;

public final class WSCAuthenticatorPluginReloadEventBungee extends AbstractWSCPluginReloadEventBungee {
    public WSCAuthenticatorPluginReloadEventBungee(ISender<?> sender) {
        super(sender);
    }    
}
