package com.perceus.spellcasting2.spellitem_recipe;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapelessRecipe;

import com.perceus.spellcasting2.BaseSpellCapsule;
import com.perceus.spellcasting2.spellitem_spell.SpellItem_Fireball;

import fish.yukiemeralis.eden.Eden;

public class SpellItem_Fireball_Recipe
{
	static ItemStack final_item;
	
	public static void Init() 
	{
		BaseSpellCapsule SpellItem_Fireball = new SpellItem_Fireball();
		final_item = SpellItem_Fireball.generate();
	}
	
	public static void Register()
	{
		
		NamespacedKey key = new NamespacedKey(Eden.getInstance(), "spellitem_fireball");
		ShapelessRecipe recipe = new ShapelessRecipe(key, final_item);

		recipe.addIngredient(Material.COAL);
		recipe.addIngredient(Material.BLAZE_POWDER);
		
		Bukkit.addRecipe(recipe);
		
	}
	public static ItemStack getFinal_item()
	{
		return final_item;
	}
}
