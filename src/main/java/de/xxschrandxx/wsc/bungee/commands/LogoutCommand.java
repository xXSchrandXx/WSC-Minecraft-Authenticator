package de.xxschrandxx.wsc.bungee.commands;

import de.xxschrandxx.wsc.bungee.MinecraftAuthenticatorBungee;
import de.xxschrandxx.wsc.bungee.api.events.LogoutEvent;
import de.xxschrandxx.wsc.core.MinecraftAuthenticatorVars.Configuration;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class LogoutCommand extends Command {
    private final MinecraftAuthenticatorBungee mab;
    public LogoutCommand() {
        super("logout");
        this.mab = MinecraftAuthenticatorBungee.getInstance();
    }
    @Override
    public void execute(CommandSender sender, String[] args) {
        if (!(sender instanceof ProxiedPlayer)) {
            sender.sendMessage(new TextComponent(this.mab.getConfiguration().getString(Configuration.LogoutCommandOnlyPlayers)));
            return;
        }
        ProxiedPlayer player = (ProxiedPlayer) sender;
        if (!this.mab.isAuthenticated(player)) {
            player.sendMessage(new TextComponent(ChatColor.translateAlternateColorCodes('&',
                this.mab.getConfiguration().getString(Configuration.LogoutCommandAlreadyOut)
            )));
            return;
        }
        this.mab.setAuthenticated(player.getUniqueId(), false);
        this.mab.getProxy().getPluginManager().callEvent(new LogoutEvent(player));
        player.sendMessage(new TextComponent(ChatColor.translateAlternateColorCodes('&',
            this.mab.getConfiguration().getString(Configuration.LogoutCommandSuccess)
        )));
    }
    
}
