package com.perceus.spellcasting2.class_select;

import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.inventory.InventoryView;

import fish.yukiemeralis.eden.surface2.SimpleComponentBuilder;
import fish.yukiemeralis.eden.surface2.SurfaceGui;
import fish.yukiemeralis.eden.surface2.enums.DefaultClickAction;

public class ClassSelectMainPageGUI extends SurfaceGui
{

	public ClassSelectMainPageGUI()
	{
		super(27, "Class Type Select", DefaultClickAction.CANCEL, InventoryAction.PICKUP_ALL, InventoryAction.PICKUP_HALF);
	}

	@Override
	public void init(HumanEntity player, InventoryView arg1)
	{
		this.paintBlack();
		this.updateSingleComponent(player, 10, SimpleComponentBuilder.build(Material.RED_STAINED_GLASS_PANE, "§r§fClose Window (D-PH)", (event) -> 
		{
			event.getWhoClicked().closeInventory();
		}));
		this.updateSingleComponent(player, 12, SimpleComponentBuilder.build(Material.SHIELD, "§r§fClass Type: §6Defense§f.", (event) -> 
		{
			new ClassTypeDefensePage().display(event.getWhoClicked());
		}));
		this.updateSingleComponent(player, 13, SimpleComponentBuilder.build(Material.NETHER_STAR, "§r§fClass Type: §aSupport§f.", (event) -> 
		{
			
		}));
		
		this.updateSingleComponent(player, 14, SimpleComponentBuilder.build(Material.IRON_SWORD, "§r§fClass Type: §cOffense§f.", (event) -> 
		{
			
		}));
		this.updateSingleComponent(player, 16, SimpleComponentBuilder.build(Material.RED_STAINED_GLASS_PANE, "§r§fClose Window (D-PH)", (event) -> 
		{
			event.getWhoClicked().closeInventory();
		}));
		
	}
}
