package com.perceus.spellcasting2.recipe_book;

import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.inventory.InventoryView;

import com.perceus.spellcasting2.recipe_book.robes_recipes.DisplayFireBoots;
import com.perceus.spellcasting2.recipe_book.robes_recipes.DisplayFireHat;
import com.perceus.spellcasting2.recipe_book.robes_recipes.DisplayFirePants;
import com.perceus.spellcasting2.recipe_book.robes_recipes.DisplayFireTunic;
import com.perceus.spellcasting2.recipe_book.robes_recipes.DisplayGeoBoots;
import com.perceus.spellcasting2.recipe_book.robes_recipes.DisplayGeoHat;
import com.perceus.spellcasting2.recipe_book.robes_recipes.DisplayGeoPants;
import com.perceus.spellcasting2.recipe_book.robes_recipes.DisplayGeoTunic;
import com.perceus.spellcasting2.recipe_book.robes_recipes.DisplayHolyBoots;
import com.perceus.spellcasting2.recipe_book.robes_recipes.DisplayHolyHat;
import com.perceus.spellcasting2.recipe_book.robes_recipes.DisplayHolyPants;
import com.perceus.spellcasting2.recipe_book.robes_recipes.DisplayHolyTunic;
import com.perceus.spellcasting2.recipe_book.robes_recipes.DisplayStormBoots;
import com.perceus.spellcasting2.recipe_book.robes_recipes.DisplayStormHat;
import com.perceus.spellcasting2.recipe_book.robes_recipes.DisplayStormPants;
import com.perceus.spellcasting2.recipe_book.robes_recipes.DisplayStormTunic;
import com.perceus.spellcasting2.recipe_book.robes_recipes.DisplayUnholyBoots;
import com.perceus.spellcasting2.recipe_book.robes_recipes.DisplayUnholyHat;
import com.perceus.spellcasting2.recipe_book.robes_recipes.DisplayUnholyPants;
import com.perceus.spellcasting2.recipe_book.robes_recipes.DisplayUnholyTunic;
import com.perceus.spellcasting2.recipe_book.robes_recipes.DisplayVoidBoots;
import com.perceus.spellcasting2.recipe_book.robes_recipes.DisplayVoidHat;
import com.perceus.spellcasting2.recipe_book.robes_recipes.DisplayVoidPants;
import com.perceus.spellcasting2.recipe_book.robes_recipes.DisplayVoidTunic;
import com.perceus.spellcasting2.recipe_book.robes_recipes.DisplayWaterBoots;
import com.perceus.spellcasting2.recipe_book.robes_recipes.DisplayWaterHat;
import com.perceus.spellcasting2.recipe_book.robes_recipes.DisplayWaterPants;
import com.perceus.spellcasting2.recipe_book.robes_recipes.DisplayWaterTunic;

import fish.yukiemeralis.eden.surface2.SimpleComponentBuilder;
import fish.yukiemeralis.eden.surface2.SurfaceGui;
import fish.yukiemeralis.eden.surface2.enums.DefaultClickAction;

public class RecipeBook_RobesRecipes extends SurfaceGui
{

	public RecipeBook_RobesRecipes()
	{
		super(54, "Robes Recipes", DefaultClickAction.CANCEL, InventoryAction.PICKUP_ALL, InventoryAction.PICKUP_HALF);
	}

	@Override
	public void init(HumanEntity player, InventoryView arg1)
	{
		this.paintBlack();
		this.updateSingleComponent(player, 10, SimpleComponentBuilder.build(Material.LEATHER_HELMET, "§r§fHat of §6Geo", (event) -> 
		{
			new DisplayGeoHat().display(event.getWhoClicked());
		}));
		this.updateSingleComponent(player, 11, SimpleComponentBuilder.build(Material.LEATHER_HELMET, "§r§fHat of §9Water", (event) -> 
		{
			new DisplayWaterHat().display(event.getWhoClicked());
		}));
		this.updateSingleComponent(player, 12, SimpleComponentBuilder.build(Material.LEATHER_HELMET, "§r§fHat of Holy", (event) -> 
		{
			new DisplayHolyHat().display(event.getWhoClicked());
		}));
		this.updateSingleComponent(player, 13, SimpleComponentBuilder.build(Material.LEATHER_HELMET, "§r§fHat of §3Void", (event) -> 
		{
			new DisplayVoidHat().display(event.getWhoClicked());
		}));
		this.updateSingleComponent(player, 14, SimpleComponentBuilder.build(Material.LEATHER_HELMET, "§r§fHat of §4Unholy", (event) -> 
		{
			new DisplayUnholyHat().display(event.getWhoClicked());
		}));
		this.updateSingleComponent(player, 15, SimpleComponentBuilder.build(Material.LEATHER_HELMET, "§r§fHat of §cFire", (event) -> 
		{
			new DisplayFireHat().display(event.getWhoClicked());
		}));
		this.updateSingleComponent(player, 16, SimpleComponentBuilder.build(Material.LEATHER_HELMET, "§r§fHat of §dStorm", (event) -> 
		{
			new DisplayStormHat().display(event.getWhoClicked());
		}));
		
		this.updateSingleComponent(player, 19, SimpleComponentBuilder.build(Material.LEATHER_CHESTPLATE, "§r§fTunic of §6Geo", (event) -> 
		{
			new DisplayGeoTunic().display(event.getWhoClicked());
		}));
		this.updateSingleComponent(player, 20, SimpleComponentBuilder.build(Material.LEATHER_CHESTPLATE, "§r§fTunic of §9Water", (event) -> 
		{
			new DisplayWaterTunic().display(event.getWhoClicked());
		}));
		this.updateSingleComponent(player, 21, SimpleComponentBuilder.build(Material.LEATHER_CHESTPLATE, "§r§fTunic of Holy", (event) -> 
		{
			new DisplayHolyTunic().display(event.getWhoClicked());
		}));
		this.updateSingleComponent(player, 22, SimpleComponentBuilder.build(Material.LEATHER_CHESTPLATE, "§r§fTunic of §3Void", (event) -> 
		{	
			new DisplayVoidTunic().display(event.getWhoClicked());
		}));
		this.updateSingleComponent(player, 23, SimpleComponentBuilder.build(Material.LEATHER_CHESTPLATE, "§r§fTunic of §4Unholy", (event) -> 
		{
			new DisplayUnholyTunic().display(event.getWhoClicked());
		}));
		this.updateSingleComponent(player, 24, SimpleComponentBuilder.build(Material.LEATHER_CHESTPLATE, "§r§fTunic of §cFire", (event) -> 
		{
			new DisplayFireTunic().display(event.getWhoClicked());
		}));
		this.updateSingleComponent(player, 25, SimpleComponentBuilder.build(Material.LEATHER_CHESTPLATE, "§r§fTunic of §dStorm", (event) -> 
		{
			new DisplayStormTunic().display(event.getWhoClicked());
		}));
		
		this.updateSingleComponent(player, 28, SimpleComponentBuilder.build(Material.LEATHER_LEGGINGS, "§r§fPants of §6Geo", (event) -> 
		{
			new DisplayGeoPants().display(event.getWhoClicked());
		}));
		this.updateSingleComponent(player, 29, SimpleComponentBuilder.build(Material.LEATHER_LEGGINGS, "§r§fPants of §9Water", (event) -> 
		{
			new DisplayWaterPants().display(event.getWhoClicked());
		}));
		this.updateSingleComponent(player, 30, SimpleComponentBuilder.build(Material.LEATHER_LEGGINGS, "§r§fPants of Holy", (event) -> 
		{
			new DisplayHolyPants().display(event.getWhoClicked());
		}));
		this.updateSingleComponent(player, 31, SimpleComponentBuilder.build(Material.LEATHER_LEGGINGS, "§r§fPants of §3Void", (event) -> 
		{
			new DisplayVoidPants().display(event.getWhoClicked());
		}));
		this.updateSingleComponent(player, 32, SimpleComponentBuilder.build(Material.LEATHER_LEGGINGS, "§r§fPants of §4Unholy", (event) -> 
		{
			new DisplayUnholyPants().display(event.getWhoClicked());
		}));
		this.updateSingleComponent(player, 33, SimpleComponentBuilder.build(Material.LEATHER_LEGGINGS, "§r§fPants of §cFire", (event) -> 
		{
			new DisplayFirePants().display(event.getWhoClicked());
		}));
		this.updateSingleComponent(player, 34, SimpleComponentBuilder.build(Material.LEATHER_LEGGINGS, "§r§fPants of §dStorm", (event) -> 
		{
			new DisplayStormPants().display(event.getWhoClicked());
		}));
		
		this.updateSingleComponent(player, 37, SimpleComponentBuilder.build(Material.LEATHER_BOOTS, "§r§fBoots of §6Geo", (event) -> 
		{
			new DisplayGeoBoots().display(event.getWhoClicked());
		}));
		this.updateSingleComponent(player, 38, SimpleComponentBuilder.build(Material.LEATHER_BOOTS, "§r§fBoots of §9Water", (event) -> 
		{
			new DisplayWaterBoots().display(event.getWhoClicked());
		}));
		this.updateSingleComponent(player, 39, SimpleComponentBuilder.build(Material.LEATHER_BOOTS, "§r§fBoots of Holy", (event) -> 
		{
			new DisplayHolyBoots().display(event.getWhoClicked());
		}));
		this.updateSingleComponent(player, 40, SimpleComponentBuilder.build(Material.LEATHER_BOOTS, "§r§fBoots of §3Void", (event) -> 
		{
			new DisplayVoidBoots().display(event.getWhoClicked());
		}));
		this.updateSingleComponent(player, 41, SimpleComponentBuilder.build(Material.LEATHER_BOOTS, "§r§fBoots of §4Unholy", (event) -> 
		{
			new DisplayUnholyBoots().display(event.getWhoClicked());
		}));
		this.updateSingleComponent(player, 42, SimpleComponentBuilder.build(Material.LEATHER_BOOTS, "§r§fBoots of §cFire", (event) -> 
		{
			new DisplayFireBoots().display(event.getWhoClicked());
		}));
		this.updateSingleComponent(player, 43, SimpleComponentBuilder.build(Material.LEATHER_BOOTS, "§r§fBoots of §dStorm", (event) -> 
		{
			new DisplayStormBoots().display(event.getWhoClicked());
		}));
		
		this.updateSingleComponent(player, 18, SimpleComponentBuilder.build(Material.LIME_STAINED_GLASS_PANE, "Go Back", (event) -> 
		{
			new RecipeBook_ArmamentsAndRobes().display(event.getWhoClicked());
		}));
		this.updateSingleComponent(player, 27, SimpleComponentBuilder.build(Material.RED_STAINED_GLASS_PANE, "Close Recipe Book", (event) -> 
		{
			event.getWhoClicked().closeInventory();
		}));
		this.updateSingleComponent(player, 26, SimpleComponentBuilder.build(Material.LIME_STAINED_GLASS_PANE, "Go Back", (event) -> 
		{
			new RecipeBook_ArmamentsAndRobes().display(event.getWhoClicked());
		}));
		this.updateSingleComponent(player, 35, SimpleComponentBuilder.build(Material.RED_STAINED_GLASS_PANE, "Close Recipe Book", (event) -> 
		{
			event.getWhoClicked().closeInventory();
		}));
		
	}

}
