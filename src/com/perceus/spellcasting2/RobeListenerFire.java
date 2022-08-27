package com.perceus.spellcasting2;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryInteractEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import fish.yukiemeralis.eden.Eden;
import fish.yukiemeralis.eden.module.annotation.HideFromCollector;
import fish.yukiemeralis.eden.utils.ItemUtils;
import fish.yukiemeralis.eden.utils.PrintUtils;

@HideFromCollector
public class RobeListenerFire implements Listener
{

	public RobeListenerFire() 
	{
		PrintUtils.log("Listener recognized.");
	}
	
	@EventHandler (priority = EventPriority.LOWEST)
	public void onWear(InventoryInteractEvent event)
	{	
	
		PrintUtils.log("Event recognized.");
		
		if (!event.getWhoClicked().getInventory().getType().equals(InventoryType.PLAYER)) 
		{
			return;
		}	
		
		Player player = (Player) event.getWhoClicked();
		String targetSpellArmorType_Fire = null;
	    for (ItemStack item : event.getWhoClicked().getInventory().getArmorContents())
	    {
			if (item == null) // If the player has no item equipped in this slot, return
			{
				PrintUtils.sendMessage(player.getPlayer(),"ItemStack == null; for loop break.");
				return;
			}
			  
			if (item.getType().equals(Material.AIR)) // Docs specify that some armor items can be null, which implies some may be air as well
			{
				PrintUtils.sendMessage(player.getPlayer(),"ItemStack == type: air; for loop break.");
				return;
			}
			  
			if (!ItemUtils.hasNamespacedKey(item, "spellarmoritem_fire")) // I chose "spelArmorItem" with the idea that the value would be the type of "spell armor" in play
			{
				PrintUtils.sendMessage(player.getPlayer(),"ItemStack != null, Itemstack NSK != spellarmoritem_fire; for loop break.");
				return;
			}
			  
			if (targetSpellArmorType_Fire == null) // The targetted type will be the first type it comes across
			{
				PrintUtils.sendMessage(player.getPlayer(),"targetSpellArmorType_Fire == null; for loop break.");
				targetSpellArmorType_Fire = ItemUtils.readFromNamespacedKey(item, "spellarmoritem_fire");
			}
			  
			if (!ItemUtils.readFromNamespacedKey(item, "spellarmoritem_fire").equals(targetSpellArmorType_Fire)) // If not all pieces match type, this check will fail and it'll stop running by this point
			{
				PrintUtils.sendMessage(player.getPlayer(),"Set not complete; for loop break.");
				return;
			}
	      
		  new BukkitRunnable() 
		  {
			  @Override
			  public void run() 
			  {        
				  if(!player.isOnline())
				  {
					  this.cancel();
					  return;
				  }
				  
				  if (event.getWhoClicked().getInventory().getItem(EquipmentSlot.HEAD).getType().equals(Material.AIR) || 
						  event.getWhoClicked().getInventory().getItem(EquipmentSlot.CHEST).getType().equals(Material.AIR) ||
						  event.getWhoClicked().getInventory().getItem(EquipmentSlot.LEGS).getType().equals(Material.AIR) || 
						  event.getWhoClicked().getInventory().getItem(EquipmentSlot.FEET).getType().equals(Material.AIR)) 
				  {
					  PrintUtils.sendMessage(player.getPlayer(),"One or more pieces of the Fire Robes set have been unequipped.");
					  this.cancel();
					  return;
				  }
		  
				  if (!ItemUtils.hasNamespacedKey(event.getWhoClicked().getInventory().getItem(EquipmentSlot.HEAD), "spellarmoritem_fire") || !ItemUtils.hasNamespacedKey(event.getWhoClicked().getInventory().getItem(EquipmentSlot.CHEST), "spellarmoritem_fire") || !ItemUtils.hasNamespacedKey(event.getWhoClicked().getInventory().getItem(EquipmentSlot.LEGS), "spellarmoritem_fire") || !ItemUtils.hasNamespacedKey(event.getWhoClicked().getInventory().getItem(EquipmentSlot.FEET), "spellarmoritem_fire"))
				  {
					  PrintUtils.sendMessage(player.getPlayer(),"One or more pieces of the Fire Robes set have been unequipped.");
					  this.cancel();
					  return;
				  }
				  	  player.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 40, 0, true));
			 }
		 }.runTaskTimer(Eden.getInstance(), 10, 20);
	      return;
	    }
		return;
	}
}
