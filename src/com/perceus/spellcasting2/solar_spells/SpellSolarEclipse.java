package com.perceus.spellcasting2.solar_spells;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.perceus.spellcasting2.BaseSpellCapsule;
import com.perceus.spellcasting2.SpellParticles;
import com.perceus.spellcasting2.manamechanic.ManaInterface;
import com.perceus.spellcasting2.manamechanic.PlayerDataMana;

import fish.yukiemeralis.eden.utils.ChatUtils;
import fish.yukiemeralis.eden.utils.PrintUtils;

public class SpellSolarEclipse extends BaseSpellCapsule
{

	public SpellSolarEclipse()
	{
		super(Material.ENCHANTED_BOOK, ChatUtils.of("☀ Solar Tome: Solar Eclipse ☀", "F9812B","FFFFFF",""), "SpellSolarEclipse", 0, false, "§r§fElement: §r§6S§eo§6l§eα§6r§r§f.",
				"§r§fA §5C§de§bl§3e§cs§4t§6i§eal §r§ftome.",
				"§r§fDuring the §r§7Night§r§f:",
				"§r§fChange the current trajectory of the sun,", 
				"§r§fcausing a solar eclipse.",
				"§r§fChange §r§7Night§r§f into §r§6Day§r§f.",
				"§r§fMana cost: 400 §r§9mana§r§f.",
				"§r§fIf it is already §r§6Day§f:",
				"§r§fConcentrate harsh sunlight around the caster,",
				"§r§fafflict all within range with the Fatigue debuff.",
				"§r§fRange: 100 meters.",
				"§r§fDuration: 3 minutes.",
				"§r§fMana cost: 200 §r§9mana§r§f.");
	}

	@Override
	public boolean cast(PlayerInteractEvent event)
	{
		if (!event.getAction().equals(Action.RIGHT_CLICK_AIR))
		{
			PrintUtils.sendMessage(event.getPlayer(),"Invalid Cast Method.");
			return false;
		}
		
		long time = Bukkit.getWorlds().get(0).getTime();
		
		if(time >=12005 && time <= 24000)
		{
			PlayerDataMana.getPlayerData(event.getPlayer()).setCurrentMana(PlayerDataMana.getPlayerData(event.getPlayer()).getCurrentMana() - 500);
			if (PlayerDataMana.getPlayerData(event.getPlayer()).getCurrentMana()<PlayerDataMana.getPlayerData(event.getPlayer()).getMinMana()) 
			{
				PlayerDataMana.getPlayerData(event.getPlayer()).setCurrentMana(PlayerDataMana.getPlayerData(event.getPlayer()).getMinMana());
				PrintUtils.sendMessage(event.getPlayer(), "Mana Insufficient.");
				return false;
			}
			ManaInterface.updateScoreBoard(event.getPlayer());
			Bukkit.broadcastMessage("§r§fThe world has fallen into §r§6S§eo§6l§eα§6r§r§f eclipse cast by " + event.getPlayer().getDisplayName() + "§r§f.");
			SpellParticles.drawDisc(event.getPlayer().getLocation(), 2, 2, 20, Particle.LAVA, null);
			SpellParticles.drawDisc(event.getPlayer().getLocation(), 2, 2, 20, Particle.CRIMSON_SPORE, null);
			
			event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_BEACON_POWER_SELECT, SoundCategory.MASTER, 1, 1);
			Bukkit.getWorlds().get(0).setTime(1500);
			//Sets the time to Sunset.
			return true;
		}
		
		PlayerDataMana.getPlayerData(event.getPlayer()).setCurrentMana(PlayerDataMana.getPlayerData(event.getPlayer()).getCurrentMana() - 200);
		if (PlayerDataMana.getPlayerData(event.getPlayer()).getCurrentMana()<PlayerDataMana.getPlayerData(event.getPlayer()).getMinMana()) 
		{
			PlayerDataMana.getPlayerData(event.getPlayer()).setCurrentMana(PlayerDataMana.getPlayerData(event.getPlayer()).getMinMana());
			PrintUtils.sendMessage(event.getPlayer(), "Mana Insufficient.");
			return false;
		}
		ManaInterface.updateScoreBoard(event.getPlayer());
		
		if (event.getPlayer().getNearbyEntities(100, 100, 100).size() == 0)
		{
			PrintUtils.sendMessage(event.getPlayer(),"Invalid Target.");
			return false;
		}
		
		event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_ENCHANTMENT_TABLE_USE, SoundCategory.MASTER, 1, 1);
		SpellParticles.drawDisc(event.getPlayer().getLocation(), 2, 2, 20, Particle.LAVA, null);
		SpellParticles.drawDisc(event.getPlayer().getLocation(), 2, 2, 20, Particle.CRIMSON_SPORE, null);
		
		for (Entity target : event.getPlayer().getNearbyEntities(100, 100, 100))
		{
			if (target instanceof LivingEntity)
	    	{
	    		((LivingEntity) target).addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 3600, 0));
	    		if (target instanceof Player) 
	    		{
	    			PrintUtils.sendMessage((Player) target, "The sun's light has become stronger, you feel fatigued.");
	    		}
	    	}
		}
		return true;
		
	}
}
