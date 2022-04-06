package de.xxschrandxx.wsc.core;

import java.util.Arrays;
import java.util.List;

public class MinecraftAuthenticatorVars {
    public static final class Configuration {
        // universal
        // url
        public static final String URL = "url";
        // key
        public static final String Key = "key";
        // logincommand
        // LoginCommandOnlyPlayers
        public static final String LoginCommandOnlyPlayers = "locale.login.OnlyPlayers";
        // LoginCommandUsage
        public static final String LoginCommandUsage = "locale.login.Usage";
        // LoginCommandSuccess
        public static final String LoginCommandSuccess = "locale.login.Success";
        // LoginCommandFailure
        public static final String LoginCommandFailure = "locale.login.Failure";
        // LoginCommandAlreadyIn
        public static final String LoginCommandAlreadyIn = "locale.login.AlreadyIn";
        // logoutcommand
        // LogoutCommandOnlyPlayers
        public static final String LogoutCommandOnlyPlayers = "locale.logout.OnlyPlayers";
        // LogoutCommandAlreadyOut
        public static final String LogoutCommandAlreadyOut = "locale.logout.AlreadyOut";
        // LogoutCommandSuccess
        public static final String LogoutCommandSuccess = "locale.logout.Success";
        // protection
        // AllowMessageReceive
        public static final String AllowMessageReceive = "protection.AllowMessageReceive";
        // AllowMessageSend
        public static final String AllowMessageSend = "protection.AllowMessageSend";
        // AllowMessageSendLocale
        public static final String AllowMessageSendLocale = "locale.AllowMessageSend";
        // AllowedCommands
        public static final String AllowedCommands = "protection.AllowedCommands";
        // DenyCommandSendLocale
        public static final String DenyCommandSendLocale = "locale.DenyCommandSend";
        // AllowMovement
        public static final String AllowMovement = "protection.AllowMovement";
        // AllowServerSwitch
        public static final String AllowServerSwitch = "protection.AllowServerSwitch";
        // teleportation bukkit only
        // TeleportUnauthed
        public static final String TeleportUnauthedEnabled = "teleport.unauthed.Enabled";
        public static final String TeleportUnauthedLocation = "teleport.unauthed.Location";
        // TeleportAuthenticated
        public static final String TeleportAuthedEnabled = "teleport.authed.Enabled";
        // server bungee only
        public static final String TeleportAuthedLocation = "teleport.authed.Location";
        // AuthenticationServerEnabled
        public static final String AuthenticationServerEnabled = "server.authentication.Enabled";
        // AuthenticationServerName
        public static final String AuthenticationServerName = "server.authentication.Name";
        // LobbyServerEnabled
        public static final String LobbyServerEnabled = "server.lobby.Enabled";
        // LobbyServerList
        public static final String LobbyServerList = "server.lobby.List";

        // Default values
        public static final class defaults {
            // universal
            // url
            public static final String URL = "https://example.com/index.php?minecraft-password-check/";
            // key
            public static final String Key = "MySuperSecretKey";
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
            // protection
            // AllowMessageReceive
            public static final boolean AllowMessageReceive = false;
            // AllowMessageSend
            public static final boolean AllowMessageSend = false;
            // AllowMessageSendLocale
            public static final String AllowMessageSendLocale = "&cYou are not allowed to send messages.";
            // AllowedCommands
            public static final List<String> AllowedCommands = Arrays.asList("/login");
            // DenyCommandSendLocale
            public static final String DenyCommandSendLocale = "&cYou are not allowed to use commands.";
            // AllowMovement
            public static final boolean AllowMovement = false;
            // AllowServerSwitch
            public static final boolean AllowServerSwitch = false;
            // teleportation bukkit only
            // TeleportUnauthed
            public static final boolean TeleportUnauthedEnabled = false;
            // TeleportAuthenticated
            public static final boolean TeleportAuthedEnabled = false;
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
}
