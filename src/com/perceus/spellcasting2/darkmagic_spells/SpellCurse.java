package com.perceus.spellcasting2.darkmagic_spells;

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
import org.bukkit.scheduler.BukkitRunnable;

import com.perceus.spellcasting2.BaseSpellCapsule;
import com.perceus.spellcasting2.SpellParticles;

import fish.yukiemeralis.eden.Eden;
import fish.yukiemeralis.eden.utils.PrintUtils;

public class SpellCurse extends BaseSpellCapsule
{

	public SpellCurse()
	{
		super(Material.ENCHANTED_BOOK, "§r§7§ko§r§7§lForbidden Spell: §r§fCurse§r§7§ko§r", "SpellCurse", 0, false, "§r§fElement: §r§4Dark Magic§r§f.","§r§fSpell Type: §cOffensive§f.", "§r§fA tome capable of cursing target player.", "§r§fThe spells effects have a delay that once completed,","§r§fweakens, slows, and fatigues the cursed target.", "§r§fRange: 20 meters.", "§r§fDelay: 30 seconds.","§r§fDuration: 5 minutes.", "§r§fThis spell by-passes absorption.","§r§4Blood Sacrifice§r§f: 5 hearts.");
	}

	@Override
	public boolean cast(PlayerInteractEvent event)
	{
		if (!event.getAction().equals(Action.RIGHT_CLICK_AIR)) {
			PrintUtils.sendMessage(event.getPlayer(), "Invalid Cast Method.");
			return false;
		}
		
		Entity target = getNearestEntityInSight(event.getPlayer(), 20);
		
		if (!(target instanceof Player)) 
		{
			PrintUtils.sendMessage(event.getPlayer(), "Invalid Target.");
			return false;
		}
		
		if (event.getPlayer().hasPotionEffect(PotionEffectType.ABSORPTION)) 
		{
			event.getPlayer().removePotionEffect(PotionEffectType.ABSORPTION);
		}
		
		event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.ENTITY_WITHER_SHOOT, SoundCategory.MASTER, 1, 1);
		((Player) target).playSound(event.getPlayer().getLocation(), Sound.BLOCK_CONDUIT_DEACTIVATE, SoundCategory.MASTER, 1, 1);
		PrintUtils.sendMessage((Player) target, "You have been cursed.");
		SpellParticles.drawDisc(event.getPlayer().getLocation(), 1, 1, 100, Particle.CRIMSON_SPORE, null);
		SpellParticles.drawLine(event.getPlayer().getLocation(), target.getLocation(), 1, Particle.CRIMSON_SPORE, null);
		event.getPlayer().damage(10);
		
		new BukkitRunnable()
		{
		  @Override
		  public void run()
		  {
			  PrintUtils.sendMessage((Player) target, "The curse has taken effect.");
			  ((Player) target).addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 6000, 4));
			  ((Player) target).addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 6000, 4));
			  ((Player) target).addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 6000, 4));
		  }
		}.runTaskLater(Eden.getInstance(), 600);
		
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
