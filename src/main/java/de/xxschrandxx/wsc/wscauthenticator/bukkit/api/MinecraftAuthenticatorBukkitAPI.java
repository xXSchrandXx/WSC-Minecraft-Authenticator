package de.xxschrandxx.wsc.wscauthenticator.bukkit.api;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.UnknownServiceException;
import java.util.Date;
import java.util.HashMap;
import java.util.UUID;
import java.util.logging.Logger;

import de.xxschrandxx.wsc.wscauthenticator.bukkit.MinecraftAuthenticatorBukkit;
import de.xxschrandxx.wsc.wscauthenticator.bukkit.api.events.LoginEvent;
import de.xxschrandxx.wsc.wscauthenticator.bukkit.api.events.LogoutEvent;
import de.xxschrandxx.wsc.wscauthenticator.core.MinecraftAuthenticatorVars;
import de.xxschrandxx.wsc.wscauthenticator.core.api.IMinecraftAuthenticatorCoreAPI;
import de.xxschrandxx.wsc.wscauthenticator.core.api.MinecraftAuthenticatorCoreAPI;
import de.xxschrandxx.wsc.wscauthenticator.core.api.SessionData;
import de.xxschrandxx.wsc.wscbridge.bukkit.api.MinecraftBridgeBukkitAPI;
import de.xxschrandxx.wsc.wscbridge.bukkit.api.command.SenderBukkit;
import de.xxschrandxx.wsc.wscbridge.core.api.MinecraftBridgeCoreAPI;
import de.xxschrandxx.wsc.wscbridge.core.api.Response;
import de.xxschrandxx.wsc.wscbridge.core.api.command.ISender;

public class MinecraftAuthenticatorBukkitAPI extends MinecraftBridgeBukkitAPI implements IMinecraftAuthenticatorCoreAPI {

    protected final URL url;

    public MinecraftAuthenticatorBukkitAPI(URL url, Logger logger, MinecraftBridgeCoreAPI api) {
        super(api.getID(), api.getAuth(), logger, api.isDebugModeEnabled());
        this.url = url;
    }

    public Response<String, Object> checkPassword(ISender<?> sender, String password) throws SocketTimeoutException, MalformedURLException, UnknownServiceException, IOException {
        return MinecraftAuthenticatorCoreAPI.checkPassword(this, url, sender.getUniqueId(), password);
    }

    // start session part
    protected HashMap<UUID, SessionData> sessions = new HashMap<UUID, SessionData>();

    public Boolean hasOpenSession(ISender<?> sender, String address) {
        MinecraftAuthenticatorBukkit instance = MinecraftAuthenticatorBukkit.getInstance();
        if (!instance.getConfiguration().getBoolean(MinecraftAuthenticatorVars.Configuration.SessionsEnabled)) {
            return false;
        
        }
        SessionData data = this.sessions.get(sender.getUniqueId());
        if (data == null) {
            return false;
        }
        if (!address.equals(data.getAddress())) {
            return false;
        }
        if (data.getEnd().after(new Date())) {
            return true;
        }
        else {
            return false;
        }
    }

    public SessionData removeSession(ISender<?> sender) {
        return this.sessions.remove(sender.getUniqueId());
    }

    public SessionData addSession(ISender<?> sender, String address) {
        MinecraftAuthenticatorBukkit instance = MinecraftAuthenticatorBukkit.getInstance();
        return this.sessions.put(sender.getUniqueId(), new SessionData(address, instance.getConfiguration().getLong(MinecraftAuthenticatorVars.Configuration.SessionLength)));
    }
    // end session part

    // start of authentication part
    public void callLoginEvent(ISender<?> sender) {
        MinecraftAuthenticatorBukkit instance = MinecraftAuthenticatorBukkit.getInstance();
        instance.getServer().getPluginManager().callEvent(new LoginEvent((SenderBukkit) sender));
    }

    public void callLogoutEvent(ISender<?> sender) {
        MinecraftAuthenticatorBukkit instance = MinecraftAuthenticatorBukkit.getInstance();
        instance.getServer().getPluginManager().callEvent(new LogoutEvent((SenderBukkit) sender));
    }

    protected HashMap<UUID, Boolean> authenticated = new HashMap<UUID, Boolean>();

    public Boolean isAuthenticated(ISender<?> sender) {
        return this.authenticated.getOrDefault(sender.getUniqueId(), false);
    }

    public Boolean setAuthenticated(ISender<?> sender, Boolean authenticated) {
        return this.authenticated.put(sender.getUniqueId(), authenticated);
    }
    // end of authentication part
}
