package com.perceus.spellcasting2;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;

import com.perceus.spellcasting2.accounts.StoredInventory;
import com.perceus.spellcasting2.recipe_book.SC2RecipeBook;
import com.perceus.spellcasting2.spellitem_recipe.MagicSpellBook_Recipe;
import com.perceus.spellcasting2.spellitem_recipe.MagicWeapon_WandOfFire_Recipe;
import com.perceus.spellcasting2.spellitem_recipe.MagicWeapon_WandOfGeo_Recipe;
import com.perceus.spellcasting2.spellitem_recipe.MagicWeapon_WandOfHoly_Recipe;
import com.perceus.spellcasting2.spellitem_recipe.MagicWeapon_WandOfStorm_Recipe;
import com.perceus.spellcasting2.spellitem_recipe.MagicWeapon_WandOfUnholy_Recipe;
import com.perceus.spellcasting2.spellitem_recipe.MagicWeapon_WandOfVoid_Recipe;
import com.perceus.spellcasting2.spellitem_recipe.MagicWeapon_WandOfWater_Recipe;
import com.perceus.spellcasting2.spellitem_recipe.SpellCreateAllay_Recipe;
import com.perceus.spellcasting2.spellitem_recipe.SpellCreateElderGuardian_Recipe;
import com.perceus.spellcasting2.spellitem_recipe.SpellCreateItem_Recipe;
import com.perceus.spellcasting2.spellitem_recipe.SpellCreateWarden_Recipe;
import com.perceus.spellcasting2.spellitem_recipe.SpellCreateWither_Recipe;
import com.perceus.spellcasting2.spellitem_recipe.SpellItem_AetherealScrollPlus_Recipe;
import com.perceus.spellcasting2.spellitem_recipe.SpellItem_AetherealScroll_Recipe;
import com.perceus.spellcasting2.spellitem_recipe.SpellItem_BloodCrystalPlus_Recipe;
import com.perceus.spellcasting2.spellitem_recipe.SpellItem_BloodCrystal_Recipe;
import com.perceus.spellcasting2.spellitem_recipe.SpellItem_EtherCrystal_Recipe;
import com.perceus.spellcasting2.spellitem_recipe.SpellItem_Fireball_Recipe;
import com.perceus.spellcasting2.spellitem_recipe.SpellItem_HealthCrystal_Recipe;
import com.perceus.spellcasting2.spellitem_recipe.SpellItem_ManaCrystal_Recipe;
import com.perceus.spellcasting2.spellitem_recipe.SpellItem_ProtectivePaste_Recipe;
import com.perceus.spellcasting2.spellitem_recipe.SpellItem_SmokeBomb_Recipe;
import com.perceus.spellcasting2.spellitem_recipe.SpellItem_Updraft_Recipe;
import com.perceus.spellcasting2.spellitem_recipe.SpellItem_XpCrystal_Recipe;

import fish.yukiemeralis.eden.core.CompletionsManager;
import fish.yukiemeralis.eden.core.CompletionsManager.ObjectMethodPair;
import fish.yukiemeralis.eden.module.EdenModule;
import fish.yukiemeralis.eden.module.EdenModule.ModInfo;
import fish.yukiemeralis.eden.module.annotation.ModuleFamily;
import fish.yukiemeralis.eden.utils.FileUtils;
import fish.yukiemeralis.eden.utils.PrintUtils;

@ModuleFamily
@ModInfo(
description = "A reimagined version of my original spell casting plugin.", 
maintainer = "Perceus Adalian (David Willy)", 
modIcon = Material.NETHER_STAR, 
modName = "§4S§cp§6e§el§2l§aC§ba§3s§1t§9i§dn§5g§fPlugin2", 
supportedApiVersions = { "v1_19_R1" }, 
version = "1.4.1")
public class Spells extends EdenModule
{
	@Override
	public void onEnable()
	{
		
		try
		{
			CompletionsManager.registerCompletion("ALLSPELLS", new ObjectMethodPair(this, "allSpells"), true);
		} 
		catch (NoSuchMethodException e)
		{
			e.printStackTrace();
		}
		
		FileUtils.ensureFolder("./plugins/Eden/playerdata/storedinventories/");
		FileUtils.ensureFolder("./plugins/Eden/playerdata/data/");
		PrintUtils.log("§4S§cp§6e§el§2l§aC§ba§3s§1t§9i§dn§5g§fPlugin2 Loaded Successfully.");
		PrintUtils.log("'You're a wizard Harry.'");
		PrintUtils.log(CastListener.spell_registry.size() + " Spells Loaded.");

		SC2RecipeBook.Init();
		SC2RecipeBook.Register();
		
		MagicSpellBook_Recipe.Init();
		MagicSpellBook_Recipe.Register();
		
		//weapon recipes
		MagicWeapon_WandOfFire_Recipe.Init();
		MagicWeapon_WandOfFire_Recipe.Register();
		
		MagicWeapon_WandOfGeo_Recipe.Init();
		MagicWeapon_WandOfGeo_Recipe.Register();
		
		MagicWeapon_WandOfUnholy_Recipe.Init();
		MagicWeapon_WandOfUnholy_Recipe.Register();
		
		MagicWeapon_WandOfHoly_Recipe.Init();
		MagicWeapon_WandOfHoly_Recipe.Register();
		
		MagicWeapon_WandOfWater_Recipe.Init();
		MagicWeapon_WandOfWater_Recipe.Register();
		
		MagicWeapon_WandOfVoid_Recipe.Init();
		MagicWeapon_WandOfVoid_Recipe.Register();
		
		MagicWeapon_WandOfStorm_Recipe.Init();
		MagicWeapon_WandOfStorm_Recipe.Register();
		
		//spell item recipes
		SpellItem_Fireball_Recipe.Init();
		SpellItem_Fireball_Recipe.Register();
		
		SpellItem_ManaCrystal_Recipe.Init();
		SpellItem_ManaCrystal_Recipe.Register();
		
		SpellItem_ProtectivePaste_Recipe.Init();
		SpellItem_ProtectivePaste_Recipe.Register();
		
		SpellItem_HealthCrystal_Recipe.Init();
		SpellItem_HealthCrystal_Recipe.Register();
		
		SpellItem_SmokeBomb_Recipe.Init();
		SpellItem_SmokeBomb_Recipe.Register();
		
		SpellItem_XpCrystal_Recipe.Init();
		SpellItem_XpCrystal_Recipe.Register();
		
		SpellItem_Updraft_Recipe.Init();
		SpellItem_Updraft_Recipe.Register();
		
		SpellItem_AetherealScroll_Recipe.Init();
		SpellItem_AetherealScroll_Recipe.Register();
		
		SpellItem_AetherealScrollPlus_Recipe.Init();
		SpellItem_AetherealScrollPlus_Recipe.Register();
		
		SpellItem_BloodCrystal_Recipe.Init();
		SpellItem_BloodCrystal_Recipe.Register();
		
		SpellItem_BloodCrystalPlus_Recipe.Init();
		SpellItem_BloodCrystalPlus_Recipe.Register();
		
		SpellCreateAllay_Recipe.Init();
		SpellCreateAllay_Recipe.Register();
		
		SpellCreateElderGuardian_Recipe.Init();
		SpellCreateElderGuardian_Recipe.Register();
		
		SpellCreateWarden_Recipe.Init();
		SpellCreateWarden_Recipe.Register();
		
		SpellCreateWither_Recipe.Init();
		SpellCreateWither_Recipe.Register();
		
		SpellItem_EtherCrystal_Recipe.Init();
		SpellItem_EtherCrystal_Recipe.Register();
		
		SpellCreateItem_Recipe.Init();
		SpellCreateItem_Recipe.Register();
		
	}

	@Override
	public void onDisable()
	{
		StoredInventory.save();
		Bukkit.getOnlinePlayers().stream().forEach(v -> v.kickPlayer("The server has closed."));
		PrintUtils.log("Lay down your wand, Harry.");
	}
	
	public List<String> allSpells()
	{
		return new ArrayList<>(CastListener.spell_registry.keySet());
	}
	
}