package com.perceus.spellcasting2.spellitem_recipe;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapelessRecipe;

import com.perceus.spellcasting2.BaseSpellCapsule;
import com.perceus.spellcasting2.spellitem_spell.SpellItem_SmokeBomb;

import fish.yukiemeralis.eden.Eden;

public class SpellItem_SmokeBomb_Recipe
{
	static ItemStack final_item;
	
	public static void Init() 
	{
		BaseSpellCapsule SmokeBomb = new SpellItem_SmokeBomb();
		ItemStack stack = SmokeBomb.generate();
		final_item = stack.clone();
		final_item.setAmount(3);
	}
	
	public static void Register()
	{
		
		NamespacedKey key = new NamespacedKey(Eden.getInstance(), "spellitem_smokebomb");
		ShapelessRecipe recipe = new ShapelessRecipe(key, final_item);

		recipe.addIngredient(Material.COAL);
		recipe.addIngredient(Material.GUNPOWDER);
		recipe.addIngredient(Material.CLAY_BALL);
		
		Bukkit.addRecipe(recipe);
		
	}
	public static ItemStack getFinal_item()
	{
		return final_item;
	}
}
