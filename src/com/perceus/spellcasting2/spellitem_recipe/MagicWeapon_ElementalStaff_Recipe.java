package com.perceus.spellcasting2.spellitem_recipe;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapelessRecipe;

import com.perceus.spellcasting2.BaseSpellCapsule;
import com.perceus.spellcasting2.spellitem_spell.MagicWeapon_ElementalStaff;

import fish.yukiemeralis.eden.Eden;

public class MagicWeapon_ElementalStaff_Recipe
{
	static ItemStack final_item;
	
	public static void Init() 
	{
		BaseSpellCapsule eStaff = new MagicWeapon_ElementalStaff();
		final_item = eStaff.generate();
	}
	
	public static void Register()
	{
		
		NamespacedKey key = new NamespacedKey(Eden.getInstance(), "magic_weapon_elemental_staff");
		ShapelessRecipe recipe = new ShapelessRecipe(key, final_item);
		
		recipe.addIngredient(Material.STICK);
		recipe.addIngredient(Material.BLAZE_POWDER);
		recipe.addIngredient(Material.LAPIS_LAZULI);
		recipe.addIngredient(Material.AMETHYST_SHARD);
		recipe.addIngredient(Material.BRICK);
		
		Bukkit.addRecipe(recipe);
		
	}
	public static ItemStack getFinal_item()
	{
		return final_item;
	}
}
