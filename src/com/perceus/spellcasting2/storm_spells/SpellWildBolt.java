package com.perceus.spellcasting2.storm_spells;

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

public class SpellWildBolt extends BaseSpellCapsule
{

	public SpellWildBolt()
	{
		super(Material.ENCHANTED_BOOK, "§r§f§ko§r§fTome: §r§fWildBolt§r§f§ko§r", "SpellWildBolt", 350, true, "§r§fElement: §r§dStorm§r§f.","§r§fSpell Type: §cOffensive§f §dAOE§f.","§r§fThis unstable spell randomly summons five","§r§fbolts of lightning to a target block.","§r§fRange to cast: 50 meters.","§r§fRange of lightning: 7 meters.","§r§fMana cost: 350 §r§9mana§r§f.");
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

		int radiusOfLightningStrike = 7; // Maximum radius, in blocks, for lightning to strike 
		int TARGETBLOCKRADIUS = 50; //the radius in which the spell can be casted on.
		
		Block target = event.getPlayer().getTargetBlock(null, TARGETBLOCKRADIUS) ;
		SpellParticles.drawLine(event.getPlayer().getLocation(), target.getLocation(), 1, Particle.ELECTRIC_SPARK, null);
		event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_ENCHANTMENT_TABLE_USE, SoundCategory.MASTER, 1, 1);
		SpellParticles.drawDisc(event.getPlayer().getLocation(), 2, 2, 20, Particle.ELECTRIC_SPARK, null);
		event.getPlayer().getWorld().strikeLightning(target.getLocation()) ;
		
		if (target.getType().equals(Material.AIR))
		{
			while (target.getType().equals(Material.AIR)) 
			{
				target = event.getPlayer().getWorld().getBlockAt(target.getLocation().subtract(new Location(event.getPlayer().getWorld(), 0, 1, 0))) ;
			}	
		}
		final Block Actualtarget = target;
		for (int i = 1; i < 5; i++)
		{
			 new BukkitRunnable()
			  {
			    @Override
			    public void run()
			    {
			    	  int offsetX = radiusOfLightningStrike * -1 + rand.nextInt(radiusOfLightningStrike * 2);
					  int offsetZ = radiusOfLightningStrike * -1 + rand.nextInt(radiusOfLightningStrike * 2);

					  // Assume we have some kind of object, like an entity or a block, that has a .getLocation() method
					  Location targetLoc = Actualtarget.getLocation().add(new Location(event.getPlayer().getWorld(), offsetX, 0, offsetZ)); // Add the offset to the strike location
					  targetLoc.getWorld().strikeLightning(targetLoc); // And strike
			    }
			  }.runTaskLater(Eden.getInstance(), 20*i);
		}	
		return true;
	}

}
