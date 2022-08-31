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

public class WaterRobe_Pants
{
	static ItemStack final_item;
	
	public static void Init() 
	{
		final_item = new ItemStack(Material.LEATHER_LEGGINGS);
		ItemUtils.applyName(final_item, "§r§fPants of §9Water§f");
		ItemUtils.applyLore(final_item,"§r§fElement: §9Water§f.","§r§fLeather pants infused with §9Water§f energy.","§r§fWhile all pieces of the Water Robe set","§r§fare worn, grants water breathing.","§r§f+25 §9mana§f regen/s.");
		ItemUtils.saveToNamespacedKey(final_item, "spellarmoritem_water", "true");
		LeatherArmorMeta data = (LeatherArmorMeta) final_item.getItemMeta();
		data.setColor(Color.AQUA);
		final_item.setItemMeta(data);
	}
	
	public static void Register()
	{
		
		NamespacedKey key = new NamespacedKey(Eden.getInstance(), "waterrobe_pants");
		ShapelessRecipe recipe = new ShapelessRecipe(key, final_item);
		
		recipe.addIngredient(Material.LEATHER_LEGGINGS);
		recipe.addIngredient(Material.LAPIS_LAZULI);
		
		Bukkit.addRecipe(recipe);
		
	}
	public static ItemStack getFinal_item()
	{
		return final_item;
	}
}
