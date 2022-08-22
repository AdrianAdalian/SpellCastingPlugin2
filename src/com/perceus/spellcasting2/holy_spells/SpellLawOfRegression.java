package com.perceus.spellcasting2.holy_spells;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.block.Block;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffectType;

import com.perceus.spellcasting2.BaseSpellCapsule;
import com.perceus.spellcasting2.SpellParticles;

import fish.yukiemeralis.eden.utils.PrintUtils;

public class SpellLawOfRegression extends BaseSpellCapsule
{

	public SpellLawOfRegression()
	{
		super(Material.PAPER, "§r§7§ko§r§7§lSpell: §r§fLaw of Regression§r§7§ko§r", "SpellLawOfRegression", 100, true, true,"§r§fElement: §r§f§o§lHoly§r§f.","§r§fSpell Type: §bUtility§f.", "§r§fRoutes all illness and", "§r§fnegative stat changes to self or target player.","§r§fMana cost: 100 §r§9mana§r§f.");
	}

	@Override
	public boolean cast(PlayerInteractEvent event)
	{
		
		if (!event.getAction().equals(Action.RIGHT_CLICK_AIR)) 
		{
			PrintUtils.sendMessage(event.getPlayer(),"Invalid Cast Method.");
			return false;
		}
		
		Entity target = getNearestEntityInSight(event.getPlayer(), 15);
		
		if (target == null) 
		{
			List<PotionEffectType> negEffects = List.of(PotionEffectType.WEAKNESS,
					PotionEffectType.BAD_OMEN,
					PotionEffectType.CONFUSION,
					PotionEffectType.POISON,
					PotionEffectType.SLOW_DIGGING,
					PotionEffectType.WITHER,
					PotionEffectType.BLINDNESS,
					PotionEffectType.DARKNESS,
					PotionEffectType.HUNGER,
					PotionEffectType.UNLUCK,
					PotionEffectType.SLOW);
			
			Player player = event.getPlayer();
			boolean hasPotionEffect = false;
			
			
			
			for(PotionEffectType pet : negEffects)
			{
				if(!player.hasPotionEffect(pet)) 
				{
					continue;
				}
				player.removePotionEffect(pet);
				hasPotionEffect = true;
				
			}
			if(hasPotionEffect == true) 
			{
				SpellParticles.drawDisc(event.getPlayer().getLocation(), 1, 1, 20, Particle.CLOUD, null);
				event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_BEACON_ACTIVATE, SoundCategory.MASTER, 1, 1);
			}
			else
			{
				PrintUtils.sendMessage(event.getPlayer(),"You are not afflicted.");
			}
			return hasPotionEffect;
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
				PotionEffectType.UNLUCK,
				PotionEffectType.SLOW);
		
		boolean hasPotionEffect = false;
		
		if (!(target instanceof Player)) 
		{
			PrintUtils.sendMessage(event.getPlayer(),"Invalid Target.");
			return false;
		}
		
		
		for(PotionEffectType pet : negEffects)
		{
			
			if (target instanceof Player) 
			{
				if(!((Player) target).hasPotionEffect(pet)) 
				{
					continue;
				}
				((Player) target).removePotionEffect(pet);
				hasPotionEffect = true;
			}
			
		}
		
		if(hasPotionEffect == true) 
		{
			SpellParticles.drawLine(event.getPlayer().getLocation(), target.getLocation(), 1, Particle.END_ROD, null);
			PrintUtils.sendMessage((Player)target, event.getPlayer().getDisplayName() + " has cured you.");
			PrintUtils.sendMessage(event.getPlayer(), ((Player)target).getDisplayName() + " has been cured.");
			SpellParticles.drawDisc(event.getPlayer().getLocation(), 1, 1, 20, Particle.CLOUD, null);
			event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_BEACON_ACTIVATE, SoundCategory.MASTER, 1, 1);
		}
		else
		{
			PrintUtils.sendMessage(event.getPlayer(),"That player is not afflicted.");
		}
		return hasPotionEffect;
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
//Make it so that this spell must check for any active potion effects.