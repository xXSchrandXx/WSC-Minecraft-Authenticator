Quicklinks: [General](#general) | [API](#api) | [Links](#links) | [License](https://github.com/xXSchrandXx/WSC-Minecraft-Authenticator/blob/main/LICENSE)

"Minecraft"™ is a trademark of Mojang Synergies AB. This Resource ist not affiliate with Mojang.

# General
## Description

## Installation
1. Download latest version from [Spigot/Resources/WSC-Minecraft-Authenticator](https://www.spigotmc.org/resources/wsc-minecraft-authenticator.101169/).
2. Put it into your `plugins` folder.
3. Start your server.
4. Update the configuration in `plugins/wscauthenticator-{bukkit|spigot}`.
5. Restart your server.
6. Configure [WoltLab](#woltlab) plugin Minecraft-Linker.
## Configration
### BungeeCord
```YAML
# URL to your minecraft-password-check action.
url: https://example.com/index.php?minecraft-password-check/
# Key set in Minecraft-Linker configuration
key: MySuperSecretKey
# Protection configuration
protection:
  # Weahter a player can switch the server
  AllowServerSwitch: false
  # Weather a player can send messages
  AllowMessageSend: false
  # List of allowed commands
  AllowedCommands:
  - /login
  # Weather a player can read the messages of other players
  AllowMessageReceive: false
# Serverswitching configuration
server:
  # Forcing player on authenticationserver 
  authentication:
    # Weather this should be enabled
    Enabled: false
    # Name of the server to force the player on
    Name: lobby
  # Forcing player on servers after authentication
  lobby:
    # Weather this should be enabled
    Enabled: false
    # List of servers to force the player on
    # Will try server by server
    List:
    - lobby
# Messages configuration
locale:
  login:
    OnlyPlayers: You have to be a player.
    Usage: '&7Usage: &b/login <Password>'
    Success: '&aSuccessfully logged in.'
    Failure: '&cWrong password.'
    AlreadyIn: '&cAlready logged in.'
  logout:
    OnlyPlayers: You have to be a player.
    AlreadyOut: '&cYou are not logged in.'
    Success: '&aSuccessfully logged out.'
  AllowMessageSend: '&cYou are not allowed to send messages.'
  DenyCommandSend: '&cYou are not allowed to use commands.'
```
### Bukkit/Spigot
```YAML
# URL to your minecraft-password-check action.
url: https://example.com/index.php?minecraft-password-check/
# Key set in Minecraft-Linker configuration
key: MySuperSecretKey
# Protection configuration
protection:
  # Weather a player can read the messages of other players
  AllowMessageReceive: false
  # Weather a player can send messages
  AllowMessageSend: false
  # List of allowed commands
  AllowedCommands:
  - /login
  # Weather the player can move
  AllowMovement: false
# Teleportation configuration
teleport:
  # Teleport unauthenticated location
  unauthed:
    # Weather this should be enabled
    Enabled: false
    # The location to teleport to
    Location:
      ==: org.bukkit.Location
      world: world
      x: 0.0
      y: 0.0
      z: 0.0
      pitch: 0.0
      yaw: 0.0
  # Teleport authenticated location
  authed:
    # Weather this should be enabled
    Enabled: false
    # The location to teleport to
    Location:
      ==: org.bukkit.Location
      world: world
      x: 0.0
      y: 0.0
      z: 0.0
      pitch: 0.0
      yaw: 0.0
# Messages configuration
locale:
  login:
    OnlyPlayers: You have to be a player.
    Usage: '&7Usage: &b/login <Password>'
    Success: '&aSuccessfully logged in.'
    Failure: '&cWrong password.'
    AlreadyIn: '&cAlready logged in.'
  logout:
    OnlyPlayers: You have to be a player.
    AlreadyOut: '&cYou are not logged in.'
    Success: '&aSuccessfully logged out.'
  AllowMessageSend: '&cYou are not allowed to send messages.'
  DenyCommandSend: '&cYou are not allowed to use commands.'
```
# API
## Password check
```JAVA
MinecraftAuthenticator{Bukkit|Bungee} instance = MinecraftAuthenticator{Bukkit|Bungee}.getInstance();
MinecraftAuthenticator{Bukkit|Bungee}API api = instance.getAPI();
```
## Events
You can listen on these events:
* LoginEvent
* LogoutEvent
# Links
## GitHub
* [xXSchrandXx/de.xxschrandxx.wsc.minecraft-api](https://github.com/xXSchrandXx/de.xxschrandxx.wsc.minecraft-api)
* [xXSchrandXx/de.xxschrandxx.wsc.minecraft-linker](https://github.com/xXSchrandXx/de.xxschrandxx.wsc.minecraft-linker)
* [xXSchrandXx/de.xxschrandxx.wsc.minecraft-sync](https://github.com/xXSchrandXx/de.xxschrandxx.wsc.minecraft-sync)
* [xXSchrandXx/de.xxschrandxx.wsc.minecraft-profile](https://github.com/xXSchrandXx/de.xxschrandxx.wsc.minecraft-profile)
* [xXSchrandXx/WSC-Minecraft-Bridge](https://github.com/xXSchrandXx/WSC-Minecraft-Bridge)
* [xXSchrandXx/WSC-Minecraft-Authenticator](https://github.com/xXSchrandXx/WSC-Minecraft-Authenticator)

## WoltLab
* [Plugin-Store/Minecraft-API](https://www.woltlab.com/pluginstore/file/7077-minecraft-api/)
* [Plugin-Store/Minecraft-Linker](https://www.woltlab.com/pluginstore/file/7093-minecraft-linker/)
## Spigot
* [Resources/WSC-Minecraft-Bridge](https://www.spigotmc.org/resources/wsc-minecraft-bridge.100716/)
* [Resources/WSC-Minecraft-Authenticator](https://www.spigotmc.org/resources/wsc-minecraft-authenticator.101169/)
## JavaDocs
* [Docs/wscbridge](https://maven.gamestrike.de/docs/wscbridge/)
* [Docs/wscauthenticator](https://maven.gamestrike.de/docs/wscauthenticator/)
## Maven
```XML
<repository>
	<id>schrand-repo</id>
	<url>https://maven.gamestrike.de/mvn/</url>
</repository>
```