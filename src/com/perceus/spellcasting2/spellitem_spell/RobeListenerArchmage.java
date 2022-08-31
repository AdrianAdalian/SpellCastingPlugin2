package com.perceus.spellcasting2.spellitem_spell;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import com.perceus.spellcasting2.manamechanic.ManaInterface;
import com.perceus.spellcasting2.manamechanic.PlayerDataMana;

import fish.yukiemeralis.eden.Eden;
import fish.yukiemeralis.eden.utils.ItemUtils;
import fish.yukiemeralis.eden.utils.PrintUtils;

public class RobeListenerArchmage implements Listener
{
	
	private static List<Player> effectActivePlayer = new ArrayList<>();
	
	@EventHandler
	public void onWear(InventoryClickEvent event)
	{	
		
		if (!event.getWhoClicked().getInventory().getType().equals(InventoryType.PLAYER)) 
		{
			return;
		}	
		
		Player player = (Player) event.getWhoClicked();
		String targetSpellArmorType_Archmage = null;
	    for (ItemStack item : event.getWhoClicked().getInventory().getArmorContents())
	    {
			if (item == null) // If the player has no item equipped in this slot, return
			{
				return;
			}
			  
			if (item.getType().equals(Material.AIR)) // Docs specify that some armor items can be null, which implies some may be air as well
			{
				return;
			}
			
			if (!ItemUtils.hasNamespacedKey(item, "spellarmoritem_archmage")) // I chose "spelArmorItem" with the idea that the value would be the type of "spell armor" in play
			{
				return;
			}
			
			if (targetSpellArmorType_Archmage == null) // The targetted type will be the first type it comes across
			{
				targetSpellArmorType_Archmage = ItemUtils.readFromNamespacedKey(item, "spellarmoritem_archmage");
			}
			  
			if (!ItemUtils.readFromNamespacedKey(item, "spellarmoritem_archmage").equals(targetSpellArmorType_Archmage)) // If not all pieces match type, this check will fail and it'll stop running by this point
			{
				return;
			}
	    }
	    
	    if (effectActivePlayer.contains((Player) event.getWhoClicked()))
	    {
	    	return;
	    }
	    
	    effectActivePlayer.add((Player) event.getWhoClicked());
	    
		  new BukkitRunnable() 
		  {
			  @Override
			  public void run() 
			  {        
				  if(!player.isOnline())
				  {
					  effectActivePlayer.remove((Player) event.getWhoClicked());
					  this.cancel();
					  return;
				  }
				  
				  if (event.getWhoClicked().getInventory().getItem(EquipmentSlot.HEAD).getType().equals(Material.AIR) || 
						  event.getWhoClicked().getInventory().getItem(EquipmentSlot.CHEST).getType().equals(Material.AIR) ||
						  event.getWhoClicked().getInventory().getItem(EquipmentSlot.LEGS).getType().equals(Material.AIR) || 
						  event.getWhoClicked().getInventory().getItem(EquipmentSlot.FEET).getType().equals(Material.AIR)) 
				  {
					  effectActivePlayer.remove((Player) event.getWhoClicked());
					  this.cancel();
					  PrintUtils.sendMessage(player.getPlayer(),"One or more pieces of the Archmage's robe set have been unequipped.");
					  return;
				  }
				  if (!ItemUtils.hasNamespacedKey(event.getWhoClicked().getInventory().getItem(EquipmentSlot.HEAD), "spellarmoritem_archmage") ||
						  !ItemUtils.hasNamespacedKey(event.getWhoClicked().getInventory().getItem(EquipmentSlot.CHEST), "spellarmoritem_archmage") ||
						  !ItemUtils.hasNamespacedKey(event.getWhoClicked().getInventory().getItem(EquipmentSlot.LEGS), "spellarmoritem_archmage") ||
						  !ItemUtils.hasNamespacedKey(event.getWhoClicked().getInventory().getItem(EquipmentSlot.FEET), "spellarmoritem_archmage"))
				  {
					  effectActivePlayer.remove((Player) event.getWhoClicked());
					  this.cancel();
					  PrintUtils.sendMessage(player.getPlayer(),"One or more pieces of the Archmage's robe set have been unequipped.");
					  return;
				  }
				  PlayerDataMana.getPlayerData(player.getUniqueId()).setCurrentMana(PlayerDataMana.getPlayerData(player.getUniqueId()).getCurrentMana() + 150);
				  if (PlayerDataMana.getPlayerData(player.getUniqueId()).getCurrentMana() > PlayerDataMana.getPlayerData(player.getUniqueId()).getMaxMana()) 
				  {
					  PlayerDataMana.getPlayerData(player.getUniqueId()).setCurrentMana(PlayerDataMana.getPlayerData(player.getUniqueId()).getMaxMana());
				  }
				  ManaInterface.updateScoreBoard(player);
			 }
		 }.runTaskTimer(Eden.getInstance(), 10, 20);
	     return;
	}
}