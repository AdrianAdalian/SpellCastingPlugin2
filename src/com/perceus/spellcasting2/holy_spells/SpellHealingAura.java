package com.perceus.spellcasting2.holy_spells;

import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.perceus.spellcasting2.BaseSpellCapsule;
import com.perceus.spellcasting2.SpellParticles;

import fish.yukiemeralis.eden.utils.PrintUtils;

public class SpellHealingAura extends BaseSpellCapsule
{

	public SpellHealingAura()
	{
		super(Material.ENCHANTED_BOOK, "§r§f§ko§r§fTome: Healing Aura§r§f§ko§r", "SpellHealingAura", 200, false, "§r§fElement: §r§f§o§lHoly§r§f.","§r§fSpell Type: §aSupport§f §dAOE§f.","§r§fA tome with an incantation that heals all within range.","§r§aHeals §r§f5 hearts to self and targets.","§r§fThose affected glow momentarily.","§r§fRange: 10 meters.","§r§fMana cost: 200 §r§9mana§r§f.");
	}

	@Override
	public boolean cast(PlayerInteractEvent event)
	{
		if (!event.getAction().equals(Action.RIGHT_CLICK_AIR))
		{
			PrintUtils.sendMessage(event.getPlayer(),"Invalid Cast Method.");
			return false;
		}
		if (event.getPlayer().getNearbyEntities(30, 30, 30).size() == 0)
		{
			PrintUtils.sendMessage(event.getPlayer(),"Invalid Target.");
			return false;
		}
		SpellParticles.drawDisc(event.getPlayer().getLocation(), 1, 1, 20, Particle.CLOUD, null);
		event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_ENCHANTMENT_TABLE_USE, SoundCategory.MASTER, 1, 1);
		for (Entity target : event.getPlayer().getNearbyEntities(30, 30, 30)) 
		{
			
			if (!(target instanceof Player)) 
			{
				continue;
			}
			
			if (target instanceof Player)
	    	{
				SpellParticles.drawLine(event.getPlayer().getLocation(), target.getLocation(), 1, Particle.END_ROD, null);
				((Player) target).addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, 80, 0, true));	
				SpellParticles.drawDisc(target.getLocation(), 1, 1, 20, Particle.CLOUD, null);

				try
				{
					((Damageable) target).setHealth(event.getPlayer().getHealth()+10);		
				}
				catch(IllegalArgumentException e)
				{		
					SpellParticles.drawLine(event.getPlayer().getLocation(), target.getLocation(), 1, Particle.END_ROD, null);
					((Player) target).addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, 80, 0, true));	
					SpellParticles.drawDisc(target.getLocation(), 1, 1, 20, Particle.CLOUD, null);

					((Damageable) target).setHealth(event.getPlayer().getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue());
				}
	    	}
		}
		
		try
		{
			event.getPlayer().setHealth(event.getPlayer().getHealth()+10);		
		}
		catch(IllegalArgumentException e)
		{			
			event.getPlayer().setHealth(event.getPlayer().getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue());
		}	
		
		return true;
	}

}
