package com.perceus.spellcasting2.ancient_spells;

import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import com.perceus.spellcasting2.BaseSpellCapsule;
import com.perceus.spellcasting2.SpellParticles;

import fish.yukiemeralis.eden.utils.PrintUtils;

public class SpellCreateItem extends BaseSpellCapsule
{

	public SpellCreateItem()
	{
		super(Material.END_CRYSTAL, "§r§f§ko§r§bAethereal §r§eRune§f: Create Matter§r§f§ko§r", "SpellCreateItem", 0, false, "§r§fElement: §r§eAncient§r§f.",
				"§r§fSpell Type: §bUtility§f.",
				"§r§fAn §b§l§oAethereal§r§f §finfused mass of", "§r§eAncient§r§f energy that allows",
				"§r§fthe caster to materialize", 
				"§r§fa single copy of their off-hand item.",
				"§r§fSets off-hand to that copy",
				"§r§fThe original item will be swapped to inventory.",
				"§r§fThe rune will be consumed upon use.");
	}
	
	@Override
	public boolean cast(PlayerInteractEvent event)
	{
		if (!event.getAction().equals(Action.RIGHT_CLICK_AIR))
		{
			PrintUtils.sendMessage(event.getPlayer(),"Invalid Cast Method.");
			return false;
		}
		
		ItemStack offhand = event.getPlayer().getInventory().getItemInOffHand();
		if (offhand == null || offhand.getType().equals(Material.AIR))
		{
			PrintUtils.sendMessage(event.getPlayer(),"Valid Item Not Detected in Offhand.");
			return false;
		}
		
		if (event.getPlayer().getInventory().getItemInMainHand().getAmount() > 1) 
		{
			event.getPlayer().getInventory().getItemInMainHand().setAmount(event.getPlayer().getInventory().getItemInMainHand().getAmount() - 1);
		}
		else
		{
			event.getPlayer().getInventory().getItemInMainHand().setAmount(0);
		}
		
		event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_AMETHYST_BLOCK_BREAK, SoundCategory.MASTER, 1, 1);
		SpellParticles.drawCylinder(event.getPlayer().getLocation(), 1, 50, 3, 1, Particle.ENCHANTMENT_TABLE, null);
		SpellParticles.drawDisc(event.getPlayer().getLocation(), 2, 2, 100, Particle.WARPED_SPORE, null);
		event.getPlayer().getInventory().addItem(offhand);
		offhand.setAmount(1);
		return true;
	}
}
