package com.perceus.spellcasting2.recipe_book;

import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.inventory.InventoryView;

import fish.yukiemeralis.eden.surface2.SimpleComponentBuilder;
import fish.yukiemeralis.eden.surface2.SurfaceGui;
import fish.yukiemeralis.eden.surface2.enums.DefaultClickAction;

public class RecipeBook_ArmamentsAndRobes extends SurfaceGui
{

	public RecipeBook_ArmamentsAndRobes()
	{
		super(27, "Armaments and Robes", DefaultClickAction.CANCEL, InventoryAction.PICKUP_ALL, InventoryAction.PICKUP_HALF);
	}

	@Override
	public void init(HumanEntity player, InventoryView arg1)
	{
		this.paintBlack();
		this.updateSingleComponent(player, 10, SimpleComponentBuilder.build(Material.LIME_STAINED_GLASS_PANE, "§r§fGo Back", (event) -> 
		{
			new RecipeBookMainPageGUI().display(event.getWhoClicked());
		}));
		
		this.updateSingleComponent(player, 12, SimpleComponentBuilder.build(Material.WOODEN_SWORD, "§r§fArmaments Recipes", (event) -> 
		{
			new RecipeBook_ArmamentsRecipesGUI().display(event.getWhoClicked());
		}));
		
		this.updateSingleComponent(player, 14, SimpleComponentBuilder.build(Material.LEATHER_CHESTPLATE, "§r§fRobes Recipes", (event) -> 
		{
			new RecipeBook_RobesRecipes().display(event.getWhoClicked());
		}));
		
		this.updateSingleComponent(player, 16, SimpleComponentBuilder.build(Material.RED_STAINED_GLASS_PANE, "§r§fClose Recipe Book", (event) -> 
		{
			event.getWhoClicked().closeInventory();
		}));
		
	}
}
