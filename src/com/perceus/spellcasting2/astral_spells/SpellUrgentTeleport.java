package com.perceus.spellcasting2.astral_spells;

import java.util.Random;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.World;
import org.bukkit.block.BlockFace;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.scheduler.BukkitRunnable;

import com.perceus.spellcasting2.BaseSpellCapsule;
import com.perceus.spellcasting2.SpellParticles;

import fish.yukiemeralis.eden.Eden;
import fish.yukiemeralis.eden.utils.ChatUtils;
import fish.yukiemeralis.eden.utils.PrintUtils;

public class SpellUrgentTeleport extends BaseSpellCapsule
{

	public SpellUrgentTeleport()
	{
		super(Material.ENCHANTED_BOOK, ChatUtils.of("✩ Astral Tome: Urgent Teleport ✩", "8B008B","FFFFFF",""), "SpellUrgentTeleport", 50, false, "§r§fElement: §r§5A§ds§5t§dr§5α§dl§r§f.",
				"§r§fSpell Type: §bUtility§f.",
				"§r§fA §5c§de§bl§3e§cs§4t§6i§eal §r§ftome.",
				"§r§fA highly unstable tome that allows the caster to",
				"§r§frandomly teleport within radius of initial cast location.",
				"§r§fSpace and Time seem to buckle when holding this book.",
				"§r§fRange: 30 meters.",
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
				
		Random random = new Random();
		int radius = 30;		
		double randomX = (radius * -1) + random.nextDouble() * (radius * 2);
	    double randomZ = (radius * -1) + random.nextDouble() * (radius * 2);
	    float YAW = event.getPlayer().getLocation().getYaw();
	    float PITCH = event.getPlayer().getLocation().getPitch();

	    Location targetLoc = event.getPlayer().getLocation().clone().add(randomX, 0, randomZ);
	    World world = event.getPlayer().getWorld();
	    
	    if (!world.getBlockAt(targetLoc).getType().isSolid())
	    {
	        // We might have teleported the player into the air, attempt to shift them down
	        while (world.getBlockAt(targetLoc).getRelative(BlockFace.DOWN).getType().equals(Material.AIR))
	        {
	            if (targetLoc.getBlockY() == -63) // Small safety check so we don't go below bedrock
	            {
	                // Cannot find an appropriate place to teleport, recurse and try again
	            	this.cast(event);
	            }

	            targetLoc = targetLoc.subtract(0, 1, 0);
	        }

	        event.getPlayer().teleport(targetLoc);
	    }

	    // Target location may be inside a solid block, attempt to shift them up
	    while (world.getBlockAt(targetLoc).getType().isSolid())
	    {
	        if (targetLoc.getBlockY() == 319) // Small safety check so we don't go above sky limit
	        {
	            // Cannot find an appropriate place to teleport, recurse and try again
	        	targetLoc = event.getPlayer().getLocation().clone().add(randomX, 0, randomZ);
	        }

	        targetLoc.add(0, 1, 0);
	    }

	    SpellParticles.drawDisc(event.getPlayer().getLocation(), 2, 2, 60, Particle.DRAGON_BREATH, null);
		targetLoc.setYaw(YAW);
		targetLoc.setPitch(PITCH);
		event.getPlayer().teleport(targetLoc);
		
		new BukkitRunnable()
		{
			 @Override
			  public void run()
			  {
				 	SpellParticles.drawDisc(event.getPlayer().getLocation(), 2, 2, 60, Particle.DRAGON_BREATH, null);
					event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, SoundCategory.MASTER, 1, 1);
			  }
			 
		}.runTaskLater(Eden.getInstance(), 10);
		
		return true;
	}

}
