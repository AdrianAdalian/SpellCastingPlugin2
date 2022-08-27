package com.perceus.spellcasting2.spellitem_recipe;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapelessRecipe;

import com.perceus.spellcasting2.BaseSpellCapsule;
import com.perceus.spellcasting2.spellitem_spell.LivingArmor_DragonScaleLeggings;

import fish.yukiemeralis.eden.Eden;

public class LivingArmor_DragonScaleLeggings_Recipe
{
	static ItemStack final_item;
	
	public static void Init() 
	{
		BaseSpellCapsule legs = new LivingArmor_DragonScaleLeggings();
		final_item = legs.generate();
	}
	
	public static void Register()
	{
		
		NamespacedKey key = new NamespacedKey(Eden.getInstance(), "dragonleggings");
		ShapelessRecipe recipe = new ShapelessRecipe(key, final_item);

		recipe.addIngredient(Material.NETHERITE_LEGGINGS);
		recipe.addIngredient(Material.NETHER_STAR);
		recipe.addIngredient(Material.NETHERITE_BLOCK);
		
		Bukkit.addRecipe(recipe);
		
	}
	public static ItemStack getFinal_item()
	{
		return final_item;
	}
}
