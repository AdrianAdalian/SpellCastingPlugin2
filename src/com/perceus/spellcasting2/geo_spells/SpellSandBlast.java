package com.perceus.spellcasting2.geo_spells;

import java.util.ArrayList;

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

public class SpellSandBlast extends BaseSpellCapsule
{

	public SpellSandBlast()
	{
		super(Material.COCOA_BEANS, "§r§7§ko§r§7§lSpell: §r§fSand Blast§r§7§ko§r", "SpellSandBlast", 60, true, true, "§r§fElement: §r§6Geo§r§f.","§r§fSpell Type: §cOffensive§f.","§r§fExpell a blast of sand at a target,","§r§fbriefly blinding and slowing them.","§r§fAlso deals 1 heart of §r§cdamage§r§f.","§r§fDuration: 10 seconds.","§r§fRange: 10 meters.","§r§fMana cost: 60 §r§9mana§r§f.");
	}

	@Override
	public boolean cast(PlayerInteractEvent event)
	{
		if (!event.getAction().equals(Action.RIGHT_CLICK_AIR))
		{
			PrintUtils.sendMessage(event.getPlayer(),"Invalid Cast Method.");
			return false;
		}
		
		Entity target = getNearestEntityInSight(event.getPlayer(), 10);
		
		if (target == null) 
		{
			PrintUtils.sendMessage(event.getPlayer(),"Invalid Target.");
			return false;
		}
		
		SpellParticles.drawDisc(event.getPlayer().getLocation(), 1, 1, 20, Particle.CAMPFIRE_COSY_SMOKE, null);
		event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_SAND_BREAK, SoundCategory.MASTER, 1, 1);
		if (target instanceof LivingEntity)
		{
			SpellParticles.drawLine(event.getPlayer().getLocation(), target.getLocation(), 1, Particle.CAMPFIRE_COSY_SMOKE, null);
			((LivingEntity) target).addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 200, 0, true));
			((LivingEntity) target).addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 200, 0, true));
			((Damageable) target).damage(2);
			return true;
		}
		PrintUtils.sendMessage(event.getPlayer(),"FIZZLE!");
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
