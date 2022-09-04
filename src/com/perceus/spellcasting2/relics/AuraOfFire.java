package com.perceus.spellcasting2.relics;

import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import com.perceus.spellcasting2.BaseSpellCapsule;
import com.perceus.spellcasting2.SpellParticles;
import com.perceus.spellcasting2.manamechanic.ManaInterface;
import com.perceus.spellcasting2.manamechanic.PlayerDataMana;

import fish.yukiemeralis.eden.Eden;
import fish.yukiemeralis.eden.utils.ItemUtils;
import fish.yukiemeralis.eden.utils.PrintUtils;

public class AuraOfFire extends BaseSpellCapsule
{

	public AuraOfFire()
	{
		super(Material.NAME_TAG, "§r§fAura of §cFire", "AuraOfFire", 0, true, "§r§fElement: §aPri§bmor§edi§6al§f.",
				"§r§fSpell Type: §3Aura§f.",
				"§r§fA talisman that emits an aura of §cFire§f energy.",
				"§r§fRight-Click to activate the item.", 
				"§r§fBurn those that are within 10 meters.",
				"§r§fLasts for 10 seconds outside of range.",
				"§r§fIf the caster runs out of mana,",
				"§r§fthe item's power will be broken", 
				"§r§fand readded to the inventory.",
				"§r§fMana cost: 50 §9mana§f/second.");
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
			event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_BEACON_ACTIVATE, SoundCategory.MASTER, 1, 1);
			SpellParticles.drawDisc(event.getPlayer().getLocation(), 1, 1, 20, Particle.LAVA, null);
			ItemStack offHand = event.getPlayer().getInventory().getItem(EquipmentSlot.OFF_HAND);
			ItemStack mainHand = event.getPlayer().getInventory().getItem(EquipmentSlot.HAND);			
			if (event.getPlayer().getInventory().getItem(EquipmentSlot.OFF_HAND).getType().equals(Material.AIR))
			{				
				event.getPlayer().getInventory().setItemInOffHand(mainHand);
				mainHand.setAmount(0);
				boonRunnable(event, offHand);
				return true;
			}
			event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_BEACON_ACTIVATE, SoundCategory.MASTER, 1, 1);
			SpellParticles.drawDisc(event.getPlayer().getLocation(), 1, 1, 20, Particle.LAVA, null);
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
				
				if (event.getPlayer().getInventory().getItem(EquipmentSlot.OFF_HAND).getType().equals(Material.AIR)) 
				{
					this.cancel();
					event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_BEACON_DEACTIVATE, SoundCategory.MASTER, 1, 1);
					PrintUtils.sendMessage(event.getPlayer(),"The talisman has been unequipped.");
					return;
				}
				
				if (!ItemUtils.readFromNamespacedKey(event.getPlayer().getInventory().getItem(EquipmentSlot.OFF_HAND), "spellname").equals("AuraOfFire"))
				{
					this.cancel();
					event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_BEACON_DEACTIVATE, SoundCategory.MASTER, 1, 1);
					PrintUtils.sendMessage(event.getPlayer(),"The talisman has been unequipped.");
					return;
				}
				
				if (PlayerDataMana.getPlayerData(event.getPlayer().getUniqueId()).getCurrentMana() <= PlayerDataMana.getPlayerData(event.getPlayer().getUniqueId()).getMinMana()) 
				{
					this.cancel();
					event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_BEACON_DEACTIVATE, SoundCategory.MASTER, 1, 1);
					PrintUtils.sendMessage(event.getPlayer(),"The talisman has lost it's power. Mana Insufficient. The talisman has returned to your inventory.");
					ItemStack item = event.getPlayer().getInventory().getItemInOffHand();
					event.getPlayer().getInventory().addItem(item);
					event.getPlayer().getInventory().getItem(EquipmentSlot.OFF_HAND).setAmount(0);
					return;
				}
				
				PlayerDataMana.getPlayerData(event.getPlayer().getUniqueId()).setCurrentMana(PlayerDataMana.getPlayerData(event.getPlayer().getUniqueId()).getCurrentMana() - 50);
				ManaInterface.updateScoreBoard(event.getPlayer());
				for (Entity target : event.getPlayer().getNearbyEntities(10, 10, 10)) 
				{
					if (target == null) 
					{
						continue;
					}
					if (!(target instanceof LivingEntity)) 
					{
						continue;
					}
					event.getPlayer().playSound(target.getLocation(), Sound.ENTITY_BLAZE_SHOOT, SoundCategory.MASTER, 1, 1);
					SpellParticles.drawLine(event.getPlayer().getLocation(), target.getLocation(), 1, Particle.LAVA, null);
					((LivingEntity) target).setFireTicks(200);
				}

				
			}
		}.runTaskTimer(Eden.getInstance(), 10, 20);
	}

}
