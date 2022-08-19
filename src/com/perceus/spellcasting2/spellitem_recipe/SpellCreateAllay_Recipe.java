package com.perceus.spellcasting2.spellitem_recipe;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapelessRecipe;

import com.perceus.spellcasting2.BaseSpellCapsule;
import com.perceus.spellcasting2.ancient_spells.SpellCreateAllay;

import fish.yukiemeralis.eden.Eden;

public class SpellCreateAllay_Recipe
{
	static ItemStack final_item;
	
	public static void Init() 
	{
		BaseSpellCapsule allay = new SpellCreateAllay();
		final_item = allay.generate();
	}
	
	public static void Register()
	{
		
		NamespacedKey key = new NamespacedKey(Eden.getInstance(), "spellcreateallay_recipe");
		ShapelessRecipe recipe = new ShapelessRecipe(key, final_item);

		recipe.addIngredient(Material.NETHER_STAR);
		recipe.addIngredient(Material.GHAST_TEAR);
		recipe.addIngredient(Material.PHANTOM_MEMBRANE);
		recipe.addIngredient(Material.DRAGON_BREATH);
		
		Bukkit.addRecipe(recipe);
		
	}
	public static ItemStack getFinal_item()
	{
		return final_item;
	}
}
