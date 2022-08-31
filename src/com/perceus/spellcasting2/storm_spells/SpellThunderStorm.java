package com.perceus.spellcasting2.storm_spells;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.entity.Entity;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import com.perceus.spellcasting2.BaseSpellCapsule;
import com.perceus.spellcasting2.SpellParticles;
import com.perceus.spellcasting2.manamechanic.ManaInterface;
import com.perceus.spellcasting2.manamechanic.PlayerDataMana;

import fish.yukiemeralis.eden.utils.PrintUtils;

public class SpellThunderStorm extends BaseSpellCapsule
{

	public SpellThunderStorm()
	{
		super(Material.PURPLE_DYE, "§r§7§ko§r§7§lSpell: §r§fThunderstorm§r§7§ko§r", "SpellThunderStorm", 0, true, true, "§r§fElement: §r§dStorm§r§f.","§r§fSpell Type: §bUtility§f and §cOffensive§f §dAOE§f.","§r§fIf local weather is clear, summon a storm.","§r§fMana cost: 250 §9mana§f.","§r§fIf local weather is currently storming,","§r§fThe caster sends lightning down on all those within range.","§r§fRange: 40 meters.","§r§fMana cost: 450 §r§9mana§r§f.");
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
			PlayerDataMana.getPlayerData(event.getPlayer().getUniqueId()).setCurrentMana(PlayerDataMana.getPlayerData(event.getPlayer().getUniqueId()).getCurrentMana() - 250);
			ManaInterface.updateScoreBoard(event.getPlayer());
			SpellParticles.drawDisc(event.getPlayer().getLocation(), 2, 2, 60, Particle.PORTAL, null);
			event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_BEACON_POWER_SELECT, SoundCategory.MASTER, 1, 1);
			Bukkit.getWorlds().get(0).setStorm(true);
			Bukkit.getWorlds().get(0).setWeatherDuration(6000);
			return true;
		} 
		if (event.getPlayer().getNearbyEntities(40, 40, 40).size() == 0)
		{
			PrintUtils.sendMessage(event.getPlayer(),"Invalid Target.");
			return false;
		}
			
		PlayerDataMana.getPlayerData(event.getPlayer().getUniqueId()).setCurrentMana(PlayerDataMana.getPlayerData(event.getPlayer().getUniqueId()).getCurrentMana() - 450);
		ManaInterface.updateScoreBoard(event.getPlayer());
		SpellParticles.drawDisc(event.getPlayer().getLocation(), 2, 2, 20, Particle.ELECTRIC_SPARK, null);
		
		for (Entity target : event.getPlayer().getNearbyEntities(40, 40, 40))
		{
			SpellParticles.drawLine(event.getPlayer().getLocation(), target.getLocation(), 1, Particle.ELECTRIC_SPARK, null);
			event.getPlayer().getWorld().strikeLightning(target.getLocation());	
		}
		return true;
	}

}
