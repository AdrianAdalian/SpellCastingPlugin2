package com.perceus.spellcasting2.recipe_book;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapelessRecipe;

import fish.yukiemeralis.eden.Eden;
import fish.yukiemeralis.eden.utils.ItemUtils;

public class SC2RecipeBook
{
	static ItemStack final_item;
	
	public static void Init() 
	{
		final_item = new ItemStack(Material.KNOWLEDGE_BOOK);
		ItemUtils.applyName(final_item, "§r§fSpell Recipe Book");
		ItemUtils.applyLore(final_item,"§r§fA recipe book containing the known knowledge,","§r§fon how to craft consumable spells.","§r§fRight-Click to open the recipe book.","§r§fLeft-Click to navigate.","§r§fNote: All recipes are shapeless.","§r§fIf you lose this book, type '/spellrecipes'.");
		ItemUtils.saveToNamespacedKey(final_item, "sc2_recipe_book", "true");
	}
	
	public static void Register()
	{
		
		NamespacedKey key = new NamespacedKey(Eden.getInstance(), "sc2_recipe_book");
		ShapelessRecipe recipe = new ShapelessRecipe(key, final_item);
		
		recipe.addIngredient(Material.BOOK);
		
		Bukkit.addRecipe(recipe);
		
	}
	public static ItemStack getFinal_item()
	{
		return final_item;
	}
}
