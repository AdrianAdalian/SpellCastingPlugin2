package com.perceus.spellcasting2.robes;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapelessRecipe;

import fish.yukiemeralis.eden.Eden;
import fish.yukiemeralis.eden.utils.ItemUtils;

public class FireRobe_Boots
{
	static ItemStack final_item;
	
	public static void Init() 
	{
		final_item = new ItemStack(Material.LEATHER_BOOTS);
		ItemUtils.applyName(final_item, "§r§fBoots of §cFire§f");
		ItemUtils.applyLore(final_item,"§r§f§oRobe Item.","§r§fElement: §cFire§f.","§r§fA leather boots infused with §cFire§f energy.","§r§fWhile all pieces of the fire robe set are worn,","§r§fgrant total fire immunity.");
		ItemUtils.saveToNamespacedKey(final_item, "spellarmoritem_fire", "true");
	}
	
	public static void Register()
	{
		
		NamespacedKey key = new NamespacedKey(Eden.getInstance(), "firerobe_boots");
		ShapelessRecipe recipe = new ShapelessRecipe(key, final_item);
		
		recipe.addIngredient(Material.LEATHER_BOOTS);
		recipe.addIngredient(Material.BLAZE_POWDER);
		
		Bukkit.addRecipe(recipe);
		
	}
	public static ItemStack getFinal_item()
	{
		return final_item;
	}
}
