package com.perceus.spellcasting2.storm_spells;

import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.entity.Entity;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import com.perceus.spellcasting2.BaseSpellCapsule;
import com.perceus.spellcasting2.SpellParticles;

import fish.yukiemeralis.eden.utils.PrintUtils;

public class SpellThunderStorm extends BaseSpellCapsule
{

	public SpellThunderStorm()
	{
		super(Material.PURPLE_DYE, "§r§7§ko§r§7§lSpell: §r§fThunderstorm§r§7§ko§r", "SpellThunderStorm", 500, true, true, "§r§fElement: §r§dStorm§r§f.","§r§fSpell Type: §cOffensive§f §dAOE§f.","§r§fThe caster summons a thunderstorm sending lightning","§r§fdown on all those within range.","§r§fRange: 25 meters.","§r§fDeals 2.5 hearts of §r§cdamage §r§fper lightning bolt.","§r§fMana cost: 500 §r§9mana§r§f.");
	}

	@Override
	public boolean cast(PlayerInteractEvent event)
	{
		if (!event.getAction().equals(Action.RIGHT_CLICK_AIR)) 
		{
			PrintUtils.sendMessage(event.getPlayer(),"Invalid Cast Method.");
			return false;
		}
		
		if (event.getPlayer().getNearbyEntities(25, 25, 25).size() == 0)
		{
			PrintUtils.sendMessage(event.getPlayer(),"Invalid Target.");
			return false;
		}
		
		SpellParticles.drawDisc(event.getPlayer().getLocation(), 2, 2, 20, Particle.ELECTRIC_SPARK, null);
		
		for (Entity target : event.getPlayer().getNearbyEntities(25, 25, 25))
		{
			SpellParticles.drawLine(event.getPlayer().getLocation(), target.getLocation(), 1, Particle.ELECTRIC_SPARK, null);
			event.getPlayer().getWorld().strikeLightning(target.getLocation());	
		}
		return true;
	}

}
