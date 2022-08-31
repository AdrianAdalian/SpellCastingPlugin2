package com.perceus.spellcasting2.robes;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import fish.yukiemeralis.eden.Eden;
import fish.yukiemeralis.eden.utils.ItemUtils;

public class HolyRobe_Tunic
{
	static ItemStack final_item;
	
	public static void Init() 
	{
		final_item = new ItemStack(Material.LEATHER_CHESTPLATE);
		ItemUtils.applyName(final_item, "§r§fTunic of §r§f§o§lHoly§r§f");
		ItemUtils.applyLore(final_item,"§r§fElement: §r§f§o§lHoly§r§f.","§r§fA leather tunic infused with §r§f§o§lHoly§r§f energy.","§r§fWhile all pieces of the Holy Robe set","§r§fare worn, grants regeneration.","§r§f+25 §9mana§f regen/s.");
		ItemUtils.saveToNamespacedKey(final_item, "spellarmoritem_holy", "true");
		LeatherArmorMeta data = (LeatherArmorMeta) final_item.getItemMeta();
		data.setColor(Color.WHITE);
		final_item.setItemMeta(data);
	}
	
	public static void Register()
	{
		
		NamespacedKey key = new NamespacedKey(Eden.getInstance(), "holyrobe_tunic");
		ShapelessRecipe recipe = new ShapelessRecipe(key, final_item);
		
		recipe.addIngredient(Material.LEATHER_CHESTPLATE);
		recipe.addIngredient(Material.GHAST_TEAR);
		
		Bukkit.addRecipe(recipe);
		
	}
	public static ItemStack getFinal_item()
	{
		return final_item;
	}
}
