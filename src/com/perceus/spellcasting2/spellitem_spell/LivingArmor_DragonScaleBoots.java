package com.perceus.spellcasting2.spellitem_spell;

import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.enchantments.Enchantment;
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
import com.perceus.spellcasting2.manamechanic.ManaInterface;
import com.perceus.spellcasting2.manamechanic.PlayerDataMana;

import fish.yukiemeralis.eden.Eden;
import fish.yukiemeralis.eden.utils.ItemUtils;
import fish.yukiemeralis.eden.utils.PrintUtils;

public class LivingArmor_DragonScaleBoots extends BaseSpellCapsule
{

	public LivingArmor_DragonScaleBoots()
	{
		super(Material.NETHERITE_BOOTS, "§r§4§lLiving §r§eArmor§f: Dragonscale Chestplate", "LivingArmor_DragonScaleBoots", 0, false, "§r§fElement: §r§6§lConstruct§r§f.",
				"§r§fA Netherite Boots infused with §r§6§lConstruct§r§f energy.",
				"§r§fThis armor has mysterious magical properties,",
				"§r§fhowever does not cost mana to use.",
				"§r§fRight-Click to equip.",
				"§r§fWhile worn, grants Lv1 speed.",
				"§r§f+20 §9mana§f regen/s.");
		
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
			ItemStack bootsItem = event.getPlayer().getInventory().getItemInMainHand();
			if (event.getPlayer().getInventory().getItem(EquipmentSlot.FEET).getType().equals(Material.AIR)) 
			{
				event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.ITEM_ARMOR_EQUIP_NETHERITE, SoundCategory.MASTER, 1, 1);
				SpellParticles.drawDisc(event.getPlayer().getLocation(), 1, 1, 20, Particle.CLOUD, null);
				event.getPlayer().getInventory().setBoots(bootsItem);
				event.getPlayer().getInventory().getItemInMainHand().setAmount(0);
				PrintUtils.sendMessage(player,"You feel the boots begin to sink into your skin, almost as if becoming a part of you.");
				event.getPlayer().getInventory().getItem(EquipmentSlot.FEET).addEnchantment(Enchantment.BINDING_CURSE, 1);
				bootsRunnable(event, player);
				return true;
			}
			
			event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.ITEM_ARMOR_EQUIP_NETHERITE, SoundCategory.MASTER, 1, 1);
			SpellParticles.drawDisc(event.getPlayer().getLocation(), 1, 1, 20, Particle.CLOUD, null);
			event.getPlayer().getInventory().addItem(event.getPlayer().getInventory().getItem(EquipmentSlot.FEET));
			event.getPlayer().getInventory().getItem(EquipmentSlot.FEET).setAmount(0);
			event.getPlayer().getInventory().setBoots(bootsItem);
			event.getPlayer().getInventory().getItemInMainHand().setAmount(0);
			PrintUtils.sendMessage(player,"You feel the boots begin to sink into your skin, almost as if becoming a part of you.");
			event.getPlayer().getInventory().getItem(EquipmentSlot.FEET).addEnchantment(Enchantment.BINDING_CURSE, 1);
			bootsRunnable(event, player);
			return true;
		}
		
		return true;
	}
	public static void bootsRunnable(PlayerInteractEvent event, Player player) 
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
				
				if (event.getPlayer().getInventory().getItem(EquipmentSlot.FEET).getType().equals(Material.AIR)) 
				{
					PrintUtils.sendMessage(player,"Dragonscale Boots has been unequipped.");
					this.cancel();
					return;
				}
				
				if (!ItemUtils.readFromNamespacedKey(event.getPlayer().getInventory().getItem(EquipmentSlot.FEET), "spellname").equals("LivingArmor_DragonScaleBoots"))
				{
					PrintUtils.sendMessage(player,"Dragonscale Boots has been unequipped.");
					this.cancel();
					return;
				}
				PlayerDataMana.getPlayerData(player.getUniqueId()).setCurrentMana(PlayerDataMana.getPlayerData(player.getUniqueId()).getCurrentMana() + 20);
				if (PlayerDataMana.getPlayerData(player.getUniqueId()).getCurrentMana() > PlayerDataMana.getPlayerData(player.getUniqueId()).getMaxMana()) 
				{
					 PlayerDataMana.getPlayerData(player.getUniqueId()).setCurrentMana(PlayerDataMana.getPlayerData(player.getUniqueId()).getMaxMana());
				}
				ManaInterface.updateScoreBoard(player);
				player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 40, 0, true));
			}
		}.runTaskTimer(Eden.getInstance(), 10, 20);
	}
}