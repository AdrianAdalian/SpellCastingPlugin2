package com.perceus.spellcasting2.darkmagic_spells;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.perceus.spellcasting2.BaseSpellCapsule;
import com.perceus.spellcasting2.SpellParticles;

import fish.yukiemeralis.eden.utils.PrintUtils;

public class SpellCrimsonFury extends BaseSpellCapsule
{

	public SpellCrimsonFury()
	{
		super(Material.ENCHANTED_BOOK, "§r§7§ko§r§7§lForbidden Spell: §r§fCrimson Fury§r§7§ko§r", "SpellCrimsonFury", 0, false, "§r§fElement: §r§4Dark Magic§r§f.",
				"§r§fSpell Type: §6Buff§f.", 
				"§r§fThe caster gashes their arm, immediately",
				"§r§fcausing adrenaline to rush throughout their body.",
				"§r§fAs a result, grant damage increase.",
				"§r§fLeft-Click to cycle between damage tiers.",
				"§r§fRight-Click to cast the spell.",
				"§r§fThis spell by-passes absorption.",
				"§r§fTier 1: 3 heart sacrifice.",
				"§r§fTier 2: 5 heart sacrifice.",
				"§r§fTier 3: 8 heart sacrifice.");
	}
	
	private static Map<UUID,Integer> cyclemanager = new HashMap<>();
	
	@Override
	public boolean cast(PlayerInteractEvent event)
	{
		if (event.getAction().equals(Action.LEFT_CLICK_AIR)) 
		{
			if (!cyclemanager.containsKey(event.getPlayer().getUniqueId()))
			{
				PrintUtils.sendMessage(event.getPlayer(),"§r§fSpell Tier 1 Cycled.");
				cyclemanager.put(event.getPlayer().getUniqueId(), 1);
				return true;
			}
			
			if (cyclemanager.get(event.getPlayer().getUniqueId()) == 1)
			{
				PrintUtils.sendMessage(event.getPlayer(),"§r§fSpell Tier 2 Cycled.");
				cyclemanager.put(event.getPlayer().getUniqueId(), 2);
				return true;
			}
			
			if (cyclemanager.get(event.getPlayer().getUniqueId()) == 2)
			{
				PrintUtils.sendMessage(event.getPlayer(),"§r§fSpell Tier 3 Cycled.");
				cyclemanager.put(event.getPlayer().getUniqueId(), 3);
				return true;
			}
			
			if (cyclemanager.get(event.getPlayer().getUniqueId()) == 3)
			{
				PrintUtils.sendMessage(event.getPlayer(),"§r§fSpell Tier 1 Cycled.");
				cyclemanager.put(event.getPlayer().getUniqueId(), 1);
				return true;
			}
		}
		
		
		if (event.getAction().equals(Action.RIGHT_CLICK_AIR)) 
		{
			
			if (!cyclemanager.containsKey(event.getPlayer().getUniqueId())) 
			{
				PrintUtils.sendMessage(event.getPlayer(),"§r§fNo spell tier has been selected.");
				return false;
			}
			
			if (cyclemanager.get(event.getPlayer().getUniqueId()) == 1)
			{
				if (event.getPlayer().hasPotionEffect(PotionEffectType.ABSORPTION)) 
				{
					event.getPlayer().removePotionEffect(PotionEffectType.ABSORPTION);
				}
				event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_NYLIUM_BREAK, SoundCategory.MASTER, 1, 1);
				SpellParticles.drawDisc(event.getPlayer().getLocation(), 1, 1, 100, Particle.CRIMSON_SPORE, null);
				event.getPlayer().damage(6);
				event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 1200, 0));
				return true;
			}
			
			if (cyclemanager.get(event.getPlayer().getUniqueId()) == 2)
			{
				if (event.getPlayer().hasPotionEffect(PotionEffectType.ABSORPTION)) 
				{
					event.getPlayer().removePotionEffect(PotionEffectType.ABSORPTION);
				}
				event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_NYLIUM_BREAK, SoundCategory.MASTER, 1, 1);
				SpellParticles.drawDisc(event.getPlayer().getLocation(), 1, 1, 100, Particle.CRIMSON_SPORE, null);
				event.getPlayer().damage(10);
				event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 1200, 1));
				return true;
			}
			
			if (cyclemanager.get(event.getPlayer().getUniqueId()) == 3) 
			{
				if (event.getPlayer().hasPotionEffect(PotionEffectType.ABSORPTION)) 
				{
					event.getPlayer().removePotionEffect(PotionEffectType.ABSORPTION);
				}
				event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_NYLIUM_BREAK, SoundCategory.MASTER, 1, 1);
				SpellParticles.drawDisc(event.getPlayer().getLocation(), 1, 1, 100, Particle.CRIMSON_SPORE, null);
				event.getPlayer().damage(16);
				event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 1200, 2));
				return true;
			}
		}
		
		return true;
	}
}
