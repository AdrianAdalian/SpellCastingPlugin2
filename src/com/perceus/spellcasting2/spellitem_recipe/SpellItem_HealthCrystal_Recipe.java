package com.perceus.spellcasting2.spellitem_recipe;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapelessRecipe;

import com.perceus.spellcasting2.BaseSpellCapsule;
import com.perceus.spellcasting2.spellitem_spell.SpellItem_HealthCrystal;

import fish.yukiemeralis.eden.Eden;

public class SpellItem_HealthCrystal_Recipe
{
	static ItemStack final_item;
	
	public static void Init() 
	{
		BaseSpellCapsule HealthCrystal = new SpellItem_HealthCrystal();
		ItemStack stack = HealthCrystal.generate();
		final_item = stack.clone();
		final_item.setAmount(2);
	}
	
	public static void Register()
	{
		
		NamespacedKey key = new NamespacedKey(Eden.getInstance(), "spellitem_healthcrystal");
		ShapelessRecipe recipe = new ShapelessRecipe(key, final_item);
		
		recipe.addIngredient(Material.QUARTZ);
		recipe.addIngredient(Material.GHAST_TEAR);
		recipe.addIngredient(Material.EMERALD);
		
		Bukkit.addRecipe(recipe);
		
	}
	public static ItemStack getFinal_item()
	{
		return final_item;
	}
}
