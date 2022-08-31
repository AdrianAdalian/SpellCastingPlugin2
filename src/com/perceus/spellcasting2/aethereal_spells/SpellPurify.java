package com.perceus.spellcasting2.aethereal_spells;

import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import com.perceus.spellcasting2.BaseSpellCapsule;
import com.perceus.spellcasting2.SpellParticles;

import fish.yukiemeralis.eden.utils.PrintUtils;

public class SpellPurify extends BaseSpellCapsule
{

	public SpellPurify()
	{
		super(Material.ENCHANTED_BOOK,"§r§f§ko§r§bAethereal Tome§f: Purify§r§f§ko§r", "SpellPurify", 1000, false, "§r§fElement: §r§b§l§oAethereal§r§f.","§r§fSpell Type: §bUtility§f.","§r§fA spelltome containing §r§b§l§oAethereal§r§f energy.","§r§fAn incantation is written within that allows","§r§fthe caster to remove curses from worn items.","§r§fIf the caster is wearing cursed armor,", "§r§fremove those curses.","§r§fMana cost: 1000 §r§9mana§r§f.");
	}

	@Override
	public boolean cast(PlayerInteractEvent event)
	{
		
		if (!event.getAction().equals(Action.RIGHT_CLICK_AIR))
		{
			PrintUtils.sendMessage(event.getPlayer(),"Invalid Cast Method.");
			return false;
		}
		
		SpellParticles.drawCylinder(event.getPlayer().getLocation(), 1, 50, 4, 1, Particle.ENCHANTMENT_TABLE, null);
		event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_CHAIN_BREAK, SoundCategory.MASTER, 1, 1);
		
		for (ItemStack item : event.getPlayer().getInventory().getArmorContents())
	    {
			if (item == null) // If the player has no item equipped in this slot, return
			{
				continue;
			}
			  
			if (item.getType().equals(Material.AIR)) // Docs specify that some armor items can be null, which implies some may be air as well
			{
				continue;
			}
			
			if (item.getEnchantmentLevel(Enchantment.BINDING_CURSE) == 1) // I chose "spelArmorItem" with the idea that the value would be the type of "spell armor" in play
			{
				item.removeEnchantment(Enchantment.BINDING_CURSE);
			}
			  
			if (item.getEnchantmentLevel(Enchantment.VANISHING_CURSE) == 1) // If not all pieces match type, this check will fail and it'll stop running by this point
			{
				item.removeEnchantment(Enchantment.VANISHING_CURSE);
			}
	    }
		PrintUtils.sendMessage(event.getPlayer(),"Purification Success.");
		return true;
	}

}
