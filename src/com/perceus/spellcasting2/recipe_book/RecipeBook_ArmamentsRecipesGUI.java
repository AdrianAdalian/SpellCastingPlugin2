package com.perceus.spellcasting2.recipe_book;

import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.inventory.InventoryView;

import com.perceus.spellcasting2.recipe_book.armament_recipes.DisplayArchmageBoots;
import com.perceus.spellcasting2.recipe_book.armament_recipes.DisplayArchmageHat;
import com.perceus.spellcasting2.recipe_book.armament_recipes.DisplayArchmagePants;
import com.perceus.spellcasting2.recipe_book.armament_recipes.DisplayArchmageTunic;
import com.perceus.spellcasting2.recipe_book.armament_recipes.DisplayArtifice;
import com.perceus.spellcasting2.recipe_book.armament_recipes.DisplayDragonscaleBoots;
import com.perceus.spellcasting2.recipe_book.armament_recipes.DisplayDragonscaleChestplate;
import com.perceus.spellcasting2.recipe_book.armament_recipes.DisplayDragonscaleHelmet;
import com.perceus.spellcasting2.recipe_book.armament_recipes.DisplayDragonscaleLeggings;
import com.perceus.spellcasting2.recipe_book.armament_recipes.DisplayFirebrand;
import com.perceus.spellcasting2.recipe_book.armament_recipes.DisplayNetheriteHoePlus;
import com.perceus.spellcasting2.recipe_book.armament_recipes.DisplayNetheritePickaxePlus;
import com.perceus.spellcasting2.recipe_book.armament_recipes.DisplayNetheriteShovelPlus;
import com.perceus.spellcasting2.recipe_book.armament_recipes.DisplayRepentance;
import com.perceus.spellcasting2.recipe_book.armament_recipes.DisplayWar;

import fish.yukiemeralis.eden.surface2.SimpleComponentBuilder;
import fish.yukiemeralis.eden.surface2.SurfaceGui;
import fish.yukiemeralis.eden.surface2.enums.DefaultClickAction;

public class RecipeBook_ArmamentsRecipesGUI extends SurfaceGui
{

	public RecipeBook_ArmamentsRecipesGUI()
	{
		super(54, "Armaments Recipes", DefaultClickAction.CANCEL, InventoryAction.PICKUP_ALL, InventoryAction.PICKUP_HALF);
	}

	@Override
	public void init(HumanEntity player, InventoryView arg1)
	{
		this.paintBlack();
		this.updateSingleComponent(player, 11, SimpleComponentBuilder.build(Material.NETHERITE_SWORD, "§r§fArmament: Firebrand", (event) -> 
		{
			new DisplayFirebrand().display(event.getWhoClicked());
		}));
		this.updateSingleComponent(player, 12, SimpleComponentBuilder.build(Material.NETHERITE_PICKAXE, "§r§fMagic Tool: Netherite Pickaxe§e+", (event) -> 
		{
			new DisplayNetheritePickaxePlus().display(event.getWhoClicked());
		}));
		this.updateSingleComponent(player, 14, SimpleComponentBuilder.build(Material.LEATHER_HELMET, "§r§fArchmage's Hat", (event) -> 
		{
			new DisplayArchmageHat().display(event.getWhoClicked());
		}));
		this.updateSingleComponent(player, 15, SimpleComponentBuilder.build(Material.NETHERITE_HELMET, "§r§fLiving Armor: Dragonscale Helmet", (event) -> 
		{
			new DisplayDragonscaleHelmet().display(event.getWhoClicked());
		}));
		
		this.updateSingleComponent(player, 20, SimpleComponentBuilder.build(Material.NETHERITE_AXE, "§r§fArmament: War", (event) -> 
		{
			new DisplayWar().display(event.getWhoClicked());
		}));
		this.updateSingleComponent(player, 21, SimpleComponentBuilder.build(Material.NETHERITE_HOE, "§r§fMagic Tool: Netherite Hoe§e+", (event) -> 
		{	
			new DisplayNetheriteHoePlus().display(event.getWhoClicked());
		}));
		this.updateSingleComponent(player, 23, SimpleComponentBuilder.build(Material.LEATHER_CHESTPLATE, "§r§fArchmage's Tunic", (event) -> 
		{
			new DisplayArchmageTunic().display(event.getWhoClicked());
		}));
		this.updateSingleComponent(player, 24, SimpleComponentBuilder.build(Material.NETHERITE_CHESTPLATE, "§r§fLiving Armor: Dragonscale Chestplate", (event) -> 
		{
			new DisplayDragonscaleChestplate().display(event.getWhoClicked());
		}));
		
		this.updateSingleComponent(player, 29, SimpleComponentBuilder.build(Material.BOW, "§r§fArmament: Repentance", (event) -> 
		{	
			new DisplayRepentance().display(event.getWhoClicked());
		}));
		this.updateSingleComponent(player, 30, SimpleComponentBuilder.build(Material.NETHERITE_SHOVEL, "§r§fMagic Tool: Netherite Shovel§e+", (event) -> 
		{
			new DisplayNetheriteShovelPlus().display(event.getWhoClicked());
		}));
		this.updateSingleComponent(player, 32, SimpleComponentBuilder.build(Material.LEATHER_LEGGINGS, "§r§fArchmage's Pants", (event) -> 
		{
			new DisplayArchmagePants().display(event.getWhoClicked());
		}));
		this.updateSingleComponent(player, 33, SimpleComponentBuilder.build(Material.NETHERITE_LEGGINGS, "§r§fLiving Armor: Dragonscale Leggings", (event) -> 
		{
			new DisplayDragonscaleLeggings().display(event.getWhoClicked());
		}));
		
		this.updateSingleComponent(player, 38, SimpleComponentBuilder.build(Material.SHIELD, "§r§fArmament: Artifice", (event) -> 
		{
			new DisplayArtifice().display(event.getWhoClicked());
		}));
		this.updateSingleComponent(player, 41, SimpleComponentBuilder.build(Material.LEATHER_BOOTS, "§r§fArchmage's Boots", (event) -> 
		{
			new DisplayArchmageBoots().display(event.getWhoClicked());
		}));
		this.updateSingleComponent(player, 42, SimpleComponentBuilder.build(Material.NETHERITE_BOOTS, "§r§fLiving Armor: Dragonscale Boots", (event) -> 
		{
			new DisplayDragonscaleBoots().display(event.getWhoClicked());
		}));
		
		this.updateSingleComponent(player, 19, SimpleComponentBuilder.build(Material.LIME_STAINED_GLASS_PANE, "Go Back", (event) -> 
		{
			new RecipeBook_ArmamentsAndRobes().display(event.getWhoClicked());
		}));
		this.updateSingleComponent(player, 28, SimpleComponentBuilder.build(Material.RED_STAINED_GLASS_PANE, "Close Recipe Book", (event) -> 
		{
			event.getWhoClicked().closeInventory();
		}));
		this.updateSingleComponent(player, 25, SimpleComponentBuilder.build(Material.LIME_STAINED_GLASS_PANE, "Go Back", (event) -> 
		{
			new RecipeBook_ArmamentsAndRobes().display(event.getWhoClicked());
		}));
		this.updateSingleComponent(player, 34, SimpleComponentBuilder.build(Material.RED_STAINED_GLASS_PANE, "Close Recipe Book", (event) -> 
		{
			event.getWhoClicked().closeInventory();
		}));
		
	}

}
