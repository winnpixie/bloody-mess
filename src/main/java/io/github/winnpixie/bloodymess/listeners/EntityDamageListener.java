package io.github.winnpixie.bloodymess.listeners;

import io.github.winnpixie.bloodymess.BloodyMess;
import io.github.winnpixie.bloodymess.Config;
import io.github.winnpixie.bloodymess.utilities.BloodHelper;
import io.github.winnpixie.commons.spigot.listeners.EventListener;
import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.block.data.BlockData;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

public class EntityDamageListener extends EventListener<BloodyMess> {
    public EntityDamageListener(BloodyMess plugin) {
        super(plugin);

        BloodHelper.init(plugin);
    }

    @EventHandler(priority = EventPriority.MONITOR)
    private void onEntityDamaged(EntityDamageEvent event) {
        if (event.isCancelled()) return;
        if (event.getFinalDamage() <= 0) return;
        if (!(event.getEntity() instanceof LivingEntity)) return;
        LivingEntity victim = (LivingEntity) event.getEntity();

        if (victim instanceof Player) {
            if (!isPlayerTriggerEnabled(event)) return;
        } else if (!isEntityTriggerEnabled(event)) return;

        Location location = event.getCause() == EntityDamageEvent.DamageCause.FALL ? victim.getLocation() : victim.getEyeLocation();
        BlockData blockData = BloodHelper.getBlockForEntity(victim.getType());
        if (blockData == null) return;

        victim.getWorld().spawnParticle(Particle.BLOCK, location, Config.PARTICLE_COUNT,
                Config.SPREAD_X, Config.SPREAD_Y, Config.SPREAD_Z, blockData);
    }

    private Entity getAttacker(EntityDamageByEntityEvent event) {
        Entity attacker = event.getDamager();
        if (attacker instanceof Projectile) {
            Projectile projectile = (Projectile) attacker;
            if (projectile.getShooter() instanceof LivingEntity) attacker = (LivingEntity) projectile.getShooter();
        }

        return attacker;
    }

    private boolean isPlayerTriggerEnabled(EntityDamageEvent event) {
        if (event.getCause() == EntityDamageEvent.DamageCause.FALL) return Config.PLAYER_FALLING;
        if (!(event instanceof EntityDamageByEntityEvent)) return Config.PLAYER_ENVIRONMENT;
        EntityDamageByEntityEvent eveEvent = (EntityDamageByEntityEvent) event;

        return getAttacker(eveEvent) instanceof Player ? Config.PLAYER_ATTACKED_BY_PLAYER : Config.PLAYER_ATTACKED_BY_MOB;
    }

    private boolean isEntityTriggerEnabled(EntityDamageEvent event) {
        if (event.getCause() == EntityDamageEvent.DamageCause.FALL) return Config.ENTITY_FALLING;
        if (!(event instanceof EntityDamageByEntityEvent)) return Config.ENTITY_ENVIRONMENT;
        EntityDamageByEntityEvent eveEvent = (EntityDamageByEntityEvent) event;

        return getAttacker(eveEvent) instanceof Player ? Config.ENTITY_ATTACKED_BY_PLAYER : Config.ENTITY_ATTACKED_BY_ENTITY;
    }
}
