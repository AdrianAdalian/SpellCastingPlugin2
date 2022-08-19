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

public class SpellOverrideClimate extends BaseSpellCapsule
{

	public SpellOverrideClimate()
	{
		super(Material.ENCHANTED_BOOK, "§r§f§ko§r§fTome: OverrideClimate§r§f§ko§r", "SpellOverrideClimate", 1000, false, "§r§fElement: §r§3§lVOID§r§f.","§r§fA tome with an incantation thought to be lost","§r§fin time has the power to change","§r§fthe world's current weather.","§r§fIf storming, changes it to clear and vice versa.","§r§fMana cost: 1000 §r§9mana§r§f.");
	}

	@Override
	public boolean cast(PlayerInteractEvent event)
	{
		if (!event.getAction().equals(Action.RIGHT_CLICK_AIR))
		{
			PrintUtils.sendMessage(event.getPlayer(),"Invalid Cast Method.");
			return false;
		}
		
		boolean weather = Bukkit.getWorlds().get(0).isClearWeather();
		
		if(weather) 
		{
			SpellParticles.drawDisc(event.getPlayer().getLocation(), 2, 2, 60, Particle.PORTAL, null);
			event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_BEACON_POWER_SELECT, SoundCategory.MASTER, 1, 1);
			Bukkit.getWorlds().get(0).setStorm(true);
			Bukkit.getWorlds().get(0).setWeatherDuration(6000);
			return true;
		} 
			SpellParticles.drawDisc(event.getPlayer().getLocation(), 2, 2, 60, Particle.PORTAL, null);
			event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_BEACON_POWER_SELECT, SoundCategory.MASTER, 1, 1);
			Bukkit.getWorlds().get(0).setStorm(false);
			Bukkit.getWorlds().get(0).setThundering(false);
			Bukkit.getWorlds().get(0).setClearWeatherDuration(12000);
			return true;
	}

}
