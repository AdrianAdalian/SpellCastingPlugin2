package com.perceus.spellcasting2.spellitem_recipe;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapelessRecipe;

import com.perceus.spellcasting2.BaseSpellCapsule;
import com.perceus.spellcasting2.spellitem_spell.MagicWeapon_WandOfWater;

import fish.yukiemeralis.eden.Eden;

public class MagicWeapon_WandOfWater_Recipe
{
	static ItemStack final_item;
	
	public static void Init() 
	{
		BaseSpellCapsule WandOfWater = new MagicWeapon_WandOfWater();
		final_item = WandOfWater.generate();
	}
	
	public static void Register()
	{
		
		NamespacedKey key = new NamespacedKey(Eden.getInstance(), "magic_weapon_wand_of_water");
		ShapelessRecipe recipe = new ShapelessRecipe(key, final_item);
		
		recipe.addIngredient(Material.STICK);
		recipe.addIngredient(Material.LAPIS_LAZULI);
		
		Bukkit.addRecipe(recipe);
		
	}
	public static ItemStack getFinal_item()
	{
		return final_item;
	}
}
