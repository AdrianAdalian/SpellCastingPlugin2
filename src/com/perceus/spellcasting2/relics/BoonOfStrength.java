package com.perceus.spellcasting2.relics;

import org.bukkit.Material;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import com.perceus.spellcasting2.BaseSpellCapsule;
import com.perceus.spellcasting2.manamechanic.ManaInterface;
import com.perceus.spellcasting2.manamechanic.PlayerDataMana;

import fish.yukiemeralis.eden.Eden;
import fish.yukiemeralis.eden.utils.ItemUtils;
import fish.yukiemeralis.eden.utils.PrintUtils;

public class BoonOfStrength extends BaseSpellCapsule
{

	public BoonOfStrength()
	{
		super(Material.NETHER_STAR, "§r§aBoon§r§f: Boon of Strength", "boonofstrength", 0, false, "§r§fElement: §aPri§bmor§edi§6al§f.",
				"§r§fSpell Type: §a§6Buff§r§f.",
				"§r§fA primordial object capable of permanently granting", 
				"§r§fStrength so long as the caster has enough mana to hold it.",
				"§r§fWhile the boon is in the offhand, grant Lv1 strength.",
				"§r§fRight-Click to activate the item. If the caster runs out of mana,",
				"§r§fThe boon's power will be broken and readded to the inventory.",
				"§r§fMana cost: 20 §9mana§f/second.");
	}

	@Override
	public boolean cast(PlayerInteractEvent event)
	{
		
		if (!event.getAction().equals(Action.RIGHT_CLICK_AIR))
		{
			PrintUtils.sendMessage(event.getPlayer(),"Invalid Cast Method.");
			return false;
		}
		
		if (PlayerDataMana.getPlayerData(event.getPlayer().getUniqueId()).getCurrentMana() > PlayerDataMana.getPlayerData(event.getPlayer().getUniqueId()).getMinMana()) 
		{
			
			ItemStack offHand = event.getPlayer().getInventory().getItem(EquipmentSlot.OFF_HAND);
			ItemStack mainHand = event.getPlayer().getInventory().getItem(EquipmentSlot.HAND);			
			if (event.getPlayer().getInventory().getItem(EquipmentSlot.OFF_HAND).getType().equals(Material.AIR))
			{				
				event.getPlayer().getInventory().setItemInOffHand(mainHand);
				mainHand.setAmount(0);
				boonRunnable(event, offHand);
				return true;
			}
			
			event.getPlayer().getInventory().setItemInMainHand(offHand);
			event.getPlayer().getInventory().setItemInOffHand(mainHand);				
			boonRunnable(event, offHand);
			return true;
		}
		PrintUtils.sendMessage(event.getPlayer(),"Mana Insufficient.");
		return false;
	}
	
	private static void boonRunnable(PlayerInteractEvent event, ItemStack offHand) 
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
				
//				System.out.println(event.getPlayer().getInventory().getItem(EquipmentSlot.OFF_HAND) + " Item Found.");
//				System.out.println(ItemUtils.getNamespacedKeysOfType(event.getPlayer().getInventory().getItem(EquipmentSlot.OFF_HAND), PersistentDataType.STRING, " Namespace keys"));

				if (event.getPlayer().getInventory().getItem(EquipmentSlot.OFF_HAND).getType().equals(Material.AIR)) 
				{
					PrintUtils.sendMessage(event.getPlayer(),"The boon has been unequipped.");
					this.cancel();
					return;
				}
				
				if (!ItemUtils.readFromNamespacedKey(event.getPlayer().getInventory().getItem(EquipmentSlot.OFF_HAND), "spellname").equals("boonofstrength"))
				{
					PrintUtils.sendMessage(event.getPlayer(),"The boon has been unequipped.");
					this.cancel();
					return;
				}
				
				if (PlayerDataMana.getPlayerData(event.getPlayer().getUniqueId()).getCurrentMana() <= PlayerDataMana.getPlayerData(event.getPlayer().getUniqueId()).getMinMana()) 
				{
					this.cancel();
					PrintUtils.sendMessage(event.getPlayer(),"The boon has lost it's power. Mana Insufficient. The boon has returned to your inventory.");
					ItemStack item = event.getPlayer().getInventory().getItemInOffHand();
					event.getPlayer().getInventory().addItem(item);
					event.getPlayer().getInventory().getItem(EquipmentSlot.OFF_HAND).setAmount(0);
					return;
				}
				
				PlayerDataMana.getPlayerData(event.getPlayer().getUniqueId()).setCurrentMana(PlayerDataMana.getPlayerData(event.getPlayer().getUniqueId()).getCurrentMana() - 20);
				ManaInterface.updateScoreBoard(event.getPlayer());
				event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 40, 0, true));
			}
		}.runTaskTimer(Eden.getInstance(), 10, 20);
	}
	
}

