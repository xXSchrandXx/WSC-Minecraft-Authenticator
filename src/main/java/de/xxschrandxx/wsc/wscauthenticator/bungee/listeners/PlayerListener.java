package de.xxschrandxx.wsc.wscauthenticator.bungee.listeners;

import de.xxschrandxx.wsc.wscauthenticator.bungee.MinecraftAuthenticatorBungee;
import de.xxschrandxx.wsc.wscauthenticator.core.MinecraftAuthenticatorVars.Configuration;
import de.xxschrandxx.wsc.wscbridge.bungee.api.command.SenderBungee;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.ChatEvent;
import net.md_5.bungee.api.event.ServerConnectEvent;
import net.md_5.bungee.api.event.ServerConnectEvent.Reason;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;
import net.md_5.bungee.event.EventPriority;

public class PlayerListener implements Listener {
    private final MinecraftAuthenticatorBungee mab;
    public PlayerListener() {
        this.mab = MinecraftAuthenticatorBungee.getInstance();
    }
    @EventHandler(priority = EventPriority.LOW)
    public void onServerSwitch(ServerConnectEvent event) {
      if (event.isCancelled()) {
        return;
      }
      if (this.mab.getConfiguration().getBoolean(Configuration.AllowServerSwitch)) {
          return;
      }
      if (event.getReason() == Reason.JOIN_PROXY) {
        return;
      }
      if (event.getReason() == Reason.SERVER_DOWN_REDIRECT) {
        return;
      }
      if (event.getReason() == Reason.LOBBY_FALLBACK) {
        return;
      }
      SenderBungee sender = new SenderBungee(event.getPlayer(), mab);
      if (this.mab.getAPI().isAuthenticated(sender)) {
        return;
      }
      event.setCancelled(true);
    }
  
    @EventHandler
    public void onChatSendEvent(ChatEvent event) {
      if (event.isCancelled()) {
        return;
      }
      if (!(event.getSender() instanceof ProxiedPlayer)) {
        return;
      }
      if (event.isCommand() || event.isProxyCommand()) {
        return;
      }
      if (this.mab.getConfiguration().getBoolean(Configuration.AllowMessageSend)) {
          return;
      }
      ProxiedPlayer player = (ProxiedPlayer) event.getSender();
      SenderBungee sender = new SenderBungee(player, mab);
      if (this.mab.getAPI().isAuthenticated(sender)) {
          return;
      }
      player.sendMessage(new TextComponent(ChatColor.translateAlternateColorCodes('&',
        this.mab.getConfiguration().getString(Configuration.AllowMessageSendLocale)
      )));
      event.setCancelled(true);
    }
  
    @EventHandler
    public void onCommandSendEvent(ChatEvent event) {
      if (event.isCancelled()) {
        return;
      }
      if (!(event.getSender() instanceof ProxiedPlayer)) {
        return;
      }
      if (!event.isCommand() || !event.isProxyCommand()) {
        return;
      }
      String command = event.getMessage().split(" ")[0];
      if (this.mab.getConfiguration().getStringList(Configuration.AllowedCommands).contains(command)) {
          return;
      }
      ProxiedPlayer player = (ProxiedPlayer) event.getSender();
      SenderBungee sender = new SenderBungee(player, mab);
      if (this.mab.getAPI().isAuthenticated(sender)) {
          return;
      }
      player.sendMessage(new TextComponent(ChatColor.translateAlternateColorCodes('&',
        this.mab.getConfiguration().getString(Configuration.DenyCommandSendLocale)
      )));
      event.setCancelled(true);
    }
  
    @EventHandler
    public void onMessageReEvent(ChatEvent event) {
      if (event.isCancelled()) {
        return;
      }
      if (!(event.getReceiver() instanceof ProxiedPlayer)) {
        return;
      }
      if (event.isCommand() || event.isProxyCommand()) {
        return;
      }
      if (this.mab.getConfiguration().getBoolean(Configuration.AllowMessageReceive)) {
          return;
      }
      SenderBungee sender = new SenderBungee((ProxiedPlayer) event.getReceiver(), mab);
      if (this.mab.getAPI().isAuthenticated(sender))
      event.setCancelled(true);
    }
}
