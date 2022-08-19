package com.perceus.spellcasting2.void_spells;

import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import com.perceus.spellcasting2.BaseSpellCapsule;
import com.perceus.spellcasting2.SpellParticles;

import fish.yukiemeralis.eden.utils.PrintUtils;

public class SpellNullPointConfiguration extends BaseSpellCapsule
{

	public SpellNullPointConfiguration()
	{
		super(Material.ENCHANTED_BOOK, "§r§f§ko§r§fTome: §r§fNullPoint Configuration§r§f§ko§r", "SpellNullPointConfiguration", 35, true, "§r§fElement: §r§3§lVOID§r§f.","§r§fA spelltome containing an incantation to reconfigure matter.","§r§fSummon a portable crafting bench at will.","§r§fMana cost: 35 §r§9mana§r§f.");
	}

	@Override
	public boolean cast(PlayerInteractEvent event)
	{
		if (!event.getAction().equals(Action.RIGHT_CLICK_AIR))
		{
			PrintUtils.sendMessage(event.getPlayer(),"Invalid Cast Method.");
			return false;
		}
		
		event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_ENCHANTMENT_TABLE_USE, SoundCategory.MASTER, 1, 1); 
		SpellParticles.drawDisc(event.getPlayer().getLocation(), 2, 2, 60, Particle.PORTAL, null);
		event.getPlayer().openInventory(event.getPlayer().openWorkbench(event.getPlayer().getLocation(), true));
		
		return true;
	}

}
