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
import org.bukkit.scheduler.BukkitRunnable;

import com.perceus.spellcasting2.BaseSpellCapsule;
import com.perceus.spellcasting2.SpellParticles;

import fish.yukiemeralis.eden.Eden;
import fish.yukiemeralis.eden.utils.PrintUtils;

public class SpellTelekinesis extends BaseSpellCapsule
{

	public SpellTelekinesis()
	{
		super(Material.ENCHANTED_BOOK, "§r§f§ko§r§fTome: §r§fTelekinesis§r§f§ko§r", "SpellTelekinesis", 150, false, "§r§fElement: §r§3§lVOID§r§f.","§r§fAn incantation is written within that allows","§r§fthe caster to teleport any target 15 meters in the air.","§r§fDeals 0-5 hearts of §r§cdamage§r§f.","§r§fRange: 20 meters.","§r§fMana cost: 150 §r§9mana§r§f.");
		// TODO Auto-generated constructor stub
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
		
		if (target instanceof LivingEntity) 
		{
			float entityYAW = target.getLocation().getYaw();
			float entityPITCH = target.getLocation().getPitch();
			
			Location newLoc = target.getLocation().add(new Location(target.getWorld(), 0.5, 16, 0.5));

			target.getLocation().setPitch(entityPITCH);
			target.getLocation().setYaw(entityYAW);
			
			event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.ENTITY_SHULKER_SHOOT, SoundCategory.MASTER, 1, 1);
			SpellParticles.drawDisc(event.getPlayer().getLocation(), 2, 2, 60, Particle.PORTAL, null);
			SpellParticles.drawDisc(target.getLocation(), 2, 2, 60, Particle.PORTAL, null);
			target.getWorld().spawnParticle(Particle.EXPLOSION_LARGE, target.getLocation(), 1);
			target.teleport(newLoc);
			new BukkitRunnable()
			{
				 @Override
				  public void run()
				  {	
					 	target.getWorld().spawnParticle(Particle.EXPLOSION_LARGE, target.getLocation(), 1);
					 	SpellParticles.drawDisc(target.getLocation(), 2, 2, 60, Particle.PORTAL, null);
				  }
				 
			}.runTaskLater(Eden.getInstance(), 10);
			return true;
		}
		PrintUtils.sendMessage(event.getPlayer(),"Invalid Target.");
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
