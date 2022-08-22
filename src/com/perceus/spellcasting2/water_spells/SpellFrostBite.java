package com.perceus.spellcasting2.water_spells;

import java.util.ArrayList;

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
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.perceus.spellcasting2.BaseSpellCapsule;
import com.perceus.spellcasting2.SpellParticles;

import fish.yukiemeralis.eden.utils.PrintUtils;

public class SpellFrostBite extends BaseSpellCapsule
{

	public SpellFrostBite()
	{
		super(Material.ENCHANTED_BOOK, "§r§f§ko§r§fTome: §r§fFrostbite§r§f§ko§r", "SpellFrostBite", 50, false, "§r§fElement: §r§9Water§r§f.","§r§fSpell Type: §bUtility§f and §cOffensive§f.","§r§fAn incantation is written within this tome","§r§fthat allows the caster to", "§r§ftemporarily freeze nearby water or any target.","§r§fRange to freeze water: 25 meters.","§r§fRange to freeze target: 20 meters.","§r§fDuration: 7 seconds.","§r§fMana cost: 50 §r§9mana§r§f.");
	}

	@Override
	public boolean cast(PlayerInteractEvent event)
	{
		if (!event.getAction().equals(Action.RIGHT_CLICK_AIR))
		{
			PrintUtils.sendMessage(event.getPlayer(),"Invalid Cast Method.");
			return false;
		}
		
		Entity target = getNearestEntityInSight(event.getPlayer(), 20);
		
		if (target == null) 
		{
			int radius = 25; 
			int radius2 = 1;
			event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.AMBIENT_UNDERWATER_EXIT, SoundCategory.MASTER, 1, 1);
			SpellParticles.drawDisc(event.getPlayer().getLocation(), 2, 2, 20, Particle.WATER_DROP, null);
			for (int iy = (radius2 * -1); iy < (radius2 * 1); iy++)
			{
				for (int ix = (radius * -1); ix < (radius * 1); ix++)
				{
					for (int iz = (radius * -1); iz < (radius * 1); iz++)
					{
						
						Block target1 = event.getPlayer().getWorld().getBlockAt(event.getPlayer().getLocation().add(new Location(event.getPlayer().getWorld(), ix, iy, iz)));
						
						if (target1.getType().equals(Material.WATER))
						{
							target1.setType(Material.FROSTED_ICE);
						}	
					}
				}
			}
			return true;
		}
		
		if (!(target instanceof LivingEntity))
		{
			PrintUtils.sendMessage(event.getPlayer(),"Invalid Target.");
			return false;
		}
		SpellParticles.drawLine(event.getPlayer().getLocation(), target.getLocation(), 1, Particle.DRIP_WATER, null);
		event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.AMBIENT_UNDERWATER_EXIT, SoundCategory.MASTER, 1, 1);
		SpellParticles.drawDisc(event.getPlayer().getLocation(), 2, 2, 20, Particle.WATER_DROP, null);
		((LivingEntity) target).addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 140, 99));
		((LivingEntity) target).addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 140, 99));
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
