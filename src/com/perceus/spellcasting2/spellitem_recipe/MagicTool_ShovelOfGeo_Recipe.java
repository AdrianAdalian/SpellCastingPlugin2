package com.perceus.spellcasting2.spellitem_recipe;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapelessRecipe;

import com.perceus.spellcasting2.BaseSpellCapsule;
import com.perceus.spellcasting2.spellitem_spell.MagicTool_ShovelOfGeo;

import fish.yukiemeralis.eden.Eden;

public class MagicTool_ShovelOfGeo_Recipe
{
	static ItemStack final_item;
	
	public static void Init() 
	{
		BaseSpellCapsule shovel = new MagicTool_ShovelOfGeo();
		final_item = shovel.generate();
	}
	
	public static void Register()
	{
		
		NamespacedKey key = new NamespacedKey(Eden.getInstance(), "magictool_shovelofgeo");
		ShapelessRecipe recipe = new ShapelessRecipe(key, final_item);

		recipe.addIngredient(Material.NETHERITE_SHOVEL);
		recipe.addIngredient(Material.NETHER_STAR);
		recipe.addIngredient(Material.BRICK);
		
		Bukkit.addRecipe(recipe);
		
	}
	public static ItemStack getFinal_item()
	{
		return final_item;
	}
}
