package com.perceus.spellcasting2.spellitem_recipe;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapelessRecipe;

import com.perceus.spellcasting2.BaseSpellCapsule;
import com.perceus.spellcasting2.spellitem_spell.Armament_War;

import fish.yukiemeralis.eden.Eden;

public class Armament_War_Recipe
{
	static ItemStack final_item;
	
	public static void Init() 
	{
		BaseSpellCapsule war = new Armament_War();
		final_item = war.generate();
	}
	
	public static void Register()
	{
		
		NamespacedKey key = new NamespacedKey(Eden.getInstance(), "war");
		ShapelessRecipe recipe = new ShapelessRecipe(key, final_item);

		recipe.addIngredient(Material.NETHERITE_SWORD);
		recipe.addIngredient(Material.NETHER_STAR);
		recipe.addIngredient(Material.AMETHYST_SHARD);
		
		Bukkit.addRecipe(recipe);
		
	}
	public static ItemStack getFinal_item()
	{
		return final_item;
	}
}
