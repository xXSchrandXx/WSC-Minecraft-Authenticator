package de.xxschrandxx.wsc.wscauthenticator.core.commands;

import de.xxschrandxx.wsc.wscauthenticator.core.MinecraftAuthenticatorVars;
import de.xxschrandxx.wsc.wscauthenticator.core.api.IMinecraftAuthenticatorCoreAPI;
import de.xxschrandxx.wsc.wscbridge.core.IBridgePlugin;
import de.xxschrandxx.wsc.wscbridge.core.api.command.ISender;

public class LogoutCore {
    private IBridgePlugin<? extends IMinecraftAuthenticatorCoreAPI> instance;
    public LogoutCore(IBridgePlugin<? extends IMinecraftAuthenticatorCoreAPI> instance) {
        this.instance = instance;
    }
    public void execute(ISender<?> sender, String[] args) {
        if (!sender.isPlayer()) {
            sender.send(MinecraftAuthenticatorVars.Configuration.LogoutCommandOnlyPlayers);
            return;
        }
        if (!instance.getAPI().isAuthenticated(sender)) {
            sender.send(MinecraftAuthenticatorVars.Configuration.LogoutCommandAlreadyOut);
            return;
        }
        instance.getAPI().setAuthenticated(sender, false);
        if (instance.getConfiguration().getBoolean(MinecraftAuthenticatorVars.Configuration.SessionsEnabled)) {
            instance.getAPI().removeSession(sender);
        }
        instance.getAPI().callLogoutEvent(sender);
        sender.send(MinecraftAuthenticatorVars.Configuration.LogoutCommandSuccess);
    }
}
