package com.perceus.spellcasting2.holy_spells;

import java.util.ArrayList;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.attribute.Attributable;
import org.bukkit.attribute.Attribute;
import org.bukkit.block.Block;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffectType;

import com.perceus.spellcasting2.BaseSpellCapsule;
import com.perceus.spellcasting2.SpellParticles;

import fish.yukiemeralis.eden.utils.PrintUtils;

public class SpellRemedy extends BaseSpellCapsule
{

	public SpellRemedy()
	{
		super(Material.ENCHANTED_BOOK, "§r§f§ko§r§fTome: Remedy§r§f§ko§r", "SpellRemedy", 100, false,"§r§fElement: §r§f§o§lHoly§r§f.",
				"§r§fSpell Type: §aSupport§f.",
				"§r§fBathe the target in healing light.",
				"§r§aHeals§f player target equal to missing health accrued.",
				"§r§fCures §2Poison§f and §4Wither§f.",
				"§r§fRange: 10 meters.",
				"§r§fMana cost: 100 §r§9mana§r§f.");
	}

	@Override
	public boolean cast(PlayerInteractEvent event)
	{

		Entity target = getNearestPlayerInSight(event.getPlayer(), 10);
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
		
		if (((Player) target).getHealth() == ((Attributable) target).getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue()) 
		{
			PrintUtils.sendMessage(event.getPlayer(),"That player is already at maximum health.");
			return false;
		}
		
		event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_ENCHANTMENT_TABLE_USE, SoundCategory.MASTER, 1, 1);
		SpellParticles.drawLine(event.getPlayer().getLocation(), ((Player) target).getLocation(), 1, Particle.DRAGON_BREATH, null);
		SpellParticles.drawDisc(event.getPlayer().getLocation(), 2, 2, 20, Particle.DRAGON_BREATH, null);
		
		if (((Player) target).hasPotionEffect(PotionEffectType.POISON)) 
		{
			((Player) target).removePotionEffect(PotionEffectType.POISON);
		}
		
		if (((Player) target).hasPotionEffect(PotionEffectType.WITHER)) 
		{
			((Player) target).removePotionEffect(PotionEffectType.WITHER);
		}
		
		double targetCurrentHealth = ((Player) target).getHealth();
		double targetMaxHealth = ((Player) target).getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue();
		
		((Player) target).setHealth(targetCurrentHealth + (targetMaxHealth - targetCurrentHealth));
		
		return true;
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
