package com.treytonwood.webshooters;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;

public final class WebShootersPlugin extends JavaPlugin implements Listener {
    private final Map<UUID, Long> cooldowns = new HashMap<UUID, Long>();

    @Override
    public void onEnable() {
        saveDefaultConfig();
        getServer().getPluginManager().registerEvents(this, this);
    }

    @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
    public void onPlayerInteract(PlayerInteractEvent event) {
        Action action = event.getAction();
        if (action != Action.RIGHT_CLICK_AIR && action != Action.RIGHT_CLICK_BLOCK) {
            return;
        }

        ItemStack item = event.getItem();
        if (item == null || item.getType() != Material.BOW) {
            return;
        }

        Player player = event.getPlayer();
        long now = System.currentTimeMillis();
        long cooldownUntil = cooldowns.containsKey(player.getUniqueId())
                ? cooldowns.get(player.getUniqueId()) : 0L;
        if (cooldownUntil > now) {
            return;
        }

        cooldowns.put(player.getUniqueId(), now + getConfig().getLong("cooldown-milliseconds"));
        event.setCancelled(true);

        Vector direction = player.getEyeLocation().getDirection().normalize();
        Vector velocity = direction.multiply(getConfig().getDouble("swing-strength"));
        velocity.setY(velocity.getY() + getConfig().getDouble("upward-boost"));
        player.setVelocity(velocity);
        player.setFallDistance(0.0F);
        player.getWorld().playSound(player.getLocation(), arrowShootSound(), 0.8F, 1.8F);
        player.getWorld().playEffect(player.getEyeLocation(), Effect.SMOKE, 0);
    }

    private Sound arrowShootSound() {
        try {
            return Sound.valueOf("ENTITY_ARROW_SHOOT");
        } catch (IllegalArgumentException exception) {
            return Sound.valueOf("SHOOT_ARROW");
        }
    }
}