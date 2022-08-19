package com.perceus.spellcasting2.holy_spells;

import java.util.ArrayList;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.attribute.Attribute;
import org.bukkit.block.Block;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import com.perceus.spellcasting2.BaseSpellCapsule;
import com.perceus.spellcasting2.SpellParticles;

import fish.yukiemeralis.eden.utils.PrintUtils;

public class SpellJudgement extends BaseSpellCapsule
{

	public SpellJudgement()
	{
		super(Material.END_CRYSTAL, "§r§7§ko§r§7§lSpell: §r§fJudgement§r§7§ko§r", "SpellJudgement", 1000, true, false,"§r§fElement: §r§f§o§lHoly§r§f.","§r§fEnact divine judgement upon any target.","§r§fIf target max health is <= 75%, kill that target.","§r§fIf target max health is > 75%, heal that target to full.", "§r§fRange: 20 meters.","§r§fMana cost: 1000 §r§9mana§r§f.");
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
			PrintUtils.sendMessage(event.getPlayer(),"Invalid Target.");
			return false;
		}
		
		if (target instanceof LivingEntity) 
		{
			//doubles are a variable, similar to `floats`, but dissimilar to a normal integer.
			double healthPercent = ((LivingEntity) target).getHealth() / ((LivingEntity) target).getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue();
			
			if (healthPercent <= 0.75)
			{
		      SpellParticles.drawDisc(event.getPlayer().getLocation(), 1, 1, 20, Particle.CLOUD, null);
			  event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_BELL_USE, SoundCategory.MASTER, 1, 1);
			  target.getWorld().playSound(target.getLocation(), Sound.BLOCK_BELL_USE, SoundCategory.MASTER, 1, 1);
			  ((Damageable) target).damage(84);
			  target.sendMessage("You have been judged.");
			  PrintUtils.sendMessage(event.getPlayer().getDisplayName() + "has been judged.");
			  return true;
			}
			
			if (healthPercent > 0.75)
			{
			  SpellParticles.drawDisc(event.getPlayer().getLocation(), 1, 1, 20, Particle.CLOUD, null);
			  event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_BEACON_ACTIVATE, SoundCategory.MASTER, 1, 1);
			  target.getWorld().playSound(target.getLocation(), Sound.BLOCK_BEACON_ACTIVATE, SoundCategory.MASTER, 1, 1);
			  ((LivingEntity) target).setHealth(((LivingEntity) target).getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue());
			  return true;
			}	
		}
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
