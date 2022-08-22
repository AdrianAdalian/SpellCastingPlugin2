package com.perceus.spellcasting2.spellitem_recipe;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapelessRecipe;

import com.perceus.spellcasting2.BaseSpellCapsule;
import com.perceus.spellcasting2.spellitem_spell.SpellItem_EtherCrystal;

import fish.yukiemeralis.eden.Eden;

public class SpellItem_EtherCrystal_Recipe
{
	static ItemStack final_item;
	
	public static void Init() 
	{
		BaseSpellCapsule ec = new SpellItem_EtherCrystal();
		final_item = ec.generate();
	}
	
	public static void Register()
	{
		
		NamespacedKey key = new NamespacedKey(Eden.getInstance(), "spellitem_ethercrystal");
		ShapelessRecipe recipe = new ShapelessRecipe(key, final_item);
		
		recipe.addIngredient(Material.QUARTZ);
		recipe.addIngredient(Material.DIAMOND);
		recipe.addIngredient(Material.GHAST_TEAR);
		recipe.addIngredient(Material.DRAGON_BREATH);
		
		Bukkit.addRecipe(recipe);
		
	}
	public static ItemStack getFinal_item()
	{
		return final_item;
	}
}
