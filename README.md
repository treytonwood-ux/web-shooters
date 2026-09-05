# Web Shooters

Minecraft Java Edition resource pack that retextures the bow as a tiny wrist-mounted web-shooter.

## Install

1. Download `web-shooters.zip`.
2. Place it in `.minecraft/resourcepacks`.
3. Enable it from the Minecraft resource pack menu.
4. Hold a bow and right-click to use the web-shooter visuals.

For the forward swing behavior, download `web-shooters-datapack.zip` and place it in the world's `datapacks` folder, then run `/reload`. Aim where you want to move and right-click with the bow. The datapack gives a short forward burst with a 12-tick cooldown.

The datapack is designed for Java Edition 1.20.1. It provides a lightweight swing burst rather than true block-anchored rope physics; that deeper behavior requires a mod or server plugin.

## Spigot 1.8.8

For Minecraft 1.8.8 servers, download `web-shooters-1.8.8.zip`. Put the included JAR in the server's `plugins` folder and restart the server. Use the fishing rod and reel in after the hook lands to pull toward it. The plugin includes configurable cooldown, pull strength, and upward boost values in `config.yml`.

If Spigot is blocked, use the vanilla command-block setup in `vanilla-1.8.8/README.txt` and the `web-shooters-resourcepack-1.8.8.zip` pack. It works in a cheated 1.8.8 world without a server plugin.

## Spigot 1.12.2

For Minecraft 1.12.2 servers, download `web-shooters-1.12.2.zip`. Put the included JAR in the server's `plugins` folder and restart the server. Use the fishing rod and reel in after the hook lands to pull toward it. Use the 1.12.2 resource pack because the 1.8.8 pack metadata is incompatible with 1.12.2.
