package com.perceus.spellcasting2;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;

import com.perceus.spellcasting2.accounts.StoredInventory;
import com.perceus.spellcasting2.recipe_book.SC2RecipeBook;
import com.perceus.spellcasting2.robes.ArchmageRobe_Hat;
import com.perceus.spellcasting2.robes.FireRobe_Boots;
import com.perceus.spellcasting2.robes.FireRobe_Hat;
import com.perceus.spellcasting2.robes.FireRobe_Pants;
import com.perceus.spellcasting2.robes.FireRobe_Tunic;
import com.perceus.spellcasting2.robes.GeoRobe_Boots;
import com.perceus.spellcasting2.robes.GeoRobe_Hat;
import com.perceus.spellcasting2.robes.GeoRobe_Pants;
import com.perceus.spellcasting2.robes.GeoRobe_Tunic;
import com.perceus.spellcasting2.robes.HolyRobe_Boots;
import com.perceus.spellcasting2.robes.HolyRobe_Hat;
import com.perceus.spellcasting2.robes.HolyRobe_Pants;
import com.perceus.spellcasting2.robes.HolyRobe_Tunic;
import com.perceus.spellcasting2.robes.StormRobe_Boots;
import com.perceus.spellcasting2.robes.StormRobe_Hat;
import com.perceus.spellcasting2.robes.StormRobe_Pants;
import com.perceus.spellcasting2.robes.StormRobe_Tunic;
import com.perceus.spellcasting2.robes.UnholyRobe_Boots;
import com.perceus.spellcasting2.robes.UnholyRobe_Hat;
import com.perceus.spellcasting2.robes.UnholyRobe_Pants;
import com.perceus.spellcasting2.robes.UnholyRobe_Tunic;
import com.perceus.spellcasting2.robes.VoidRobe_Boots;
import com.perceus.spellcasting2.robes.VoidRobe_Hat;
import com.perceus.spellcasting2.robes.VoidRobe_Pants;
import com.perceus.spellcasting2.robes.VoidRobe_Tunic;
import com.perceus.spellcasting2.robes.WaterRobe_Boots;
import com.perceus.spellcasting2.robes.WaterRobe_Hat;
import com.perceus.spellcasting2.robes.WaterRobe_Pants;
import com.perceus.spellcasting2.robes.WaterRobe_Tunic;
import com.perceus.spellcasting2.spellitem_recipe.Armament_Artifice_Recipe;
import com.perceus.spellcasting2.spellitem_recipe.Armament_Firebrand_Recipe;
import com.perceus.spellcasting2.spellitem_recipe.Armament_Repentance_Recipe;
import com.perceus.spellcasting2.spellitem_recipe.Armament_War_Recipe;
import com.perceus.spellcasting2.spellitem_recipe.LivingArmor_DragonScaleBoots_Recipe;
import com.perceus.spellcasting2.spellitem_recipe.LivingArmor_DragonScaleChestplate_Recipe;
import com.perceus.spellcasting2.spellitem_recipe.LivingArmor_DragonScaleHelmet_Recipe;
import com.perceus.spellcasting2.spellitem_recipe.LivingArmor_DragonScaleLeggings_Recipe;
import com.perceus.spellcasting2.spellitem_recipe.MagicSpellBook_Recipe;
import com.perceus.spellcasting2.spellitem_recipe.MagicTool_PickaxeOfGeo_Recipe;
import com.perceus.spellcasting2.spellitem_recipe.MagicTool_ScytheOfUnholy_Recipe;
import com.perceus.spellcasting2.spellitem_recipe.MagicTool_ShovelOfGeo_Recipe;
import com.perceus.spellcasting2.spellitem_recipe.MagicWeapon_ElementalStaff_Recipe;
import com.perceus.spellcasting2.spellitem_recipe.MagicWeapon_SpiritualStaff_Recipe;
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
import com.perceus.spellcasting2.spellitem_spell.ArchmageRobe_Boots;
import com.perceus.spellcasting2.spellitem_spell.ArchmageRobe_Pants;
import com.perceus.spellcasting2.spellitem_spell.ArchmageRobe_Tunic;

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
modName = "SpellCastingPlugin2", 
supportedApiVersions = { "v1_19_R1" }, 
version = "1.5.0")
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
		
		MagicWeapon_ElementalStaff_Recipe.Init();
		MagicWeapon_ElementalStaff_Recipe.Register();
		
		MagicWeapon_SpiritualStaff_Recipe.Init();
		MagicWeapon_SpiritualStaff_Recipe.Register();
		
		Armament_Repentance_Recipe.Init();
		Armament_Repentance_Recipe.Register();
		
		Armament_Artifice_Recipe.Init();
		Armament_Artifice_Recipe.Register();
		
		Armament_Firebrand_Recipe.Init();
		Armament_Firebrand_Recipe.Register();
		
		Armament_War_Recipe.Init();
		Armament_War_Recipe.Register();
		
		//tool recipes
		MagicTool_PickaxeOfGeo_Recipe.Init();
		MagicTool_PickaxeOfGeo_Recipe.Register();
		
		MagicTool_ScytheOfUnholy_Recipe.Init();
		MagicTool_ScytheOfUnholy_Recipe.Register();
		
		MagicTool_ShovelOfGeo_Recipe.Init();
		MagicTool_ShovelOfGeo_Recipe.Register();
		
		//armor recipes
		LivingArmor_DragonScaleChestplate_Recipe.Init();
		LivingArmor_DragonScaleChestplate_Recipe.Register();
		
		LivingArmor_DragonScaleHelmet_Recipe.Init();
		LivingArmor_DragonScaleHelmet_Recipe.Register();
		
		LivingArmor_DragonScaleLeggings_Recipe.Init();
		LivingArmor_DragonScaleLeggings_Recipe.Register();
		
		LivingArmor_DragonScaleBoots_Recipe.Init();
		LivingArmor_DragonScaleBoots_Recipe.Register();
		
		
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
		
		//robes

		FireRobe_Hat.Init();
		FireRobe_Hat.Register();
		
		FireRobe_Tunic.Init();
		FireRobe_Tunic.Register();
		
		FireRobe_Pants.Init();
		FireRobe_Pants.Register();
		
		FireRobe_Boots.Init();
		FireRobe_Boots.Register();

		WaterRobe_Boots.Init();
		WaterRobe_Boots.Register();
		
		WaterRobe_Pants.Init();
		WaterRobe_Pants.Register();
		
		WaterRobe_Tunic.Init();
		WaterRobe_Tunic.Register();
		
		WaterRobe_Hat.Init();
		WaterRobe_Hat.Register();

		GeoRobe_Boots.Init();
		GeoRobe_Boots.Register();
		
		GeoRobe_Pants.Init();
		GeoRobe_Pants.Register();
		
		GeoRobe_Tunic.Init();
		GeoRobe_Tunic.Register();
		
		GeoRobe_Hat.Init();
		GeoRobe_Hat.Register();
		
		StormRobe_Hat.Init();
		StormRobe_Hat.Register();
		
		StormRobe_Tunic.Init();
		StormRobe_Tunic.Register();
		
		StormRobe_Pants.Init();
		StormRobe_Pants.Register();
		
		StormRobe_Boots.Init();
		StormRobe_Boots.Register();
		
		HolyRobe_Hat.Init();
		HolyRobe_Hat.Register();
		
		HolyRobe_Tunic.Init();
		HolyRobe_Tunic.Register();
		
		HolyRobe_Pants.Init();
		HolyRobe_Pants.Register();
		
		HolyRobe_Boots.Init();
		HolyRobe_Boots.Register();
		
		UnholyRobe_Hat.Init();
		UnholyRobe_Hat.Register();
		
		UnholyRobe_Tunic.Init();
		UnholyRobe_Tunic.Register();
		
		UnholyRobe_Pants.Init();
		UnholyRobe_Pants.Register();
		
		UnholyRobe_Boots.Init();
		UnholyRobe_Boots.Register();
		
		VoidRobe_Hat.Init();
		VoidRobe_Hat.Register();
		
		VoidRobe_Tunic.Init();
		VoidRobe_Tunic.Register();
		
		VoidRobe_Pants.Init();
		VoidRobe_Pants.Register();
		
		VoidRobe_Boots.Init();
		VoidRobe_Boots.Register();
		
		ArchmageRobe_Hat.Init();
		ArchmageRobe_Hat.Register();
		
		ArchmageRobe_Tunic.Init();
		ArchmageRobe_Tunic.Register();
		
		ArchmageRobe_Pants.Init();
		ArchmageRobe_Pants.Register();
		
		ArchmageRobe_Boots.Init();
		ArchmageRobe_Boots.Register();
		
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