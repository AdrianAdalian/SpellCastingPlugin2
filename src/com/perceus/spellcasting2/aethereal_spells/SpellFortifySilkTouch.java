package com.perceus.spellcasting2.aethereal_spells;

import java.util.List;

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

public class SpellFortifySilkTouch extends BaseSpellCapsule
{

	public SpellFortifySilkTouch()
	{
		super(Material.ENCHANTED_BOOK, "§r§f§ko§r§bAethereal Tome§f: Fortify Silk Touch§r§f§ko§r", "SpellFortifySilkTouch", 1000, false, "§r§fElement: §r§b§l§oAethereal§r§f.","§r§fSpell Type: §bUtility§f.","§r§fA spelltome containing §r§b§l§oAethereal§r§f energy.","§r§fAn incantation is written within that allows","§r§fthe caster to enchant items.","§r§fEnchant offhand applicable item with silk touch.","§r§fMana cost: 1000 §r§9mana§r§f.");
	}

	@Override
	public boolean cast(PlayerInteractEvent event)
	{
		if (!event.getAction().equals(Action.RIGHT_CLICK_AIR))
		{
			PrintUtils.sendMessage(event.getPlayer(),"Invalid Cast Method.");
			return false;
		}
		
		ItemStack stack = event.getPlayer().getInventory().getItemInOffHand();
		
		List<Material> material = List.of(Material.WOODEN_AXE, 
				Material.WOODEN_HOE, 
				Material.WOODEN_PICKAXE, 
				Material.WOODEN_SHOVEL,
				Material.STONE_AXE,
				Material.STONE_HOE,
				Material.STONE_PICKAXE,
				Material.STONE_SHOVEL,
				Material.IRON_HOE,
				Material.IRON_AXE,
				Material.IRON_PICKAXE,
				Material.IRON_SHOVEL,
				Material.GOLDEN_AXE,
				Material.GOLDEN_HOE,
				Material.GOLDEN_PICKAXE,
				Material.GOLDEN_SHOVEL,
				Material.DIAMOND_AXE,
				Material.DIAMOND_HOE,
				Material.DIAMOND_PICKAXE,
				Material.DIAMOND_SHOVEL,
				Material.NETHERITE_AXE,
				Material.NETHERITE_HOE,
				Material.NETHERITE_PICKAXE,
				Material.NETHERITE_SHOVEL,
				Material.SHEARS);
        
		ItemStack offhand = event.getPlayer().getInventory().getItemInOffHand();
		
        if (stack == null || !material.contains(stack.getType()))
        {
            PrintUtils.sendMessage(event.getPlayer(),"Valid Item Not Detected in Offhand.");
            return false;
        }
        
        if (offhand.containsEnchantment(Enchantment.SILK_TOUCH)) 
        {
        	PrintUtils.sendMessage(event.getPlayer(),"Current held item is already enchanted with Silk Touch.");
            return false;
        }
        
        SpellParticles.drawCylinder(event.getPlayer().getLocation(), 1, 50, 4, 1, Particle.ENCHANTMENT_TABLE, null);
		event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_SMITHING_TABLE_USE, SoundCategory.MASTER, 1, 1);
        offhand.addUnsafeEnchantment(Enchantment.SILK_TOUCH, 1);
        
		return true;
	}

}
