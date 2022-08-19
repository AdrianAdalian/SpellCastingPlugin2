package com.perceus.spellcasting2.spellitem_recipe;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapelessRecipe;

import com.perceus.spellcasting2.BaseSpellCapsule;
import com.perceus.spellcasting2.ancient_spells.SpellCreateElderGuardian;

import fish.yukiemeralis.eden.Eden;

public class SpellCreateElderGuardian_Recipe
{
	static ItemStack final_item;
	
	public static void Init() 
	{
		BaseSpellCapsule g = new SpellCreateElderGuardian();
		final_item = g.generate();
	}
	
	public static void Register()
	{
		
		NamespacedKey key = new NamespacedKey(Eden.getInstance(), "spellcreateelderguardian_recipe");
		ShapelessRecipe recipe = new ShapelessRecipe(key, final_item);

		recipe.addIngredient(Material.NETHER_STAR);
		recipe.addIngredient(Material.LAPIS_LAZULI);
		recipe.addIngredient(Material.PRISMARINE_CRYSTALS);
		recipe.addIngredient(Material.PRISMARINE_SHARD);
		
		Bukkit.addRecipe(recipe);
		
	}
	public static ItemStack getFinal_item()
	{
		return final_item;
	}
}
