package com.perceus.spellcasting2.astral_spells;

import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import com.perceus.spellcasting2.BaseSpellCapsule;
import com.perceus.spellcasting2.SpellParticles;
import com.perceus.spellcasting2.accounts.StoredInventory;
import com.perceus.spellcasting2.manamechanic.ManaInterface;
import com.perceus.spellcasting2.manamechanic.PlayerDataMana;

import fish.yukiemeralis.eden.utils.ChatUtils;
import fish.yukiemeralis.eden.utils.PrintUtils;

public class SpellSuspendedMatter extends BaseSpellCapsule
{
	
	public SpellSuspendedMatter()
	{
		super(Material.ENCHANTED_BOOK, ChatUtils.of("✩ Astral Tome: Suspended Matter ✩", "8B008B","FFFFFF",""), "SpellSuspendedMatter", 0, false, 
				"§r§fElement: §r§5A§ds§5t§dr§5α§dl§r§f.",
				"§r§fSpell Type: §bUtility§f.",
				"§r§fA §5c§de§bl§3e§cs§4t§6i§eal §r§ftome.",
				"§r§fThis spell allows the caster to suspend matter",
				"§r§fwithin a second inventory to the player.",
				"§r§fOn Left-Click: Create a permanent second inventory.", 
				"§r§fMana cost: 2000 §r§9mana§r§f.",
				"§r§fOn Right-Click: Open the inventory.", 
				"§r§fMana cost: 50 §r§9mana§r§f.");
	}
	
	@Override
	public boolean cast(PlayerInteractEvent event)
	{
		if (event.getAction().equals(Action.LEFT_CLICK_BLOCK)) 
		{
			PrintUtils.sendMessage(event.getPlayer(),"Invalid Cast Method.");
			return false;
		}
		if (event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) 
		{
			PrintUtils.sendMessage(event.getPlayer(),"Invalid Cast Method.");
			return false;
		}
		
		if (event.getAction().equals(Action.LEFT_CLICK_AIR)) 
		{
			
			if (StoredInventory.hasInventory(event.getPlayer()))
			{
				PrintUtils.sendMessage(event.getPlayer(), "You already own a suspended inventory.");
				return false;
			}
			
			PlayerDataMana.getPlayerData(event.getPlayer()).setCurrentMana(PlayerDataMana.getPlayerData(event.getPlayer()).getCurrentMana() - 2000);
			if (PlayerDataMana.getPlayerData(event.getPlayer()).getCurrentMana()<PlayerDataMana.getPlayerData(event.getPlayer()).getMinMana()) 
			{ 
				PlayerDataMana.getPlayerData(event.getPlayer()).setCurrentMana(PlayerDataMana.getPlayerData(event.getPlayer()).getMinMana());
				PrintUtils.sendMessage(event.getPlayer(), "Mana Insufficient.");
				return false;
			}
			ManaInterface.updateScoreBoard(event.getPlayer());
			
			SpellParticles.drawDisc(event.getPlayer().getLocation(), 2, 2, 100, Particle.DRAGON_BREATH, null);
			event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_ENCHANTMENT_TABLE_USE, SoundCategory.MASTER, 1, 1);
			
			StoredInventory.getStoredInventory(event.getPlayer());
			
			PrintUtils.sendMessage(event.getPlayer(), "Inventory Created.");
			return true;
		}
		
		if (event.getAction().equals(Action.RIGHT_CLICK_AIR)) 
		{
			if (!StoredInventory.hasInventory(event.getPlayer()))
			{
				PrintUtils.sendMessage(event.getPlayer(), "You have not yet created a suspended inventory.");
				return false;
			}
			
			PlayerDataMana.getPlayerData(event.getPlayer()).setCurrentMana(PlayerDataMana.getPlayerData(event.getPlayer()).getCurrentMana() - 50);
			if (PlayerDataMana.getPlayerData(event.getPlayer()).getCurrentMana()<PlayerDataMana.getPlayerData(event.getPlayer()).getMinMana()) 
			{ 
				PlayerDataMana.getPlayerData(event.getPlayer()).setCurrentMana(PlayerDataMana.getPlayerData(event.getPlayer()).getMinMana());
				PrintUtils.sendMessage(event.getPlayer(), "Mana Insufficient.");
				return false;
			}
			ManaInterface.updateScoreBoard(event.getPlayer());
			
			SpellParticles.drawDisc(event.getPlayer().getLocation(), 2, 2, 100, Particle.DRAGON_BREATH, null);
			event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_ENDER_CHEST_OPEN, SoundCategory.MASTER, 1, 1);
			
			StoredInventory.getStoredInventory(event.getPlayer()).openInventory(event.getPlayer());
			return true;
		}
		PrintUtils.sendMessage(event.getPlayer(),"FIZZLE!");
		return false;
	}

//	@EventHandler
//	public void onQuit(PlayerQuitEvent event) 
//	{
//		if (storedInventory.containsKey(event.getPlayer()))
//		{
//			
//		}
//	}

}
