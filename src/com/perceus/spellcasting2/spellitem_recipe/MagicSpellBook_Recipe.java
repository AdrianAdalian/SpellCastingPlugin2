package com.perceus.spellcasting2.spellitem_recipe;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapelessRecipe;

import fish.yukiemeralis.eden.Eden;
import fish.yukiemeralis.eden.utils.ItemUtils;

public class MagicSpellBook_Recipe 
{
	static ItemStack final_item;
	
	public static void Init() 
	{
		final_item = new ItemStack(Material.ENCHANTED_BOOK);
		ItemUtils.applyName(final_item, "§r§f§ko§r§f§l§4M§ca§eg§ai§bc§1a§dl §fSpellbook§r§f§ko§r");
		ItemUtils.applyLore(final_item,"§r§fA spellbook with magic properties,","§r§fcontaining the many elements of Minecraft.","§r§fRight-Click to open the spellbook while in the hotbar.","§r§fLeft-Click to engage/disengage spells.");
		ItemUtils.saveToNamespacedKey(final_item, "magic_spell_book", "true");
	}
	
	public static void Register()
	{
		
		NamespacedKey key = new NamespacedKey(Eden.getInstance(), "magic_spell_book");
		ShapelessRecipe recipe = new ShapelessRecipe(key, final_item);
		
		recipe.addIngredient(Material.BOOK);
		recipe.addIngredient(Material.DRAGON_BREATH);
		
		Bukkit.addRecipe(recipe);
		
	}
	public static ItemStack getFinal_item()
	{
		return final_item;
	}
}
