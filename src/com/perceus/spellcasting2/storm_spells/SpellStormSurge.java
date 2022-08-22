package com.perceus.spellcasting2.storm_spells;

import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Entity;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import com.perceus.spellcasting2.BaseSpellCapsule;
import com.perceus.spellcasting2.SpellParticles;

import fish.yukiemeralis.eden.utils.PrintUtils;

public class SpellStormSurge extends BaseSpellCapsule
{

	public SpellStormSurge()
	{
		super(Material.ENCHANTED_BOOK, "§r§f§ko§r§fTome: Storm Surge§r§f§ko§r", "SpellStormSurge", 500, false, "§r§fElement: §r§dStorm§r§f.","§r§fSpell Type: §cOffensive§f §dAOE§f.","§r§fThe caster summons a cursed thunderstorm","§r§fthat casts down lightning on all those within range.","§r§fThe storm's effects cause an energy drain on those hit","§r§fwhich heals the caster.","§r§fDeals 2.5 hearts of §r§cdamage §r§fper lightning bolt.","§r§4Drain §r§f1/2 hearts health for each target.","§r§fRange: 30 meters.","§r§fMana cost: 500 §r§9mana§r§f.");
	}

	@Override
	public boolean cast(PlayerInteractEvent event)
	{
		if (!event.getAction().equals(Action.RIGHT_CLICK_AIR)) 
		{
			PrintUtils.sendMessage(event.getPlayer(),"Invalid Cast Method.");
			return false;
		}
		
		if (event.getPlayer().getNearbyEntities(30, 30, 30).size() == 0)
		{
			PrintUtils.sendMessage(event.getPlayer(),"Invalid Target.");
			return false;
		}
		
		for (Entity target : event.getPlayer().getNearbyEntities(30, 30, 30))
		{
			
			event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_ENCHANTMENT_TABLE_USE, SoundCategory.MASTER, 1, 1);
			SpellParticles.drawLine(event.getPlayer().getLocation(), target.getLocation(), 1, Particle.ELECTRIC_SPARK, null);
			SpellParticles.drawDisc(event.getPlayer().getLocation(), 2, 2, 20, Particle.ELECTRIC_SPARK, null);
			event.getPlayer().getWorld().strikeLightning(target.getLocation());
			
			try
			{
				event.getPlayer().setHealth(event.getPlayer().getHealth()+1);		
			}
			catch(IllegalArgumentException e)
			{
				event.getPlayer().setHealth(event.getPlayer().getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue());
			}	
		}
		return true;
	}

}
