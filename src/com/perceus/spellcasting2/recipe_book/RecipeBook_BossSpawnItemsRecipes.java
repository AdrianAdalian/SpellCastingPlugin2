package com.perceus.spellcasting2.recipe_book;

import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.inventory.InventoryView;

import fish.yukiemeralis.eden.surface2.SimpleComponentBuilder;
import fish.yukiemeralis.eden.surface2.SurfaceGui;
import fish.yukiemeralis.eden.surface2.enums.DefaultClickAction;

public class RecipeBook_BossSpawnItemsRecipes extends SurfaceGui
{

	public RecipeBook_BossSpawnItemsRecipes()
	{
		super(27, "Boss Spawn Items", DefaultClickAction.CANCEL, InventoryAction.PICKUP_ALL, InventoryAction.PICKUP_HALF);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void init(HumanEntity player, InventoryView arg1)
	{
		this.paintBlack();
		this.updateSingleComponent(player, 10, SimpleComponentBuilder.build(Material.LIME_STAINED_GLASS_PANE, "§r§fGo Back", (event) -> 
		{
			new RecipeBookMainPageGUI().display(event.getWhoClicked());
		}));
		this.updateSingleComponent(player, 11, SimpleComponentBuilder.build(Material.END_CRYSTAL, "§r§fMaterialize Allay", (event) -> 
		{
			new DisplayAllay().display(event.getWhoClicked());
		}));
		this.updateSingleComponent(player, 12, SimpleComponentBuilder.build(Material.END_CRYSTAL, "§r§fMaterialize Warden", (event) -> 
		{
			new DisplayWarden().display(event.getWhoClicked());
		}));
		
		this.updateSingleComponent(player, 14, SimpleComponentBuilder.build(Material.END_CRYSTAL, "§r§fMaterialize Elder Guardian", (event) -> 
		{
			new DisplayElderGuardian().display(event.getWhoClicked());
		}));
		this.updateSingleComponent(player, 15, SimpleComponentBuilder.build(Material.END_CRYSTAL, "§r§fMaterialize Wither", (event) -> 
		{
			new DisplayWither().display(event.getWhoClicked());
		}));
		this.updateSingleComponent(player, 16, SimpleComponentBuilder.build(Material.RED_STAINED_GLASS_PANE, "§r§fClose Recipe Book", (event) -> 
		{
			event.getWhoClicked().closeInventory();
		}));
		
	}

}
