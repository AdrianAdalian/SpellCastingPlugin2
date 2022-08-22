package com.perceus.spellcasting2.holy_spells;

import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.scheduler.BukkitRunnable;

import com.perceus.spellcasting2.BaseSpellCapsule;
import com.perceus.spellcasting2.SpellParticles;

import fish.yukiemeralis.eden.Eden;
import fish.yukiemeralis.eden.utils.PrintUtils;

public class SpellAngelicFlight extends BaseSpellCapsule
{

	public SpellAngelicFlight()
	{
		super(Material.NETHER_STAR, "§r§7§ko§r§7§lSpell: §r§fAngelic Flight§r§7§ko§r", "SpellAngelicFlight", 100, true, false,"§r§fElement: §r§f§o§lHoly§r§f.","§r§fSpell Type: §bUtility§f.","§r§fGrants the caster temporary flight.","§r§fDuration: 1 minute.","§r§fMana cost: 100 §r§9mana§r§f.");
	}

	@Override
	public boolean cast(PlayerInteractEvent event)
	{
		if (!event.getAction().equals(Action.RIGHT_CLICK_AIR)) 
		{
			PrintUtils.sendMessage(event.getPlayer(),"Invalid Cast Method.");
			return false; 
		}
		
		event.getPlayer().setAllowFlight(true);
		event.getPlayer().setFlying(true);
		SpellParticles.drawDisc(event.getPlayer().getLocation(), 1, 1, 20, Particle.CLOUD, null);
		event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_BEACON_ACTIVATE, SoundCategory.MASTER, 1, 1);
		
		new BukkitRunnable()
		{
			@Override
			public void run()
			{
			  	
				event.getPlayer().setAllowFlight(false);
			  	event.getPlayer().setFlying(false);
			  
			  	event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_BEACON_DEACTIVATE, SoundCategory.MASTER, 1, 1);
			
			}
		}.runTaskLater(Eden.getInstance(), 1205);
		return true;
	}

}
