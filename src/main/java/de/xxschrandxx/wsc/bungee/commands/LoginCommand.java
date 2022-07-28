package de.xxschrandxx.wsc.bungee.commands;

import de.xxschrandxx.wsc.bungee.MinecraftAuthenticatorBungee;
import de.xxschrandxx.wsc.bungee.api.events.LoginEvent;
import de.xxschrandxx.wsc.core.MinecraftAuthenticatorVars.Configuration;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class LoginCommand extends Command {
    private final MinecraftAuthenticatorBungee mab;
    public LoginCommand() {
        super("login");
        this.mab = MinecraftAuthenticatorBungee.getInstance();
    }
    @Override
    public void execute(CommandSender sender, String[] args) {
        if (!(sender instanceof ProxiedPlayer)) {
            sender.sendMessage(new TextComponent(this.mab.getConfiguration().getString(Configuration.LoginCommandOnlyPlayers)));
            return;
        }
        ProxiedPlayer player = (ProxiedPlayer) sender;
        if (this.mab.getAPI().isAuthenticated(player)) {
            player.sendMessage(new TextComponent(ChatColor.translateAlternateColorCodes('&',
                this.mab.getConfiguration().getString(Configuration.LoginCommandAlreadyIn)
            )));
            return;
        }
        if (args.length != 1) {
            player.sendMessage(new TextComponent(ChatColor.translateAlternateColorCodes('&',
                this.mab.getConfiguration().getString(Configuration.LoginCommandUsage)
            )));
            return;
        }
        boolean valid = false;
        try {
            valid = this.mab.getAPI().checkPassword(player, args[0]);
            this.mab.getProxy().getPluginManager().callEvent(new LoginEvent(player));
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        if (valid) {
            this.mab.getAPI().setAuthenticated(player, true);
            if (this.mab.getConfiguration().getBoolean(Configuration.SessionsEnabled)) {
                this.mab.getAPI().removeSession(player);
            }
            player.sendMessage(new TextComponent(ChatColor.translateAlternateColorCodes('&',
                this.mab.getConfiguration().getString(Configuration.LoginCommandSuccess)
            )));
        }
        else {
            player.sendMessage(new TextComponent(ChatColor.translateAlternateColorCodes('&',
                this.mab.getConfiguration().getString(Configuration.LoginCommandFailure)
            )));
        }
    }
    
}
