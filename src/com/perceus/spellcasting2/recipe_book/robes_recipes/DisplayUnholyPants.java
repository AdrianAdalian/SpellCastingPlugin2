package com.perceus.spellcasting2.recipe_book.robes_recipes;

import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.inventory.InventoryView;

import com.perceus.spellcasting2.recipe_book.RecipeBookMainPageGUI;
import com.perceus.spellcasting2.recipe_book.RecipeBook_RobesRecipes;

import fish.yukiemeralis.eden.surface2.SimpleComponentBuilder;
import fish.yukiemeralis.eden.surface2.SurfaceGui;
import fish.yukiemeralis.eden.surface2.enums.DefaultClickAction;

public class DisplayUnholyPants extends SurfaceGui
{

	public DisplayUnholyPants()
	{
		super(54, "Pants of Unholy Recipe", DefaultClickAction.CANCEL, InventoryAction.PICKUP_ALL, InventoryAction.PICKUP_HALF);
	}

	@Override
	public void init(HumanEntity player, InventoryView arg1)
	{
		this.paintBlack();
		
		this.updateSingleComponent(player, 20, SimpleComponentBuilder.build(Material.LEATHER_LEGGINGS, "", (event) -> 
		{
			
		}));
		this.updateSingleComponent(player, 21, SimpleComponentBuilder.build(Material.BONE, "", (event) -> 
		{

		}));
		
		this.updateSingleComponent(player, 24, SimpleComponentBuilder.build(Material.LEATHER_LEGGINGS, "§r§fPants of §r§4Unholy", (event) -> 
		{

		}));
		
		this.updateSingleComponent(player, 29, SimpleComponentBuilder.build(Material.GLASS_PANE, "Shapeless Recipe", (event) -> 
		{

		}));
		this.updateSingleComponent(player, 30, SimpleComponentBuilder.build(Material.GLASS_PANE, "Shapeless Recipe", (event) -> 
		{
	
		}));
		
		this.updateSingleComponent(player, 41, SimpleComponentBuilder.build(Material.LIME_STAINED_GLASS_PANE, "Go Back", (event) -> 
		{
			new RecipeBook_RobesRecipes().display(event.getWhoClicked());
		}));
		this.updateSingleComponent(player, 42, SimpleComponentBuilder.build(Material.YELLOW_STAINED_GLASS_PANE, "Home Page", (event) -> 
		{
			new RecipeBookMainPageGUI().display(event.getWhoClicked());
		}));
		this.updateSingleComponent(player, 43, SimpleComponentBuilder.build(Material.RED_STAINED_GLASS_PANE, "Close Recipe Book", (event) -> 
		{
			event.getWhoClicked().closeInventory();
		}));
	}

}
