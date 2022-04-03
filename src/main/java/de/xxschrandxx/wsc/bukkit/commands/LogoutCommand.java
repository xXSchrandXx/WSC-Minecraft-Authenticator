package de.xxschrandxx.wsc.bukkit.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.xxschrandxx.wsc.bukkit.MinecraftAuthenticatorBukkit;
import de.xxschrandxx.wsc.bukkit.api.events.LogoutEvent;
import de.xxschrandxx.wsc.core.MinecraftAuthenticatorVars.Configuration;

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
        if (!this.mab.isAuthenticated(player)) {
            player.sendMessage(ChatColor.translateAlternateColorCodes('&',
                this.mab.getConfiguration().getString(Configuration.LogoutCommandAlreadyOut)
            ));
            return true;
        }
        this.mab.setAuthenticated(player.getUniqueId(), false);
        this.mab.getServer().getPluginManager().callEvent(new LogoutEvent(player));
        player.sendMessage(ChatColor.translateAlternateColorCodes('&',
            this.mab.getConfiguration().getString(Configuration.LogoutCommandSuccess)
        ));
        return true;
    }
    
}
