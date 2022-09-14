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
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import com.perceus.spellcasting2.BaseSpellCapsule;
import com.perceus.spellcasting2.SpellParticles;

import fish.yukiemeralis.eden.utils.ChatUtils;
import fish.yukiemeralis.eden.utils.PrintUtils;

public class SpellVectorManipulation extends BaseSpellCapsule
{

	public SpellVectorManipulation()
	{
		super(Material.ENCHANTED_BOOK, ChatUtils.of("✩ Astral Tome: Anti Vector ✩", "8B008B","FFFFFF",""), "SpellVectorManipulation", 50, false, "§r§fElement: §r§5A§ds§5t§dr§5α§dl§r§f.",
				"§r§fSpell Type: §bUtility§f.",
				"§r§fA §5c§de§bl§3e§cs§4t§6i§eal §r§ftome.",
				"§r§fThis tome inflates caster vector polarity.",   
				"§r§fCast to block or target to send the caster",
				"§r§fin the opposite direction.",
				"§r§fBlock range: 7 meters. Target range: 10 meters.",
				"§r§fMana cost: 50 §r§9mana§r§f.");
	}

	@Override
	public boolean cast(PlayerInteractEvent event)
	{
		
		if (event.getAction().equals(Action.RIGHT_CLICK_AIR)) 
		{
			Entity target = getNearestEntityInSight(event.getPlayer(), 10);
			
			if (target == null) 
			{
				PrintUtils.sendMessage(event.getPlayer(),"Invalid Target.");
				return false;
			}
			SpellParticles.drawLine(event.getPlayer().getLocation(), target.getLocation(), 1, Particle.DRAGON_BREATH, null);
			SpellParticles.drawDisc(event.getPlayer().getLocation(), 1, 1, 20, Particle.DRAGON_BREATH, null);
			event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_ENCHANTMENT_TABLE_USE, SoundCategory.MASTER, 1, 1);
			event.getPlayer().setVelocity(event.getPlayer().getLocation().toVector().subtract(target.getLocation().toVector()).normalize().multiply(2));
			return true;
		}
		
		if (event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) 
		{
			Block block = event.getPlayer().getTargetBlock(null, 7) ;
			
			if (block == null)
			{
				PrintUtils.sendMessage(event.getPlayer(),"Invalid Target.");
				return false;
			}
			SpellParticles.drawLine(event.getPlayer().getLocation(), block.getLocation(), 1, Particle.DRAGON_BREATH, null);
			SpellParticles.drawDisc(event.getPlayer().getLocation(), 1, 1, 20, Particle.DRAGON_BREATH, null);
			event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_ENCHANTMENT_TABLE_USE, SoundCategory.MASTER, 1, 1);
			event.getPlayer().setVelocity(event.getPlayer().getLocation().toVector().subtract(block.getLocation().toVector()).normalize().multiply(2));
			return true;
		}
		PrintUtils.sendMessage(event.getPlayer(),"Invalid Cast Method.");
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
