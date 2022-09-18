package de.xxschrandxx.wsc.wscauthenticator.bungee.api.events;

import de.xxschrandxx.wsc.wscauthenticator.core.api.events.ILoginCoreEvent;
import de.xxschrandxx.wsc.wscbridge.bungee.api.command.SenderBungee;
import net.md_5.bungee.api.plugin.Event;

/**
 * Event called when a player has successfully logged in or registered.
 */
public class LoginEvent extends Event implements ILoginCoreEvent {

    private final SenderBungee sender;

    public LoginEvent(SenderBungee sender) {
        this.sender = sender;
    }

    public SenderBungee get() {
        return sender;
    }
}