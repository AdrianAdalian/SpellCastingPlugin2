package com.perceus.spellcasting2.gui;

import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;

import com.perceus.spellcasting2.accounts.PlayerAccountManagement;
import com.perceus.spellcasting2.fire_spells.SpellCombustion;
import com.perceus.spellcasting2.fire_spells.SpellEmbers;
import com.perceus.spellcasting2.fire_spells.SpellExpulsion;
import com.perceus.spellcasting2.fire_spells.SpellFireball;
import com.perceus.spellcasting2.fire_spells.SpellFlamethrower;
import com.perceus.spellcasting2.fire_spells.SpellHellFire;
import com.perceus.spellcasting2.fire_spells.SpellIgnite;
import com.perceus.spellcasting2.fire_spells.SpellIgnitionDrive;
import com.perceus.spellcasting2.fire_spells.SpellInsulate;
import com.perceus.spellcasting2.fire_spells.SpellKindleFlame;
import com.perceus.spellcasting2.fire_spells.SpellManaBurn;
import com.perceus.spellcasting2.fire_spells.SpellMeteor;
import com.perceus.spellcasting2.fire_spells.SpellOverclockProtocol;
import com.perceus.spellcasting2.fire_spells.SpellSmokeScreen;

import fish.yukiemeralis.eden.surface2.GuiUtils;
import fish.yukiemeralis.eden.surface2.SimpleComponentBuilder;
import fish.yukiemeralis.eden.surface2.SurfaceGui;
import fish.yukiemeralis.eden.surface2.component.GuiComponent;
import fish.yukiemeralis.eden.surface2.enums.DefaultClickAction;

public class SpellGUI_Fire extends SurfaceGui
{

	public SpellGUI_Fire()
	{
		super(54, "Fire Spells", DefaultClickAction.CANCEL, InventoryAction.PICKUP_ALL, InventoryAction.PICKUP_HALF);
	}

	@Override
	public void init(HumanEntity player, InventoryView arg1)
	{
		this.paintBlack();
		this.updateSingleDataItem(player, GuiUtils.generateItemRectangle(1, 1, 8, 2, new ItemStack(Material.AIR)), false);
		this.updateSingleDataItem(player, GuiUtils.generateItemRectangle(1, 3, 8, 5, new ItemStack(Material.AIR)), false);
		this.updateSingleComponent(player, 10, SimpleComponentBuilder.build(Material.BRICK, "§r§6Geo §r§fSpells", (event) -> 
		{
			new SpellGUI_Geo().display(event.getWhoClicked());
		}));
		this.updateSingleComponent(player, 11, SimpleComponentBuilder.build(Material.LAPIS_LAZULI, "§r§9Water §r§fSpells", (event) -> 
		{
			new SpellGUI_Water().display(event.getWhoClicked());
		}));
		this.updateSingleComponent(player, 12, SimpleComponentBuilder.build(Material.NETHER_STAR, "§r§fHoly Spells", (event) -> 
		{
			new SpellGUI_Holy().display(event.getWhoClicked());
		}));
		
		this.updateSingleComponent(player, 13, SimpleComponentBuilder.build(Material.ENDER_PEARL, "§r§3Void §r§fSpells", (event) -> 
		{
			new SpellGUI_Void().display(event.getWhoClicked());
		}));
		this.updateSingleComponent(player, 14, SimpleComponentBuilder.build(Material.BONE, "§r§4Unholy §r§fSpells", (event) -> 
		{
			new SpellGUI_Unholy().display(event.getWhoClicked());
		}));
		this.updateSingleComponent(player, 15, SimpleComponentBuilder.build(Material.BLAZE_POWDER, "§r§cFire §r§fSpells", (event) -> 
		{
			new SpellGUI_Fire().display(event.getWhoClicked());
		}));
		this.updateSingleComponent(player, 16, SimpleComponentBuilder.build(Material.AMETHYST_SHARD, "§r§dStorm §r§fSpells", (event) -> 
		{
			new SpellGUI_Storm().display(event.getWhoClicked());
		}));
		
		GuiComponent p = SimpleComponentBuilder.build(Material.PAPER, "§r§fSpell Page Missing", (event) -> {}, "§r§fNote: You have not yet discovered this spell.");
		
		this.updateSingleComponent(player, 28, PlayerAccountManagement.hasSpellUnlocked((Player) player, new SpellKindleFlame()) ? new SpellKindleFlame() : p);
		this.updateSingleComponent(player, 29, PlayerAccountManagement.hasSpellUnlocked((Player) player, new SpellEmbers()) ? new SpellEmbers() : p);
		this.updateSingleComponent(player, 30, PlayerAccountManagement.hasSpellUnlocked((Player) player, new SpellIgnite()) ? new SpellIgnite() : p);
		this.updateSingleComponent(player, 31, PlayerAccountManagement.hasSpellUnlocked((Player) player, new SpellFireball()) ? new SpellFireball() : p);
		this.updateSingleComponent(player, 32, PlayerAccountManagement.hasSpellUnlocked((Player) player, new SpellExpulsion()) ? new SpellExpulsion() : p);
		this.updateSingleComponent(player, 33, PlayerAccountManagement.hasSpellUnlocked((Player) player, new SpellOverclockProtocol()) ? new SpellOverclockProtocol() : p);
		this.updateSingleComponent(player, 34, PlayerAccountManagement.hasSpellUnlocked((Player) player, new SpellSmokeScreen()) ? new SpellSmokeScreen() : p);
		this.updateSingleComponent(player, 37, PlayerAccountManagement.hasSpellUnlocked((Player) player, new SpellFlamethrower()) ? new SpellFlamethrower() : p);
		this.updateSingleComponent(player, 38, PlayerAccountManagement.hasSpellUnlocked((Player) player, new SpellInsulate()) ? new SpellInsulate() : p);
		this.updateSingleComponent(player, 39, PlayerAccountManagement.hasSpellUnlocked((Player) player, new SpellMeteor()) ? new SpellMeteor() : p);
		this.updateSingleComponent(player, 40, PlayerAccountManagement.hasSpellUnlocked((Player) player, new SpellCombustion()) ? new SpellCombustion() : p);
		this.updateSingleComponent(player, 41, PlayerAccountManagement.hasSpellUnlocked((Player) player, new SpellHellFire()) ? new SpellHellFire() : p);
		this.updateSingleComponent(player, 42, PlayerAccountManagement.hasSpellUnlocked((Player) player, new SpellIgnitionDrive()) ? new SpellIgnitionDrive() : p);
		this.updateSingleComponent(player, 43, PlayerAccountManagement.hasSpellUnlocked((Player) player, new SpellManaBurn()) ? new SpellManaBurn() : p);
		//shift + alt + a = multi coursor.
	}   
}       
        