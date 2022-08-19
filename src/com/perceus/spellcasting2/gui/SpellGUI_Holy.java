package com.perceus.spellcasting2.gui;

import org.bukkit.Material;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;

import com.perceus.spellcasting2.accounts.PlayerAccountManagement;
import com.perceus.spellcasting2.holy_spells.SpellAccolades;
import com.perceus.spellcasting2.holy_spells.SpellAngelicFlight;
import com.perceus.spellcasting2.holy_spells.SpellBarrier;
import com.perceus.spellcasting2.holy_spells.SpellCure;
import com.perceus.spellcasting2.holy_spells.SpellEmitForce;
import com.perceus.spellcasting2.holy_spells.SpellExpellUndead;
import com.perceus.spellcasting2.holy_spells.SpellExtricate;
import com.perceus.spellcasting2.holy_spells.SpellHealingHands;
import com.perceus.spellcasting2.holy_spells.SpellJudgement;
import com.perceus.spellcasting2.holy_spells.SpellLawOfRegression;
import com.perceus.spellcasting2.holy_spells.SpellMagicBolt;
import com.perceus.spellcasting2.holy_spells.SpellPenance;
import com.perceus.spellcasting2.holy_spells.SpellRegenerate;
import com.perceus.spellcasting2.holy_spells.SpellSatiate;

import fish.yukiemeralis.eden.surface2.GuiUtils;
import fish.yukiemeralis.eden.surface2.SimpleComponentBuilder;
import fish.yukiemeralis.eden.surface2.SurfaceGui;
import fish.yukiemeralis.eden.surface2.component.GuiComponent;
import fish.yukiemeralis.eden.surface2.enums.DefaultClickAction;

public class SpellGUI_Holy extends SurfaceGui
{

	public SpellGUI_Holy()
	{
		super(54, "Holy Spells", DefaultClickAction.CANCEL, InventoryAction.PICKUP_ALL, InventoryAction.PICKUP_HALF);
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
		
		this.updateSingleComponent(player, 28, PlayerAccountManagement.hasSpellUnlocked((Player) player, new SpellEmitForce()) ? new SpellEmitForce() : p);
		this.updateSingleComponent(player, 29, PlayerAccountManagement.hasSpellUnlocked((Player) player, new SpellMagicBolt()) ? new SpellMagicBolt() : p);
		this.updateSingleComponent(player, 30, PlayerAccountManagement.hasSpellUnlocked((Player) player, new SpellHealingHands()) ? new SpellHealingHands() : p);
		this.updateSingleComponent(player, 31, PlayerAccountManagement.hasSpellUnlocked((Player) player, new SpellBarrier()) ? new SpellBarrier() : p);
		this.updateSingleComponent(player, 32, PlayerAccountManagement.hasSpellUnlocked((Player) player, new SpellCure()) ? new SpellCure() : p);
		this.updateSingleComponent(player, 33, PlayerAccountManagement.hasSpellUnlocked((Player) player, new SpellSatiate()) ? new SpellSatiate() : p);
		this.updateSingleComponent(player, 34, PlayerAccountManagement.hasSpellUnlocked((Player) player, new SpellLawOfRegression()) ? new SpellLawOfRegression() : p);
		this.updateSingleComponent(player, 37, PlayerAccountManagement.hasSpellUnlocked((Player) player, new SpellExpellUndead()) ? new SpellExpellUndead() : p);
		this.updateSingleComponent(player, 38, PlayerAccountManagement.hasSpellUnlocked((Player) player, new SpellAngelicFlight()) ? new SpellAngelicFlight() : p);
		this.updateSingleComponent(player, 39, PlayerAccountManagement.hasSpellUnlocked((Player) player, new SpellRegenerate()) ? new SpellRegenerate() : p);
		this.updateSingleComponent(player, 40, PlayerAccountManagement.hasSpellUnlocked((Player) player, new SpellAccolades()) ? new SpellAccolades() : p);
		this.updateSingleComponent(player, 41, PlayerAccountManagement.hasSpellUnlocked((Player) player, new SpellExtricate()) ? new SpellExtricate() : p);
		this.updateSingleComponent(player, 42, PlayerAccountManagement.hasSpellUnlocked((Player) player, new SpellPenance()) ? new SpellPenance() : p);
		this.updateSingleComponent(player, 43, PlayerAccountManagement.hasSpellUnlocked((Player) player, new SpellJudgement()) ? new SpellJudgement() : p);
		
	}
//	@EventHandler
//	public void onInteract(InventoryClickEvent event)
//	{
//		
//		if (!isEventApplicable(event, true))
//		    return;
//
//		SurfaceGui gui = SurfaceGui.getOpenedGuis().get(event.getWhoClicked());
//		if (gui.getComponents().containsKey(event.getRawSlot()))
//		{
//		    gui.getComponents().get(event.getRawSlot()).onIconInteract(event);
//		    return;
//		}
//	}
}
