package com.perceus.spellcasting2.recipe_book;

import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.inventory.InventoryView;

import fish.yukiemeralis.eden.surface2.SimpleComponentBuilder;
import fish.yukiemeralis.eden.surface2.SurfaceGui;
import fish.yukiemeralis.eden.surface2.enums.DefaultClickAction;

public class RecipeBook_WandRecipes extends SurfaceGui
{

	public RecipeBook_WandRecipes()
	{
		super(54, "Crafted Spell Recipes", DefaultClickAction.CANCEL, InventoryAction.PICKUP_ALL, InventoryAction.PICKUP_HALF);
	}

	@Override
	public void init(HumanEntity player, InventoryView arg1)
	{
		this.paintBlack();
		this.updateSingleComponent(player, 12, SimpleComponentBuilder.build(Material.STICK, "§r§fWand of §r§cFire", (event) -> 
		{
			new DisplayWOF().display(event.getWhoClicked());
		}));
		this.updateSingleComponent(player, 14, SimpleComponentBuilder.build(Material.STICK, "§r§fWand of §r§l§o§fHoly", (event) -> 
		{
			new DisplayWOH().display(event.getWhoClicked());
		}));
		this.updateSingleComponent(player, 21, SimpleComponentBuilder.build(Material.STICK, "§r§fWand of §r§9Water", (event) -> 
		{
			new DisplayWOW().display(event.getWhoClicked());
		}));
		this.updateSingleComponent(player, 23, SimpleComponentBuilder.build(Material.STICK, "§r§fWand of §r§o§l§4Unholy", (event) -> 
		{
			new DisplayWOU().display(event.getWhoClicked());
		}));
		this.updateSingleComponent(player, 30, SimpleComponentBuilder.build(Material.STICK, "§r§fWand of §r§6Geo", (event) -> 
		{
			new DisplayWOG().display(event.getWhoClicked());
		}));
		this.updateSingleComponent(player, 32, SimpleComponentBuilder.build(Material.STICK, "§r§fWand of §r§l§3VOID", (event) -> 
		{
			new DisplayWOV().display(event.getWhoClicked());
		}));
		this.updateSingleComponent(player, 39, SimpleComponentBuilder.build(Material.STICK, "§r§fWand of §r§dStorm", (event) -> 
		{
			new DisplayWOS().display(event.getWhoClicked());
		}));
		this.updateSingleComponent(player, 40, SimpleComponentBuilder.build(Material.STICK, "§r§f§lMagic Weapon §r§f: Sp§3ir§bit§cua§4l §fStaff", (event) -> 
		{
			new DisplaySS().display(event.getWhoClicked());
		}));
		this.updateSingleComponent(player, 41, SimpleComponentBuilder.build(Material.STICK, "§r§f§lMagic Weapon §r§f: §4E§cl§6e§em§ae§9n§bt§da§5l§f Staff", (event) -> 
		{
			new DisplayES().display(event.getWhoClicked());
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
