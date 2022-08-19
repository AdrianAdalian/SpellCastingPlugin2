package com.perceus.spellcasting2.lunar_spells;

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

public class SpellLunarEclipse extends BaseSpellCapsule
{

	public SpellLunarEclipse()
	{
		super(Material.ENCHANTED_BOOK, ChatUtils.of("☽ Lunar Tome: Eclipse ☽", "30D5C8","FFFFFF",""), "SpellLunarEclipse", 0, false, "§r§fElement: §r§bL§3u§bn§3α§br§r§f.",
				"§r§fA §5C§de§bl§3e§cs§4t§6i§eal §r§ftome.",
				"§r§fDuring the §r§6Day§r§f:",
				"§r§fChange the current trajectory of the moon,", 
				"§r§fcausing an eclipse to bathe the world in darkness.",
				"§r§fChange §r§6Day§r§f into §r§7Night§r§f.",
				"§r§fMana cost: 500 §r§9mana§r§f.",
				"§r§fIf it is already §r§7Night§f:",
				"§r§fCause all within range to be",
				"§r§fafflicted with the Darkness debuff.",
				"§r§fRange: 200 meters.",
				"§r§fDuration: 5 minutes.",
				"§r§fMana cost: 100 §r§9mana§r§f.");
		// TODO Auto-generated constructor stub
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
		
		if(time >= 0 && time <= 12000)
		{
			PlayerDataMana.getPlayerData(event.getPlayer()).setCurrentMana(PlayerDataMana.getPlayerData(event.getPlayer()).getCurrentMana() - 500);
			if (PlayerDataMana.getPlayerData(event.getPlayer()).getCurrentMana()<PlayerDataMana.getPlayerData(event.getPlayer()).getMinMana()) 
			{
				PlayerDataMana.getPlayerData(event.getPlayer()).setCurrentMana(PlayerDataMana.getPlayerData(event.getPlayer()).getMinMana());
				PrintUtils.sendMessage(event.getPlayer(), "Mana Insufficient.");
				return false;
			}
			ManaInterface.updateScoreBoard(event.getPlayer());
			Bukkit.broadcastMessage("§r§fThe world has fallen into §r§bL§3u§bn§3α§br§r§f eclipse cast by " + event.getPlayer().getDisplayName() + "§r§f.");
			SpellParticles.drawDisc(event.getPlayer().getLocation(), 2, 2, 20, Particle.GLOW_SQUID_INK, null);

			event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_BEACON_POWER_SELECT, SoundCategory.MASTER, 1, 1);
			Bukkit.getWorlds().get(0).setTime(15000);
			//Sets the time to Sunset.
			return true;
		}
		
		PlayerDataMana.getPlayerData(event.getPlayer()).setCurrentMana(PlayerDataMana.getPlayerData(event.getPlayer()).getCurrentMana() - 100);
		if (PlayerDataMana.getPlayerData(event.getPlayer()).getCurrentMana()<PlayerDataMana.getPlayerData(event.getPlayer()).getMinMana()) 
		{
			PlayerDataMana.getPlayerData(event.getPlayer()).setCurrentMana(PlayerDataMana.getPlayerData(event.getPlayer()).getMinMana());
			PrintUtils.sendMessage(event.getPlayer(), "Mana Insufficient.");
			return false;
		}
		ManaInterface.updateScoreBoard(event.getPlayer());
		
		if (event.getPlayer().getNearbyEntities(200, 200, 200).size() == 0)
		{
			PrintUtils.sendMessage(event.getPlayer(),"Invalid Target.");
			return false;
		}
		
		event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_ENCHANTMENT_TABLE_USE, SoundCategory.MASTER, 1, 1);
		SpellParticles.drawDisc(event.getPlayer().getLocation(), 2, 2, 20, Particle.GLOW_SQUID_INK, null);
		
		for (Entity target : event.getPlayer().getNearbyEntities(200, 200, 200))
		{
			if (target instanceof LivingEntity)
	    	{
	    		((LivingEntity) target).addPotionEffect(new PotionEffect(PotionEffectType.DARKNESS, 6000, 0));
	    		if (target instanceof Player) 
	    		{
	    			PrintUtils.sendMessage((Player) target, "The moon has shrouded you in darkness.");
	    		}
	    	}
		}
		return true;
		
	}

}
