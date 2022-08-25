package com.perceus.spellcasting2.holy_spells;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.attribute.Attribute;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import com.perceus.spellcasting2.BaseSpellCapsule;
import com.perceus.spellcasting2.SpellParticles;
import com.perceus.spellcasting2.manamechanic.PlayerDataMana;

import fish.yukiemeralis.eden.Eden;
import fish.yukiemeralis.eden.utils.PrintUtils;

public class SpellUndyingSoul extends BaseSpellCapsule
{

	public SpellUndyingSoul()
	{
		super(Material.ENCHANTED_BOOK, "§r§f§ko§r§fTome: Undying Soul§r§f§ko§r", "SpellUndyingSoul", 500, false, "§r§fElement: §r§f§o§lHoly§r§f.",
				"§r§fSpell Type: §bUtility§f.", 
				"§r§fAn incantation capable of preventing the caster's death.",
				"§r§fIf the caster should die within 1 minute of cast,",
				"§r§fRestore all health and grant 20 seconds of Immunity.",
				"§r§fMana cost: 500 §r§9mana§r§f.");
	}

	@Override
	public boolean cast(PlayerInteractEvent event)
	{
		
		if (event.getPlayer().getGameMode().equals(GameMode.CREATIVE)) 
		{
			PrintUtils.sendMessage(event.getPlayer(),"This spell cannot be cast in creative mode.");
			return false; 
		}
		
		if (!event.getAction().equals(Action.RIGHT_CLICK_AIR)) 
		{
			PrintUtils.sendMessage(event.getPlayer(),"Invalid Cast Method.");
			return false; 
		}
		
		
		SpellParticles.drawDisc(event.getPlayer().getLocation(), 1, 1, 20, Particle.CLOUD, null);
		event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_ENCHANTMENT_TABLE_USE, SoundCategory.MASTER, 1, 1);
		
		long time = System.currentTimeMillis();
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
				if (System.currentTimeMillis()-time >= 60000)
				{
					PrintUtils.sendMessage(event.getPlayer(),"You are no longer pretected from death.");
					this.cancel();
					return;
				} 		
				if (event.getPlayer().getHealth() <= 1 || event.getPlayer().isDead()) 
				{
										
					for (PotionEffectType effect : PotionEffectType.values()) 
					{
						event.getPlayer().removePotionEffect(effect);
					}
					PlayerDataMana.getPlayerData(event.getPlayer().getUniqueId()).setCurrentMana(PlayerDataMana.getPlayerData(event.getPlayer().getUniqueId()).getMinMana());
					Bukkit.broadcastMessage(event.getPlayer().getDisplayName() + " died but was brought back to life via the spell: Undying Soul.");
					event.getPlayer().setHealth(event.getPlayer().getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue());
					event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 400, 99, true));
					event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 400, 99, true));
					event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, 400, 9, true));
					PrintUtils.sendMessage(event.getPlayer(),"You have been saved from death.");
					SpellParticles.drawDisc(event.getPlayer().getLocation(), 2, 2, 40, Particle.SMOKE_LARGE, null);
					event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.ITEM_TOTEM_USE, SoundCategory.MASTER, 1, 1);
					this.cancel();
					return;
				}
			}
		}.runTaskTimer(Eden.getInstance(), 0, 1);
		
		return true;
	}
	
}
