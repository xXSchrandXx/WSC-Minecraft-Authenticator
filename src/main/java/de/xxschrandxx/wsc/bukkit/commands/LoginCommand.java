package de.xxschrandxx.wsc.bukkit.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.xxschrandxx.wsc.bukkit.MinecraftAuthenticatorBukkit;
import de.xxschrandxx.wsc.bukkit.api.events.LoginEvent;
import de.xxschrandxx.wsc.core.MinecraftAuthenticatorVars.Configuration;

public class LoginCommand implements CommandExecutor {
    private final MinecraftAuthenticatorBukkit mab;
    public LoginCommand() {
        this.mab = MinecraftAuthenticatorBukkit.getInstance();
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(this.mab.getConfiguration().getString(Configuration.LoginCommandOnlyPlayers));
            return true;
        }
        Player player = (Player) sender;
        if (this.mab.getAPI().isAuthenticated(player)) {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&',
                this.mab.getConfiguration().getString(Configuration.LoginCommandAlreadyIn)
            ));
            return true;
        }
        if (args.length != 1) {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&',
                this.mab.getConfiguration().getString(Configuration.LoginCommandUsage)
            ));
            return true;
        }
        boolean valid = false;
        try {
            valid = this.mab.getAPI().checkPassword(player, args[0]);
            this.mab.getServer().getPluginManager().callEvent(new LoginEvent(player));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        if (valid) {
            this.mab.getAPI().setAuthenticated(player, true);
            if (this.mab.getConfiguration().getBoolean(Configuration.SessionsEnabled)) {
                this.mab.getAPI().removeSession(player);
            }
            player.sendMessage(ChatColor.translateAlternateColorCodes('&',
                this.mab.getConfiguration().getString(Configuration.LoginCommandSuccess)
            ));
        }
        else {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&',
                this.mab.getConfiguration().getString(Configuration.LoginCommandFailure)
            ));
        }
        return true;
    }
    
}
