package de.xxschrandxx.wsc.wscauthenticator.bukkit.api.events;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import de.xxschrandxx.wsc.wscauthenticator.core.api.events.ILoginCoreEvent;
import de.xxschrandxx.wsc.wscbridge.bukkit.api.command.SenderBukkit;

/**
 * Event called when a player has successfully logged in or registered.
 */
public class LoginEvent extends Event implements ILoginCoreEvent {

    private final SenderBukkit sender;

    public LoginEvent(SenderBukkit sender) {
        this.sender = sender;
    }

    public SenderBukkit get() {
        return sender;
    }

    private static final HandlerList handlers = new HandlerList();

    /**
     * Return the list of handlers, equivalent to {@link #getHandlers()} and
     * required by {@link Event}.
     *
     * @return The list of handlers
     */
    public static HandlerList getHandlerList() {
        return handlers;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

}