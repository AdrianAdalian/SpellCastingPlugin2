package com.perceus.spellcasting2.spellitem_recipe;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapelessRecipe;

import com.perceus.spellcasting2.BaseSpellCapsule;
import com.perceus.spellcasting2.spellitem_spell.SpellItem_BloodCrystalPlus;

import fish.yukiemeralis.eden.Eden;

public class SpellItem_BloodCrystalPlus_Recipe
{
	static ItemStack final_item;
	
	public static void Init() 
	{
		BaseSpellCapsule bc2 = new SpellItem_BloodCrystalPlus();
		final_item = bc2.generate();
	}
	
	public static void Register()
	{
		
		NamespacedKey key = new NamespacedKey(Eden.getInstance(), "spellitem_bloodcrystalplus");
		ShapelessRecipe recipe = new ShapelessRecipe(key, final_item);
		
		recipe.addIngredient(Material.QUARTZ);
		recipe.addIngredient(Material.REDSTONE);
		recipe.addIngredient(Material.NETHERITE_INGOT);
		recipe.addIngredient(Material.NETHER_STAR);

		Bukkit.addRecipe(recipe);
		
	}
	public static ItemStack getFinal_item()
	{
		return final_item;
	}
}
