package com.perceus.spellcasting2.water_spells;

import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.perceus.spellcasting2.BaseSpellCapsule;
import com.perceus.spellcasting2.SpellParticles;

import fish.yukiemeralis.eden.utils.PrintUtils;

public class SpellTsunami extends BaseSpellCapsule
{

	public SpellTsunami()
	{
		super(Material.ENCHANTED_BOOK, "§r§f§ko§r§fTome: §r§fTsunami§r§f§ko§r", "SpellTsunami", 1000, false, "§r§fElement: §r§9Water§r§f.","§r§fSpell Type: §cOffensive§f §dAOE§f.","§r§fThis tome expells an intense force of","§r§fwater around the caster.","§r§fThose caught are slowed, fatigued,", "§r§fweakened, and pushed away.","§r§fDeals 5 hearts of §cdamage§f.","§r§fDuration: 30 seconds.","§r§fRange: 50 meters.","§r§fMana cost: 1000 §r§9mana§r§f.");
	}

	@Override
	public boolean cast(PlayerInteractEvent event)
	{
		if (!event.getAction().equals(Action.RIGHT_CLICK_AIR))
		{
			PrintUtils.sendMessage(event.getPlayer(),"Invalid Cast Method.");
			return false;
		}
		
		if (event.getPlayer().getNearbyEntities(50, 50, 50).size() == 0)
		{
			PrintUtils.sendMessage(event.getPlayer(),"Invalid Target.");
			return false;
		}
		
		SpellParticles.drawDisc(event.getPlayer().getLocation(), 2, 2, 20, Particle.WATER_DROP, null);
		event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.AMBIENT_UNDERWATER_EXIT, SoundCategory.MASTER, 1, 1);
		for (Entity target : event.getPlayer().getNearbyEntities(50, 50, 50)) 
		{
			if (!(target instanceof LivingEntity)) 
			{
				continue;
			}
			
			if (target instanceof LivingEntity) 
			{				
				SpellParticles.drawLine(event.getPlayer().getLocation(), target.getLocation(), 1, Particle.DRIP_WATER, null);
				if (target instanceof Damageable) 
				{
					((Damageable) target).damage(10);					
				}
				target.setVelocity(target.getLocation().toVector().subtract(event.getPlayer().getLocation().toVector()));
				((LivingEntity) target).addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 600, 2));
				((LivingEntity) target).addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 600, 2));
				((LivingEntity) target).addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 600, 2));
			}
		}
		return true;
	}

}
