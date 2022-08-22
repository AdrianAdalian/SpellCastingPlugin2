package com.perceus.spellcasting2.void_spells;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import com.perceus.spellcasting2.BaseSpellCapsule;
import com.perceus.spellcasting2.SpellParticles;

import fish.yukiemeralis.eden.utils.PrintUtils;

public class SpellPrimordialShift extends BaseSpellCapsule
{

	public SpellPrimordialShift()
	{
		super(Material.ENCHANTED_BOOK, "§r§f§ko§r§fTome: Primordial Shift§r§f§ko§r", "SpellPrimordialShift", 1000, false, "§r§fElement: §r§3§lVOID§r§f.","§r§fSpell Type: §bUtility§f.","§r§fA tome with an incantation thought to be lost","§r§fin time has the power to swap","§r§6Day §r§fwith §r§7Night §r§fand vice versa.","§r§fMana cost: 1000 §r§9mana§r§f.");
	}

	@Override
	public boolean cast(PlayerInteractEvent event)
	{
		if (!event.getAction().equals(Action.RIGHT_CLICK_AIR))
		{
			PrintUtils.sendMessage(event.getPlayer(),"Invalid Cast Method.");
			return false;
		}
		
		long time = Bukkit.getWorlds().get(0).getTime();
		
		if(time >= 0 && time <= 12000)
		{
			SpellParticles.drawDisc(event.getPlayer().getLocation(), 2, 2, 60, Particle.PORTAL, null);
			event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_BEACON_POWER_SELECT, SoundCategory.MASTER, 1, 1);
			Bukkit.broadcastMessage("§r§fThe world's time has shifted due to a spell cast by " + event.getPlayer().getDisplayName() + "§r§f.");
			Bukkit.getWorlds().get(0).setTime(12000);
			//Sets the time to Sunset.
			return true;
		}
		if(time >=12005 && time <= 24000)
		{
			SpellParticles.drawDisc(event.getPlayer().getLocation(), 2, 2, 60, Particle.PORTAL, null);
			event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_BEACON_POWER_SELECT, SoundCategory.MASTER, 1, 1);
			Bukkit.broadcastMessage("§r§fThe world's time has shifted due to a spell cast by " + event.getPlayer().getDisplayName() + "§r§f.");
			Bukkit.getWorlds().get(0).setTime(0);
			//Sets the time to Sunrise.
			return true;
		}
		return false;
	}

}
