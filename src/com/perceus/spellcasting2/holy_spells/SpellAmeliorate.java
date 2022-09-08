package com.perceus.spellcasting2.holy_spells;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.attribute.Attributable;
import org.bukkit.attribute.Attribute;
import org.bukkit.block.Block;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffectType;

import com.perceus.spellcasting2.BaseSpellCapsule;
import com.perceus.spellcasting2.SpellParticles;

import fish.yukiemeralis.eden.utils.PrintUtils;

public class SpellAmeliorate extends BaseSpellCapsule
{

	public SpellAmeliorate()
	{
		super(Material.ENCHANTED_BOOK, "§r§fTome: Ameliorate", "SpellAmeliorate", 500, false, "§r§fElement: §r§f§o§lHoly§r§f.","§r§fSpell Type: §aSupport§f.", "§r§fRoute all illness and", "§r§fnegative stat changes of target player.","§r§fSubsequently §r§aHeal §r§fthem to full.","§r§fRange: 15 meters.","§r§fMana cost: 500 §r§9mana§r§f.");
	}

	@Override
	public boolean cast(PlayerInteractEvent event)
	{
		if (!event.getAction().equals(Action.RIGHT_CLICK_AIR)) 
		{
			PrintUtils.sendMessage(event.getPlayer(),"Invalid Cast Method.");
			return false; 
		}
		
		Entity target = getNearestPlayerInSight(event.getPlayer(), 15);
		
		if (target == null) 
		{
			PrintUtils.sendMessage(event.getPlayer(),"Invalid Target.");
			return false;
		}
		
		if (!(target instanceof Player)) 
		{
			PrintUtils.sendMessage(event.getPlayer(),"Invalid Target.");
			return false;
		}
		
		List<PotionEffectType> negEffects = List.of(PotionEffectType.WEAKNESS,
				PotionEffectType.BAD_OMEN,
				PotionEffectType.CONFUSION,
				PotionEffectType.POISON,
				PotionEffectType.SLOW_DIGGING,
				PotionEffectType.WITHER,
				PotionEffectType.BLINDNESS,
				PotionEffectType.DARKNESS,
				PotionEffectType.HUNGER,
				PotionEffectType.UNLUCK);
		
		boolean hasPotionEffect = false;

		for(PotionEffectType pet : negEffects)
		{
			
			if(!((Player) target).hasPotionEffect(pet)) 
			{
				continue;
			}
			((Player) target).removePotionEffect(pet);
			
			if (((Player) target).getHealth() < ((Attributable) target).getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue()) 
			{
				((Damageable) target).setHealth(event.getPlayer().getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue());
			}
			
			hasPotionEffect = true;
			
		}
		if(hasPotionEffect == true || (!(((Player) target).getHealth() == ((Attributable) target).getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue()))) 
		{
			SpellParticles.drawLine(event.getPlayer().getLocation(), target.getLocation(), 1, Particle.END_ROD, null);
			SpellParticles.drawDisc(event.getPlayer().getLocation(), 1, 1, 20, Particle.CLOUD, null);
			event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_ENCHANTMENT_TABLE_USE, SoundCategory.MASTER, 1, 1);
			((Player) target).playSound(((Player)target).getLocation(), Sound.BLOCK_BEACON_ACTIVATE, SoundCategory.MASTER, 1, 1);
			PrintUtils.sendMessage(((Player) target), "You have been healed of all ailments.");
		}
		else
		{
			PrintUtils.sendMessage(event.getPlayer(),"Target player is not afflicted with any illness or is at full health.");
		}
		
		return hasPotionEffect;
	}
	private Entity getNearestPlayerInSight(Player player, int range) 
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
