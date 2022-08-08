package de.xxschrandxx.wsc.wscauthenticator.bukkit.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.xxschrandxx.wsc.wscauthenticator.bukkit.MinecraftAuthenticatorBukkit;
import de.xxschrandxx.wsc.wscauthenticator.bukkit.api.events.LogoutEvent;
import de.xxschrandxx.wsc.wscauthenticator.core.MinecraftAuthenticatorVars.Configuration;

public class LogoutCommand implements CommandExecutor {
    private final MinecraftAuthenticatorBukkit mab;
    public LogoutCommand() {
        this.mab = MinecraftAuthenticatorBukkit.getInstance();
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(this.mab.getConfiguration().getString(Configuration.LogoutCommandOnlyPlayers));
            return true;
        }
        Player player = (Player) sender;
        if (!this.mab.getAPI().isAuthenticated(player)) {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&',
                this.mab.getConfiguration().getString(Configuration.LogoutCommandAlreadyOut)
            ));
            return true;
        }
        this.mab.getAPI().setAuthenticated(player, false);
        if (this.mab.getConfiguration().getBoolean(Configuration.SessionsEnabled)) {
            this.mab.getAPI().removeSession(player);
        }
        this.mab.getServer().getPluginManager().callEvent(new LogoutEvent(player));
        player.sendMessage(ChatColor.translateAlternateColorCodes('&',
            this.mab.getConfiguration().getString(Configuration.LogoutCommandSuccess)
        ));
        return true;
    }
    
}
