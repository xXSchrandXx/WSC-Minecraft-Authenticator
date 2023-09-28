package de.xxschrandxx.wsc.wscauthenticator.core;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import de.xxschrandxx.wsc.wscbridge.core.api.configuration.AbstractConfiguration;
import de.xxschrandxx.wsc.wscbridge.core.api.configuration.IConfiguration;

public class MinecraftAuthenticatorVars extends AbstractConfiguration {
    public static boolean startConfig(IConfiguration<?> configuration, Logger logger) {
        return startConfig(configuration, Configuration.class, defaults.class, logger);
    }
    public static final class Configuration {
        // universal
        // url
        public static final String url = "url";

        // sessions
        // SessionsEnabled
        public static final String SessionsEnabled = "sessions.Enabled";
        // SessionLength
        public static final String SessionLength = "sessions.Length";

        // language.command.reload.config.start
        public static final String LangCmdReloadConfigStart = "language.command.reload.config.start";
        // language.command.reload.config.error
        public static final String LangCmdReloadConfigError = "language.command.reload.config.error";
        // language.command.reload.config.success
        public static final String LangCmdReloadConfigSuccess = "language.command.reload.config.success";
        // language.command.reload.api.start
        public static final String LangCmdReloadAPIStart = "language.command.reload.api.start";
        // language.command.reload.api.success
        public static final String LangCmdReloadAPISuccess = "language.command.reload.api.success";

        // logincommand
        // LoginCommandOnlyPlayers
        public static final String LoginCommandOnlyPlayers = "language.command.login.OnlyPlayers";
        // LoginCommandUsage
        public static final String LoginCommandUsage = "language.command.login.Usage";
        // LoginCommandSuccess
        public static final String LoginCommandSuccess = "language.command.login.Success";
        // LoginCommandFailure
        public static final String LoginCommandFailure = "language.command.login.Failure";
        // LoginCommandAlreadyIn
        public static final String LoginCommandAlreadyIn = "language.command.login.AlreadyIn";
        // logoutcommand
        // LogoutCommandOnlyPlayers
        public static final String LogoutCommandOnlyPlayers = "language.command.logout.OnlyPlayers";
        // LogoutCommandAlreadyOut
        public static final String LogoutCommandAlreadyOut = "language.command.logout.AlreadyOut";
        // LogoutCommandSuccess
        public static final String LogoutCommandSuccess = "language.command.logout.Success";
        // session
        // LoginViaSession
        public static final String LoginViaSession = "language.command.login.ViaSession";
        // protection
        // AllowMessageReceive
        public static final String AllowMessageReceive = "protection.AllowMessageReceive";
        // AllowMessageSend
        public static final String AllowMessageSend = "protection.AllowMessageSend";
        // AllowMessageSendLocale
        public static final String AllowMessageSendLocale = "language.protection.AllowMessageSend";
        // AllowedCommands
        public static final String AllowedCommands = "protection.AllowedCommands";
        // DenyCommandSendLocale
        public static final String DenyCommandSendLocale = "language.protection.DenyCommandSend";
        // AllowMovement
        public static final String AllowMovement = "protection.AllowMovement";
        // AllowServerSwitch
        public static final String AllowServerSwitch = "protection.AllowServerSwitch";
        // teleportation bukkit only
        // TeleportUnauthed
        public static final String TeleportUnauthedEnabled = "teleport.unauthed.Enabled";
        public static final String TeleportUnauthedLocationWorld = "teleport.unauthed.Location.world";
        public static final String TeleportUnauthedLocationX = "teleport.unauthed.Location.x";
        public static final String TeleportUnauthedLocationY = "teleport.unauthed.Location.y";
        public static final String TeleportUnauthedLocationZ = "teleport.unauthed.Location.z";
        public static final String TeleportUnauthedLocationYaw = "teleport.unauthed.Location.yaw";
        public static final String TeleportUnauthedLocationPitch = "teleport.unauthed.Location.pitch";
        // TeleportAuthenticated
        public static final String TeleportAuthedEnabled = "teleport.authed.Enabled";
        public static final String TeleportAuthedLocationWorld = "teleport.authed.Location.world";
        public static final String TeleportAuthedLocationX = "teleport.authed.Location.x";
        public static final String TeleportAuthedLocationY = "teleport.authed.Location.y";
        public static final String TeleportAuthedLocationZ = "teleport.authed.Location.z";
        public static final String TeleportAuthedLocationYaw = "teleport.authed.Location.yaw";
        public static final String TeleportAuthedLocationPitch = "teleport.authed.Location.pitch";
        // server bungee only
        // AuthenticationServerEnabled
        public static final String AuthenticationServerEnabled = "server.authentication.Enabled";
        // AuthenticationServerName
        public static final String AuthenticationServerName = "server.authentication.Name";
        // LobbyServerEnabled
        public static final String LobbyServerEnabled = "server.lobby.Enabled";
        // LobbyServerList
        public static final String LobbyServerList = "server.lobby.List";
    }
    // Default values
    public static final class defaults {
        // universal
        // url
        public static final String url = "https://example.com/index.php?minecraft-password-check/";
        // key
        public static final String Key = "MySuperSecretKey";
        // sessions
        // SessionsEnabled
        public static final Boolean SessionsEnabled = false;
        // SessionLength
        public static final Integer SessionLength = 300000; // 5 Minutes

        // language.command.reload.config.start
        public static final String LangCmdReloadConfigStart = "&8[&6WSC-Authenticator&8]&7 Reloading configuration.";
        // language.command.reload.config.error
        public static final String LangCmdReloadConfigError = "&8[&6WSC-Authenticator&8]&e Reloading configuration failed.";
        // language.command.reload.config.success
        public static final String LangCmdReloadConfigSuccess = "&8[&6WSC-Authenticator&8]&7 Configuration reloaded successfully.";
        // language.command.reload.api.start
        public static final String LangCmdReloadAPIStart = "&8[&6WSC-Authenticator&8]&7 Reloading API.";
        // language.command.reload.api.success
        public static final String LangCmdReloadAPISuccess = "&8[&6WSC-Authenticator&8]&7 API reloaded successfully.";

        // logincommand
        // LoginCommandOnlyPlayers
        public static final String LoginCommandOnlyPlayers = "You have to be a player.";
        // LoginCommandUsage
        public static final String LoginCommandUsage = "&7Usage: &b/login <Password>";
        // LoginCommandSuccess
        public static final String LoginCommandSuccess = "&aSuccessfully logged in.";
        // LoginCommandFailure
        public static final String LoginCommandFailure = "&cWrong password.";
        // LoginCommandAlreadyIn
        public static final String LoginCommandAlreadyIn = "&cAlready logged in.";
        // logoutcommand
        // LogoutCommandOnlyPlayers
        public static final String LogoutCommandOnlyPlayers = "You have to be a player.";
        // LogoutCommandAlreadyOut
        public static final String LogoutCommandAlreadyOut = "&cYou are not logged in.";
        // LogoutCommandSuccess
        public static final String LogoutCommandSuccess = "&aSuccessfully logged out.";
        // session
        // LoginViaSession
        public static final String LoginViaSession = "&aSuccessfully logged in via session";
        // protection
        // AllowMessageReceive
        public static final boolean AllowMessageReceive = false;
        // AllowMessageSend
        public static final boolean AllowMessageSend = false;
        // AllowMessageSendLocale
        public static final String AllowMessageSendLocale = "&cYou are not allowed to send messages.";
        // AllowedCommands
        public static final List<String> AllowedCommands = Arrays.asList("/login", "/wsclinker");
        // DenyCommandSendLocale
        public static final String DenyCommandSendLocale = "&cYou are not allowed to use commands.";
        // AllowMovement
        public static final boolean AllowMovement = false;
        // AllowServerSwitch
        public static final boolean AllowServerSwitch = false;
        // teleportation bukkit only
        // TeleportUnauthed
        public static final boolean TeleportUnauthedEnabled = false;
        public static final String TeleportUnauthedLocationWorld = "world";
        public static final Double TeleportUnauthedLocationX = 0.0;
        public static final Double TeleportUnauthedLocationY = 0.0;
        public static final Double TeleportUnauthedLocationZ = 0.0;
        public static final Float TeleportUnauthedLocationYaw = Float.valueOf(0);
        public static final Float TeleportUnauthedLocationPitch = Float.valueOf(0);
        // TeleportAuthenticated
        public static final boolean TeleportAuthedEnabled = false;
        public static final String TeleportAuthedLocationWorld = "world";
        public static final Double TeleportAuthedLocationX = 0.0;
        public static final Double TeleportAuthedLocationY = 0.0;
        public static final Double TeleportAuthedLocationZ = 0.0;
        public static final Float TeleportAuthedLocationYaw = Float.valueOf(0);
        public static final Float TeleportAuthedLocationPitch = Float.valueOf(0);
        // server bungee only
        // AuthenticationServerEnabled
        public static final boolean AuthenticationServerEnabled = false;
        // AuthenticationServerName
        public static final String AuthenticationServerName = "lobby";
        // LobbyServerEnabled
        public static final boolean LobbyServerEnabled = false;
        // LobbyServerList
        public static final List<String> LobbyServerList = Arrays.asList("lobby");
    }
}
