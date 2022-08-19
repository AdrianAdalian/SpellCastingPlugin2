package com.perceus.spellcasting2.darkmagic_spells;

import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffectType;

import com.perceus.spellcasting2.BaseSpellCapsule;
import com.perceus.spellcasting2.SpellParticles;
import com.perceus.spellcasting2.manamechanic.ManaInterface;
import com.perceus.spellcasting2.manamechanic.PlayerDataMana;

import fish.yukiemeralis.eden.utils.PrintUtils;

public class SpellBloodletting extends BaseSpellCapsule
{

	public SpellBloodletting()
	{
		super(Material.ENCHANTED_BOOK, "§r§7§ko§r§7§lForbidden Spell: §r§fBloodletting§r§7§ko§r", "SpellBloodletting", 0, false, "§r§fElement: §r§4Dark Magic§r§f.", "§r§fThe caster gashes their arm, opening a vein,","§r§fwhich allows ether to flow", "§r§fdirectly into their bloodstream","§r§frestoring 500 §r§9mana§r§f.","§r§fThis spell by-passes absorption.","§r§4Blood Sacrifice§r§f: 5 hearts.");
	}

	@Override
	public boolean cast(PlayerInteractEvent event)
	{
		if (!event.getAction().equals(Action.RIGHT_CLICK_AIR)) {
			PrintUtils.sendMessage(event.getPlayer(), "Invalid Cast Method.");
			return false;
		}
		
		if (event.getPlayer().hasPotionEffect(PotionEffectType.ABSORPTION)) 
		{
			event.getPlayer().removePotionEffect(PotionEffectType.ABSORPTION);
		}
		
		event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_NYLIUM_BREAK, SoundCategory.MASTER, 1, 1);
		SpellParticles.drawDisc(event.getPlayer().getLocation(), 1, 1, 100, Particle.CRIMSON_SPORE, null);
		event.getPlayer().damage(10);
		PlayerDataMana.getPlayerData(event.getPlayer()).setCurrentMana(PlayerDataMana.getPlayerData(event.getPlayer()).getCurrentMana() + 500);
		if (PlayerDataMana.getPlayerData(event.getPlayer()).getCurrentMana()>PlayerDataMana.getPlayerData(event.getPlayer()).getMaxMana()) 
		{
			PlayerDataMana.getPlayerData(event.getPlayer()).setCurrentMana(PlayerDataMana.getPlayerData(event.getPlayer()).getMaxMana());
		}
		ManaInterface.updateScoreBoard(event.getPlayer());
		
		return true;
	}
}
