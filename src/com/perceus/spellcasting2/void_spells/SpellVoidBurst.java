package com.perceus.spellcasting2.void_spells;

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

public class SpellVoidBurst extends BaseSpellCapsule
{

	public SpellVoidBurst()
	{
		super(Material.ENDER_EYE, "§r§7§ko§r§7§lSpell: §r§fVoid Burst§r§7§ko§r", "SpellVoidBurst", 10, true, true, "§r§fElement: §r§3§lVOID§r§f.","§r§fEmit a burst of concentrated §r§3§lVOID§r§f energy","§r§fstrong enough to distort matter.","§r§fCause any target block to acutely decompose.","§r§fRange: 6 meter.","§r§fMana cost: 10 §r§9mana§r§f.");
	}

	@Override
	public boolean cast(PlayerInteractEvent event)
	{
		if (!event.getAction().equals(Action.RIGHT_CLICK_BLOCK))
		{
			PrintUtils.sendMessage(event.getPlayer(),"Invalid Cast Method.");
			return false;
		}
		
		Block target = event.getPlayer().getTargetBlock(null, 6) ;
		
		if (target.getType().equals(Material.AIR))
		{
			return false;			
		}
		if (target.getType().equals(Material.BEDROCK)) 
		{
			PrintUtils.sendMessage(event.getPlayer(), "Invalid Target.");
			return false;
		}
		
		new BukkitRunnable()
		{
			@Override
			public void run()
			{
				SpellParticles.drawDisc(event.getPlayer().getLocation(), 2, 2, 60, Particle.PORTAL, null);
				event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.ENTITY_FOX_TELEPORT, SoundCategory.MASTER, 1, 1);
				target.getLocation().getWorld().spawnParticle(Particle.EXPLOSION_LARGE, target.getLocation(), 1);
				target.breakNaturally();
			}
		}.runTaskLater(Eden.getInstance(), 1);
		return true;
	}
}
