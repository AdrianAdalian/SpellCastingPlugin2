package com.perceus.spellcasting2.void_spells;

import java.util.Random;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.block.Block;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.scheduler.BukkitRunnable;

import com.perceus.spellcasting2.BaseSpellCapsule;
import com.perceus.spellcasting2.SpellParticles;

import fish.yukiemeralis.eden.Eden;
import fish.yukiemeralis.eden.utils.PrintUtils;

public class SpellSingularity extends BaseSpellCapsule
{

	public SpellSingularity()
	{
		super(Material.ENCHANTED_BOOK, "§r§f§ko§r§fTome: §r§fUnstable Singularity§r§f§ko§r", "SpellSingularity", 1000, true, true, "§r§fElement: §r§3§lVOID§r§f.","§r§fSpell Type: §cOffensive§f.","§r§fA highly unstable incantation is written within","§r§fthat allows the caster to summon a singularity on target block.","§r§fThis singularity will rabidly expand,", "§r§fand cause massive explosions,","§r§fdevastating the surrounding terrain.","§r§fAfter 5 explosions have occured,","§r§fthe singularity will collapse causing","§r§fa final explosion where it was summoned.","§r§fRange to cast: 100 meters.","§r§fRadius of explosions around target:", "§r§f25 squared meters.","§r§fMana cost: 1000 §r§9mana§r§f.");
	}

	@Override
	public boolean cast(PlayerInteractEvent event)
	{
		if (!event.getAction().equals(Action.RIGHT_CLICK_AIR))
		{
			PrintUtils.sendMessage(event.getPlayer(),"Invalid Cast Method.");
			return false;
		}
		
		Random rand = new Random(); // Create a new random object

		int radiusOfExplosions = 25; // Maximum radius, in blocks, for lightning to strike 
		
		
		Block target = event.getPlayer().getTargetBlock(null, 100) ;
		event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_ENCHANTMENT_TABLE_USE, SoundCategory.MASTER, 1, 1);
		SpellParticles.drawLine(event.getPlayer().getLocation(), target.getLocation(), 1, Particle.PORTAL, null);
		SpellParticles.drawDisc(event.getPlayer().getLocation(), 3, 3, 60, Particle.PORTAL, null);
		target.getLocation().getWorld().createExplosion(target.getLocation(), 5);
		
		final Block Actualtarget = target ;
		for (int i = 1; i < 5; i++)
		{
			 new BukkitRunnable()
			  {
			    @Override
			    public void run()
			    {
			    	int offsetX = radiusOfExplosions * -1 + rand.nextInt(radiusOfExplosions * 1); // Container values for X and Z offsets
					int offsetZ = radiusOfExplosions * -1 + rand.nextInt(radiusOfExplosions * 1);

					Location targetLoc = Actualtarget.getLocation().add(new Location(event.getPlayer().getWorld(), offsetX, 0, offsetZ)); // Add the offset to the explosion location
					targetLoc.getWorld().createExplosion(targetLoc, 20); // And strike
			    }
			  }.runTaskLater(Eden.getInstance(), 20*i);
		}	
		
		new BukkitRunnable()
		  {
		    @Override
		    public void run()
		    {
				 target.getLocation().getWorld().createExplosion(target.getLocation(), 50);
		    }
		  }.runTaskLater(Eden.getInstance(), 120);
		
		return true;
	}

}
