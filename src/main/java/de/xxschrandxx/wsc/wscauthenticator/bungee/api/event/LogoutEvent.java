package de.xxschrandxx.wsc.wscauthenticator.bungee.api.event;

import de.xxschrandxx.wsc.wscauthenticator.core.api.events.ILogoutCoreEvent;
import de.xxschrandxx.wsc.wscbridge.bungee.api.command.SenderBungee;
import net.md_5.bungee.api.plugin.Event;

/**
 * Event called when a player has successfully logged in or registered.
 */
public class LogoutEvent extends Event implements ILogoutCoreEvent {

    private final SenderBungee sender;

    public LogoutEvent(SenderBungee sender) {
        this.sender = sender;
    }

    public SenderBungee get() {
        return sender;
    }
}