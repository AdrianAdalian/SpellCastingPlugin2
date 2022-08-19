package com.perceus.spellcasting2.void_spells;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import com.perceus.spellcasting2.BaseSpellCapsule;
import com.perceus.spellcasting2.SpellParticles;

import fish.yukiemeralis.eden.Eden;
import fish.yukiemeralis.eden.utils.PrintUtils;

public class SpellVoidShift extends BaseSpellCapsule
{

	public SpellVoidShift()
	{
		super(Material.ENCHANTED_BOOK, "§r§f§ko§r§fTome: Void Shift§r§f§ko§r", "SpellVoidShift", 350, false, "§r§fElement: §r§3§lVOID§r§f.","§r§fA forbidden tome that allows the caster to temporarily","§r§fshift into the void, free of physical restraints.","§r§fDuration: 15 seconds.","§r§fMana cost: 350 §r§9mana§r§f.");
	}

	@Override
	public boolean cast(PlayerInteractEvent event)
	{
		if (!event.getAction().equals(Action.RIGHT_CLICK_AIR))
		{
			PrintUtils.sendMessage(event.getPlayer(),"Invalid Cast Method.");
			return false;
		}
		
		if(event.getPlayer().getGameMode()==GameMode.ADVENTURE) 
		{
			PrintUtils.sendMessage(event.getPlayer(),"Adventure Mode is not a valid Gamemode for this spell.");
			return false;
		}
		if(event.getPlayer().getGameMode()==GameMode.CREATIVE) 
		{
			PrintUtils.sendMessage(event.getPlayer(),"Creative Mode is not a valid Gamemode for this spell.");
			return false;
		}
		SpellParticles.drawDisc(event.getPlayer().getLocation(), 2, 2, 60, Particle.PORTAL, null);
		event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_ENCHANTMENT_TABLE_USE, SoundCategory.MASTER, 1, 1); 
		event.getPlayer().setGameMode(GameMode.SPECTATOR);
		
		new BukkitRunnable()
		{
			@Override
			public void run()
			{
				event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 30, 0));
			}
		}.runTaskLater(Eden.getInstance(), 240);	
		new BukkitRunnable()
		{
			@Override
			public void run()
			{
				event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_ENCHANTMENT_TABLE_USE, SoundCategory.MASTER, 1, 1); 
				event.getPlayer().setGameMode(GameMode.SURVIVAL);
			}
		}.runTaskLater(Eden.getInstance(), 300);	
		
		return true;
	}

}
