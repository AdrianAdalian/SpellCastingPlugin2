package com.perceus.spellcasting2.darkmagic_spells;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.attribute.Attributable;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.block.Block;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import com.perceus.spellcasting2.BaseSpellCapsule;
import com.perceus.spellcasting2.SpellParticles;

import fish.yukiemeralis.eden.Eden;
import fish.yukiemeralis.eden.utils.PrintUtils;

public class SpellCrimsonVigor extends BaseSpellCapsule
{

	public SpellCrimsonVigor()
	{
		super(Material.ENCHANTED_BOOK, "§r§7§ko§r§7§lForbidden Spell: §r§fCrimson Vigor§r§7§ko§r", "SpellCrimsonVigor", 0, false, "§r§fElement: §r§4Dark Magic§r§f.",
				"§r§fSpell Type: §9Unique§f §bUtility§f.", 
				"§r§fThe caster slices their target's flesh,",
				"§r§fcausing their lifeforce to be drained",
				"§r§fand applied to the caster's health pool.",
				"§r§fDeal §cdamage§f equal to target's maximum health.",
				"§r§fMaximum health added according",
				"§r§fto target's base max health.",
				"§r§fThis spell can only be cast again after the",
				"§r§feffects of the original cast have subsided.",
				"§r§fDuration: 1 minute. Range: 20 meters.");
	}
	
	private static Map<UUID, Integer> playerContainer = new HashMap<>();
	@Override
	public boolean cast(PlayerInteractEvent event)
	{
		if (!event.getAction().equals(Action.RIGHT_CLICK_AIR))
		{
			PrintUtils.sendMessage(event.getPlayer(),"Invalid Cast Method.");
			return false;
		}
		
		Entity target = getNearestEntityInSight(event.getPlayer(), 20);
		
		if (target==null) 
		{
			PrintUtils.sendMessage(event.getPlayer(),"Invalid Target");
			return false;
		}
		
		if (!(target instanceof LivingEntity)) 
		{
			PrintUtils.sendMessage(event.getPlayer(),"Invalid Target");
			return false;
		}
		
		if (playerContainer.containsKey(event.getPlayer().getUniqueId())) 
		{
			PrintUtils.sendMessage(event.getPlayer(),"§r§fYou may not cast this spell until the original spell's effects have subsided.");
			return false;
		}

		playerContainer.put(event.getPlayer().getUniqueId(), 0);
		
		double targetMaxHealth = ((Attributable) target).getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue();
		double targetCurrentHealth = ((Damageable) target).getHealth();
		
		event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_NYLIUM_BREAK, SoundCategory.MASTER, 1, 1);
		SpellParticles.drawDisc(event.getPlayer().getLocation(), 1, 1, 100, Particle.CRIMSON_SPORE, null);
		SpellParticles.drawLine(event.getPlayer().getLocation(), target.getLocation(), 1, Particle.CRIMSON_SPORE, null);
		
		((Damageable) target).damage(targetCurrentHealth);
		
		AttributeInstance instance = event.getPlayer().getAttribute(Attribute.GENERIC_MAX_HEALTH);
		AttributeModifier modifier = new AttributeModifier("spellModifier", targetMaxHealth, AttributeModifier.Operation.ADD_NUMBER);
		instance.addModifier(modifier);
		event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, 100, 4));

		new BukkitRunnable()
		{
			@Override
			public void run()
			{
				if (!event.getPlayer().isOnline()) 
				{
					this.cancel();
					return;
				}
				
				playerContainer.clear();
				instance.removeModifier(modifier);
				event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_BEACON_DEACTIVATE, SoundCategory.MASTER, 1, 1);
				SpellParticles.drawDisc(event.getPlayer().getLocation(), 1, 1, 100, Particle.CRIMSON_SPORE, null);
				PrintUtils.sendMessage(event.getPlayer(),"§r§fSpell: Crimson Vigor's effects have subsided.");
			}
		}.runTaskLater(Eden.getInstance(), 1205);
		
		return true;
	}
	
	private Entity getNearestEntityInSight(Player player, int range) 
	{
		
	    ArrayList<Entity> entities = (ArrayList<Entity>) player.getNearbyEntities(range, range, range);
	    ArrayList<Block> sightBlock = null;
	    try 
	    {
	        sightBlock = (ArrayList<Block>) player.getLineOfSight(null, range);
	    } catch (IllegalStateException error) 
	    {
	        return null;
	    }
	     
	    ArrayList<Location> sight = new ArrayList<Location>();
	    for (int i = 0;i<sightBlock.size();i++)
	        sight.add(sightBlock.get(i).getLocation());
	    for (int i = 0;i<sight.size();i++) {
	        for (int k = 0;k<entities.size();k++) {
	            if (entities.get(k) instanceof LivingEntity && !(entities.get(k) instanceof ArmorStand)) {
	                if (Math.abs(entities.get(k).getLocation().getX()-sight.get(i).getX())<1.3) {
	                    if (Math.abs(entities.get(k).getLocation().getY()-sight.get(i).getY())<1.5) {
	                        if (Math.abs(entities.get(k).getLocation().getZ()-sight.get(i).getZ())<1.3) {
	                                
	                        	return entities.get(k);
	                        	
	                        }
	                    }
	                }
	            }
	        }
	    }
	    	return null; // Return null if no entity was found
	}
}
