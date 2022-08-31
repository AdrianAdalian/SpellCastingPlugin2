package com.perceus.spellcasting2.robes;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapelessRecipe;

import fish.yukiemeralis.eden.Eden;
import fish.yukiemeralis.eden.utils.ItemUtils;

public class GeoRobe_Boots
{
	static ItemStack final_item;
	
	public static void Init() 
	{
		final_item = new ItemStack(Material.LEATHER_BOOTS);
		ItemUtils.applyName(final_item, "§r§fBoots of §6Geo§f");
		ItemUtils.applyLore(final_item,"§r§fElement: §6Geo§f.","§r§fLeather boots infused with §6Geo§f energy.","§r§fWhile all pieces of the Geo Robe set","§r§fare worn, grants Lv1 damage resistance.","§r§f+25 §9mana§f regen/s.");
		ItemUtils.saveToNamespacedKey(final_item, "spellarmoritem_geo", "true");
	}
	
	public static void Register()
	{
		
		NamespacedKey key = new NamespacedKey(Eden.getInstance(), "georobe_boots");
		ShapelessRecipe recipe = new ShapelessRecipe(key, final_item);
		
		recipe.addIngredient(Material.LEATHER_BOOTS);
		recipe.addIngredient(Material.BRICK);
		
		Bukkit.addRecipe(recipe);
		
	}
	public static ItemStack getFinal_item()
	{
		return final_item;
	}
}
