package com.perceus.spellcasting2.astral_spells;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
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

public class SpellTimeDilation extends BaseSpellCapsule
{

	public SpellTimeDilation()
	{
		super(Material.ENCHANTED_BOOK, ChatUtils.of("✩ Astral Tome: Time Dilation ✩", "8B008B","FFFFFF",""), "SpellTimeDilation", 0, false, "§r§fElement: §r§5A§ds§5t§dr§5α§dl§r§f.",
				"§r§fSpell Type: §bUtility§f.",
				"§r§fA §5c§de§bl§3e§cs§4t§6i§eal §r§ftome.",
				"§r§fThis tome allows the caster to dilate time,",   
				"§r§fcausing the caster to be able to cast spells",
				"§r§fand freely lose health. When the effects",
				"§r§fsubside, force teleport to the cast",
				"§r§flocation, and refund all health and mana.",
				"§r§fDuration: 30 seconds.");
	}

	private static Map<UUID, Location> playerLocale = new HashMap<>();
	private static Map<UUID, Double> playerHealth = new HashMap<>();
	private static Map<UUID, Integer> playerMana = new HashMap<>();
	
	@Override
	public boolean cast(PlayerInteractEvent event)
	{

		if (!event.getAction().equals(Action.RIGHT_CLICK_AIR)) 
		{
			PrintUtils.sendMessage(event.getPlayer(),"Invalid Cast Method.");
			return false; 
		}
		
		event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_ENCHANTMENT_TABLE_USE, SoundCategory.MASTER, 1, 1);
		SpellParticles.drawDisc(event.getPlayer().getLocation(), 2, 2, 20, Particle.DRAGON_BREATH, null);			
		playerLocale.put(event.getPlayer().getUniqueId(), event.getPlayer().getLocation());
		playerHealth.put(event.getPlayer().getUniqueId(), event.getPlayer().getHealth());
		playerMana.put(event.getPlayer().getUniqueId(), PlayerDataMana.getPlayerData(event.getPlayer().getUniqueId()).getCurrentMana());
		PrintUtils.sendMessage(event.getPlayer(),"§r§fRecall Point Established.");
		PrintUtils.sendMessage(event.getPlayer(),"§r§f§oTime and space begin to distort around you.");
		
		new BukkitRunnable()
		{
			@Override
			public void run()
			{
				event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_ENCHANTMENT_TABLE_USE, SoundCategory.MASTER, 1, 1);
				SpellParticles.drawDisc(event.getPlayer().getLocation(), 2, 2, 20, Particle.DRAGON_BREATH, null);	
				event.getPlayer().teleport(playerLocale.get(event.getPlayer().getUniqueId()));
				event.getPlayer().setHealth(playerHealth.get(event.getPlayer().getUniqueId()));
				PlayerDataMana.getPlayerData(event.getPlayer().getUniqueId()).setCurrentMana(playerMana.get(event.getPlayer().getUniqueId()));
				ManaInterface.updateScoreBoard(event.getPlayer());
				playerLocale.clear();
				playerHealth.clear();
				playerMana.clear();
			}
		}.runTaskLater(Eden.getInstance(), 600);
		
		return true;
	}

}
