package de.xxschrandxx.wsc.wscauthenticator.bungee.api.events;

import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Event;

/**
 * Event called when a player has successfully logged in or registered.
 */
public class LogoutEvent extends Event {

    private final ProxiedPlayer player;

    public LogoutEvent(ProxiedPlayer player) {
        this.player = player;
    }

    /**
     * Gets the {@link ProxiedPlayer} for the event.
     * 
     * @return The {@link ProxiedPlayer}.
     */
    public ProxiedPlayer get() {
        return player;
    }
}