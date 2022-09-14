package com.perceus.spellcasting2.astral_spells;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.scheduler.BukkitRunnable;

import com.perceus.spellcasting2.BaseSpellCapsule;
import com.perceus.spellcasting2.SpellParticles;
import com.perceus.spellcasting2.manamechanic.ManaInterface;
import com.perceus.spellcasting2.manamechanic.PlayerDataMana;

import fish.yukiemeralis.eden.Eden;
import fish.yukiemeralis.eden.utils.ChatUtils;
import fish.yukiemeralis.eden.utils.PrintUtils;

public class SpellAstralProjection extends BaseSpellCapsule
{

	public SpellAstralProjection()
	{
		super(Material.ENCHANTED_BOOK, ChatUtils.of("✩ Astral Tome: Astral Projection ✩", "8B008B","FFFFFF",""), "SpellAstralProjection", 0, false, "§r§fElement: §r§5A§ds§5t§dr§5α§dl§r§f.",
				"§r§fSpell Type: §bUtility§f.",
				"§r§fA §5c§de§bl§3e§cs§4t§6i§eal §r§ftome.",
				"§r§fThis tome can eject the caster's consciousness,", 
				"§r§fentering Astral Form and leaving behind an apparition.", 
				"§r§fWhile in Astral Form, the caster can",  
				"§r§fmove freely, however if mana runs out,", 
				"§r§fthe caster will be teleported to their original location.",
				"§r§fExit Astral Form by possessing the apparition.",
				"§r§fIf the apparition is destroyed, the caster will die.",
				"§r§fMana cost: 50 §r§9mana§r§f/second.");
	}
	
	private static Map<UUID, ArmorStand> standInstance = new HashMap<>();
	@Override
	public boolean cast(PlayerInteractEvent event)
	{
		if (event.getPlayer().getGameMode().equals(GameMode.CREATIVE)) 
		{
			PrintUtils.sendMessage(event.getPlayer(),"This spell cannot be cast whilst in Creative Mode.");
			return false; 
		}
		
		if (!event.getAction().equals(Action.RIGHT_CLICK_AIR)) 
		{
			PrintUtils.sendMessage(event.getPlayer(),"Invalid Cast Method.");
			return false; 
		}
		event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_ENCHANTMENT_TABLE_USE, SoundCategory.MASTER, 1, 1);
		SpellParticles.drawDisc(event.getPlayer().getLocation(), 2, 2, 20, Particle.DRAGON_BREATH, null);			
		Entity stand = event.getPlayer().getLocation().getWorld().spawnEntity(event.getPlayer().getLocation(), EntityType.ARMOR_STAND);
		stand.setCustomName(event.getPlayer().getDisplayName() + "'s §r§f§ko §r§d§oAstral Apparition §r§f§ko");
		stand.setCustomNameVisible(true);
		stand.setGlowing(true);
		stand.setPersistent(true);
		((LivingEntity) stand).setRemoveWhenFarAway(false);
		standInstance.put(event.getPlayer().getUniqueId(), (ArmorStand) stand);
		event.getPlayer().setGameMode(GameMode.SPECTATOR);
		boonRunnable(event);
		
		return true;
	}
	private static void boonRunnable(PlayerInteractEvent event) 
	{
		
		new BukkitRunnable() 
		{
			@Override
			public void run() 
			{    
				
				if(!event.getPlayer().isOnline())
				{
					this.cancel();
					standInstance.get(event.getPlayer().getUniqueId()).teleport(new Location(standInstance.get(event.getPlayer().getUniqueId()).getWorld(), 1000d, 1000d, 1000d));
					standInstance.get(event.getPlayer().getUniqueId()).remove();
					standInstance.clear();
					return;
				}
				
				if (PlayerDataMana.getPlayerData(event.getPlayer().getUniqueId()).getCurrentMana() <= PlayerDataMana.getPlayerData(event.getPlayer().getUniqueId()).getMinMana()) 
				{
					this.cancel();
					PrintUtils.sendMessage(event.getPlayer(),"§r§fMana Insufficient. Canceling Astral Form.");
					event.getPlayer().teleport(standInstance.get(event.getPlayer().getUniqueId()).getLocation());
					event.getPlayer().setGameMode(GameMode.SURVIVAL);
					event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_ENCHANTMENT_TABLE_USE, SoundCategory.MASTER, 1, 1);
					SpellParticles.drawDisc(standInstance.get(event.getPlayer().getUniqueId()).getLocation(), 2, 2, 20, Particle.DRAGON_BREATH, null);
					standInstance.get(event.getPlayer().getUniqueId()).teleport(new Location(standInstance.get(event.getPlayer().getUniqueId()).getWorld(), 1000d, 1000d, 1000d));
					standInstance.get(event.getPlayer().getUniqueId()).remove();
					standInstance.clear();
					return;
				}
				
				if (standInstance.get(event.getPlayer().getUniqueId()).isDead()) 
				{
					this.cancel();
					Bukkit.broadcastMessage(event.getPlayer().getDisplayName() + "'s §r§f§ko §r§d§oAstral Apparition §r§f§ko" + " has been destroyed.");
					PrintUtils.sendMessage(event.getPlayer(),"§r§4Warning§f: Your apparition has been §4Destroyed§f.");
					event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_ENCHANTMENT_TABLE_USE, SoundCategory.MASTER, 1, 1);
					event.getPlayer().setGameMode(GameMode.SURVIVAL);
					SpellParticles.drawDisc(standInstance.get(event.getPlayer().getUniqueId()).getLocation(), 2, 2, 20, Particle.DRAGON_BREATH, null);
					event.getPlayer().damage(1000);
					standInstance.clear();
					return;
				}				
				
				if (event.getPlayer().getLocation().getBlockY() < -70)
				{
					this.cancel();
					PrintUtils.sendMessage(event.getPlayer(),"§r§4Warning§f: Entering The_Void Biome. Canceling Astral Form.");
					event.getPlayer().teleport(standInstance.get(event.getPlayer().getUniqueId()).getLocation());
					event.getPlayer().setGameMode(GameMode.SURVIVAL);
					event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_ENCHANTMENT_TABLE_USE, SoundCategory.MASTER, 1, 1);
					SpellParticles.drawDisc(standInstance.get(event.getPlayer().getUniqueId()).getLocation(), 2, 2, 20, Particle.DRAGON_BREATH, null);
					standInstance.get(event.getPlayer().getUniqueId()).teleport(new Location(standInstance.get(event.getPlayer().getUniqueId()).getWorld(), 1000d, 1000d, 1000d));
					standInstance.get(event.getPlayer().getUniqueId()).remove();
					standInstance.clear();
					return;
				}
				
				if (!(event.getPlayer().getSpectatorTarget() == null))
				{
					if (event.getPlayer().getSpectatorTarget().equals(standInstance.get(event.getPlayer().getUniqueId()))) 
					{						
						this.cancel();
						PrintUtils.sendMessage(event.getPlayer(),"§r§f§oYou have returned to your body.");
						event.getPlayer().setGameMode(GameMode.SURVIVAL);
						event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_ENCHANTMENT_TABLE_USE, SoundCategory.MASTER, 1, 1);
						SpellParticles.drawDisc(standInstance.get(event.getPlayer().getUniqueId()).getLocation(), 2, 2, 20, Particle.DRAGON_BREATH, null);	
						standInstance.get(event.getPlayer().getUniqueId()).teleport(new Location(standInstance.get(event.getPlayer().getUniqueId()).getWorld(), 1000d, 1000d, 1000d));
						standInstance.get(event.getPlayer().getUniqueId()).remove();
						standInstance.clear();
					}
					return;
				}
				
				PlayerDataMana.getPlayerData(event.getPlayer().getUniqueId()).setCurrentMana(PlayerDataMana.getPlayerData(event.getPlayer().getUniqueId()).getCurrentMana() - 50);
				ManaInterface.updateScoreBoard(event.getPlayer());
			}
		}.runTaskTimer(Eden.getInstance(), 0, 20);
	}
}
