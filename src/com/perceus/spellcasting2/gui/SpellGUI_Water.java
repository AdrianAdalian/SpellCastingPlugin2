package com.perceus.spellcasting2.gui;

import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;

import com.perceus.spellcasting2.accounts.PlayerAccountManagement;
import com.perceus.spellcasting2.water_spells.SpellConduitEther;
import com.perceus.spellcasting2.water_spells.SpellCrystalize;
import com.perceus.spellcasting2.water_spells.SpellFishScales;
import com.perceus.spellcasting2.water_spells.SpellGills;
import com.perceus.spellcasting2.water_spells.SpellMendingWater;
import com.perceus.spellcasting2.water_spells.SpellRagingCurrent;
import com.perceus.spellcasting2.water_spells.SpellReconstitute;
import com.perceus.spellcasting2.water_spells.SpellRipTide;
import com.perceus.spellcasting2.water_spells.SpellSaturate;
import com.perceus.spellcasting2.water_spells.SpellSoak;
import com.perceus.spellcasting2.water_spells.SpellSplash;
import com.perceus.spellcasting2.water_spells.SpellSuffocate;
import com.perceus.spellcasting2.water_spells.SpellTidalForce;
import com.perceus.spellcasting2.water_spells.SpellWaveWake;

import fish.yukiemeralis.eden.surface2.GuiUtils;
import fish.yukiemeralis.eden.surface2.SimpleComponentBuilder;
import fish.yukiemeralis.eden.surface2.SurfaceGui;
import fish.yukiemeralis.eden.surface2.component.GuiComponent;
import fish.yukiemeralis.eden.surface2.enums.DefaultClickAction;

public class SpellGUI_Water extends SurfaceGui
{

	public SpellGUI_Water()
	{
		super(54, "Water Spells", DefaultClickAction.CANCEL, InventoryAction.PICKUP_ALL, InventoryAction.PICKUP_HALF);
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
		
		this.updateSingleComponent(player, 28, PlayerAccountManagement.hasSpellUnlocked((Player) player, new SpellRipTide())?new SpellRipTide():p);
		this.updateSingleComponent(player, 29, PlayerAccountManagement.hasSpellUnlocked((Player) player, new SpellReconstitute())?new SpellReconstitute():p);
		this.updateSingleComponent(player, 30, PlayerAccountManagement.hasSpellUnlocked((Player) player, new SpellTidalForce())?new SpellTidalForce():p);
		this.updateSingleComponent(player, 31, PlayerAccountManagement.hasSpellUnlocked((Player) player, new SpellFishScales())?new SpellFishScales():p);
		this.updateSingleComponent(player, 32, PlayerAccountManagement.hasSpellUnlocked((Player) player, new SpellSoak())?new SpellSoak():p);
		this.updateSingleComponent(player, 33, PlayerAccountManagement.hasSpellUnlocked((Player) player, new SpellSaturate())?new SpellSaturate():p);
		this.updateSingleComponent(player, 34, PlayerAccountManagement.hasSpellUnlocked((Player) player, new SpellWaveWake())?new SpellWaveWake():p);
		this.updateSingleComponent(player, 37, PlayerAccountManagement.hasSpellUnlocked((Player) player, new SpellRagingCurrent())?new SpellRagingCurrent():p);
		this.updateSingleComponent(player, 38, PlayerAccountManagement.hasSpellUnlocked((Player) player, new SpellGills())?new SpellGills():p);
		this.updateSingleComponent(player, 39, PlayerAccountManagement.hasSpellUnlocked((Player) player, new SpellSplash())?new SpellSplash():p);
		this.updateSingleComponent(player, 40, PlayerAccountManagement.hasSpellUnlocked((Player) player, new SpellConduitEther())?new SpellConduitEther():p);
		this.updateSingleComponent(player, 41, PlayerAccountManagement.hasSpellUnlocked((Player) player, new SpellSuffocate())?new SpellSuffocate():p);
		this.updateSingleComponent(player, 42, PlayerAccountManagement.hasSpellUnlocked((Player) player, new SpellMendingWater())?new SpellMendingWater():p);
		this.updateSingleComponent(player, 43, PlayerAccountManagement.hasSpellUnlocked((Player) player, new SpellCrystalize())?new SpellCrystalize():p);
		
	}

}
