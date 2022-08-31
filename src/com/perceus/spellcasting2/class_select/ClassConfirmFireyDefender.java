package com.perceus.spellcasting2.class_select;

import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.inventory.InventoryView;

import fish.yukiemeralis.eden.surface2.SimpleComponentBuilder;
import fish.yukiemeralis.eden.surface2.SurfaceGui;
import fish.yukiemeralis.eden.surface2.enums.DefaultClickAction;

public class ClassConfirmFireyDefender extends SurfaceGui
{

	public ClassConfirmFireyDefender()
	{
		super(27, "Class Confirm | Firey Defender", DefaultClickAction.CANCEL, InventoryAction.PICKUP_ALL, InventoryAction.PICKUP_HALF);
	}

	@Override
	public void init(HumanEntity player, InventoryView arg1)
	{
		this.paintBlack();

		this.updateSingleComponent(player, 11, SimpleComponentBuilder.build(Material.RED_STAINED_GLASS_PANE, "Go Back", (event) -> 
		{
			new ClassTypeDefensePage().display(event.getWhoClicked());
		}));
		
		this.updateSingleComponent(player, 13, SimpleComponentBuilder.build(Material.PAPER, "§r§fPH Description", (event) -> 
		{
			
		}));
		
		this.updateSingleComponent(player, 15, SimpleComponentBuilder.build(Material.LIME_STAINED_GLASS_PANE, "Confirm", (event) -> 
		{
			
		}));
		
	}
}
