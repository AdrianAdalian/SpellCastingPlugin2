package com.perceus.spellcasting2.accounts;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import com.perceus.spellcasting2.BaseSpellCapsule;
import com.perceus.spellcasting2.CastListener;
import com.perceus.spellcasting2.holy_spells.SpellHeal;
import com.perceus.spellcasting2.recipe_book.SC2RecipeBook;
import com.perceus.spellcasting2.spellitem_recipe.MagicSpellBook_Recipe;

import fish.yukiemeralis.eden.Eden;
import fish.yukiemeralis.eden.permissions.PlayerData;
import fish.yukiemeralis.eden.utils.PrintUtils;

public class PlayerAccountManagement implements Listener
{
	
	@SuppressWarnings("serial")
	private static Map<String, Object> defaultdata = new HashMap<>() 
	{{
		for (String spell : CastListener.spell_registry.keySet()) 
		{
			put(spell, false);
		}
	}};
	
	@EventHandler
	public void onJoin(PlayerJoinEvent event) 
	{
		
		if (!(event.getPlayer().hasPlayedBefore())) 
		{
			PrintUtils.sendMessage(event.getPlayer(), "Welcome to the server, " + event.getPlayer().getDisplayName() + "!");
			PrintUtils.sendMessage(event.getPlayer(), "§fThis server is currently running §4S§cp§6e§el§2l§aC§ba§3s§1t§9i§dn§5g§fPlugin2 (§cv§41.5.1§f)");
			PrintUtils.sendMessage(event.getPlayer(), "This server has custom recipes! Do /spellrecipes to view them.");
			PrintUtils.sendMessage(event.getPlayer(), "Items added to player inventory as first time join event:");
			PrintUtils.sendMessage(event.getPlayer(), "Spellbook, Spell Recipe Book, Spell Page: Magic Missile, Spell: Heal.");
			event.getPlayer().getInventory().addItem(new KBSpellMagicBolt().generate());
			event.getPlayer().getInventory().addItem(new SpellHeal().generate());
			event.getPlayer().getInventory().addItem(SC2RecipeBook.getFinal_item());
			event.getPlayer().getInventory().addItem(MagicSpellBook_Recipe.getFinal_item());
		}
		
		if (event.getPlayer().hasPlayedBefore()) 
		{
			PrintUtils.sendMessage(event.getPlayer(), "§fThis server is currently running §4S§cp§6e§el§2l§aC§ba§3s§1t§9i§dn§5g§fPlugin2 (§cv§41.5.1§f)");
			PrintUtils.sendMessage(event.getPlayer(), "This server has custom recipes! Do /spellrecipes to view them.");
		}
		
		PlayerData account = Eden.getPermissionsManager().getPlayerData(event.getPlayer());
		
		if (!account.hasModuleData("unlockedspells")) 
		{
			account.createModuleData("unlockedspells", defaultdata);
		}
		
		for (String spell : CastListener.spell_registry.keySet()) 
		{
			if (!account.getModuleData("unlockedspells").getModuleData().containsKey(spell)) 
			{
				account.getModuleData("unlockedspells").setValue(spell, false);
			}
		}
	}
	
	public static boolean hasSpellUnlocked(Player player, BaseSpellCapsule spell) 
	{
		return Eden.getPermissionsManager().getPlayerData(player).getModuleData("unlockedspells").getValue(spell.getInternalName(), Boolean.class);
	}
}
