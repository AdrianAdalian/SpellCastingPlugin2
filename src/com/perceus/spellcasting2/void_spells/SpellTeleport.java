package com.perceus.spellcasting2.void_spells;

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

public class SpellTeleport extends BaseSpellCapsule
{

	public SpellTeleport()
	{
		super(Material.CYAN_DYE, "§r§7§ko§r§7§lSpell: §r§fTeleport§r§7§ko§r", "SpellTeleport", 50, true, true, "§r§fElement: §r§3§lVOID§r§f.","§r§fTeleport a far distance away.","§r§fRange: 25 meters.","§r§fMana cost: 50 §r§9mana§r§f.");
	}

	@Override
	public boolean cast(PlayerInteractEvent event)
	{
		if (!event.getAction().equals(Action.RIGHT_CLICK_AIR))
		{
			PrintUtils.sendMessage(event.getPlayer(),"Invalid Cast Method.");
			return false;
		}
		
		int TARGETRANGE = 25 ;
		
		float YAW = event.getPlayer().getLocation().getYaw() ;
		float PITCH = event.getPlayer().getLocation().getPitch() ;
		
		Block target = event.getPlayer().getTargetBlock(null, TARGETRANGE) ;
		
		while (target.getType().equals(Material.AIR)) 
		{
			target = event.getPlayer().getWorld().getBlockAt(target.getLocation().subtract(new Location(event.getPlayer().getWorld(), 0.5, 1, 0.5))) ;
		}
		
		Location newlocation = target.getLocation().add(new Location(event.getPlayer().getWorld(), 0.5, 1, 0.5)) ;
		newlocation.setYaw(YAW);
		newlocation.setPitch(PITCH);
		SpellParticles.drawDisc(event.getPlayer().getLocation(), 2, 2, 60, Particle.PORTAL, null);
		event.getPlayer().teleport(newlocation);
		
		new BukkitRunnable()
		{
			 @Override
			  public void run()
			  {
				 	SpellParticles.drawDisc(event.getPlayer().getLocation(), 2, 2, 60, Particle.PORTAL, null);
					event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, SoundCategory.MASTER, 1, 1);
			  }
			 
		}.runTaskLater(Eden.getInstance(), 10);
		
		return true;
	}

}
