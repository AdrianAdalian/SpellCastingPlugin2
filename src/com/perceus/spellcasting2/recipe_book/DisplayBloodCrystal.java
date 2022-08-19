package com.perceus.spellcasting2.recipe_book;

import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.inventory.InventoryView;

import fish.yukiemeralis.eden.surface2.SimpleComponentBuilder;
import fish.yukiemeralis.eden.surface2.SurfaceGui;
import fish.yukiemeralis.eden.surface2.enums.DefaultClickAction;

public class DisplayBloodCrystal extends SurfaceGui
{

	public DisplayBloodCrystal()
	{
		super(54, "Blood Crystal Recipe", DefaultClickAction.CANCEL, InventoryAction.PICKUP_ALL, InventoryAction.PICKUP_HALF);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void init(HumanEntity player, InventoryView arg1)
	{
		this.paintBlack();
		
		this.updateSingleComponent(player, 20, SimpleComponentBuilder.build(Material.QUARTZ, "", (event) -> 
		{
			
		}));
		this.updateSingleComponent(player, 21, SimpleComponentBuilder.build(Material.REDSTONE, "", (event) -> 
		{

		}));
		
		this.updateSingleComponent(player, 24, SimpleComponentBuilder.build(Material.QUARTZ, "§r§7§ko§r§7§lMagical Item: §r§fBlood Crystal§r§7§ko§r", (event) -> 
		{

		}));
		
		this.updateSingleComponent(player, 29, SimpleComponentBuilder.build(Material.NETHERITE_INGOT, "", (event) -> 
		{

		}));
		this.updateSingleComponent(player, 30, SimpleComponentBuilder.build(Material.GLASS_PANE, "Shapeless Recipe", (event) -> 
		{
	
		}));
		
		this.updateSingleComponent(player, 41, SimpleComponentBuilder.build(Material.LIME_STAINED_GLASS_PANE, "Go Back", (event) -> 
		{
			new RecipeBook_MagicalItemRecipes().display(event.getWhoClicked());
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
