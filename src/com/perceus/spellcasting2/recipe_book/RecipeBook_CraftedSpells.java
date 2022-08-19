package com.perceus.spellcasting2.recipe_book;

import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.inventory.InventoryView;

import fish.yukiemeralis.eden.surface2.SimpleComponentBuilder;
import fish.yukiemeralis.eden.surface2.SurfaceGui;
import fish.yukiemeralis.eden.surface2.enums.DefaultClickAction;

public class RecipeBook_CraftedSpells extends SurfaceGui
{

	public RecipeBook_CraftedSpells()
	{
		super(54, "Crafted Spell Recipes", DefaultClickAction.CANCEL, InventoryAction.PICKUP_ALL, InventoryAction.PICKUP_HALF);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void init(HumanEntity player, InventoryView arg1)
	{
		this.paintBlack();
		this.updateSingleComponent(player, 21, SimpleComponentBuilder.build(Material.FIRE_CHARGE, "§r§7§ko§r§7§lCrafted Spell: §r§fHot Coals§r§7§ko§r", (event) -> 
		{
			new DisplayHotCoals().display(event.getWhoClicked());
		}));
		this.updateSingleComponent(player, 23, SimpleComponentBuilder.build(Material.CLAY_BALL, "§r§7§ko§r§7§lCrafted Spell: §r§fProtective Paste§r§7§ko§r", (event) -> 
		{
			new DisplayProtectivePaste().display(event.getWhoClicked());
		}));
		this.updateSingleComponent(player, 30, SimpleComponentBuilder.build(Material.FIREWORK_STAR, "§r§7§ko§r§7§lCrafted Spell: §r§fSmoke Bomb§r§7§ko§r", (event) -> 
		{
			new DisplaySmokeBomb().display(event.getWhoClicked());
		}));
		this.updateSingleComponent(player, 32, SimpleComponentBuilder.build(Material.STRING, "§r§7§ko§r§7§lCrafted Spell: §r§fUpdraft§r§7§ko§r", (event) -> 
		{
			new DisplayUpdraft().display(event.getWhoClicked());
		}));
		
		this.updateSingleComponent(player, 19, SimpleComponentBuilder.build(Material.LIME_STAINED_GLASS_PANE, "Go Back", (event) -> 
		{
			new RecipeBookMainPageGUI().display(event.getWhoClicked());
		}));
		this.updateSingleComponent(player, 28, SimpleComponentBuilder.build(Material.RED_STAINED_GLASS_PANE, "Close Recipe Book", (event) -> 
		{
			event.getWhoClicked().closeInventory();
		}));
		this.updateSingleComponent(player, 25, SimpleComponentBuilder.build(Material.LIME_STAINED_GLASS_PANE, "Go Back", (event) -> 
		{
			new RecipeBookMainPageGUI().display(event.getWhoClicked());
		}));
		this.updateSingleComponent(player, 34, SimpleComponentBuilder.build(Material.RED_STAINED_GLASS_PANE, "Close Recipe Book", (event) -> 
		{
			event.getWhoClicked().closeInventory();
		}));
		
	}

}
