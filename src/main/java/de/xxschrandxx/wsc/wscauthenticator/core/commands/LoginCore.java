package de.xxschrandxx.wsc.wscauthenticator.core.commands;

import java.io.IOException;

import de.xxschrandxx.wsc.wscauthenticator.core.MinecraftAuthenticatorVars;
import de.xxschrandxx.wsc.wscauthenticator.core.api.IMinecraftAuthenticatorCoreAPI;
import de.xxschrandxx.wsc.wscbridge.core.IMinecraftBridgePlugin;
import de.xxschrandxx.wsc.wscbridge.core.api.Response;
import de.xxschrandxx.wsc.wscbridge.core.api.command.ISender;

public class LoginCore {
    private IMinecraftBridgePlugin<? extends IMinecraftAuthenticatorCoreAPI> instance;
    public LoginCore(IMinecraftBridgePlugin<? extends IMinecraftAuthenticatorCoreAPI> instance) {
        this.instance = instance;
    }
    public void execute(ISender<?> sender, String[] args) {
        if (!sender.isPlayer()) {
            sender.send(MinecraftAuthenticatorVars.Configuration.LoginCommandOnlyPlayers);
            return;
        }
        if (instance.getAPI().isAuthenticated(sender)) {
            sender.send(MinecraftAuthenticatorVars.Configuration.LoginCommandAlreadyIn);
            return;
        }
        if (args.length != 1) {
            sender.send(MinecraftAuthenticatorVars.Configuration.LoginCommandUsage);
            return;
        }
        boolean valid = false;
        Response<String, Object> response;
        try {
            response = instance.getAPI().checkPassword(sender, args[0]);
            if (!response.getResponse().containsKey("valid")) {
                if (instance.getAPI().isDebugModeEnabled()) {
                    instance.getAPI().log("Response does not contain key 'valid'.");
                }
            }
            else if (!(response.get("valid") instanceof Boolean)) {
                if (instance.getAPI().isDebugModeEnabled()) {
                    instance.getAPI().log("Wrong password");
                }
            }
            else if ((Boolean) response.get("valid")) {
                if (instance.getAPI().isDebugModeEnabled()) {
                    instance.getAPI().log("Right password");
                }
                valid = true;
            }
        } catch (IOException e) {
            if (instance.getAPI().isDebugModeEnabled()) {
                instance.getAPI().log("Error while checking password.", e);
            }
            valid = false;
        }
        if (valid) {
            instance.getAPI().setAuthenticated(sender, true);
            if (instance.getConfiguration().getBoolean(MinecraftAuthenticatorVars.Configuration.SessionsEnabled)) {
                instance.getAPI().removeSession(sender);
            }
            sender.send(MinecraftAuthenticatorVars.Configuration.LoginCommandSuccess);
            instance.getAPI().callLoginEvent(sender);
        }
        else {
            sender.send(MinecraftAuthenticatorVars.Configuration.LoginCommandFailure);
        }
    }
}
