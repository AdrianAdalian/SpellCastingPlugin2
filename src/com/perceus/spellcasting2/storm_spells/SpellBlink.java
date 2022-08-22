package com.perceus.spellcasting2.storm_spells;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.block.Block;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import com.perceus.spellcasting2.BaseSpellCapsule;
import com.perceus.spellcasting2.SpellParticles;

import fish.yukiemeralis.eden.utils.PrintUtils;

public class SpellBlink extends BaseSpellCapsule
{

	public SpellBlink()
	{
		super(Material.STRING, "§r§7§ko§r§7§lSpell: §r§fBlink§r§7§ko§r", "SpellBlink", 25, true, true, "§r§fElement: §r§dStorm§r§f.","§r§fSpell Type: §bUtility§f.","§r§fThe caster accelerates their body's capability","§r§fto move causing them to violently shift forward.","§r§fTeleport a short distance away.","§r§fRange: 10 meters.","§r§fMana cost: 25 §r§9mana§r§f.");
	}

	@Override
	public boolean cast(PlayerInteractEvent event)
	{
		if (!event.getAction().equals(Action.RIGHT_CLICK_AIR))
		{
			PrintUtils.sendMessage(event.getPlayer(),"Invalid Cast Method.");
			return false;
		}
		int TARGETRANGE = 10 ;
		
		float YAW = event.getPlayer().getLocation().getYaw() ;
		float PITCH = event.getPlayer().getLocation().getPitch() ; //float is a variable that is a decimal, we can use this to get a rotated object/pitch/yaw.
		
		Block target = event.getPlayer().getTargetBlock(null, TARGETRANGE) ;
		
		while (target.getType().equals(Material.AIR)) 
		{
			
			event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.ENTITY_ENDERMAN_TELEPORT, SoundCategory.MASTER, 1, 1);
			SpellParticles.drawDisc(event.getPlayer().getLocation(), 2, 2, 20, Particle.ELECTRIC_SPARK, null);
			target = event.getPlayer().getWorld().getBlockAt(target.getLocation().subtract(new Location(event.getPlayer().getWorld(), 0.5, 1, 0.5))) ;
		
		}
		
		Location newlocation = target.getLocation().add(new Location(event.getPlayer().getWorld(), 0.5, 1, 0.5)) ;
		newlocation.setYaw(YAW);
		newlocation.setPitch(PITCH);
		
		//Location newlocation is a defined variable that we are using to store a player float (yaw/pitch), and then plugging this information into the teleport.
		
		event.getPlayer().teleport(newlocation);
		return true;
	}

}
