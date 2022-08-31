package com.perceus.spellcasting2.accounts;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import com.perceus.spellcasting2.manamechanic.ManaInterface;
import com.perceus.spellcasting2.manamechanic.PlayerDataMana;

import fish.yukiemeralis.eden.Eden;
import fish.yukiemeralis.eden.utils.ItemUtils;
import fish.yukiemeralis.eden.utils.PrintUtils;

public class OnJoinForLivingArmorListener implements Listener
{
	@EventHandler
	public void onJoinArmor(PlayerJoinEvent event) 
	{
		
		if (event.getPlayer().getInventory().getItem(EquipmentSlot.HEAD).hasItemMeta()) 
		{			
			if (ItemUtils.readFromNamespacedKey(event.getPlayer().getInventory().getItem(EquipmentSlot.HEAD), "spellname").equals("LivingArmor_DragonScaleHelmet")) 
			{
				new BukkitRunnable() 
				{
					@Override
					public void run() 
					{        
						if(!event.getPlayer().isOnline())
						{
							this.cancel();
							return;
						}
						
						if (event.getPlayer().getInventory().getItem(EquipmentSlot.HEAD).getType().equals(Material.AIR)) 
						{
							PrintUtils.sendMessage(event.getPlayer(),"Dragonscale Helmet has been unequipped.");
							this.cancel();
							return;
						}
						
						if (!ItemUtils.readFromNamespacedKey(event.getPlayer().getInventory().getItem(EquipmentSlot.HEAD), "spellname").equals("LivingArmor_DragonScaleHelmet"))
						{
							PrintUtils.sendMessage(event.getPlayer(),"Dragonscale Helmet has been unequipped.");
							this.cancel();
							return;
						}
						PlayerDataMana.getPlayerData(event.getPlayer().getUniqueId()).setCurrentMana(PlayerDataMana.getPlayerData(event.getPlayer().getUniqueId()).getCurrentMana() + 20);
						if (PlayerDataMana.getPlayerData(event.getPlayer().getUniqueId()).getCurrentMana() > PlayerDataMana.getPlayerData(event.getPlayer().getUniqueId()).getMaxMana()) 
						{
							PlayerDataMana.getPlayerData(event.getPlayer().getUniqueId()).setCurrentMana(PlayerDataMana.getPlayerData(event.getPlayer().getUniqueId()).getMaxMana());
						}
						ManaInterface.updateScoreBoard(event.getPlayer());
						event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.WATER_BREATHING, 40, 0, true));
						event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 600, 0, true));
						
					}
				}.runTaskTimer(Eden.getInstance(), 10, 20);
			}
		}
		
		if (event.getPlayer().getInventory().getItem(EquipmentSlot.CHEST).hasItemMeta()) 
		{			
			if (ItemUtils.readFromNamespacedKey(event.getPlayer().getInventory().getItem(EquipmentSlot.CHEST), "spellname").equals("LivingArmor_DragonScaleChestplate")) 
			{
				new BukkitRunnable() 
				{
					@Override
					public void run() 
					{        
						if(!event.getPlayer().isOnline())
						{
							this.cancel();
							return;
						}
						
						if (event.getPlayer().getInventory().getItem(EquipmentSlot.CHEST).getType().equals(Material.AIR)) 
						{
							PrintUtils.sendMessage(event.getPlayer(),"Dragonscale Chestplate has been unequipped.");
							this.cancel();
							return;
						}
						
						if (!ItemUtils.readFromNamespacedKey(event.getPlayer().getInventory().getItem(EquipmentSlot.CHEST), "spellname").equals("LivingArmor_DragonScaleChestplate"))
						{
							PrintUtils.sendMessage(event.getPlayer(),"Dragonscale Chestplate has been unequipped.");
							this.cancel();
							return;
						}
						PlayerDataMana.getPlayerData(event.getPlayer().getUniqueId()).setCurrentMana(PlayerDataMana.getPlayerData(event.getPlayer().getUniqueId()).getCurrentMana() + 20);
						if (PlayerDataMana.getPlayerData(event.getPlayer().getUniqueId()).getCurrentMana() > PlayerDataMana.getPlayerData(event.getPlayer().getUniqueId()).getMaxMana()) 
						{
							PlayerDataMana.getPlayerData(event.getPlayer().getUniqueId()).setCurrentMana(PlayerDataMana.getPlayerData(event.getPlayer().getUniqueId()).getMaxMana());
						}
						ManaInterface.updateScoreBoard(event.getPlayer());
						event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 40, 0, true));
						event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 40, 0, true));
					}
				}.runTaskTimer(Eden.getInstance(), 10, 20);
			}
		}
		
		if (event.getPlayer().getInventory().getItem(EquipmentSlot.LEGS).hasItemMeta()) 
		{			
			if (ItemUtils.readFromNamespacedKey(event.getPlayer().getInventory().getItem(EquipmentSlot.LEGS), "spellname").equals("LivingArmor_DragonScaleLeggings")) 
			{
				new BukkitRunnable() 
				{
					@Override
					public void run() 
					{        
						if(!event.getPlayer().isOnline())
						{
							this.cancel();
							return;
						}
						
						if (event.getPlayer().getInventory().getItem(EquipmentSlot.LEGS).getType().equals(Material.AIR)) 
						{
							PrintUtils.sendMessage(event.getPlayer(),"Dragonscale Leggings has been unequipped.");
							this.cancel();
							return;
						}
						
						if (!ItemUtils.readFromNamespacedKey(event.getPlayer().getInventory().getItem(EquipmentSlot.LEGS), "spellname").equals("LivingArmor_DragonScaleLeggings"))
						{
							PrintUtils.sendMessage(event.getPlayer(),"Dragonscale Leggings has been unequipped.");
							this.cancel();
							return;
						}
						PlayerDataMana.getPlayerData(event.getPlayer().getUniqueId()).setCurrentMana(PlayerDataMana.getPlayerData(event.getPlayer().getUniqueId()).getCurrentMana() + 20);
						if (PlayerDataMana.getPlayerData(event.getPlayer().getUniqueId()).getCurrentMana() > PlayerDataMana.getPlayerData(event.getPlayer().getUniqueId()).getMaxMana()) 
						{
							PlayerDataMana.getPlayerData(event.getPlayer().getUniqueId()).setCurrentMana(PlayerDataMana.getPlayerData(event.getPlayer().getUniqueId()).getMaxMana());
						}
						ManaInterface.updateScoreBoard(event.getPlayer());
						event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SLOW_FALLING, 40, 0, true));
						event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 40, 0, true));
					}
				}.runTaskTimer(Eden.getInstance(), 10, 20);
			}
		}
		
		if (event.getPlayer().getInventory().getItem(EquipmentSlot.FEET).hasItemMeta()) 
		{			
			if (ItemUtils.readFromNamespacedKey(event.getPlayer().getInventory().getItem(EquipmentSlot.FEET), "spellname").equals("LivingArmor_DragonScaleBoots")) 
			{
				new BukkitRunnable() 
				{
					@Override
					public void run() 
					{        
						if(!event.getPlayer().isOnline())
						{
							this.cancel();
							return;
						}
						
						if (event.getPlayer().getInventory().getItem(EquipmentSlot.FEET).getType().equals(Material.AIR)) 
						{
							PrintUtils.sendMessage(event.getPlayer(),"Dragonscale Boots has been unequipped.");
							this.cancel();
							return;
						}
						
						if (!ItemUtils.readFromNamespacedKey(event.getPlayer().getInventory().getItem(EquipmentSlot.FEET), "spellname").equals("LivingArmor_DragonScaleBoots"))
						{
							PrintUtils.sendMessage(event.getPlayer(),"Dragonscale Boots has been unequipped.");
							this.cancel();
							return;
						}
						PlayerDataMana.getPlayerData(event.getPlayer().getUniqueId()).setCurrentMana(PlayerDataMana.getPlayerData(event.getPlayer().getUniqueId()).getCurrentMana() + 20);
						if (PlayerDataMana.getPlayerData(event.getPlayer().getUniqueId()).getCurrentMana() > PlayerDataMana.getPlayerData(event.getPlayer().getUniqueId()).getMaxMana()) 
						{
							PlayerDataMana.getPlayerData(event.getPlayer().getUniqueId()).setCurrentMana(PlayerDataMana.getPlayerData(event.getPlayer().getUniqueId()).getMaxMana());
						}
						ManaInterface.updateScoreBoard(event.getPlayer());
						event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 40, 0, true));
					}
				}.runTaskTimer(Eden.getInstance(), 10, 20);
			}
		}
		
		return;
	}
}
