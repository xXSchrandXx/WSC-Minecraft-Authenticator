package de.xxschrandxx.wsc.wscauthenticator.bukkit.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import de.xxschrandxx.wsc.wscauthenticator.bukkit.MinecraftAuthenticatorBukkit;
import de.xxschrandxx.wsc.wscauthenticator.core.commands.LogoutCore;
import de.xxschrandxx.wsc.wscbridge.bukkit.api.command.SenderBukkit;

public class LogoutCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        MinecraftAuthenticatorBukkit instance = MinecraftAuthenticatorBukkit.getInstance();
        SenderBukkit sb = new SenderBukkit(sender, instance);
        new LogoutCore(instance).execute(sb, args);
        return true;
    }
}
