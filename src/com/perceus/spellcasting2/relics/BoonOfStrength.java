package com.perceus.spellcasting2.relics;

import org.bukkit.Material;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
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
		super(Material.NETHER_STAR, "§r§e§lRelic§r§f: Boon of Strength", "boonofstrength", 0, false, "§r§fElement: §aPri§bmor§edi§6al§f.",
				"§r§fSpell Type: §a§lBoon§r§f.",
				"§r§fA primordial object capable of permanently granting", 
				"§r§fStrength so long as the caster has enough mana to hold it.",
				"§r§fWhile the boon is in the offhand, grant lv1 strength.",
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
		
		if (PlayerDataMana.getPlayerData(event.getPlayer()).getCurrentMana() > PlayerDataMana.getPlayerData(event.getPlayer()).getMinMana()) 
		{
			
			ItemStack offHand = event.getPlayer().getInventory().getItemInOffHand();
			ItemStack mainHand = event.getPlayer().getInventory().getItemInMainHand();
			
			if (offHand == null || offHand.getType().equals(Material.AIR)) 
			{				
				event.getPlayer().getInventory().setItemInOffHand(mainHand);
				mainHand.setAmount(0);
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
						
						if (offHand.getType().equals(Material.AIR) || ItemUtils.hasNamespacedKey(offHand, "boonofstrength"))
						{
							PrintUtils.sendMessage(event.getPlayer(),"The boon has been unequipped.");
							this.cancel();
							return;
						}
						
						if (PlayerDataMana.getPlayerData(event.getPlayer()).getCurrentMana() <= PlayerDataMana.getPlayerData(event.getPlayer()).getMinMana()) 
						{
							this.cancel();
							PrintUtils.sendMessage(event.getPlayer(),"The boon has lost it's power. You do not have the required mana. The boon has returned to your inventory.");
							ItemStack item = event.getPlayer().getInventory().getItemInOffHand();
							event.getPlayer().getInventory().addItem(item);
							offHand.setAmount(0);
							return;
						}
						
						PlayerDataMana.getPlayerData(event.getPlayer()).setCurrentMana(PlayerDataMana.getPlayerData(event.getPlayer()).getCurrentMana() - 20);
						ManaInterface.updateScoreBoard(event.getPlayer());
						event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 40, 0, true));
					}
				}.runTaskTimer(Eden.getInstance(), 10, 20);
				return true;
			}
			
			event.getPlayer().getInventory().setItemInMainHand(offHand);
			event.getPlayer().getInventory().setItemInOffHand(mainHand);
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
					
					if (offHand.getType().equals(Material.AIR))
					{
						PrintUtils.sendMessage(event.getPlayer(),"The boon has been unequipped.");
						this.cancel();
						return;
					}
					
					if (PlayerDataMana.getPlayerData(event.getPlayer()).getCurrentMana() <= PlayerDataMana.getPlayerData(event.getPlayer()).getMinMana()) 
					{
						this.cancel();
						PrintUtils.sendMessage(event.getPlayer(),"The boon has lost it's power. You do not have the required mana. The boon has returned to your inventory.");
						ItemStack item = event.getPlayer().getInventory().getItemInOffHand();
						event.getPlayer().getInventory().addItem(item);
						offHand.setAmount(0);
						return;
					}
					
					PlayerDataMana.getPlayerData(event.getPlayer()).setCurrentMana(PlayerDataMana.getPlayerData(event.getPlayer()).getCurrentMana() - 20);
					ManaInterface.updateScoreBoard(event.getPlayer());
					event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 40, 0, true));
				}
			}.runTaskTimer(Eden.getInstance(), 10, 20);
			return true;
		}
		PrintUtils.sendMessage(event.getPlayer(),"Mana Insufficient.");
		return false;
	}
}

