package de.xxschrandxx.wsc.wscauthenticator.core.api.events;

import de.xxschrandxx.wsc.wscbridge.core.api.command.ISender;

public interface ILogoutCoreEvent {
    /**
     * Gets the {@link ISender} for the event.
     * 
     * @return The {@link ISender}.
     */
    public ISender<?> get();
}
