package com.perceus.spellcasting2.spellitem_recipe;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapelessRecipe;

import com.perceus.spellcasting2.BaseSpellCapsule;
import com.perceus.spellcasting2.spellitem_spell.LivingArmor_DragonScaleChestplate;

import fish.yukiemeralis.eden.Eden;

public class LivingArmor_DragonScaleChestplate_Recipe
{
	static ItemStack final_item;
	
	public static void Init() 
	{
		BaseSpellCapsule chest = new LivingArmor_DragonScaleChestplate();
		final_item = chest.generate();

	}
	
	public static void Register()
	{
		
		NamespacedKey key = new NamespacedKey(Eden.getInstance(), "dragonchestplate");
		ShapelessRecipe recipe = new ShapelessRecipe(key, final_item);

		recipe.addIngredient(Material.NETHERITE_CHESTPLATE);
		recipe.addIngredient(Material.NETHER_STAR);
		recipe.addIngredient(Material.NETHERITE_BLOCK);
		
		Bukkit.addRecipe(recipe);
		
	}
	public static ItemStack getFinal_item()
	{
		return final_item;
	}
}
