package de.xxschrandxx.wsc.wscauthenticator.bukkit.api.event;

import de.xxschrandxx.wsc.wscbridge.bukkit.api.event.AbstractWSCConfigReloadEventBukkit;
import de.xxschrandxx.wsc.wscbridge.core.api.command.ISender;

public final class WSCAuthenticatorConfigReloadEventBukkit extends AbstractWSCConfigReloadEventBukkit {
    public WSCAuthenticatorConfigReloadEventBukkit(ISender<?> sender) {
        super(sender);
    }   
}
