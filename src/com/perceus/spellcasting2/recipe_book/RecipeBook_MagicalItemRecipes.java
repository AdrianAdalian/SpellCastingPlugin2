package com.perceus.spellcasting2.recipe_book;

import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.inventory.InventoryView;

import fish.yukiemeralis.eden.surface2.SimpleComponentBuilder;
import fish.yukiemeralis.eden.surface2.SurfaceGui;
import fish.yukiemeralis.eden.surface2.enums.DefaultClickAction;

public class RecipeBook_MagicalItemRecipes extends SurfaceGui
{

	public RecipeBook_MagicalItemRecipes()
	{
		super(54, "Magical Item Recipes", DefaultClickAction.CANCEL, InventoryAction.PICKUP_ALL, InventoryAction.PICKUP_HALF);
	}

	@Override
	public void init(HumanEntity player, InventoryView arg1)
	{
		this.paintBlack();
		
		this.updateSingleComponent(player, 20, SimpleComponentBuilder.build(Material.PAPER, "§r§7§ko§r§7§lMagical Item: §r§bAethereal Scroll§r§7§ko§r", (event) -> 
		{
			new DisplayAetherealScroll().display(event.getWhoClicked());
		}));
		this.updateSingleComponent(player, 22, SimpleComponentBuilder.build(Material.QUARTZ, "§r§7§ko§r§7§lMagical Item: §r§fMana Crystal§r§7§ko§r", (event) -> 
		{
			new DisplayManaCrystal().display(event.getWhoClicked());
		}));
		this.updateSingleComponent(player, 24, SimpleComponentBuilder.build(Material.QUARTZ, "§r§7§ko§r§7§lMagical Item: §r§fBlood Crystal§r§7§ko§r", (event) -> 
		{
			new DisplayBloodCrystal().display(event.getWhoClicked());
		}));
		this.updateSingleComponent(player, 29, SimpleComponentBuilder.build(Material.PAPER, "§r§7§ko§r§7§lMagical Item: §r§bAethereal Scroll§r§e+§r§7§ko§r", (event) -> 
		{
			new DisplayAetherealScrollPlus().display(event.getWhoClicked());
		}));
		this.updateSingleComponent(player, 31, SimpleComponentBuilder.build(Material.QUARTZ, "§r§7§ko§r§7§lMagical Item: §r§fHealth Crystal§r§7§ko§r", (event) -> 
		{
			new DisplayHealthCrystal().display(event.getWhoClicked());
		}));
		this.updateSingleComponent(player, 33, SimpleComponentBuilder.build(Material.QUARTZ, "§r§7§ko§r§7§lMagical Item: §r§fBlood Crystal§r§e+§r§7§ko§r", (event) -> 
		{
			new DisplayBloodCrystalPlus().display(event.getWhoClicked());
		}));
		this.updateSingleComponent(player, 40, SimpleComponentBuilder.build(Material.QUARTZ, "§r§7§ko§r§7§lMagical Item: §r§fXp Crystal§r§7§ko§r", (event) -> 
		{
			new DisplayXpCrystal().display(event.getWhoClicked());
		}));
		
		this.updateSingleComponent(player, 37, SimpleComponentBuilder.build(Material.LIME_STAINED_GLASS_PANE, "Go Back", (event) -> 
		{
			new RecipeBookMainPageGUI().display(event.getWhoClicked());
		}));
		this.updateSingleComponent(player, 43, SimpleComponentBuilder.build(Material.RED_STAINED_GLASS_PANE, "Close Recipe Book", (event) -> 
		{
			event.getWhoClicked().closeInventory();
		}));
		
	}

}
