Web Shooters for vanilla Minecraft 1.8.8

This setup uses command blocks and does not require Spigot, Bukkit, mods, or a datapack.

1. Enable cheats in the world.
2. Run this once:

   /scoreboard objectives add webUse stat.useItem.minecraft.fishing_rod
   /gamerule commandBlockOutput false

3. Build a fast redstone clock with four separate outputs, one for each command block. Use only ordinary Impulse command blocks; 1.8.8 does not have chain or repeating command blocks.
4. Put these commands in the four command blocks without the leading slash:

   execute @a[score_webUse_min=1] ~ ~ ~ tp @p ^ ^0.15 ^1.6
   execute @a[score_webUse_min=1] ~ ~ ~ particle smoke ~ ~1.2 ~ 0.2 0.2 0.2 0.04 12
   execute @a[score_webUse_min=1] ~ ~ ~ playsound random.bow @a ~ ~ ~ 0.8 1.8
   scoreboard players set @a[score_webUse_min=1] webUse 0

Power the teleport, particle, and sound blocks before the reset block. Cast a fishing rod at a block and reel it in. The command clock detects the fishing-rod use and launches you forward.
This is a forward burst, not block-anchored rope physics. Vanilla command blocks cannot cancel a fishing-rod cast.