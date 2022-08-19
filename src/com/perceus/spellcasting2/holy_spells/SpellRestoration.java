package com.perceus.spellcasting2.holy_spells;

import java.util.List;

import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffectType;

import com.perceus.spellcasting2.BaseSpellCapsule;
import com.perceus.spellcasting2.SpellParticles;
import com.perceus.spellcasting2.manamechanic.ManaInterface;
import com.perceus.spellcasting2.manamechanic.PlayerDataMana;

import fish.yukiemeralis.eden.utils.PrintUtils;

public class SpellRestoration extends BaseSpellCapsule
{

	public SpellRestoration()
	{
		super(Material.ENCHANTED_BOOK, "§r§f§ko§r§fTome: §r§fAura of Restoration§r§f§ko§r", "SpellRestoration", 500, false, "§r§fElement: §r§f§o§lHoly§r§f.","§r§fAn incation written by Archangels", "§r§fcalls home to the pages of this tome.","§r§fThe caster is able to fully restore health and mana,","§r§fand cure diseases and dispell debuffs","§r§fof all those within range.", "§r§fRange: 15 meters.","§r§fMana cost: 500 §r§9mana§r§f.");
	}

	@Override
	public boolean cast(PlayerInteractEvent event)
	{
		if (!event.getAction().equals(Action.RIGHT_CLICK_AIR)) 
		{
			PrintUtils.sendMessage(event.getPlayer(),"Invalid Cast Method.");
			return false;
		}
		
		if (event.getPlayer().getNearbyEntities(15, 15, 15).size() == 0)
		{
			PrintUtils.sendMessage(event.getPlayer(),"Invalid Target.");
			return false;
		}
		
		SpellParticles.drawDisc(event.getPlayer().getLocation(), 2, 2, 20, Particle.CLOUD, null);
		event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_ENCHANTMENT_TABLE_USE, SoundCategory.MASTER, 1, 1);
		
		for (Entity target : event.getPlayer().getNearbyEntities(15, 15, 15))
		{
			
			if (!(target instanceof Player)) 
			{
				continue;
			}
			
			List<PotionEffectType> negEffects = List.of(PotionEffectType.WEAKNESS,
					PotionEffectType.BAD_OMEN,
					PotionEffectType.CONFUSION,
					PotionEffectType.POISON,
					PotionEffectType.SLOW_DIGGING,
					PotionEffectType.WITHER,
					PotionEffectType.BLINDNESS,
					PotionEffectType.DARKNESS,
					PotionEffectType.HUNGER,
					PotionEffectType.UNLUCK,
					PotionEffectType.SLOW);
			
			for (PotionEffectType pet : negEffects) 
			{
				if (target instanceof Player) 
				{
					
					if (!(((Player) target).hasPotionEffect(pet))) 
					{
						continue;
					}
					if (((Player) target).hasPotionEffect(pet)) 
					{
						((Player) target).removePotionEffect(pet);
					}
				}
			}		
			
			if (target instanceof Player) 
			{
				((Player) target).setHealth(((Player) target).getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue());
			}
			
			PlayerDataMana.getPlayerData((Player) target).setCurrentMana(PlayerDataMana.getPlayerData((Player) target).getMaxMana());
			ManaInterface.updateScoreBoard((Player) target);
			
			PrintUtils.sendMessage((Player)target.getLocation(), event.getPlayer().getDisplayName() + " has restored your Mana, Health and routed all ailments.");
			
		}
		
		return true;
	}
}
