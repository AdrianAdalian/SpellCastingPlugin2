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
import org.bukkit.scheduler.BukkitRunnable;

import com.perceus.spellcasting2.BaseSpellCapsule;
import com.perceus.spellcasting2.SpellParticles;

import fish.yukiemeralis.eden.Eden;
import fish.yukiemeralis.eden.utils.PrintUtils;

public class SpellCrystalize extends BaseSpellCapsule
{

	public SpellCrystalize()
	{
		super(Material.QUARTZ, "§r§7§ko§r§7§lSpell: §r§fCrystallize§r§7§ko§r", "SpellCrystalize", 1000, true, true, "§r§fElement: §r§9Water§r§f.","§r§fSpell Type: §cOffensive§f §dAOE§f.","§r§fSpikes burst from the casters body crystallizing them","§r§fwhile briefly rendering them immobilized and impervious.","§r§fAny within close proximity are immobilized, weakened and damaged.","§r§fThe effects of the blast also cause targets to glow.","§r§fAfter the caster thaws,","§r§fgrant increased movement speed for 5 seconds.","§r§fDeals 7 hearts of §r§cdamage§r§f.","§r§fDuration for caster: 10 seconds.","§r§fDuration for targets: 20 seconds.","§r§fRange: 5 meters.","§r§fMana cost: 1000 §r§9mana§r§f.");
	}

	@Override
	public boolean cast(PlayerInteractEvent event)
	{
		if (!event.getAction().equals(Action.RIGHT_CLICK_AIR))
		{
			PrintUtils.sendMessage(event.getPlayer(),"Invalid Cast Method.");
			return false;
		}
		
		if (event.getPlayer().getNearbyEntities(5, 5, 5).size() == 0)
		{
			PrintUtils.sendMessage(event.getPlayer(),"Invalid Target.");
			return false;
		}
		
		for (Entity target : event.getPlayer().getNearbyEntities(5, 5, 5)) 
		{
			if (target instanceof LivingEntity)
	    	{
				SpellParticles.drawLine(event.getPlayer().getLocation(), target.getLocation(), 1, Particle.DRIP_WATER, null);
				((Damageable) target).damage(14, event.getPlayer());
	    		((LivingEntity) target).addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 400, 99));
	    		((LivingEntity) target).addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 400, 99));
	    		((LivingEntity) target).addPotionEffect(new PotionEffect(PotionEffectType.GLOWING, 400, 0));
	    	}  
		}	
		SpellParticles.drawDisc(event.getPlayer().getLocation(), 2, 2, 20, Particle.WATER_DROP, null);
		event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_CONDUIT_ACTIVATE, SoundCategory.MASTER, 1, 1);
		event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_GLASS_PLACE, SoundCategory.MASTER, 1, 1);
		event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_AMETHYST_BLOCK_PLACE, SoundCategory.MASTER, 1, 1);
		event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 200, 99));
		event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, 200, 9));
		
		new BukkitRunnable()
		{
		   @Override
		   public void run()
		   {
			   event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_GLASS_BREAK, SoundCategory.MASTER, 1, 1);
			   event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_AMETHYST_BLOCK_BREAK, SoundCategory.MASTER, 1, 1);
			   event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 100, 2));
		   }
		}.runTaskLater(Eden.getInstance(), 205);
		new BukkitRunnable()
		{
		   @Override
		   public void run()
		   {
			   event.getPlayer().playSound(event.getPlayer().getLocation(), Sound.BLOCK_CONDUIT_DEACTIVATE, SoundCategory.MASTER, 1, 1);
		   }
		}.runTaskLater(Eden.getInstance(), 310);
		return true;
	}

}
