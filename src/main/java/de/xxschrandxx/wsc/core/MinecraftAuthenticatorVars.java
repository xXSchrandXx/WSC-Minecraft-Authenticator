package de.xxschrandxx.wsc.core;

import java.util.Arrays;
import java.util.List;

public class MinecraftAuthenticatorVars {
    public static final class Configuration {
        // url
        public static final String URL = "url";
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
        // teleportation
        // TeleportUnauthed
        public static final String TeleportUnauthedEnabled = "teleport.unauthed.Enabled";
        public static final String TeleportUnauthedLocation = "teleport.unauthed.Location";
        // TeleportAuthenticated
        public static final String TeleportAuthedEnabled = "teleport.authed.Enabled";
        public static final String TeleportAuthedLocation = "teleport.authed.Location";
        public static final class defaults {
            // url
            public static final String URL = "https://example.com/index.php?minecraft-password-check/";
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
            // teleportation
            // TeleportUnauthed
            public static final boolean TeleportUnauthedEnabled = false;
            // TeleportAuthenticated
            public static final boolean TeleportAuthedEnabled = false;
        }
    }
}
