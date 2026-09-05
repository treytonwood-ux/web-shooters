package com.treytonwood.webshooters;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Effect;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;
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
    public void onPlayerFish(PlayerFishEvent event) {
        if (event.getState() != PlayerFishEvent.State.IN_GROUND) {
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

        Vector pullDirection = event.getHook().getLocation().toVector()
            .subtract(player.getLocation().toVector()).normalize();
        Vector velocity = pullDirection.multiply(getConfig().getDouble("pull-strength"));
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