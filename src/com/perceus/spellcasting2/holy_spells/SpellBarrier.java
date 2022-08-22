package com.perceus.spellcasting2.holy_spells;

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

public class SpellBarrier extends BaseSpellCapsule
{

	public SpellBarrier()
	{
		super(Material.WHITE_DYE, "§r§7§ko§r§7§lSpell: §r§fBarrier§r§7§ko§r", "SpellBarrier", 50, true, true,"§r§fElement: §r§f§o§lHoly§r§f.","§r§fSpell Type: §6Buff§f.","§r§fProtect the caster by raising a barrier around them.","§r§fApply a full bar of absorption.","§r§fDuration: 1 minute.","§r§fMana cost: 50 §r§9mana§r§f.");
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean cast(PlayerInteractEvent event)
	{
		if (!event.getAction().equals(Action.RIGHT_CLICK_AIR)) 
		{
			PrintUtils.sendMessage(event.getPlayer(),"Invalid Cast Method.");
			return false; 
		}
		
		event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_BEACON_ACTIVATE, SoundCategory.MASTER, 1, 1);
		SpellParticles.drawDisc(event.getPlayer().getLocation(), 1, 1, 20, Particle.CLOUD, null);
		event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, 1200, 4));
		return true;
	}

}
