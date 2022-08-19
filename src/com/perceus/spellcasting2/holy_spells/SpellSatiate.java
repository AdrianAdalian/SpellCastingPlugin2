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

public class SpellSatiate extends BaseSpellCapsule
{

	public SpellSatiate()
	{
		super(Material.WHITE_DYE, "§r§7§ko§r§7§lSpell: §r§fSatiate§r§7§ko§r", "SpellSatiate", 75, true, true,"§r§fElement: §r§f§o§lHoly§r§f.","§r§fSatiates the caster.","§r§fCounteracts the Hunger debuff.","§r§fRestore saturation over 5 seconds.","§r§fMana cost: 75 §r§9mana§r§f.");
	}

	@Override
	public boolean cast(PlayerInteractEvent event)
	{
		if (!event.getAction().equals(Action.RIGHT_CLICK_AIR))
		{
			PrintUtils.sendMessage(event.getPlayer(),"Invalid Cast Method.");
			return false;
		}
		
		if (event.getPlayer().hasPotionEffect(PotionEffectType.HUNGER)) 
		{
			event.getPlayer().removePotionEffect(PotionEffectType.HUNGER);
		}
		
		SpellParticles.drawDisc(event.getPlayer().getLocation(), 1, 1, 20, Particle.CLOUD, null);
		event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.ITEM_BONE_MEAL_USE, SoundCategory.MASTER, 1, 1);
		event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, 100, 0));
		
		return true;
	}

}
