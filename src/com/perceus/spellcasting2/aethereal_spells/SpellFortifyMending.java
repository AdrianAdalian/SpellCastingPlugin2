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

public class SpellFortifyMending extends BaseSpellCapsule
{

	public SpellFortifyMending()
	{
		super(Material.ENCHANTED_BOOK, "§r§f§ko§r§bAethereal Tome§f: Fortify Mending§r§f§ko§r", "SpellFortifyMending", 1000, false, "§r§fElement: §r§b§l§oAethereal§r§f.","§r§fSpell Type: §bUtility§f.","§r§fA spelltome containing §r§b§l§oAethereal§r§f energy.","§r§fAn incantation is written within that allows","§r§fthe caster to enchant items.","§r§fEnchant offhand applicable item with mending.","§r§fMana cost: 1000 §r§9mana§r§f.");
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
				Material.WOODEN_SWORD,
				Material.STONE_AXE,
				Material.STONE_HOE,
				Material.STONE_PICKAXE,
				Material.STONE_SHOVEL,
				Material.STONE_SWORD,
				Material.IRON_HOE,
				Material.IRON_AXE,
				Material.IRON_PICKAXE,
				Material.IRON_SWORD,
				Material.IRON_SHOVEL,
				Material.GOLDEN_AXE,
				Material.GOLDEN_HOE,
				Material.GOLDEN_PICKAXE,
				Material.GOLDEN_SHOVEL,
				Material.GOLDEN_SWORD,
				Material.DIAMOND_AXE,
				Material.DIAMOND_HOE,
				Material.DIAMOND_PICKAXE,
				Material.DIAMOND_SHOVEL,
				Material.DIAMOND_SWORD,
				Material.NETHERITE_AXE,
				Material.NETHERITE_HOE,
				Material.NETHERITE_PICKAXE,
				Material.NETHERITE_SHOVEL,
				Material.NETHERITE_SWORD,
				Material.LEATHER_BOOTS,
				Material.LEATHER_CHESTPLATE,
				Material.LEATHER_HELMET,
				Material.LEATHER_LEGGINGS,
				Material.CHAINMAIL_BOOTS,
				Material.CHAINMAIL_CHESTPLATE,
				Material.CHAINMAIL_HELMET,
				Material.CHAINMAIL_LEGGINGS,
				Material.GOLDEN_BOOTS,
				Material.GOLDEN_CHESTPLATE,
				Material.GOLDEN_LEGGINGS,
				Material.GOLDEN_HELMET,
				Material.IRON_BOOTS,
				Material.IRON_CHESTPLATE,
				Material.IRON_LEGGINGS,
				Material.IRON_HELMET,
				Material.DIAMOND_BOOTS,
				Material.DIAMOND_LEGGINGS,
				Material.DIAMOND_HELMET,
				Material.DIAMOND_CHESTPLATE,
				Material.NETHERITE_BOOTS,
				Material.NETHERITE_CHESTPLATE,
				Material.NETHERITE_LEGGINGS,
				Material.NETHERITE_HELMET,
				Material.BOW,
				Material.FISHING_ROD,
				Material.SHEARS,
				Material.TURTLE_HELMET,
				Material.TRIDENT,
				Material.CROSSBOW,
				Material.SHIELD);
        
		ItemStack offhand = event.getPlayer().getInventory().getItemInOffHand();
		
        if (stack == null || !material.contains(stack.getType()))
        {
            PrintUtils.sendMessage(event.getPlayer(),"Valid Item Not Detected in Offhand.");
            return false;
        }
        
        if (offhand.containsEnchantment(Enchantment.MENDING)) 
        {
        	PrintUtils.sendMessage(event.getPlayer(),"Current held item is already enchanted with Mending.");
            return false;
        }
        
        SpellParticles.drawCylinder(event.getPlayer().getLocation(), 1, 50, 4, 1, Particle.ENCHANTMENT_TABLE, null);
		event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_SMITHING_TABLE_USE, SoundCategory.MASTER, 1, 1);
        offhand.addUnsafeEnchantment(Enchantment.MENDING, 1);
        
		return true;
	}

}
