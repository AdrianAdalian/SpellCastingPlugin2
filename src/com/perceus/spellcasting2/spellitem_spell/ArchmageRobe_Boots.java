package com.perceus.spellcasting2.spellitem_spell;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import fish.yukiemeralis.eden.Eden;
import fish.yukiemeralis.eden.utils.ItemUtils;

public class ArchmageRobe_Boots
{
	static ItemStack final_item;
	
	public static void Init() 
	{
		final_item = new ItemStack(Material.LEATHER_BOOTS);
		ItemUtils.applyName(final_item, "§r§fArchmage's Boots");
		ItemUtils.applyLore(final_item,"§r§fElement: §bE§ft§bh§fe§br.","§r§fLeather boots infused with §bE§ft§bh§fe§br energy.","§r§fWhile all pieces of the Archmage's robe set","§r§fare worn, grants +150 §9mana§f regen/s.");
		ItemUtils.saveToNamespacedKey(final_item, "spellarmoritem_archmage", "true");
		LeatherArmorMeta data = (LeatherArmorMeta) final_item.getItemMeta();
		data.setColor(Color.NAVY);
		final_item.setItemMeta(data);
	}
	
	public static void Register()
	{
		
		NamespacedKey key = new NamespacedKey(Eden.getInstance(), "archmagerobe_boots");
		ShapelessRecipe recipe = new ShapelessRecipe(key, final_item);
		
		recipe.addIngredient(Material.LEATHER_BOOTS);
		recipe.addIngredient(Material.NETHER_STAR);
		recipe.addIngredient(Material.GHAST_TEAR);
		recipe.addIngredient(Material.LAPIS_LAZULI);
		Bukkit.addRecipe(recipe);
		
	}
	public static ItemStack getFinal_item()
	{
		return final_item;
	}
}
