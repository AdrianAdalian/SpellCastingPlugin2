package com.perceus.spellcasting2.spellitem_recipe;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapelessRecipe;

import com.perceus.spellcasting2.BaseSpellCapsule;
import com.perceus.spellcasting2.spellitem_spell.SpellItem_ManaCrystal;

import fish.yukiemeralis.eden.Eden;

public class SpellItem_ManaCrystal_Recipe
{
	static ItemStack final_item;
	
	public static void Init() 
	{
		BaseSpellCapsule ManaCrystal = new SpellItem_ManaCrystal();
		ItemStack stack = ManaCrystal.generate();
		final_item = stack.clone();
		final_item.setAmount(2);
	}
	
	public static void Register()
	{
		
		NamespacedKey key = new NamespacedKey(Eden.getInstance(), "spellitem_manacrystal");
		ShapelessRecipe recipe = new ShapelessRecipe(key, final_item);
		
		recipe.addIngredient(Material.QUARTZ);
		recipe.addIngredient(Material.GHAST_TEAR);
		recipe.addIngredient(Material.LAPIS_LAZULI);
		
		Bukkit.addRecipe(recipe);
		
	}
	public static ItemStack getFinal_item()
	{
		return final_item;
	}
}
