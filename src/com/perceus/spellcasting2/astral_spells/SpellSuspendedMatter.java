package com.perceus.spellcasting2.astral_spells;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;

import com.perceus.spellcasting2.BaseSpellCapsule;
import com.perceus.spellcasting2.SpellParticles;
import com.perceus.spellcasting2.manamechanic.ManaInterface;
import com.perceus.spellcasting2.manamechanic.PlayerDataMana;

import fish.yukiemeralis.eden.utils.ChatUtils;
import fish.yukiemeralis.eden.utils.PrintUtils;

public class SpellSuspendedMatter extends BaseSpellCapsule
{

	public static Map<Player, Inventory> storedInventory = new HashMap<>();
	public SpellSuspendedMatter()
	{
		super(Material.ENCHANTED_BOOK, ChatUtils.of("✩ Astral Tome: Suspended Matter ✩", "8B008B","FFFFFF",""), "SpellSuspendedMatter", 0, false, 
				"§r§fElement: §r§5A§ds§5t§dr§5α§dl§r§f.",
				"§r§fA §5c§de§bl§3e§cs§4t§6i§eal §r§ftome.",
				"§r§fThis spell allows the caster to suspend matter",
				"§r§fwithin a second inventory to  the player.", 
				"§r§fThis inventory is unstable and will",
				"§r§fdissapear after the caster leaves the server.",
				"§r§fOn Left-Click: Create a temporary inventory.", 
				"§r§fMana cost: 250 §r§9mana§r§f.",
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
			PlayerDataMana.getPlayerData(event.getPlayer()).setCurrentMana(PlayerDataMana.getPlayerData(event.getPlayer()).getCurrentMana() - 250);
			if (PlayerDataMana.getPlayerData(event.getPlayer()).getCurrentMana()<PlayerDataMana.getPlayerData(event.getPlayer()).getMinMana()) 
			{ 
				PlayerDataMana.getPlayerData(event.getPlayer()).setCurrentMana(PlayerDataMana.getPlayerData(event.getPlayer()).getMinMana());
				PrintUtils.sendMessage(event.getPlayer(), "Mana Insufficient.");
				return false;
			}
			ManaInterface.updateScoreBoard(event.getPlayer());
			
			SpellParticles.drawDisc(event.getPlayer().getLocation(), 1, 1, 20, Particle.CLOUD, null);
			SpellParticles.drawDisc(event.getPlayer().getLocation(), 2, 2, 40, Particle.PORTAL, null);
			SpellParticles.drawDisc(event.getPlayer().getLocation(), 3, 3, 20, Particle.CLOUD, null);
			SpellParticles.drawDisc(event.getPlayer().getLocation(), 4, 4, 60, Particle.DRAGON_BREATH, null);
			event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_ENCHANTMENT_TABLE_USE, SoundCategory.MASTER, 1, 1);
			Inventory i = Bukkit.createInventory(event.getPlayer().getInventory().getHolder(), InventoryType.CHEST, "Astral Inventory");
			storedInventory.put(event.getPlayer(), i);
			PrintUtils.sendMessage(event.getPlayer(), "Inventory Created.");
			return true;
		}
		
		if (event.getAction().equals(Action.RIGHT_CLICK_AIR)) 
		{
			PlayerDataMana.getPlayerData(event.getPlayer()).setCurrentMana(PlayerDataMana.getPlayerData(event.getPlayer()).getCurrentMana() - 50);
			if (PlayerDataMana.getPlayerData(event.getPlayer()).getCurrentMana()<PlayerDataMana.getPlayerData(event.getPlayer()).getMinMana()) 
			{ 
				PlayerDataMana.getPlayerData(event.getPlayer()).setCurrentMana(PlayerDataMana.getPlayerData(event.getPlayer()).getMinMana());
				PrintUtils.sendMessage(event.getPlayer(), "Mana Insufficient.");
				return false;
			}
			ManaInterface.updateScoreBoard(event.getPlayer());
			
			if (!storedInventory.containsKey(event.getPlayer())) 
			{
				PrintUtils.sendMessage(event.getPlayer(), "You have not yet created a suspended inventory.");
				return false;
			}
			
			SpellParticles.drawDisc(event.getPlayer().getLocation(), 1, 1, 20, Particle.CLOUD, null);
			SpellParticles.drawDisc(event.getPlayer().getLocation(), 2, 2, 40, Particle.PORTAL, null);
			SpellParticles.drawDisc(event.getPlayer().getLocation(), 3, 3, 20, Particle.CLOUD, null);
			SpellParticles.drawDisc(event.getPlayer().getLocation(), 4, 4, 60, Particle.DRAGON_BREATH, null);
			event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_ENDER_CHEST_OPEN, SoundCategory.MASTER, 1, 1);

			event.getPlayer().openInventory(storedInventory.get(event.getPlayer()));
			
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
