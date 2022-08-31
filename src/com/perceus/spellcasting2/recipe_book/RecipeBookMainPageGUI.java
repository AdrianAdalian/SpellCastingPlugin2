package com.perceus.spellcasting2.recipe_book;

import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.inventory.InventoryView;

import fish.yukiemeralis.eden.surface2.SimpleComponentBuilder;
import fish.yukiemeralis.eden.surface2.SurfaceGui;
import fish.yukiemeralis.eden.surface2.enums.DefaultClickAction;

public class RecipeBookMainPageGUI extends SurfaceGui
{

	public RecipeBookMainPageGUI()
	{
		super(27, "Spell Recipe Book", DefaultClickAction.CANCEL, InventoryAction.PICKUP_ALL, InventoryAction.PICKUP_HALF);
	}

	@Override
	public void init(HumanEntity player, InventoryView arg1)
	{
		this.paintBlack();
		this.updateSingleComponent(player, 10, SimpleComponentBuilder.build(Material.RED_STAINED_GLASS_PANE, "§r§fClose Recipe Book", (event) -> 
		{
			event.getWhoClicked().closeInventory();
		}));
		this.updateSingleComponent(player, 11, SimpleComponentBuilder.build(Material.DRAGON_BREATH, "§r§fMagical Item Recipes", (event) -> 
		{
			new RecipeBook_MagicalItemRecipes().display(event.getWhoClicked());
		}));
		this.updateSingleComponent(player, 12, SimpleComponentBuilder.build(Material.END_CRYSTAL, "§r§fRune Item Recipes", (event) -> 
		{
			new RecipeBook_BossSpawnItemsRecipes().display(event.getWhoClicked());
		}));
		this.updateSingleComponent(player, 13, SimpleComponentBuilder.build(Material.NETHER_STAR, "§r§fArmaments and Robes", (event) -> 
		{
			new RecipeBook_ArmamentsAndRobes().display(event.getWhoClicked());
		}));
		this.updateSingleComponent(player, 14, SimpleComponentBuilder.build(Material.STICK, "§r§fWand Recipes", (event) -> 
		{
			new RecipeBook_WandRecipes().display(event.getWhoClicked());
		}));
		this.updateSingleComponent(player, 15, SimpleComponentBuilder.build(Material.CRAFTING_TABLE, "§r§fCrafted Spell Recipes", (event) -> 
		{
			new RecipeBook_CraftedSpells().display(event.getWhoClicked());
		}));
		this.updateSingleComponent(player, 16, SimpleComponentBuilder.build(Material.RED_STAINED_GLASS_PANE, "§r§fClose Recipe Book", (event) -> 
		{
			event.getWhoClicked().closeInventory();
		}));
		
	}
}
