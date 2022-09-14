package com.perceus.spellcasting2.darkmagic_spells;

import java.util.ArrayList;
import java.util.Random;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
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

import com.perceus.spellcasting2.BaseSpellCapsule;
import com.perceus.spellcasting2.SpellParticles;

import fish.yukiemeralis.eden.utils.PrintUtils;

public class SpellWrath extends BaseSpellCapsule
{

	public SpellWrath()
	{
		super(Material.ENCHANTED_BOOK, "§r§7§ko§r§7§lForbidden Spell: §r§fWrath§r§7§ko§r", "SpellWrath", 0, false, "§r§fElement: §r§4Dark Magic§r§f.",
				"§r§fSpell Type: §cOffensive§f.", 
				"§r§fA forbidden spell capable of instantly", 
				"§r§fkilling any target at a 10% chance.",
				"§r§fOtherwise, grant Strength for 30 seconds.", 
				"§r§fThis spell by-passes absorption.", 
				"§r§fRange: 20 meters.", 
				"§r§4Blood Sacrifice§r§f: 5 hearts.");
		
	}
	
	@Override
	public boolean cast(PlayerInteractEvent event)
	{
		if (!event.getAction().equals(Action.RIGHT_CLICK_AIR)) {
			PrintUtils.sendMessage(event.getPlayer(), "Invalid Cast Method.");
			return false;
		}
		
		
		Entity target = getNearestEntityInSight(event.getPlayer(), 20);
		
		if (target == null) 
		{
			PrintUtils.sendMessage(event.getPlayer(), "Invalid Target.");
			return false;
		}
		
		if (!(target instanceof LivingEntity)) 
		{
			PrintUtils.sendMessage(event.getPlayer(), "Invalid Target.");
			return false;
		}
		
		if (target instanceof LivingEntity) 
		{			
			Random random = new Random();
			double randomStored = random.nextDouble();
			if (randomStored <= 0.10) 
			{
				if (event.getPlayer().hasPotionEffect(PotionEffectType.ABSORPTION)) 
				{
					event.getPlayer().removePotionEffect(PotionEffectType.ABSORPTION);
				}
				event.getPlayer().damage(10);
				PrintUtils.sendMessage(event.getPlayer(), "Critical Cast! " + "(" + randomStored +"%)");
				event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.ENTITY_WITHER_AMBIENT, SoundCategory.MASTER, 1, 1);
				SpellParticles.drawDisc(event.getPlayer().getLocation(), 1, 1, 100, Particle.CRIMSON_SPORE, null);
				SpellParticles.drawLine(event.getPlayer().getLocation(), target.getLocation(), 1, Particle.CRIMSON_SPORE, null);
				double health = ((Damageable) target).getHealth();
				((Damageable) target).damage(health);	
				return true;
			}
			if (randomStored > 0.10) 
			{			
				if (event.getPlayer().hasPotionEffect(PotionEffectType.ABSORPTION)) 
				{
					event.getPlayer().removePotionEffect(PotionEffectType.ABSORPTION);
				}
				event.getPlayer().damage(10);
				PrintUtils.sendMessage(event.getPlayer(), "Normal cast at " + randomStored + "% efficiency.");
				event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_ENCHANTMENT_TABLE_USE, SoundCategory.MASTER, 1, 1);
				SpellParticles.drawDisc(event.getPlayer().getLocation(), 1, 1, 100, Particle.SMOKE_LARGE, null);
				SpellParticles.drawLine(event.getPlayer().getLocation(), target.getLocation(), 1, Particle.SMOKE_LARGE, null);
				event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 600, 2));
				return true;
			}
		}
		PrintUtils.sendMessage(event.getPlayer(), "FIZZLE!");
		return false;
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