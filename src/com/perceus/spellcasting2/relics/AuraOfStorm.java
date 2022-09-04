package com.perceus.spellcasting2.relics;

import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import com.perceus.spellcasting2.BaseSpellCapsule;
import com.perceus.spellcasting2.SpellParticles;
import com.perceus.spellcasting2.manamechanic.ManaInterface;
import com.perceus.spellcasting2.manamechanic.PlayerDataMana;

import fish.yukiemeralis.eden.Eden;
import fish.yukiemeralis.eden.utils.ItemUtils;
import fish.yukiemeralis.eden.utils.PrintUtils;

public class AuraOfStorm extends BaseSpellCapsule
{

	public AuraOfStorm()
	{
		super(Material.NAME_TAG, "§r§fAura of §dStorm", "AuraOfStorm", 0, true, "§r§fElement: §aPri§bmor§edi§6al§f.",
				"§r§fSpell Type: §3Aura§f.",
				"§r§fA talisman that emits an aura of §dStorm§f energy.",
				"§r§fRight-Click to activate the item.", 
				"§r§fEmit a continual electrical discharge that",
				"§r§fCripples those within 20 meters.",
				"§r§fDeals 1/2 hearts of §cdamage§f/s.",
				"§r§fLasts for 5 seconds outside of range.",
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
				
				if (!ItemUtils.readFromNamespacedKey(event.getPlayer().getInventory().getItem(EquipmentSlot.OFF_HAND), "spellname").equals("AuraOfStorm"))
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
				for (Entity target : event.getPlayer().getNearbyEntities(20, 20, 20)) 
				{
					if (target == null) 
					{
						continue;
					}
					if (!(target instanceof LivingEntity)) 
					{
						continue;
					}
					target.getWorld().playSound(target.getLocation(), Sound.ENTITY_LIGHTNING_BOLT_THUNDER, SoundCategory.MASTER, 1, 1);
					((LivingEntity)target).getWorld().strikeLightningEffect(target.getLocation());
					SpellParticles.drawLine(event.getPlayer().getLocation(), target.getLocation(), 1, Particle.ELECTRIC_SPARK, null);
					((Damageable) target).damage(1);
					((LivingEntity) target).addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 100, 99, true));
				}
			}
		}.runTaskTimer(Eden.getInstance(), 10, 20);
	}
}