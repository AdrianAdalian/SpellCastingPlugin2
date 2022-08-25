package com.perceus.spellcasting2;

import org.bukkit.Material;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import com.perceus.spellcasting2.manamechanic.ManaInterface;
import com.perceus.spellcasting2.manamechanic.PlayerDataMana;

import fish.yukiemeralis.eden.Eden;
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
		
		String spell = ItemUtils.readFromNamespacedKey(held, "spellname");
		
		if (PlayerDataMana.getPlayerData(event.getPlayer().getUniqueId()).getCurrentMana() < PlayerDataMana.getPlayerData(event.getPlayer().getUniqueId()).getMinMana() && PlayerDataMana.getPlayerData(event.getPlayer().getUniqueId()).getCurrentMana() > PlayerDataMana.getPlayerData(event.getPlayer().getUniqueId()).getNegMana()) 
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
					
					if (PlayerDataMana.getPlayerData(event.getPlayer().getUniqueId()).getCurrentMana() > PlayerDataMana.getPlayerData(event.getPlayer().getUniqueId()).getMinMana()) 
					{
						this.cancel();
						return;
					}
					
					event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 40, 2, true));
					event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 40, 2, true));
					event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 40, 2, true));
					return;
				}
			}.runTaskTimer(Eden.getInstance(), 0, 35);
			if (PlayerDataMana.getPlayerData(event.getPlayer().getUniqueId()).getCurrentMana()<CastListener.spell_registry.get(spell).getManaCost()) 
			{
				event.setUseInteractedBlock(Event.Result.DENY);
				event.setUseItemInHand(Event.Result.DENY);
				if(ItemUtils.hasNamespacedKey(held, "spellname")) 
				{
					event.setCancelled(true);
					PrintUtils.sendMessage(event.getPlayer(), "FIZZLE! Mana Reserves Depleted. You have §r§9Mana Sickness§f.");
					return;
				}
				return;
			}
			return;
		}
		
		if (PlayerDataMana.getPlayerData(event.getPlayer().getUniqueId()).getCurrentMana() > PlayerDataMana.getPlayerData(event.getPlayer().getUniqueId()).getMinMana())
		{
			
			if(ItemUtils.hasNamespacedKey(held, "spellname")) 
			{
				event.setUseInteractedBlock(Event.Result.DENY);
				event.setUseItemInHand(Event.Result.DENY);
				if(CastListener.spell_registry.get(spell).cast(event)) 
				{
					PlayerDataMana.getPlayerData(event.getPlayer().getUniqueId()).setCurrentMana(PlayerDataMana.getPlayerData(event.getPlayer().getUniqueId()).getCurrentMana() - CastListener.spell_registry.get(spell).getManaCost());
					ManaInterface.updateScoreBoard(event.getPlayer());
					return;		
				}				
			}
		}
		
			return;
	}
}

//PrintUtils.log(event.getAction().toString());