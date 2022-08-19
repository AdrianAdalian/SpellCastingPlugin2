package com.perceus.spellcasting2.void_spells;

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

import com.perceus.spellcasting2.BaseSpellCapsule;
import com.perceus.spellcasting2.SpellParticles;

import fish.yukiemeralis.eden.utils.PrintUtils;

public class SpellPolarize extends BaseSpellCapsule
{

	public SpellPolarize()
	{
		super(Material.CYAN_DYE, "§r§7§ko§r§7§lSpell: §r§fPolarize§r§7§ko§r", "SpellPolarize", 200, true, true, "§r§fElement: §r§3§lVOID§r§f.","§r§fSummon an expanding gravitational force upon any target","§r§fthat pushes away other entities around it.","§r§fThe gravitational field causes those affected to be damaged.","§r§fDeals 2 hearts of §r§cdamage§r§f.","§r§fCasting Range: 40 meters.","§r§fField Range: 7 meters.","§r§fMana cost: 200 §r§9mana§r§f.");
	}

	@Override
	public boolean cast(PlayerInteractEvent event)
	{
		if (!event.getAction().equals(Action.RIGHT_CLICK_AIR))
		{
			PrintUtils.sendMessage(event.getPlayer(),"Invalid Cast Method.");
			return false;
		}
		
		Entity InitialTarget = getNearestEntityInSight(event.getPlayer(), 40);
		
		if (InitialTarget == null) 
		{
			PrintUtils.sendMessage(event.getPlayer(),"Invalid Target.");
			return false;
		}
		if (InitialTarget.getNearbyEntities(7, 7, 7).size() == 0)
		{
			PrintUtils.sendMessage(event.getPlayer(),"No Surrounding Targets Detected.");
			return false;
		}
		event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.ENTITY_SHULKER_SHOOT, SoundCategory.MASTER, 1, 1);
		SpellParticles.drawDisc(event.getPlayer().getLocation(), 2, 2, 60, Particle.PORTAL, null);
		for (Entity TargetsOfTarget : InitialTarget.getNearbyEntities(7, 7, 7)) 
		{
			TargetsOfTarget.setVelocity(TargetsOfTarget.getLocation().toVector().subtract(InitialTarget.getLocation().toVector()).normalize().multiply(5));
			if(!(TargetsOfTarget instanceof LivingEntity)) 
			{ 
				continue; 
			}
			((LivingEntity) TargetsOfTarget).damage(4);
			
		}
		
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
