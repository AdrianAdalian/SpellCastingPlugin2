package com.perceus.spellcasting2.void_spells;

import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;

import com.perceus.spellcasting2.BaseSpellCapsule;
import com.perceus.spellcasting2.SpellParticles;

import fish.yukiemeralis.eden.utils.PrintUtils;

public class SpellVoidContainment extends BaseSpellCapsule
{

	public SpellVoidContainment()
	{
		super(Material.ENCHANTED_BOOK, "§r§f§ko§r§fTome: Void Containment§r§f§ko§r", "SpellVoidContainment", 35, false, "§r§fElement: §r§3§lVOID§r§f.","§r§fSpell Type: §bUtility§f.","§r§fA spelltome containing an incantation to","§r§fsummon the caster's Ender Chest at will.","§r§fMana cost: 35 §r§9mana§r§f.");
	}

	@Override
	public boolean cast(PlayerInteractEvent event)
	{
		if (!event.getAction().equals(Action.RIGHT_CLICK_AIR))
		{
			PrintUtils.sendMessage(event.getPlayer(),"Invalid Cast Method.");
			return false;
		}
		
		event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_ENDER_CHEST_OPEN, SoundCategory.MASTER, 1, 1);
		SpellParticles.drawDisc(event.getPlayer().getLocation(), 2, 2, 60, Particle.PORTAL, null);
		Inventory enderchest = event.getPlayer().getEnderChest();
		
		event.getPlayer().openInventory(enderchest);
		
		return true;
	}

}
