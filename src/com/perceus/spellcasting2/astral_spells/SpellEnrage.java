package com.perceus.spellcasting2.astral_spells;

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
import org.bukkit.entity.Mob;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import com.perceus.spellcasting2.BaseSpellCapsule;
import com.perceus.spellcasting2.SpellParticles;

import fish.yukiemeralis.eden.utils.ChatUtils;
import fish.yukiemeralis.eden.utils.PrintUtils;

public class SpellEnrage extends BaseSpellCapsule
{

	public SpellEnrage()
	{
		super(Material.ENCHANTED_BOOK, ChatUtils.of("✩ Astral Tome: Enrage ✩", "8B008B","FFFFFF",""), "SpellEnrage", 50, false, "§r§fElement: §r§5A§ds§5t§dr§5α§dl§r§f.",
				"§r§fSpell Type: §bUtility§f and §dAOE§f.",
				"§r§fA §5c§de§bl§3e§cs§4t§6i§eal §r§ftome.",
				"§r§fThis tome alerts and overrides target agro.", 
				"§r§fSets target agro to the caster.",  
				"§r§fThe spell will ignore player targets.",
				"§r§fRange: 40 meters.",
				"§r§fMana cost: 50 §r§9mana§r§f.");
	}

	@Override
	public boolean cast(PlayerInteractEvent event)
	{
		if (!event.getAction().equals(Action.RIGHT_CLICK_AIR)) 
		{
			PrintUtils.sendMessage(event.getPlayer(),"Invalid Cast Method.");
			return false; 
		}
		
		Entity target = getNearestPlayerInSight(event.getPlayer(), 30);
		if (target == null) 
		{
			if (event.getPlayer().getNearbyEntities(40, 40, 40).size() == 0)
			{
				PrintUtils.sendMessage(event.getPlayer(),"No targets found.");
				return false;
			}
			
			event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_ENCHANTMENT_TABLE_USE, SoundCategory.MASTER, 1, 1);
			SpellParticles.drawDisc(event.getPlayer().getLocation(), 2, 2, 20, Particle.DRAGON_BREATH, null);			
			
			for (Entity target2 : event.getPlayer().getNearbyEntities(40, 40, 40))
			{
				if (target2 instanceof Player) 
				{
					continue;
				}
				if (target2 instanceof Mob) 
				{	
					((Mob) target2).setTarget(event.getPlayer());
					SpellParticles.drawDisc(target2.getLocation(), 2, 2, 200, Particle.CRIMSON_SPORE, null);
					SpellParticles.drawLine(event.getPlayer().getLocation(), target2.getLocation(), 1, Particle.DRAGON_BREATH, null);
				}
			}
			return true;
		}
		
		if (target instanceof Player) 
		{
			PrintUtils.sendMessage(event.getPlayer(),"Invalid Target.");
			return false;
		}
		
		if (target instanceof Mob)
		{			
			event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_ENCHANTMENT_TABLE_USE, SoundCategory.MASTER, 1, 1);
			SpellParticles.drawLine(event.getPlayer().getLocation(), target.getLocation(), 1, Particle.DRAGON_BREATH, null);
			SpellParticles.drawDisc(event.getPlayer().getLocation(), 2, 2, 20, Particle.DRAGON_BREATH, null);
			SpellParticles.drawDisc(target.getLocation(), 2, 2, 200, Particle.CRIMSON_SPORE, null);
			((Mob) target).setTarget(event.getPlayer());
			return true;
		}
		
		return false;
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
