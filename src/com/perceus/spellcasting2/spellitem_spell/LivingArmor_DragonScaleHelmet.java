package com.perceus.spellcasting2.spellitem_spell;

import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import com.perceus.spellcasting2.BaseSpellCapsule;
import com.perceus.spellcasting2.SpellParticles;

import fish.yukiemeralis.eden.Eden;
import fish.yukiemeralis.eden.utils.ItemUtils;
import fish.yukiemeralis.eden.utils.PrintUtils;

public class LivingArmor_DragonScaleHelmet extends BaseSpellCapsule
{

	public LivingArmor_DragonScaleHelmet()
	{
		super(Material.NETHERITE_HELMET, "§r§b§lLiving §r§eArmor§f: Dragonscale Helmet", "LivingArmor_DragonScaleHelmet", 0, false, "§r§fElement: §r§6§lConstruct§r§f.",
				"§r§fA Netherite Helmet infused with §r§6§lConstruct§r§f energy.",
				"§r§fThis armor has magical properties,",
				"§r§fhowever does not cost mana.",
				"§r§fRight-Click to equip.",
				"§r§fWhile worn, grants water breathing and night vision.");
	}

	@Override
	public boolean cast(PlayerInteractEvent event)
	{
		event.setCancelled(false);
		event.setUseInteractedBlock(Event.Result.ALLOW);
		event.setUseItemInHand(Event.Result.ALLOW);
		Player player = event.getPlayer();
		if (event.getAction().equals(Action.RIGHT_CLICK_AIR)) 
		{
			ItemStack helmItem = event.getPlayer().getInventory().getItemInMainHand();
			if (event.getPlayer().getInventory().getItem(EquipmentSlot.HEAD).getType().equals(Material.AIR)) 
			{
				event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.ITEM_ARMOR_EQUIP_NETHERITE, SoundCategory.MASTER, 1, 1);
				SpellParticles.drawDisc(event.getPlayer().getLocation(), 1, 1, 20, Particle.CLOUD, null);
				event.getPlayer().getInventory().setHelmet(helmItem);
				event.getPlayer().getInventory().getItemInMainHand().setAmount(0);
				armorHelmetRunnable(event, player);
				return true;
			}
			
			event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.ITEM_ARMOR_EQUIP_NETHERITE, SoundCategory.MASTER, 1, 1);
			SpellParticles.drawDisc(event.getPlayer().getLocation(), 1, 1, 20, Particle.CLOUD, null);
			event.getPlayer().getInventory().addItem(event.getPlayer().getInventory().getItem(EquipmentSlot.HEAD));
			event.getPlayer().getInventory().getItem(EquipmentSlot.CHEST).setAmount(0);
			event.getPlayer().getInventory().setHelmet(helmItem);
			event.getPlayer().getInventory().getItemInMainHand().setAmount(0);
			armorHelmetRunnable(event, player);
			return true;
		}
		
		return true;
	}
	private static void armorHelmetRunnable(PlayerInteractEvent event, Player player) 
	{
		
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
				
				if (event.getPlayer().getInventory().getItem(EquipmentSlot.HEAD).getType().equals(Material.AIR)) 
				{
					PrintUtils.sendMessage(player,"Dragonscale Helmt has been unequipped.");
					this.cancel();
					return;
				}
				
				if (!ItemUtils.readFromNamespacedKey(event.getPlayer().getInventory().getItem(EquipmentSlot.HEAD), "spellname").equals("LivingArmor_DragonScaleHelmet"))
				{
					PrintUtils.sendMessage(player,"Dragonscale Helmet has been unequipped.");
					this.cancel();
					return;
				}
				player.addPotionEffect(new PotionEffect(PotionEffectType.WATER_BREATHING, 40, 0, true));
				player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 600, 0, true));

			}
		}.runTaskTimer(Eden.getInstance(), 10, 20);
	}
}