package com.perceus.spellcasting2.holy_spells;

import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.attribute.Attribute;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffectType;

import com.perceus.spellcasting2.BaseSpellCapsule;
import com.perceus.spellcasting2.SpellParticles;

import fish.yukiemeralis.eden.utils.PrintUtils;

public class SpellCure extends BaseSpellCapsule
{

	public SpellCure()
	{
		super(Material.WHITE_DYE, "§r§7§ko§r§7§lSpell: §r§fCure§r§7§ko§r", "SpellCure", 50, true, true,"§r§fElement: §r§f§o§lHoly§r§f.","§r§fRapidly regenerates the caster's cells, curing poison.","§r§fFully §r§aHeals§r§f the caster.","§r§fMana cost: 50 §r§9mana§r§f.");
	}

	@Override
	public boolean cast(PlayerInteractEvent event)
	{
		if (!event.getAction().equals(Action.RIGHT_CLICK_AIR)) 
		{
			PrintUtils.sendMessage(event.getPlayer(),"Invalid Cast Method.");
			return false;
		}
		if(event.getPlayer().hasPotionEffect(PotionEffectType.POISON))
		{
			SpellParticles.drawDisc(event.getPlayer().getLocation(), 1, 1, 20, Particle.CLOUD, null);
			event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_CHAIN_BREAK, SoundCategory.MASTER, 1, 1);
			event.getPlayer().setHealth(event.getPlayer().getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue());
			event.getPlayer().removePotionEffect(PotionEffectType.POISON);
			return true;
		}
		
		PrintUtils.sendMessage(event.getPlayer(),"You are not afflicted with poison.");
		return false;
	}

}
