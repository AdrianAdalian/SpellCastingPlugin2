package com.perceus.spellcasting2.spellitem_spell;

import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.entity.AbstractArrow.PickupStatus;
import org.bukkit.entity.Arrow;
import org.bukkit.event.Event;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import com.perceus.spellcasting2.BaseSpellCapsule;
import com.perceus.spellcasting2.SpellParticles;
import com.perceus.spellcasting2.manamechanic.ManaInterface;
import com.perceus.spellcasting2.manamechanic.PlayerDataMana;

import fish.yukiemeralis.eden.Eden;
import fish.yukiemeralis.eden.utils.ChatUtils;
import fish.yukiemeralis.eden.utils.PrintUtils;

public class Armament_Repentance extends BaseSpellCapsule
{

	public Armament_Repentance()
	{
		super(Material.BOW, ChatUtils.of("Armament: Repentance", "FFE748","FFFFFF","§l§o"), "Armament_Repentance", 0, false, "§r§fElement: §r§f§o§lHoly§r§f.",
				"§r§fSpell Type: §cOffensive§f.",
				"§r§fA bow infused with the element of §r§f§o§lHoly§r§f.",
				"§r§6Ability§r§f: Quickshot",
				"§r§fLeft-Click:",
				"§r§fQuickly fire a §r§f§o§lHoly§r§f infused arrow.",
				"§r§fAny hit will glow momentarily.",
				"§r§fDeals 5 hearts of §r§cdamage§r§f.",
				"§r§fMana cost: 25 §r§9mana§r§f.",
				"§r§fThe ability will fail if no arrows are held in offhand.",
				"§r§fAn arrow will be consumed upon use of ability.");
	}

	@Override
	public boolean cast(PlayerInteractEvent event)
	{
		event.setCancelled(false);
		event.setUseInteractedBlock(Event.Result.ALLOW);
		event.setUseItemInHand(Event.Result.ALLOW);
		
		if (event.getAction().equals(Action.LEFT_CLICK_AIR)) 
		{
			if (!(event.getPlayer().getInventory().getItemInOffHand().getType().equals(Material.ARROW))) 
			{
				PrintUtils.sendMessage(event.getPlayer(),"No Arrows Detected in Offhand.");
				return false;
			}
			
			if (event.getPlayer().getInventory().getItemInOffHand().getAmount() > 1) 
			{
				event.getPlayer().getInventory().getItemInOffHand().setAmount(event.getPlayer().getInventory().getItemInOffHand().getAmount() - 1);
			}
			else
			{
				event.getPlayer().getInventory().getItemInOffHand().setAmount(0);
			}
			
			PlayerDataMana.getPlayerData(event.getPlayer().getUniqueId()).setCurrentMana(PlayerDataMana.getPlayerData(event.getPlayer().getUniqueId()).getCurrentMana() - 25);
			ManaInterface.updateScoreBoard(event.getPlayer());
			event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.ENTITY_ARROW_SHOOT, SoundCategory.MASTER, 1, 1);
			SpellParticles.drawDisc(event.getPlayer().getLocation(), 1, 1, 20, Particle.CLOUD, null);
			Arrow arrow = event.getPlayer().launchProjectile(Arrow.class);
			arrow.setPickupStatus(PickupStatus.DISALLOWED);
			arrow.setCritical(false);
			arrow.setKnockbackStrength(0);
			arrow.setDamage(5);
			arrow.setGlowing(true);
			arrow.addCustomEffect(new PotionEffect(PotionEffectType.GLOWING, 200, 0), true);
			new BukkitRunnable()
			{
				@Override
				public void run()
				{
					arrow.remove();
				}
			}.runTaskLater(Eden.getInstance(), 50);
			return true;
		}
		
		return true;
	}

}
