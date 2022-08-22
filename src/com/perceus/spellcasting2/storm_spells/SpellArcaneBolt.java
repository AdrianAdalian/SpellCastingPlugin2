package com.perceus.spellcasting2.storm_spells;

import java.util.ArrayList;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import com.perceus.spellcasting2.BaseSpellCapsule;
import com.perceus.spellcasting2.SpellParticles;

import fish.yukiemeralis.eden.Eden;
import fish.yukiemeralis.eden.utils.PrintUtils;

public class SpellArcaneBolt extends BaseSpellCapsule
{

	public SpellArcaneBolt()
	{
		super(Material.AMETHYST_SHARD, "§r§7§ko§r§7§lSpell: §r§fArcane Bolt§r§7§ko§r", "SpellArcaneBolt", 1000, true, true, "§r§fElement: §r§dStorm§r§f.","§r§fSpell Type: §cOffensive§f.","§r§fHarness the true power of storm.","§r§fOverload any target with the","§r§felemental power of the non-spiritual elements.","§r§fSummon a small bolt of lightning at the target.","§r§fPoisons, burns, and slows thenafter.","§r§fDuration: 10 seconds.","§r§fRange: 100 meters.","§r§fMana cost: 1000 §r§9mana§r§f.");
	}

	@Override
	public boolean cast(PlayerInteractEvent event)
	{
		if (!event.getAction().equals(Action.RIGHT_CLICK_AIR))
		{
			PrintUtils.sendMessage(event.getPlayer(),"Invalid Cast Method.");
			return false;
		}
		
		Entity target = getNearestEntityInSight(event.getPlayer(), 100);
		
		if (target==null) 
		{
			PrintUtils.sendMessage(event.getPlayer(),"Invalid Target");
			return false;
		}
		
		SpellParticles.drawDisc(event.getPlayer().getLocation(), 2, 2, 20, Particle.ELECTRIC_SPARK, null);
		SpellParticles.drawLine(event.getPlayer().getLocation(), target.getLocation(), 1, Particle.ELECTRIC_SPARK, null);
		event.getPlayer().getWorld().strikeLightning(target.getLocation()) ;		
		
		new BukkitRunnable()
		{
		  @Override
		  public void run()
		  {
			  ((LivingEntity) target).addPotionEffect(new PotionEffect(PotionEffectType.POISON, 200, 0));
			  ((LivingEntity) target).addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 200, 0));
			  ((LivingEntity) target).setFireTicks(200);  
		  }
		}.runTaskLater(Eden.getInstance(), 20);
		return true;
	}
	private Entity getNearestEntityInSight(LivingEntity player, int range) 
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
