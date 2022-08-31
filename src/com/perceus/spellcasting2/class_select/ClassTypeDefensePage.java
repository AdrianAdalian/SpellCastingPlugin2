package com.perceus.spellcasting2.class_select;

import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.inventory.InventoryView;

import fish.yukiemeralis.eden.surface2.SimpleComponentBuilder;
import fish.yukiemeralis.eden.surface2.SurfaceGui;
import fish.yukiemeralis.eden.surface2.enums.DefaultClickAction;

public class ClassTypeDefensePage extends SurfaceGui
{

	public ClassTypeDefensePage()
	{
		super(54, "Defense Class Type Select", DefaultClickAction.CANCEL, InventoryAction.PICKUP_ALL, InventoryAction.PICKUP_HALF);
	}

	@Override
	public void init(HumanEntity player, InventoryView arg1)
	{
		this.paintBlack();
		
		this.updateSingleComponent(player, 21, SimpleComponentBuilder.build(Material.BLAZE_POWDER, "§r§fClass: Firey Defender", (event) -> 
		{
			new ClassConfirmFireyDefender().display(event.getWhoClicked());
		}));
		this.updateSingleComponent(player, 23, SimpleComponentBuilder.build(Material.NETHERITE_INGOT, "§r§fClass: Guardian", (event) -> 
		{

		}));
		this.updateSingleComponent(player, 30, SimpleComponentBuilder.build(Material.GOLD_INGOT, "§r§fClass: Guilded Fortress", (event) -> 
		{

		}));
		this.updateSingleComponent(player, 32, SimpleComponentBuilder.build(Material.STRING, "§r§fClass: Karmic Protector", (event) -> 
		{
			
		}));
		
		this.updateSingleComponent(player, 22, SimpleComponentBuilder.build(Material.LIME_STAINED_GLASS_PANE, "Go Back", (event) -> 
		{
			new ClassSelectMainPageGUI().display(event.getWhoClicked());
		}));
		this.updateSingleComponent(player, 31, SimpleComponentBuilder.build(Material.LIME_STAINED_GLASS_PANE, "Go Back", (event) -> 
		{
			new ClassSelectMainPageGUI().display(event.getWhoClicked());
		}));

	}

}
