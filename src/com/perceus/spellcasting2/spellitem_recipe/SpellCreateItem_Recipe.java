package com.perceus.spellcasting2.spellitem_recipe;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapelessRecipe;

import com.perceus.spellcasting2.BaseSpellCapsule;
import com.perceus.spellcasting2.ancient_spells.SpellCreateItem;

import fish.yukiemeralis.eden.Eden;

public class SpellCreateItem_Recipe
{
	static ItemStack final_item;
	
	public static void Init() 
	{
		BaseSpellCapsule ci = new SpellCreateItem();
		final_item = ci.generate();
	}
	
	public static void Register()
	{
		
		NamespacedKey key = new NamespacedKey(Eden.getInstance(), "spellcreateitem_recipe");
		ShapelessRecipe recipe = new ShapelessRecipe(key, final_item);

		recipe.addIngredient(Material.GHAST_TEAR);
		recipe.addIngredient(Material.GOLD_INGOT);
		recipe.addIngredient(Material.BLAZE_POWDER);
		recipe.addIngredient(Material.NETHER_STAR);
		
		Bukkit.addRecipe(recipe);
		
	}
	public static ItemStack getFinal_item()
	{
		return final_item;
	}
}
