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

public class VoidRobe_Tunic
{
	static ItemStack final_item;
	
	public static void Init() 
	{
		final_item = new ItemStack(Material.LEATHER_CHESTPLATE);
		ItemUtils.applyName(final_item, "§r§fTunic of §r§3§lVOID§r§f");
		ItemUtils.applyLore(final_item,"§r§fElement: §r§3§lVOID§r§f.","§r§fA leather tunic infused with §r§3§lVOID§r§f energy.","§r§fWhile all pieces of the Void Robe set","§r§fare worn, grants slow falling.","§r§f+25 §9mana§f regen/s.");
		ItemUtils.saveToNamespacedKey(final_item, "spellarmoritem_void", "true");
		LeatherArmorMeta data = (LeatherArmorMeta) final_item.getItemMeta();
		data.setColor(Color.TEAL);
		final_item.setItemMeta(data);
	}
	
	public static void Register()
	{
		
		NamespacedKey key = new NamespacedKey(Eden.getInstance(), "voidrobe_tunic");
		ShapelessRecipe recipe = new ShapelessRecipe(key, final_item);
		
		recipe.addIngredient(Material.LEATHER_CHESTPLATE);
		recipe.addIngredient(Material.ENDER_PEARL);
		
		Bukkit.addRecipe(recipe);
		
	}
	public static ItemStack getFinal_item()
	{
		return final_item;
	}
}
