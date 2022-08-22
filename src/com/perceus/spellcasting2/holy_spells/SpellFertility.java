package com.perceus.spellcasting2.holy_spells;

import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.entity.Breedable;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.perceus.spellcasting2.BaseSpellCapsule;
import com.perceus.spellcasting2.SpellParticles;

import fish.yukiemeralis.eden.utils.PrintUtils;

public class SpellFertility extends BaseSpellCapsule
{

	public SpellFertility()
	{
		super(Material.ENCHANTED_BOOK, "§r§f§ko§r§fTome: Fertility§r§f§ko§r", "SpellFertility", 100, false, "§r§fElement: §r§f§o§lHoly§r§f.","§r§fSpell Type: §bUtility§f §dAOE§f.","§r§fA tome that allows the caster to instantly","§r§fgrow all animals within a radius.","§r§fTargets affected will begin to glow.","§r§fThis spell will also reset breed timers.","§r§fRange: 30 meters.","§r§fMana cost: 100 §r§9mana§r§f.");
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
		
		if (event.getPlayer().getNearbyEntities(30, 30, 30).size() == 0)
		{
			PrintUtils.sendMessage(event.getPlayer(),"Invalid Target.");
			return false;
		}
		
		event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_ENCHANTMENT_TABLE_USE, SoundCategory.MASTER, 1, 1);
		SpellParticles.drawDisc(event.getPlayer().getLocation(), 1, 1, 20, Particle.CLOUD, null);
		
		for (Entity target : event.getPlayer().getNearbyEntities(30, 30, 30))
		{
			
			if (!(target instanceof Breedable)) 
			{
				continue;
			}

			if (((Breedable) target).canBreed()) 
			{
				continue;
			}
			
			SpellParticles.drawDisc(target.getLocation(), 2, 2, 30, Particle.CLOUD, null);
			SpellParticles.drawCylinder(target.getLocation(), 1, 50, 3, 1, Particle.ENCHANTMENT_TABLE, null);
			SpellParticles.drawLine(event.getPlayer().getLocation(), target.getLocation(), 1, Particle.END_ROD, null);
			((LivingEntity) target).addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, 100, 0));
			if (target instanceof Breedable) 
			{
				if (!((Breedable) target).canBreed()) 
				{	
					((Breedable) target).setBreed(true);
				}
			}
		}
		return true;
	}
}
