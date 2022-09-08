package com.perceus.spellcasting2.storm_spells;

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

public class SpellTornado extends BaseSpellCapsule
{

	public SpellTornado()
	{
		super(Material.ENCHANTED_BOOK, "§r§fTome: Tornado", "SpellTornado", 1000, false, "§r§fElement: §r§dStorm§r§f.","§r§fSpell Type: §cOffensive§f §dAOE§f.","§r§fThe caster summons an utterly debilitating torrent of wind.","§r§fTargets caught will be crippled and pushed away.","§r§fDeals 5 hearts of §cdamage§f.","§r§fDuration: 15 seconds.","§r§fRange: 50 meters.","§r§fMana cost: 1000 §r§9mana§r§f.");
	}

	@Override
	public boolean cast(PlayerInteractEvent event)
	{
		if (event.getAction().equals(Action.RIGHT_CLICK_AIR)) 
		{
			if (event.getPlayer().getNearbyEntities(50, 50, 50).size() == 0)
			{
				PrintUtils.sendMessage(event.getPlayer(),"Invalid Target.");
				return false;
			}
			
			event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_ENCHANTMENT_TABLE_USE, SoundCategory.MASTER, 1, 1);
			SpellParticles.drawDisc(event.getPlayer().getLocation(), 1, 1, 20, Particle.CLOUD, null);
			
			for (Entity target : event.getPlayer().getNearbyEntities(50, 50, 50))
			{
				if (target instanceof LivingEntity) 
				{					
					SpellParticles.drawLine(event.getPlayer().getLocation(), target.getLocation(), 1, Particle.ELECTRIC_SPARK, null);
					target.setVelocity(target.getLocation().toVector().subtract(event.getPlayer().getLocation().toVector()));
					if (target instanceof Damageable) 
					{						
						((Damageable) target).damage(10);
					}
					((LivingEntity) target).addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 300, 99));
				}
			}
			return true;
		}
		PrintUtils.sendMessage(event.getPlayer(),"Invalid Cast Method.");
		return false;
	}

}
