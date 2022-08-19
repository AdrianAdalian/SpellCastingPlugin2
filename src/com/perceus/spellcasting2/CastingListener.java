package com.perceus.spellcasting2;

import org.bukkit.Material;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

import com.perceus.spellcasting2.manamechanic.ManaInterface;
import com.perceus.spellcasting2.manamechanic.PlayerDataMana;

import fish.yukiemeralis.eden.utils.ItemUtils;
import fish.yukiemeralis.eden.utils.PrintUtils;

public class CastingListener implements Listener
{
	@EventHandler
	public void onCast(PlayerInteractEvent event) 
	{
		ItemStack held;
		held = event.getPlayer().getInventory().getItem(EquipmentSlot.HAND) ;
	
		if (event.getHand() == null) 
		{
			return;
		}
		
		if (event.getHand().equals(EquipmentSlot.OFF_HAND))
		{
			return;
		}
		
		//if in offhand, return.
		if(held==null || held.getType().equals(Material.AIR))
		{
			return ;
		}
		//if a spell is being used in the air that otherwise would not be cast in, return.
		if(ItemUtils.hasNamespacedKey(held, "spellname")) 
		{
			event.setUseInteractedBlock(Event.Result.DENY);
			event.setUseItemInHand(Event.Result.DENY);
			
			//PrintUtils.log(event.getAction().toString());

			String spell = ItemUtils.readFromNamespacedKey(held, "spellname");
		
			if (PlayerDataMana.getPlayerData(event.getPlayer()).getCurrentMana()>=CastListener.spell_registry.get(spell).getManaCost()) 
			{
				if(CastListener.spell_registry.get(spell).cast(event)) 
				{
					PlayerDataMana.getPlayerData(event.getPlayer()).setCurrentMana(PlayerDataMana.getPlayerData(event.getPlayer()).getCurrentMana() - CastListener.spell_registry.get(spell).getManaCost());
					ManaInterface.updateScoreBoard(event.getPlayer());
					return;		
				}
				return;
			}
			//mana cost
			PrintUtils.sendMessage(event.getPlayer(), "Mana Insufficient.");
		
			return;
		}
	}
}
