package de.xxschrandxx.wsc.wscauthenticator.bungee.commands;

import de.xxschrandxx.wsc.wscauthenticator.bungee.MinecraftAuthenticatorBungee;
import de.xxschrandxx.wsc.wscauthenticator.core.commands.LogoutCore;
import de.xxschrandxx.wsc.wscbridge.bungee.api.command.SenderBungee;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.plugin.Command;

public class LogoutCommand extends Command {
    public LogoutCommand(String name) {
        super(name);
    }
    @Override
    public void execute(CommandSender sender, String[] args) {
        MinecraftAuthenticatorBungee instance = MinecraftAuthenticatorBungee.getInstance();
        SenderBungee sb = new SenderBungee(sender, instance);
        new LogoutCore(instance).execute(sb, args);
    }
}
