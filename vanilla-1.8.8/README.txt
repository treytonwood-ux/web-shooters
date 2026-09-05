Web Shooters for vanilla Minecraft 1.8.8

This setup uses command blocks and does not require Spigot, Bukkit, mods, or a datapack.

1. Enable cheats in the world.
2. Run this once:

   /scoreboard objectives add webUse stat.useItem.minecraft.bow
   /gamerule commandBlockOutput false

3. Build a fast redstone clock connected to four command blocks in this order.
4. Set the first command block to Impulse or Repeat, and the remaining three to Chain.
5. Put these commands in the command blocks without the leading slash:

   execute @a[score_webUse_min=1] ~ ~ ~ tp @p ^ ^0.15 ^1.6
   execute @a[score_webUse_min=1] ~ ~ ~ particle smoke ~ ~1.2 ~ 0.2 0.2 0.2 0.04 12
   execute @a[score_webUse_min=1] ~ ~ ~ playsound random.bow @a ~ ~ ~ 0.8 1.8
   scoreboard players set @a[score_webUse_min=1] webUse 0

Hold a bow and release an arrow. The command clock detects the bow use and launches you forward.
This is a forward burst, not block-anchored rope physics. The bow will still fire arrows because vanilla command blocks cannot cancel the bow action.