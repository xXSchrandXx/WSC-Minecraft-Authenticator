package de.xxschrandxx.wsc.wscauthenticator.bungee.api.event;

import de.xxschrandxx.wsc.wscbridge.bungee.api.event.AbstractWSCConfigReloadEventBungee;
import de.xxschrandxx.wsc.wscbridge.core.api.command.ISender;

public final class WSCAuthenticatorConfigReloadEventBungee extends AbstractWSCConfigReloadEventBungee {
    public WSCAuthenticatorConfigReloadEventBungee(ISender<?> sender) {
        super(sender);
    }   
}
