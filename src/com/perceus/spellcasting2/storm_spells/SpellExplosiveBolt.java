package com.perceus.spellcasting2.storm_spells;

import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.scheduler.BukkitRunnable;

import com.perceus.spellcasting2.BaseSpellCapsule;
import com.perceus.spellcasting2.SpellParticles;

import fish.yukiemeralis.eden.Eden;
import fish.yukiemeralis.eden.utils.PrintUtils;

public class SpellExplosiveBolt extends BaseSpellCapsule
{

	public SpellExplosiveBolt()
	{
		super(Material.AMETHYST_SHARD, "§r§7§ko§r§7§lSpell: §r§fExplosive Bolt§r§7§ko§r", "SpellExplosiveBolt", 250, true, true, "§r§fElement: §r§dStorm§r§f.","§r§fSpell Type: §cOffensive§f.","§r§fSummon a bolt of lightning that is charged for explosion.","§r§fRange: 25 meters.","§r§fMana cost: 250 §r§9mana§r§f.");
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
		
		Block target = event.getPlayer().getTargetBlock(null, TARGETRANGE) ;
		
		if (target.getType().equals(Material.AIR))
		{
			PrintUtils.sendMessage(event.getPlayer(),"Invalid Target.");
			return false;			
		}
		SpellParticles.drawLine(event.getPlayer().getLocation(), target.getLocation(), 1, Particle.ELECTRIC_SPARK, null);
		SpellParticles.drawDisc(event.getPlayer().getLocation(), 2, 2, 20, Particle.ELECTRIC_SPARK, null);
		event.getPlayer().getWorld().strikeLightning(target.getLocation()) ;		

		new BukkitRunnable()
		{
		  @Override
		  public void run()
		  {
			  event.getPlayer().getWorld().createExplosion(target.getLocation(), 3);
		  }
		}.runTaskLater(Eden.getInstance(), 20);
		return true;
	}

}
